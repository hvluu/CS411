public class Driver {
  public static void main(String...z) {
    Lexer scanner = null;
    try {
      scanner = new Lexer( new java.io.FileReader(z[0]) );
    }
    catch(Exception e) {
      System.out.printf("File %s doesn't exist\n", z[0]);
    }
    try {
      parser p = new parser(scanner);
      p.parse();
      //p.debug_parse();
      System.out.println();
    }
    catch(Exception e) {
      System.out.println("Exception while parsing");
    }
  }
}