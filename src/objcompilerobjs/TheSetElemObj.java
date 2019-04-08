package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheSetElemObj extends TheStmtObj {

   public TheIdObj array; public TheExprObj index; public TheExprObj expr;

   public TheSetElemObj(TheAccessObj x, TheExprObj y) 
   {
	  
      array = x.array; index = x.index; expr = y;
      if ( check(x.type, expr.type) == null ) error("type error");
   }

   public SyType check(SyType p1, SyType p2) 
   {
	  
      if ( p1 instanceof SyArray || p2 instanceof SyArray ) return null;
      else if ( p1 == p2 ) return p2;
      else if ( SyType.numeric(p1) && SyType.numeric(p2) ) return p2;
      else return null;
   }

   public void gen(int b, int a) 
   {
	 
      String s1 = index.reduce().toString();
      String s2 = expr.reduce().toString();
      emit(array.toString() + " [ " + s1 + " ] = " + s2);
   }
}
