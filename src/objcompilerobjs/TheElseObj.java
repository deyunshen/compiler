package objcompilerobjs;
import thesymbolsobjs.*;

public class TheElseObj extends TheStmtObj {

   TheExprObj expr; TheStmtObj stmt1, stmt2;

   public TheElseObj(TheExprObj x, TheStmtObj s1, TheStmtObj s2) 
   {
	   
      expr = x; stmt1 = s1; stmt2 = s2;
      if( expr.type != SyType.Bool ) expr.error("boolean required in if");
   }
   public void gen(int b, int a) 
   {
	   
      int label1 = newlabel();   // label1 for stmt1
      int label2 = newlabel();   // label2 for stmt2
      expr.jumping(0,label2);    // fall through to stmt1 on true
      emitlabel(label1);
      stmt1.gen(label1, a);
      emit("goto L" + a);
      emitlabel(label2); 
      stmt2.gen(label2, a);
   }
}
