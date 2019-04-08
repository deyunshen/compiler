package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheOpObj extends TheExprObj {

   public TheOpObj(TheTokenObj tok, SyType p)  
   { 
	   super(tok, p);
	
   }

   @Override
public TheExprObj reduce() 
   {
	 
      TheExprObj x = gen();
      TheTempObj t = new TheTempObj(type);
      emit( t.toString() + " = " + x.toString() );
      return t;
   }
}
