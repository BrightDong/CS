/*
 * File: TurtleTokenizer.java
 * --------------------------
 * This file implements a simple tokenizer for the TurtleGraphics system.
 */

/**
 * This class divides up a command string into individual tokens.
 * A token consists of one of two forms:
 *
 * (1) A letter, optionally followed by any number of decimal digits,
 *     as in "F20", "R120", or "D", or
 * (2) A string beginning with "{" and continuing up to the matching "}".
 *
 * The tokenizer ignores all whitespace characters separating tokens.
 */

public class TurtleTokenizer {

/**
 * Creates a new TurtleTokenizer that takes its input from the string str.
 * @param str The string to be scanned
 */
	public TurtleTokenizer(String str) {
		// Fill this in
		turtleStr = str.toUpperCase();
		index = 0;
	}

/**
 * Returns true if there are more tokens to read and false if the tokenizer
 * has reached the end of the input.
 * @return A boolean value indicating whether there are any unread tokens
 */
	public boolean hasMoreTokens() {
		//return false; // Replace this line with the correct implementation
		if(index == turtleStr.length())
			return false;
		else
			return true;
	}

/**
 * Returns the next complete token.  If this method is called at the end
 * of the input, the tokenizer returns the empty string.
 * @return The next token in the input
 */
	public String nextToken() {
		String s = "";
		//int start = index;
		int brace = 0;
		while(hasMoreTokens()) {
			char ch = turtleStr.charAt(index);
			if(ch == ' ') {  // skip blank char
				index++;
				continue;
			}else if(s == "") {  //accept the first char, if s is null
				// now can only process token begin with F L R X or {
				if(ch == 'F' || ch == 'L' || ch == 'R' || ch == 'X' || ch == '{') {
					s += ch;
					index++;
				}else if(ch == '{') {
					brace++;
					index++;
				}else if(ch == 'D' || ch == 'U') {
					index++;
					s +=ch;
					return s;  // return if U or D
				}else{
					System.out.println("can not handle this char: " + ch);
					return null;
				}
			}else{  // handle the rest char: {} and number, other value will cause return
				switch(s.charAt(0)) {
				case '{':     // if start with '{', so only need find the end '}'
					if(ch == '}') {
						brace--;
						s += ch;
						index++;
						if(brace == 0) {  // find the end of brace, return
							return s;
						}
					}else if(ch == '{') {
						brace++;
						s += ch;
						index++;
					}else {
						s += ch;
						index++;
					}					
					break;
				case 'F': case 'L': case 'R': case 'X':
					if(ch >= '0' && ch <= '9') { // still number
						s += ch;
						index++;
					}else{ // next is not number, return s. not change index or s
						return s;
					}	
					break;		
				default:
					System.out.println("not a correct command: " + ch);
					index++;
					break;
			
				}
			}
		}	
		return s;
	}
	
// Add private methods and instance variables here
	String turtleStr;
	int index;  // index means the index value that not be get.
}
