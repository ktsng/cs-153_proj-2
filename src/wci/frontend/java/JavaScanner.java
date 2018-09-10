package wci.frontend.java;

import wci.frontend.*;
import wci.frontend.java.tokens.*;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

/**
 * <h1>JavaScanner</h1>
 *
 * <p>
 * The Java scanner.
 * </p>
 *
 * <p>
 * Copyright (c) 2009 by Ronald Mak
 * </p>
 * <p>
 * For instructional purposes only. No warranties.
 * </p>
 */
public class JavaScanner extends Scanner {
	/**
	 * Constructor
	 * 
	 * @param source the source to be used with this scanner.
	 */
	public JavaScanner(Source source) {
		super(source);
	}

	/**
	 * Extract and return the next Pascal token from the source.
	 * 
	 * @return the next token.
	 * @throws Exception if an error occurred.
	 */
	protected Token extractToken() throws Exception {
		skipWhiteSpace();

		Token token;
		char currentChar = currentChar();

		// Construct the next token. The current character determines the
		// token type.
		if (currentChar == EOF) {
			token = new EofToken(source);
		} else if (Character.isLetter(currentChar)) {
			token = new JavaWordToken(source);
		} else if (Character.isDigit(currentChar)) {
			token = new JavaNumberToken(source);
		} else if (currentChar == '\"') {
			token = new JavaStringToken(source);
		} else if (currentChar == '\'') {
        	token = new JavaCharacterToken(source);
		} else if (JavaTokenType.SPECIAL_SYMBOLS.containsKey(Character.toString(currentChar))) {
			token = new JavaSpecialSymbolToken(source);
		} else {
			token = new JavaErrorToken(source, INVALID_CHARACTER, Character.toString(currentChar));
			nextChar(); // consume character
		}

		return token;
	}

	/**
	 * Skip whitespace characters by consuming them. A comment is whitespace.
	 * 
	 * @throws Exception if an error occurred.
	 */
	private void skipWhiteSpace() throws Exception {
		
		while (Character.isWhitespace(currentChar()) || (currentChar() == '/' && peekChar() == '/')
				|| (currentChar() == '/' && peekChar() == '*')) {

			if (currentChar() == '/' && peekChar() == '/') {
				while (currentChar() != EOL) {
					nextChar();
				}
				
			} else if (currentChar() == '/' && peekChar() == '*') {
				
				while (currentChar() != EOF) {
					nextChar();
					if (currentChar() == '*' && peekChar() == '/') {
						nextChar();
						nextChar();
						break;
					}
				}
			} else {
				nextChar();
			}
		}

	}
}
