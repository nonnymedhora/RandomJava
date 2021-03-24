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
		
		System.out.println("myStackIs----strLen=="+this.numString.length()+",numString=="+this.numString);
		this.printStack(myStack);
		
		System.out.println("aStackIs----strLen=="+aNum.getNumString().length()+",numString=="+aNum.getNumString());
		this.printStack(aStack);

		Stack<Character> smallrStck = myStack.size() <= aStack.size() ? myStack : aStack;
		Stack<Character> lrgrStck = myStack.size() >= aStack.size() ? myStack : aStack;
		Stack<Integer> sumStack = new Stack<Integer>();
		
		HighNum numSum = null;
		String numSumStr = "";
		int carryOver = 0;
		final int smllStckSize = smallrStck.size();
		final int lrgrStckSize = lrgrStck.size();
		System.out.println("o smallrStck.size()  "+ smllStckSize);
		System.out.println("o lrgrStck.size()  "+ lrgrStckSize);
		Character /*Integer*/ s = null;// = smallrStck.pop();
		Character /*Integer*/ l = null;// = lrgrStck.pop();
		System.out.println("____________________");

		for (int i = 0; i < smllStckSize/*-1*/; i++) {
			s = smallrStck.pop();
			l = lrgrStck.pop();
			
			int chSum = this.add(s, l, carryOver);
			System.out.println("i==="+i+" s==="+s+", l==="+l+", carr="+carryOver+", chSum=="+chSum);
			System.out.print("smallStack==");this.printStack(smallrStck);
			System.out.print("lrgrStck==");this.printStack(lrgrStck);
			System.out.print("numSumStack==");this.printStack(sumStack);
			if (chSum >= 10) {
//				final String chrSumStr = String.valueOf(chSum);
//				carryOver = Integer.parseInt(chrSumStr.substring(0, chrSumStr.length() - 1));

				carryOver = chSum / 10;
				sumStack.push(chSum % 10);
			} else {
				carryOver = 0;
				sumStack.push(chSum);
			}
			
			/*s = smallrStck.pop();
			l = lrgrStck.pop();*/
		}
		System.out.println("aftiii1 s==="+s+", l==="+l+", carr="+carryOver );
		System.out.print("smallStack==");this.printStack(smallrStck);
		System.out.print("lrgrStck==");this.printStack(lrgrStck);
		System.out.print("numSumStack==");this.printStack(sumStack);
		System.out.println("ass smallrStck.size()  "+  smallrStck.size());
		System.out.println("ass lrgrStck.size()  "+  lrgrStck.size());
		System.out.println("ass sumStack.size()  "+  sumStack.size());
		this.printStack(sumStack);
		System.out.println("____________________");
		for (int i = 0; i < (lrgrStckSize - smllStckSize); i++) {
			System.out.print("i==>"+i+"lrgrStck==");this.printStack(lrgrStck);
			System.out.print("numSumStack==");this.printStack(sumStack);
			int chSum = this.add(lrgrStck.pop(), /*sumStack,*/ carryOver);

			if (chSum >= 10) {
//				final String chrSumStr = String.valueOf(chSum);
//				carryOver = Integer.parseInt(chrSumStr.substring(0, chrSumStr.length() - 1));
				
				carryOver = chSum / 10;
				sumStack.push(chSum % 10);
			} else {
				carryOver = 0;
				sumStack.push(chSum);
			}
		}
		System.out.println("eee lrgrStck.size()  "+ lrgrStck.size());
		System.out.print("eee numSumStack==");this.printStack(sumStack);
		
		numSumStr = this.getNumSumStr(sumStack);
		System.out.println("numSumStr===>>"+numSumStr);
		return numSum = new HighNum(numSumStr);
	}

	private void printStack(Stack/*<Character>*/ sStack) {
		final Stack aStack = (Stack) sStack.clone();
		StringBuilder s = new StringBuilder("");
		String ss = "";
		for (int i = 0; i < sStack.size(); i++) {
/*			s.append(String.valueOf(sStack.pop()));*/
			ss += String.valueOf(aStack.pop());
		}
		System.out.println( ss	/*s.toString()*/ );
	}

	private String getNumSumStr(final Stack<Integer> sStack) {
		System.out.println("In getNumSumStr");
		printStack(sStack);
		final Stack aStack = (Stack) sStack.clone();
		String ss = "";
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < sStack.size(); i++) {
			s.append(String.valueOf(aStack.pop()));
//			ss += String.valueOf(aStack.pop());
		}
		return /*ss;*/s.toString();
	}

	private int add(Character p, /*Stack<Integer> sumStck,*/ int carryOver) {
		int sum = Character.getNumericValue(p)+carryOver;
//		sumStck.push(sum%10);
		return sum;
	}

	private int add(Integer p, Stack<Integer> sumStck, int carryOver) {
		int sum = p+carryOver;
		sumStck.push(sum%10);
		return sum;
	}
	

	private int add(Integer p, Integer s, int carryOver) {
		int sum = p + s + carryOver;
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
		for (int i = 0; i < aStrng.length(); i++)
			aStack.push(aStrng.charAt(i));
		return aStack;
	}
	

	private Stack<Integer> getNumStack(HighNum aNum) {
		return this.getNumStack(aNum.getNumString());
	}
	
	private Stack<Integer> getNumStack(final String aStrng) {
		System.out.println("IngetNumStack---aString-is=="+aStrng);
		Stack<Integer> aStack = new Stack<Integer>();
		for (int i = 0; i < aStrng.length(); i++)
			aStack.push(Character.getNumericValue(aStrng.charAt(i)));
		return aStack;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*int p = 27;
		int d = p%10;
		int m = p/10;
		System.out.println("p==="+p+", p%10="+d+", p/10=="+m);*/
		HighNum left = /*new HighNum("122344");*/new HighNum("815581167");
		HighNum right = /*new HighNum("1234");*/new HighNum("999978999999999");
		
		System.out.println("LeftPlusRight--"+left.plus(right).getNumString());
		

	}

}
