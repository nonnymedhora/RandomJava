/**
 * 
 */
package org.bawaweb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Navroz
 *
 */
public class Fibonacci {

	static int n1 = 0, n2 = 1, n3 = 0;
	
	static int a1, a2, a4 = 0;
	static int a3 = 1;
	
	
	public static void main(String[] args) {
		int count = 20;
		System.out.println("Fibonacci\n=========================================================");
		System.out.print(n1 + " " + n2);			// printing 0 and 1
		printFibonacci(count - 2);					// n-2 because 2 numbers are already printed
		
		System.out.println("\n\nTribonacci\n====================================================");
		System.out.print(a1 + " " + a2 + " " + a3);	// printing 0, 0, 1
		printTribonacci(count - 3);					// n-3, since 3 numbers are already printed
		
		System.out.println("\n\nUsing Recursion - fib("+(count-10)+") is: ");
		System.out.print( fib(count-10) );
	}


	static void printFibonacci(int count) {
		if (count > 0) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
			System.out.print(" " + n3);
			printFibonacci(count - 1);
		}
	}
	
	
	//--------------------------------------------------
	
	static void printTribonacci(int count) {
		if (count > 0) {
			a4 = a1 + a2 + a3;
			
			a1 = a2;
			a2 = a3;
			a3 = a4;
			
			System.out.print(" " + a4);
			printTribonacci(count - 1);
		}
	}
			
	

	/*
	 * public List<Integer> getFibonacci(int n) { List<Integer> list = new
	 * ArrayList<Integer>();
	 * 
	 * return list; }
	 */

	/*
	 * public int[] getFibonacci(int n) { if(n-1 != 0) return new int[] {1};
	 * else return new int[] return null;
	 * 
	 * }
	 */

//	General Form of Tribonacci number:
//
//		a(n) = a(n-1) + a(n-2) + a(n-3) 
//		with 
//		a(0) = a(1) = 0, a(2) = 1. 
	
	
	
	//https://engineering.shopify.com/blogs/engineering/understanding-programs-using-graphs/
	//  note -- recursive
	static int fib(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fib(n - 1) + fib(n - 2);
		}
	}
	
}
