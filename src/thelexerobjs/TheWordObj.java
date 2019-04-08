package thelexerobjs;

public class TheWordObj extends TheTokenObj {
	public String lexeme= "";
	public TheWordObj(String s,int tag) { super(tag);lexeme=s; }
	@Override
	public String toString() {return lexeme;}
	
	public static final TheWordObj
		and	= new TheWordObj("&&",TheTagObj.AND), or=new TheWordObj("||",TheTagObj.OR),
		eq	= new TheWordObj("==",TheTagObj.EQ),  ne=new TheWordObj("!=",TheTagObj.NE),
		le	= new TheWordObj("<=",TheTagObj.LE),  ge=new TheWordObj(">=",TheTagObj.GE),
		
		minus = new TheWordObj("minus",TheTagObj.MINUS),
		True  = new TheWordObj("true",TheTagObj.TRUE),
		False = new TheWordObj("false",TheTagObj.FALSE),
		temp  = new TheWordObj("t",TheTagObj.TEMP);
}
