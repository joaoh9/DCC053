package inter;

import lexer.*;
import symbols.*;

public class Logical extends Expr {
	public Expr expr1, expr2;

	Logical(Token tok, Expr x1, Expr x2) {
		super(tok, null);
		this.expr1 = x1;
		this.expr2 = x2;
		this.type = check(this.expr1.type, this.expr2.type);

		if (this.type == null)
			error("type error");
	}

	public Type check(Type p1, Type p2) {
		if (p1 == Type.Bool && p2 == Type.Bool)
			return Type.Bool;
		else
			return null;
	}

	public Expr gen() {
		int f = newlabel();
		int a = newlabel();
		Temp temp = new Temp(this.type);
		this.jumping(0, f);
		emit(temp.toString() + " = true");
		emit("goto L" + a);
		emitlabel(f);
		emit(temp.toString() + " = false");
		emitlabel(a);
		return temp;
	}

	public String toString() {
		return this.expr1.toString() + " " + this.op.toString() + " " 
			+ this.expr2.toString();
	}
}