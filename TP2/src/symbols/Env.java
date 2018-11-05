package symbols;

import java.util.*;
import lexer .*;
import inter.*;

public class Env {
	private Hashtable table;
	protected Env prev;

	public Env(Env n) {
		this.table = new Hashtable();
		this.prev = n;
	}

	public void put(Token w, Id i) {
		this.table.put(w, i);
	}

	public Id get(Token w) {
		for (Env e = this; e != null; e = e.prev) {
			Id found = (Id)(e.table.get(w));
			if (found != null)
				return found;
		}

		return null;
	}
}