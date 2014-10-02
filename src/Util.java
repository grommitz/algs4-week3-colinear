import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Util {

	/**
	 * for testing
	 * @param line
	 * @return
	 */
	static String toString(Point[] line) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length; ++i) {
			sb.append(line[i].toString());
			if (i + 1 < line.length)
				sb.append(" -> ");
		}
		return sb.toString();
	}
	
	static void print(Point[] line) {
		System.out.println(toString(line));
	}

	static void draw(Point[] line) {
		for (int i = 0; i < line.length; ++i) {
			line[i].draw();
			if (i + 1 < line.length) {
				line[i].drawTo(line[i + 1]);
			}
		}
	}

	static Point[] toLine(Point p0, Point... theRest) {
		assert(theRest.length >= 3);
		Point[] line = new Point[1 + theRest.length];
		line[0] = p0;
		for (int i = 0; i < theRest.length; ++i)
			line[i+1] = theRest[i];
		Arrays.sort(line);
		return line;
	}

	/**
	 * visible for testing
	 * @param is
	 * @return
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	static Point[] readFile(InputStream is) throws NumberFormatException, IOException {
		try (Scanner s = new Scanner(is)) {
			int num = s.nextInt();
			Point[] points = new Point[num];
			for (int i = 0; i < num; ++i) {
				int x = s.nextInt();
				int y = s.nextInt();
				points[i] = new Point(x, y);
			}
			return points;
		}
	}

}
