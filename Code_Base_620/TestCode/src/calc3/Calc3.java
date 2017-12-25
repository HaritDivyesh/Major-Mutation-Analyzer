package calc3;

import java.util.List;

public class Calc3 {

	public static int calc3(List<Integer> a) throws Exception {
		int value = 0;
		if (a == null || a.isEmpty()) {
			throw new Exception();
		}
		int size = a.size();
		int counter = 0;
		while (counter < size) {
			value += a.get(counter);
			counter += 1;
		}
		return value;
	}

}
