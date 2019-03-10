/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 * An Armstrong Nuber is
 * 	-	a Three-Digit Number
 *  -	sum of cubes of its digits
 *  is the number itself
 *  
 *  371	==	3^3	+	7^3	+	1^3
 *  153	==	1^3	+	5^3	+	3^3
 *  
 *  Generic Armstrong till 999999	-	six digits
 */
public class ArmstrongNumber {
	
	public ArmstrongNumber() {}
	
	private boolean isArmstrongNumber(final int number) {
		boolean isArmstrong = false;
		
		String numString = String.valueOf(number);
		
		int a = 	Character.getNumericValue(numString.charAt(0));		
		int b =  	Character.getNumericValue(numString.charAt(1));
		int c =  	Character.getNumericValue(numString.charAt(2));
		
//		System.out.println("for-number "+number+"\na is"+a+" b is "+b+ " c is "+c);
//		System.out.println("((int)Math.pow(a,3) + (int)Math.pow(b,3)  + (int)Math.pow(c,3))--is-"+((int)Math.pow(a,3) + (int)Math.pow(b,3)  + (int)Math.pow(c,3)));
		
		if(	((int)Math.pow(a,3) + (int)Math.pow(b,3)  + (int)Math.pow(c,3))  == number) 	isArmstrong = true;
		
		return isArmstrong ;
	}
	
	
	private boolean isGenericArmstrongNumber(final int number) {
		if ( number < 10 || number > 999999 )
			System.out.println("Invalid-Number	" + number);
		boolean isArmstrong = false;
		
		String numString = String.valueOf(number);
		final int power = numString.length();
		
		int[] a = new int[power];
		for(int i = 0; i < power; i++) {
			a[i] = Character.getNumericValue(numString.charAt(i));			
		}
		
		int sum = 0;		
		for(int i = 0; i < power; i++) {
			sum += (int)Math.pow(a[i],power);
		}
		
		if( sum == number)	isArmstrong = true;
		
		return isArmstrong;
	}
	
	private void printArmstrongNumbersTill(int number) {
		if ( number < 100 || number > 999 )
			System.out.println("Invalid-Number	" + number);
		
		String armstrongNumbers = "";

		for (int num = 100; num <= number; num++) {

			if (isArmstrongNumber(num)) {
				armstrongNumbers += num + " ";
			}
		}

		System.out.println(armstrongNumbers);
	}
	
	private void printGenericArmstrongNumbersTill(int number) {
		if ( number < 10 || number > 999999 )
			System.out.println("Invalid-Number	" + number);
		
		String armstrongNumbers = "";

		for (int num = 100; num <= number; num++) {

			if (isGenericArmstrongNumber(num)) {
				armstrongNumbers += num + " ";
			}
		}

		System.out.println(armstrongNumbers);
	}

	public static void main(String[] args) {
		ArmstrongNumber an = new ArmstrongNumber();
		an.printArmstrongNumbersTill(999);
		
		an.printGenericArmstrongNumbersTill(999999);

	}

}
