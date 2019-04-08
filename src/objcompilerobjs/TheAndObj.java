package objcompilerobjs;
import thelexerobjs.*;

public class TheAndObj extends TheLogicalObj {

   public TheAndObj(TheTokenObj tok, TheExprObj x1, TheExprObj x2) 
   { 
	   super(tok, x1, x2);
	    
   }

   @Override
public void jumping(int t, int f)
   {
	  
      int label = f != 0 ? f : newlabel();
      expr1.jumping(0, label);
      expr2.jumping(t,f);
      if( f == 0 ) emitlabel(label);
   }
}
