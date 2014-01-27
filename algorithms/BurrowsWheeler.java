public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
    	String text = "";
//    	while (!BinaryStdIn.isEmpty()) {
//            char c = BinaryStdIn.readChar();
//            text += c;
//            
//    	}
    	text = BinaryStdIn.readString();
    	CircularSuffixArray CSArray = new CircularSuffixArray(text);
    	int startIndex = 0;
        for (int i = 0; i < CSArray.length(); i++) {
        	if (CSArray.index(i) == 0) {
        		startIndex = i;
        		break;
        	}
        }    	
    	BinaryStdOut.write(startIndex);
        BinaryStdOut.flush();
        for (int i = 0; i < CSArray.length(); i++) {
        	int index = (CSArray.index(i) + CSArray.length() - 1) % CSArray.length();
        	BinaryStdOut.write(text.charAt(index));
        	BinaryStdOut.flush();
        }
    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {

    	int start = BinaryStdIn.readInt();
    	String text = BinaryStdIn.readString();
    	
    	//Here is for test due eclipse doesn't support redirect stdin or stdout
//    	int start = 3;
//    	String text = "ARD!RCAAAABB";
    	
    	CircularSuffixArray CSArray = new CircularSuffixArray(text);
    	int[] next = new int[CSArray.length()];
    	
//    	char c =text.charAt(CSArray.index(0));
//    	int index = text.indexOf(c);
//    	StdOut.println(index);
    	
    	//int numDumpulate = 0;
    	char preChar = '0';
    	int preIndex = 0;
    	for (int i = 0; i < CSArray.length(); i++) {
    		char c = text.charAt(CSArray.index(i));
    		int index;
    		if (i == 0) {
    			//numDumpulate = 0;
    			index = text.indexOf(c);
    			preChar = c;
    			preIndex = index;
    		} else {
    			if (c == preChar) {
    				//numDumpulate++;
    				index = text.indexOf(c, preIndex + 1);
    				preIndex = index;
    				preChar = c;
    			} else {
    				index = text.indexOf(c);
    				preChar = c;
    				preIndex = index;
    			}
    		}
    		next[i] = index;
    	}
    	
//    	for (int i = 0; i < next.length; i++) {
//    		StdOut.printf("%d ", next[i]);
//    	}
//    	StdOut.println();
    	
    	
//    	StdOut.println(start);
//    	char tmp = text.charAt(CSArray.index(start));

//    	StdOut.println(tmp);
    	
//    	int n = next[start];
//    	StdOut.println(n);
    	
    	int[] indexDecode = new int[next.length];
    	indexDecode[0] = start;
    	//StdOut.print(text.charAt(CSArray.index(start)));
    	BinaryStdOut.write(text.charAt(CSArray.index(start)));
    	for (int i = 1; i < next.length; i++) {
    		indexDecode[i] = next[indexDecode[i - 1]];
    		//StdOut.print(indexDecode[i]);
    		BinaryStdOut.write(text.charAt(CSArray.index(indexDecode[i])));
    		BinaryStdOut.flush();
    	}    	
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
    	if (args[0].equals("-")) {
    		//StdOut.println("encode");
    		encode();
    	}

    	if (args[0].equals("+"))
    		decode();    	
    }
}

