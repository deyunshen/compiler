package objcompilerobjs;

public class TheSeqObj extends TheStmtObj {

   TheStmtObj stmt1; TheStmtObj stmt2;

   public TheSeqObj(TheStmtObj s1, TheStmtObj s2) 
   { 
	
	   stmt1 = s1;
	   stmt2 = s2;
   }

   @Override
public void gen(int b, int a) 
   {
	  
      if ( stmt1 == TheStmtObj.Null ) stmt2.gen(b, a);
      else if ( stmt2 == TheStmtObj.Null ) stmt1.gen(b, a);
      else {
         int label = newlabel();
         stmt1.gen(b,label);
         emitlabel(label);
         stmt2.gen(label,a);
      }
   }
}
