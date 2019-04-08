package thelexerobjs;

public class TheTokenObj {
	public final int tag;
	public TheTokenObj(int t) { tag=t;}
	@Override
	public String toString() {return ""+(char)tag;}

}
