package thelexerobjs;
public class TheRealObj extends TheTokenObj {
	//浮点类型
	public final float value;
	public TheRealObj(float v)
	{ 
		super(TheTagObj.REAL);
		value = v; 
	}
	@Override
	public String toString() 
	{ 
		return "" + value;
	}
}
