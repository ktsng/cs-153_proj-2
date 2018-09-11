package wci.frontend.java.tokens;

import wci.frontend.*;
import wci.frontend.java.*;

import static wci.frontend.Source.EOL;
import static wci.frontend.Source.EOF;
import static wci.frontend.java.JavaTokenType.*;
import static wci.frontend.java.JavaErrorCode.*;

/**
 * <h1>JavaStringToken</h1>
 *
 * <p> Java string tokens.</p>
 *
 * <p>Copyright (c) 2009 by Ronald Mak</p>
 * <p>For instructional purposes only.  No warranties.</p>
 */
public class JavaStringToken extends JavaToken
{
    /**
     * Constructor.
     * @param source the source from where to fetch the token's characters.
     * @throws Exception if an error occurred.
     */
    public JavaStringToken(Source source)
        throws Exception
    {
        super(source);
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
        textBuffer.append('\"');

        // Get string characters.
        do {
            // Replace any whitespace character with a blank.
            if (Character.isWhitespace(currentChar)) {
                currentChar = ' ';
            }

            if ((currentChar != '\"') && (currentChar != EOF)) {
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
            	currentChar = nextChar();  // consume character
            }

            // Quote?  Each pair of adjacent quotes represents a single-quote.
            
        } while ((currentChar != '\"') && (currentChar != EOF));

        if (currentChar == '\"') {
            nextChar();  // consume final quote
            textBuffer.append('\"');
            type = STRING;
            value = valueBuffer.toString();
        }
        else {
            type = ERROR;
            value = UNEXPECTED_EOF;
        }

        text = textBuffer.toString();
    }
}