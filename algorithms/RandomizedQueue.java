import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] a;
	private int N = 0;
   public RandomizedQueue()  {         // construct an empty randomized queue
	   a = (Item[])new Object[2];
	   
   }
   public boolean isEmpty() {          // is the queue empty?
	   return N == 0;
   }
   public int size()  {                // return the number of items on the queue
	   return N;
   }
   
   private void resize(int capacity) {
	   assert capacity >= N;
	   Item[] temp = (Item[]) new Object[capacity];
	   for (int i = 0; i < N; i++) {
		   temp[i] = a[i];
	   }
	   a = temp;
   }
   public void enqueue(Item item){     // add the item
	   if (item == null) throw new java.lang.NullPointerException();
	   if (N == a.length) resize(2*a.length);
	   a[N++] = item;
   }
   public Item dequeue()  {            // delete and return a random item
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   StdRandom.shuffle(a, 0, N-1);
	   Item item = a[N-1];
	   a[N-1] = null;
	   N--;
	   
	   if (N > 0 && N == a.length / 4) resize(a.length/2);
	   
	   return item;
	   
   }
   public Item sample()  {             // return (but do not delete) a random item
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   StdRandom.shuffle(a, 0, N-1);
	   return a[N-1];
   }
   public Iterator<Item> iterator() {  // return an independent iterator over items in random order
	   return new ReverseArrayIterator();
   }
   
   private class ReverseArrayIterator implements Iterator<Item> {
	   private int i;
	   private int offset;
	   
	   public ReverseArrayIterator() {
		   i = N;
		   offset = StdRandom.uniform(N);

	   }
	   
	   public boolean hasNext() {
		   return i > 0;
	   }
	   
	   public void remove() {
		   throw new java.lang.UnsupportedOperationException();
	   }
	   
	   public Item next() {
		   if (!hasNext()) throw new java.util.NoSuchElementException();

		   return a[(--i + offset) % N];

	   }
   }
}

