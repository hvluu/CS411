/*CS 411 Lexer*/

import java.util.ArrayList;

public class Lexer {

	/** This character denotes the end of file */
	public static final int YYEOF = -1;

	/** initial size of the lookahead buffer */
	private static final int ZZ_BUFFERSIZE = 16384;

	/** lexical states */
	public static final int YYINITIAL = 0;

	/**
	 * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
	 * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l at the
	 * beginning of a line l is of the form l = 2*k, k a non negative integer
	 */
	private static final int ZZ_LEXSTATE[] = { 0, 0 };

	/**
	 * Translates characters to character classes
	 */
	private static final String ZZ_CMAP_PACKED = "\11\0\1\17\1\16\2\0\1\20\22\0\1\17\1\54\1\15\2\0" + "\1\50\2\0\1\57\1\60\1\22\1\11\1\56\1\12\1\13\1\21" + "\1\3\11\2\1\0\1\55\1\51\1\52\1\53\2\0\4\6\1\7" + "\1\6\21\1\1\5\2\1\1\61\1\0\1\62\1\0\1\14\1\0" + "\1\27\1\23\1\31\1\33\1\10\1\37\1\45\1\47\1\40\1\1" + "\1\30\1\25\1\41\1\36\1\24\1\42\1\1\1\26\1\32\1\35" + "\1\34\1\46\1\43\1\4\1\44\1\1\1\63\1\0\1\64\uff82\0";

	/**
	 * Translates characters to character classes
	 */
	private static final char[] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

	/**
	 * Translates DFA states to action switch labels.
	 */
	private static final int[] ZZ_ACTION = zzUnpackAction();

	private static final String ZZ_ACTION_PACKED_0 = "\1\0\1\1\1\2\2\3\1\2\1\4\1\5\1\6" + "\1\1\1\7\1\10\1\11\1\12\14\2\1\13\1\14" + "\1\15\1\16\1\1\1\17\1\20\1\21\1\22\1\23" + "\1\24\1\25\1\26\1\0\1\27\1\0\2\2\1\0" + "\1\30\2\0\13\2\1\31\4\2\1\32\1\33\1\34" + "\1\35\1\27\1\0\1\3\2\2\2\10\2\0\11\2" + "\1\36\1\2\1\37\5\2\1\40\1\0\1\41\6\2" + "\1\42\5\2\1\43\1\2\1\44\2\2\1\45\6\2" + "\1\46\1\2\1\47\1\50\1\51\1\52\4\2\1\53" + "\3\2\1\54\1\55\2\2\1\56\1\2\1\57";

	private static int[] zzUnpackAction() {
		int[] result = new int[142];
		int offset = 0;
		offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAction(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/**
	 * Translates a state to a row index in the transition table
	 */
	private static final int[] ZZ_ROWMAP = zzUnpackRowMap();

	private static final String ZZ_ROWMAP_PACKED_0 = "\0\0\0\65\0\152\0\237\0\324\0\u0109\0\65\0\65" + "\0\65\0\u013e\0\65\0\u0173\0\u01a8\0\65\0\u01dd\0\u0212" + "\0\u0247\0\u027c\0\u02b1\0\u02e6\0\u031b\0\u0350\0\u0385\0\u03ba" + "\0\u03ef\0\u0424\0\65\0\u0459\0\u048e\0\u04c3\0\u04f8\0\65" + "\0\65\0\65\0\65\0\65\0\65\0\65\0\65\0\u052d" + "\0\u0562\0\u0597\0\u05cc\0\u0601\0\u013e\0\65\0\u0636\0\u066b" + "\0\u06a0\0\u06d5\0\u070a\0\u073f\0\u0774\0\u07a9\0\u07de\0\u0813"
			+ "\0\u0848\0\u087d\0\u08b2\0\152\0\u08e7\0\u091c\0\u0951\0\u0986" + "\0\65\0\65\0\65\0\65\0\u09bb\0\u09bb\0\u0597\0\u09f0" + "\0\u0a25\0\65\0\u0a5a\0\u0a8f\0\u0ac4\0\u0af9\0\u0b2e\0\u0b63" + "\0\u0b98\0\u0bcd\0\u0c02\0\u0c37\0\u0c6c\0\u0ca1\0\152\0\u0cd6" + "\0\u0d0b\0\u0d40\0\u0d75\0\u0daa\0\u0ddf\0\u0e14\0\152\0\u0e49" + "\0\152\0\u0e7e\0\u0eb3\0\u0ee8\0\u0f1d\0\u0f52\0\u0f87\0\152" + "\0\u0fbc\0\u0ff1\0\u1026\0\u105b\0\u1090\0\152\0\u10c5\0\152"
			+ "\0\u10fa\0\u112f\0\152\0\u1164\0\u1199\0\u11ce\0\u1203\0\u1238" + "\0\u126d\0\152\0\u12a2\0\152\0\152\0\152\0\152\0\u12d7" + "\0\u130c\0\u1341\0\u1376\0\152\0\u13ab\0\u13e0\0\u1415\0\152" + "\0\152\0\u144a\0\u147f\0\152\0\u14b4\0\152";

	private static int[] zzUnpackRowMap() {
		int[] result = new int[142];
		int offset = 0;
		offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackRowMap(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int high = packed.charAt(i++) << 16;
			result[j++] = high | packed.charAt(i++);
		}
		return j;
	}

	/**
	 * The transition table of the DFA
	 */
	private static final int[] ZZ_TRANS = zzUnpackTrans();

	private static final String ZZ_TRANS_PACKED_0 = "\1\2\1\3\1\4\1\5\4\3\1\6\1\7\1\10" + "\1\11\1\2\1\12\1\13\1\14\1\2\1\15\1\16" + "\1\17\2\3\1\20\2\3\1\21\1\22\1\23\1\3" + "\1\24\1\25\1\26\1\27\1\3\1\30\1\31\2\3" + "\1\32\1\3\1\33\1\34\1\35\1\36\1\37\1\40" + "\1\41\1\42\1\43\1\44\1\45\1\46\1\47\66\0" + "\10\3\3\0\1\3\6\0\25\3\17\0\2\4\3\0" + "\2\50\2\0\1\51\53\0\2\4\2\52\1\0\2\50" + "\2\0\1\51\52\0\3\3\1\53\4\3\3\0\1\3" + "\6\0\2\3\1\54\22\3\15\0\15\55\1\56\1\0"
			+ "\46\55\17\0\1\14\66\0\1\57\1\60\43\0\10\3" + "\3\0\1\3\6\0\1\3\1\61\1\3\1\62\21\3" + "\16\0\7\3\1\63\3\0\1\3\6\0\25\3\16\0" + "\10\3\3\0\1\3\6\0\2\3\1\64\22\3\16\0" + "\10\3\3\0\1\3\6\0\12\3\1\65\12\3\16\0" + "\10\3\3\0\1\3\6\0\1\3\1\66\23\3\16\0" + "\10\3\3\0\1\3\6\0\3\3\1\67\21\3\16\0" + "\7\3\1\70\3\0\1\3\6\0\25\3\16\0\10\3" + "\3\0\1\3\6\0\1\3\1\71\2\3\1\72\20\3" + "\16\0\10\3\3\0\1\3\6\0\13\3\1\73\1\74" + "\1\3\1\75\6\3\16\0\10\3\3\0\1\3\6\0"
			+ "\3\3\1\76\21\3\16\0\10\3\3\0\1\3\6\0" + "\24\3\1\77\16\0\10\3\3\0\1\3\6\0\1\3" + "\1\100\23\3\67\0\1\101\64\0\1\102\64\0\1\103" + "\64\0\1\104\14\0\2\105\5\0\2\106\54\0\2\51" + "\3\0\2\50\56\0\2\107\2\0\3\107\12\0\1\107" + "\3\0\1\107\1\0\1\107\1\0\1\107\3\0\1\107" + "\26\0\10\3\3\0\1\3\6\0\12\3\1\110\12\3" + "\16\0\10\3\3\0\1\3\6\0\7\3\1\111\15\3" + "\15\0\16\57\1\112\1\57\1\113\44\57\22\114\1\115" + "\42\114\1\0\10\3\3\0\1\3\6\0\1\3\1\116"
			+ "\23\3\16\0\7\3\1\117\3\0\1\3\6\0\25\3" + "\16\0\10\3\3\0\1\3\6\0\4\3\1\120\5\3" + "\1\121\12\3\16\0\10\3\3\0\1\3\6\0\4\3" + "\1\122\20\3\16\0\10\3\3\0\1\3\6\0\3\3" + "\1\123\21\3\16\0\10\3\3\0\1\3\6\0\11\3" + "\1\124\13\3\16\0\10\3\3\0\1\3\6\0\11\3" + "\1\125\13\3\16\0\10\3\3\0\1\3\6\0\20\3" + "\1\126\4\3\16\0\10\3\3\0\1\3\6\0\3\3" + "\1\127\21\3\16\0\10\3\3\0\1\3\6\0\2\3" + "\1\130\22\3\16\0\10\3\3\0\1\3\6\0\12\3" + "\1\131\12\3\16\0\10\3\3\0\1\3\6\0\17\3"
			+ "\1\132\5\3\16\0\10\3\3\0\1\3\6\0\15\3" + "\1\133\7\3\16\0\10\3\3\0\1\3\6\0\15\3" + "\1\134\7\3\16\0\10\3\3\0\1\3\6\0\15\3" + "\1\135\7\3\17\0\2\105\62\0\7\3\1\136\3\0" + "\1\3\6\0\25\3\16\0\7\3\1\137\3\0\1\3" + "\6\0\25\3\33\0\1\112\46\0\22\114\1\140\42\114" + "\21\0\1\112\1\115\43\0\10\3\3\0\1\3\6\0" + "\2\3\1\141\22\3\16\0\10\3\3\0\1\3\6\0" + "\4\3\1\142\20\3\16\0\10\3\3\0\1\3\6\0" + "\10\3\1\143\14\3\16\0\10\3\3\0\1\3\6\0" + "\11\3\1\144\13\3\16\0\10\3\3\0\1\3\6\0"
			+ "\7\3\1\145\15\3\16\0\10\3\3\0\1\3\6\0" + "\15\3\1\146\7\3\16\0\10\3\3\0\1\3\6\0" + "\1\147\24\3\16\0\7\3\1\150\3\0\1\3\6\0" + "\25\3\16\0\10\3\3\0\1\3\6\0\4\3\1\151" + "\20\3\16\0\10\3\3\0\1\3\6\0\7\3\1\125" + "\15\3\16\0\7\3\1\152\3\0\1\3\6\0\25\3" + "\16\0\10\3\3\0\1\3\6\0\2\3\1\153\22\3" + "\16\0\10\3\3\0\1\3\6\0\13\3\1\154\11\3" + "\16\0\10\3\3\0\1\3\6\0\2\3\1\155\22\3" + "\16\0\10\3\3\0\1\3\6\0\10\3\1\156\14\3" + "\16\0\10\3\3\0\1\3\6\0\13\3\1\157\11\3"
			+ "\15\0\21\114\1\112\1\140\42\114\1\0\10\3\3\0" + "\1\3\6\0\5\3\1\160\17\3\16\0\10\3\3\0" + "\1\3\6\0\2\3\1\161\22\3\16\0\10\3\3\0" + "\1\3\6\0\3\3\1\162\21\3\16\0\10\3\3\0" + "\1\3\6\0\7\3\1\163\15\3\16\0\10\3\3\0" + "\1\3\6\0\13\3\1\164\11\3\16\0\10\3\3\0" + "\1\3\6\0\2\3\1\165\22\3\16\0\10\3\3\0" + "\1\3\6\0\3\3\1\166\21\3\16\0\10\3\3\0" + "\1\3\6\0\3\3\1\167\21\3\16\0\7\3\1\170" + "\3\0\1\3\6\0\25\3\16\0\10\3\3\0\1\3" + "\6\0\12\3\1\171\12\3\16\0\7\3\1\172\3\0"
			+ "\1\3\6\0\25\3\16\0\10\3\3\0\1\3\6\0" + "\10\3\1\173\14\3\16\0\10\3\3\0\1\3\6\0" + "\13\3\1\174\11\3\16\0\10\3\3\0\1\3\6\0" + "\13\3\1\175\11\3\16\0\10\3\3\0\1\3\6\0" + "\22\3\1\176\2\3\16\0\7\3\1\177\3\0\1\3" + "\6\0\25\3\16\0\10\3\3\0\1\3\6\0\3\3" + "\1\200\21\3\16\0\10\3\3\0\1\3\6\0\14\3" + "\1\201\10\3\16\0\10\3\3\0\1\3\6\0\16\3" + "\1\202\6\3\16\0\10\3\3\0\1\3\6\0\2\3" + "\1\203\22\3\16\0\10\3\3\0\1\3\6\0\7\3" + "\1\204\15\3\16\0\10\3\3\0\1\3\6\0\4\3"
			+ "\1\205\20\3\16\0\10\3\3\0\1\3\6\0\4\3" + "\1\206\20\3\16\0\7\3\1\207\3\0\1\3\6\0" + "\25\3\16\0\10\3\3\0\1\3\6\0\13\3\1\210" + "\11\3\16\0\10\3\3\0\1\3\6\0\21\3\1\211" + "\3\3\16\0\10\3\3\0\1\3\6\0\6\3\1\212" + "\16\3\16\0\10\3\3\0\1\3\6\0\13\3\1\213" + "\11\3\16\0\7\3\1\214\3\0\1\3\6\0\25\3" + "\16\0\10\3\3\0\1\3\6\0\12\3\1\215\12\3" + "\16\0\10\3\3\0\1\3\6\0\7\3\1\216\15\3" + "\15\0";

	private static int[] zzUnpackTrans() {
		int[] result = new int[5353];
		int offset = 0;
		offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackTrans(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			value--;
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/* error codes */
	private static final int ZZ_UNKNOWN_ERROR = 0;
	private static final int ZZ_NO_MATCH = 1;
	private static final int ZZ_PUSHBACK_2BIG = 2;

	/* error messages for the codes above */
	private static final String ZZ_ERROR_MSG[] = { "Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large" };

	/**
	 * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
	 */
	private static final int[] ZZ_ATTRIBUTE = zzUnpackAttribute();

	private static final String ZZ_ATTRIBUTE_PACKED_0 = "\1\0\1\11\4\1\3\11\1\1\1\11\2\1\1\11" + "\14\1\1\11\4\1\10\11\1\0\1\1\1\0\2\1" + "\1\0\1\11\2\0\20\1\4\11\1\1\1\0\3\1" + "\1\11\1\1\2\0\22\1\1\0\56\1";

	private static int[] zzUnpackAttribute() {
		int[] result = new int[142];
		int offset = 0;
		offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
		return result;
	}

	private static int zzUnpackAttribute(String packed, int offset, int[] result) {
		int i = 0; /* index in packed string */
		int j = offset; /* index in unpacked array */
		int l = packed.length();
		while (i < l) {
			int count = packed.charAt(i++);
			int value = packed.charAt(i++);
			do
				result[j++] = value;
			while (--count > 0);
		}
		return j;
	}

	/** the input device */
	private java.io.Reader zzReader;

	/** the current state of the DFA */
	private int zzState;

	/** the current lexical state */
	private int zzLexicalState = YYINITIAL;

	/**
	 * this buffer contains the current text to be matched and is the source of the
	 * yytext() string
	 */
	private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

	/** the textposition at the last accepting state */
	private int zzMarkedPos;

	/** the current text position in the buffer */
	private int zzCurrentPos;

	/** startRead marks the beginning of the yytext() string in the buffer */
	private int zzStartRead;

	/**
	 * endRead marks the last character in the buffer, that has been read from input
	 */
	private int zzEndRead;

	/** number of newlines encountered up to the start of the matched text */
	private int yyline;

	/** the number of characters up to the start of the matched text */
	private int yychar;

	/**
	 * the number of characters from the last newline up to the start of the matched
	 * text
	 */
	private int yycolumn;

	/**
	 * zzAtBOL == true <=> the scanner is currently at the beginning of a line
	 */
	private boolean zzAtBOL = true;

	/** zzAtEOF == true <=> the scanner is at the EOF */
	private boolean zzAtEOF;

	/** denotes if the user-EOF-code has already been executed */
	private boolean zzEOFDone;

	/* user code: */

	public enum tokens {
		t_bool, t_break, t_class, t_double, t_else, t_extends, t_for, t_if, t_implements, t_int, t_interface, t_newarray, t_println, t_readln, t_return, t_string, t_void, t_while, t_plus, t_minus, t_multiplication, t_division, t_mod, t_less, t_lessequal, t_greater, t_greaterequal, t_equal, t_notequal, t_assignop, t_semicolon, t_comma, t_period, t_leftparen, t_rightparen, t_leftbracket, t_rightbracket, t_leftbrace, t_rightbrace, t_boolconstant, t_intconstant, t_doubleconstant, t_stringconstant, t_id
	}

	public class symbol_table {
		public int[] control = new int[52];
		public ArrayList<Integer> next = new ArrayList<Integer>();
		public ArrayList<Character> symbol = new ArrayList<Character>();

		public symbol_table() {
			for (int i = 0; i < this.control.length; ++i) {
				this.control[i] = -1;
			}
		}
	}

	public symbol_table s = new symbol_table();

	// Return array index of character
	public int alphaIndex(char c) {
		int v = c;
		if (v >= 97) {
			return v - 97 + 26;
		}
		return v - 65;
	}

	public void trie(String str) {
		int value = alphaIndex(str.charAt(0));
		int ptr = s.control[value];

		if (ptr == -1) { // Undefined
			// point to last
			s.control[value] = s.symbol.size();
			// add the rest of the characters
			for (int i = 1; i < str.length(); ++i) {
				s.symbol.add(str.charAt(i));
			}
			s.symbol.add('*');
		} else { // Defined

			int i = 1; // 2nd character, 'i' is the symbol counter
			boolean exit = false;

			if (str.length() == 1) {
				return;
			}

			while (!exit) {
				if (s.symbol.get(ptr) == str.charAt(i)) {
					// if endmarker
					if (str.length() - 1 <= i) {
						exit = true;
						break;
					}
					i++;
					ptr++;
				} else if ((s.next.size() > ptr) && (s.next.get(ptr) != -1)) {
					ptr = s.next.get(ptr);
				} else {

					while (s.next.size() <= ptr) {
						s.next.add(-1);
					} // grow the (next) array

					// Set next available which will
					// always be size() (dynamically allocated)
					s.next.set(ptr, s.symbol.size());

					while (i < str.length()) {
						s.symbol.add(str.charAt(i++));
					}
					s.symbol.add('*');

					exit = true;
					break;
				}
			}

		}

	}

	public void printControl(int head, int tail) {
		System.out.printf("%-7s", "switch:");
		int v = 0;
		for (; head < tail; ++head) {
			v = s.control[head];
			if (v == -1) {
				System.out.printf("%4d", -1);
			} else {
				System.out.printf("%4d", v);
			}
		}
		System.out.println("\n");
	}

	public void printSymbol(int head, int tail) {
		System.out.printf("%-7s", "symbol:");
		for (int i = head; i < tail; ++i) {
			System.out.printf("%4c", s.symbol.get(i));
		}
		System.out.println();
	}

	public void printNext(int head, int tail) {
		System.out.printf("%-7s", "next:");
		int v = 0;
		for (int i = head; i < tail; ++i) {
			v = s.next.get(i);
			if (v == -1) {
				System.out.printf("%4s", "");
			} else {
				System.out.printf("%4d", v);
			}
		}
		System.out.println("\n");

	}

	private void equalizeNext() {
		if (s.symbol.size() > s.next.size()) {
			while (s.next.size() != s.symbol.size()) {
				s.next.add(-1);
			}
		}
	}

	public void printTable() {
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		System.out.printf("%-7s", "");
		int head = 0;
		int i = 0;
		for (; i < 52; ++i) {
			if (i != 0 && i % 20 == 0) {
				System.out.println();
				printControl(head, i);
				System.out.printf("%-7s", "");
				head = i;
			}
			System.out.printf("%4c", alpha.charAt(i));
		}
		System.out.println();
		printControl(head, i);

		equalizeNext();

		i = 0;
		head = 0;
		System.out.printf("%-7s", "");
		for (; i < s.symbol.size(); ++i) {
			if (i != 0 && i % 20 == 0) {
				System.out.println();
				printSymbol(head, i);
				printNext(head, i);
				System.out.printf("%-7s", "");
				head = i;
			}
			System.out.printf("%4d", i);
		}
		System.out.println();
		printSymbol(head, i);
		printNext(head, i);
	}

	/**
	 * Creates a new scanner There is also a java.io.InputStream version of this
	 * constructor.
	 *
	 * @param in
	 *            the java.io.Reader to read input from.
	 */
	public Lexer(java.io.Reader in) {
		this.zzReader = in;
	}

	/**
	 * Creates a new scanner. There is also java.io.Reader version of this
	 * constructor.
	 *
	 * @param in
	 *            the java.io.Inputstream to read input from.
	 */
	public Lexer(java.io.InputStream in) {
		this(new java.io.InputStreamReader(in));
	}

	/**
	 * Unpacks the compressed character translation table.
	 *
	 * @param packed
	 *            the packed character translation table
	 * @return the unpacked character translation table
	 */
	private static char[] zzUnpackCMap(String packed) {
		char[] map = new char[0x10000];
		int i = 0; /* index in packed string */
		int j = 0; /* index in unpacked array */
		while (i < 140) {
			int count = packed.charAt(i++);
			char value = packed.charAt(i++);
			do
				map[j++] = value;
			while (--count > 0);
		}
		return map;
	}

	/**
	 * Refills the input buffer.
	 *
	 * @return <code>false</code>, iff there was new input.
	 * 
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	private boolean zzRefill() throws java.io.IOException {

		/* first: make room (if you can) */
		if (zzStartRead > 0) {
			System.arraycopy(zzBuffer, zzStartRead, zzBuffer, 0, zzEndRead - zzStartRead);

			/* translate stored positions */
			zzEndRead -= zzStartRead;
			zzCurrentPos -= zzStartRead;
			zzMarkedPos -= zzStartRead;
			zzStartRead = 0;
		}

		/* is the buffer big enough? */
		if (zzCurrentPos >= zzBuffer.length) {
			/* if not: blow it up */
			char newBuffer[] = new char[zzCurrentPos * 2];
			System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
			zzBuffer = newBuffer;
		}

		/* finally: fill the buffer with new input */
		int numRead = zzReader.read(zzBuffer, zzEndRead, zzBuffer.length - zzEndRead);

		if (numRead > 0) {
			zzEndRead += numRead;
			return false;
		}
		// unlikely but not impossible: read 0 characters, but not at end of stream
		if (numRead == 0) {
			int c = zzReader.read();
			if (c == -1) {
				return true;
			} else {
				zzBuffer[zzEndRead++] = (char) c;
				return false;
			}
		}

		// numRead < 0
		return true;
	}

	/**
	 * Closes the input stream.
	 */
	public final void yyclose() throws java.io.IOException {
		zzAtEOF = true; /* indicate end of file */
		zzEndRead = zzStartRead; /* invalidate buffer */

		if (zzReader != null)
			zzReader.close();
	}

	/**
	 * Resets the scanner to read from a new input stream. Does not close the old
	 * reader.
	 *
	 * All internal variables are reset, the old input stream <b>cannot</b> be
	 * reused (internal buffer is discarded and lost). Lexical state is set to
	 * <tt>ZZ_INITIAL</tt>.
	 *
	 * @param reader
	 *            the new input stream
	 */
	public final void yyreset(java.io.Reader reader) {
		zzReader = reader;
		zzAtBOL = true;
		zzAtEOF = false;
		zzEOFDone = false;
		zzEndRead = zzStartRead = 0;
		zzCurrentPos = zzMarkedPos = 0;
		yyline = yychar = yycolumn = 0;
		zzLexicalState = YYINITIAL;
	}

	/**
	 * Returns the current lexical state.
	 */
	public final int yystate() {
		return zzLexicalState;
	}

	/**
	 * Enters a new lexical state
	 *
	 * @param newState
	 *            the new lexical state
	 */
	public final void yybegin(int newState) {
		zzLexicalState = newState;
	}

	/**
	 * Returns the text matched by the current regular expression.
	 */
	public final String yytext() {
		return new String(zzBuffer, zzStartRead, zzMarkedPos - zzStartRead);
	}

	/**
	 * Returns the character at position <tt>pos</tt> from the matched text.
	 * 
	 * It is equivalent to yytext().charAt(pos), but faster
	 *
	 * @param pos
	 *            the position of the character to fetch. A value from 0 to
	 *            yylength()-1.
	 *
	 * @return the character at position pos
	 */
	public final char yycharat(int pos) {
		return zzBuffer[zzStartRead + pos];
	}

	/**
	 * Returns the length of the matched text region.
	 */
	public final int yylength() {
		return zzMarkedPos - zzStartRead;
	}

	/**
	 * Reports an error that occured while scanning.
	 *
	 * In a wellformed scanner (no or only correct usage of yypushback(int) and a
	 * match-all fallback rule) this method will only be called with things that
	 * "Can't Possibly Happen". If this method is called, something is seriously
	 * wrong (e.g. a JFlex bug producing a faulty scanner etc.).
	 *
	 * Usual syntax/scanner level error handling should be done in error fallback
	 * rules.
	 *
	 * @param errorCode
	 *            the code of the errormessage to display
	 */
	private void zzScanError(int errorCode) {
		String message;
		try {
			message = ZZ_ERROR_MSG[errorCode];
		} catch (ArrayIndexOutOfBoundsException e) {
			message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
		}

		throw new Error(message);
	}

	/**
	 * Pushes the specified amount of characters back into the input stream.
	 *
	 * They will be read again by then next call of the scanning method
	 *
	 * @param number
	 *            the number of characters to be read again. This number must not be
	 *            greater than yylength()!
	 */
	public void yypushback(int number) {
		if (number > yylength())
			zzScanError(ZZ_PUSHBACK_2BIG);

		zzMarkedPos -= number;
	}

	/**
	 * Contains user EOF-code, which will be executed exactly once, when the end of
	 * file is reached
	 */
	private void zzDoEOF() throws java.io.IOException {
		if (!zzEOFDone) {
			zzEOFDone = true;
			yyclose();
		}
	}

	/**
	 * Resumes scanning until the next regular expression is matched, the end of
	 * input is encountered or an I/O-Error occurs.
	 *
	 * @return the next token
	 * @exception java.io.IOException
	 *                if any I/O-Error occurs
	 */
	public Integer yylex() throws java.io.IOException {
		int zzInput;
		int zzAction;

		// cached fields:
		int zzCurrentPosL;
		int zzMarkedPosL;
		int zzEndReadL = zzEndRead;
		char[] zzBufferL = zzBuffer;
		char[] zzCMapL = ZZ_CMAP;

		int[] zzTransL = ZZ_TRANS;
		int[] zzRowMapL = ZZ_ROWMAP;
		int[] zzAttrL = ZZ_ATTRIBUTE;
		
		while (true) {
			zzMarkedPosL = zzMarkedPos;

			boolean zzR = false;
			for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL; zzCurrentPosL++) {
				switch (zzBufferL[zzCurrentPosL]) {
				case '\u000B':
				case '\u000C':
				case '\u0085':
				case '\u2028':
				case '\u2029':
					yyline++;
					yycolumn = 0;
					zzR = false;
					break;
				case '\r':
					yyline++;
					yycolumn = 0;
					zzR = true;
					break;
				case '\n':
					if (zzR)
						zzR = false;
					else {
						yyline++;
						yycolumn = 0;
					}
					break;
				default:
					zzR = false;
					yycolumn++;
				}
			}

			if (zzR) {
				// peek one character ahead if it is \n (if we have counted one line too much)
				boolean zzPeek;
				if (zzMarkedPosL < zzEndReadL)
					zzPeek = zzBufferL[zzMarkedPosL] == '\n';
				else if (zzAtEOF)
					zzPeek = false;
				else {
					boolean eof = zzRefill();
					zzEndReadL = zzEndRead;
					zzMarkedPosL = zzMarkedPos;
					zzBufferL = zzBuffer;
					if (eof)
						zzPeek = false;
					else
						zzPeek = zzBufferL[zzMarkedPosL] == '\n';
				}
				if (zzPeek)
					yyline--;
			}
			zzAction = -1;

			zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

			zzState = ZZ_LEXSTATE[zzLexicalState];

			zzForAction: {
				while (true) {
					if (zzCurrentPosL < zzEndReadL)
						zzInput = zzBufferL[zzCurrentPosL++];
					else if (zzAtEOF) {
						zzInput = YYEOF;
						break zzForAction;
					} else {
						// store back cached positions
						zzCurrentPos = zzCurrentPosL;
						zzMarkedPos = zzMarkedPosL;
						boolean eof = zzRefill();
						// get translated positions and possibly new buffer
						zzCurrentPosL = zzCurrentPos;
						zzMarkedPosL = zzMarkedPos;
						zzBufferL = zzBuffer;
						zzEndReadL = zzEndRead;
						if (eof) {
							zzInput = YYEOF;
							break zzForAction;
						} else {
							zzInput = zzBufferL[zzCurrentPosL++];
						}
					}
					int zzNext = zzTransL[zzRowMapL[zzState] + zzCMapL[zzInput]];
					if (zzNext == -1)
						break zzForAction;
					zzState = zzNext;

					int zzAttributes = zzAttrL[zzState];
					if ((zzAttributes & 1) == 1) {
						zzAction = zzState;
						zzMarkedPosL = zzCurrentPosL;
						if ((zzAttributes & 8) == 8)
							break zzForAction;
					}

				}
			}

			// store back cached position
			zzMarkedPos = zzMarkedPosL;

			switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
			case 32: {
				System.out.printf("%s ", yytext());
				return tokens.t_else.ordinal();
			}
			case 48:
				break;
			case 35: {
				System.out.printf("%s ", yytext());
				return tokens.t_void.ordinal();
			}
			case 49:
				break;
			case 31: {
				System.out.printf("%s ", yytext());
				return tokens.t_int.ordinal();
			}
			case 50:
				break;
			case 42: {
				System.out.printf("%s ", yytext());
				return tokens.t_double.ordinal();
			}
			case 51:
				break;
			case 46: {
				System.out.printf("%s ", yytext());
				return tokens.t_interface.ordinal();
			}
			case 52:
				break;
			case 22: {
				System.out.print("rightbrace ");
				return tokens.t_rightbrace.ordinal();
			}
			case 53:
				break;
			case 37: {
				System.out.printf("%s ", yytext());
				return tokens.t_class.ordinal();
			}
			case 54:
				break;
			case 5: {
				System.out.print("minus ");
				return tokens.t_minus.ordinal();
			}
			case 55:
				break;
			case 2: {
				System.out.print("id ");
				trie(yytext());
				return tokens.t_id.ordinal();
			}
			case 56:
				break;
			case 34: {
				System.out.print("boolconstant ");
				return tokens.t_boolconstant.ordinal();
			}
			case 57:
				break;
			case 43: {
				System.out.printf("%s ", yytext());
				return tokens.t_extends.ordinal();
			}
			case 58:
				break;
			case 33: {
				System.out.printf("%s ", yytext());
				return tokens.t_bool.ordinal();
			}
			case 59:
				break;
			case 38: {
				System.out.printf("%s ", yytext());
				return tokens.t_while.ordinal();
			}
			case 60:
				break;
			case 47: {
				System.out.printf("%s ", yytext());
				return tokens.t_implements.ordinal();
			}
			case 61:
				break;
			case 20: {
				System.out.print("leftbracket ");
				return tokens.t_leftbracket.ordinal();
			}
			case 62:
				break;
			case 28: {
				System.out.print("greaterequal ");
				return tokens.t_greaterequal.ordinal();
			}
			case 63:
				break;
			case 15: {
				System.out.print("semicolon ");
				return tokens.t_semicolon.ordinal();
			}
			case 64:
				break;
			case 3: {
				System.out.print("intconstant ");
				return tokens.t_intconstant.ordinal();
			}
			case 65:
				break;
			case 24: {
				System.out.print("stringconstant ");
				return tokens.t_stringconstant.ordinal();
			}
			case 66:
				break;
			case 30: {
				System.out.printf("%s ", yytext());
				return tokens.t_for.ordinal();
			}
			case 67:
				break;
			case 14: {
				System.out.print("greater ");
				return tokens.t_greater.ordinal();
			}
			case 68:
				break;
			case 41: {
				System.out.printf("%s ", yytext());
				return tokens.t_string.ordinal();
			}
			case 69:
				break;
			case 21: {
				System.out.print("leftbrace ");
				return tokens.t_leftbrace.ordinal();
			}
			case 70:
				break;
			case 45: {
				System.out.printf("%s ", yytext());
				return tokens.t_newarray.ordinal();
			}
			case 71:
				break;
			case 13: {
				System.out.print("assignop ");
				return tokens.t_assignop.ordinal();
			}
			case 72:
				break;
			case 27: {
				System.out.print("equal ");
				return tokens.t_equal.ordinal();
			}
			case 73:
				break;
			case 26: {
				System.out.print("lessequal ");
				return tokens.t_lessequal.ordinal();
			}
			case 74:
				break;
			case 44: {
				System.out.printf("%s ", yytext());
				return tokens.t_println.ordinal();
			}
			case 75:
				break;
			case 25: {
				System.out.printf("%s ", yytext());
				return tokens.t_if.ordinal();
			}
			case 76:
				break;
			case 39: {
				System.out.printf("%s ", yytext());
				return tokens.t_readln.ordinal();
			}
			case 77:
				break;
			case 6: {
				System.out.print("period ");
				return tokens.t_period.ordinal();
			}
			case 78:
				break;
			case 7: {
				System.out.print("\n");
			}
			case 79:
				break;
			case 9: {
				System.out.print("division ");
				return tokens.t_division.ordinal();
			}
			case 80:
				break;
			case 11: {
				System.out.print("mod ");
				return tokens.t_mod.ordinal();
			}
			case 81:
				break;
			case 17: {
				System.out.print("leftparen ");
				return tokens.t_leftparen.ordinal();
			}
			case 82:
				break;
			case 4: {
				System.out.print("plus ");
				return tokens.t_plus.ordinal();
			}
			case 83:
				break;
			case 40: {
				System.out.printf("%s ", yytext());
				return tokens.t_return.ordinal();
			}
			case 84:
				break;
			case 1: { /* ignore illegal chars */
			}
			case 85:
				break;
			case 18: {
				System.out.print("rightparen ");
				return tokens.t_rightparen.ordinal();
			}
			case 86:
				break;
			case 10: {
				System.out.print("multiplication ");
				return tokens.t_multiplication.ordinal();
			}
			case 87:
				break;
			case 19: {
				System.out.print("rightbracket ");
				return tokens.t_rightbracket.ordinal();
			}
			case 88:
				break;
			case 29: {
				System.out.print("notequal ");
				return tokens.t_notequal.ordinal();
			}
			case 89:
				break;
			case 36: {
				System.out.printf("%s ", yytext());
				return tokens.t_break.ordinal();
			}
			case 90:
				break;
			case 16: {
				System.out.print("comma ");
				return tokens.t_comma.ordinal();
			}
			case 91:
				break;
			case 23: {
				System.out.print("doubleconstant ");
				return tokens.t_doubleconstant.ordinal();
			}
			case 92:
				break;
			case 12: {
				System.out.print("less ");
				return tokens.t_less.ordinal();
			}
			case 93:
				break;
			case 8: {
			}
			case 94:
				break;
			default:
				if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
					zzAtEOF = true;
					zzDoEOF();
					return null;
				} else {
					zzScanError(ZZ_NO_MATCH);
				}
			}
		}
	}

}
