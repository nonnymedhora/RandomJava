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
public class MathLib {

	public static void main(String[] args) {
		int[] ar = new int[] { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 };
		int r = 5;
		int n = ar.length;
		// printCombinations(ar,n,r);
		printListArr(combine(ar, r));

		System.out.println(nCk(n, r));

		for (int i = 1; i <= n; i++) {
			System.out.print(fibonacci(i) + " ");
		}
		List<int[]> pascalsT = pascalsT(n);
		printList(pascalsT);
		System.out.println("--------------------");
		System.out.println("Size is " + pascalsT.size());
	}

	public static List<int[]> pascalsT(final int n) {
		List<int[]> list = new ArrayList<int[]>();
		list.add(new int[] { 1 });
		if (n == 1) {
			return list;
		}

		return pascalsLevelT(n, list);
	}

	private static List<int[]> pascalsLevelT(int n, List<int[]> list) {

		int[] level = new int[n + 1];
		level[0] = 1; // first
		level[n] = 1; // last

		if (n == 2) {
			level[1] = 2;
			if (list.add(level))
				return list;
		} else {

			list = pascalsLevelT(n - 1, list);

			int[] last = list.get(list.size() - 1);
			for (int i = 0; i < last.length - 1; i++) {
				level[i + 1] = last[i] + last[i + 1];
			}

			list.add(level);
		}

		return list;
	}

	public static int nCk(final int n, final int k) {
		if (k > n)
			return -1; // invalid

		return factorial(n) / (factorial(k) * factorial(n - k));
	}

	public static int factorial(int k) {
		if (k == 0 || k == 1)
			return 1;
		return k * factorial(k - 1);
	}

	public static int fibonacci(final int k) {
		// sample == 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
		if (k == 0 || k == 1)
			return 1;
		if (k == 2)
			return 2;
		return fibonacci(k - 2) + fibonacci(k - 1);
	}
	
//	/C:\Users\Navroz\Documents\ed-etc\Algorithms\[Steven_S._Skiena]_The_Algorithm_Design_Manual(Bokos-Z1).pdf
	public static void generatePermutations(int n){
		int[] a=new int[9];
		
	}
	
	private static boolean finished = false;
	private static void backtrack(int[] a, int n, int k){
		
	}
	
	public static List<Integer> permute(final int[] arr, final int k) {
		if (arr == null)
			return null;
		if (arr.length == 0 || arr.length < k || k <= 0)
			return null;

		int[] data = new int[k];

		List<Integer[]> perms = new ArrayList<Integer[]>();
		return getPerms(arr,k,0,data,perms);
		
	}

	private static List<Integer> getPerms(int[] a, int k, int i, int[] c, List<Integer[]> cands) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isASolution(int[] a, int n, int k){
		return (k==n);
	}
	
	

	public static List<Integer[]> combine(final int[] arr, final int k) {
		if (arr == null)
			return null;
		if (arr.length == 0 || arr.length < k || k <= 0)
			return null;

		int[] data = new int[k];

		List<Integer[]> combos = new ArrayList<Integer[]>();
		return getCombos(arr, k, 0, data, 0, combos);
	}

	private static List<Integer[]> getCombos(int[] arr, int r, int index, int[] data, int i, List<Integer[]> combos) {
		// System.out.println("index-- "+index+" i== "+i);
		int n = arr.length;

		if (index == r) {
			Integer[] combo = new Integer[r];
			for (int j = 0; j < r; j++) {
				combo[j] = data[j];
			}
			combos.add(combo);
			return combos;
		}

		if (i >= n) {
			return combos;
		}

		data[index] = arr[i];
		combos = getCombos(arr, r, index + 1, data, i + 1, combos);

		return combos = getCombos(arr, r, index, data, i + 1, combos);
	}

	static void combineUntil(int[] arr, int n, int r, int index, int[] data, int i) {
		if (index == r) {
			for (int j = 0; j < r; j++) {
				System.out.print(data[j] + " ");
			}
			System.out.println();
			return;
		}

		if (i >= n) {
			return;
		}

		data[index] = arr[i];
		combineUntil(arr, n, r, index + 1, data, i + 1);

		combineUntil(arr, n, r, index, data, i + 1);
	}

	static void printCombinations(int[] arr, int n, int r) {
		int[] data = new int[r];

		combineUntil(arr, n, r, 0, data, 0);
	}

	private static void printList(List<int[]> list) {
		for (int i = 0; i < list.size(); i++) {
			int[] arr = list.get(i);
			printArray(arr);
		}

	}

	private static void printNSpaces(int n) {
		String s = "";
		final String space = " ";
		for (int i = 0; i < n; i++)
			s += space;
		System.out.print(s);
	}

	private static void printListArr(List<Integer[]> combine) {
		for (int i = 0; i < combine.size(); i++) {
			Integer[] arr = combine.get(i);
			printIntArray(arr);
		}

	}

	private static void printArray(int[] arr) {
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s += arr[i] + " ";
		}
		System.out.println(s);
	}

	private static void printIntArray(Integer[] arr) {
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s += arr[i] + " ";
		}
		System.out.println(s);
	}

}
