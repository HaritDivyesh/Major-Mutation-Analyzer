package calc3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Calc3Test {

	@Test
	public void test1() {

		List<Integer> a = new ArrayList<>();
		a.add(2);
		a.add(3);
		int value = 0;
		try {
			value = Calc3.calc3(a);
		} catch (Exception e) {
		}
		assertEquals(value, 5);

	}

}
