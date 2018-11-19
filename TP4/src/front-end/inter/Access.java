package inter;

import lexer.*;
import symbols.*;

public class Access extends Op {
	public Id array;
	public Expr index;

	public Access(Id a, Expr i, Type p) {
		super(new Word("[]", Tag.INDEX), p);
		this.array = a;
		this.index = i;
	}

	public Expr gen() {
		return new Access(array, this.index.reduce(), this.type);
	}

	public void jumping(int t, int f) {
		emitjumps(reduce().toString(), t, f);
	}

	public String toString() {
		return array.toString() + " [ " + this.index.toString() + " ] ";
	}
}