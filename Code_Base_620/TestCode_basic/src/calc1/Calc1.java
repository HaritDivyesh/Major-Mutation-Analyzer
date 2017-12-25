package calc1;

public class Calc1 {

	public static int calc1(int a, int b) {
		// calculates the following
		int x = 10;
		int y = 5;
		int comp = a * x + (b - y);
		if (comp % 2 == 0 && a >= 8) {
			comp = (comp + 9) / 2;
		}
		return comp;
	}

}
