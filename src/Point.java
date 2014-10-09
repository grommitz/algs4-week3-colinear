import java.util.Comparator;


public class Point implements Comparable<Point> {
	
	// compare points by slope to this point
	public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
		@Override
		public int compare(Point p1, Point p2) {
			Point p0 = new Point(x, y);
			double s1 = p0.slopeTo(p1);
			double s2 = p0.slopeTo(p2);
			//System.out.println("s1=" + s1 + ", s2=" + s2);
			if (s1 == s2)
				return 0;
			else if (s1 < s2)
				return -1;
			else
				return 1;
		}		
	};

	private final int x;
	private final int y;

	// construct the point (x, y)
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// draw this point
	public void draw() {
		StdDraw.point(x, y);
	}
	
	// draw the line segment from this point to that point
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	// string representation
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	// is this point lexicographically smaller than that point?
	public int compareTo(Point that) {
		if (this.y == that.y) {
			return this.x - that.x;
		} else {
			return this.y - that.y;
		}
	}
	
	// the slope between this point and that point
	public double slopeTo(Point that) {
		if (this.x == that.x) {
			if (this.y == that.y) {
				return Double.NEGATIVE_INFINITY; // degenerate
			} else {
				return Double.POSITIVE_INFINITY; // vertical
			}
		} else if (this.y == that.y) {
			return 0.0; // horizontal line
		}
		double s = (double) (that.y - this.y) / (double) (that.x - this.x);
		return s;
	}
	
}

