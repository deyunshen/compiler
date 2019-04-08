package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheExprObj extends TheNodeObj {

   public TheTokenObj op;
   public SyType type;

   TheExprObj(TheTokenObj tok, SyType p) 
   { 
	  
	   op = tok;
	   type = p;
   }

   public TheExprObj gen() 
   { 
	   
	   return this; 
   }
   public TheExprObj reduce() 
   { 
	    
	   return this; 
   }

   public void jumping(int t, int f) 
   { 
	    
	   emitjumps(toString(), t, f);
   }

   public void emitjumps(String test, int t, int f) 
   {
	 
      if( t != 0 && f != 0 ) {
         emit("if " + test + " goto 指令" + t);
         emit("goto 指令" + f);
      }
      else if( t != 0 ) emit("if " + test + " goto L" + t);
      else if( f != 0 ) emit("iffalse " + test + " goto L" + f);
      else ; // nothing since both t and f fall through
   }
   
   @Override
	public String toString() 
	   { 
		   
		   return op.toString(); 
	   }
}
