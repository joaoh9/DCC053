package parser;

import java.io.*;
import lexer.*;
import symbols.*;
import inter.*;

public class Parser {
	private Lexer lex;
	private Token look;
	Env top = null;
	int used = 0;

	public Parser(Lexer l) throws IOException {
		this.lex = l;
		move();
	}

	void move() throws IOException {
		this.look = lex.scan();
	}

	void error(String s) {
		throw new Error("near line" + this.lex.line + ": " + s);
	}

	void match(int t) throws IOException {
		if (this.look.tag == t)
			move();
		else
			error("syntax error");
	}

	public void program() throws IOException { // program -> block
		Stmt s = block();
		int begin = s.newlabel();
		int after = s.newlabel();
		s.emitlabel(begin);
		s.gen(begin, after);
		s.emitlabel(after);
		Node.bw.close();
		Node.fw.close();
	}

	Stmt block() throws IOException { // block -> { decls stmts }
		match('{');
		Env savedEnv = top;
		top = new Env(top);
		decls();
		Stmt s = stmts();
		match('}');
		top = savedEnv;
		return s;
	}

	void decls() throws IOException {
		Id last = null;
		while (this.look.tag == Tag.BASIC) { // D -> type ID ;
			Type p = type();
			Token tok = this.look;
			match(Tag.ID);
			match(';');
			Id id = new Id((Word)tok, p, used);
			id.emit(id.type + " " + id.op);
			top.put(tok, id);
			this.used = used + p.width;
			last = id;
		}

		if (last != null)
			last.emit("");
	}

	Type type() throws IOException {
		Type p = (Type) this.look;	// expects this.look.tag == Tag.BASIC
		match(Tag.BASIC);

		if (this.look.tag != '[')
			return p;	// T -> basic
		else
			return dims(p);	// returns array's type
	}

	Type dims(Type p) throws IOException {
		match('[');
		Token tok = this.look;
		match(Tag.NUM);
		match(']');

		if (this.look.tag == ']')
			p = dims(p);

		return new Array(((Num)tok).value, p);
	}

	Stmt stmts() throws IOException {
		if (this.look.tag == '}')
			return Stmt.Null;
		else
			return new Seq(stmt(), stmts());
	}

	Stmt stmt() throws IOException {
		Expr x;
		Stmt s, s1, s2;
		Stmt savedStmt;

		switch (this.look.tag) {
		case ';':
			move();
			return Stmt.Null;

		case Tag.IF:
			match(Tag.IF);
			match('(');
			x = bool();
			match(')');
			s1 = stmt();

			if (this.look.tag != Tag.ELSE)
				return new If(x, s1);

			match(Tag.ELSE);
			s2 = stmt();

			return new Else(x, s1, s2);

		case Tag.WHILE:
			While whilenode = new While();
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = whilenode;
			match(Tag.WHILE);
			match('(');
			x = bool();
			match(')');
			s1 = stmt();
			whilenode.init(x, s1);
			Stmt.Enclosing = savedStmt;	// restarts Stmt.Enclosing
			return whilenode;

		case Tag.DO:
			Do donode = new Do();
			savedStmt = Stmt.Enclosing;
			Stmt.Enclosing = donode;
			match(Tag.DO);
			s1 = stmt();
			match(Tag.WHILE);
			match('(');
			x = bool();
			match(')');
			match(';');
			donode.init(s1, x);
			Stmt.Enclosing = savedStmt;	// restarts Stmt.Enclosing
			return donode;

		case Tag.BREAK:
			match(Tag.BREAK);
			match(';');
			return new Break();

		case '{':
			return block();

		default:
			return assign();
		}
	}

	Stmt assign() throws IOException {
		Stmt stmt;
		Token t = this.look;
		match(Tag.ID);
		Id id = top.get(t);

		if (id == null)
			error(t.toString() + " undeclared");

		if (this.look.tag == '=') {	// S -> id = E;
			move();
			stmt = new Set(id, bool());
		} else {
			Access x = offset(id);
			match('=');
			stmt = new SetElem(x, bool());
		}

		match(';');
		return stmt;
	}

	Expr bool() throws IOException {
		Expr x = join();

		while (this.look.tag == Tag.OR) {
			Token tok = this.look;
			move();
			x = new Or(tok, x, join());
		}

		return x;
	}

	Expr join() throws IOException {
		Expr x = equality();

		while (this.look.tag == Tag.AND) {
			Token tok = this.look;
			move();
			x = new And(tok, x, equality());
		}

		return x;
	}

	Expr equality() throws IOException {
		Expr x = rel();

		while (this.look.tag == Tag.EQ || this.look.tag == Tag.NE) {
			Token tok = this.look;
			move();
			x = new Rel(tok, x, rel());
		}

		return x;
	}

	Expr rel() throws IOException {
		Expr x = expr();

		switch (this.look.tag) {
		case '<':
		case Tag.LE:
		case Tag.GE:
		case '>':
			Token tok = this.look;
			move();
			return new Rel(tok, x, expr());

		default:
			return x;
		}
	}

	Expr expr() throws IOException {
		Expr x = term();

		while (this.look.tag == '+' || this.look.tag == '-') {
			Token tok = this.look;
			move();
			x = new Arith(tok, x, term());
		}

		return x;
	}

	Expr term() throws IOException {
		Expr x = unary();

		while (this.look.tag == '*' || this.look.tag == '/') {
			Token tok = this.look;
			move();
			x = new Arith(tok, x, unary());
		}

		return x;
	}

	Expr unary() throws IOException {
		if (this.look.tag == '-') {
			move();
			return new Unary(Word.minus, unary());
		} else if (this.look.tag == '!') {
			Token tok = this.look;
			move();
			return new Not(tok, unary());
		} else {
			return factor();
		}
	}

	Expr factor() throws IOException {
		Expr x = null;

		switch (this.look.tag) {
		case '(':
			move();
			x = bool();
			match(')');
			return x;

		case Tag.NUM:
			x = new Constant(this.look, Type.Int);
			move();
			return x;

		case Tag.REAL:
			x = new Constant(this.look, Type.Float);
			move();
			return x;

		case Tag.TRUE:
			x = Constant.True;
			move();
			return x;

		case Tag.FALSE:
			x = Constant.False;
			move();
			return x;

		case Tag.ID:
			String s = this.look.toString();
			Id id = top.get(this.look);

			if (id == null)
				error(this.look.toString() + " undeclared");

			move();

			if (this.look.tag != '[')
				return id;
			else
				return offset(id);

		default:
			error("syntax error");
			return x;
		}
	}

	Access offset(Id a) throws IOException { // I -> [E] | [E] I
		Expr i, w, t1, t2, loc;	// inherits id
		Type type = a.type;

		match('[');
		i = bool();
		match(']');	// First index, I - > [E]

		type = ((Array)type).of;
		w = new Constant(type.width);
		t1 = new Arith(new Token('*'), i, w);
		loc = t1;

		while (this.look.tag == '[') { // I multidimensional -> [E] I
			match('[');
			i = bool();
			match(']');
			type = ((Array)type).of;
			w = new Constant(type.width);
			t1 = new Arith(new Token('*'), i, w);
			t2 = new Arith(new Token('+'), loc, t1);
			loc = t2;
		}

		return new Access(a, loc, type);
	}
}