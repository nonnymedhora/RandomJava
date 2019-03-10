/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 *
 */
public class PrimeNumber {

	public PrimeNumber() {
	}

	private boolean isPrimeNumber(final int number) {
		boolean isPrime = true;

		if (number < 0)
			System.out.println("Invalid-Number	" + number);

		if (number == 1 || number == 2 || number == 3)
			return true;

		for (int i = 2; i < ((int) Math.sqrt(number) + 1); i++) {
			if (number % i == 0)
				return false;
		}

		return isPrime;
	}

	private void printPrimeNumbersTill(int number) {
		if (number < 0)
			System.out.println("Invalid-Number	" + number);
		
		String primeNumbers = "";

		for (int num = 0; num <= number; num++) {

			if (isPrimeNumber(num)) {
				primeNumbers += num + " ";
			}
		}

		System.out.println(primeNumbers);
	}

	public static void main(String[] args) {
		PrimeNumber pn = new PrimeNumber();

		pn.printPrimeNumbersTill(100);

	}

}
