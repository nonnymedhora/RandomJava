package org.bawaweb;

public class LastPoweredDigit {
	
	private final int TEN = 10;

	public int getLastDigit(int n) {
		return n % TEN;
	}

	public double getPower(int x, int y) {
		return Math.pow(x, y);
	}

	public void generateMatrix() {
		System.out.println("x \\ y || 1 2 3 4 5 6 7 8 9");
		System.out.println("_____________________________");

		for (int i = 1; i < TEN; i++) {
			System.out.print(i + "     || ");
			for (int j = 1; j < TEN; j++) {
				System.out.print(getLastDigit((int) getPower(i, j)) + " ");
				if (j == 9)
					System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		LastPoweredDigit lpd = new LastPoweredDigit();
		lpd.generateMatrix();
	}

}
/**
x \ y || 1 2 3 4 5 6 7 8 9
_____________________________
1     || 1 1 1 1 1 1 1 1 1 
2     || 2 4 8 6 2 4 8 6 2 
3     || 3 9 7 1 3 9 7 1 3 
4     || 4 6 4 6 4 6 4 6 4 
5     || 5 5 5 5 5 5 5 5 5 
6     || 6 6 6 6 6 6 6 6 6 
7     || 7 9 3 1 7 9 3 1 7 
8     || 8 4 2 6 8 4 2 6 8 
9     || 9 1 9 1 9 1 9 1 9 

 */ 
