package wci.frontend.java;

import java.util.Hashtable;
import java.util.HashSet;

import wci.frontend.TokenType;

/**
 * <h1>PascalTokenType</h1>
 *
 * <p>Pascal token types.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public enum JavaTokenType implements TokenType
{
    // Reserved words.    
    ABSTRACT, DOUBLE, INT, SUPER,
    BREAK, ELSE, LONG, SWITCH,
    CASE, ENUM, NATIVE,
    CHAR, EXTENDS, RETURN, THIS,
    CLASS, FLOAT, SHORT, THROW,
    CONST, FOR, PACKAGE, VOID,
    CONTINUE, GOTO, PROTECTED, VOLATILE,
    DO, IF, STATIC, WHILE,

    //Special symbols.
    TILDE("~"), EXCLAMATION_MARK("!"), AT_SIGN("@"), PERCENT("%"), UP_ARROW("^"), 
    AMPERSAND("&"), STAR("*"), MINUS("-"), PLUS("+"), EQUALS("="),
    VERTICAL_BAR("|"), SLASH("/"), COLON(":"), SEMICOLON(";"), QUESTION_MARK("?"),
    LESS_THAN("<"), GREATER_THAN(">"), DOT("."), COMMA(","), QUOTE("'"), 
    QUOTATION_MARKS("\""), LEFT_PAREN("("), RIGHT_PAREN(")"), LEFT_BRACKET("["), 
    RIGHT_BRACKET("]"), LEFT_BRACE("{"), RIGHT_BRACE("}"), PLUS_PLUS("++"),
    MINUS_MINUS("--"), LEFT_SHIFT("<<"), RIGHT_SHIFT(">>"), LESS_EQUALS("<="),
    GREATER_EQUALS(">="), PLUS_EQUALS("+="), MINUS_EQUALS("-="), STAR_EQUALS("*="),
    SLASH_EQUALS("/="), EQUALS_EQUALS("=="), VERTICAL_BAR_EQUALS("|="), PERCENT_EQUALS("%="),
    AMPERSAND_EQUALS("&="), UP_ARROW_EQUALS("^="), NOT_EQUALS("!="), LEFT_SHIFT_EQUALS("<<="),
    RIGHT_SHIFT_EQUALS(">>="), PIPE("||"), AMPERSAND_AMPERSAND("&&"), SLASH_SLASH("//"),
    SLASH_STAR("/*"), STAR_SLASH("*/"),

    CHARACTER, IDENTIFIER, INTEGER, REAL, STRING,
    ERROR, END_OF_FILE;

    private static final int FIRST_RESERVED_INDEX = ABSTRACT.ordinal();
    private static final int LAST_RESERVED_INDEX  = WHILE.ordinal();

    private static final int FIRST_SPECIAL_INDEX = TILDE.ordinal();
    private static final int LAST_SPECIAL_INDEX  = STAR_SLASH.ordinal();

    private String text;  // token text

    /**
     * Constructor.
     */
    JavaTokenType()
    {
        this.text = this.toString().toLowerCase();
    }

    /**
     * Constructor.
     * @param text the token text.
     */
    JavaTokenType(String text)
    {
        this.text = text;
    }

    /**
     * Getter.
     * @return the token text.
     */
    public String getText()
    {
        return text;
    }

    // Set of lower-cased Pascal reserved word text strings.
    public static HashSet<String> RESERVED_WORDS = new HashSet<String>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_RESERVED_INDEX; i <= LAST_RESERVED_INDEX; ++i) {
            RESERVED_WORDS.add(values[i].getText().toLowerCase());
        }
    }

    // Hash table of Pascal special symbols.  Each special symbol's text
    // is the key to its Pascal token type.
    public static Hashtable<String, JavaTokenType> SPECIAL_SYMBOLS =
        new Hashtable<String, JavaTokenType>();
    static {
        JavaTokenType values[] = JavaTokenType.values();
        for (int i = FIRST_SPECIAL_INDEX; i <= LAST_SPECIAL_INDEX; ++i) {
            SPECIAL_SYMBOLS.put(values[i].getText(), values[i]);
        }
    }
}
