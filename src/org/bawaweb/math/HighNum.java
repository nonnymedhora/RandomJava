/**
 * 
 */
package org.bawaweb.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Navroz
 *
 */
public class HighNum {

	private static final char DOT = '.';
	private static final char DASH = '-';
	private static final char ZERO = '0';
//	private static final char EMPTY = "".charAt(0);//".";
	private final String numString;
	private boolean isDebug = false;//true;//
	private boolean isDecimal = false;
	private boolean isNegative = false;

	public HighNum(final String string) {
		this.numString = string;
		
		this.isDecimal = string.contains(new Character(DOT).toString());
		
		this.isNegative  = string.startsWith(String.valueOf(DASH));
		
		if (this.isDebug) {
			System.out.println("for " + this.numString + "  decimal==" + this.isDecimal);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isDecimal ? 1231 : 1237);
		result = prime * result + (isNegative ? 1231 : 1237);
		result = prime * result + ((numString == null) ? 0 : numString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighNum other = (HighNum) obj;
		if (isDecimal != other.isDecimal)
			return false;
		if (isNegative != other.isNegative)
			return false;
		if (numString == null) {
			if (other.numString != null)
				return false;
		} else if (!numString.equals(other.numString))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HighNum [numString=" + this.numString + "]";
	}

	public String getNumString() {
		return this.numString;
	}
	/**
	 * Will remove Leading zeroes
	 * @param aStr	=	something like "00006.88" becomes
	 * @return	"6.88"
	 */
	private String removeLeadingZeroes(final String aStr) {
		String st = aStr;
		if (aStr.startsWith(String.valueOf(ZERO))) {
			
			if (!aStr.startsWith(String.valueOf(ZERO)+String.valueOf(DOT))) {
				int numZeroes = 0;
				for (int i = 0; i < aStr.length(); i++) {
					if (ZERO == st.charAt(i)) {
						numZeroes += 1;
					} else {
						break;
					}
				}
				st = st.substring(numZeroes);
			}
		}
		
		return st;
	}
	
	/**
	 * Returns a clean HighNum
	 * so	0.60000	becomes 0.6
	 * and	12.000 becomes 12
	 * etc
	 * @param aNum - the string to clean up
	 * @return
	 */
	private HighNum cleanup(final String aNum) {
		if(this.isDebug)
			System.out.println("In cleanup -- aNum == "+aNum);
		
		String astr = aNum;

		if (astr.contains(new Character(DOT).toString())) {

			if (astr.endsWith(String.valueOf(ZERO))) {
				// remove trailing zeroes
				boolean done = false;
				do {
					astr = astr.substring(0, astr.length() - 1);
					done = !astr.endsWith(String.valueOf(ZERO));
				} while (!done);
				
				// remove last dot char
				if (astr.endsWith(String.valueOf(DOT))) {
					astr = astr.substring(0, astr.length() - 1);
				}
			}
			
			astr = this.removeLeadingZeroes(astr);
			
			

//			if (astr.startsWith(String.valueOf(ZERO))
//					/*|| (astr.startsWith(String.valueOf(DASH) + String.valueOf(ZERO)))*/) {
//
//				int nxtChar = astr.startsWith(String.valueOf(DASH) + String.valueOf(ZERO)) ? 1 : 0;
//				
//				if (astr.charAt(nxtChar) == ZERO) {
//					// remove leading zeroes
//					
//					boolean done = false;
//					do {
//						astr = astr.substring(nxtChar);
//						done = !astr.startsWith(String.valueOf(ZERO));
//						
//					} while (!done);
//					
//					// remove last dot char
//					if (astr.startsWith(String.valueOf(DOT))) {
//						astr = astr.substring(0, astr.length() - 1);
//					}
//				}
//
//			}

		} else {
			
		}
		
		if(this.isDebug)
			System.out.println("returning cleaned == "+astr);
		
		return new HighNum(astr);
	}
	

	public boolean isNegativeDecimal() {
		return (this.isDecimal && this.isNegative);
	}

	public boolean isNegative() {
		return this.isNegative;
	}

	public void setNegative(boolean isNgve) {
		this.isNegative = isNgve;
	}

	public boolean isDecimal() {
		return this.isDecimal;
	}

	public void setDecimal(boolean isDml) {
		this.isDecimal = isDml;
	}

	private boolean containsDecimal(HighNum a, HighNum b) {
		return (a.isDecimal || b.isDecimal);
	}

	private boolean containsNegative(HighNum a, HighNum b) {
		return (a.isNegative || b.isNegative);
	}

	private HighNum plusDecimals(final HighNum aNum, final HighNum bNum) {
		return this.plusDecimals(aNum, bNum, 0);
	}
	


	public HighNum minus(final HighNum aNum) {
		return this.minus(aNum, 0);

	}
	
	private HighNum minus(HighNum aNum, int carryOver) {
		// TODO Auto-generated method stub
		if (this.containsDecimal(this, aNum)) {
			if (!this.containsNegative(this, aNum)) {
				return this.minusDecimals(this, aNum, carryOver);
			} else {
				return this.minusNegDecimals(this, aNum, carryOver);
			}
		}
		
		if(this.isDebug)
			System.out.println("In here -- minus,neither neg nor dec");
		
		Stack<Character> myStack = this.getStack(this.numString);
		Stack<Character> aStack = this.getStack(aNum);
		
		if (this.isDebug) {
			System.out.println("myStackIs----strLen==" + this.numString.length() + ",numString==" + this.numString);
			this.printStack(myStack);
			System.out.println(
					"aStackIs----strLen==" + aNum.getNumString().length() + ",numString==" + aNum.getNumString());
			this.printStack(aStack);
		}
		
		Stack<Integer> minusStack = new Stack<Integer>();
		String numMinusStr = "";
		
		boolean amILarger = this.isGreaterThan(aNum);
		
		if (myStack.size() == aStack.size()) {
			
			
			
			Character s = null;
			Character l = null;
			
			int size = myStack.size();
			for (int i = 0; i < size; i++) {
				s = myStack.pop();
				l = aStack.pop();

				int valCMinus = amILarger ? this.getMinusCharMapping(s, l, carryOver)
						: this.getMinusCharMapping(l, s, carryOver);
				
				if (valCMinus >= 10) {
					carryOver = valCMinus / 10;
					minusStack.push(valCMinus % 10);
				} else {
					carryOver = 0;
					minusStack.push(valCMinus);
				}
			}

			if (carryOver != 0) {
				minusStack.push(carryOver);
			}
			
			if (this.isDebug) {
				System.out.print("numMinusStack==");
				this.printStack(minusStack);
			}

			numMinusStr = this.getNumStckStr(minusStack);
			if (this.isDebug) {
				System.out.print("numMinusStr==>" + numMinusStr);
			}

			if (!amILarger) {
				numMinusStr = DASH + numMinusStr;
				if (this.isDebug)
					System.out.print("[!amILarger]numMinusStr==>" + numMinusStr);
			}

			return new HighNum(numMinusStr);

		}
		
		if(this.isDebug){
			System.out.println("StackSizes Different minus");
			System.out.println("this.isGreaterThan(aNum)=amILarger==="+amILarger+" for this=="+this.getNumString()+" and aNum=="+aNum.getNumString());
			
		}

		if (amILarger && myStack.size() > aStack.size()) {
			Character s = null;
			Character l = null;
			//System.out.println("HERRRR----this="+this.getNumString()+"  anum==="+aNum.getNumString());
			int size = aStack.size();
			for (int i = 0; i < size; i++) {
				s = myStack.pop();
				l = aStack.pop();

				int valCMinus = this.getMinusCharMapping(s, l, carryOver);
				
				if (valCMinus >= 10) {
					carryOver = valCMinus / 10;
					minusStack.push(valCMinus % 10);
				} else {
					carryOver = 0;
					minusStack.push(valCMinus);
				}
			}

			/*if (carryOver != 0) {
				minusStack.push(carryOver);
			}
			*/
			int diff = myStack.size() - aStack.size();
			for (int i = 0; i < diff; i++) {
				//System.out.println("AAAAAArrrrrGGGGhhh11111==="+i);
				minusStack.push(Character.getNumericValue(myStack.pop()));
			}
			
			if (this.isDebug) {
				System.out.println("numMinusStack22==");
				this.printStack(minusStack);
			}

			numMinusStr = this.getNumStckStr(minusStack);
			if (this.isDebug) {
				System.out.println("numMinusStr22==>" + numMinusStr);
			}
			


			return new HighNum(numMinusStr);
		}

		if (/* amILarger && */myStack.size() < aStack.size()) {
			if(this.isDebug)
				System.out.println("MINU__D______StackOfMin<aStackSize amILarger is "+ amILarger/*=false*/);
			
			//do-switch
			HighNum tempANum = aNum;
			HighNum tempBNum = this;
			//System.out.println("tempANum.minus(this).getNumString()===="+tempANum.minus(this).getNumString());
			return new HighNum(String.valueOf(DASH) + tempANum.minus(this).getNumString());
			
		}
		
		return null;
	}

	private HighNum minusNegDecimals(HighNum aNum, HighNum bNum, int carryOver) {
		// TODO Auto-generated method stub
		return null;
	}

	private HighNum minusDecimals(HighNum aNum, HighNum bNum, int carryOver) {
		HighNum majorANum = null;
		HighNum majorBNum = null;
		HighNum minorANum = null;
		HighNum minorBNum = null;

		Stack<Character> minorAStack = new Stack<Character>();
		Stack<Character> minorBStack = new Stack<Character>();
		
		String majorAStr = "";
		String majorBStr = "";
		String minorAStr = "";
		String minorBStr = "";
				
		if(aNum.isDecimal) {
			final String aNumString = aNum.getNumString();
			majorAStr = aNumString.substring(0,this.getDotPosition(aNumString));
			minorAStr = aNumString.substring(this.getDotPosition(aNumString) + 1);

			majorANum = new HighNum(majorAStr);
			minorANum = new HighNum(minorAStr);
		}
		
		if(bNum.isDecimal) {
			final String bNumString = bNum.getNumString();
			majorBStr = bNumString.substring(0,this.getDotPosition(bNumString));
			minorBStr = bNumString.substring(this.getDotPosition(bNumString) + 1);

			majorBNum = new HighNum(majorBStr);
			minorBNum = new HighNum(minorBStr);
		}
		
		if (!aNum.isDecimal && bNum.isDecimal) {
			for (int i = 0; i < minorBStr.length(); i++) {
				minorAStack.push(DOT);
				minorAStr += ZERO;
			}
			
			majorANum = aNum;
			minorANum = new HighNum(minorAStr);
		}
		
		if(!bNum.isDecimal && aNum.isDecimal) {
			for(int i = 0; i < minorAStr.length(); i++) {
				minorBStack.push(DOT);
				minorBStr += ZERO;
			}
			
			majorBNum = bNum;
			minorBNum = new HighNum(minorBStr);
		}
		
		if (minorBStr.length() > minorAStr.length()) {
			int diff = minorBStr.length()-minorAStr.length();
			for(int i = 0; i < diff; i++){
				minorAStr += ZERO;
			}
			
			minorANum = new HighNum(minorAStr);
		}
		
		if (minorAStr.length() > minorBStr.length()) {
			int diff = minorAStr.length()-minorBStr.length();
			for(int i = 0; i < diff; i++){
				minorBStr += ZERO;
			}
			
			minorBNum = new HighNum(minorBStr);
		}
		
		if (this.isDebug) {
			System.out.println("Minus\nminorsA&BEqlLength====" + (minorAStr.length() == minorBStr.length()));
			System.out.println("minorAStr == " + minorAStr);
			System.out.println("minorBStr == " + minorBStr);
			System.out.println("majorAStr == " + majorAStr);
			System.out.println("majorBStr == " + majorBStr);
		}
		
		final boolean minorAgreaterThanB = minorANum.isGreaterThan(minorBNum);
		minorANum = minorAgreaterThanB ? minorANum : new HighNum("1".concat(minorANum.getNumString()));

		HighNum minorMinusVal = minorANum.minus(minorBNum, 0);
		minorMinusVal = !minorAgreaterThanB ? new HighNum(minorMinusVal.getNumString().substring(1)) : minorMinusVal;
		final String minorMinusValStr = minorMinusVal.getNumString();

		if (this.isDebug) {
			System.out.println("1************minorMinusValStr == " + minorMinusValStr);
			System.out.println("11here carry==="+carryOver);	
		}	
		
		if (minorMinusValStr.length() > minorAStr.length()) {
			System.out.println("INIF1232");
			carryOver = Integer.parseInt(minorMinusValStr.substring(0, minorMinusValStr.length() - minorAStr.length()));
			String newMinSumStr = minorMinusValStr.substring(minorMinusValStr.length() - minorAStr.length());
			minorMinusVal = new HighNum(newMinSumStr);
		}
		
		if(!minorAgreaterThanB){carryOver+=1;}
		//System.out.println("here carry2==="+carryOver);

		HighNum majorMinusVal = majorANum.minus(majorBNum, carryOver);
		if (!minorAgreaterThanB) {
			majorMinusVal = new HighNum(majorMinusVal.getNumString().substring(1));
		}
		if (this.isDebug) {
			System.out.println("2************majorMinusVal == " + majorMinusVal.getNumString());
		}
		
		//return new HighNum(this.cleanup(majorMinusVal.getNumString()).getNumString() + DOT + this.cleanup(minorMinusVal.getNumString()).getNumString());
		
		return this.cleanup(majorMinusVal.getNumString() + DOT + minorMinusVal.getNumString());

	}

	private int getMinusCharMapping(final char a, final char b, final int carry) {
		int diff = 0;
		int lftVal = Character.getNumericValue(a);
		int rhtVal = Character.getNumericValue(b) + carry;

		if (lftVal == rhtVal)
			return diff;
		
		if (lftVal > rhtVal)
			return diff = (lftVal - rhtVal);

		return diff = ((lftVal + 10) - rhtVal) + 10;
	}
	


	public boolean isGreaterThanOrEqualTo(HighNum aNum) {
		return (this.isGreaterThan(aNum) || this.equals(aNum));
	}
	
	/**
	 * Returns true if 'this' is greater than aNum
	 * @param aNum	-	the HighNum to test
	 * @return	true - if this is greater than aNum
	 * 			false	- otherwise
	 */
	public boolean isGreaterThan(final HighNum aNum) {
		if (aNum == null || this.equals(aNum)) {
			return false;
		}
		boolean isGreater = false;
		boolean amINegative = this.isNegative;
		boolean isANumNegative = aNum.isNegative;

		if (!(this.isDecimal || aNum.isDecimal)) {
			if (!(amINegative || isANumNegative)) {
				if (this.getNumString().length() != aNum.getNumString().length()) {
					return this.getNumString().length() > aNum.getNumString().length() ? true : false;
				} else {
					// doComparison	8876 to 2212	//	5754	to 5709
					for(int i = 0; i < this.getNumString().length(); i++) {
						if (this.getNumString().charAt(i) == aNum.getNumString().charAt(i)) {
							continue;
						} else {
							return Character.getNumericValue(this.getNumString().charAt(i))
									> Character.getNumericValue(aNum.getNumString().charAt(i)) 
									? true : false;
						}
					}
					
				}
			} else {
				if (amINegative && !isANumNegative)
					return false;
				if (!amINegative && isANumNegative)
					return true;
				
				if (!amINegative && !isANumNegative) {
					System.out.println("11111hererreiammmmm");
				}

			}
			/////////////ends	notDecimals 4 neither
		} else {
			/////////// eitherRDecimals
			if (!(amINegative || isANumNegative)) {
				if(this.isDebug)	System.out.println("2222hererreiammmmm");
				
				final String myNumString = this.getNumString();
				String majorMyStr = myNumString.substring(0,this.getDotPosition(myNumString));
				String minorMyStr = myNumString.substring(this.getDotPosition(myNumString) + 1);

				HighNum majorMyNum = new HighNum(majorMyStr);
				HighNum minorMyNum = new HighNum(minorMyStr);
				
				final String aNumString = aNum.getNumString();
				String majorAStr = aNumString.substring(0,this.getDotPosition(aNumString));
				String minorAStr = aNumString.substring(this.getDotPosition(aNumString) + 1);

				HighNum majorANum = new HighNum(majorAStr);
				HighNum minorANum = new HighNum(minorAStr);
				
				return majorMyNum.isGreaterThan(majorANum);
				
				
			} else {
				if (amINegative && !isANumNegative)
					return false;
				if (!amINegative && isANumNegative)
					return true;
				
				if (!amINegative && !isANumNegative) {
					System.out.println("3333hererreiammmmm");
				}
			}
		}
		return false;
	}
	
	/**
	 * Here either a or b or both are decimal
	 * AND either a or b or both are negative
	 * @param aNum
	 * @param bNum
	 * @param carry
	 * @return
	 */
	private HighNum plusNegDecimals (final HighNum aNum, final HighNum bNum, int carry) {
		boolean leftNeg = aNum.isNegative;
		boolean rightNeg = bNum.isNegative;
		
		boolean leftDec = aNum.isDecimal;
		boolean rightDec = bNum.isDecimal;
		
		boolean leftNegDec = aNum.isNegativeDecimal();
		boolean rightNegDec = bNum.isNegativeDecimal();
		
		// below condition should never reach 
		//	a,b both neither decimal/nor negative
		if(!(leftNeg||rightNeg||leftDec||rightDec))
			return aNum.plus(bNum,carry);
		
		
		return bNum;
		
	}

	/**
	 * Here either a or b or both are decimal
	 * But BOTH are positive
	 * @param aNum	
	 * @param bNum
	 * @return
	 */
	private HighNum plusDecimals (final HighNum aNum, final HighNum bNum, int carry) {
		HighNum majorANum = null;
		HighNum majorBNum = null;
		HighNum minorANum = null;
		HighNum minorBNum = null;

		Stack<Character> minorAStack = new Stack<Character>();
		Stack<Character> minorBStack = new Stack<Character>();
		
		String majorAStr = "";
		String majorBStr = "";
		String minorAStr = "";
		String minorBStr = "";
		
		/*int carry = 0;*/
				
		if(aNum.isDecimal) {
			final String aNumString = aNum.getNumString();
			majorAStr = aNumString.substring(0,this.getDotPosition(aNumString));
			minorAStr = aNumString.substring(this.getDotPosition(aNumString) + 1);

			majorANum = new HighNum(majorAStr);
			minorANum = new HighNum(minorAStr);
		}
		
		if(bNum.isDecimal) {
			final String bNumString = bNum.getNumString();
			majorBStr = bNumString.substring(0,this.getDotPosition(bNumString));
			minorBStr = bNumString.substring(this.getDotPosition(bNumString) + 1);

			majorBNum = new HighNum(majorBStr);
			minorBNum = new HighNum(minorBStr);
		}
		
		if (!aNum.isDecimal && bNum.isDecimal) {
			for (int i = 0; i < minorBStr.length(); i++) {
				minorAStack.push(DOT);
				minorAStr += ZERO;
			}
			
			majorANum = aNum;
			minorANum = new HighNum(minorAStr);
		}
		
		if(!bNum.isDecimal && aNum.isDecimal) {
			for(int i = 0; i < minorAStr.length(); i++) {
				minorBStack.push(DOT);
				minorBStr += ZERO;
			}
			
			majorBNum = bNum;
			minorBNum = new HighNum(minorBStr);
		}
		
		if (minorBStr.length() > minorAStr.length()) {
			int diff = minorBStr.length()-minorAStr.length();
			for(int i = 0; i < diff; i++){
				minorAStr += ZERO;
			}
			
			minorANum = new HighNum(minorAStr);
		}
		
		if (minorAStr.length() > minorBStr.length()) {
			int diff = minorAStr.length()-minorBStr.length();
			for(int i = 0; i < diff; i++){
				minorBStr += ZERO;
			}
			
			minorBNum = new HighNum(minorBStr);
		}
		
		if (this.isDebug) {
			System.out.println("minorsA&BEqlLength====" + (minorAStr.length() == minorBStr.length()));
			System.out.println("minorAStr == " + minorAStr);
			System.out.println("minorBStr == " + minorBStr);
			System.out.println("majorAStr == " + majorAStr);
			System.out.println("majorBStr == " + majorBStr);
		}
	
		HighNum minorSum = minorANum.plus(minorBNum, 0);
		final String minSumStr = minorSum.getNumString();
		
		if (this.isDebug) {
			System.out.println("*************minSumStr == " + minSumStr);
		}
		
		if (minSumStr.length() > minorAStr.length()) {
			carry = Integer.parseInt(minSumStr.substring(0, minSumStr.length() - minorAStr.length()));
			String newMinSumStr = minSumStr.substring(minSumStr.length() - minorAStr.length());
			minorSum = new HighNum(newMinSumStr);
		}

		HighNum majorSum = majorANum.plus(majorBNum, carry);
		return /*new HighNum(*/this.cleanup(majorSum.getNumString() + DOT + minorSum.getNumString())/*)*/;

	}

	private int getDotPosition(String dString) {
		for (int i = 0; i < dString.length(); i++) {
			if (DOT == dString.charAt(i))
				return i;
		}
		return dString.length();
	}

	public HighNum plus(final HighNum aNum) {
		return this.plus(aNum, 0);

	}

	private HighNum plus(final HighNum aNum, int carryOver/* = 0*/) {
		if (this.containsDecimal(this, aNum)) {
			if (!this.containsNegative(this, aNum)) {
				return this.plusDecimals(this, aNum, carryOver);
			} else {
				return this.plusNegDecimals(this, aNum, carryOver);
			}
		}

		Stack<Character> myStack = this.getStack(this.numString);
		Stack<Character> aStack = this.getStack(aNum);

		if (this.isDebug) {
			System.out.println("myStackIs----strLen==" + this.numString.length() + ",numString==" + this.numString);
			this.printStack(myStack);
			System.out.println(
					"aStackIs----strLen==" + aNum.getNumString().length() + ",numString==" + aNum.getNumString());
			this.printStack(aStack);
		}
		
		Stack<Character> smallrStck = null;
		Stack<Character> lrgrStck = null;
		
		if (aStack.size() != myStack.size()) {
			smallrStck = myStack.size() < aStack.size() ? myStack : aStack;
			lrgrStck = myStack.size() > aStack.size() ? myStack : aStack;
		} else {
			smallrStck = myStack;
			lrgrStck = aStack;
		}
		Stack<Integer> sumStack = new Stack<Integer>();

		String numSumStr = "";
		/*int carryOver = 0;*/
		final int smllStckSize = smallrStck.size();
		final int lrgrStckSize = lrgrStck.size();
		Character s = null;
		Character l = null;

		if (this.isDebug) {
			System.out.println("o smallrStck.size()  " + smllStckSize);
			System.out.println("o lrgrStck.size()  " + lrgrStckSize);
			System.out.println("____________________");
		}

		for (int i = 0; i < smllStckSize; i++) {
			s = smallrStck.pop();
			l = lrgrStck.pop();

			int chSum = this.add(s, l, carryOver);
			if (this.isDebug) {
				System.out
						.println("i===" + i + " s===" + s + ", l===" + l + ", carr=" + carryOver + ", chSum==" + chSum);
				System.out.print("smallStack==");
				this.printStack(smallrStck);
				System.out.print("lrgrStck==");
				this.printStack(lrgrStck);
				System.out.print("numSumStack==");
				this.printStack(sumStack);
			}

			if (chSum >= 10) {
				carryOver = chSum / 10;
				sumStack.push(chSum % 10);
			} else {
				carryOver = 0;
				sumStack.push(chSum);
			}

		}

		if (this.isDebug) {
			System.out.println("aftiii1 s===" + s + ", l===" + l + ", carr=" + carryOver);
			System.out.print("smallStack==");
			this.printStack(smallrStck);
			System.out.print("lrgrStck==");
			this.printStack(lrgrStck);
			System.out.print("numSumStack==");
			this.printStack(sumStack);
			System.out.println("ass smallrStck.size()  " + smallrStck.size());
			System.out.println("ass lrgrStck.size()  " + lrgrStck.size());
			System.out.println("ass sumStack.size()  " + sumStack.size());
			this.printStack(sumStack);
			System.out.println("____________________");
		}

		for (int i = 0; i < (lrgrStckSize - smllStckSize); i++) {
			if (this.isDebug) {
				System.out.print("i==>" + i + "lrgrStck==");
				this.printStack(lrgrStck);
				System.out.print("numSumStack==");
				this.printStack(sumStack);
			}

			int chSum = this.add(lrgrStck.pop(), carryOver);

			if (chSum >= 10) {
				carryOver = chSum / 10;
				sumStack.push(chSum % 10);
			} else {
				carryOver = 0;
				sumStack.push(chSum);
			}
		}
		
		if (carryOver != 0) {
			sumStack.push(carryOver);
		}

		if (this.isDebug) {
			System.out.println("eee lrgrStck.size()  " + lrgrStck.size());
			System.out.print("eee numSumStack==");
			this.printStack(sumStack);
		}

		numSumStr = this.getNumStckStr(sumStack);
		if (this.isDebug) {
			System.out.println("numSumStr===>>" + numSumStr);
		}

		return new HighNum(numSumStr);
	}
	
	/**
	 * 
	 * @param aNum - the Divisor for this [the dividend]
	 * @return	integer result / quotient of "this / dividedBy aNum"
	 */
	public HighNum dividedBy(final HighNum aNum) {
		HighNum tempNum = this;
		HighNum bNum = aNum;
		
		HighNum diffNum;		
		HighNum divVal = null;
		final HighNum zeroNum = new HighNum("0");
		final HighNum oneNum = new HighNum("1");

		if (tempNum.isGreaterThan(bNum)) {

			if (this.isDebug)
				System.out.println("Here tempNum.isGreaterThan(bNum) bNum== "+bNum );			
			boolean done = false;
			int i = 1;
			while (!done) {
				
//System.out.println("b4  In !done bNum====> "+bNum+" and i = "+i);				
				bNum = bNum.plus(aNum);
				boolean d = tempNum.isGreaterThanOrEqualTo(bNum);
				if (!d) {
					done = true;
					break;
				}
//System.out.println("aftr  In !done bNum====> "+bNum+" and i = "+i);	
				
//				diffNum = tempNum.minus(bNum);
//				tempNum = tempNum.minus(bNum);
				i = i + 1;
			}
			if (this.isDebug)
				System.out.println("ENDed   i= "+i+" bNum---"+bNum);
			
			return divVal = new HighNum(String.valueOf(i));
		}
		
		
		/*
		if (tNum.isGreaterThan(bNum)) {

			diffNum = tNum.minus(bNum);
			int i = 0;

			while (!diffNum.isNegativeGreaterThan(zeroNum)) {
				tNum = tNum.minus(bNum);
				diffNum = tNum.minus(bNum);

				i = i + 1;
				if (i<=10||i == 10000 || i == 100000 || i == 200000||i==239717)
					System.out.println("i===> " + i+ "  diffNum == "+diffNum+ "  tNum == "+tNum);
				
				if(i>239717)break;
			}
			
			return divVal = new HighNum(String.valueOf(i));
		}
		*/
		return divVal;
	}

	private HighNum multiplyDecimals(HighNum aNum, HighNum bNum, int carryOver/* = 0*/) {
		String aStr = aNum.getNumString();
		int aDotPosn = aStr.indexOf(DOT);
		int minorALngth = aStr.substring(aDotPosn + 1).length();
		
		String bStr = bNum.getNumString();
		int bDotPosn = bStr.indexOf(DOT);
		int minorBLngth = bStr.substring(bDotPosn + 1).length();
		
		aStr = aStr.substring(0, aDotPosn) + aStr.substring(aDotPosn + 1);
		bStr = bStr.substring(0, bDotPosn) + bStr.substring(bDotPosn + 1);
		if(this.isDebug){
			System.out.println(aNum.getNumString() + " minL== " + minorALngth + "");
			System.out.println(bNum.getNumString() + " minL== " + minorBLngth + "");

			System.out.println("aStr==="+aStr);
			System.out.println("bStr==="+bStr);
		}
		
		HighNum multNum = new HighNum(aStr).times(new HighNum(bStr));
		String multStr = multNum.getNumString();
		if(this.isDebug)
			System.out.println("aDotPosn--->"+aDotPosn+"===bDotPosn--->"+bDotPosn+"\nmultStr===="+multStr);
		
		int multDotNum = minorALngth + minorBLngth;
		int mDtPosn = multStr.length() - multDotNum;

		if(this.isDebug)
			System.out.println("multDotNum===="+multDotNum+",,mDtPosn===="+mDtPosn);
		
		String mStr = multStr.substring(0, mDtPosn) + DOT + multStr.substring(mDtPosn);
		
		return /*new HighNum*/this.cleanup(mStr);
//		
//		
//		
//		
//		
//		
//		
//		HighNum majorANum = null;
//		HighNum majorBNum = null;
//		HighNum minorANum = null;
//		HighNum minorBNum = null;
//
//		Stack<Character> minorAStack = new Stack<Character>();
//		Stack<Character> minorBStack = new Stack<Character>();
//		
//		String majorAStr = "";
//		String majorBStr = "";
//		String minorAStr = "";
//		String minorBStr = "";
//		
//		/*int carry = 0;*/
//				
//		if(aNum.isDecimal) {
//			final String aNumString = aNum.getNumString();
//			majorAStr = aNumString.substring(0,this.getDotPosition(aNumString));
//			minorAStr = aNumString.substring(this.getDotPosition(aNumString) + 1);
//
//			majorANum = new HighNum(majorAStr);
//			minorANum = new HighNum(minorAStr);
//		}
//		
//		if(bNum.isDecimal) {
//			final String bNumString = bNum.getNumString();
//			majorBStr = bNumString.substring(0,this.getDotPosition(bNumString));
//			minorBStr = bNumString.substring(this.getDotPosition(bNumString) + 1);
//
//			majorBNum = new HighNum(majorBStr);
//			minorBNum = new HighNum(minorBStr);
//		}
//		
//		if (!isDebug) {
//			System.out.println("minorsA&BEqlLength====" + (minorAStr.length() == minorBStr.length()));
//			System.out.println("minorAStr == " + minorAStr);
//			System.out.println("minorBStr == " + minorBStr);
//			System.out.println("majorAStr == " + majorAStr);
//			System.out.println("majorBStr == " + majorBStr);
//		}
//		
//		HighNum minorMultiple = minorANum.multiply(minorBNum, carryOver);
//		final String minMultStr = minorMultiple.getNumString();
//		
//		if (isDebug) {
//			System.out.println("*************minMultStr == " + minMultStr);
//		}
//		
//		return null;
		
	}

	private HighNum multiply(HighNum aNum, int carryOver) {			
		return this.times(aNum).plus(new HighNum(String.valueOf(carryOver)));
	}

	private HighNum multiplyDecimals(HighNum aNum, HighNum bNum) {
		return this.multiplyDecimals(aNum, bNum, 0);		
	}
	
	public HighNum times(final HighNum aNum) {
		return this.multiply(aNum);		
	}
	
	private HighNum multiply(HighNum aNum) {
		if (this.containsDecimal(this, aNum)) {
			return this.multiplyDecimals(this, aNum );
		}
		
		Stack<Character> myStack = this.getStack(this.numString);
		Stack<Character> aStack = this.getStack(aNum);

		Stack<Character> smallrStck = null;
		Stack<Character> lrgrStck = null;
		
		if (aStack.size() != myStack.size()) {
			smallrStck = myStack.size() < aStack.size() ? myStack : aStack;
			lrgrStck = myStack.size() > aStack.size() ? myStack : aStack;
		} else {
			smallrStck = myStack;
			lrgrStck = aStack;
		}
		
		final int smllStckSize = smallrStck.size();
		Character s = null;
		
		List<HighNum> theNums = new ArrayList<HighNum>();
		
		for (int i = 0; i < smllStckSize; i++) {
			s = smallrStck.pop();
			theNums.add(this.getHighNum(this.multiply(i, s, lrgrStck)));
		}
		
		return this.addAllNums(theNums);
	}

	private HighNum addAllNums(List<HighNum> theNums) {
		HighNum sum = new HighNum("0");
		for(int i = 0; i < theNums.size(); i++) {
			sum = sum.plus(theNums.get(i));
		}
		return sum;
	}

	private HighNum getHighNum(Stack<?> aStack) {
		String s = "";
		Stack<?> stck = (Stack<?>) aStack.clone();
		for(int i = 0; i < aStack.size(); i++) {
			s += stck.pop();
		}
		return new HighNum(s);
	}
	
	private Stack<Character> multiply(final int i, final Character s, final Stack<Character> multStck) {
		Stack<Character> multStack = new Stack<Character>();
		for(int indx = 0; indx < i; indx++) {
			multStack.push('0');
		}

		Stack<Character> aStck = (Stack<Character>) multStck.clone();
		Character l = null;
		int carry = 0;
		for (int j = 0; j < multStck.size(); j++) {
			l = aStck.pop();
			int m = this.multiply(s, l, carry);
			if (m >= 10) {
				carry = m / 10;
				multStack.push(new Integer(m % 10).toString().charAt(0));
			} else {
				carry = 0;
				multStack.push(new Integer(m).toString().charAt(0));
			}
		}
		
		if (carry != 0) {
			multStack.push(new Integer(carry).toString().charAt(0));
		}
		
		if (this.isDebug)	
			this.printStack(multStack);
		
		return multStack;
	}

	private int multiply(Character x, Character y, int c) {
		return (Character.getNumericValue(x) * Character.getNumericValue(y)) + c;
	}

	private void printStack(Stack<?> sStack) {
		final Stack<?> aStack = (Stack<?>) sStack.clone();
		String ss = "";
		for (int i = 0; i < sStack.size(); i++) {
			ss += String.valueOf(aStack.pop());
		}
		System.out.println( ss );
	}

	private String getNumStckStr(final Stack<Integer> sStack) {
		if (this.isDebug) {
			System.out.println("In getNumStckStr");
			this.printStack(sStack);
		}
		final Stack<Integer> aStack = (Stack<Integer>) sStack.clone();
		String ss = "";
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < sStack.size(); i++) {
			s.append(String.valueOf(aStack.pop()));
//			ss += String.valueOf(aStack.pop());
		}
		return /*ss;*/s.toString();
	}

	private int add(Character p, int carryOver) {
		int sum = Character.getNumericValue(p) + carryOver;
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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int p = 7;
//		System.out.println(new Integer(p).toString().charAt(0));
		int pq = 4; 
		
//		System.out.println("4-7==="+(pq-p));
				
		if (args.length == 0) {
			/***********************************************/
			/*
			 * int p = 27; int d = p%10; int m = p/10;
			 * System.out.println("p==="+p+", p%10="+d+", p/10=="+m);
			 */
			HighNum id = new HighNum("54637.87680000");
			HighNum id1 = new HighNum("54637.0000");
			
			System.out.println("id == "+id.getNumString() +"\nclean=="+ id.cleanup(id.getNumString()).getNumString());
			System.out.println("id1 == "+id1.getNumString() +"\nclean=="+ id1.cleanup(id1.getNumString()).getNumString());
			
			HighNum idD = new HighNum("876543234567.8765456");
			HighNum idD2 = new HighNum("9876.34565445656");//876543244444.22220005656(+)	
			
			System.out.println(idD.getNumString() +" Plus " + idD2.getNumString() + " == "+ idD.plus(idD2).getNumString());
			System.out.println(idD.getNumString() +" Times " + idD2.getNumString() + " == "+ idD.times(idD2).getNumString());
			
			idD =  new HighNum("270000089"); //XXX		124710406997
			idD2 = new HighNum("4573");//1234710406997
			System.out.println(idD.getNumString() +" Times " + idD2.getNumString() + " == "+ idD.times(idD2).getNumString());
			 
			idD = new HighNum("99876.543234567");
			idD2 = new HighNum("9876.94");//109753.483234567
			
			System.out.println(idD.getNumString() +" Plus " + idD2.getNumString() + " == "+ idD.plus(idD2).getNumString());
			
			idD = new HighNum("876543234");
			idD2 = new HighNum("2345649876.34565445656");//3222193110.34565445656

			System.out.println(idD.getNumString() +" Plus " + idD2.getNumString() + " == "+ idD.plus(idD2).getNumString());
			
			idD = new HighNum("987659");
			idD2 = new HighNum("912349");//1900008

			System.out.println(idD.getNumString() +" Plus " + idD2.getNumString() + " == "+ idD.plus(idD2).getNumString());

//////////////////////////////////////////////////////////////////////////			
			
			HighNum left = /* new HighNum("122344"); */new HighNum("815581167");
			HighNum right = /* new HighNum("1234"); */new HighNum("999978999999999");//999979815581166
			System.out.println(left.getNumString()+" Plus " +right.getNumString()+" == "+ left.plus(right).getNumString());
			
			left =  new HighNum("122344"); 
			right = new HighNum("1234");//123578
			System.out.println(left.getNumString()+" Plus " +right.getNumString()+" == "+ left.plus(right).getNumString());
			
			left =  new HighNum("14141419999999994");
			right = new HighNum("878787124558");//14142298787124552
			System.out.println(left.getNumString()+" Plus " +right.getNumString()+" == "+ left.plus(right).getNumString());
			
		/***********************************************/	
			left =  new HighNum("122344"); 
			right = new HighNum("1234");//150972496
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());

			left =  new HighNum("270000089"); //		1234710406997
			right = new HighNum("4573");//1234710406997
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());

			left =  new HighNum("27.0000089"); //1234.710406997
			right = new HighNum("45.73");//1234.710406997
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());

			left =  new HighNum("561.9000089"); //22478849.35761512567
			right = new HighNum("40005.0703");//22478849.35761512567
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());

			left =  new HighNum("2790.0000089"); //128032.347108418597
			right = new HighNum("45.88973");//128032.347108418597
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());


			left =  new HighNum("11.25");//XXX	100.00
			right = new HighNum("88.75");//100
			System.out.println(left.getNumString()+" Plus " +right.getNumString()+" == "+ left.plus(right).getNumString());


			left =  new HighNum("1.25");//XXX	12.00
			right = new HighNum("10.75");//12
			System.out.println(left.getNumString()+" Plus " +right.getNumString()+" == "+ left.plus(right).getNumString());

			left =  new HighNum("11.25");//998.4375
			right = new HighNum("88.75");//998.4375
			System.out.println(left.getNumString()+" Times " +right.getNumString()+" == "+ left.times(right).getNumString());

			left =  new HighNum("1.20");//XXX	0.6000
			right = new HighNum("0.50");//0.6
			System.out.println(left.getNumString()+" Times " +right.getNumString()+" == "+ left.times(right).getNumString());


			left = new HighNum("8876");
			right = new HighNum("2212");
			
			System.out.println(left.getNumString()+" Greater " +right.getNumString()+" > "+ left.isGreaterThan(right));


			left = new HighNum("5574");
			right = new HighNum("5509");
			
			System.out.println(left.getNumString()+" Greater " +right.getNumString()+" > "+ left.isGreaterThan(right));


			left = new HighNum("-5574");
			right = new HighNum("5509");
			
			System.out.println(left.getNumString()+" Greater " +right.getNumString()+" > "+ left.isGreaterThan(right));

			left = new HighNum("5574");
			right = new HighNum("-5509");
			
			System.out.println(left.getNumString()+" Greater " +right.getNumString()+" > "+ left.isGreaterThan(right));

			left = new HighNum("544444574");
			right = new HighNum("5509");
			
			System.out.println(left.getNumString()+" Greater " +right.getNumString()+" > "+ left.isGreaterThan(right));


			left = new HighNum("5574");
			right = new HighNum("566666666509");
			
			System.out.println(left.getNumString()+" Greater " +right.getNumString()+" > "+ left.isGreaterThan(right));

			left = new HighNum("578878888.8574");
			right = new HighNum("56666666.6509");
			
			System.out.println(left.getNumString()+" Greater " +right.getNumString()+" > "+ left.isGreaterThan(right));

			left = new HighNum("107");
			right = new HighNum("89");
			
			System.out.println(left.getNumString()+" Minus " +right.getNumString()+" ===> "+ left.minus(right));

			left = new HighNum("545.8574");
			right = new HighNum("187.6509");
			
			System.out.println(left.getNumString()+" Minus " +right.getNumString()+" ===> "+ left.minus(right).getNumString());

			
			
			
			
		} else {System.out.println("argslength==="+args.length);//for	*	==	4
			if (args.length == 3) {
				System.out.println("a0===>"+args[0]+"a1==="+args[1]+"a2==="+args[2]);
				HighNum left = new HighNum(args[0]);
				String op = args[1];System.out.println("op	==	"+op);
				HighNum right = new HighNum(args[2]);
				
				switch(op) {
				case	"plus":	System.out.println("LeftPlusRight-->>" + left.plus(right).getNumString());
				break;
				case	"+":	System.out.println("LeftPlusRight-->>" + left.plus(right).getNumString());
				break;
				case	"times":	System.out.println("LeftTimesRight-->>" + left.times(right).getNumString());
				break;
				case	"*":	System.out.println("LeftTimesRight-->>" + left.times(right).getNumString());
				break;
				default:
					System.out.println("LeftPlusRight-->" + left.plus(right).getNumString());
				}
			}
			
		}
		

	}

}
/* * 
 *	Navroz@Navroz-PC MINGW64 ~/eclipseSpaces/workspace/RandomJava/bin (master)
 *	$ java org.bawaweb.math.HighNum 7848558510558566 times 988597489936585154
 *	LeftTimesRight-->>920607742610156716330279163129164
 * 	Navroz@Navroz-PC MINGW64 ~/eclipseSpaces/workspace/RandomJava/bin (master)
 * 
 * 
 * 
Navroz@Navroz-PC MINGW64 ~/eclipseSpaces/workspace/RandomJava/bin (master)
$ java org.bawaweb.math.HighNum 7848558510558566 times 988597489936585154
LeftTimesRight-->>920607742610156716330279163129164

Navroz@Navroz-PC MINGW64 ~/eclipseSpaces/workspace/RandomJava/bin (master)
$ java org.bawaweb.math.HighNum 15448855851055856677 times 3398859748993658515466
LeftTimesRight-->>41386382220046799509720727950312683866482

Navroz@Navroz-PC MINGW64 ~/eclipseSpaces/workspace/RandomJava/bin (master)
$

 * 
 * 
 */