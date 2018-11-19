package inter;

import symbols.*;

public class Do extends Stmt {
	Expr expr;
	Stmt stmt;

	public Do() {
		this.expr = null;
		this.stmt = null;
	}

	public void init(Stmt s, Expr x) {
		this.expr = x;
		this.stmt = s;

		if (this.expr.type != Type.Bool)
			this.expr.error("boolean required in do");
	}

	public void gen(int b, int a) {
		this.after = a;
		int label = newlabel();
		this.stmt.gen(b, label);
		this.expr.jumping(b, 0);
	}
}