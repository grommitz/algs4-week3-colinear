import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class BruteTest {

	private Brute brute = new Brute();
	private Point[] points;

	@Test
	public void testReadFile() throws NumberFormatException, IOException {
		using("input6.txt");
		assertThat(points.length, is(6));
		assertThat(points[0], is(new Point(19000, 10000)));
		assertThat(points[5], is(new Point(14000, 10000)));
	}
	
	@Test
	public void testFindLines() throws NumberFormatException, IOException {
		using("input6.txt");
		Point[] line = new Point[]{new Point(18000, 10000), 
				new Point(19000, 10000), 
				new Point(21000, 10000), 
				new Point(32000, 10000)};
		assertNumberOfLinesAndSpecificLineFound(5, line);
	}
	
	@Test
	public void testFindLinesInput8() throws NumberFormatException, IOException {
		using("input8.txt");
		Point[] line = new Point[]{new Point(10000, 0),
				new Point(7000, 3000),
				new Point(3000, 7000),
				new Point(0, 10000)};
		assertNumberOfLinesAndSpecificLineFound(2, line);
	}

	private void using(String file) throws NumberFormatException, IOException {
		InputStream is = getClass().getResourceAsStream(file);
		points = brute.readFile(is);
	}

	private void assertNumberOfLinesAndSpecificLineFound(int numLines, Point[] expectedLine) {
		List<Point[]> lines = brute.findLines(points);
		assertThat(lines.size(), is(numLines));
		String lineStr = brute.toString(expectedLine);
		boolean found = false;
		for (Point[] line_ : lines) {
			if (brute.toString(line_).equals(lineStr)) {
				found = true;
			}
		}
		assertThat("Did not find " + lineStr, found, is(true));
	}

}
