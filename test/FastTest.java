
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class FastTest extends TestBase {

	private Fast fast = new Fast();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInput6() throws NumberFormatException, IOException {
		using("input6.txt");
		Point[] line = new Point[]{
				new Point(14000, 10000), 
				new Point(18000, 10000), 
				new Point(19000, 10000), 
				new Point(21000, 10000), 
				new Point(32000, 10000)};
		List<Point[]> lines = fast.findLines(points);
		for (Point[] l : lines)
			System.out.println(toString(l));
		assertNumberOfLinesAndSpecificLineFound(lines, 1, line);
	}

	@Test
	public void testInput8() throws NumberFormatException, IOException {
		using("input8.txt");
		List<Point[]> lines = fast.findLines(points);
		for (Point[] l : lines)
			System.out.println(toString(l));
		assertNumberOfLinesAndSpecificLineFound(lines, 2, input8line1());
		assertNumberOfLinesAndSpecificLineFound(lines, 2, input8line2());
	}
	
	@Test
	public void testInput40() throws NumberFormatException, IOException {
		using("input40.txt");
		Point[] line = new Point[]{
				new Point(1000, 17000), new Point(1000, 27000), new Point(1000, 28000), new Point(1000, 31000)};
		List<Point[]> lines = fast.findLines(points);
		for (Point[] l : lines)
			System.out.println(toString(l));
		assertNumberOfLinesAndSpecificLineFound(lines, 4, line);
	}

	private Point[] input8line1() {
		return new Point[]{
				new Point(10000, 0), 
				new Point(7000, 3000), 
				new Point(3000, 7000), 
				new Point(0, 10000)};
	}

	private Point[] input8line2() {
		return new Point[]{
				new Point(3000, 4000), 
				new Point(6000, 7000), 
				new Point(14000, 15000), 
				new Point(20000, 21000)};
	}
	
}
