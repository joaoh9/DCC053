package inter;

import symbols.*;

public class If extends Stmt {
	Expr expr;
	Stmt stmt;

	public If(Expr x, Stmt s) {
		this.expr = x;
		this.stmt = s;

		if (this.expr.type != Type.Bool)
			this.expr.error("boolean required in if");
	}

	public void gen(int b, int a) {
		int label = newlabel();
		this.expr.jumping(0, a);
		emitlabel(label);
		this.stmt.gen(label, a);
	}
}