package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheArithObj extends TheOpObj {

   public TheExprObj expr1, expr2;

   public TheArithObj(TheTokenObj tok, TheExprObj x1, TheExprObj x2)  
   {
      super(tok, null); expr1 = x1; expr2 = x2;
      type = SyType.max(expr1.type, expr2.type);
      if (type == null ) error("type error");
   }

   @Override
public TheExprObj gen() 
   {
	   
      return new TheArithObj(op, expr1.reduce(), expr2.reduce());
   }

   @Override
public String toString() 
   {
	 
      return expr1.toString()+" "+op.toString()+" "+expr2.toString();
   }
}
