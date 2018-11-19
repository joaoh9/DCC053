package inter;

import java.io.*;

import lexer.*;

public class Node {
	static final String FILENAME = "code.out";
	public static BufferedWriter bw = null;
	public static FileWriter fw = null;
	static int labels = 0;
	int lexline = 0;

	Node() {
		this.lexline = Lexer.line;

		if (bw == null || fw == null) {
			try {
				fw = new FileWriter(FILENAME);
				bw = new BufferedWriter(fw);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	void error(String s) {
		throw new Error("near line " + this.lexline + ": " + s);
	}

	public int newlabel() {
		return ++labels;
	}

	public void emitlabel(int i) {
		try {
			bw.write("L" + i + ":");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void emit(String s) {
		try {
			bw.write("\t" + s + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}