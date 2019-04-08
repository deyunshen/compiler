package thesymbolsobjs;
import java.util.*;

import objcompilerobjs.*;
import thelexerobjs.*;

public class SyEnv {

	private Hashtable table;
	protected SyEnv prev;

	public SyEnv(SyEnv n) 
	{
		table = new Hashtable();
		prev = n;
	}

	public void put(TheTokenObj w, TheIdObj i) 
	{ 
		table.put(w, i);
	}

	public TheIdObj get(TheTokenObj w) 
	{
		for( SyEnv e = this; e != null; e = e.prev ) {
			TheIdObj found = (TheIdObj)(e.table.get(w));
			if( found != null ) return found;
		}
		return null;
	}
}
