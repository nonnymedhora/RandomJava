/**
 * 
 */
package org.bawaweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bawaweb.ComplexExpressionTransformer.ComplexNumber;
/**
 * @author Navroz
 *
 */
public class ComplexExpressionTransformer {
	 
	/*	ClassicJulia
	 * z^4 + z^3/(z-1) + z^2/(z^3 + 4*z^2 + 5) + c
	 * */
	
	final String START_BRACKET = "(";
	final String END_BRACKET = ")";
	final String WHITESPACE = " ";
	final String EMPTY = "";

	final ComplexNumber i = new ComplexNumber(0.0, 1.0);

	final ComplexNumber one = new ComplexNumber(1.0, 0.0);
	final ComplexNumber two = new ComplexNumber(2.0, 0.0);
	final ComplexNumber three = new ComplexNumber(3.0, 0.0);
	final ComplexNumber four = new ComplexNumber(4.0, 0.0);
	final ComplexNumber five = new ComplexNumber(5.0, 0.0);
	final ComplexNumber six = new ComplexNumber(6.0, 0.0);
	final ComplexNumber seven = new ComplexNumber(7.0, 0.0);
	final ComplexNumber eight = new ComplexNumber(8.0, 0.0);
	final ComplexNumber nine = new ComplexNumber(9.0, 0.0);
	final ComplexNumber ten = new ComplexNumber(10.0, 0.0);

	final ComplexNumber realOne = new ComplexNumber(1.0, 0.0);
	final ComplexNumber realTwo = new ComplexNumber(2.0, 0.0);
	final ComplexNumber realThree = new ComplexNumber(3.0, 0.0);
	final ComplexNumber realFour = new ComplexNumber(4.0, 0.0);
	final ComplexNumber realFive = new ComplexNumber(5.0, 0.0);
	final ComplexNumber realSix = new ComplexNumber(6.0, 0.0);
	final ComplexNumber realSeven = new ComplexNumber(7.0, 0.0);
	final ComplexNumber realEight = new ComplexNumber(8.0, 0.0);
	final ComplexNumber realNine = new ComplexNumber(9.0, 0.0);
	final ComplexNumber realTen = new ComplexNumber(10.0, 0.0);

	final ComplexNumber imagOne = new ComplexNumber(0.0, 1.0);
	final ComplexNumber imagTwo = new ComplexNumber(0.0, 2.0);
	final ComplexNumber imagThree = new ComplexNumber(0.0, 3.0);
	final ComplexNumber imagFour = new ComplexNumber(0.0, 4.0);
	final ComplexNumber imagFive = new ComplexNumber(0.0, 5.0);
	final ComplexNumber imagSix = new ComplexNumber(0.0, 6.0);
	final ComplexNumber imagSeven = new ComplexNumber(0.0, 7.0);
	final ComplexNumber imagEight = new ComplexNumber(0.0, 8.0);
	final ComplexNumber imagNine = new ComplexNumber(0.0, 9.0);
	final ComplexNumber imagTen = new ComplexNumber(0.0, 10.0);
	
	final List<String> tokens = getTokens();
	private Map<String,String> tokenFuncMap = getTokenFuncMap();

	public ComplexExpressionTransformer(){
	}
	
	private ComplexNumber generateComplexNumber(String ex) {
		return new ComplexNumber(ex);
		
	}

	public ComplexNumber transformComplexExpressions(String cEx) {
		if (cEx.indexOf('z') > -1 || cEx.indexOf('Z') > -1) {

			if (cEx.indexOf(START_BRACKET) > -1) {
				return this.groupAndTransform(cEx);
			} else {
				return this.transform(cEx);
			}
		}
		return null;
	}

	
	private ComplexNumber transform(String cEx) {
		String[] exes = cEx.split(WHITESPACE);
		
		//simplest	z+1
		if (exes.length == 3) {
			String left = exes[0];
			String right = exes[2];
			String op = exes[1];

			if (left.startsWith("e")) {
				return processForExponent(cEx);
			}
			if (this.isVariable(left)) {
				if(this.tokens.contains(op)){
					String tokOp = this.tokenFuncMap.get(op);
				}
			}
			if (this.isVariable(right)) {

			}
		}
		return null;
	}

	private ComplexNumber processForExponent(String cEx) {
		// TODO Auto-generated method stub
		return null;
	}

	private ComplexNumber groupAndTransform(String cEx) {
		if (!this.checkWellFormed(cEx)) {
			return null;
		}
		ComplexNumber[] groups = this.getGroups(cEx);
		String[] operations = this.getOps(cEx);

		return this.process(groups, operations);
	}

	private boolean checkWellFormed(String cEx) {
		return this.bracketsWellFormed(cEx);
	}

	private boolean bracketsWellFormed(String c) {
		int startBrCount = 0; // tracks "("
		int endBrCount = 0; // tracks ")"

		String startBr = START_BRACKET;
		String endBr = END_BRACKET;

		for (int i = 0; i < c.length(); i++) {
			if (startBr.equals(String.valueOf(c.charAt(i)))) {
				startBrCount += 1;
			}

			if (endBr.equals(String.valueOf(c.charAt(i)))) {
				endBrCount += 1;
			}
		}

		return startBrCount == endBrCount;
	}

	private ComplexNumber process(ComplexNumber[] groups, String[] operations) {
		// TODO Auto-generated method stub
		return null;
	}

	private String[] getOps(String cEx) {
		// TODO Auto-generated method stub
		return null;
	}

	private ComplexNumber[] getGroups(String cEx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*private List<String> getOperations(){
		List<String> t = new ArrayList<String>();
		//Token				//	ComplexNumber operation
		t.add("+");			//	plus
		t.add("-");			//	minus
		t.add("*");			//	times
		t.add("/");			//	divides
		return t;
		
	}*/
	
	private Map<String, String> getTokenFuncMap() {
		Map<String, String> tfMap = new HashMap<String, String>();

		// Token // ComplexNumber operation
		tfMap.put("+", "plus");
		tfMap.put("-", "minus");
		tfMap.put("*", "times");
		tfMap.put("/", "divides");
		tfMap.put("|", "abs");

		tfMap.put("^", "power");
		tfMap.put("sqrt", "sqroot");
		// tfMap.put("1/,"reciprocal");
		// tfMap.put("1.0/,"reciprocal");
		tfMap.put("e", "exp");
		tfMap.put("ln", "ln"); // 4 log(base e)
		tfMap.put("log", "log"); // TODO 4 log(base 10)

		tfMap.put("sin", "sin");
		tfMap.put("cos", "cos");
		tfMap.put("tan", "tan");
		tfMap.put("asin", "inverseSine");
		tfMap.put("acos", "inverseCosine");
		tfMap.put("atan", "inverseTangent");
		tfMap.put("atan2", "inverseTangent");

		return tfMap;
	}

	private List<String> getTokens() {
		List<String> t = new ArrayList<String>();
		//Token				//	ComplexNumber operation
		t.add("+");			//	plus
		t.add("-");			//	minus
		t.add("*");			//	times
		t.add("/");			//	divides
		t.add("|");			//	abs
		
		t.add("^");			//	power
		t.add("sqrt");		//	sqroot
//		t.add("1/");		//	reciprocal	
//		t.add("1.0/");		//	reciprocal		
		t.add("e");			//	exp
		t.add("ln");		//	ln	4	log(base e)
		t.add("log");		//	log	//TODO	4	log(base 10)
		
		t.add("sin");		//	sin
		t.add("cos");		//	cos
		t.add("tan");		//	tan		
		t.add("asin");		//	inverseSine
		t.add("acos");		//	inverseCosine
		t.add("atan");		//	inverseTangent
		t.add("atan2");		//	inverseTangent
		
		return t;
	}
	
	
	private boolean isVariable(String ex){
		return ex.toUpperCase().equalsIgnoreCase("z");
	}


	//convert	0.2-7.5i	//	5.7	//	-1.1i	//-4.3-0.7i
	//3.1+i	//-1.8+0.8i	//	-0.8
	//	NO	leading	plus	SO	+0.8+1.5i	invalid
	//						but	0.8+1.5i	valid
	//	No stars for i		SO	1.1*i		invalid
	//						but	1.1i		valid
	public static void main(String[] args) {
		ComplexExpressionTransformer cxT = new ComplexExpressionTransformer();
//		
//		/*
//		String t1 = "0.2-7.5i";
//		String t1 = "0.2-7.5i";
//		String t1 = "0.2-7.5i";*/
//		String t1 = "0.2 - 7.5i";
//		String t7 = "0.57";
//		String t6 = "-1.1 i";
//		String t5 = "-4.3 - 0.7i";
//		String t4 = "3.1 +i";
//		String t3 = "-1.8 +0.8i";
//		String t2 = "-0.8";
//		
//		System.out.println("t1 = "+t1);
//		System.out.println(cxT.generateComplexNumber(t1));
//
//		System.out.println("t2 = "+t2);
//		System.out.println(cxT.generateComplexNumber(t2));
//		
//
//		System.out.println("t3 = "+t3);
//		System.out.println(cxT.generateComplexNumber(t3));
//		
//
//		System.out.println("t4 = "+t4);
//		System.out.println(cxT.generateComplexNumber(t4));
//
//		System.out.println("t5 = "+t5);
//		System.out.println(cxT.generateComplexNumber(t5));
//		
//
//		System.out.println("t6 = "+t6);
//		System.out.println(cxT.generateComplexNumber(t6));
//		
//
//		System.out.println("t7 = "+t7);
//		System.out.println(cxT.generateComplexNumber(t7));
//		
//		
//		FullCalculator fc = cxT.calculator();
//		fc.processInput("3 / (( 2 + 3 ) * ( 4 - 6 ))");
//	
//	//////////////////////////////
//		
//		Expr ex = cxT.expr();
////		ex.parse("sin(2.3*x-7.1) - cos(7.1*x-2.3)");//
//		ex.parse("3 / (sin( 0.2 + 0.3 ) * ( 4 - 6 ))");
////		ex.getDefinition();
//		
	//////////////////////////////	
		
		FunctionEvaluator funcEval = cxT.evaluator();
		String funcZ =  "sin(2.3*z-7.1) - cos(7.1*z-2.3)";
		String z = "1.5 + 0.7i";
		
		System.out.println(funcEval.evaluate(funcZ,z));
		//vaSelf
		
		ComplexNumber zz = cxT.doSelfEval(z);
		System.out.println("SELFEVAL\n"+zz);
		
	}
	
	
	private ComplexNumber doSelfEval(String z) {
		ComplexNumber zz = new ComplexNumber(z);
		zz=(new ComplexNumber(2.3).times(zz).minus(new ComplexNumber(7.1))).sine().minus(new ComplexNumber(7.1).times(zz).minus(new ComplexNumber(2.3)).cosine());
		return zz;
	}

	private Expr expr() {
		return new Expr();//"3 / (sin( 0.2 + 0.3 ) * ( 4 - 6 ))"
	}

	private FullCalculator calculator() {
		return new FullCalculator();
	}

	private FunctionEvaluator evaluator() {
		return new FunctionEvaluator();
	}


	class ComplexNumber {
		final String I = "i";
		final double real;		// the real part
		final double imaginary;	// the imaginary part
		
		// return the real or imaginary part
		public double real(){
			return real;
		}
		
		public double imaginary(){
			return imaginary;
		}
		/**
		 * @param r	-	real part
		 * @param i	-	imaginary part
		 */
		public ComplexNumber(double r, double i) {
			super();
			this.real = r;
			this.imaginary = i;
		}
		
		public ComplexNumber(double r) {
			super();
			this.real = r;
			this.imaginary = 0.0;
		}
		
		
		//convert	0.2-7.5i	//	5.7	//	-1.1i	//-4.3-0.7i
		//3.1+i	//-1.8+0.8i	//	-0.8
		//	1.1i
		//	NO	leading	plus	SO	+0.8+1.5i	invalid
		//						but	0.8+1.5i	valid
		//	No stars for i		SO	1.1*i		invalid
		//						but	1.1i		valid
		public ComplexNumber(String c) {
			System.out.println("c[1] is "+c);
			c = c.replaceAll(WHITESPACE,EMPTY);
			System.out.println("c[2] is "+c);
			
			final String plus = "+";
			final String minus = "-";
			final String[] EMPTYARR = new String[] {};

			int i_index = c.indexOf(I);					//System.out.println("i_index="+i_index);
			final int plusIndex = c.indexOf(plus);		//System.out.println("plusIndex"+plusIndex);
			final int minusIndex = c.indexOf(minus);	//System.out.println("minusIndex="+minusIndex);

			boolean hasPlus = plusIndex > -1;			//System.out.println("hasPlus is "+hasPlus);
			boolean hasMinus = minusIndex > -1;			//System.out.println("hasMinus is "+hasMinus);

			int plusCount = 0;
			for (int i = 0; i < c.length(); i++) {
				if (plus.equals(String.valueOf(c.charAt(i)))) {
					plusCount += 1;
				}
			}											//System.out.println("plusCount is "+plusCount);

			int minusCount = 0;
			for (int i = 0; i < c.length(); i++) {
				if (minus.equals(String.valueOf(c.charAt(i)))) {
					minusCount += 1;
				}
			}											//System.out.println("minusCount is "+minusCount);

			if (i_index == -1) {
				this.real = Double.parseDouble(c);
				this.imaginary = 0.0;
			} else if (minusCount == 0) {
				if (plusCount == 1) {
					String realPart = c.substring(0, plusIndex);
					String imaginaryPart = c.substring(plusIndex);
					this.real = Double.parseDouble(realPart);
					if (plusIndex + 1 == i_index/* && imaginaryPart.equals(I)*/) {
						this.imaginary = 1.0;
					} else {
						this.imaginary = Double.parseDouble(imaginaryPart.replace(I, EMPTY));
					}
				} else {
System.out.println("WHYr v here?");
this.real=Double.NaN;this.imaginary=Double.NaN;
				}
			} else {
				if (plusCount == 1) {
					String realPart = c.substring(0, plusIndex);
					String imaginaryPart = c.substring(plusIndex);
					this.real = Double.parseDouble(realPart);
					if (plusIndex + 1 == i_index && imaginaryPart.equals(I)) {
						this.imaginary = 1.0;
					} else {
						this.imaginary = Double.parseDouble(imaginaryPart.replace(I, EMPTY));
					}
				} else {
					if (!c.startsWith(minus)) {
						String realPart = c.substring(0, minusIndex);
						String imaginaryPart = c.substring(minusIndex);
						this.real = Double.parseDouble(realPart);
						if (minusIndex + 1 == i_index && imaginaryPart.equals(I)) {
							this.imaginary = 1.0;
						} else {
							this.imaginary = Double.parseDouble(imaginaryPart.replace(I, EMPTY));
						} 
					} else {
						String c2 = c.substring(1,c.length()-1);	//remove leading minus
						System.out.println("C2 us "+c2);
						final int c2MinusIndex = c2.indexOf(minus);
						if (c2MinusIndex>0) {
							this.real = Double.parseDouble(c2.substring(0, c2MinusIndex)) * -1;
							this.imaginary = Double.parseDouble(c2.substring(c2MinusIndex).replace(I, EMPTY));
						}else{
							this.real = Double.parseDouble(c.replace(I,EMPTY));
							this.imaginary=0;
						}
					}
				}
			}
			
			
//			
//			/////////////////////////////////////////////////////////////////
//			//rem1
//			
//			
//			String[] plusTokens = hasPlus?c.split(plus):EMPTYARR;
//			String[] minusTokens = hasMinus?c.split(minus):EMPTYARR;
//			
//			for (int i = 0; i < plusTokens.length; i++) {
//				System.out.println("plustoken [" + i + "] is " + plusTokens[i]);
//			}
//			for (int i = 0; i < minusTokens.length; i++) {
//				System.out.println("minustoken [" + i + "] is " + minusTokens[i]);
//			}
//
//			if (plusTokens.length > 0) {
//				
//				this.real = Double.parseDouble(plusTokens[0]);
//				this.imaginary = Double.parseDouble(plusTokens[1].replace(I,EMPTY));
//			} else if (minusTokens.length > 0) {				
//				if(minusTokens.length==1){
//					/*if (!c.startsWith(minus)) {*/
//					this.real = 0;
//					this.imaginary = Double.parseDouble(minusTokens[0].replace(I, EMPTY));
//					/*}else{
//						this.real=Double.parseDouble(minusTokens[0])*-1;
//					}*/
//				}else{
//					if (minusTokens[0]!=EMPTY) {
//						this.real = Double.parseDouble(minusTokens[0]);
//						this.imaginary=Double.parseDouble(minusTokens[1].replace(I,EMPTY))*-1;
//					}else{
//						this.real=0;
//						this.imaginary=Double.parseDouble(minusTokens[1].replace(I,EMPTY))*-1;
//					}
//				}
//			} else {
//				if(c.indexOf(I)>-1){
//					this.real=0;
//					this.imaginary=Double.parseDouble(c.replace(I,EMPTY))*-1;					
//				}else{
//					this.real=Double.parseDouble(c);
//				this.imaginary = 0;
//				}
//			}
//			///////////////////////////////////////////////////////
//			//ends	rem
//			//	tokens-behave-bad
//			

		}
		
		// return a new Complex object whose value is (this * b)
	    public ComplexNumber times(ComplexNumber b) {
	    	ComplexNumber a = this;
	        double real = a.real * b.real - a.imaginary * b.imaginary;
	        double imag = a.real * b.imaginary + a.imaginary * b.real;
	        return new ComplexNumber(real, imag);
	    }
	    
	 // return a new Complex object whose value is (this + b)
	    public ComplexNumber plus(ComplexNumber b) {
	    	ComplexNumber a = this;             // invoking object
	        double real = a.real + b.real;
	        double imag = a.imaginary + b.imaginary;
	        return new ComplexNumber(real, imag);
	    }
	    
	 // return a new Complex object whose value is (this - b)
	    public ComplexNumber minus(ComplexNumber b) {
	    	ComplexNumber a = this;             // invoking object
	        double real = a.real - b.real;
	        double imag = a.imaginary - b.imaginary;
	        return new ComplexNumber(real, imag);
	    }

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {System.out.println("real("+this.real+") imaginary("+this.imaginary+")");
			if (imaginary == 0.0 && real != 0.0)
				return real + "";
			if (real == 0.0 && imaginary != 0.0)
				return imaginary + I;
			if (real < 0.0) {
				if (imaginary < 0.0)
					return real + " - " + (-1*imaginary) + I;
				else
					return real + " + " + imaginary + I;
			} else if (real > 0.0) {
				if (imaginary < 0.0)
					return real + " - " + (-1*imaginary) + I;
				else
					return real + " + " + imaginary + I;
			}
			return real + " + " + imaginary + I;
//			return "ComplexNumber [real=" + real + ", imaginary=" + imaginary + "]";
		}
		
		// See Section 3.3.
	    public boolean equals(Object x) {
	        if (x == null) return false;
	        if (this.getClass() != x.getClass()) return false;
	        ComplexNumber that = (ComplexNumber) x;
	        return (this.real == that.real) && (this.imaginary == that.imaginary);
	    }

	    // See Section 3.3.
	    public int hashCode() {
	        return Objects.hash(real, imaginary);
	    }

		public double abs() {
			return Math.hypot(real, imaginary);
		}
		
		
		/**
		 * https://math.stackexchange.com/questions/44406/how-do-i-get-the-square-root-of-a-complex-number
		 * 
		 * z=c+d*i				c=real,	d=imaginary
		 * 
		 * sqroot(z)=a+b*i
		 * 
		 * a=root((c+root(c^2+d^2))/2) 
		 * b=d/|d|*root((-c+root(c^2+d^2))/2)
		 * 
		 * d/|d|	==> b has same sign as d
		 * 
		 * @return
		 */
		public ComplexNumber sqroot() {
//			System.out.println("Real==="+real+"---Img:=="+imaginary);
			double a = Math.sqrt(Math.abs(real) + ( Math.pow(Math.abs(real), 2.0) + Math.pow(Math.abs(imaginary), 2.0) ) / 2.0);
			double b = Math.sqrt( Math.abs(real /** (-1.0)*/) + Math.sqrt( Math.pow(Math.abs(real), 2.0) + Math.pow(Math.abs(imaginary), 2.0) ) / 2.0);
//System.out.println("a==="+a);
//System.out.println("b==="+b);
			if (real == 0.0 && imaginary != 0.0) {
//				System.out.println("returning___new ComplexNumber(0.0, Math.sqrt(Math.abs(imaginary)));"+new ComplexNumber(0.0, Math.sqrt(Math.abs(imaginary))));
				return new ComplexNumber(0.0, Math.sqrt(Math.abs(imaginary)));
			}
			if (imaginary > 0.0) {
//				System.out.println("returning___new ComplexNumber(a, b);"+new ComplexNumber(a, b));
				return new ComplexNumber(a, b);
			} else if (imaginary < 0.0) {
//				System.out.println("returning___new ComplexNumber(a, b * (-1.0));"+new ComplexNumber(a,( Math.abs(b) * (-1.0))));
			
				return new ComplexNumber(a,( Math.abs(b) * (-1.0)));
			}
//			System.out.println("returning___new ComplexNumber(Math.sqrt(Math.abs(real)), 0.0);"+new ComplexNumber(Math.sqrt(Math.abs(real)), 0.0));
			return new ComplexNumber(Math.sqrt(Math.abs(real)), 0.0);
		}
		
		public ComplexNumber curoot(){
			return sqroot();	//for-now
		}

		
		/**https://en.wikipedia.org/wiki/Complex_logarithm
		//
		//	z=x+y*i
		//	log(z)=ln(root(x^2+y^2))+atan2(y,x)*i
		 * 
		 * @return
		 */
		public ComplexNumber ln() {
			double a = Math.log(Math.sqrt(Math.pow(real, 2.0) + Math.pow(imaginary, 2.0)));
			double b = Math.atan2(imaginary, real);
			return new ComplexNumber(a, b);
		}
		
		
		/*public final ComplexNumber one = new ComplexNumber(1.0, 0.0);*/

	    // return a new Complex object whose value is the complex exponential of this
		// return e ^ z
	    public ComplexNumber exp() {
	        return new ComplexNumber(Math.exp(real) * Math.cos(imaginary), Math.exp(real) * Math.sin(imaginary));
	    }

	    // return a new Complex object whose value is the complex sine of this
	    public ComplexNumber sin() {
	        return new ComplexNumber(Math.sin(real) * Math.cosh(imaginary), Math.cos(real) * Math.sinh(imaginary));
	    }

	    // return a new Complex object whose value is the complex cosine of this
	    public ComplexNumber cos() {
	        return new ComplexNumber(Math.cos(real) * Math.cosh(imaginary), -Math.sin(real) * Math.sinh(imaginary));
	    }

	    // return a new Complex object whose value is the complex tangent of this
	    public ComplexNumber tan() {
	        return sin().divides(cos());
	    }
	    

	    // return a / b
	    public ComplexNumber divides(ComplexNumber b) {
	        ComplexNumber a = this;
	        return a.times(b.reciprocal());
	    }
	 // return a new Complex object whose value is the reciprocal of this
	    public ComplexNumber reciprocal() {
	        double scale = real*real + imaginary*imaginary;
	        return new ComplexNumber(real / scale, -imaginary / scale);
	    }

		public ComplexNumber power(int power) {
			if (power > 0) {
				int iter = 1;
				final ComplexNumber a = this;
				ComplexNumber powered = a;
				for (int i = 1; i < power; i++) {
					powered = powered.times(a);
				}
				return powered;
			} else if (power < 0) {
				final ComplexNumber a = this;
				ComplexNumber powered = a.reciprocal();
				int powAbs = Math.abs(power);
				for (int i = 1; i < powAbs; i++) {
					powered = powered.times(powered);
				}
				return powered;

			}	
			//power==0
			return new ComplexNumber(1.0,0.0);	//	^0==1
		}

		public ComplexNumber sine() {
			double sineR = Math.sin(this.real) * Math.cosh(this.imaginary);
			double sineI = Math.cos(this.real) * Math.sinh(this.imaginary);
			return new ComplexNumber(sineR,sineI);
		}
		
		//arcsin
		public ComplexNumber inverseSine(){
			return new ComplexNumber(1.0, 0.0).divides(this.sine());
		}
		
		public ComplexNumber cosine() {
			// cos(x)*cosh(y) - isin(x)*sinh(y)
			double cosR = Math.cos(this.real) * Math.cosh(this.imaginary);
			double cosI = -Math.sin(this.real) * Math.sinh(this.imaginary);
			return new ComplexNumber(cosR, cosI);
		}
		
		//arccos
		public ComplexNumber inverseCosine(){
			return new ComplexNumber(1.0, 0.0).divides(this.cosine());
		}
		
		public ComplexNumber tangent(){
			return this.sine().divides(this.cosine());
		}
		
		//arctan
		public ComplexNumber inverseTangent(){
			return new ComplexNumber(1.0, 0.0).divides(this.tangent());
		}
		
	}
	
	//I:\MySamsungPhone---all-files\Samsung Galaxy J5\My Documents\Evaluating_arithmetic_expressions.pdf
	class Token {
		public static final int UNKNOWN = -1;
		public static final int NUMBER = 0;
		public static final int OPERATOR = 1;
		public static final int LEFT_PARENTHESIS = 2;
		public static final int RIGHT_PARENTHESIS = 3;

		private int type;
		private double value;
		private char operator;
		private int precedence;

		public Token() {
			type = UNKNOWN;
		}

		public Token(String contents) {
			switch (contents) {
			case "+":
				type = OPERATOR;
				operator = contents.charAt(0);
				precedence = 1;
				break;
			case "-":
				type = OPERATOR;
				operator = contents.charAt(0);
				precedence = 1;
				break;
			case "*":
				type = OPERATOR;
				operator = contents.charAt(0);
				precedence = 2;
				break;
			case "/":
				type = OPERATOR;
				operator = contents.charAt(0);
				precedence = 2;
				break;
			case "(":
				type = LEFT_PARENTHESIS;
				break;
			case ")":
				type = RIGHT_PARENTHESIS;
				break;
			default:
				type = NUMBER;
				try {
					value = Double.parseDouble(contents);
				} catch (Exception ex) {
					type = UNKNOWN;
				}
			}
		}

		public Token(double x) {
			type = NUMBER;
			value = x;
		}

		int getType() {
			return type;
		}

		double getValue() {
			return value;
		}

		int getPrecedence() {
			return precedence;
		}

		Token operate(double a, double b) {
			double result = 0;
			switch (operator) {
			case '+':
				result = a + b;
				break;
			case '-':
				result = a - b;
				break;
			case '*':
				result = a * b;
				break;
			case '/':
				result = a / b;
				break;
			}
			return new Token(result);
		}
		
	} 
	
	
	class TokenStack {
		/** Member variables **/
		private /*Array*/List<Token> tokens;

		/** Constructors **/
		public TokenStack() {
			tokens = new ArrayList<Token>();
		}

		/** Accessor methods **/
		public boolean isEmpty() {
			return tokens.size() == 0;
		}

		public Token top() {
			return tokens.get(tokens.size() - 1);
		}

		/** Mutator methods **/
		public void push(Token t) {
			tokens.add(t);
		}

		public void pop() {
			tokens.remove(tokens.size() - 1);
		}
	}  

	class FullCalculator {

		private TokenStack operatorStack;
		private TokenStack valueStack;
		private boolean error;

		public FullCalculator() {
			operatorStack = new TokenStack();
			valueStack = new TokenStack();
			error = false;
		}

		private void processOperator(Token t) {
			Token A = null, B = null;
			if (valueStack.isEmpty()) {
				System.out.println("Expression error.");
				error = true;
				return;
			} else {
				B = valueStack.top();
				valueStack.pop();
			}
			if (valueStack.isEmpty()) {
				System.out.println("Expression error.");
				error = true;
				return;
			} else {
				A = valueStack.top();
				valueStack.pop();
			}
			Token R = t.operate(A.getValue(), B.getValue());
			valueStack.push(R);
		}

		public void processInput(String input) {
			// The tokens that make up the input
			String[] parts = input.split(" ");
			Token[] tokens = new Token[parts.length];
			for (int n = 0; n < parts.length; n++) {
				tokens[n] = new Token(parts[n]);
			}

			// Main loop - process all input tokens
			for (int n = 0; n < tokens.length; n++) {
				Token nextToken = tokens[n];
				if (nextToken.getType() == Token.NUMBER) {
					valueStack.push(nextToken);
				} else if (nextToken.getType() == Token.OPERATOR) {
					if (operatorStack.isEmpty() || nextToken.getPrecedence() > operatorStack.top().getPrecedence()) {
						operatorStack.push(nextToken);
					} else {
						while (!operatorStack.isEmpty()
								&& nextToken.getPrecedence() <= operatorStack.top().getPrecedence()) {
							Token toProcess = operatorStack.top();
							operatorStack.pop();
							processOperator(toProcess);
						}
						operatorStack.push(nextToken);
					}
				} else if (nextToken.getType() == Token.LEFT_PARENTHESIS) {
					operatorStack.push(nextToken);
				} else if (nextToken.getType() == Token.RIGHT_PARENTHESIS) {
					while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
						Token toProcess = operatorStack.top();
						operatorStack.pop();
						processOperator(toProcess);
					}
					if (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.LEFT_PARENTHESIS) {
						operatorStack.pop();
					} else {
						System.out.println("Error: unbalanced parenthesis.");
						error = true;
					}
				}
			}
			// Empty out the operatorstack at the endoftheinput
			while (!operatorStack.isEmpty() && operatorStack.top().getType() == Token.OPERATOR) {
				Token toProcess = operatorStack.top();
				operatorStack.pop();
				processOperator(toProcess);
			}
			// Print the result if no error has been seen.
			if (error == false) {
				Token result = valueStack.top();
				valueStack.pop();
				if (!operatorStack.isEmpty() || !valueStack.isEmpty()) {
					System.out.println("Expression error.");
				} else {
					System.out.println("The result is " + result.getValue());
				}
			}
		}

	}

	//ends___I:\MySamsungPhone---all-files\Samsung Galaxy J5\My Documents\Evaluating_arithmetic_expressions.pdf
	
	
	
	//https://math.hws.edu › javanotes › source › chapter8 › Expr
	/*
    An object belonging to the class Expr is a mathematical expression that
    can involve:

            -- real numbers such as 2.7, 3, and 12.7e-12
            -- the variable x
            -- arithmetic operators  +,  -,  *,  /,  and  ^ , where
               the last of these represents raising to a power
            -- the mathematical functions sin, cos, tan, sec, csc, cot,
               arcsin, arccos, arctan, exp, ln, log2, log10, abs, and sqrt,
               where ln is the natural log, log2 is the log to the base 2,
               log10 is the log to the base 10, abs is absolute value,
               and sqrt is the square root
            -- parentheses

     Some examples would be:   x^2 + x + 1
                               sin(2.3*x-7.1) - cos(7.1*x-2.3)
                               x - 1
                               42
                               exp(x^2) - 1

     The trigonometric functions work with radians, not degrees.  The parameter
     of a function must be enclosed in parentheses, so that "sin x" is NOT allowed.

     An Expr is constructed from a String that contains its definition.  If an
     error is found in this definition, the constructor will throw an
     IllegalArgumentException with a message that describes the error and
     the position of the error in the string.  After an Expr has been
     constructed, its defining string can be retrieved using the
     method getDefinition().

     The main operation on an Expr object is to find its value, given
     a value for the variable x.  The value is computed by the value() method.
     If the expression is undefined at the given value of x, then the
     number returned by value() method will be the special "non-a-number number",
     Double.NaN.  (The boolean-valued function Double.isNaN(v) can be used to
     test whether the double value v is the special NaN value.  For technical
     reasons, you can't just use the == operator to test for this value.)

	 */

	class Expr {

    //----------------- public interface ---------------------------------------
		public Expr(){}		//me

		/**
		 * Construct an expression, given its definition as a string. This will
		 * throw an IllegalArgumentException if the string does not contain a
		 * legal expression.
		 */
		public Expr(String definition) {
			parse(definition);
		}

		/**
		 * Computes the value of this expression, when the variable x has a
		 * specified value. If the expression is undefined for the specified
		 * value of x, then Double.NaN is returned.
		 * 
		 * @param x
		 *            the value to be used for the variable x in the expression
		 * @return the computed value of the expression
		 */
		public ComplexNumber value(ComplexNumber x) {
			return eval(x);
		}

		/**
		 * Return the original definition string of this expression. This is the
		 * same string that was provided in the constructor.
		 */
		public String toString() {
			return definition;
		}

		// ------------------- private implementation details
		// ----------------------------------

		private String definition; // The original definition of the expression,
									// as passed to the constructor.

		private byte[] code; // A translated version of the expression,
								// containing
								// stack operations that compute the value of
								// the expression.

		private ComplexNumber[] stack; // A stack to be used during the evaluation of
								// the expression.

		private ComplexNumber[] constants; // An array containing all the constants
									// found in the expression.

		private static final byte // values for code array; values >= 0 are
									// indices into constants array
		PLUS = -1, MINUS = -2, TIMES = -3, DIVIDE = -4, POWER = -5, SIN = -6, COS = -7, TAN = -8, COT = -9, SEC = -10,
				CSC = -11, ARCSIN = -12, ARCCOS = -13, ARCTAN = -14, EXP = -15, LN = -16, LOG10 = -17, LOG2 = -18,
				ABS = -19, SQRT = -20, UNARYMINUS = -21, VARIABLE = -22;

		private /*static*/ String[] functionNames = { 
				// names of standard functions, used during parsing
				"sin", "cos", "tan", "cot", "sec", "csc", "arcsin", "arccos", "arctan", "exp", "ln", "log10", "log2",
				"abs", "sqrt" };

		private ComplexNumber eval(ComplexNumber x2) { // evaluate this expression for
												// this value of the variable
			try {
				int top = 0;
				for (int i = 0; i < codeSize; i++) {
					if (code[i] >= 0)
						stack[top++] = constants[code[i]];
					else if (code[i] >= POWER) {
						ComplexNumber y = stack[--top];
						ComplexNumber x = stack[--top];
						ComplexNumber ans = new ComplexNumber(Double.NaN);
						switch (code[i]) {
						case PLUS:
							ans = x.plus( y );
							break;
						case MINUS:
							ans = x.minus( y );
							break;
						case TIMES:
							ans = x.times( y);
							break;
						case DIVIDE:
							ans = x.divides( y );
							break;
						case POWER:
							ans = x.power((int)y.real);//Math.pow(x, y);
							break;
						}
						if (Double.isNaN(ans.real))
							return ans;
						stack[top++] = ans;
					} else if (code[i] == VARIABLE) {
						stack[top++] = x2;//new ComplexNumber(x2);
					} else {
						ComplexNumber x = stack[--top];
						ComplexNumber ans = new ComplexNumber(Double.NaN);
						switch (code[i]) {
							case SIN:
								ans = x.sine();//Math.sin(x);
								break;
							case COS:
								ans = x.cosine();//Math.cos(x);
								break;
							case TAN:
								ans = x.tangent();//Math.tan(x);
								break;
							case COT:
								ans = x.cosine().divides(x.sine());	//	Math.cos(x) / Math.sin(x);
								break;
							case SEC:
								ans = x.cosine().reciprocal();// 1.0 / Math.cos(x);
								break;
							case CSC:
								ans = x.sine().reciprocal();	//1.0 / Math.sin(x);
								break;
							case ARCSIN:
								//if (Math.abs(x.real) <= 1.0)
									ans = x.inverseSine();//Math.asin(x);
								break;
							case ARCCOS:
								//if (Math.abs(x.real) <= 1.0)
									ans = x.inverseCosine();//Math.acos(x);
								break;
							case ARCTAN:
								ans = x.inverseTangent();//Math.atan(x);
								break;
							case EXP:
								ans = x.exp();//Math.exp(x);
								break;
							case LN:
								//if (x.real > 0.0)
									ans = x.ln();//Math.log(x);
								break;
							/*case LOG2:
								if (x > 0.0)
									ans = Math.log(x) / Math.log(2);
								break;
							case LOG10:
								if (x > 0.0)
									ans = Math.log(x) / Math.log(10);
								break;*/
							case ABS:
								ans = new ComplexNumber(x.abs());//Math.abs(x);
								break;
							case SQRT:
								//if (x.real >= 0.0)
									ans = x.sqroot();//Math.sqrt(x);
								break;
							case UNARYMINUS:
								ans = new ComplexNumber(-1.0).times(x);
								break;
						}
						if (Double.isNaN(ans.real)||Double.isNaN(ans.imaginary))
							return ans;
						stack[top++] = ans;

					}
				}
			} catch (Exception e) {
				return new ComplexNumber(Double.NaN);
			}
			if (Double.isInfinite(stack[0].real)||Double.isInfinite(stack[0].imaginary))
				return new ComplexNumber(Double.NaN);
			else
				return stack[0];
		}

		private int pos = 0, constantCt = 0, codeSize = 0; // data for use
															// during parsing

		private void error(String message) { 
			// called when an error occurs during parsing
			throw new IllegalArgumentException("Parse error:  " + message + "  (Position in data = " + pos + ".)");
		}

		private int computeStackUsage() { // call after code[] is computed
			int s = 0; // stack size after each operation
			int max = 0; // maximum stack size seen
			for (int i = 0; i < codeSize; i++) {
				if (code[i] >= 0 || code[i] == VARIABLE) {
					s++;
					if (s > max)
						max = s;
				} else if (code[i] >= POWER)
					s--;
			}
			return max;
		}

		private void parse(String definition) { 
			// Parse the definition and produce all the data that represents the
			// expression internally; can throw IllegalArgumentException
			if (definition == null || definition.trim().equals(""))
				error("No data provided to Expr constructor");
			this.definition = definition;
			code = new byte[definition.length()];
			constants = new ComplexNumber[definition.length()];
			parseExpression();
			skip();
			if (next() != 0)
				error("Extra data found after the end of the expression.");
			int stackSize = computeStackUsage();
			stack = new ComplexNumber[stackSize];
			byte[] c = new byte[codeSize];
			System.arraycopy(code, 0, c, 0, codeSize);
			code = c;
			ComplexNumber[] A = new ComplexNumber[constantCt];
			System.arraycopy(constants, 0, A, 0, constantCt);
			constants = A;
		}

		private char next() { 
			// return next char in data or 0 if data is all used up
			if (pos >= definition.length())
				return 0;
			else
				return definition.charAt(pos);
		}

		private void skip() { // skip over white space in data
			while (Character.isWhitespace(next()))
				pos++;
		}

		// remaining routines do a standard recursive parse of the expression
		private void parseExpression() {
			boolean neg = false;
			skip();
			if (next() == '+' || next() == '-') {
				neg = (next() == '-');
				pos++;
				skip();
			}
			parseTerm();
			if (neg)
				code[codeSize++] = UNARYMINUS;
			skip();
			while (next() == '+' || next() == '-') {
				char op = next();
				pos++;
				parseTerm();
				code[codeSize++] = (op == '+') ? PLUS : MINUS;
				skip();
			}
		}

		private void parseTerm() {
			parseFactor();
			skip();
			while (next() == '*' || next() == '/') {
				char op = next();
				pos++;
				parseFactor();
				code[codeSize++] = (op == '*') ? TIMES : DIVIDE;
				skip();
			}
		}

		private void parseFactor() {
			parsePrimary();
			skip();
			while (next() == '^') {
				pos++;
				parsePrimary();
				code[codeSize++] = POWER;
				skip();
			}
		}

		private void parsePrimary() {
			skip();
			char ch = next();
			if (ch == 'z' || ch == 'Z') {
				pos++;
				code[codeSize++] = VARIABLE;
			} else if (Character.isLetter(ch))
				parseWord();
			else if (Character.isDigit(ch) || ch == '.')
				parseNumber();
			else if (ch == '(') {
				pos++;
				parseExpression();
				skip();
				if (next() != ')')
					error("Expected a right parenthesis.");
				pos++;
			} else if (ch == ')')
				error("Unmatched right parenthesis.");
			else if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^')
				error("Operator '" + ch + "' found in an unexpected position.");
			else if (ch == 0)
				error("Unexpected end of data in the middle of an expression.");
			else
				error("Illegal character '" + ch + "' found in data.");
		}

		private void parseWord() {
			String w = "";
			while (Character.isLetterOrDigit(next())) {
				w += next();
				pos++;
			}
			w = w.toLowerCase();
			for (int i = 0; i < functionNames.length; i++) {
				if (w.equals(functionNames[i])) {
					skip();
					if (next() != '(')
						error("Function name '" + w + "' must be followed by its parameter in parentheses.");
					pos++;
					parseExpression();
					skip();
					if (next() != ')')
						error("Missing right parenthesis after parameter of function '" + w + "'.");
					pos++;
					code[codeSize++] = (byte) (SIN - i);
					return;
				}
			}
			error("Unknown word '" + w + "' found in data.");
		}

		private void parseNumber() {
			String w = "";
			while (Character.isDigit(next())) {
				w += next();
				pos++;
			}
			if (next() == '.') {
				w += next();
				pos++;
				while (Character.isDigit(next())) {
					w += next();
					pos++;
				}
			}
			if (w.equals("."))
				error("Illegal number found, consisting of decimal point only.");
			if (next() == 'E' || next() == 'e') {
				w += next();
				pos++;
				if (next() == '+' || next() == '-') {
					w += next();
					pos++;
				}
				if (!Character.isDigit(next()))
					error("Illegal number found, with no digits in its exponent.");
				while (Character.isDigit(next())) {
					w += next();
					pos++;
				}
			}
			ComplexNumber d = new ComplexNumber(Double.NaN);
			try {
				d = new ComplexNumber(Double.valueOf(w).doubleValue());
			} catch (Exception e) {
			}
			if (Double.isNaN(d.real))
				error("Illegal number '" + w + "' found in data.");
			code[codeSize++] = (byte) constantCt;
			constants[constantCt++] = d;
		}

	} // end class Expr
	
	
	
	//https://math.hws.edu/javanotes/c8/ex4-ans.html
	//uses_Expr_above
	class FunctionEvaluator {
		   
	      String line;      // A line of input read from the user.
	      Expr expression;  // The definition of the function f(x).
	      ComplexNumber x;         // A value of x for which f(x) is to be calculated.
	      ComplexNumber val;       // The value of f(x) for the specified value of x.
	      
	      public FunctionEvaluator(){}
	      
	      public ComplexNumber evaluate(String line,String line2){

	          
	             /* Get the function from the user.  A line of input is read and
	                used to construct an object of type Expr.  If the input line is
	                empty, then the loop will end, and the program will terminate. */
	     
//	             System.out.println("\n\n\nEnter definition of f(x), or press return to quit.");
	             System.out.print("\nf(x) = "+line.trim());
//	             line = TextIO.getln().trim();
			if (line.length() == 0) {
				System.out.println("Err___line");
				return null; // break;
			}
	                
	             try {
	                expression = new Expr(line);
	             }
	             catch (IllegalArgumentException e) {
	                    // An error was found in the input.  Print an error
	                    //    message and go back to the beginning of the loop.
	                System.out.println("Error!  The definition of f(x) is not valid.");
	                System.out.println(e.getMessage());
//	                continue;
	             }
	             
	             /* Read values of x from the user, until the user presses return.
	                If the user's input is not a legal number, print an error message.
	                Otherwise, compute f(x) and print the result. */
	             
//	             System.out.println("\nEnter values of x where f(x) is to be evaluated.");
//	             System.out.println("Press return to end.");
	             
//	             while (true) {
	                System.out.print("\nx = ");
//	                line2 = "2.5";		//TextIO.getln().trim();
			if (line2.length() == 0) {
				System.out.println("Err___line2");
				return null; // break;
			}
	                try {
	                   x = new ComplexNumber(line2);//Double.parseDouble(line2);
	                }
	                catch (NumberFormatException e) {
	                   System.out.println("\"" + line2 + "\" is not a legal number.");
//	                   continue;
	                }
	                val = expression.value(x);
	                if (Double.isNaN(val.real))
	                   System.out.println("f(" + x + ") is undefined.");
	                else
	                   System.out.println("f(" + x + ") = " + val);
//	             }  // end while
	             return val;
	          
//	          System.out.println("\n\n\nOK.  Bye for now.");
	      }
	}
	
}
