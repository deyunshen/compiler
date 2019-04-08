package objcompilerobjs;
import thelexerobjs.*;
import thesymbolsobjs.*;

public class TheIdObj extends TheExprObj {

	public int offset;     // relative address

	public TheIdObj(TheWordObj id, SyType p, int b) 
	{ 
		super(id, p); 
		
		offset = b; 
	}

//	public String toString() {return "" + op.toString() + offset;}
}
