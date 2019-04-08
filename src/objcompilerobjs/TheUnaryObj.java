package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheUnaryObj extends TheOpObj {

   public TheExprObj expr;

   public TheUnaryObj(TheTokenObj tok, TheExprObj x) 
   {    
	  // handles minus, for ! see Not
      super(tok, null); 
       
      expr = x;
      type = SyType.max(SyType.Int, expr.type);
      if (type == null ) 
    	  error("type error");
   }

   @Override
public TheExprObj gen() 
   { 
	    
	   return new TheUnaryObj(op, expr.reduce()); 
   }

   @Override
public String toString() 
   { 
	   
	   return op.toString()+" "+expr.toString(); 
   }
}
