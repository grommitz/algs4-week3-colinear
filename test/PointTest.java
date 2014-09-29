import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PointTest {

	Point a = new Point(1,1);
	Point b = new Point(2,1);
	Point c = new Point(2,2);

	@Test
	public void testCompareTo() {
		assertTrue(a.compareTo(b) < 0);
		assertTrue(a.compareTo(c) < 0);
		assertTrue(b.compareTo(c) < 0);
	}

}
