//not fix backwash yet

public class Percolation {
	private int gridN;
    private int gridSize;
    private int[] grid;
    private WeightedQuickUnionUF uf;
    private int virtual_top;
    private int virtual_bottom;
    
   public Percolation(int N) {  // create N-by-N grid, with all sites blocked
	   gridN = N;
	   gridSize = gridN * gridN;
	   grid = new int [gridSize];
	   virtual_top = gridSize;
	   virtual_bottom = gridSize + 1;
	   
	   for(int i = 0; i< gridSize; i++)
		   grid[i] = -1;  //record the grid if blocked. current value -1 is used for blocked.	   
	   uf = new WeightedQuickUnionUF(gridSize + 2);
   }   
   
   //uniquely mapping 2D coordinates to 1D coordinates.
   //a private method with a signature along the lins of int xyTo1D(int, int)
   //that perform this conversion. Need to utilize the percolation grid size
   //when writing this method.
   private int xyTo1D(int x, int y) {
	   //valid row and column indices are between 1 and N.
	   return (x-1) * gridN + (y-1);
   }
   
   public void open(int i, int j){  // open site (row i, column j) if it is not already
	   //First, it should validate the indices of the site that it receives.
	   //Second, somehow mark the site as open.
	   //Third, it should perform some sequence of WeightedQuickUnionUF operations
	   //       that links the site in question to its open neighbors.
	   if (i <= 0 || i > gridN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   if (j <= 0 || j > gridN) throw new IndexOutOfBoundsException("clumn index j out of bounds");
	   
	   int k = xyTo1D(i, j);
	   grid[k] = k;
	   
	   int i_up = i - 1;
	   int i_down = i + 1;
	   int j_left = j - 1;
	   int j_right = j + 1;
	   
	   //union up, down, left and right
	   if (i_up > 0) {
		   int k_up = grid[xyTo1D(i_up, j)];
		   if (isOpen(i_up, j) && !uf.connected(k, k_up))
				   uf.union(k, k_up);		   
	   }	   
	   if (i_down <= gridN) {
		   int k_down = grid[xyTo1D(i_down, j)];
		   if (isOpen(i_down, j) && !uf.connected(k, k_down))
				   uf.union(k, k_down);		   
	   }	   
	   if (j_left > 0) {
		   int k_left = grid[xyTo1D(i, j_left)];
		   if (isOpen(i, j_left) && !uf.connected(k, k_left))
				   uf.union(k, k_left);		   
	   }	   
	   if (j_right <= gridN) {
		   int k_right = grid[xyTo1D(i, j_right)];
		   if (isOpen(i, j_right) && !uf.connected(k, k_right))
				   uf.union(k, k_right);		   
	   }	   
	   
	   //union top or bottom
	   if(i == 1)
		   uf.union(k, virtual_top);
	   
//	   if(i == gridN)
//		   uf.union(k, virtual_bottom);
	   if (i == gridN && uf.find(k) < uf.find(virtual_bottom))
		   virtual_bottom =uf.find(k);

   }         
   public boolean isOpen(int i, int j) {
	   if (i <= 0 || i > gridN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   if (j <= 0 || j > gridN) throw new IndexOutOfBoundsException("clumn index j out of bounds");
	   
	   if (grid[xyTo1D(i, j)] == -1)
		   return false;
	   else
		   return true;
	   }   // is site (row i, column j) open?
   
   public boolean isFull(int i, int j) {
	   if (i <= 0 || i > gridN) throw new IndexOutOfBoundsException("row index i out of bounds");
	   if (j <= 0 || j > gridN) throw new IndexOutOfBoundsException("clumn index j out of bounds");
	   
	   int k = xyTo1D(i, j);
	   if (uf.connected(k, virtual_top))
		   return true;
	   else
		   return false;
	   }   // is site (row i, column j) full?
   
   public boolean percolates()  {
	   if(uf.connected(virtual_bottom, virtual_top))
		   return true;
	   else
		   return false;	   
   }          // does the system percolate?
}