package triangle;

import org.junit.Test;
import static org.junit.Assert.*;

import static triangle.Triangle.Type;
import static triangle.Triangle.Type.*;

/**
 * 
 * @author Utkarsh Srivastava, Rishi Mody, Divyesh Harit
 * 
 */

/**
 * Test class for the Triangle implementation.
 */
public class TriangleMutationScoreTest {

	/*
	 * Test the classification of an equilateral triangle.
	 */

	// Building on top of Condition Coverage Test Suite to find Mutation Score
	// Test Suite

	// provided test case - (Covers Constructor Call for Byte-code dependent
	// coverage test tool)
	@Test
	public void test1() {
		Triangle t = new Triangle();
		Type actual = Triangle.classify(1, 1, 1);
		Type expected = EQUILATERAL;
		assertEquals(expected, actual);
	}

	// invalid edges
	@Test
	public void test2() {
		Type actual = Triangle.classify(0, 2, 1);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	// for trian = 0 and scalene
	@Test
	public void test3() {
		Type actual = Triangle.classify(3, 8, 9);
		Type expected = SCALENE;
		assertEquals(expected, actual);
	}

	// for trian = 0 and invalid
	@Test
	public void test4() {
		Type actual = Triangle.classify(2, 3, 5);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	// a=b and valid
	@Test
	public void test5() {
		Type actual = Triangle.classify(4, 4, 5);
		Type expected = ISOSCELES;
		assertEquals(expected, actual);
	}

	// a=c and valid
	@Test
	public void test6() {
		Type actual = Triangle.classify(4, 5, 4);
		Type expected = ISOSCELES;
		assertEquals(expected, actual);
	}

	// b=c and valid
	@Test
	public void test7() {
		Type actual = Triangle.classify(5, 4, 4);
		Type expected = ISOSCELES;
		assertEquals(expected, actual);
	}

	// a=b and invalid
	@Test
	public void test8() {
		Type actual = Triangle.classify(1, 1, 3);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	// a=b=c and valid
	@Test
	public void test9() {
		Type actual = Triangle.classify(2, 2, 2);
		Type expected = EQUILATERAL;
		assertEquals(expected, actual);
	}

	// for trian = 0 and scalene
	@Test
	public void test10() {
		Type actual = Triangle.classify(5, 4, 3);
		Type expected = SCALENE;
		assertEquals(expected, actual);
	}

	// ADDITIONAL TEST CASES FOR CONDITIONAL COVERAGE

	// to test additional cases of 1st decision statement
	@Test
	public void test11() {
		Type actual = Triangle.classify(2, 0, 1);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test12() {
		Type actual = Triangle.classify(1, 2, 0);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test13() {
		Type actual = Triangle.classify(0, 0, 2);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test14() {
		Type actual = Triangle.classify(0, 2, 0);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test15() {
		Type actual = Triangle.classify(2, 0, 0);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test16() {
		Type actual = Triangle.classify(0, 0, 0);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	// to test additional cases of 'isocelese' decision statement
	@Test
	public void test17() {
		Type actual = Triangle.classify(5, 2, 2);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test18() {
		Type actual = Triangle.classify(2, 2, 5);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test19() {
		Type actual = Triangle.classify(2, 5, 2);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test20() {
		Type actual = Triangle.classify(5, 2, 4);
		Type expected = SCALENE;
		assertEquals(expected, actual);
	}

	@Test
	public void test21() {
		Type actual = Triangle.classify(5, 3, 2);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test22() {
		Type actual = Triangle.classify(2, 5, 3);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test23() {
		Type actual = Triangle.classify(2, 1, 5);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test24() {
		Type actual = Triangle.classify(5, 1, 2);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test25() {
		Type actual = Triangle.classify(2, 5, 1);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test26() {
		Type actual = Triangle.classify(3, 4, 5);
		Type expected = SCALENE;
		assertEquals(expected, actual);
	}

	@Test
	public void test27() {
		Type actual = Triangle.classify(1, 3, 1);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test28() {
		Type actual = Triangle.classify(3, 1, 1);
		Type expected = INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test29() {
		Type actual = Triangle.classify(2, 5, 4);
		Type expected = SCALENE;
		assertEquals(expected, actual);
	}

	// ADDITIONAL TEST CASES FOR MUTATION TESTING
	// caters to mutants (including : 109,126,4,9,18,105,122,139 etc.)

	@Test
	public void test30() {
		Type actual = Triangle.classify(0, 4, 4);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test31() {
		Type actual = Triangle.classify(4, 4, 0);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test32() {
		Type actual = Triangle.classify(4, 0, 4);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test33() {
		Type actual = Triangle.classify(2, 2, 4);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test34() {
		Type actual = Triangle.classify(2, 4, 2);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test35() {
		Type actual = Triangle.classify(4, 2, 2);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test36() {
		Type actual = Triangle.classify(-1, 2, 2);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test37() {
		Type actual = Triangle.classify(2, -1, 2);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test38() {
		Type actual = Triangle.classify(2, 2, -1);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test39() {
		Type actual = Triangle.classify(3, 3, 7);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test40() {
		Type actual = Triangle.classify(3, 7, 3);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

	@Test
	public void test41() {
		Type actual = Triangle.classify(7, 3, 3);
		Type expected = Type.INVALID;
		assertEquals(expected, actual);
	}

}
