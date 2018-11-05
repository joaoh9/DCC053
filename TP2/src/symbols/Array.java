package symbols;

import lexer.*;

public class Array extends Type {
	public Type of; // array of type
	public int size = 1;

	public Array(int sz, Type p) {
		super("[]", Tag.INDEX, sz * p.width);
		this.size = sz;
		this.of = p;
	}

	public String toString() {
		return "[" + this.size + "] " + this.of.toString();
	}
}