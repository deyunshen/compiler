package objcompilerobjs;
import thelexerobjs.*;

public class TheNodeObj {

   int lexline = 0;

   TheNodeObj() 
   { 
	   lexline = TheLexer.line;
   }

   void error(String s) 
   { 
	   throw new Error("near line "+lexline+": "+s); 
   }

   static int labels = 0;

   public int newlabel() 
   { 
	   return ++labels; 
   }

   public void emitlabel(int i) 
   { 
	   System.out.print("指令" + i + ":");
   }

   public void emit(String s) 
   { 
	   System.out.println("\t" + s); 
   }
}
