/* The following code was generated by JFlex 1.4.1 on 6/23/18 4:56 PM */

// ************  Código a incluir ********************

package scanner;
import parser.Parser;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 6/23/18 4:56 PM from the specification file
 * <tt>src/scanner/scanner.jflex</tt>
 */
public class Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\2\1\10\2\0\1\2\22\0\1\2\1\43\1\14\1\13"+
    "\1\0\1\1\1\44\1\4\5\1\1\7\1\5\1\1\12\3\2\1"+
    "\1\42\1\41\1\40\1\1\1\0\4\12\1\6\25\12\1\1\1\11"+
    "\1\1\1\0\1\12\1\0\1\35\1\34\1\32\1\15\1\16\1\17"+
    "\1\12\1\25\1\26\2\12\1\27\1\37\1\23\1\33\1\31\1\12"+
    "\1\20\1\30\1\21\1\22\1\36\1\24\3\12\1\1\1\45\1\1"+
    "\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\3\1\1\1\4\1\5\1\3\1\6"+
    "\1\1\1\2\12\6\6\3\1\5\1\0\2\5\1\0"+
    "\1\5\1\0\5\6\1\7\10\6\1\10\1\11\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\5\1\0"+
    "\1\20\1\0\1\21\4\6\1\22\10\6\1\0\1\6"+
    "\1\23\6\6\1\24\1\25\1\26\1\27\1\0\2\6"+
    "\1\30\1\31\2\6\1\32\1\1\1\33\1\34\1\35"+
    "\1\36";

  private static int [] zzUnpackAction() {
    int [] result = new int[100];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\46\0\46\0\114\0\162\0\230\0\276\0\344"+
    "\0\u010a\0\u0130\0\u0156\0\u017c\0\u01a2\0\u01c8\0\u01ee\0\u0214"+
    "\0\u023a\0\u0260\0\u0286\0\u02ac\0\u02d2\0\u02f8\0\u031e\0\u0344"+
    "\0\u036a\0\u0390\0\276\0\u03b6\0\u03dc\0\u0402\0\u0428\0\u044e"+
    "\0\u0474\0\u049a\0\u04c0\0\u04e6\0\u050c\0\u0532\0\344\0\u0558"+
    "\0\u057e\0\u05a4\0\u05ca\0\u05f0\0\u0616\0\u063c\0\u0662\0\46"+
    "\0\46\0\46\0\46\0\46\0\46\0\46\0\46\0\46"+
    "\0\u0688\0\u06ae\0\u03b6\0\u06d4\0\344\0\u06fa\0\u0720\0\u0746"+
    "\0\u076c\0\344\0\u0792\0\u07b8\0\u07de\0\u0804\0\u082a\0\u0850"+
    "\0\u0876\0\u089c\0\u08c2\0\u08e8\0\344\0\u090e\0\u0934\0\u095a"+
    "\0\u0980\0\u09a6\0\u09cc\0\344\0\344\0\344\0\344\0\u09f2"+
    "\0\u0a18\0\u0a3e\0\344\0\344\0\u0a64\0\u0a8a\0\344\0\46"+
    "\0\344\0\344\0\344\0\344";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[100];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
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
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\3"+
    "\1\4\1\2\1\10\1\11\1\12\1\13\1\14\1\10"+
    "\1\15\3\10\1\16\1\10\1\17\1\10\1\20\1\21"+
    "\1\22\3\10\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\50\0\1\4\5\0\1\4\40\0\1\5"+
    "\2\33\1\10\3\0\1\10\2\0\23\10\6\0\3\34"+
    "\1\35\2\34\1\36\1\34\1\0\1\37\4\34\1\36"+
    "\27\34\3\0\1\33\2\0\1\40\7\0\1\40\32\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\23\10\6\0"+
    "\10\11\1\0\35\11\14\0\1\41\34\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\1\10\1\42\14\10\1\43"+
    "\4\10\11\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\12\10\1\44\10\10\11\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\1\10\1\45\21\10\11\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\10\10\1\46\12\10\11\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\2\10\1\47"+
    "\3\10\1\50\14\10\11\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\4\10\1\51\2\10\1\52\13\10\11\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\3\10\1\53"+
    "\17\10\11\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\10\10\1\54\7\10\1\55\2\10\11\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\16\10\1\56\4\10\11\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\20\10\1\57"+
    "\2\10\46\0\1\60\1\61\45\0\1\62\45\0\1\63"+
    "\1\64\44\0\1\65\50\0\1\66\46\0\1\67\4\0"+
    "\1\70\44\0\1\35\1\70\1\0\1\40\7\0\1\40"+
    "\32\0\1\71\1\70\2\0\1\71\36\0\3\34\1\72"+
    "\1\73\3\34\1\0\35\34\3\0\1\71\3\0\1\71"+
    "\52\0\1\74\34\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\2\10\1\75\20\10\11\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\5\10\1\76\15\10\11\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\13\10\1\77\7\10"+
    "\11\0\1\10\2\0\1\10\3\0\1\10\2\0\4\10"+
    "\1\100\16\10\11\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\11\10\1\101\11\10\11\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\4\10\1\102\7\10\1\103\6\10"+
    "\11\0\1\10\2\0\1\10\3\0\1\10\2\0\3\10"+
    "\1\104\17\10\11\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\11\10\1\105\11\10\11\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\11\10\1\106\11\10\11\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\20\10\1\107\2\10"+
    "\11\0\1\10\2\0\1\10\3\0\1\10\2\0\13\10"+
    "\1\110\7\10\11\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\11\10\1\111\11\10\11\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\11\10\1\112\11\10\11\0\1\71"+
    "\45\0\1\72\1\70\43\0\5\74\1\0\1\74\1\0"+
    "\1\74\1\0\1\113\23\74\11\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\17\10\1\114\3\10\11\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\1\10\1\115\21\10"+
    "\11\0\1\10\2\0\1\10\3\0\1\10\2\0\5\10"+
    "\1\116\15\10\11\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\12\10\1\117\10\10\11\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\5\10\1\120\15\10\11\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\5\10\1\121\15\10"+
    "\11\0\1\10\2\0\1\10\3\0\1\10\2\0\4\10"+
    "\1\122\16\10\11\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\6\10\1\123\14\10\11\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\3\10\1\124\17\10\11\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\1\10\1\125\21\10"+
    "\11\0\1\10\2\0\1\10\3\0\1\10\2\0\1\126"+
    "\22\10\11\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\6\10\1\127\14\10\22\0\1\130\34\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\12\10\1\131\10\10\11\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\3\10\1\132"+
    "\17\10\11\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\1\10\1\133\21\10\11\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\4\10\1\134\16\10\11\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\15\10\1\135\5\10\11\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\15\10\1\136"+
    "\5\10\11\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\4\10\1\137\16\10\22\0\1\140\34\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\1\10\1\141\21\10\11\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\6\10\1\142"+
    "\14\10\11\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\4\10\1\143\16\10\11\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\10\10\1\144\12\10\6\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2736];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\2\11\30\1\1\0\2\1\1\0\1\1\1\0"+
    "\16\1\11\11\1\1\1\0\1\1\1\0\16\1\1\0"+
    "\14\1\1\0\7\1\1\11\4\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[100];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
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
// ************  Atributos y métodos ********************

// * Para acceder al número de línea (yyline es package)
public int getLine() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al número de columna (yycolumn es package)
public int getColumn() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 122) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
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
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
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
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 27: 
          { this.yylval = new String(yytext());
         			    return Parser.REAL_TYPE;
          }
        case 31: break;
        case 3: 
          { return yytext().charAt(0);
          }
        case 32: break;
        case 23: 
          { this.yylval = new String(yytext());
         			    return Parser.MAIN;
          }
        case 33: break;
        case 6: 
          { this.yylval = new String(yytext());
						return Parser.ID;
          }
        case 34: break;
        case 11: 
          { this.yylval = yytext();
         			    return Parser.LESS_THAN;
          }
        case 35: break;
        case 4: 
          { this.yylval = new Integer(yytext());
         			    return Parser.INT_CONSTANT;
          }
        case 36: break;
        case 9: 
          { this.yylval = yytext();
         			    return Parser.GREATER_THAN;
          }
        case 37: break;
        case 19: 
          { this.yylval = new String(yytext());
         			    return Parser.ELSE;
          }
        case 38: break;
        case 7: 
          { this.yylval = new String(yytext());
         			    return Parser.IF;
          }
        case 39: break;
        case 24: 
          { this.yylval = new String(yytext());
         			    return Parser.WHILE;
          }
        case 40: break;
        case 22: 
          { this.yylval = new String(yytext());
         			    return Parser.VOID;
          }
        case 41: break;
        case 25: 
          { this.yylval = new String(yytext());
         			    return Parser.INPUT;
          }
        case 42: break;
        case 2: 
          { System.err.println ("Lexical error at line " 
		+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'.");
          }
        case 43: break;
        case 5: 
          { this.yylval = new Double(yytext());
						return Parser.REAL_CONSTANT;
          }
        case 44: break;
        case 20: 
          { this.yylval = new String(yytext());
         			    return Parser.CHAR_TYPE;
          }
        case 45: break;
        case 14: 
          { this.yylval = yytext();
         			    return Parser.AND;
          }
        case 46: break;
        case 15: 
          { this.yylval = yytext();
         			    return Parser.OR;
          }
        case 47: break;
        case 29: 
          { this.yylval = new String(yytext());
         			    return Parser.STRUCT;
          }
        case 48: break;
        case 8: 
          { this.yylval = yytext();
         			    return Parser.RANGE_RIGHT;
          }
        case 49: break;
        case 10: 
          { this.yylval = yytext();
         			    return Parser.EQ;
          }
        case 50: break;
        case 21: 
          { this.yylval = yytext();
         			    return Parser.CASE;
          }
        case 51: break;
        case 12: 
          { this.yylval = yytext();
         			    return Parser.RANGE_LEFT;
          }
        case 52: break;
        case 17: 
          { this.yylval = new String(yytext());
         			    return Parser.def;
          }
        case 53: break;
        case 28: 
          { this.yylval = new String(yytext());
         			    return Parser.RETURN;
          }
        case 54: break;
        case 26: 
          { this.yylval = new String(yytext());
         			    return Parser.PRINT;
          }
        case 55: break;
        case 30: 
          { this.yylval = yytext();
         			    return Parser.SWITCH;
          }
        case 56: break;
        case 13: 
          { this.yylval = yytext();
         			    return Parser.NEQ;
          }
        case 57: break;
        case 18: 
          { this.yylval = new String(yytext());
         			    return Parser.INT;
          }
        case 58: break;
        case 16: 
          { this.yylval = new String(yytext());
         			    return Parser.CHAR_CONSTANT;
          }
        case 59: break;
        case 1: 
          { 
          }
        case 60: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
