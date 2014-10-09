import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A more intelligent way of finding 4-point segments than Brute force.
 * 
 * @author Martin Charlesworth
 *
 */
public class Fast {

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Fast <input file>");
			return;
		}
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		try (FileInputStream fis = new FileInputStream(args[0])) {
			Fast fast = new Fast();
			fast.run(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void run(InputStream is) throws IOException {
		Point[] points = readFile(is);
		drawPoints(points);
		List<Point[]> lines = findLines(points);
		for (Point[] line : lines) {
			print(line);
			drawLine(line);
		}
	}

	private
	List<Point[]> findLines(Point[] points) {
		List<Point[]> lines = new ArrayList<>();
		List<Integer> hashes = new ArrayList<>();
		Point[] others = Arrays.copyOf(points, points.length);
		for (int p = 0; p < points.length; ++p) {
			Point origin = points[p];
			System.out.println("origin = " + origin);
			Arrays.sort(others, origin.SLOPE_ORDER);
			double prev = 0;
			int counter = 0;
			
			for (int i = 0; i < others.length; ++i) {
				Point other = others[i];
				double slope = origin.slopeTo(other);
				if (slope == Double.NEGATIVE_INFINITY) {
					continue;
				}
				if (slope == prev) {
					++counter;
				} else {
					if (counter >= 3) {
						Point[] pts = Arrays.copyOfRange(others, i-counter, i);
						Point[] line = toLine(origin, pts);
						int hash = toString(line).hashCode();
						if (!hashes.contains(hash)) {
							lines.add(line);
							hashes.add(hash);
						}
					}
					prev = slope;
					counter = 1;
				}
				if (i + 1 == others.length && counter >= 3) {
					Point[] pts = Arrays.copyOfRange(others, i-counter+1, i+1);
					Point[] line = toLine(origin, pts);
					int hash = toString(line).hashCode();
					if (!hashes.contains(hash)) {
						lines.add(line);
						hashes.add(hash);
					}
				}
			}
		}
		return lines;
	}
	
	private void print(Point[] line) {
		System.out.println(toString(line));
	}

	private void drawPoints(Point[] points) {
		for (Point p : points)
			p.draw();
	}

	private void drawLine(Point[] line) {
		line[0].drawTo(line[line.length - 1]);
	}

	private Point[] toLine(Point p0, Point... theRest) {
		assert (theRest.length >= 3);
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
	private Point[] readFile(InputStream is) throws IOException {
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

	private String toString(Point[] line) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < line.length; ++i) {
			sb.append(line[i].toString());
			if (i + 1 < line.length)
				sb.append(" -> ");
		}
		return sb.toString();
	}

	
}
