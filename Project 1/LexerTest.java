import java.io.*;

public class LexerTest {
	public static void main(String... z) {
		try {
			Lexer lex = new Lexer(new FileReader(z[0]));
			System.out.println("List of identifiers:\n=============================\n");
			while (lex.yylex() != null) {
			}
			System.out.println("\n\nTrie Table for identifiers:\n=============================\n");
			lex.printTable();
			System.out.println("\n\n");
		} catch (Exception e) {
			System.out.println("Usage: java LexerTest <Input File Name>");
		}
	}
}