import java.util.ArrayList;

public class MoveToFront {
	private static final int NUMASCII = 256;
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
    	ArrayList<Integer> ascii = new ArrayList<Integer>();
    	for (int i = 0; i < NUMASCII; i++) {
    		ascii.add(i, Integer.valueOf(i));
    	}
    	
    	//String text = "ABRACADABRA!";
    	String text = BinaryStdIn.readString();
    	//StdOut.println(text);
    	for (int i = 0; i < text.length(); i++) {
    		char c = text.charAt(i);
    		//StdOut.print((int)c);
    		//StdOut.print(ascii.indexOf(Integer.valueOf(c)));
//    		BinaryStdOut.write(ascii.indexOf(Integer.valueOf(c)));
    		int index = ascii.indexOf(Integer.valueOf(c));
    		BinaryStdOut.write((char)index);
    		BinaryStdOut.flush();
    		if (index != 0) { //not the start index
    			ascii.remove(index);
    			ascii.add(0, Integer.valueOf(c));
    		}
    	}    	
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
    	
    	ArrayList<Integer> ascii = new ArrayList<Integer>();
    	for (int i = 0; i < NUMASCII; i++) {
    		ascii.add(i, Integer.valueOf(i));
    	}
    	
    	//String text = "ABRACADABRA!";
    	String text = BinaryStdIn.readString();
    	//StdOut.println(text);
    	for (int i = 0; i < text.length(); i++) {
    		char c = text.charAt(i);  //it should be index
    		int index = (int)c;
    		//StdOut.print(index);
    		//BinaryStdOut.write((char)((ascii.get(index)).shortValue()));
    		char r = (char)(ascii.get(index).intValue());
    		BinaryStdOut.write(r);
    		BinaryStdOut.flush();
    		//StdOut.print(ascii.get(index));
    		//StdOut.print(ascii.indexOf(Integer.valueOf(c)));
    		//int index = ascii.indexOf(Integer.valueOf(c));
    		if (index != 0) { //not the start index
    			ascii.remove(index);
    			ascii.add(0, Integer.valueOf(r));
    		}
    	}    	
    	
//    	int[] ascii = new int[NUMASCII];
//    	for (int i = 0; i < ascii.length; i++) {
//    		ascii[i] = i;
//    	}
//    	
//    	String text = "ABRACADABRA!";
//    	StdOut.println(text);
//    	for (int i = 0; i < text.length(); i++) {
//    		StdOut.print(ascii[text.charAt(i)]);
//    		if (ascii[text.charAt(i)] != 0) {
//        		for (int j = 0; j < ascii.length; j++) {
//        			ascii[j]++;
//        		}
//        		ascii[text.charAt(i)] = 0;
//    		}
//
//    	}
//    	
    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
    	if (args[0].equals("-")) {
    		//StdOut.println("encode");
    		encode();
    	}

    	if (args[0].equals("+"))
    		decode();    
    }
}