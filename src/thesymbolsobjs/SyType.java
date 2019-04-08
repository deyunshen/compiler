package thesymbolsobjs;

import thelexerobjs.TheTagObj;
import thelexerobjs.TheWordObj;

public class SyType   extends TheWordObj {
	public int width = 0; 
	
	public SyType(String s, int tag, int w) 
	   { 
		   super(s, tag);
		   width = w; 
	   }
	
	public static final SyType
	      Int   = new SyType( "int",   TheTagObj.BASIC, 4 ),
	      Float = new SyType( "float", TheTagObj.BASIC, 8 ),
	      Char  = new SyType( "char",  TheTagObj.BASIC, 1 ),
	      Bool  = new SyType( "bool",  TheTagObj.BASIC, 1 );
	
	public static boolean numeric(SyType p) 
	   {
	      if (p == SyType.Char || p == SyType.Int || p == SyType.Float) return true;
	      else return false;
	   }
	
	public static SyType max(SyType p1, SyType p2 ) 
	   {
	      if ( ! numeric(p1) || ! numeric(p2) ) return null;
	      else if ( p1 == SyType.Float || p2 == SyType.Float ) return SyType.Float;
	      else if ( p1 == SyType.Int   || p2 == SyType.Int   ) return SyType.Int;
	      else return SyType.Char;
	   }
	
 	

}
