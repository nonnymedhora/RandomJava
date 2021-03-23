/**
 * 
 */
package org.bawaweb.math;

import java.util.Stack;

/**
 * @author Navroz
 *
 */
public class HighNum {

	private final String numString;

	public HighNum(final String string) {
		this.numString = string;
	}

	public String getNumString() {
		return this.numString;
	}

	private HighNum plus(final HighNum aNum) {
		Stack<Character> myStack = this.getStack(this.numString);
		Stack<Character> aStack = this.getStack(aNum);

		Stack<Character> smallrStck = myStack.size() <= aStack.size() ? myStack : aStack;
		Stack<Character> lrgrStck = myStack.size() >= aStack.size() ? myStack : aStack;
		Stack<Integer> sumStack = new Stack<Integer>();
		
		HighNum numSum = null;
		String numSumStr = "";
		int carryOver=0;
		Character s = smallrStck.pop();
		Character l = lrgrStck.pop();
		
		for (int i = 0; i < smallrStck.size(); i++) {
			int chSum = this.add(s, l, carryOver);
			if (chSum >= 10) {
				final String chrSumStr = String.valueOf(chSum);
				carryOver = Integer.parseInt(chrSumStr.substring(0, chrSumStr.length() - 1));
				sumStack.push(chSum % 10);
			} else {
				carryOver = 0;
				sumStack.push(chSum);
			}
			
			s = smallrStck.pop();
			l = lrgrStck.pop();
		}
		
		for (int i = 0; i < (lrgrStck.size() - smallrStck.size()); i++) {
			int chSum = this.add(lrgrStck.pop(), sumStack, carryOver);

			if (chSum >= 10) {
				final String chrSumStr = String.valueOf(chSum);
				carryOver = Integer.parseInt(chrSumStr.substring(0, chrSumStr.length() - 1));
				sumStack.push(chSum % 10);
			} else {
				carryOver = 0;
				sumStack.push(chSum);
			}
		}
		
		numSumStr = this.getNumSumStr(sumStack);

		return numSum = new HighNum(numSumStr);
	}

	private String getNumSumStr(final Stack<Integer> sStack) {
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < sStack.size(); i++) {
			s.append(String.valueOf(sStack.pop()));
		}
		return s.toString();
	}

	private int add(Character p, Stack<Integer> sumStck, int carryOver) {
		int sum = Character.getNumericValue(p)+carryOver;
		return sum;
	}

	private int add(Character a, Character b, int c) {
		return c + Character.getNumericValue(a) + Character.getNumericValue(b);
	}

	private Stack<Character> getStack(HighNum aNum) {
		return this.getStack(aNum.getNumString());
	}

	private Stack<Character> getStack(final String aStrng) {
		Stack<Character> aStack = new Stack<Character>();
		for(int i=0;i<aStrng.length();i++)
			aStack.push(aStrng.charAt(i));
		return aStack;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HighNum left = new HighNum("815581199");
		HighNum right = new HighNum("999999999999999");
		
		System.out.println("LeftPlusRight--"+left.plus(right).getNumString());
		

	}

}
