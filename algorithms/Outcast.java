public class Outcast {// constructor takes a WordNet object
	
	//TODO: how about immutable data type Outcase???
	//TODO: Write JUnit test cases later.
	private WordNet wordnet;
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		int len_largest = 0;
		int index_largest = 0;
		int[] len = new int[nouns.length];
		
		for(int i = 0; i < nouns.length; i++) {
			if (!wordnet.isNoun(nouns[i])){				
				StdOut.println("here is because the word isn't in the synet. So it should be outcast: ");
				StdOut.println(nouns[i]);
				return nouns[i];
			}else {
				for(int j = 0; j < nouns.length; j++) {
					len[i] += wordnet.distance(nouns[i], nouns[j]);
				}
				if(len_largest < len[i]) {
					len_largest = len[i];
					index_largest = i;
				}
			}
		}
		return nouns[index_largest];

	}

	// for unit testing of this class (such as the one below)
	//public static void main(String[] args)
	public static void main(String[] args) {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        String[] nouns = In.readStrings(args[t]);
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
}