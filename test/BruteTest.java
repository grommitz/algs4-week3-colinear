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
public class BruteTest extends TestBase {

	private Brute brute = new Brute();

	@Test
	public void testReadFile() throws NumberFormatException, IOException {
		using("input6.txt");
		assertThat(points.length, is(6));
		assertThat(points[0], is(new Point(19000, 10000)));
		assertThat(points[5], is(new Point(14000, 10000)));
	}
	
	@Test
	public void testFindLinesInput6() throws NumberFormatException, IOException {
		using("input6.txt");
		Point[] line = new Point[]{new Point(18000, 10000), 
				new Point(19000, 10000), 
				new Point(21000, 10000), 
				new Point(32000, 10000)};
		assertNumberOfLinesAndSpecificLineFound(brute.findLines(points), 5, line);
	}
	
	@Test
	public void testFindLinesInput8() throws NumberFormatException, IOException {
		using("input8.txt");
		Point[] line = new Point[]{new Point(10000, 0),
				new Point(7000, 3000),
				new Point(3000, 7000),
				new Point(0, 10000)};
		assertNumberOfLinesAndSpecificLineFound(brute.findLines(points), 2, line);
	}



}
