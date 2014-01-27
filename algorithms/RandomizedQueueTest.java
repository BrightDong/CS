import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class RandomizedQueueTest {
	
	@Test
	public void testIterator() {
		RandomizedQueue reque = new RandomizedQueue<String>();
		reque.enqueue("a");
		reque.enqueue("b");
		reque.enqueue("c");
		reque.enqueue("d");
		reque.enqueue("e");
		
		Iterator it_1 = reque.iterator();
		Iterator it_2 = reque.iterator();
		while(it_1.hasNext()) {
			StdOut.print(it_1.next() + " ");
			StdOut.println(it_2.next());
		}
		
		//while(it_2.hasNext()) {
		//	StdOut.println(it_2.next());
		//}
		
	}
	
}