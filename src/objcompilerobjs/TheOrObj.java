package objcompilerobjs;
import thelexerobjs.*;

public class TheOrObj extends TheLogicalObj {

   public TheOrObj(TheTokenObj tok, TheExprObj x1, TheExprObj x2) 
   { 
	   super(tok, x1, x2);
	    
   }

   @Override
public void jumping(int t, int f) 
   {
	   
      int label = t != 0 ? t : newlabel();
      expr1.jumping(label, 0);
      expr2.jumping(t,f);
      if( t == 0 ) emitlabel(label);
   }
}
