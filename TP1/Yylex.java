package lexsyn;
import java_cup.runtime.Symbol;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	String TOKENS_FILENAME = "tokens.txt";
	FileWriter fw = null;
	BufferedWriter bw =null;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader)
		throws 
	IOException

		{
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream)
		throws 
	IOException

		{
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex ()
		throws 
	IOException

		{
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

	fw = new FileWriter(TOKENS_FILENAME);
	bw = new BufferedWriter(fw);
	}

	private boolean yy_eof_done = false;
	private void yy_do_eof ()
		throws 
	IOException

		{
		if (false == yy_eof_done) {

	bw.close();
	fw.close();
		}
		yy_eof_done = true;
	}
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NOT_ACCEPT,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NOT_ACCEPT,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NOT_ACCEPT,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NOT_ACCEPT,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"34:9,1,2,34,1:2,34:18,1,34:7,6,7,29,27,34,28,32,30,24:10,34,5,25,19,26,34:2" +
",23:4,33,23:21,34:6,13,15,11,23,21,18,23,12,8,23:2,17,23,9,16,23:2,14,22,10" +
",23:2,20,23:3,3,31,4,34:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,57,
"0,1:8,2,1,3,4,5,6:2,1:2,6,7,1:2,8,7:6,9,7,10,11,12,11,13,1,10,14,15,16,12,1" +
"7,18,19,20,21,22,23,24,25,26,27,28,29,30,31")[0];

	private int yy_nxt[][] = unpackFromString(32,35,
"1,2,3,4,5,6,7,8,9,30:2,50,30:3,51,30:2,55,10,56,52,30:2,11,12,13,14,15,16,1" +
"7,18,32,30,36,-1:43,30,35,30:8,19,-1,30:5,-1:8,30,-1:22,29,-1:2,11,-1:6,29," +
"34,29,-1:20,20,-1:34,21,-1:39,11,-1:7,34,-1:10,30:11,-1,30:5,-1:8,30,-1:22," +
"39,-1:2,22,-1:6,39,-1,39,-1:25,31,-1:2,37:2,-1:2,37,-1:27,31,-1:34,22,-1:34" +
",33,-1:18,30:2,23,30:8,-1,30:5,-1:8,30,-1:9,30:6,24,30:4,-1,30:5,-1:8,30,-1" +
":25,33,-1:2,41:2,-1:2,41,-1:11,30:9,25,30,-1,30:5,-1:8,30,-1:9,30:11,-1,30," +
"26,30:3,-1:8,30,-1:9,30:2,27,30:8,-1,30:5,-1:8,30,-1:9,30:11,-1,30,28,30:3," +
"-1:8,30,-1:9,30:5,38,30:5,-1,30:5,-1:8,30,-1:9,30:8,40,30:2,-1,30:5,-1:8,30" +
",-1:9,30:11,-1,30:2,42,30:2,-1:8,30,-1:9,30:5,43,30:5,-1,30:5,-1:8,30,-1:9," +
"30:9,44,30,-1,30:5,-1:8,30,-1:9,30:4,45,30:6,-1,30:5,-1:8,30,-1:9,30:8,46,3" +
"0:2,-1,30:5,-1:8,30,-1:9,30:9,47,30,-1,30:5,-1:8,30,-1:9,30:8,48,30:2,-1,30" +
":5,-1:8,30,-1:9,49,30:10,-1,30:5,-1:8,30,-1:9,30:9,53,30,-1,30:5,-1:8,30,-1" +
":9,30:4,54,30:6,-1,30:5,-1:8,30,-1");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				yy_do_eof();
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ /* ignore white space. */
	System.out.print(yytext());
}
					case -3:
						break;
					case 3:
						{
	System.out.print(yytext());
	bw.write(yytext());
}
					case -4:
						break;
					case 4:
						{
	System.out.print(yytext());
	bw.write("<" + "lcbrack, >");
	return new Symbol(sym.LCBRACK);
}
					case -5:
						break;
					case 5:
						{
	System.out.print(yytext());
	bw.write("<" + "rcbrack, >");
	return new Symbol(sym.RCBRACK);
}
					case -6:
						break;
					case 6:
						{
	System.out.print(yytext());
	bw.write("<" + "semi, >");
	return new Symbol(sym.SEMI);
}
					case -7:
						break;
					case 7:
						{
	System.out.print(yytext());
	bw.write("<" + "lparen, >");
	return new Symbol(sym.LPAREN);
}
					case -8:
						break;
					case 8:
						{
	System.out.print(yytext());
	bw.write("<" + "rparen, >");
	return new Symbol(sym.RPAREN);
}
					case -9:
						break;
					case 9:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -10:
						break;
					case 10:
						{
	System.out.print(yytext());
	bw.write("<" + "assign, \"" + yytext() + "\">");
	return new Symbol(sym.ASSIGN);
}
					case -11:
						break;
					case 11:
						{
	System.out.print(yytext());
	bw.write("<" + "num, " + yytext() + ">");
	return new Symbol(sym.INTN, new Integer(yytext()));
}
					case -12:
						break;
					case 12:
						{
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.LT);
}
					case -13:
						break;
					case 13:
						{
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.GT);
}
					case -14:
						break;
					case 14:
						{
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.PLUS);
}
					case -15:
						break;
					case 15:
						{
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.MINUS);
}
					case -16:
						break;
					case 16:
						{
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.MUL);
}
					case -17:
						break;
					case 17:
						{
	System.out.print(yytext());
	bw.write("<" + "binop, \"" + yytext() + "\">");
	return new Symbol(sym.DIV);
}
					case -18:
						break;
					case 18:
						{
	System.out.println("\n\n[ERROR] Illegal character: "+yytext());
	System.exit(1);
}
					case -19:
						break;
					case 19:
						{
	System.out.print(yytext());
	bw.write("<" + "if, >");
	return new Symbol(sym.IF);
}
					case -20:
						break;
					case 20:
						{
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.LE);
}
					case -21:
						break;
					case 21:
						{
	System.out.print(yytext());
	bw.write("<" + "relop, \"" + yytext() + "\">");
	return new Symbol(sym.GE);
}
					case -22:
						break;
					case 22:
						{
	System.out.print(yytext());
	bw.write("<" + "num, " + yytext() + ">");
	return new Symbol(sym.REALN, new Double(yytext()));
}
					case -23:
						break;
					case 23:
						{
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.INTT);
}
					case -24:
						break;
					case 24:
						{
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.CHART);
}
					case -25:
						break;
					case 25:
						{
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.BOOLT);
}
					case -26:
						break;
					case 26:
						{
	System.out.print(yytext());
	bw.write("<" + "else, >");
	return new Symbol(sym.ELSE);
}
					case -27:
						break;
					case 27:
						{
	System.out.print(yytext());
	bw.write("<" + "type, \"" + yytext() + "\">");
	return new Symbol(sym.FLOATT);
}
					case -28:
						break;
					case 28:
						{
	System.out.print(yytext());
	bw.write("<" + "while, >");
	return new Symbol(sym.WHILE);
}
					case -29:
						break;
					case 30:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -30:
						break;
					case 31:
						{
	System.out.print(yytext());
	bw.write("<" + "num, " + yytext() + ">");
	return new Symbol(sym.INTN, new Integer(yytext()));
}
					case -31:
						break;
					case 32:
						{
	System.out.println("\n\n[ERROR] Illegal character: "+yytext());
	System.exit(1);
}
					case -32:
						break;
					case 33:
						{
	System.out.print(yytext());
	bw.write("<" + "num, " + yytext() + ">");
	return new Symbol(sym.REALN, new Double(yytext()));
}
					case -33:
						break;
					case 35:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -34:
						break;
					case 36:
						{
	System.out.println("\n\n[ERROR] Illegal character: "+yytext());
	System.exit(1);
}
					case -35:
						break;
					case 38:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -36:
						break;
					case 40:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -37:
						break;
					case 42:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -38:
						break;
					case 43:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -39:
						break;
					case 44:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -40:
						break;
					case 45:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -41:
						break;
					case 46:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -42:
						break;
					case 47:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -43:
						break;
					case 48:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -44:
						break;
					case 49:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -45:
						break;
					case 50:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -46:
						break;
					case 51:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -47:
						break;
					case 52:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -48:
						break;
					case 53:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -49:
						break;
					case 54:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -50:
						break;
					case 55:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -51:
						break;
					case 56:
						{
	System.out.print(yytext());
	bw.write("<" + "id, \"" + yytext() + "\">");
	return new Symbol(sym.ID);
}
					case -52:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
