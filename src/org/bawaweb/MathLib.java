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
		
//		  int[] ar = new int[] { 1, 3, 5, 7, 9, 2, 4, 6, 8, 10 }; int r = 5;
//		  int n = ar.length; // printCombinations(ar,n,r);
//		  printListArr(combine(ar, r));
//		  
//		  System.out.println(nCk(n, r));
//		  
//		  for (int i = 1; i <= n; i++) { System.out.print(fibonacci(i) + " ");
//		  } List<int[]> pascalsT = pascalsT(n); printList(pascalsT);
//		  System.out.println("--------------------");
//		  System.out.println("Size is " + pascalsT.size());
		 //-
//		int[][] matrix = new int[3][];
//		matrix[0] = new int[] { 5, 4, 1 };
//		matrix[1] = new int[] { 2, 6, 3 };
//		matrix[2] = new int[] { 9, 7, 8 };
//
//		System.out.println(dotProductSelf(matrix)); // 5*5+6*6+8*8=25+36+64=125
//
//		printMatrix(crossproductSelf(matrix));
//
//		int[][] m1 = new int[2][];
//		m1[0] = new int[] { 1, 2, 3 };
//		m1[1] = new int[] { 4, 5, 6 };
//
//		int[][] m2 = new int[3][];
//		m2[0] = new int[] { 7, 8 };
//		m2[1] = new int[] { 9, 10 };
//		m2[2] = new int[] { 11, 12 };			//[58 64][139 154]
//
//		printMatrix(matrixMultiply(m1, m2));
		
		int[][] idem1 = new int[3][];
		idem1[0] = new int[]{1,0,0};
		idem1[1] = new int[]{0,1,0};
		idem1[2] = new int[]{0,0,1};
		printMatrix(idem1);
		System.out.println("idem1 is Idempotent  "+ isIdempotent(idem1));
	}
	
	public static boolean isIdempotent(int[][] matrix) {
		boolean idempotent = false;
		if (isSquare(matrix)) {
			final int length = matrix.length;
			for (int row = 0; row < length; row++) {
				for (int col = 0; col < length; col++) {
					if (row == col) {
						if (matrix[row][col] != 1) {
							return false;
						}
					} else {
						if (matrix[row][col] != 0) {
							return false;
						}
					}
				}
			}
			idempotent = true;
		}
		return idempotent;
	}

	public static int[][] matrixMultiply(final int[][] m1, final int[][] m2) {
		// num rows in m1 must equal num cols in m2
		if (m1 == null || m2 == null || m1.length == 0 || m2.length == 0 || m1.length != m2[0].length) {
			return null;
		}
		int numRow = m1.length;
		for (int i = 0; i < m2.length; i++) {
			if (m2[i].length != numRow) {
				return null;
			}
		}
		int numCol = numRow;
		int[][] result = new int[numRow][numCol];

		for (int row = 0; row < numRow; row++) {
			for (int col = 0; col < numCol; col++) {
				result[row][col] = doCross(m1, m2, row, col);
			}
		}

		return result;
	}

	private static int doCross(int[][] m1, int[][] m2, int i, int j) {
		int[] row = m1[i];

		int[] col = new int[m2.length];
		for (int r = 0; r < m2.length; r++) {
			col[r] = m2[r][j];
		}
		int result = 0;

		for (int m1r = 0; m1r < row.length; m1r++) {
			result += (row[m1r] * col[m1r]);
		}

		return result;
	}

	public static int[][] crossproductSelf(final int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return null;

		final int length = matrix[0].length;
		int[][] res = new int[length][length];
		if (isSquare(matrix)) {

			for (int row = 0; row < length; row++) {
				for (int col = 0; col < length; col++) {
					int result = 0;

					for (int i = 0; i < length; i++) {
						for (int j = 0; j < length; j++) {
							if (row == i && col == j) {
								result += ((matrix[row][col]) * (matrix[i][j]));
							}
						}
					}

					res[row][col] = result;
				}
			}
		}
		return res;
	}
	
	// [a].[b] = sum(a*b) for row=col
	public static int dotProductSelf(final int[][] matrix) {
		if (matrix == null || matrix.length == 0)
			return -1;
		int result = 0;

		if (isSquare(matrix)) {
			final int length = matrix[0].length;
			for (int row = 0; row < length; row++) {
				for (int col = 0; col < length; col++) {
					if (row == col) {
						result += (matrix[row][col] * matrix[row][col]);
					}
				}

			}
		}
		return result;
	}

	private static boolean isSquare(final int[][] aMatrix) {
		if (aMatrix == null || aMatrix.length == 0)
			return false;
		boolean isSquare = true;
		final int length = aMatrix[0].length;
		for (int i = 1; i < length; i++) {
			if (aMatrix[i].length != length)
				return false;
		}
		return isSquare;
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
	


	/** from GfG
     * permutation function 
     * @param str string to calculate permutation for 
     * @param l starting index 
     * @param r end index 
	 * @return list of permutations of str
     */
	private static List<String> permute(String str, int l, int r,List<String> perms) {
		if (l == r) {
//			System.out.println(str);
			perms.add(str);
			
		} else {
			for (int i = l; i <= r; i++) {
				str = swap(str, l, i);
				/*perms=*/permute(str, l + 1, r,perms);
				str = swap(str, l, i);
			}
		}
		return perms;
	}
  
    /** from GfG
     * Swap Characters at position 
     * @param a string value 
     * @param i position 1 
     * @param j position 2 
     * @return swapped string 
     */
	public static String swap(String a, int i, int j) {
		char temp;
		char[] charArray = a.toCharArray();
		temp = charArray[i];
		charArray[i] = charArray[j];
		charArray[j] = temp;
		return String.valueOf(charArray);
	}
	
	private int getArraySum(final int[] anArray) {
		int sum = 0;
		final int length = anArray.length;
		for(int index = 0; index < length; index++) {
			sum += anArray[index];
		}
		return sum;
	}
	
	protected int[] getLRDiagonal(final int[][] aMatrix) {
		final int length = aMatrix[0].length;
		int[] lrDiag = new int[length];
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (row == col) {
					lrDiag[row] = aMatrix[row][col];
				}
			}
		}
		return lrDiag;
	}
	
	protected int getLRDiagonalSum(final int[][] aMatrix) {
		int[] lrDiag = this.getLRDiagonal(aMatrix);
		return this.getArraySum(lrDiag);
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
	
	/*private static void printArray(final int[] anArray) {
		StringBuilder x = new StringBuilder();
		for(int i = 0; i < anArray.length; i++) {
			if( i == anArray.length - 1) {
				x.append("| "+anArray[i] + " | ");
			} else {
				x.append("| "+anArray[i] + " ");
			}
		}
		System.out.println(x.toString());
	}*/
	
	private static void printMatrix(final int[][] is) {
//		System.out.println("\n [" + is[0].length + "] is\n");
//		System.out.println("______________________________________");
		for (int row = 0; row < is[0].length; row++) {
			printArray(is[row]);
//			System.out.println("______________________________________");
		}
	}

}
