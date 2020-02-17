
import static org.junit.Assert.*; 
import org.junit.Test;

public class ArithTest {

	@Test
	public void testValidatePrefixOrder() {
		String[] testArray = {"*", "+", "1", "2", "3"};
		assertTrue(Arith.validatePrefixOrder(testArray));
		String [] testArray1 = {"*", "3"};
		assertFalse(Arith.validatePrefixOrder(testArray1));
		String [] testArray2 = {"3", "1", "2", "+", "*"};
		assertFalse(Arith.validatePrefixOrder(testArray2));
	}

	@Test
	public void testValidatePostfixOrder() {
		String [] testArray = {"3", "1", "2", "+", "*"};
		assertTrue(Arith.validatePostfixOrder(testArray));
		String [] testArray1 = {"*", "+", "1", "2", "3"};
		assertFalse(Arith.validatePostfixOrder(testArray1));
	}
	
	@Test
	public void testEvaluatePrefixOrder() {
		String[] testArray = {"*", "+", "1", "2", "3"};
		assertEquals(9, Arith.evaluatePrefixOrder(testArray));
		String[] testArray1 = {"/", "-", "6", "1", "5"};
		assertEquals(1, Arith.evaluatePrefixOrder(testArray1));
	}
	
	@Test
	public void testEvaluatePostfixOrder() {
		String [] testArray = {"3", "1", "2", "+", "*"};
		assertEquals(9, Arith.evaluatePostfixOrder(testArray));
	}
	
	@Test
	public void testConvertPrefixToPostfix() {
		String [] answerArray = {"1", "2","+", "3", "*"};
		String [] testArray = {"*", "+", "1", "2", "3"};
		assertArrayEquals(answerArray, Arith.convertPrefixToPostfix(testArray));
		String [] testArray1 = {"/", "-", "A", "B", "C"};
		String [] answerArray1 = {"A", "B", "-", "C", "/"};
		assertArrayEquals(answerArray1, Arith.convertPrefixToPostfix(testArray1));
	}
	@Test
	public void testConvertPostfixToPrefix() {
		String [] testArray = {"1", "2","+", "3", "*"};
		String [] answerArray = {"*", "+", "1", "2", "3"};
		assertArrayEquals(answerArray, Arith.convertPostfixToPrefix(testArray));
		String [] answerArray1 = {"/", "-", "A", "B", "C"};
		String [] testArray1 = {"A", "B", "-", "C", "/"};
		assertArrayEquals(answerArray1, Arith.convertPostfixToPrefix(testArray1));
	}
	
	@Test
	public void testConvertPrefixToInfix() {
		String [] testArray = {"*", "+", "1", "2", "3"};
		String [] answerArray = {"(", "(", "1", "+", "2", ")", "*", "3", ")"};
		assertArrayEquals(answerArray, Arith.convertPrefixToInfix(testArray));
		String [] testArray1 = {"/", "-", "A", "B", "C"};
		String [] answerArray1 = {"(", "(", "A", "-", "B", ")", "/", "C", ")"};
		assertArrayEquals(answerArray1, Arith.convertPrefixToInfix(testArray1));
	}
	
	@Test
	public void testConvertPostfixToInfix() {
		String [] testArray = {"1", "2","+", "3", "*"};
		String [] answerArray = {"(", "(", "1", "+", "2", ")", "*", "3", ")"};
		assertArrayEquals(answerArray, Arith.convertPostfixToInfix(testArray));
		String [] testArray1 = {"A", "B", "-", "C", "/"};
		String [] answerArray1 = {"(", "(", "A", "-", "B", ")", "/", "C", ")"};
		assertArrayEquals(answerArray1, Arith.convertPostfixToInfix(testArray1));
		
	}
	
	@Test 
	public void testOperation() {
		String notOperator = "_";
		int x =6;
		int y=8;
		assertEquals(0, Arith.operation(notOperator, x, y));
	}
	
	@Test 
	public void testIsOperator() {
		String notOperator ="5";
		String operator = "+";
		String operator1 = "-";
		String operator2 = "*";
		String operator3 = "/";
		assertTrue(Arith.isOperator(operator));
		assertFalse(Arith.isOperator(notOperator));
		assertTrue(Arith.isOperator(operator1));
		assertTrue(Arith.isOperator(operator3));
		assertTrue(Arith.isOperator(operator2));
	}
}
