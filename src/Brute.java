
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Brute {
	
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Brute <input file>");
			return;
		}
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		try (FileInputStream fis = new FileInputStream(args[0])) {
			Brute brute = new Brute();
			brute.run(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Main methood:
	 * - read the points from the input file
	 * - find the lines of 4 colinear points
	 * - print them to std out in the prescribed format
	 * - draw them to StdDraw.
	 * 
	 * @param is
	 * @throws NumberFormatException
	 * @throws IOException
	 */
	protected void run(InputStream is) throws NumberFormatException, IOException {
		Point[] points = readFile(is);
		List<Point[]> lines = findLines(points);
		for (Point[] line : lines) {
			print(line);
			draw(line);
		}
	}

	/**
	 * Find all sets of 4 colinear points in the entire set by using brute force to iterate thru
	 * every permutation of points.
	 * perm 4 from N
	 * eg: perm 4 from 6 = 15 combinations ( 6 * 5 * 4 * 3) / (4 * 3 * 2 * 1)
	 * 
	 * @param points
	 * @return
	 */
	List<Point[]> findLines(Point[] points) {
		List<Point[]> lines = new ArrayList<Point[]>();
		for (int i = 0; i < points.length; ++i) {
			for (int j = i + 1; j < points.length; ++j) {
				for (int k = j + 1; k < points.length; ++k) {
					for (int l = k + 1; l < points.length; ++l) {
						Point p0 = points[i], p1 = points[j], p2 = points[k], p3 = points[l];
						double s1 = p0.slopeTo(p1);
						if (s1 == p0.slopeTo(p2) && s1 == p0.slopeTo(p3)) {
							Point[] line = toLine(p0, p1, p2, p3);
							lines.add(line);
						}
					}
				}
			}
		}
		return lines;
	}

	protected void print(Point[] line) {
		System.out.println(toString(line));
	}

	protected void draw(Point[] line) {
		for (int i = 0; i < line.length; ++i) {
			line[i].draw();
			if (i + 1 < line.length) {
				line[i].drawTo(line[i + 1]);
			}
		}
	}

	protected Point[] toLine(Point p0, Point... theRest) {
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
	Point[] readFile(InputStream is) throws NumberFormatException, IOException {
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

	/**
	 * for testing
	 * @param line
	 * @return
	 */
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
