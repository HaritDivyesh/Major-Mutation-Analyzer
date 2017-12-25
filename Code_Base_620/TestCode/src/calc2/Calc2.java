package calc2;

public class Calc2 {

	public static int calc2(int a, int b) {
		// calculates the following
		int x = 2;
		int y = 7;
		int comp = a / x + b * y;
		if (comp % 2 == 1 && a < 25) {
			comp = comp / 3;
		}
		return comp;
	}

}
