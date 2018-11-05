package inter;

import lexer.*;
import symbols.*;

public class Unary extends Op {
	public Expr expr;

	public Unary(Token tok, Expr x) {
		super(tok, null);
		this.expr = x;
		this.type = Type.max(Type.Int, expr.type);

		if (this.type == null)
			error("type error");
	}

	public Expr gen() {
		return new Unary(op, this.expr.reduce());
	}

	public String toString() {
		return this.op.toString() + " " + this.expr.toString();
	}
}