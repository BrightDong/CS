import java.util.Arrays;

public class CircularSuffixArray {
	private String text;
	private Suffix[] suffixes;
    public CircularSuffixArray(String text) { // circular suffix array of s
    	int N = text.length();
    	this.text = new String(text);
    	this.suffixes = new Suffix[N];
    	for (int i = 0; i < N; i++) {
    		suffixes[i] = new Suffix(text, i);
    	}
		Arrays.sort(suffixes);
    }
    
    private static class Suffix implements Comparable<Suffix> {
    	private final String text;
    	private final int index;
    	
    	private Suffix(String text, int index) {
    		this.text = text;
    		this.index = index;
    	}
    	
    	//TODO: if need this as it is fix length for circularSuffixArray.
    	private int length() {
    		return text.length();
    	}
    	
    	private char charAt(int i) {
    		return text.charAt((index + i) % length());
    	}
    	
    	public int compareTo(Suffix that) {
    		if (this == that) return 0;
    		
    		for (int i = 0; i < length(); i++) {
    			if (this.charAt(i) < that.charAt(i)) return -1;
    			if (this.charAt(i) > that.charAt(i)) return +1;
    		}
    		return 0;
    	}
    	
    	public String toString() {
    		return text.substring(index) + text.substring(0, index);
    	}
    }
    
    public int length() {                  // length of s
    	return suffixes.length;
    }
    public int index(int i) {              // returns index of ith sorted suffix

    	if (i < 0 || i >= suffixes.length) throw new java.lang.IndexOutOfBoundsException();
    	return suffixes[i].index;
    }
    
    public static void main(String[] args) {
    	In in = new In(args[0]);
    	String text = in.readLine();
    	
    	for(int i = 0; i < text.length(); i++) {
    		StdOut.print(text.charAt(i));
    	}	
    	StdOut.println();
    	
    	CircularSuffixArray CSArray = new CircularSuffixArray(text);
    	for(int i = 0; i < text.length(); i++) {
    		StdOut.print(text.charAt(CSArray.index(i)));
    	}
    	StdOut.println();
    }
    
}

