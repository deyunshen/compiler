package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheLogicalObj extends TheExprObj {

   public TheExprObj expr1, expr2;

   TheLogicalObj(TheTokenObj tok, TheExprObj x1, TheExprObj x2) 
   {
      super(tok, null);                      // null type to start
      
      expr1 = x1; expr2 = x2;
      type = check(expr1.type, expr2.type);
      if (type == null ) error("type error");
   }

   public SyType check(SyType p1, SyType p2) 
   {
	  
      if ( p1 == SyType.Bool && p2 == SyType.Bool ) return SyType.Bool;
      else return null;
   }

	   @Override
	public TheExprObj gen() 
	   {
		   
	      int f = newlabel(); int a = newlabel();
	      TheTempObj temp = new TheTempObj(type);
	      this.jumping(0,f);
	      emit(temp.toString() + " = true");
	      emit("goto L" + a);
	      emitlabel(f); emit(temp.toString() + " = false");
	      emitlabel(a);
	      return temp;
	   }
	
	   @Override
	public String toString() 
	   {
	 
	      return expr1.toString()+" "+op.toString()+" "+expr2.toString();
	   }
}
