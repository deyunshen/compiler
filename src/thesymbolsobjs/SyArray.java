package thesymbolsobjs;
import thelexerobjs.*;
public class SyArray extends SyType {
   public SyType of;                  // array *of* type
   public int size = 1;             // number of elements
   public SyArray(int sz, SyType p) 
   {
      super("[]", TheTagObj.INDEX, sz*p.width); size = sz;  of = p;
      
   }
   @Override
public String toString() 
   { 
	   
	   return "[" + size + "] " + of.toString();
   }
}
