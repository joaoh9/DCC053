package inter;

import lexer.*;
import symbols.*;

public class Arith extends Op {
	public Expr expr1, expr2;

	public Arith(Token tok, Expr x1, Expr x2) {
		super(tok, null);
		this.expr1 = x1;
		this.expr2 = x2;
		this.type = Type.max(this.expr1.type, this.expr2.type);

		if (type == null)
			error("type error");
	}

	public Expr gen() {
		return new Arith(op, this.expr1.reduce(), this.expr2.reduce());
	}

	public String toString() {
		return this.expr1.toString() + " " + this.op.toString() + " " +
			this.expr2.toString();
	}
}