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
	private static final char ZERO = '0';
//	private static final char EMPTY = "".charAt(0);//".";
	private final String numString;
	private boolean isDebug = false;//true;//
	private boolean isDecimal = false;

	public HighNum(final String string) {
		this.numString = string;
		
		this.isDecimal = string.contains(new Character(DOT).toString());
		
		if (this.isDebug) {
			System.out.println("for " + this.numString + "  decimal==" + this.isDecimal);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isDecimal ? 1231 : 1237);
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
		if (numString == null) {
			if (other.numString != null)
				return false;
		} else if (!numString.equals(other.numString))
			return false;
		return true;
	}

	public String getNumString() {
		return this.numString;
	}
	
	private boolean containsDecimal(HighNum a, HighNum b) {
		return (a.isDecimal || b.isDecimal);
	}

	private HighNum plusDecimals(final HighNum aNum, final HighNum bNum) {
		return this.plusDecimals(aNum, bNum, 0);
	}

	/**
	 * Here either a or b or both are decimal
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
		return new HighNum(majorSum.getNumString() + DOT + minorSum.getNumString());

	}

	private int getDotPosition(String dString) {
		for (int i = 0; i < dString.length(); i++) {
			if (DOT == dString.charAt(i))
				return i;
		}
		return dString.length();
	}

	private HighNum plus(final HighNum aNum) {
		return this.plus(aNum, 0);

	}

	private HighNum plus(final HighNum aNum, int carryOver/* = 0*/) {
		if (this.containsDecimal(this, aNum)) {
			return this.plusDecimals(this, aNum, carryOver );
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

		numSumStr = this.getNumSumStr(sumStack);
		if (this.isDebug) {
			System.out.println("numSumStr===>>" + numSumStr);
		}

		return new HighNum(numSumStr);
	}

	private HighNum multiplyDecimals(HighNum aNum, HighNum bNum, int carryOver/* = 0*/) {
		String aStr = aNum.getNumString();
		int aDotPosn = aStr.indexOf(DOT);
		int minorALngth = aStr.substring(aDotPosn + 1).length();
		
		String bStr = bNum.getNumString();
		int bDotPosn = bStr.indexOf(DOT);
		int minorBLngth = bStr.substring(bDotPosn + 1).length();
		
		/**/

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
			System.out.println("multStr===="+multStr);
		
		int multDotPosn = aDotPosn + bDotPosn - 1;
		String mString = multStr.substring(0, multDotPosn + 1) + DOT + multStr.substring(multDotPosn + 1);
		
		return new HighNum(mString);
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
	
	private HighNum times(final HighNum aNum) {
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

	private String getNumSumStr(final Stack<Integer> sStack) {
		if (this.isDebug) {
			System.out.println("In getNumSumStr");
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
//		int p = 7;
//		System.out.println(new Integer(p).toString().charAt(0));
		if (args.length == 0) {
			/***********************************************/
			/*
			 * int p = 27; int d = p%10; int m = p/10;
			 * System.out.println("p==="+p+", p%10="+d+", p/10=="+m);
			 */
			
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

			left =  new HighNum("27.0000089"); //XXX	12347.10406997
			right = new HighNum("45.73");//1234.710406997
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());

			left =  new HighNum("561.9000089"); //XXX	224788493.5761512567
			right = new HighNum("40005.0703");//22478849.35761512567
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());

			left =  new HighNum("2790.0000089"); //XXX	1280323.47108418597
			right = new HighNum("45.88973");//128032.347108418597
			System.out.println(left.getNumString()+" times " +right.getNumString()+" == "+ left.times(right).getNumString());
			
		} else {
			if (args.length == 3) {
				HighNum left = new HighNum(args[0]);
				String op = args[1];
				HighNum right = new HighNum(args[2]);
				
				switch(op) {
				case	"plus":	System.out.println("LeftPlusRight-->>" + left.plus(right).getNumString());
				break;
				case	"times":	System.out.println("LeftTimesRight-->>" + left.times(right).getNumString());
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