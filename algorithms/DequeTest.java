import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class DequeTest {
	
	@Test
	public void testremoveLast() {
		Deque deque = new Deque<String>();
		String s = "a";
		deque.addLast("a");
		String r = (String)deque.removeLast();
		assertEquals(s, r);
	}
	
}