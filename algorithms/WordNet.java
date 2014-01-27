import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class WordNet{
	
	//shall I use both ArrayList and HashSet to store the synsets?
	//That ArrayList is used to find the id,
	//HastSet is used to Iterable and is_nouns?
	private ArrayList<String> list_synsets;
	private HashSet<String> set_synsets;
	private Hashtable<String, String> hashtable_synsets;
	private Digraph G;
	
	private SAP sap;
	
	// constructor takes the name of the two input files
	//TODO: add below exception handling
	//if the input does not correspond to a rooted DAG
	// constructor takes a digraph (not necessarily a DAG)
	public WordNet(String synsets, String hypernyms) {
		//Read in the sysnets.txt file and build appropriate data structures.
		//You shouldn't need to design any data structures here, but choosing how to
		//represent the data for efficient access is important.
		//Think about what operations you need to support.

		//select a data structure to store synsets and hypernyms
		//TODO: Write JUnit test cases later.
		//ArrayList used to store synsets
		
		//the second field is the synonym set (or synset), 
		//So one and more in second field!!!!
		list_synsets = new ArrayList<String>();
		set_synsets = new HashSet<String>();
		// due synonym set,     key is noun: apple, value is index set: "123,223,930"
		hashtable_synsets = new Hashtable();
		In in = new In(synsets);
		while(in.hasNextLine()) {
			String line = in.readLine();
			String str[] = line.split(",");
			list_synsets.add(str[1]);
			set_synsets.add(str[1]);
			
			//StdOut.println(str[1]);
			String[] strSynset = str[1].split(" ");
			//StdOut.println(strSynset[0]);
			for(int i = 0; i < strSynset.length ; i++) {
				//StdOut.printf("here",strSynset[i], "\n");
				// if already has this key, add the index to the end of the value set
				if(hashtable_synsets.containsKey(strSynset[i])) {
					String value = hashtable_synsets.get(strSynset[i]) + "," + str[0];
					hashtable_synsets.put(strSynset[i], value);
				}else{
					hashtable_synsets.put(strSynset[i], str[0]);
				}
			}
			
		}		
		//Read in the hypernyns.txt file and build a Digraph.
		G = new Digraph(list_synsets.size());
		
		In in_hypernyns = new In(hypernyms);
		while(in_hypernyns.hasNextLine()) {
			String line = in_hypernyns.readLine();
			String str[] = line.split(",");
			//!!!here is special, i < str.length -1,
			//because edge is the first one with others
			for(int i = 0; i < str.length - 1; i++) {
				G.addEdge(Integer.parseInt(str[0]), Integer.parseInt(str[i + 1]));
			}
		}
		
		//check if cycle
		DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) throw new java.lang.IllegalArgumentException();
		
//        //check if two root  //too slow!!!!       
//        HashSet<Integer> root = new HashSet<Integer>();
//        for (int i = 0; i < G.V(); i++)
//        	root.add(Integer.valueOf(i));
//        
//        for(int i = 0; i < G.V(); i++) {
//        	ArrayList<Integer> toRemove = new ArrayList<Integer>();
//    		DepthFirstDirectedPaths dfsp_i = new DepthFirstDirectedPaths(G, i);
//        	for (int r : root) {
//        		if (!dfsp_i.hasPathTo(r)) {
//        			toRemove.add(Integer.valueOf(r));
//        		}
//        	}        	
//        	for (int r : toRemove) {
//    			root.remove(Integer.valueOf(r));
//        	}
//        }
//        if (root.isEmpty()) throw new java.lang.IllegalArgumentException();
        
        //check if exit 2 root
        int numRoot = 0;
        for(int v = 0; v < G.V(); v++) {
        	if (!G.adj(v).iterator().hasNext())
        		numRoot += 1;
        }
        if (numRoot != 1) throw new java.lang.IllegalArgumentException();
        
        
		//TODO: Shall the sap be initialed here or later???
		sap = new SAP(G);		
	}

	// the set of nouns (no duplicates), returned as an Iterable
	public Iterable<String> nouns() {
		return hashtable_synsets.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		return hashtable_synsets.containsKey(word);
		//return set_synsets.contains(word);
	}
	
	private HashSet<Integer> nounToSet(String noun) {
		String [] index = ((String)hashtable_synsets.get(noun)).split(",");			
		HashSet<Integer> setInt = new HashSet<Integer>();			
		for(int i = 0; i< index.length; i++) {
			Integer integer = new Integer(index[i]);
			setInt.add(integer);
		}
		return setInt;
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		//find the index of nounA and nounB by look at ArrayList<String> list_synsets.
		//then call sap.length(int v, int w) to calculate the length.
		if(!(isNoun(nounA) && isNoun(nounB))) {
			throw new java.lang.IllegalArgumentException();
		}else{			
			return sap.length(nounToSet(nounA), nounToSet(nounB));
		}
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		int id_synset;
		if(!(isNoun(nounA) && isNoun(nounB))){
			throw new java.lang.IllegalArgumentException();			
		}else{
			id_synset = sap.ancestor(nounToSet(nounA), nounToSet(nounB));
			return list_synsets.get(id_synset);
		}
	}

	// for unit testing of this class
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		
		while(true) {
			StdOut.println("give me a noun");
			String word = StdIn.readLine();
			StdOut.printf("word: %s: is a noun?: ", word);
			StdOut.println(wordnet.isNoun(word));
		
		
			StdOut.println("give me another noun");
			String word2 = StdIn.readLine();
			StdOut.printf("distance between word: %s and word2: %s is %d\n", word, word2, wordnet.distance(word, word2));
		}
	}

}