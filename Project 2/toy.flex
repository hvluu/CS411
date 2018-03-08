/*CS 411 Lexer*/

import java.util.ArrayList;
import java_cup.runtime.*;

%%

%public
%class Lexer
%cup
%type Symbol
%intwrap
%unicode
%line
%column
%eofclose


%{

    private Symbol symbol(int sym) {
        return new Symbol(sym, yyline+1, yycolumn+1);
    }

    public class symbol_table {
        public int [] control = new int[52];
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
        s.symbol.add('@'); 
    }
    else { // Defined
        
        int i = 1; // 2nd character, 'i' is the symbol counter
        boolean exit = false;

        if(str.length() == 1) {
            return;
        }

        while(!exit) {
            if (s.symbol.get(ptr) == str.charAt(i)) {
                // if endmarker
                if(str.length() -1 <= i) {
                    exit = true;
                    break; 
                }
                i++; 
                ptr++;
            }
            else if((s.next.size() > ptr) && (s.next.get(ptr) != -1)) {
                ptr = s.next.get(ptr);
            }
            else {

                while(s.next.size() <= ptr) {
                    s.next.add(-1);
                } // grow the (next) array

                // Set next available which will 
                // always be size() (dynamically allocated)
                s.next.set(ptr,s.symbol.size()); 

                while(i < str.length()) {
                    s.symbol.add(str.charAt(i++));
                }
                s.symbol.add('@');

                exit = true;
                break;
            }
        }


    }

}

public void printControl(int head, int tail) {
    System.out.printf("%-10s", "switch:");
    int v = 0;
    for (; head < tail; ++head) {
        v = s.control[head];
        if (v == -1) {
            System.out.print("$   ");
        }
        else {
            System.out.printf("%-3d ", v);
        }
    }
    System.out.println("\n");
}

public void printSymbol(int head, int tail) {
    System.out.printf("%-10s", "symbol:");
    for(int i = head; i < tail; ++i) {
        System.out.printf("%c   ", s.symbol.get(i));
    }
    System.out.println();
}

public void printNext(int head, int tail) {
    System.out.printf("%-10s", "next:");
    int v = 0;
    for (int i = head; i < tail; ++i) {
        v = s.next.get(i);
        if (v == -1) {
            System.out.print("$   ");
        }
        else {
            System.out.printf("%-3d ", v);
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
    System.out.printf("%-10s","");
    int head = 0;
    int i = 0;
    for (; i < 52; ++i) {
        if ((i+1)%20 == 0) {
            System.out.println();
            printControl(head,i);
            System.out.printf("%-10s","");
            head = i;
        }
        System.out.printf("%c   ", alpha.charAt(i));
    }
    System.out.println();
    printControl(head,i);

    equalizeNext();

    i = 0;
    head  = 0;
    System.out.printf("%-10s",""); 
    for (; i < s.symbol.size(); ++i) {
        if ((i+1)%20 == 0) {
            System.out.println();
            printSymbol(head,i);
            printNext(head,i);
            System.out.printf("%-10s","");
            head = i;
        }
        System.out.printf("%-3d ", i);
    }
    System.out.println();
    printSymbol(head,i);
    printNext(head,i);
}

%}

/*The alphabet*/
letter = [a-zA-Z]

/*Base 10 digits*/
digit = [0-9]

/*Hex with 0x or 0X followed by 1 or more hex digits*/
hex = (0x|0X)[a-fA-F0-9]+

/*Integer is base 10 or 16*/
integer = ({digit}+|{hex})

/*Exponents begining with e/E, optional sign, 1+ digits*/
exponent = ((E|e)("+"|"-")?({digit})+)

/*Float with optional exponent, and int with exponent*/
double = (({digit}+"."{digit}*{exponent}?)|({digit}+{exponent}))

/*Identifiers begin with letter, followed by letter/digit/underscore*/
identifier = {letter}({letter}|{digit}|"_")*

/*double quote, anything but newline and quote, double quote*/
string = \"([^\"\n])*\"

/*New line character class*/
newline = \n

/*spaces and tabs*/
whitespace = [ \t]+

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
Comment = ({TraditionalComment}|{EndOfLineComment})

%%

/*Ignore comments that match the following*/

{Comment} { }

{string} {return symbol(sym.t_stringconstant);}

/*Keywords*/

bool        {System.out.printf("%n%s ",yytext());return symbol(sym.t_bool);}
break       {System.out.printf("%n%s ",yytext());return symbol(sym.t_break);}
class       {System.out.printf("%n%s ",yytext());return symbol(sym.t_class);}
double      {System.out.printf("%n%s ",yytext());return symbol(sym.t_double);}
else        {System.out.printf("%n%s ",yytext());return symbol(sym.t_else);}
extends     {System.out.printf("%n%s ",yytext());return symbol(sym.t_extends);}
for         {System.out.printf("%n%s ",yytext());return symbol(sym.t_for);}
if          {System.out.printf("%n%s ",yytext());return symbol(sym.t_if);}
implements  {System.out.printf("%n%s ",yytext());return symbol(sym.t_implements);}
int         {System.out.printf("%n%s ",yytext());return symbol(sym.t_int);}
interface   {System.out.printf("%n%s ",yytext());return symbol(sym.t_interface);}
newarray    {System.out.printf("%n%s ",yytext());return symbol(sym.t_newarray);}
println     {System.out.printf("%n%s ",yytext());return symbol(sym.t_println);}
readln      {System.out.printf("%n%s ",yytext());return symbol(sym.t_readln);}
return      {System.out.printf("%n%s ",yytext());return symbol(sym.t_return);}
string      {System.out.printf("%n%s ",yytext());return symbol(sym.t_string);}
void        {System.out.printf("%n%s ",yytext());return symbol(sym.t_void);}
while       {System.out.printf("%n%s ",yytext());return symbol(sym.t_while);}

/*stupid thing has to be declared up here or it will match id */
true|false   {System.out.printf("%n%s ",yytext());return symbol(sym.t_boolconstant);}

{identifier} {System.out.printf("%n%s ",yytext());trie(yytext());return symbol(sym.t_id);}
{whitespace} { }
{LineTerminator} { } /*preserve line breaks*/
{integer}    {System.out.printf("%n%s ",yytext());return symbol(sym.t_intconstant);}
{double}     {System.out.printf("%n%s ",yytext());return symbol(sym.t_doubleconstant);}


/*Operators and Punctuation*/

"+"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_plus);}
"-"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_minus);}
"*"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_multiplication);}
"/"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_division);}
"%"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_mod);}
"<"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_less);}
"<="    {System.out.printf("%n%s ",yytext());return symbol(sym.t_lessequal);}
">"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_greater);}
">="    {System.out.printf("%n%s ",yytext());return symbol(sym.t_greaterequal);}
"=="    {System.out.printf("%n%s ",yytext());return symbol(sym.t_equal);}
"!="    {System.out.printf("%n%s ",yytext());return symbol(sym.t_notequal);}
"="     {System.out.printf("%n%s ",yytext());return symbol(sym.t_assignop);}
";"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_semicolon);}
","     {System.out.printf("%n%s ",yytext());return symbol(sym.t_comma);}
"."     {System.out.printf("%n%s ",yytext());return symbol(sym.t_period);}
"("     {System.out.printf("%n%s ",yytext());return symbol(sym.t_leftparen);}
")"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_rightparen);}
"["     {System.out.printf("%n%s ",yytext());return symbol(sym.t_leftbracket);}
"]"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_rightbracket);}
"{"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_leftbrace);}
"}"     {System.out.printf("%n%s ",yytext());return symbol(sym.t_rightbrace);}

.       {System.out.printf("%n%s ",yytext());return symbol(sym.error);}
<<EOF>> {return symbol(sym.EOF);}
