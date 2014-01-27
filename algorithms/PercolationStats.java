import java.util.*;
//import java.lang.*;

public class PercolationStats {
	private double[] times;
	private int percN;
	private int percT;
	private double mean;
	private double stddev;
	//private Percolation perc;
   public PercolationStats(int N, int T)  {  // perform T independent computational experiments on an N-by-N grid
	   if (N <= 0 ) throw new IllegalArgumentException ("grid N out of bounds");
	   if (T <= 0 ) throw new IllegalArgumentException ("repeat time T out of bounds");
	   percN = N;
	   percT = T;
	   times = new double[percT];
	   for(int i = 0; i < percT; i++) {
		   times[i] =this.percOne(percN) * 1.0 / (percN * percN);
		   //times[i] = i;
	   }
	   
	   double sum = 0;
	   for(int i = 0; i < percT; i++) {
		   sum += times[i];
	   }
	   mean = sum / percT;
	   
	   
	   double devsum = 0;
	   for(int i = 0; i < percT; i++) {
		   devsum += Math.pow(times[i]  - mean, 2);
	   }
	   double stddevpow = devsum / (percT - 1);
	   stddev = Math.sqrt(stddevpow);
   }
   
   private int percOne(int N) {
	   Percolation perc = new Percolation(N);
	   int time = 0;
	   //final static Random rand = new Random();
	   Random r = new Random();
	   
	   while(!perc.percolates()) {
		   int i = r.nextInt(N) + 1;
		   int j = r.nextInt(N) + 1;
		   if(!perc.isOpen(i,j)) {
			   time++;
			   perc.open(i, j);
		   }else {
			   continue;
		   }
	   }
	   return time;
   }

   public double mean() {                  // sample mean of percolation threshold
	   return mean;
   }
   public double stddev()   {              // sample standard deviation of percolation threshold
	   return stddev;
   }
   public double confidenceLo()   {          // returns lower bound of the 95% confidence interval
	   return mean - 1.96 * stddev / Math.sqrt(percN);
   }
   public double confidenceHi()  {           // returns upper bound of the 95% confidence interval
	   return mean + 1.96 * stddev / Math.sqrt(percN);
   }
   public static void main(String[] args) {  // test client, described below
	   int N = Integer.parseInt(args[0]);
	   int T = Integer.parseInt(args[1]);
	   PercolationStats percStats = new PercolationStats(N, T);
	   StdOut.print("mean = ");
	   StdOut.println(percStats.mean());
	   StdOut.print("stddev = ");
	   StdOut.println(percStats.stddev());
	   StdOut.print("95% confidence interval = ");
	   StdOut.print(percStats.confidenceLo());
	   StdOut.print(", ");
	   StdOut.println(percStats.confidenceHi());
	   
   }
}
