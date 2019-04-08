package thelexerobjs;

import java.io.IOException;
import java.util.Hashtable;

import thesymbolsobjs.SyType;

public class TheLexer {
	public static int line=1;
	char peek = ' ';
	Hashtable words = new Hashtable();
	void reserve(TheWordObj w) 
	{
		   words.put(w.lexeme, w); 
	}
	
	public TheLexer() 
	 {
	      reserve( new TheWordObj("if",    TheTagObj.IF)    );
	      reserve( new TheWordObj("else",  TheTagObj.ELSE)  );
	      reserve( new TheWordObj("while", TheTagObj.WHILE) );
	      reserve( new TheWordObj("do",    TheTagObj.DO)    );
	      reserve( new TheWordObj("break", TheTagObj.BREAK) );

	      reserve( TheWordObj.True );  reserve( TheWordObj.False );

	      reserve( SyType.Int  );  reserve( SyType.Char  );
	      reserve( SyType.Bool );  reserve( SyType.Float );
	 }
	
	 void readch() throws IOException 
	   { 
		   //peek = (char)System.in.read();
		   peek = this.readFile();
	   }
	 
	 char readFile() throws IOException 
	   {
			char c = (char)(Reader.in.read());
			return c;
	   }
	 
	 boolean readch(char c) throws IOException 
	 {
	      readch();
	      if( peek != c ) return false;
	      peek = ' ';
	      return true;
	 }
	 
	 public TheTokenObj scan() throws IOException {
	      for( ; ; readch() ) {
	         if( peek == ' ' || peek == '\t' ) continue;
	         else if( peek == '\n' ) line = line + 1;
	         else break;
	      }
	      switch( peek ) {
	      case '&':
	         if( readch('&') ) return TheWordObj.and;  else return new TheTokenObj('&');
	      case '|':
	         if( readch('|') ) return TheWordObj.or;   else return new TheTokenObj('|');
	      case '=':
	         if( readch('=') ) return TheWordObj.eq;   else return new TheTokenObj('=');
	      case '!':
	         if( readch('=') ) return TheWordObj.ne;   else return new TheTokenObj('!');
	      case '<':
	         if( readch('=') ) return TheWordObj.le;   else return new TheTokenObj('<');
	      case '>':
	         if( readch('=') ) return TheWordObj.ge;   else return new TheTokenObj('>');
	      }
	      if( Character.isDigit(peek) ) {
	         int v = 0;
	         do {
	            v = 10*v + Character.digit(peek, 10); readch();
	         } while( Character.isDigit(peek) );
	         if( peek != '.' ) return new TheNumObj(v);
	         float x = v; float d = 10;
	         for(;;) {
	            readch();
	            if( ! Character.isDigit(peek) ) break;
	            x = x + Character.digit(peek, 10) / d; d = d*10;
	         }
	         return new TheRealObj(x);
	      }
	      if( Character.isLetter(peek) ) {
	         StringBuffer b = new StringBuffer();
	         do {
	            b.append(peek); readch();
	         } while( Character.isLetterOrDigit(peek) );
	         String s = b.toString();
	         TheWordObj w = (TheWordObj)words.get(s);
	         if( w != null ) return w;
	         w = new TheWordObj(s, TheTagObj.ID);
	         words.put(s, w);
	         return w;
	      } 
	      //-------
	      TheTokenObj tok = new TheTokenObj(peek); peek = ' ';
	      return tok;
	   }
	 
 
}
