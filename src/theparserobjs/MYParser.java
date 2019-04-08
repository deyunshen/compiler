package theparserobjs;

import java.io.IOException;

import objcompilerobjs.TheAccessObj;
import objcompilerobjs.TheAndObj;
import objcompilerobjs.TheArithObj;
import objcompilerobjs.TheBreakObj;
import objcompilerobjs.TheConstantObj;
import objcompilerobjs.TheDoObj;
import objcompilerobjs.TheElseObj;
import objcompilerobjs.TheExprObj;
import objcompilerobjs.TheIdObj;
import objcompilerobjs.TheIfObj;
import objcompilerobjs.TheNotObj;
import objcompilerobjs.TheOrObj;
import objcompilerobjs.TheRelObj;
import objcompilerobjs.TheSeqObj;
import objcompilerobjs.TheSetElemObj;
import objcompilerobjs.TheSetObj;
import objcompilerobjs.TheStmtObj;
import objcompilerobjs.TheUnaryObj;
import objcompilerobjs.TheWhileObj;
import thelexerobjs.TheNumObj;
import thelexerobjs.TheLexer;
import thelexerobjs.TheTagObj;
import thelexerobjs.TheTokenObj;
import thelexerobjs.TheWordObj;
import thesymbolsobjs.SyArray;
import thesymbolsobjs.SyEnv;
import thesymbolsobjs.SyType;

public class MYParser {
	  private TheLexer lex;    // lexical analyzer for this parser
	  private TheTokenObj look;   // lookahead tagen
	  SyEnv top = null;       // current or top symbol table
	  int used = 0;         // storage used for declarations
	  
	  public MYParser(TheLexer l) throws IOException 
	   {
		   lex = l; move(); 
	   }

	  void move() throws IOException 
	   {
		   look = lex.scan(); 
	   }
	  
	  void error(String s) 
	   { 
		   throw new Error("near line "+TheLexer.line+": "+s); 
	   }
	  
	  void match(int t) throws IOException {
	      if( look.tag == t ) move();
	      else error("syntax error");
	   }
	  
	  public void compilerProgram() throws IOException 
	  {  
		  // program -> block
	      TheStmtObj s = compilerBlock();
	      int begin = s.newlabel(); 
	      int after = s.newlabel();
	      s.emitlabel(begin); 
	      s.gen(begin, after); 
	      s.emitlabel(after);
	   }
	  TheStmtObj compilerBlock() throws IOException {  // block -> { decls stmts }
	      match('{'); 
	      SyEnv savedEnv = top;
	      top = new SyEnv(top);
	      decls();
	      TheStmtObj s = stmts();
	      match('}'); 
	      top = savedEnv;
	      return s;
	   }
	  
	  void decls() throws IOException {
	      while( look.tag == TheTagObj.BASIC ) {   // D -> type ID ;
	         SyType p = type();
	         TheTokenObj tok = look; 
	         match(TheTagObj.ID); 
	         match(';');
	         TheIdObj id = new TheIdObj((TheWordObj)tok, p, used);
	         top.put( tok, id );
	         used = used + p.width;
	      }
	   }
	  
	  SyType type() throws IOException {
		   
	      SyType p = (SyType)look;            // expect look.tag == Tag.BASIC 
	      match(TheTagObj.BASIC);
	      if( look.tag != '[' ) return p; // T -> basic
	      else return dims(p);            // return array type
	   }
	  
	  SyType dims(SyType p) throws IOException {
	      match('[');  TheTokenObj tok = look;  match(TheTagObj.NUM);  match(']');
	      if( look.tag == '[' )
	      p = dims(p);
	      return new SyArray(((TheNumObj)tok).value, p);
	   }
	  
	  TheStmtObj stmts() throws IOException {
		  
	      if ( look.tag == '}' ) return TheStmtObj.Null;
	      else return new TheSeqObj(stmt(), stmts());
	   }
	  
	  TheStmtObj stmt() throws IOException {
			 
	      TheExprObj x;  TheStmtObj s, s1, s2;
	      TheStmtObj savedStmt;         // save enclosing loop for breaks

	      switch( look.tag ) {

	      case ';':
	         move();
	         return TheStmtObj.Null;
	         
	      case TheTagObj.IF:
	          match(TheTagObj.IF); match('('); x = bool(); match(')');
	          s1 = stmt();
	          if( look.tag != TheTagObj.ELSE ) return new TheIfObj(x, s1);
	          match(TheTagObj.ELSE);
	          s2 = stmt();
	          return new TheElseObj(x, s1, s2);
	          
	      case TheTagObj.WHILE:
	          TheWhileObj whilenode = new TheWhileObj();
	          savedStmt = TheStmtObj.Enclosing; TheStmtObj.Enclosing = whilenode;
	          match(TheTagObj.WHILE); match('('); x = bool(); match(')');
	          s1 = stmt();
	          whilenode.init(x, s1);
	          TheStmtObj.Enclosing = savedStmt;  // reset Stmt.Enclosing
	          return whilenode;
	          
	      case TheTagObj.DO:
	          TheDoObj donode = new TheDoObj();
	          savedStmt = TheStmtObj.Enclosing; TheStmtObj.Enclosing = donode;
	          match(TheTagObj.DO);
	          s1 = stmt();
	          match(TheTagObj.WHILE); match('('); x = bool(); match(')'); match(';');
	          donode.init(s1, x);
	          TheStmtObj.Enclosing = savedStmt;  // reset Stmt.Enclosing
	          return donode;
	          
	      case TheTagObj.BREAK:
	          match(TheTagObj.BREAK); match(';');
	          return new TheBreakObj();
 
	      case '{':
	         return compilerBlock();

	      default:
	         return assign();
	      }
	   }
	  
	//处理声明的数据
	   TheStmtObj assign() throws IOException {
		   
	      TheStmtObj stmt ;  
	      TheTokenObj t = look;
	      match(TheTagObj.ID);
	      TheIdObj id = top.get(t);
	      if( id == null ) error(t.toString() + " undeclared");

	      if( look.tag == '=' ) {       // S -> id = E ;
	         move(); 
	         stmt = new TheSetObj(id, bool());
	      }
	      else {                        // S -> L = E ;
	    	  TheAccessObj x = offset(id);
	          match('=');  stmt = new TheSetElemObj(x, bool());
	      }
	      match(';');
	      return stmt;
	   }
	   
	   TheExprObj bool() throws IOException {
			  
		      TheExprObj x = join();
		      while( look.tag == TheTagObj.OR ) {
		         TheTokenObj tok = look;  move();  x = new TheOrObj(tok, x, join());
		      }
		      return x;
		   }
	   TheExprObj join() throws IOException {
		   
		      TheExprObj x = equality();
		      while( look.tag == TheTagObj.AND ) {
		         TheTokenObj tok = look;  move();  x = new TheAndObj(tok, x, equality());
		      }
		      return x;
		   }
	   TheExprObj equality() throws IOException {
		   
		      TheExprObj x = rel();
		      while( look.tag == TheTagObj.EQ || look.tag == TheTagObj.NE ) {
		         TheTokenObj tok = look;  move();  x = new TheRelObj(tok, x, rel());
		      }
		      return x;
		   }
	   
	   TheExprObj rel() throws IOException {
		   
		      TheExprObj x = expr();
		      switch( look.tag ) {
		      case '<': case TheTagObj.LE: case TheTagObj.GE: case '>':
		         TheTokenObj tok = look;  move();  return new TheRelObj(tok, x, expr());
		      default:
		         return x;
		      }
		   }
	   
	   TheExprObj expr() throws IOException {
		   
		      TheExprObj x = term();
		      while( look.tag == '+' || look.tag == '-' ) {
		         TheTokenObj tok = look;  move();  x = new TheArithObj(tok, x, term());
		      }
		      return x;
		   }
	   
	   TheExprObj term() throws IOException {
			 
		      TheExprObj x = unary();
		      while(look.tag == '*' || look.tag == '/' ) {
		         TheTokenObj tok = look;  move();   x = new TheArithObj(tok, x, unary());
		      }
		      return x;
		   }
	   
	   TheExprObj unary() throws IOException {
			  
		      if( look.tag == '-' ) {
		         move();  return new TheUnaryObj(TheWordObj.minus, unary());
		      }
		      else if( look.tag == '!' ) {
		         TheTokenObj tok = look;  move();  return new TheNotObj(tok, unary());
		      }
		      else return factor();
		   }
	  
	   TheExprObj factor() throws IOException {
		   
		      TheExprObj x = null;
		      switch( look.tag ) {
		      case '(':
		         move(); x = bool(); match(')');
		         return x;
		      case TheTagObj.NUM:
		         x = new TheConstantObj(look, SyType.Int);    move(); return x;
		      case TheTagObj.REAL:
		         x = new TheConstantObj(look, SyType.Float);  move(); return x;
		      case TheTagObj.TRUE:
		         x = TheConstantObj.True;                   move(); return x;
		      case TheTagObj.FALSE:
		         x = TheConstantObj.False;                  move(); return x;
		      default:
		         error("syntax error");
		         return x;
		      case TheTagObj.ID:
		         String s = look.toString();
		         TheIdObj id = top.get(look);
		         if( id == null ) error(look.toString() + " undeclared");
		         move();
		         if( look.tag != '[' ) return id;
		         else return offset(id);
		      }
		   }
	   
	   TheAccessObj offset(TheIdObj a) throws IOException {   // I -> [E] | [E] I
			  
		      TheExprObj i; TheExprObj w; TheExprObj t1, t2; TheExprObj loc;  // inherit id

		      SyType type = a.type;
		      match('['); i = bool(); match(']');     // first index, I -> [ E ]
		      type = ((SyArray)type).of;
		      w = new TheConstantObj(type.width);
		      t1 = new TheArithObj(new TheTokenObj('*'), i, w);
		      loc = t1;
		      while( look.tag == '[' ) {      // multi-dimensional I -> [ E ] I
		         match('['); i = bool(); match(']');
		         type = ((SyArray)type).of;
		         w = new TheConstantObj(type.width);
		         t1 = new TheArithObj(new TheTokenObj('*'), i, w);
		         t2 = new TheArithObj(new TheTokenObj('+'), loc, t1);
		         loc = t2;
		      }

		      return new TheAccessObj(a, loc, type);
		   }
}
