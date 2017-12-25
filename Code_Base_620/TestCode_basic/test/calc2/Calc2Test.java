package calc2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Calc2Test {

	@Test
	public void test1() {
		int answer = Calc2.calc2(3, 5);
		assertEquals(answer, 36);
	}

	@Test
	public void test2() {
		int answer = Calc2.calc2(100, 15);
		assertEquals(answer, 155);
	}

	@Test
	public void test3() {
		int answer = Calc2.calc2(1, 1);
		assertEquals(answer, 2);
	}

	@Test
	public void test4() {
		int answer = Calc2.calc2(0, 0);
		assertEquals(answer, 0);
	}

	@Test
	public void test5() {
		int answer = Calc2.calc2(10, 10);
		assertEquals(answer, 25);
	}

	@Test
	public void test6() {
		int answer = Calc2.calc2(10, 7);
		assertEquals(answer, 54);
	}

}
