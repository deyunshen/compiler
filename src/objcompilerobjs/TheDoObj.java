package objcompilerobjs;
import thesymbolsobjs.*;

public class TheDoObj extends TheStmtObj {

   TheExprObj expr; TheStmtObj stmt;

   public TheDoObj() 
   { 
	   expr = null; 
	   stmt = null; 
   }

   public void init(TheStmtObj s, TheExprObj x) 
   {
      expr = x; stmt = s;
      if( expr.type != SyType.Bool ) expr.error("boolean required in do");
   }

   public void gen(int b, int a) 
   {
      after = a;
      int label = newlabel();   // label for expr
      stmt.gen(b,label);
      emitlabel(label);
      expr.jumping(b,0);
   }
}
