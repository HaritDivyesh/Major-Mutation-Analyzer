package calc1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Calc1Test {

	@Test
	public void test1() {
		int answer = Calc1.calc1(3, 5);
		assertEquals(answer, 30);
	}

	@Test
	public void test2() {
		int answer = Calc1.calc1(10, 1);
		assertEquals(answer, 52);
	}
	
	@Test
	public void test3() {
		int answer = Calc1.calc1(1, 1);
		assertEquals(answer, 6);
	}

	@Test
	public void test4() {
		int answer = Calc1.calc1(0, 0);
		assertEquals(answer, -5);
	}
	
	@Test
	public void test5() {
		int answer = Calc1.calc1(10, 10);
		assertEquals(answer, 105);
	}
	
	@Test
	public void test6() {
		int answer = Calc1.calc1(10, 7);
		assertEquals(answer, 55);
	}

}
