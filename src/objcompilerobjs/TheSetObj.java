package objcompilerobjs;
import thesymbolsobjs.*;

public class TheSetObj extends TheStmtObj {

   public TheIdObj id; public TheExprObj expr;

   public TheSetObj(TheIdObj i, TheExprObj x) 
   {
	   
      id = i; expr = x;
      if ( check(id.type, expr.type) == null ) error("type error");
   }

   public SyType check(SyType p1, SyType p2) 
   {
	   
      if ( SyType.numeric(p1) && SyType.numeric(p2) ) return p2;
      else if ( p1 == SyType.Bool && p2 == SyType.Bool ) return p2;
      else return null;
   }

   @Override
   public void gen(int b, int a) 
	   {
		 
	      emit( id.toString() + " = " + expr.gen().toString() );
	   }
}
