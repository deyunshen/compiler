package thelexerobjs;

public class TheNumObj extends TheTokenObj {

	public final int value;
	public TheNumObj(int v) 
	{ 
		super(TheTagObj.NUM);
		value = v;
	}
	@Override
	public String toString() 
	{ 
		return "" + value;
	}
}
