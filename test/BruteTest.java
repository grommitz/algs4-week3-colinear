import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class BruteTest {

	Brute brute = new Brute();
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReadFile() throws NumberFormatException, IOException {
		InputStream is = getClass().getResourceAsStream("input6.txt");
		//Point[] points = brute.readFile("input6.txt");
		Point[] points = brute.readFile(is);
		assertThat(points.length, is(6));
		assertThat(points[0], is(new Point(19000, 10000)));
		assertThat(points[5], is(new Point(14000, 10000)));
	}

}
