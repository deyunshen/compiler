package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheConstantObj extends TheExprObj {

   public TheConstantObj(TheTokenObj tok, SyType p) 
   { 
	   super(tok, p);
	    
   }
   public TheConstantObj(int i) 
   { 
	   	super(new TheNumObj(i), SyType.Int);
	    
   }

   public static final TheConstantObj
      True  = new TheConstantObj(TheWordObj.True,  SyType.Bool),
      False = new TheConstantObj(TheWordObj.False, SyType.Bool);

   @Override
public void jumping(int t, int f) 
   {
	 
      if ( this == True && t != 0 ) emit("goto L" + t);
      else if ( this == False && f != 0) emit("goto L" + f);
   }
}
