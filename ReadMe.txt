=======================================
| CS411.01 Compilers and Interpreters
| Professor Sang
| Project 1 - Lexical Analyzer
=======================================

Contributors
===============

Hung V Luu
Andrew Trang
Michaela [LastName]


How To Run
=============

To run the program, enter "java LexerTest inputFile" into the console after compiling the java files. Replace "inputFile" with the name of the text file of your choice and press 'Enter'. The program should generate a list of tokens and the Trie Table detailing the content of the arrays.

NOTE: The inputFile should be placed in the same folder as the compiled java file for it to run properly.


Description
==============

This program is a lexical analyzer for a simple object-oriented programming language. The program is able to translate any input into a sequence of tokens and creates a symbol table using the trie structure for all the keywords and user-defined identifiers.


Lexical Conventions
======================

1. Keywords are reserved words that cannot be used as identifiers nor be redefined.

	INCLUDES:
	boolean break class double else extends false for if implements int interface newarray println readln return string true void while

2. Identifiers are sequences of letters, digits, and underscores that begin with a letter. Note that identifiers are case sensitive.

3. White space consists of blanks, newlines, and tab inputs. These characters are ignored by the lexical analyzer and only serve as token separators.

4. An integer constant can be specified in decimal or hexadecimal. Hexadecimal integers must begin with 0X or 0x followed by a sequence of at least one hexadecimal digit. They are not case sensitive. eg) 0xABADCAB

5. A double constant is a sequence of at least one digit, a period, followed by any sequence of zero or more digits. Exponents are optional and can be represented with the letter 'E'. The sign of the exponent is optional and will assume positive if not stated. It is not case sensitive. Leading zeroes on the mantissa and exponent are allowed. eg) 12.E+2 [Valid], .12E+2 [Invalid]

6. A string constant is a sequence of characters enclosed in double quotations. Strings can contain any character except a newline or double quote. A string must start and end on a single line and cannot be split over multiple lines.

7. A boolean constant is either 'true' or 'false'.

8. Operators and Punctuation Characters
	
	INCLUDES:
	+ - * / % < <= > >= == != && || ! = ; , . ( ) [ ] { }

9. A single line comment is started with "//" and extends to the end of the line. Multi-line comments start with "/*" and end with the first subsequent "*/". Any symbol is allowed in a comment except the comment termination sequence. Multi-line comments do not nest.

Definition of Tokens
=======================

Tokens are simple syntactic units defined based on the lexical rules of the language. The set of tokens consist of grammar elements of the language including keywords, operators, and special symbols, identifiers, and constants.

Tokens are represented as integers in the Lexer and are passed as parameters to the parser of the subsequent project. In this project, the program will print the corresponding string names of the ordinal numbers for verification.