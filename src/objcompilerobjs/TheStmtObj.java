package objcompilerobjs;

public class TheStmtObj extends TheNodeObj {

   public TheStmtObj() 
   {
	   
   }

   public static TheStmtObj Null = new TheStmtObj();

   public void gen(int b, int a) 
   {
	   
   } // called with labels begin and after

   int after = 0;                   // saves label after
   public static TheStmtObj Enclosing = TheStmtObj.Null;  // used for break stmts
}
