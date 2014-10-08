import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class PointTest {

	Point a = new Point(1,1);
	Point b = new Point(2,1);
	Point c = new Point(2,2);
	Point d = new Point(1,2);

	@Test
	public void testCompareTo() {
		assertTrue(a.compareTo(b) < 0);
		assertTrue(a.compareTo(c) < 0);
		assertTrue(b.compareTo(c) < 0);
	}

	@Test
	public void testSlopeTo() {
		assertThat(b.slopeTo(c), is(Double.POSITIVE_INFINITY)); // vertical
		assertThat(a.slopeTo(b), is(0.0)); // horizontal
		assertThat(a.slopeTo(a), is(Double.NEGATIVE_INFINITY)); // to self "degenerate"		
	}

	@Test
	public void testComparator() {
		assertThat(new Point(0,0).SLOPE_ORDER.compare(new Point(10,0),new Point(20,0)), is(0));
		assertThat(new Point(0,0).SLOPE_ORDER.compare(new Point(10,0),new Point(10,1)), is(-1));
		assertThat(new Point(0,0).SLOPE_ORDER.compare(new Point(10,0),new Point(10,-1)), is(1));
		assertThat(new Point(0,0).SLOPE_ORDER.compare(new Point(10,0),new Point(-10,0)), is(0));
		assertThat(new Point(0,0).SLOPE_ORDER.compare(new Point(10,0),new Point(-10,1)), is(1));
		assertThat(new Point(0,0).SLOPE_ORDER.compare(new Point(10,0),new Point(-10,-1)), is(-1));
	}
	
	@Test
	public void test1() {
		assertThat (new Point(416, 149).slopeTo(new Point(115, 149)), is(0.0));
		assertThat (new Point(22217, 20089).slopeTo(new  Point(12726, 20089)), is(0.0));
	}

}
