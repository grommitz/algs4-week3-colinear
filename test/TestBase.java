
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class TestBase {

	protected Point[] points;
	
	protected void using(String file) throws NumberFormatException, IOException {
		InputStream is = getClass().getResourceAsStream(file);
		if (is == null) 
			fail("InputStream null. Does the file exist and have you added resources to the project build path?");
		points = new Brute().readFile(is);
	}

	protected void assertNumberOfLinesAndSpecificLineFound(List<Point[]> lines, int numLines, Point[] expectedLine) {
		assertThat(lines.size(), is(numLines));
		String lineStr = toString(expectedLine);
		boolean found = false;
		for (Point[] line_ : lines) {
			String actual = toString(line_);
			System.out.println(actual);
			if (actual.equals(lineStr)) {
				found = true;
				break;
			}
		}
		assertThat("Did not find " + lineStr, found, is(true));
	}

	protected String toString(Point[] line) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length; ++i) {
			sb.append(line[i].toString());
			if (i + 1 < line.length)
				sb.append(" -> ");
		}
		return sb.toString();
	}

}
