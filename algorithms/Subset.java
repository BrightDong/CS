public class Subset {
   public static void main(String[] args) {
	   RandomizedQueue<String> s = new RandomizedQueue<String>();
	   int N = Integer.parseInt(args[0]);
	   //StdOut.println("N: "+ N);
	   //StdOut.println("input your string");
	   //int j = 5;
	   while(!StdIn.isEmpty()) {
	   //while(j > 0) {
		   String item = StdIn.readString();
		   //StdOut.print(item + " ");
		   s.enqueue(item);  
		   //j--;
		   //StdOut.print(s.dequeue());
	   }

	   //StdOut.println(" ");
	   for(int i = 0; i < N; i++) StdOut.println(s.dequeue());
   }
}