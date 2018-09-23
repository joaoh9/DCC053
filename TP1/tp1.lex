package lexsyn;

import java_cup.runtime.Symbol;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

%%
%cup

%{
	String TOKENS_FILENAME = "tokens.txt";

	FileWriter fw = null;
	BufferedWriter bw =null;
%}

%init{
	fw = new FileWriter(TOKENS_FILENAME);
	bw = new BufferedWriter(fw);
%init}

%initthrow{
	IOException
%initthrow}

%eof{
	bw.close();
	fw.close();
%eof}

%eofthrow{
	IOException
%eofthrow}

%%

[ \t\r\f] { /* ignore white space. */
	System.out.print(yytext());
}

[\n] {
	System.out.print(yytext());
	bw.write(yytext());
}

"{" {
	System.out.print(yytext());
	bw.write("<" + "lcbrack, >");
	return new Symbol(sym.LCBRACK);
}

"}" {
	System.out.print(yytext());
	bw.write("<" + "rcbrack, >");
	return new Symbol(sym.RCBRACK);
}

";" {
	System.out.print(yytext());
	bw.write("<" + "semi, >");
	return new Symbol(sym.SEMI);
}

"(" {
	System.out.print(yytext());
	bw.write("<" + "lparen, >");
	return new Symbol(sym.LPAREN);
}

")" {
	System.out.print(yytext());
	bw.write("<" + "rparen, >");
	return new Symbol(sym.RPAREN);
}

(int) {
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.INTT);
}

(char) {
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.CHART);
}

(bool) {
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.BOOLT);
}

(float) {
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.FLOATT);
}

"=" {
	System.out.print(yytext());
	bw.write("<" + "assign, \"" + yytext() + "\">");
	return new Symbol(sym.ASSIGN);
}

(while) {
	System.out.print(yytext());
	bw.write("<" + "while, >");
	return new Symbol(sym.WHILE);
}

(if) {
	System.out.print(yytext());
	bw.write("<" + "if, >");
	return new Symbol(sym.IF);
}

(else) {
	System.out.print(yytext());
	bw.write("<" + "else, >");
	return new Symbol(sym.ELSE);
}

[A-Za-z][A-Za-z0-9]* {
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}

"<" {
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.LT);
}

"<=" {
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.LE);
}

">=" {
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.GE);
}

">" {
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.GT);
}

"+" {
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.PLUS);
}

"-" {
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.MINUS);
}

"*" {
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.MUL);
}

"/" {
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.DIV);
}

[+|-]?[0-9]*[.][0-9]+([E|e][+|-]?[0-9]+)? {
	System.out.print(yytext());
	bw.write("<" + "num, " + yytext() + ">");
	return new Symbol(sym.REALN, new Double(yytext()));
}

[+|-]?[0-9]+([E|e][+|-]?[0-9]+)? {
	System.out.print(yytext());
	bw.write("<" + "num, " + yytext() + ">");
	return new Symbol(sym.INTN, new Integer(yytext()));
}

. {
	System.out.println("\n\n[ERROR] Illegal character: "+yytext());
	System.exit(1);
}