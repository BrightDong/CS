import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

/*************************************************************************
 *  Compilation:  javac TrieST.java
 *  Execution:    java TrieST < words.txt
 *  Dependencies: StdIn.java
 *
 *  A string symbol table for ASCII strings, implemented using a 256-way trie.
 *
 *  % java TrieST < shellsST.txt 
 *  by 4
 *  sea 6
 *  sells 1
 *  she 0
 *  shells 3
 *  shore 7
 *  the 5
 *
 *************************************************************************/

public class Dictionary<Value> {
    private static final int R = 26;        // for A B C...X Y Z
    private Node root = new Node();
    
//    //for speed to maintain a hashset of prefix
//    private Hashtable<String, Node> prefixIndex = new Hashtable<String, Node>();
    
    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
        private boolean hasChild = false;
    }

   /****************************************************
    * Is the key in the symbol table?
    ****************************************************/
    public boolean contains(String key) {
        return get(key) != null;
    }

//    public Value get(String key) {
//        Node x = get(root, key, 0);
//        if (x == null) return null;
//        return (Value) x.val;
//    }
//
//    private Node get(Node x, String key, int d) {
//        if (x == null) return null;
//        if (d == key.length()) return x;
//        char c = key.charAt(d);
//        return get(x.next[c - 'A'], key, d+1);
//    }

//nocurcise implement for get node 
    public Value get(String key) {
    	Node x = root;
    	int len = key.length();
    	for (int i = 0; i < len; i++) {
    		char c = key.charAt(i);
    		x = x.next[c - 'A'];
            if (x == null) return null;
    	}
       // Node x = get(root, key, 0);

        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
    	
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c - 'A'], key, d+1);
    }
    
    
   /****************************************************
    * Insert key-value pair into the symbol table.
    ****************************************************/
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c - 'A'] = put(x.next[c - 'A'], key, val, d+1);
        x.hasChild = true;
        return x;
    }

    // find the key that is the longest prefix of s
    public String longestPrefixOf(String query) {
        int length = longestPrefixOf(root, query, 0, 0);
        return query.substring(0, length);
    }

    // find the key in the subtrie rooted at x that is the longest
    // prefix of the query string, starting at the dth character
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.val != null) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c - 'A'], query, d+1, length);
    }


    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    
//    public boolean iskeysWithPrefix(String prefix) {
//        Queue<String> queue = new Queue<String>();
//        Node x = get(root, prefix, 0);
//        isCollect(x, prefix, queue);
//        return !queue.isEmpty();
//    }
//
//    private void isCollect(Node x, String key, Queue<String> queue) {
//    	if (queue.size() >= 1) return;
//        if (x == null) return;
//        if (x.val != null) {queue.enqueue(key); return;}
//        for (int c = 0; c < R; c++) {
//            isCollect(x.next[c], key + (char) (c + 'A'), queue);
//        }
//    }
    
//    //Here is the nonrecursive implement for search keyWithPrefix
//    public boolean iskeysWithPrefix(String prefix) {
//    	Node x = get(root, prefix, 0);
//        return isCollect(x, prefix);
//    }
//
//    private boolean isCollect(Node x, String key) {
//    	if (x == null) return false;
//    	Queue<Node> nodeQueue = new Queue<Node>();
//    	nodeQueue.enqueue(x);
//    	while(!nodeQueue.isEmpty()) {
//    		Node y = nodeQueue.dequeue();
//    		//if (y == null) continue;    	
//        	//Node in the nodeQueue is not null
//    		if (y.val != null) return true;
//    		else {
//    			return y.hasChild;
////    	        for (int c = 0; c < R; c++) {
////    	        	if(y.next[c] != null) nodeQueue.enqueue(y.next[c]);
////    	        	if(y.next[c] != null) return true;
////    	        }    			
//    		}
//    	}
//    	return false;
//    }    
    
    
    public boolean iskeysWithPrefix(String prefix) {
    	Node x = get(root, prefix, 0);
    	if (x == null) return false;
//    	if (x.val != null) return true;
		else {
			return x.hasChild;
		}
    }
    
//    public boolean[] iskeysWithPrefixSon(String prefix) {
//    	boolean[] isExitArray = new boolean[R];
//    	Node x = get(root, prefix, 0);
//    	if (x == null) {
//			for (int i = 0; i < R; i++) {
//				isExitArray[i] = false;
//			}
//    	}
////    	if (x.val != null) return true;
//		else {
//			for (int i = 0; i < R; i++) {
//				if (x.next[i] != null)
//					isExitArray[i] = x.next[i].hasChild;
//				else
//					isExitArray[i] = false;
//			}
////			return x.hasChild;
//		}
//    	return isExitArray;
//    }
    
//    private boolean isCollect(Node x, String key) {
//    	if (x == null) return false;
//    	Queue<Node> nodeQueue = new Queue<Node>();
//    	nodeQueue.enqueue(x);
//    	while(!nodeQueue.isEmpty()) {
//    		Node y = nodeQueue.dequeue();
//    		//if (y == null) continue;    	
//        	//Node in the nodeQueue is not null
//    		if (y.val != null) return true;
//    		else {
//    			return y.hasChild;
////    	        for (int c = 0; c < R; c++) {
////    	        	if(y.next[c] != null) nodeQueue.enqueue(y.next[c]);
////    	        	if(y.next[c] != null) return true;
////    	        }    			
//    		}
//    	}
//    	return false;
//    }       
//    
    
    
    
    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<String>();
        Node x = get(root, prefix, 0);
        collect(x, prefix, queue);
        return queue;
    }

    private void collect(Node x, String key, Queue<String> queue) {
        if (x == null) return;
        if (x.val != null) queue.enqueue(key);
        for (int c = 0; c < R; c++)
            collect(x.next[c - 'A'], key + (char) (c + 'A'), queue);
    }


    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<String>();
        collect(root, "", pat, q);
        return q;
    }
 
    public void collect(Node x, String prefix, String pat, Queue<String> q) {
        if (x == null) return;
        if (prefix.length() == pat.length() && x.val != null) q.enqueue(prefix);
        if (prefix.length() == pat.length()) return;
        char next = pat.charAt(prefix.length());
        for (int c = 0; c < R; c++)
            if (next == '.' || next == c)
                collect(x.next[c - 'A'], prefix + (char) (c + 'A'), pat, q);
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) x.val = null;
        else {
            char c = key.charAt(d);
            x.next[c - 'A'] = delete(x.next[c - 'A'], key, d+1);
        }
        if (x.val != null) return x;
        for (int c = 0; c < R; c++)
            if (x.next[c - 'A'] != null)
                return x;
        return null;
    }


    // test client
    public static void main(String[] args) {

        // build symbol table from standard input
    	Dictionary<Integer> st = new Dictionary<Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        // print results
        for (String key : st.keys()) {
            StdOut.println(key + " " + st.get(key));
        }
    }
}
