package lexer;

import java.io.*;
import java.util.*;
import symbols.*;

public class Lexer {
    public static int line = 1;
    char peek = 0;
    Hashtable words = new Hashtable();

    public Lexer() {
        System.out.println("-------------------BEGIN INPUT-------------------");
        reserve(new Word("if", Tag.IF));
        reserve(new Word("else", Tag.ELSE));
        reserve(new Word("while", Tag.WHILE));
        reserve(new Word("do", Tag.DO));
        reserve(new Word("break", Tag.BREAK));
        reserve(Word.True);
        reserve(Word.False);
        reserve(Type.Int);
        reserve(Type.Char);
        reserve(Type.Bool);
        reserve(Type.Float);
    }

    public void reserve(Word w) {
        this.words.put(w.lexeme, w);
    }

    public void readch() throws IOException {
        this.peek = (char) System.in.read();

        if (this.peek != 65535) { // EOF
            System.out.print(this.peek);
        } else {
            System.out.println();
            System.out.println("--------------------END INPUT--------------------");
        }
    }

    public boolean readch(char c) throws IOException {
        readch();
        
        if (this.peek != c)
            return false;

        this.peek = 0;
        return true;
    }

    public Token scan() throws IOException {
        for ( ; ; readch()) {
            if (this.peek == ' ' || this.peek == '\t' || this.peek == 0) {
                continue;
            } else if (this.peek == '/') {
                readch();

                if (this.peek == '/') {
                    while (this.peek != '\n')
                        readch();

                    this.line++;
                }
            } else if (this.peek == '\n') {
                this.line++;
            } else {
                break;
            }
        }

        switch (this.peek) {
        case '&':
            if (readch('&'))
                return Word.and;
            else
                return new Token('&');
        case '|':
            if (readch('|'))
                return Word.or;
            else
                return new Token('|');
        case '=':
            if (readch('='))
                return Word.eq;
            else
                return new Token('=');
        case '!':
            if (readch('='))
                return Word.ne;
            else
                return new Token('!');
        case '<':
            if (readch('='))
                return Word.le;
            else
                return new Token('<');
        case '>':
            if (readch('='))
                return Word.ge;
            else
                return new Token('>');
        }

        if (Character.isDigit(this.peek)) {
            int v = 0;

            do {
                v = 10*v + Character.digit(this.peek, 10);
                readch();
            } while (Character.isDigit(this.peek));

            if (this.peek != '.')
                return new Num(v);

            float x = v, d = 10;

            for (;;) {
                readch();

                if (!Character.isDigit(this.peek))
                    break;

                x = x + Character.digit(this.peek, 10) / d;
                d = d * 10;
            }

            return new Real(x);
        }

        if (Character.isLetter(this.peek)) {
            StringBuffer b = new StringBuffer();

            do {
                b.append(this.peek);
                readch();
            } while (Character.isLetterOrDigit(this.peek));

            String s = b.toString();
            Word w = (Word) this.words.get(s);

            if (w != null)
                return w;
            w = new Word(s, Tag.ID);
            words.put(s, w);
            return w;
        }

        Token tok = new Token(this.peek);
        this.peek = 0;
        return tok;
    }
}
