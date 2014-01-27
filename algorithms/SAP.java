

//TODO: Write JUnit test cases later.
public class SAP {
	private Digraph G;
	//The constructor should throw a java.lang.IllegalArgumentException
	public SAP(Digraph G) {
		//!!! must use this perfix before G, or local private Digraph G is uninitialed.
		this.G = new Digraph(G);
	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		// write down the first line of code, that is a good beginning.
		//write todo immediately for further implement.
		int anc_len[] = new int[G.V()];
		int len = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(G, w);
		for(int i = 0; i < G.V(); i++) {
			if (bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i)) {
				anc_len[i] = bfs_v.distTo(i) + bfs_w.distTo(i);
				if (len > anc_len[i]) {
					len = anc_len[i];
				}
			}
		}		
		if (len != Integer.MAX_VALUE)
			return len;
		else 
			return -1;
	}

	// a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	public int ancestor(int v, int w) {
		//TODO: many duplicated code with length.
		int anc_len[] = new int[G.V()];
		int len = Integer.MAX_VALUE;
		int anc = 0;
		BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(G, w);
		for(int i = 0; i < G.V(); i++) {
			if (bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i)) {
				anc_len[i] = bfs_v.distTo(i) + bfs_w.distTo(i);
				if (len > anc_len[i]) {
					len = anc_len[i];
					anc = i;
				}
			}
		}		
		
		if (len != Integer.MAX_VALUE)
			return anc;
		else 
			return -1;	
	}

	// length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		int anc_len[] = new int[G.V()];
		int len = Integer.MAX_VALUE;
		BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(G, w);
		for(int i = 0; i < G.V(); i++) {
			if (bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i)) {
				anc_len[i] = bfs_v.distTo(i) + bfs_w.distTo(i);
				if (len > anc_len[i]) {
					len = anc_len[i];
				}
			}
		}		
		if (len != Integer.MAX_VALUE)
			return len;
		else 
			return -1;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		int anc_len[] = new int[G.V()];
		int len = Integer.MAX_VALUE;
		int anc = 0;
		BreadthFirstDirectedPaths bfs_v = new BreadthFirstDirectedPaths(G, v);
		BreadthFirstDirectedPaths bfs_w = new BreadthFirstDirectedPaths(G, w);
		for(int i = 0; i < G.V(); i++) {
			if (bfs_v.hasPathTo(i) && bfs_w.hasPathTo(i)) {
				anc_len[i] = bfs_v.distTo(i) + bfs_w.distTo(i);
				if (len > anc_len[i]) {
					len = anc_len[i];
					anc = i;
				}
			}
		}		
		
		if (len != Integer.MAX_VALUE)
			return anc;
		else 
			return -1;	
	}

	// for unit testing of this class (such as the one below)
	//public static void main(String[] args) {}
	public static void main(String[] args) {
	    In in = new In(args[0]);
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    while (!StdIn.isEmpty()) {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        //System.out.println("come here! SAP line110");
	        int length   = sap.length(v, w);
	        StdOut.printf("length = %d\n", length);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
	}
}