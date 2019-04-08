package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheRelObj extends TheLogicalObj {

   public TheRelObj(TheTokenObj tok, TheExprObj x1, TheExprObj x2) 
   { 
	   super(tok, x1, x2); 
	    
   }

   @Override
public SyType check(SyType p1, SyType p2) 
   {
	   
      if ( p1 instanceof SyArray || p2 instanceof SyArray ) return null;
      else if( p1 == p2 ) return SyType.Bool;
      else return null;
   }

   @Override
public void jumping(int t, int f) 
   {  
      TheExprObj a = expr1.reduce();
      TheExprObj b = expr2.reduce();
      String test = a.toString() + " " + op.toString() + " " + b.toString();
      emitjumps(test, t, f);
   }
}
