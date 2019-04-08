package objcompilerobjs;
import thesymbolsobjs.*;

public class TheWhileObj extends TheStmtObj {

   TheExprObj expr; TheStmtObj stmt;

   public TheWhileObj() 
   { 
	   expr = null; 
	   stmt = null; 	   
   }

   public void init(TheExprObj x, TheStmtObj s) 
   {
      expr = x;  stmt = s;
      if( expr.type != SyType.Bool ) expr.error("boolean required in while");
   }
   public void gen(int b, int a) 
   {
      after = a;                // save label a
      expr.jumping(0, a);
      int label = newlabel();   // label for stmt
      emitlabel(label); 
      stmt.gen(label, b);
      emit("goto L" + b);
   }
}
