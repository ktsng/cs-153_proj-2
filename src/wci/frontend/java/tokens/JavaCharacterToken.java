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
        
        char currentChar = nextChar();  // consume initial quote
        textBuffer.append('\'');
		
		if (currentChar == '\\') {
			textBuffer.append('\\');
            currentChar = nextChar();  // consume character
            
            if (currentChar == '\\' || currentChar == '\'' || currentChar == 'n' || currentChar == 't') {
            	textBuffer.append(currentChar);
            	
            	currentChar = nextChar();
            	
            	if (currentChar == '\'') {
            		nextChar();
            		textBuffer.append('\'');
            		
            		type = CHARACTER;
                    value = textBuffer.toString();
            	} else {
            		type = ERROR;
            		value = UNEXPECTED_EOF;
            	}
            } else {
                type = ERROR;
                value = INVALID_CHARACTER;
            }
		}
	    else {
	      textBuffer.append(currentChar);
	      valueBuffer.append(currentChar);
	      currentChar = nextChar();
	    }

		
		
		
		
		
	    // closing quote
	    if (currentChar == '\'') {
	      nextChar(); // consume final quote
	      textBuffer.append('\'');

	      type = CHARACTER;
	      value = valueBuffer.toString();
	    } else {
	      type = ERROR;
	      value = INVALID_CHARACTER;

	      // loop until found closing '
	      do {
	        currentChar = nextChar();
	        textBuffer.append(currentChar);
	      } while (currentChar != '\'');
	      currentChar = nextChar();
	    }

	text = textBuffer.toString();
    }
}