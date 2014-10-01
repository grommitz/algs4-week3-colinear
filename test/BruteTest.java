import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class BruteTest {

	Brute brute = new Brute();
	Point[] points;
	
	@Before
	public void setUp() throws Exception {
		InputStream is = getClass().getResourceAsStream("input6.txt");
		points = brute.readFile(is);
	}

	@Test
	public void testReadFile() throws NumberFormatException, IOException {
		assertThat(points.length, is(6));
		assertThat(points[0], is(new Point(19000, 10000)));
		assertThat(points[5], is(new Point(14000, 10000)));
	}
	
	@Test
	public void testFindLines() throws NumberFormatException, IOException {
		List<Point[]> lines = brute.findLines(points);
		assertThat(lines.size(), is(5));
		Point[] line = new Point[]{new Point(18000, 10000), 
								new Point(19000, 10000), 
								new Point(21000, 10000), 
								new Point(32000, 10000)};
		String lineStr = brute.toString(line);
		boolean found = false;
		for (Point[] line_ : lines) {
			if (brute.toString(line_).equals(lineStr)) {
				found = true;
			}
		}
		assertThat("Did not find " + lineStr, found, is(true));
	}

}
