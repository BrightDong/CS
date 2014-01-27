import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node head = null;
	private int N = 0;
	private class Node {
		Item item;
		Node prev;
		Node next;
	}
	
   public Deque(){                     // construct an empty deque
	   //initial Deque by double linked list with head
	   head = new Node();	   
	   head.prev = head.next = head;	   
   }
   public boolean isEmpty() {          // is the deque empty?
	   if (N == 0)
		   return true;
	   else
		   return false;
   }
   public int size() {                 // return the number of items on the deque
	   return N;
   }
   public void addFirst(Item item){    // insert the item at the front
	   if (item == null) throw new java.lang.NullPointerException();
	   Node node = new Node();
	   node.item = item;
	   node.prev = head;
	   node.next = head.next;
	   
	   Node tmp = head.next;
	   head.next = node;
	   tmp.prev = node;
	   
	   N++; // remember to update the length
	   
   }
   public void addLast(Item item) {    // insert the item at the end
	   if (item == null) throw new java.lang.NullPointerException();
	   
	   Node node = new Node();
	   node.item = item;
	   
	   Node temp = head.prev;
	   
	   node.prev = temp;
	   node.next = head;
	   
	   temp.next = node;
	   head.prev = node;
	   
	   N++;
   }
   
   public Item removeFirst() {         // delete and return the item at the front
	   if (isEmpty())
		   throw new java.util.NoSuchElementException();
	   else{
		   Node node = head.next;
		   Item item = node.item;
		   
		   Node tmp = node.next;
		   head.next = tmp;
		   tmp.prev = head;
		   N--;
		   return item;
	   }
   }
   public Item removeLast() {          // delete and return the item at the end
	   if (isEmpty())
		   throw new java.util.NoSuchElementException();
	   else{
		   Node node = head.prev;
		   Item item = node.item;
		   
		   Node temp = node.prev;
		   head.prev = temp;
		   temp.next = head;
		   N--;
		   return item;
	   }
   }
   public Iterator<Item> iterator() {  // return an iterator over items in order from front to end
	   return new DequeIterator();
   }
   private class DequeIterator implements Iterator<Item>
   {
	   private Node n = head;
	   
	   public boolean hasNext() { 
		   if(n.next != head)
			   return true;
		   else
			   return false;
		   }
	   public void remove() {throw new java.lang.UnsupportedOperationException();}
	   public Item next() {
		   if(hasNext()) {
			   n = n.next;
			   return n.item;
		   }else{
			   throw new java.util.NoSuchElementException();
		   }	
	   }	   
   }
}