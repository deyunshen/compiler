package start;
import java.io.*;

import thelexerobjs.*;
import thelexerobjs.Reader;
import theparserobjs.*;

public class StartCompiler {
	public static void main(String[] args) throws IOException {
	  //读取
	  new Reader();
	  TheLexer lex = new TheLexer();
	  MYParser parse = new MYParser(lex);
	  parse.compilerProgram();
	}
}
