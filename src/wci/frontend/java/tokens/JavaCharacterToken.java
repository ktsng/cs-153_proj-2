package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

/**
 * <h1>JavaCharacterToken</h1>
 *
 * <p> Java character tokens.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class JavaCharacterToken extends JavaToken {
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
	public JavaCharacterToken(Source source) throws Exception {
		super(source);
		// TODO Auto-generated constructor stub
	}

    /**
     * Extract a Java string token from the source.
     * @throws Exception if an error occurred.
     */
    protected void extract()
        throws Exception
    {
        StringBuilder textBuffer = new StringBuilder();
        StringBuilder valueBuffer = new StringBuilder();
        textBuffer.append('\'');

        char currentChar = nextChar();  // consume initial quote
        
        if (currentChar == '\\') {
        	currentChar = nextChar();  // consume character
        	
        	if (currentChar == 't') {
        		valueBuffer.append('\t');
        		textBuffer.append("\\t");
        	} else if (currentChar == 'n') {
        		valueBuffer.append('\n');
        		textBuffer.append("\\n");
        	} else if (currentChar == '\'') {
        		valueBuffer.append('\'');
        		textBuffer.append("\\\'");
        	} else if (currentChar == '\"') {
        		valueBuffer.append('\"');
        		textBuffer.append("\\\"");
        	} else if (currentChar == '\\') {
        		valueBuffer.append('\\');
        		textBuffer.append("\\\\");
        	} else {
                type = ERROR;
                value = INVALID_CHARACTER;
        	}
        } else {
        	textBuffer.append(currentChar);
        	valueBuffer.append(currentChar);
        }

        // System.out.println(text);
        textBuffer.append('\'');
        text = textBuffer.toString();

        currentChar = nextChar();

        if (currentChar != '\'') {
            type = ERROR;
            value = INVALID_CHARACTER;
        } else {
            type = CHARACTER;
            value = valueBuffer;
            nextChar();
        }
    }
}