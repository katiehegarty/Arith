import java.util.Stack;
// -------------------------------------------------------------------------
/**
 *  Utility class containing validation/evaluation/conversion operations
 *  for prefix and postfix arithmetic expressions.
 *
 *  @author  Katie Hegarty	
 *  @version 29/01/2020 17:29
 */

public class Arith 
{
	//~ Validation methods ..........................................................


	/**
	 * Validation method for prefix notation.
	 *
	 * @param prefixLiterals : an array containing the string literals hopefully in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return true if the parameter is indeed in prefix notation, and false otherwise.
	 **/
	public static boolean validatePrefixOrder(String prefixLiterals[])
	{
		// operators need to be ahead of numbers
		int counter=1;
		if(prefixLiterals.length <3) {
			return false;
		}
		for(int index=0; index< prefixLiterals.length; index++)
		{
			if(counter<=0) {
				return false;
			}
			String literal = prefixLiterals[index];
			if(isOperator(literal))
			{
				counter++;
			}
			else if(isNumber(literal))
			{
				counter--; 
			}
		}
		return true;
	}

	/**
	 * Validation method for postfix notation.
	 *
	 * @param postfixLiterals : an array containing the string literals hopefully in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return true if the parameter is indeed in postfix notation, and false otherwise.
	 **/
	public static boolean validatePostfixOrder(String prefixLiterals[])
	{
		// numbers before operators
		// on CSU22010 website the parameter is called prefixLiterals so I left it as that but changed to postfixLiterals for the sake of the rest of the method
		String [] postfixLiterals = prefixLiterals;
		int count =0;
		for(int index=0; index<postfixLiterals.length; index++)
		{
			String literal=postfixLiterals[index];
			if(isOperator(literal)) {
				count--; 
				if(count -1 <0) {
					return false;
				}
			}
			else if(isNumber(literal)) //checking if character is integer?
			{
				count++;
			}
		}
		if(count==1) return true;
		else return false;
	}
	
	public static boolean isOperator(String literal) {
		if(literal.equals("+") || literal.equals("-") || literal.equals("*") || literal.equals("/")) {
			return true;
		}
		else return false;
	}
	
	public static boolean isNumber(String number) {
		try {
			Integer.parseInt(number);
		} catch(NumberFormatException exception) {
			return false;
		}
		return true;
	}

	//~ Evaluation  methods ..........................................................


	/**
	 * Evaluation method for prefix notation.
	 *
	 * @param prefixLiterals : an array containing the string literals in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePrefixOrder(String prefixLiterals[]) {
			Stack<Integer> stackSum = new Stack<>();
			for(int i = (prefixLiterals.length -1); i >=0; i--) 
			{
				String literal = prefixLiterals[i];
				if(isOperator(literal)) 
				{
					stackSum.push(operation(literal, stackSum.pop(), stackSum.pop()));
				}
				else {
					stackSum.push(Integer.valueOf(literal));
				}
			}

			return stackSum.pop();
		
	}


	/**
	 * Evaluation method for postfix notation.
	 *
	 * @param postfixLiterals : an array containing the string literals in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePostfixOrder(String postfixLiterals[])
	{
		Stack<Integer> stackSum = new Stack<>();
		for(int index=0; index<postfixLiterals.length; index++) {
			String literal = postfixLiterals[index];
			if(isNumber(literal)) //checking if character is integer?
			{
				stackSum.push(Integer.parseInt(literal));
			}
			else {
				int x=stackSum.pop();
				int y=stackSum.pop();
				int result =operation(literal, y, x);
				stackSum.push(result);
			}
		}
		return stackSum.pop();
	}

	public static int operation(String string, int x, int y) {
		switch(string) {
		case"+":
			return(x+y);
		case"-":
			return(x-y);
		case"*":
			return(x*y);
		case"/":
			return(x/y);
		}
		return 0;
	}
	//~ Conversion  methods ..........................................................


	/**
	 * Converts prefix to postfix.
	 *
	 * @param prefixLiterals : an array containing the string literals in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in postfix order.
	 **/
	public static String[] convertPrefixToPostfix(String prefixLiterals[])
	{
		Stack<String> stack = new Stack<String>();
		String x, y = "";
		String[] postfixLiterals = new String[prefixLiterals.length];
		for(int index = prefixLiterals.length -1; index >= 0; index--) {
			String literal = prefixLiterals[index];
			if(isOperator(literal)) {
				x=stack.pop();
				y=stack.pop();
				stack.push(x+y+prefixLiterals[index]);
			}
			else {
				stack.push(prefixLiterals[index]);
			}
		}
		char[] postfix = stack.pop().toCharArray();
		for(int index = 0; index<postfix.length; index++) {
			postfixLiterals[index] = String.valueOf(postfix[index]);
		}
		return postfixLiterals;
	}


	/**
	 * Converts postfix to prefix.
	 *
	 * @param prefixLiterals : an array containing the string literals in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in prefix order.
	 **/
	public static String[] convertPostfixToPrefix(String postfixLiterals[])
	{
		Stack<String> stack = new Stack<String>();
		String x, y = "";
		String[] prefixLiterals = new String[postfixLiterals.length];
		for(int index = 0; index < postfixLiterals.length; index++) {
			String literal = postfixLiterals[index];
			if(isOperator(literal)) {
				x=stack.pop();
				y=stack.pop();
				stack.push(postfixLiterals[index] +y + x);
			}
			else {
				stack.push(postfixLiterals[index]);
			}
		}
		char[] prefix = stack.pop().toCharArray();
		for(int index = 0; index<prefix.length; index++) {
			prefixLiterals[index] = String.valueOf(prefix[index]);
		}
		return prefixLiterals;
	}

	/**
	 * Converts prefix to infix.
	 *
	 * @param infixLiterals : an array containing the string literals in prefix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPrefixToInfix(String prefixLiterals[])
	{
		Stack<String> stack = new Stack<String>();
		String x, y = "";
		String[] infixLiterals = new String[prefixLiterals.length *2-1];
		for(int index = prefixLiterals.length -1; index>=0; index--) {
			String literal = prefixLiterals[index];
			if(isOperator(literal)) {
				x=stack.pop();
				y=stack.pop();
				stack.push("(" + x + prefixLiterals[index] + y + ")");
			}
			else {
				stack.push(prefixLiterals[index]);
			}
		}
		char[] infix = stack.pop().toCharArray();
		for(int index = 0; index<infix.length; index++) {
			infixLiterals[index] = String.valueOf(infix[index]);
		}
		return infixLiterals;
	}

	/**
	 * Converts postfix to infix.
	 *
	 * @param infixLiterals : an array containing the string literals in postfix order.
	 * The method assumes that each of these literals can be one of:
	 * - "+", "-", "*", or "/"
	 * - or a valid string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPostfixToInfix(String postfixLiterals[])
	{
			Stack<String> stack = new Stack<String>();
			String x, y = "";
			String[] infixLiterals = new String[postfixLiterals.length *2-1];
			for(int index = 0; index < postfixLiterals.length; index++) {
				String literal = postfixLiterals[index];
				if(isOperator(literal)) {
					x=stack.pop();
					y=stack.pop();
					stack.push("(" + y + postfixLiterals[index] + x + ")");
				}
				else {
					stack.push(postfixLiterals[index]);
				}
			}
			char[] infix = stack.pop().toCharArray();
			for(int index = 0; index<infix.length; index++) {
				infixLiterals[index] = String.valueOf(infix[index]);
			}
			return infixLiterals;
		
	}
}

/* Q5 Research
 * String and char array 
 * methods used = toCharArray O(N) where N is the length of the array
 * 					toString O(N) where N is the number of nodes because you iterate over each node
 * 
 * Stacks
 * method used = pop O(1) 
 * 				push O(1)
 * 
 */


