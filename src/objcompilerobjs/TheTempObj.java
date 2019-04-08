package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheTempObj extends TheExprObj {

   static int count = 0;
   int number = 0;

   public TheTempObj(SyType p) 
   { 
	   super(TheWordObj.temp, p);
	   number = ++count;
   }

   @Override
public String toString() 
   { 
	   return "t" + number; 
   }
}
