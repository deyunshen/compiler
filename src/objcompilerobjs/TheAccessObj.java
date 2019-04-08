package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheAccessObj extends TheOpObj {

   public TheIdObj array;
   public TheExprObj index;

   public TheAccessObj(TheIdObj a, TheExprObj i, SyType p) 
   {    
	  // p is element type after
      super(new TheWordObj("[]", TheTagObj.INDEX), p);  // flattening the array
       
      array = a; index = i;
   }

   public TheExprObj gen() 
   { 
	   return new TheAccessObj(array, index.reduce(), type); 
   }

   public void jumping(int t,int f) 
   { 
	    
	   emitjumps(reduce().toString(),t,f); 
   }

   public String toString() 
   {
	  
      return array.toString() + " [ " + index.toString() + " ]";
   }
}
