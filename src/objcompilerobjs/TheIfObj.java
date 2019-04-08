package objcompilerobjs;
import thesymbolsobjs.*;

public class TheIfObj extends TheStmtObj {

   TheExprObj expr; TheStmtObj stmt;

   public TheIfObj(TheExprObj x, TheStmtObj s) 
   { 
      expr = x;  stmt = s;
      if( expr.type != SyType.Bool ) expr.error("boolean required in if");
   }

   public void gen(int b, int a) 
   { 
      int label = newlabel(); // label for the code for stmt
      expr.jumping(0, a);     // fall through on true, goto a on false
      emitlabel(label); stmt.gen(label, a);
   }
}
