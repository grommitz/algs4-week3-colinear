import java.util.Comparator;


public class Point implements Comparable<Point> {
	
	// compare points by slope to this point
	public final Comparator<Point> SLOPE_ORDER = Comparator<Point>        
	
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
			return Double.POSITIVE_INFINITY;
		}
		if (this.x == that.x && this.y == that.y) {
			return -Double.NEGATIVE_INFINITY;
		}
		double s = Math.abs(that.y - this.y) / Math.abs(that.x - this.x);
		return s;
	}
	
}

