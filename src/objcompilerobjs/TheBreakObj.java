package objcompilerobjs;

public class TheBreakObj extends TheStmtObj {

   TheStmtObj stmt;

   public TheBreakObj() 
   {
      if( TheStmtObj.Enclosing == TheStmtObj.Null ) error("unenclosed break");
      stmt = TheStmtObj.Enclosing;
   }

   public void gen(int b, int a) 
   {
      emit( "goto L" + stmt.after);
   }
}
