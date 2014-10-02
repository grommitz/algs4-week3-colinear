import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


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
			fast.print(l);
		assertNumberOfLinesAndSpecificLineFound(lines, 1, line);
	}

	@Test
	public void testInput8() throws NumberFormatException, IOException {
		using("input8.txt");
		Point[] line = new Point[]{
				new Point(10000, 0), 
				new Point(7000, 3000), 
				new Point(3000, 7000), 
				new Point(0, 10000)};
		List<Point[]> lines = fast.findLines(points);
		for (Point[] l : lines)
			fast.print(l);
		assertNumberOfLinesAndSpecificLineFound(lines, 1, line);
	}

}
