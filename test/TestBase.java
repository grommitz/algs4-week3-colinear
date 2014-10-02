import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class TestBase {

	protected Point[] points;
	
	protected void using(String file) throws NumberFormatException, IOException {
		InputStream is = getClass().getResourceAsStream(file);
		points = Util.readFile(is);
	}

	protected void assertNumberOfLinesAndSpecificLineFound(List<Point[]> lines, int numLines, Point[] expectedLine) {
		assertThat(lines.size(), is(numLines));
		String lineStr = Util.toString(expectedLine);
		boolean found = false;
		for (Point[] line_ : lines) {
			String actual = Util.toString(line_);
			if (actual.equals(lineStr)) {
				found = true;
			}
		}
		assertThat("Did not find " + lineStr, found, is(true));
	}
}
