package thelexerobjs;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Reader {
	public static  InputStream in; 
	public Reader() throws FileNotFoundException 
	{
		String path=TheLexer.class.getResource("/").getPath();  
		in= new FileInputStream(path+"input/code.txt");
		 
	}
}
