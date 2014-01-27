import java.util.ArrayList;
import java.util.HashSet;



public class BoggleSolver
{
	
	private Dictionary<Integer> st;
	private HashSet<String> validWords;
	private Bag<Integer>[] preCompAdj;
	private String prev2char[];
    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionary) {
    	st = new Dictionary<Integer>();
       	for (int i = 0; i < dictionary.length; i++) {
    		st.put(dictionary[i], i);
    	}       	
       	validWords = new HashSet<String>();
    	
    }


    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
    	//!!!!!!! must clear the validWords for new board.
    	validWords.clear();
    	Digraph G = boardGraph(board);
    	for (int i = 0; i < board.rows(); i++) {
    		for (int j = 0; j < board.cols(); j++) {
    			int v = i * board.cols() + j;
    			//StdOut.printf("v %d, i %d, j %d, rows %d, cols %d \n", v, i, j , board.rows(), board.cols());
    			dfs(board, G, v);
    		}
    	}
    	
    	return validWords;
    }
    
   
    //recurrent implement for dfs
//    private void dfs(BoggleBoard board, Digraph G, int v) {
//    	String prefix = "";
//    	prefix += prev2char[v];
//    	HashSet<Integer> hashset = new HashSet<Integer>();
//    	hashset.add(Integer.valueOf(v));    	
//    	dfs(board, G, v, hashset, prefix);    	
//    }
//    
//    private void dfs(BoggleBoard board, Digraph G, int v, HashSet<Integer> hashset, String prefix) {
//		if (prefix.length() > 2) {
//    		if (st.contains(prefix))
//    			validWords.add(prefix);	
//    	}    	
//    	for (int w: preCompAdj[v]) {
//    		if(!hashset.contains(Integer.valueOf(w))) {
//        		String newPrefix = prefix + prev2char[w];
//        		if (st.iskeysWithPrefix(newPrefix)) {
//            		HashSet<Integer> newHashset = new HashSet<Integer>(hashset);
//            		newHashset.add(Integer.valueOf(w));
//            		dfs(board, G, w, newHashset, newPrefix);  
//        		}  			
//    		}
//    	}
//    } 
    

    //norecursive implement for dfs to improve the speed
//    private void dfs(BoggleBoard board, Digraph G, int v) {
//    	
//    	ArrayList<Integer> path = new ArrayList<Integer>();
//    	path.add(Integer.valueOf(v));
//    	
//    	Queue<ArrayList<Integer>> pathQueue = new Queue<ArrayList<Integer>>();
//    	pathQueue.enqueue(path);
//    	//maintain is just for speed try
//    	Queue<String> prefixQueue = new Queue<String>();
//    	prefixQueue.enqueue(prev2char[v]);
//    	while (!pathQueue.isEmpty()) {
//    		ArrayList<Integer> curpath = pathQueue.dequeue();
////    		String curPrefix = "";
////    		for (int i : curpath) {
////    			curPrefix += prev2char[i];
////    		}
//    		String curPrefix = prefixQueue.dequeue();
//    		int curV = curpath.get(curpath.size() - 1);
//    		if (curPrefix.length() > 2) {
//        		if (st.contains(curPrefix))
//        			validWords.add(curPrefix);
//        	}
//    		
////    		boolean[] isPrefixExit = st.iskeysWithPrefixSon(curPrefix);
//    		
//    		for (int w: preCompAdj[curV]) {
//    			if(!curpath.contains(Integer.valueOf(w))) {
//    				char n = prev2char[w].charAt(0);
//            		String newPrefix = curPrefix + prev2char[w];
//            		
//            		if (st.iskeysWithPrefix(newPrefix)) {
////            		if (isPrefixExit[n - 'A']) {
//                		ArrayList<Integer> newPath = new ArrayList<Integer>(curpath);
//                		newPath.add(Integer.valueOf(w));
//                		pathQueue.enqueue(newPath);
//                		prefixQueue.enqueue(newPrefix);
//            		}  			
//        		}
//        	} 		
//    	}
//
//    }
    
    private void dfs(BoggleBoard board, Digraph G, int v) {
    	
    	ArrayList<Integer> path = new ArrayList<Integer>();
    	path.add(Integer.valueOf(v));
    	
    	Queue<ArrayList<Integer>> pathQueue = new Queue<ArrayList<Integer>>();
    	pathQueue.enqueue(path);
    	//maintain is just for speed try
    	Queue<String> prefixQueue = new Queue<String>();
    	prefixQueue.enqueue(prev2char[v]);
    	while (!pathQueue.isEmpty()) {
    		ArrayList<Integer> curpath = pathQueue.dequeue();
//    		String curPrefix = "";
//    		for (int i : curpath) {
//    			curPrefix += prev2char[i];
//    		}
    		String curPrefix = prefixQueue.dequeue();
    		int curV = curpath.get(curpath.size() - 1);
//    		if (curPrefix.length() > 2) {
//        		if (st.contains(curPrefix))
//        			validWords.add(curPrefix);
//        	}
    		
//    		boolean[] isPrefixExit = st.iskeysWithPrefixSon(curPrefix);
    		
    		for (int w: preCompAdj[curV]) {
    			if(!curpath.contains(Integer.valueOf(w))) {
    				char n = prev2char[w].charAt(0);
            		String newPrefix = curPrefix + prev2char[w];
            		// check if newPrefix is a word. 
            		//then next loop don't check it, just check its children
            		if (newPrefix.length() > 2) {
                		if (st.contains(newPrefix))
                			validWords.add(newPrefix);
                	}
            		if (st.iskeysWithPrefix(newPrefix)) {
//            		if (isPrefixExit[n - 'A']) {
                		ArrayList<Integer> newPath = new ArrayList<Integer>(curpath);
                		newPath.add(Integer.valueOf(w));
                		pathQueue.enqueue(newPath);
                		prefixQueue.enqueue(newPrefix);
            		}  			
        		}
        	} 		
    	}

    }
      
// check if exit prefix return array of son node is slower than only check one.
//    private void dfs(BoggleBoard board, Digraph G, int v) {
//    	
//    	ArrayList<Integer> path = new ArrayList<Integer>();
//    	path.add(Integer.valueOf(v));
//    	
//    	Queue<ArrayList<Integer>> pathQueue = new Queue<ArrayList<Integer>>();
//    	pathQueue.enqueue(path);
//    	//maintain is just for speed try
//    	Queue<String> prefixQueue = new Queue<String>();
//    	prefixQueue.enqueue(prev2char[v]);
//    	while (!pathQueue.isEmpty()) {
//    		ArrayList<Integer> curpath = pathQueue.dequeue();
//    		String curPrefix = prefixQueue.dequeue();
//    		int curV = curpath.get(curpath.size() - 1);
//    		boolean[] isPrefixExit = st.iskeysWithPrefixSon(curPrefix);    		
//    		for (int w: preCompAdj[curV]) {        		
//    			if(!curpath.contains(Integer.valueOf(w))) {
//        			char n = prev2char[w].charAt(0);
//            		String newPrefix = curPrefix + prev2char[w];
//            		if (newPrefix.length() > 2) {
//                		if (st.contains(newPrefix))
//                			validWords.add(newPrefix);
//                	}
//            		if (isPrefixExit[n - 'A']) {
//                		ArrayList<Integer> newPath = new ArrayList<Integer>(curpath);
//                		newPath.add(Integer.valueOf(w));
//                		pathQueue.enqueue(newPath);
//                		prefixQueue.enqueue(newPrefix);
//            		}  			
//        		}
//        	} 		
//    	}
//
//    }
    
    
    
    
    private Digraph boardGraph(BoggleBoard board) {
    	int v = board.cols() * board.rows();
    	Digraph G = new Digraph(v);
    	//for (int i = 0; i < 1; i++) {
    	for (int i = 0; i < board.rows(); i++) {
    		for (int j = 0; j < board.cols(); j++) {
    			if (i > 0)
    				G.addEdge(i * board.cols() + j, board.cols() * (i-1)  + j);

    			if (i > 0 && j > 0)
    				G.addEdge(i * board.cols() + j, board.cols() * (i-1) + j-1);
    	
    			if (i > 0 && j < board.cols() - 1)
    				G.addEdge(i * board.cols() + j, board.cols() * (i-1) + j+1);
    			
    			if (i < board.rows() - 1 )
    				G.addEdge(i * board.cols() + j, board.cols() * (i+1) + j);

    			if (i < board.rows() -1 && j > 0)
    				G.addEdge(i * board.cols() + j, board.cols() * (i+1) + (j-1));
    	
    			if (i < board.rows() -1 && j < board.cols() - 1)
    				G.addEdge(i * board.cols() + j, board.cols() * (i+1) + j+1);
    		
    			if (j > 0)
    				G.addEdge(i * board.cols() + j, board.cols() * i + j - 1);
    		
    			if (j < board.cols() - 1)
    				G.addEdge(i * board.cols() + j, board.cols() * i + j + 1);
    
    		}    		
    	}    	
    	//StdOut.println(G);
    	
    	//precompute the adj of G. just for speed
    	preCompAdj = (Bag<Integer>[]) new Bag[G.V()];
    	prev2char = new String[G.V()];
    	for (int i = 0; i < G.V(); i++) {
    		preCompAdj[i] = new Bag<Integer>();
    		for (int w : G.adj(i))
    			preCompAdj[i].add(Integer.valueOf(w));
    		
    		
        	int m = i / board.cols() ;
        	int n = i % board.cols();
        	String str = "";
        	//StdOut.printf("v %d, i %d, j %d, rows %d, cols %d \n", v, i, j , board.rows(), board.cols());
        	char c = board.getLetter(m, n);
        	str += c;
        	if (c == 'Q' || c == 'q')
        		str += 'U';
        	prev2char[i] = str;
        	//StdOut.println(prev2char[i]);

        }
    	
    	return G;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
    	if (!st.contains(word))
    		return 0;
    	int len = word.length();
    	if (len >= 0 && len <= 2)
    		return 0;
    	else if (len >=3 && len <= 4)
    		return 1;
    	else if (len == 5)
    		return 2;
    	else if (len == 6)
    		return 3;
    	else if (len == 7)
    		return 5;
    	else if (len >= 6)
    		return 11;
    	else
    		return 0;
    }
    
    public static void main(String[] args)
    {
        In in = new In(args[0]);
        String[] dictionary = in.readAllStrings();
        BoggleSolver solver = new BoggleSolver(dictionary);
        BoggleBoard board = new BoggleBoard(args[1]);
        int score = 0;
        for (String word : solver.getAllValidWords(board))
        {
            StdOut.println(word);
            score += solver.scoreOf(word);
        }
        StdOut.println("Score = " + score);
        
        
//        BoggleBoard board_1 = new BoggleBoard(args[2]);
//        int score_1 = 0;
//        for (String word : solver.getAllValidWords(board_1))
//        {
//            StdOut.println(word);
//            score_1 += solver.scoreOf(word);
//        }
//        StdOut.println("Score = " + score_1);
//        
        
    }

}