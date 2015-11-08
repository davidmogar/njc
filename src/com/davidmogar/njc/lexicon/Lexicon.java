/* The following code was generated by JFlex 1.4.1 on 11/8/15 4:23 PM */

package com.davidmogar.njc.lexicon;

import com.davidmogar.njc.syntactic.Parser;
import com.davidmogar.njc.TypeError;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 11/8/15 4:23 PM from the specification file
 * <tt>../src/com/davidmogar/njc/lexicon/lexicon.flex</tt>
 */
public class Lexicon {

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
    "\11\0\1\24\1\2\1\0\1\24\1\4\22\0\1\24\1\15\1\16"+
    "\1\0\1\21\1\11\1\51\1\17\1\11\1\11\1\3\1\7\1\11"+
    "\1\10\1\22\1\1\12\5\1\0\1\11\1\12\1\14\1\13\2\0"+
    "\4\6\1\23\15\6\1\43\7\6\1\11\1\20\1\11\1\0\1\21"+
    "\1\0\1\27\1\34\1\25\1\31\1\36\1\46\1\44\1\26\1\37"+
    "\2\6\1\35\1\50\1\40\1\32\2\6\1\30\1\45\1\41\1\33"+
    "\1\42\1\47\3\6\1\11\1\52\1\11\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\2\1\3\1\4\1\5"+
    "\6\2\3\1\1\3\11\5\2\1\1\3\1\0\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\0\1\15"+
    "\2\0\1\6\5\5\1\16\5\5\1\17\1\20\2\0"+
    "\1\21\2\0\5\5\1\22\5\5\1\6\2\0\1\23"+
    "\1\24\1\25\2\5\1\26\1\27\3\5\1\30\1\0"+
    "\3\5\1\31\1\32\1\33\1\34\1\35\1\36";

  private static int [] zzUnpackAction() {
    int [] result = new int[96];
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
    "\0\0\0\53\0\126\0\201\0\53\0\254\0\327\0\u0102"+
    "\0\u012d\0\u0158\0\u0183\0\u01ae\0\u01d9\0\u0204\0\u022f\0\u025a"+
    "\0\u0285\0\53\0\u02b0\0\u02db\0\u0306\0\u0331\0\u035c\0\u0387"+
    "\0\u03b2\0\u03dd\0\u0408\0\u0433\0\u045e\0\u0489\0\u04b4\0\u04df"+
    "\0\53\0\53\0\53\0\53\0\53\0\53\0\u022f\0\53"+
    "\0\u050a\0\u0535\0\u0560\0\u058b\0\u05b6\0\u05e1\0\u060c\0\u0637"+
    "\0\u0102\0\u0662\0\u068d\0\u06b8\0\u06e3\0\u070e\0\53\0\53"+
    "\0\u0739\0\u0764\0\53\0\u078f\0\u07ba\0\u07e5\0\u0810\0\u083b"+
    "\0\u0866\0\u0891\0\u0102\0\u08bc\0\u08e7\0\u0912\0\u093d\0\u0968"+
    "\0\u0993\0\u0993\0\u09be\0\53\0\u0102\0\u0102\0\u09e9\0\u0a14"+
    "\0\u0102\0\u0102\0\u0a3f\0\u0a6a\0\u0a95\0\u0102\0\u0ac0\0\u0aeb"+
    "\0\u0b16\0\u0b41\0\u0102\0\u0102\0\53\0\u0102\0\u0102\0\u0102";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[96];
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
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\5\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\2\2\1\21\1\10\1\22\1\23\2\10\1\24\1\25"+
    "\4\10\1\26\1\27\2\10\1\30\1\31\3\10\1\32"+
    "\1\33\1\34\1\35\54\0\1\36\1\0\1\37\53\0"+
    "\1\22\50\0\1\22\55\0\1\7\14\0\1\40\35\0"+
    "\2\10\12\0\1\10\1\0\1\10\1\0\24\10\11\0"+
    "\1\41\53\0\1\42\56\0\1\43\52\0\1\44\52\0"+
    "\1\45\52\0\1\46\36\0\16\47\1\50\34\47\2\51"+
    "\1\0\15\51\1\52\32\51\5\0\1\53\52\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\1\10\1\54\22\10"+
    "\7\0\2\10\12\0\1\10\1\0\1\10\1\0\11\10"+
    "\1\55\12\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\5\10\1\56\16\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\10\10\1\57\13\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\13\10\1\60\5\10"+
    "\1\61\2\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\5\10\1\62\16\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\14\10\1\63\7\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\1\10\1\64\1\10"+
    "\1\65\20\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\2\10\1\66\21\10\53\0\1\67\53\0\1\70"+
    "\2\36\1\0\50\36\3\37\1\71\47\37\5\0\1\40"+
    "\15\0\1\72\12\0\1\72\33\0\1\73\40\0\1\74"+
    "\11\0\1\73\20\0\2\75\34\0\1\72\12\0\1\72"+
    "\21\0\2\10\12\0\1\10\1\0\1\10\1\0\2\10"+
    "\1\76\21\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\2\10\1\77\11\10\1\100\7\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\6\10\1\101\15\10"+
    "\7\0\2\10\12\0\1\10\1\0\1\10\1\0\20\10"+
    "\1\102\3\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\14\10\1\103\7\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\12\10\1\104\11\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\3\10\1\105\20\10"+
    "\7\0\2\10\12\0\1\10\1\0\1\10\1\0\12\10"+
    "\1\106\11\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\12\10\1\107\11\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\12\10\1\110\11\10\2\0\1\37"+
    "\1\22\1\37\1\71\47\37\5\0\1\111\1\0\2\112"+
    "\47\0\1\113\64\0\1\114\40\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\3\10\1\115\20\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\4\10\1\116\17\10"+
    "\7\0\2\10\12\0\1\10\1\0\1\10\1\0\6\10"+
    "\1\117\15\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\7\10\1\120\14\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\11\10\1\121\12\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\4\10\1\122\17\10"+
    "\7\0\2\10\12\0\1\10\1\0\1\10\1\0\12\10"+
    "\1\123\11\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\10\10\1\124\13\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\14\10\1\125\7\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\13\10\1\126\10\10"+
    "\7\0\1\111\52\0\1\127\52\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\3\10\1\130\20\10\7\0\2\10"+
    "\12\0\1\10\1\0\1\10\1\0\10\10\1\131\13\10"+
    "\7\0\2\10\12\0\1\10\1\0\1\10\1\0\13\10"+
    "\1\132\10\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\11\10\1\133\12\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\11\10\1\134\12\10\21\0\1\135"+
    "\40\0\2\10\12\0\1\10\1\0\1\10\1\0\13\10"+
    "\1\136\10\10\7\0\2\10\12\0\1\10\1\0\1\10"+
    "\1\0\11\10\1\137\12\10\7\0\2\10\12\0\1\10"+
    "\1\0\1\10\1\0\17\10\1\140\4\10\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[2924];
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
    "\1\0\1\11\2\1\1\11\14\1\1\11\14\1\1\0"+
    "\1\1\6\11\1\0\1\11\2\0\14\1\2\11\2\0"+
    "\1\11\2\0\14\1\2\0\1\11\12\1\1\0\5\1"+
    "\1\11\3\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[96];
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

public Object matchedValue; // Semantic value of the token returned.

/**
 * Return token's column.
 */
public int getColumn() {
	return yycolumn + 1;
}

/**
 * Return token's line.
 */
public int getLine() {
	return yyline + 1;
}



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexicon(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexicon(java.io.InputStream in) {
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
    while (i < 134) {
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
        case 22: 
          { matchedValue = yytext(); return Parser.ELSE;
          }
        case 31: break;
        case 7: 
          { matchedValue = yytext(); return Parser.INCREMENT;
          }
        case 32: break;
        case 19: 
          { matchedValue = '\n'; return Parser.CHARACTER_LITERAL;
          }
        case 33: break;
        case 29: 
          { matchedValue = yytext(); return Parser.DOUBLE;
          }
        case 34: break;
        case 23: 
          { matchedValue = yytext(); return Parser.VOID;
          }
        case 35: break;
        case 20: 
          { matchedValue = yytext(); return Parser.CHARACTER;
          }
        case 36: break;
        case 15: 
          { matchedValue = yytext(); return Parser.AND;
          }
        case 37: break;
        case 13: 
          { matchedValue = yytext().replaceAll("\"", "").replaceAll("\\\\n", "\n").replaceAll("\\\\t", "\t"); return Parser.STRING_LITERAL;
          }
        case 38: break;
        case 8: 
          { matchedValue = yytext(); return Parser.DECREMENT;
          }
        case 39: break;
        case 16: 
          { matchedValue = yytext(); return Parser.OR;
          }
        case 40: break;
        case 17: 
          { matchedValue = yytext().charAt(1); return Parser.CHARACTER_LITERAL;
          }
        case 41: break;
        case 26: 
          { matchedValue = yytext(); return Parser.WRITE;
          }
        case 42: break;
        case 6: 
          { matchedValue = new Double(yytext()); return Parser.DOUBLE_LITERAL;
          }
        case 43: break;
        case 28: 
          { matchedValue = yytext(); return Parser.RETURN;
          }
        case 44: break;
        case 10: 
          { matchedValue = yytext(); return Parser.GREATER_EQUALS;
          }
        case 45: break;
        case 21: 
          { matchedValue = yytext(); return Parser.READ;
          }
        case 46: break;
        case 30: 
          { matchedValue = yytext(); return Parser.STRING;
          }
        case 47: break;
        case 9: 
          { matchedValue = yytext(); return Parser.LOWER_EQUALS;
          }
        case 48: break;
        case 14: 
          { matchedValue = yytext(); return Parser.IF;
          }
        case 49: break;
        case 25: 
          { matchedValue = yytext(); return Parser.WHILE;
          }
        case 50: break;
        case 12: 
          { matchedValue = yytext(); return Parser.NOT_EQUALS;
          }
        case 51: break;
        case 2: 
          { matchedValue = yycharat(0); return (int) yycharat(0);
          }
        case 52: break;
        case 24: 
          { matchedValue = yytext(); return Parser.MAIN;
          }
        case 53: break;
        case 18: 
          { matchedValue = yytext(); return Parser.INTEGER;
          }
        case 54: break;
        case 4: 
          { matchedValue = new Integer(yytext()); return Parser.INTEGER_LITERAL;
          }
        case 55: break;
        case 27: 
          { matchedValue = (char) Integer.parseInt(yytext().replaceAll("[\\\\\']", ""));
						return Parser.CHARACTER_LITERAL;
          }
        case 56: break;
        case 11: 
          { matchedValue = yytext(); return Parser.EQUALS;
          }
        case 57: break;
        case 1: 
          { new TypeError(this.getLine(), this.getColumn(),"Character \'" + yycharat(0) + "' unknown.");
          }
        case 58: break;
        case 5: 
          { matchedValue = yytext(); return Parser.IDENTIFIER;
          }
        case 59: break;
        case 3: 
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
