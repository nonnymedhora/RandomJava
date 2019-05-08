/**
 * 
 */
package org.bawaweb.prepInsta.mettl;

import java.util.Stack;

/**
 * @author Navroz
 * https://prepinsta.com/mettl-coding-questions-4/
 * 
 * Write a program to print all the LEADERS in the array. 
 * An element is leader if it is greater than all the elements to its right side.
 * 
 * And the rightmost element is always a leader. 
 * 
 * For example in the array {16, 19, 4, 3, 8, 3}, 
 * leaders are 19, 8 and 3
 *
 */
public class ArrayLeaders {

	public static Stack<Integer> getLeaders(int[] anArray) {
		if (anArray == null || anArray.length == 0) {
			return null;
		}
		if (anArray.length == 1) {
			Stack<Integer> stack = new Stack<Integer>();
			stack.push(anArray[0]);
			return stack;
		} else {
			Stack<Integer> stack = new Stack<Integer>();
			int max = anArray[anArray.length - 1];
			stack.push(max);

			for (int i = anArray.length - 2; i >= 0; i--) {
				if (anArray[i] > max) {
					max = anArray[i];
					stack.push(max);

				}
			}
			return stack;

		}
	}

	public static void main(String[] args) {
		int[] theArray = new int[] { 16, 19, 4, 3, 8, 3 };
		Stack<Integer> theStack = getLeaders(theArray);

		while (theStack.size() != 0) {
			System.out.println(theStack.pop());
		}

	}

}
