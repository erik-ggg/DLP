/* The following code was generated by JFlex 1.4.1 on 3/4/18 11:10 PM */

// ************  Código a incluir ********************

package scanner;
import parser.Parser;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 3/4/18 11:10 PM from the specification file
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
    "\11\0\1\2\1\10\2\0\1\2\22\0\1\2\1\1\1\14\1\13"+
    "\1\0\1\1\1\37\1\4\5\1\1\7\1\5\1\1\12\3\5\1"+
    "\2\0\4\12\1\6\25\12\1\1\1\11\1\1\1\0\1\12\1\0"+
    "\1\35\1\34\1\32\1\15\1\16\1\17\1\12\1\25\1\26\2\12"+
    "\1\27\1\12\1\23\1\33\1\31\1\12\1\20\1\30\1\21\1\22"+
    "\1\36\1\24\3\12\1\1\1\40\1\1\uff82\0";

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
    "\1\1\1\2\11\6\2\3\1\5\1\0\2\5\1\0"+
    "\1\5\1\0\5\6\1\7\5\6\1\10\1\11\1\12"+
    "\1\5\1\0\1\12\1\0\1\13\4\6\1\14\5\6"+
    "\1\0\1\6\1\15\5\6\1\16\1\17\1\0\2\6"+
    "\1\20\1\21\1\6\1\22\1\1\1\23\1\24\1\25";

  private static int [] zzUnpackAction() {
    int [] result = new int[78];
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
    "\0\0\0\41\0\41\0\102\0\143\0\204\0\245\0\306"+
    "\0\347\0\u0108\0\u0129\0\u014a\0\u016b\0\u018c\0\u01ad\0\u01ce"+
    "\0\u01ef\0\u0210\0\u0231\0\u0252\0\u0273\0\245\0\u0294\0\u02b5"+
    "\0\u02d6\0\u02f7\0\u0318\0\u0339\0\u035a\0\u037b\0\u039c\0\u03bd"+
    "\0\u03de\0\306\0\u03ff\0\u0420\0\u0441\0\u0462\0\u0483\0\41"+
    "\0\41\0\41\0\u04a4\0\u04c5\0\u0294\0\u04e6\0\306\0\u0507"+
    "\0\u0528\0\u0549\0\u056a\0\306\0\u058b\0\u05ac\0\u05cd\0\u05ee"+
    "\0\u060f\0\u0630\0\u0651\0\306\0\u0672\0\u0693\0\u06b4\0\u06d5"+
    "\0\u06f6\0\306\0\306\0\u0717\0\u0738\0\u0759\0\306\0\306"+
    "\0\u077a\0\306\0\41\0\306\0\306\0\306";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[78];
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
    "\1\22\3\10\1\23\1\24\1\25\43\0\1\4\5\0"+
    "\1\4\33\0\1\5\2\26\1\10\3\0\1\10\2\0"+
    "\22\10\2\0\3\27\1\30\2\27\1\31\1\27\1\0"+
    "\1\32\4\27\1\31\22\27\3\0\1\26\2\0\1\33"+
    "\7\0\1\33\25\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\22\10\2\0\10\11\1\0\30\11\14\0\1\34"+
    "\27\0\1\10\2\0\1\10\3\0\1\10\2\0\1\10"+
    "\1\35\14\10\1\36\3\10\5\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\12\10\1\37\7\10\5\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\1\10\1\40\20\10"+
    "\5\0\1\10\2\0\1\10\3\0\1\10\2\0\10\10"+
    "\1\41\11\10\5\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\2\10\1\42\3\10\1\43\13\10\5\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\4\10\1\44\15\10"+
    "\5\0\1\10\2\0\1\10\3\0\1\10\2\0\3\10"+
    "\1\45\16\10\5\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\10\10\1\46\11\10\5\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\16\10\1\47\3\10\41\0\1\50"+
    "\41\0\1\51\4\0\1\52\37\0\1\30\1\52\1\0"+
    "\1\33\7\0\1\33\25\0\1\53\1\52\2\0\1\53"+
    "\31\0\3\27\1\54\1\55\3\27\1\0\30\27\3\0"+
    "\1\53\3\0\1\53\45\0\1\56\27\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\2\10\1\57\17\10\5\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\5\10\1\60"+
    "\14\10\5\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\13\10\1\61\6\10\5\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\4\10\1\62\15\10\5\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\11\10\1\63\10\10\5\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\4\10\1\64"+
    "\7\10\1\65\5\10\5\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\3\10\1\66\16\10\5\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\11\10\1\67\10\10\5\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\20\10\1\70"+
    "\1\10\5\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\11\10\1\71\10\10\5\0\1\53\40\0\1\54\1\52"+
    "\36\0\5\56\1\0\1\56\1\0\1\56\1\0\1\72"+
    "\22\56\5\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\17\10\1\73\2\10\5\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\1\10\1\74\20\10\5\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\5\10\1\75\14\10\5\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\12\10\1\76"+
    "\7\10\5\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\5\10\1\77\14\10\5\0\1\10\2\0\1\10\3\0"+
    "\1\10\2\0\5\10\1\100\14\10\5\0\1\10\2\0"+
    "\1\10\3\0\1\10\2\0\6\10\1\101\13\10\5\0"+
    "\1\10\2\0\1\10\3\0\1\10\2\0\3\10\1\102"+
    "\16\10\5\0\1\10\2\0\1\10\3\0\1\10\2\0"+
    "\1\103\21\10\16\0\1\104\27\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\12\10\1\105\7\10\5\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\3\10\1\106\16\10"+
    "\5\0\1\10\2\0\1\10\3\0\1\10\2\0\1\10"+
    "\1\107\20\10\5\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\4\10\1\110\15\10\5\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\15\10\1\111\4\10\5\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\4\10\1\112\15\10"+
    "\16\0\1\113\27\0\1\10\2\0\1\10\3\0\1\10"+
    "\2\0\1\10\1\114\20\10\5\0\1\10\2\0\1\10"+
    "\3\0\1\10\2\0\6\10\1\115\13\10\5\0\1\10"+
    "\2\0\1\10\3\0\1\10\2\0\4\10\1\116\15\10"+
    "\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1947];
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
    "\1\1\2\11\23\1\1\0\2\1\1\0\1\1\1\0"+
    "\13\1\3\11\1\1\1\0\1\1\1\0\13\1\1\0"+
    "\11\1\1\0\6\1\1\11\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[78];
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
    while (i < 114) {
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
        case 19: 
          { this.yylval = new String(yytext());
         			    return Parser.REAL_TYPE;
          }
        case 22: break;
        case 3: 
          { return yytext().charAt(0);
          }
        case 23: break;
        case 6: 
          { this.yylval = new String(yytext());
						return Parser.ID;
          }
        case 24: break;
        case 8: 
          { this.yylval = new String(yytext());
         			    return Parser.AND;
          }
        case 25: break;
        case 4: 
          { this.yylval = new Integer(yytext());
         			    return Parser.INT_CONSTANT;
          }
        case 26: break;
        case 13: 
          { this.yylval = new String(yytext());
         			    return Parser.ELSE;
          }
        case 27: break;
        case 7: 
          { this.yylval = new String(yytext());
         			    return Parser.IF;
          }
        case 28: break;
        case 16: 
          { this.yylval = new String(yytext());
         			    return Parser.WHILE;
          }
        case 29: break;
        case 15: 
          { this.yylval = new String(yytext());
         			    return Parser.VOID;
          }
        case 30: break;
        case 2: 
          { System.err.println ("Lexical error at line " 
		+ this.getLine() + " and column "+getColumn()+":\n\tUnknow character \'"+ yycharat(0)+"\'.");
          }
        case 31: break;
        case 17: 
          { this.yylval = new String(yytext());
         			    return Parser.INPUT;
          }
        case 32: break;
        case 14: 
          { this.yylval = new String(yytext());
         			    return Parser.CHAR_TYPE;
          }
        case 33: break;
        case 5: 
          { this.yylval = new String(yytext());
						return Parser.REAL_CONSTANT;
          }
        case 34: break;
        case 21: 
          { this.yylval = new String(yytext());
         			    return Parser.STRUCT;
          }
        case 35: break;
        case 9: 
          { this.yylval = new String(yytext());
         			    return Parser.OR;
          }
        case 36: break;
        case 11: 
          { this.yylval = new String(yytext());
         			    return Parser.def;
          }
        case 37: break;
        case 20: 
          { this.yylval = new String(yytext());
         			    return Parser.RETURN;
          }
        case 38: break;
        case 18: 
          { this.yylval = new String(yytext());
         			    return Parser.PRINT;
          }
        case 39: break;
        case 12: 
          { this.yylval = new String(yytext());
         			    return Parser.INT;
          }
        case 40: break;
        case 10: 
          { this.yylval = new String(yytext());
         			    return Parser.CHAR_CONSTANT;
          }
        case 41: break;
        case 1: 
          { 
          }
        case 42: break;
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
