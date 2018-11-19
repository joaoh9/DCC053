package inter;

import symbols.*;

public class While extends Stmt {
	Expr expr;
	Stmt stmt;

	public While() {
		this.expr = null;
		this.stmt = null;
	}

	public void init(Expr x, Stmt s) {
		this.expr = x;
		this.stmt = s;

		if (this.expr.type != Type.Bool)
			this.expr.error("boolean required in while");
	}

	public void gen(int b, int a) {
		this.after = a;	// stores label a
		this.expr.jumping(0, a);

		int label = newlabel(); // label for command
		emitlabel(label);
		this.stmt.gen(label, b);
		emit("goto L" + b);
	}
}