/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz 
 * adapted src - princeton, rosettacode
 */
public class MagicSquare {

	public static void main(String[] args) {
//		System.out.println("bits==38505===="+0b1001_0110_0110_1001);
		MagicSquare msq = new MagicSquare();
		for (int n = 3; n <= 40; n++) {
			System.out.println("for  "+n);
			int[][] magic = new int[n][n];
			magic = msq.createMagic(n, magic);
			if (msq.checkMagic(magic)) {
				msq.printMagic(n, magic);
			} 
		}
	}

	private boolean checkMagic(int[][] matrix) {
		boolean isThereMagic = false;
		final int baseCheck = getRowSum(matrix, 0);
		final int length = matrix.length;
		
		int magicConst = length % 2 != 0 ? 
					length * ((((int) Math.pow(length, 2)) + 1) / 2)
				: 	(int)(length * (((((int) Math.pow(length, 2)) + 1) / 2)+0.5));
					
		System.out.println("MagicConstant is " + magicConst + " for length " + length);
		if( baseCheck != magicConst) {
			return isThereMagic;
		}
		
		boolean rowsOk = true;
		for (int row = 0; row < length; row++) {
			if (baseCheck != getRowSum(matrix, row)) {
				rowsOk = false;
				break;
			}
		}

		boolean colsOk = true;
		if (rowsOk) {
			for (int col = 0; col < length; col++) {
				if (baseCheck != getColSum(matrix, col)) {
					colsOk = false;
					break;
				}
			}
		}

		if (rowsOk && colsOk) {
			if (baseCheck == getLRDiagonalSum(matrix) && baseCheck == getRLDiagonalSum(matrix)) {
				isThereMagic = true;
			}
		}

		return isThereMagic;
	}

	protected int[][] createMagic(int n, int[][] magic) {
		if (n % 2 != 0) {
			return createOddMagic(n, magic);
		} else {
			if (n >= 4 && n % 4 == 0) {
				return createDoublyEvenMagic(n, magic);
			} else {
				if (n >= 6) {
					return createSinglyEvenMagic(n, magic);
				}
			}
		}
		return null;
	}

	private int[][] createDoublyEvenMagic(int n, int[][] matrix) {
 
        // pattern of count-up vs count-down zones
        int bits = 0b1001_0110_0110_1001;
        int size = n * n;
        int mult = n / 4;  // how many multiples of 4
 
        for (int row = 0, i = 0; row < n; row++) {
            for (int col = 0; col < n; col++, i++) {
                int bitPos = col / mult + (row / mult) * 4;
                matrix[row][col] = (bits & (1 << bitPos)) != 0 ? i + 1 : size - i;
            }
        }
        return matrix;
	}

	protected int[][] createSinglyEvenMagic(int n, int[][] matrix) {
 
        int size = n * n;
        int halfN = n / 2;
        int subSquareSize = size / 4;
 
        int[][] subSquare = createOddMagic(halfN,matrix);
        int[] quadrantFactors = {0, 2, 3, 1};
 
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                int quadrant = (row / halfN) * 2 + (col / halfN);
                matrix[row][col] = subSquare[row % halfN][col % halfN];
                matrix[row][col] += quadrantFactors[quadrant] * subSquareSize;
            }
        }
 
        int nColsLeft = halfN / 2;
        int nColsRight = nColsLeft - 1;
 
        for (int row = 0; row < halfN; row++)
            for (int col = 0; col < n; col++) {
                if (col < nColsLeft || col >= n - nColsRight
                        || (col == nColsLeft && row == nColsLeft)) {
 
                    if (col == 0 && row == nColsLeft)
                        continue;
 
                    int tmp = matrix[row][col];
                    matrix[row][col] = matrix[row + halfN][col];
                    matrix[row + halfN][col] = tmp;
                }
            }
 
        return matrix;	
		
	}

	protected int[][] createOddMagic(int n, int[][] matrix) {
		int row = n - 1;
		int col = n / 2;
		matrix[row][col] = 1;
		for (int i = 2; i <= n * n; i++) {
			if (matrix[(row + 1) % n][(col + 1) % n] == 0) {
				row = (row + 1) % n;
				col = (col + 1) % n;
			} else {
				row = (row - 1 + n) % n;
				// don't change col
			}
			matrix[row][col] = i;
		}
		return matrix;
	}

	protected void printMagic(int n, int[][] magic) {
		System.out.println("------------"+n+"---------------");
		// print results
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (magic[i][j] < 10)
					System.out.print(" "); // for alignment
				if (magic[i][j] < 100)
					System.out.print(" "); // for alignment
				System.out.print(magic[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("---------------------------\n\n");
	}

	private int getArraySum(final int[] anArray) {
		int sum = 0;
		final int length = anArray.length;
		for (int index = 0; index < length; index++) {
			sum += anArray[index];
		}
		return sum;
	}

	private int[] getColumn(final int[][] aMatrix, final int aCol) {
		final int length = aMatrix[0].length;
		int[] theColumn = new int[length];
		for (int row = 0; row < length; row++) {
			theColumn[row] = aMatrix[row][aCol];
		}
		return theColumn;
	}

	private int getColSum(final int[][] aMatrix, final int aCol) {
		int[] colArray = getColumn(aMatrix, aCol);
		return getArraySum(colArray);
	}

	private int[] getRow(final int[][] aMatrix, final int aRow) {
		final int length = aMatrix[0].length;
		int[] theRow = new int[length];
		for (int col = 0; col < length; col++) {
			theRow[col] = aMatrix[aRow][col];
		}
		return theRow;
	}

	private int getRowSum(final int[][] aMatrix, final int aRow) {
		int[] rowArray = getRow(aMatrix, aRow);
		return getArraySum(rowArray);
	}

	private int[] getLRDiagonal(final int[][] aMatrix) {
		int[] lrDiag = new int[aMatrix[0].length];
		final int length = aMatrix[0].length;
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (row == col) {
					lrDiag[row] = aMatrix[row][col];
				}
			}
		}
		return lrDiag;
	}

	private int getLRDiagonalSum(final int[][] aMatrix) {
		int[] lrDiag = getLRDiagonal(aMatrix);
		return getArraySum(lrDiag);
	}

	private int[] getRLDiagonal(final int[][] aMatrix) {
		int[] rlDiag = new int[aMatrix[0].length];
		final int length = aMatrix[0].length;

		for (int row = 0; row < length; row++) {
			for (int col = length - 1; col >= 0; col--) {
				if (row + col == length - 1) {
					rlDiag[row] = aMatrix[row][col];
				}
			}
		}

		return rlDiag;
	}

	private int getRLDiagonalSum(final int[][] aMatrix) {
		int[] rlDiag = getRLDiagonal(aMatrix);
		return getArraySum(rlDiag);
	}

}
