import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void run(InputStream is) throws NumberFormatException, IOException {
		Point[] points = Util.readFile(is);
		List<Point[]> lines = findLines(points);
		for (Point[] line : lines) {
			Util.print(line);
			Util.draw(line);
		}
	}

	List<Point[]> findLines(Point[] points) {
		List<Point[]> lines = new ArrayList<>();
		for (int p = 0; p < points.length; ++p) {
			Point origin = points[p];
			Point[] others = Arrays.copyOfRange(points, p+1, points.length); // pointsWithout(points, origin);
			Arrays.sort(others, origin.SLOPE_ORDER);
			double prev = -1.0;
			int counter = 0;
			for (int i = 0; i < others.length; ++i) {
				Point other = others[i];
				double slope = origin.slopeTo(other);
				if (slope == prev) {
					++counter;
				} else {
					if (counter >= 3) {
						Point[] pts = Arrays.copyOfRange(others, i-counter, i);
						Point[] line = Util.toLine(origin, pts);
						lines.add(line);
					}
					prev = slope;
					counter = 1;
				}
			}
		}
		return lines;
	}
	
	private Point[] pointsWithout(Point[] all, Point withoutMe) {
		Set<Point> set = new HashSet<Point>(Arrays.asList(all));
		set.remove(withoutMe);
		return set.toArray(new Point[]{});
	}
	
	
	
	
	
}
