
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package lexsyn;

import java_cup.runtime.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\040\000\002\002\003\000\002\002\004\000\002\003" +
    "\006\000\002\004\004\000\002\004\002\000\002\005\005" +
    "\000\002\006\003\000\002\006\003\000\002\006\003\000" +
    "\002\006\003\000\002\007\004\000\002\007\002\000\002" +
    "\010\006\000\002\010\007\000\002\010\011\000\002\010" +
    "\007\000\002\010\003\000\002\011\005\000\002\011\005" +
    "\000\002\011\005\000\002\011\005\000\002\011\003\000" +
    "\002\012\005\000\002\012\005\000\002\012\003\000\002" +
    "\013\005\000\002\013\005\000\002\013\003\000\002\014" +
    "\004\000\002\014\003\000\002\015\003\000\002\015\003" +
    "" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\072\000\004\004\006\001\002\000\004\002\074\001" +
    "\002\000\004\002\001\001\002\000\024\004\ufffd\005\ufffd" +
    "\011\ufffd\012\ufffd\013\ufffd\014\ufffd\016\ufffd\017\ufffd\023" +
    "\ufffd\001\002\000\024\004\ufff6\005\ufff6\011\012\012\014" +
    "\013\016\014\013\016\ufff6\017\ufff6\023\ufff6\001\002\000" +
    "\014\004\006\005\022\016\024\017\025\023\021\001\002" +
    "\000\024\004\ufffe\005\ufffe\011\ufffe\012\ufffe\013\ufffe\014" +
    "\ufffe\016\ufffe\017\ufffe\023\ufffe\001\002\000\004\023\ufffb" +
    "\001\002\000\004\023\ufff8\001\002\000\004\023\ufffa\001" +
    "\002\000\004\023\017\001\002\000\004\023\ufff9\001\002" +
    "\000\004\006\020\001\002\000\024\004\ufffc\005\ufffc\011" +
    "\ufffc\012\ufffc\013\ufffc\014\ufffc\016\ufffc\017\ufffc\023\ufffc" +
    "\001\002\000\004\015\071\001\002\000\020\002\uffff\004" +
    "\uffff\005\uffff\016\uffff\017\uffff\020\uffff\023\uffff\001\002" +
    "\000\014\004\ufff7\005\ufff7\016\ufff7\017\ufff7\023\ufff7\001" +
    "\002\000\004\007\065\001\002\000\004\007\027\001\002" +
    "\000\016\004\ufff1\005\ufff1\016\ufff1\017\ufff1\020\ufff1\023" +
    "\ufff1\001\002\000\010\021\036\022\037\031\034\001\002" +
    "\000\004\010\061\001\002\000\026\006\uffe6\010\uffe6\024" +
    "\uffe6\025\uffe6\026\uffe6\027\uffe6\030\uffe6\031\uffe6\032\uffe6" +
    "\033\uffe6\001\002\000\026\006\uffe4\010\uffe4\024\uffe4\025" +
    "\uffe4\026\uffe4\027\uffe4\030\uffe4\031\uffe4\032\uffe4\033\uffe4" +
    "\001\002\000\026\006\uffe9\010\uffe9\024\uffe9\025\uffe9\026" +
    "\uffe9\027\uffe9\030\uffe9\031\uffe9\032\047\033\050\001\002" +
    "\000\010\021\036\022\037\031\034\001\002\000\020\010" +
    "\uffec\024\040\025\043\026\041\027\044\030\042\031\045" +
    "\001\002\000\026\006\uffe3\010\uffe3\024\uffe3\025\uffe3\026" +
    "\uffe3\027\uffe3\030\uffe3\031\uffe3\032\uffe3\033\uffe3\001\002" +
    "\000\026\006\uffe2\010\uffe2\024\uffe2\025\uffe2\026\uffe2\027" +
    "\uffe2\030\uffe2\031\uffe2\032\uffe2\033\uffe2\001\002\000\010" +
    "\021\036\022\037\031\034\001\002\000\010\021\036\022" +
    "\037\031\034\001\002\000\010\021\036\022\037\031\034" +
    "\001\002\000\010\021\036\022\037\031\034\001\002\000" +
    "\010\021\036\022\037\031\034\001\002\000\010\021\036" +
    "\022\037\031\034\001\002\000\026\006\uffea\010\uffea\024" +
    "\uffea\025\uffea\026\uffea\027\uffea\030\uffea\031\uffea\032\047" +
    "\033\050\001\002\000\010\021\036\022\037\031\034\001" +
    "\002\000\010\021\036\022\037\031\034\001\002\000\026" +
    "\006\uffe7\010\uffe7\024\uffe7\025\uffe7\026\uffe7\027\uffe7\030" +
    "\uffe7\031\uffe7\032\uffe7\033\uffe7\001\002\000\026\006\uffe8" +
    "\010\uffe8\024\uffe8\025\uffe8\026\uffe8\027\uffe8\030\uffe8\031" +
    "\uffe8\032\uffe8\033\uffe8\001\002\000\010\010\uffed\030\042" +
    "\031\045\001\002\000\010\010\uffef\030\042\031\045\001" +
    "\002\000\026\006\uffeb\010\uffeb\024\uffeb\025\uffeb\026\uffeb" +
    "\027\uffeb\030\uffeb\031\uffeb\032\047\033\050\001\002\000" +
    "\010\010\uffee\030\042\031\045\001\002\000\010\010\ufff0" +
    "\030\042\031\045\001\002\000\026\006\uffe5\010\uffe5\024" +
    "\uffe5\025\uffe5\026\uffe5\027\uffe5\030\uffe5\031\uffe5\032\uffe5" +
    "\033\uffe5\001\002\000\012\004\006\016\024\017\025\023" +
    "\021\001\002\000\016\004\ufff4\005\ufff4\016\ufff4\017\ufff4" +
    "\020\063\023\ufff4\001\002\000\012\004\006\016\024\017" +
    "\025\023\021\001\002\000\016\004\ufff3\005\ufff3\016\ufff3" +
    "\017\ufff3\020\ufff3\023\ufff3\001\002\000\010\021\036\022" +
    "\037\031\034\001\002\000\004\010\067\001\002\000\012" +
    "\004\006\016\024\017\025\023\021\001\002\000\016\004" +
    "\ufff2\005\ufff2\016\ufff2\017\ufff2\020\ufff2\023\ufff2\001\002" +
    "\000\010\021\036\022\037\031\034\001\002\000\010\006" +
    "\073\030\042\031\045\001\002\000\016\004\ufff5\005\ufff5" +
    "\016\ufff5\017\ufff5\020\ufff5\023\ufff5\001\002\000\004\002" +
    "\000\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\072\000\006\002\003\003\004\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\004\006\001\001\000\010" +
    "\005\010\006\014\007\007\001\001\000\006\003\025\010" +
    "\022\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\014\011\027\012\034" +
    "\013\032\014\030\015\031\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\006" +
    "\014\057\015\031\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\012\012\056\013\032\014\030" +
    "\015\031\001\001\000\012\012\055\013\032\014\030\015" +
    "\031\001\001\000\010\013\054\014\030\015\031\001\001" +
    "\000\012\012\053\013\032\014\030\015\031\001\001\000" +
    "\012\012\052\013\032\014\030\015\031\001\001\000\010" +
    "\013\045\014\030\015\031\001\001\000\002\001\001\000" +
    "\006\014\051\015\031\001\001\000\006\014\050\015\031" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\006\003\025\010" +
    "\061\001\001\000\002\001\001\000\006\003\025\010\063" +
    "\001\001\000\002\001\001\000\014\011\065\012\034\013" +
    "\032\014\030\015\031\001\001\000\002\001\001\000\006" +
    "\003\025\010\067\001\001\000\002\001\001\000\012\012" +
    "\071\013\032\014\030\015\031\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



	public static void main(String args[]) throws Exception {
		System.out.println("----------------------");
		parser myParser = new parser(new Yylex(System.in));
		myParser.parse();
		System.out.print("Program accepted.");
	}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parser$do_action_part00000000(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // program ::= block 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				System.out.println("\n----------------------");
				String rules = new String("program -> block\n" + b);
				System.out.print(rules);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("program",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // block ::= LCBRACK decls stmts RCBRACK 
            {
              Object RESULT =null;
		int dsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int dsright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object ds = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int ssleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int ssright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object ss = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
				RESULT = new String("block -> { decls stmts }\n" + ds + ss);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("block",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // decls ::= decls decl 
            {
              Object RESULT =null;
		int dsleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int dsright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object ds = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int dleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object d = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("decls -> decls decl\n" + ds + d);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decls",2, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // decls ::= 
            {
              Object RESULT =null;
		
				RESULT = new String("decls -> \n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decls",2, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // decl ::= type ID SEMI 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		
				RESULT = new String("decl -> type id;\n" + t);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("decl",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // type ::= INTT 
            {
              Object RESULT =null;
		
				RESULT = new String("type -> int\n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // type ::= CHART 
            {
              Object RESULT =null;
		
				RESULT = new String("type -> char\n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // type ::= BOOLT 
            {
              Object RESULT =null;
		
				RESULT = new String("type -> bool\n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // type ::= FLOATT 
            {
              Object RESULT =null;
		
				RESULT = new String("type -> float\n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("type",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // stmts ::= stmts stmt 
            {
              Object RESULT =null;
		int ssleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int ssright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object ss = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("stmts -> stmts stmt\n" + ss + s);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmts",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // stmts ::= 
            {
              Object RESULT =null;
		
				RESULT = new String("stmts -> \n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmts",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // stmt ::= ID ASSIGN expr SEMI 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		
				RESULT = new String("stmt -> id = expr;\n" + e);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // stmt ::= IF LPAREN rel RPAREN stmt 
            {
              Object RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int rright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object r = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("stmt -> if ( rel ) stmt\n" + r + s);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // stmt ::= IF LPAREN rel RPAREN stmt ELSE stmt 
            {
              Object RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).left;
		int rright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)).right;
		Object r = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-4)).value;
		int s1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int s1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object s1 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int s2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int s2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object s2 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("stmt -> if ( rel ) stmt else stmt\n" +
				r + s1 + s2);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-6)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // stmt ::= WHILE LPAREN rel RPAREN stmt 
            {
              Object RESULT =null;
		int rleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int rright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object r = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("stmt -> while ( rel ) stmt\n" + r + s);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // stmt ::= block 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("stmt -> block\n" + b);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("stmt",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // rel ::= expr LT expr 
            {
              Object RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object e1 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object e2 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("rel -> expr < expr\n" + e1 + e2);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("rel",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // rel ::= expr LE expr 
            {
              Object RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object e1 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object e2 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("rel -> expr <= expr\n" + e1 + e2);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("rel",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // rel ::= expr GT expr 
            {
              Object RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object e1 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object e2 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("rel -> expr > expr\n" + e1 + e2);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("rel",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // rel ::= expr GE expr 
            {
              Object RESULT =null;
		int e1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int e1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object e1 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int e2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int e2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object e2 = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("rel -> expr >= expr\n" + e1 + e2);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("rel",7, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // rel ::= expr 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("rel -> expr\n" + e);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("rel",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // expr ::= expr PLUS term 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("expr -> expr + term\n" + e + t);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // expr ::= expr MINUS term 
            {
              Object RESULT =null;
		int eleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int eright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object e = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("expr -> expr - term\n" + e + t);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",8, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // expr ::= term 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("expr -> term\n" + t);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("expr",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // term ::= term MUL unary 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int uleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int uright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object u = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("term -> term * unary\n" + t + u);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",9, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // term ::= term DIV unary 
            {
              Object RESULT =null;
		int tleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int tright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int uleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int uright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object u = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("term -> term / unary\n" + t + u);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",9, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // term ::= unary 
            {
              Object RESULT =null;
		int uleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int uright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object u = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("term -> unary\n" + u);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("term",9, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // unary ::= MINUS unary 
            {
              Object RESULT =null;
		int uleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int uright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object u = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("unary -> - unary\n" + u);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("unary",10, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 29: // unary ::= factor 
            {
              Object RESULT =null;
		int fleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int fright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object f = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("unary -> factor\n" + f);
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("unary",10, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 30: // factor ::= INTN 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object n = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("factor -> num\n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 31: // factor ::= REALN 
            {
              Object RESULT =null;
		int nleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int nright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Object n = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		
				RESULT = new String("factor -> real\n\n");
			
              CUP$parser$result = parser.getSymbolFactory().newSymbol("factor",11, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
              return CUP$parser$do_action_part00000000(
                               CUP$parser$act_num,
                               CUP$parser$parser,
                               CUP$parser$stack,
                               CUP$parser$top);
    }
}

}
