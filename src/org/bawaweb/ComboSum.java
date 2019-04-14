/**
 * 
 */
package org.bawaweb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Navroz
 *	Given an array of n numbers
 *	Identify all k-indices
 *	whose values sum to s
 */
public class ComboSum {

	public static void main(String[] args) {
		int[] ar = new int[] { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 };
		int r = 2;			// size of combo
		int n = ar.length;
		int s = 15;//12;	//	target
		printListArr(combine(ar, r, s));

	}
	
	
	public static List<int[]> combine(final int[] arr, final int k, final int target) {
		if (arr == null)
			return null;
		if (arr.length == 0 || arr.length < k || k <= 0 || target <= 0)
			return null;

		int[] data = new int[k];
		int[] indices = new int[k];

		List<int[]> combos = new ArrayList<int[]>();
		return getCombos(arr, k, 0, data, indices, 0, combos, target);
	}

	private static List<int[]> getCombos(final int[] arr, final int r, final int index, final int[] data, final int[] indices, final int i, List<int[]> combos, final int t) {
		int n = arr.length;

		if (index == r) {
			int[] combo = new int[r];
			int[] inds = new int[r];
			for (int j = 0; j < r; j++) {
				combo[j] = data[j];
				inds[j] = indices[j];
			}

			if (checkComboSum(combo, t)) {
				combos.add(inds);
			}
			return combos;

		}

		if (i >= n) {
			return combos;
		}

		data[index] = arr[i];
		indices[index] = i;

		combos = getCombos(arr, r, index + 1, data, indices, i + 1, combos, t);

		return combos = getCombos(arr, r, index, data, indices, i + 1, combos, t);
	}
	
	private static boolean checkComboSum(int[] combo, int t) {
		int sum = 0;
		for(int val : combo) {
			sum += val;
		}
		return (sum==t);
	}


	private static void printListArr(List<int[]> combine) {
		for (int i = 0; i < combine.size(); i++) {
			int[] arr = combine.get(i);
			printIntArray(arr);
		}

	}

	private static void printIntArray(int[] arr) {
		String s = "";
		for (int i = 0; i < arr.length; i++) {
			s += arr[i] + " ";
		}
		System.out.println(s);
	}

}
