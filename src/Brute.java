import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * 
 * @author Martin Charlesworth
 *
 */
public class Brute {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		if (args.length != 1) {
			System.err.println("Usage: java Brute <input file>");
			return;
		}
		Brute brute = new Brute();
		brute.run(args[0]);
	}
	
	private void run(String inputFile) throws NumberFormatException, IOException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Point[] points = readFile(fis);
		System.out.println("Read " + points.length + " points.");
	}

	Point[] readFile(InputStream is) throws NumberFormatException, IOException {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
			int num = Integer.parseInt(reader.readLine());
			Point[] points = new Point[num];
			for (int i = 0; i < num; ++i) {
				String line = reader.readLine();
				try (Scanner s = new Scanner(line)) {
					int x = s.nextInt();
					int y = s.nextInt();
					points[i] = new Point(x, y);
				}
			}
			return points;
		}
	}
}
