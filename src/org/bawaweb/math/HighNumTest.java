package org.bawaweb.math;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HighNumTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNumString() {
		final String aStr = "844858";
		final String bStr = "844.66858";
		HighNum n = new HighNum( aStr );
		assert( aStr.equals(n.getNumString()) );

		n = new HighNum( bStr );
		assert ( bStr.equals(n.getNumString()) );
	}
	
	@Test
	public void testIsGreaterThan() {
		String a = "8876";
		String b = "2212";
		
		HighNum aNum = new HighNum(a);
		HighNum bNum = new HighNum(b);
		
		assert( aNum.isGreaterThan( bNum ) );
		
		a = "5574";
		b = "5509";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.isGreaterThan(bNum));
		

		a = "-5574";
		b = "5509";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (! aNum.isGreaterThan(bNum));
		
		a = "5574";
		b = "-5509";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.isGreaterThan(bNum));

		
		a = "544444574";
		b = "5509";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.isGreaterThan(bNum));

		
		a = "5574";
		b = "566666666509";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (! aNum.isGreaterThan(bNum));

		
		a = "578878888.8574";
		b = "56666666.6509";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.isGreaterThan(bNum));

		
		a = "5574";
		b = "5509";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.isGreaterThan(bNum));
		
		
		
	}

	@Test
	public void testIsNegativeDecimal() {
		final String aStr = "-98654.008";
		final String bStr = "-456765";
		
		HighNum n = new HighNum( aStr );
		assert( n.isNegativeDecimal() );
		
		n = new HighNum( bStr );
		assert(! n.isNegativeDecimal() );
	}

	@Test
	public void testIsNegative() {
		final String aStr = "-98654.008";
		final String bStr = "-456765";
		
		HighNum n = new HighNum( aStr );
		assert( n.isNegative() );
		
		n = new HighNum( bStr );
		assert( n.isNegative() );
	}
	
	@Test
	public void testIsDecimal() {
		final String aStr = "-98654.008";
		final String bStr = "-456765";
		
		HighNum n = new HighNum( aStr );
		assert( n.isDecimal() );
		
		n = new HighNum( bStr );
		assert(! n.isDecimal() );
	}


	@Test
	public void testMinus() {
		String a = "8876";
		String b = "2212";
		
		HighNum aNum = new HighNum(a);
		HighNum bNum = new HighNum(b);
		
		assert (aNum.minus(bNum).getNumString().equals("6664"));

		a = "2212";
		b = "8876";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.minus(bNum).getNumString().equals("-6664"));

		a = "2222";
		b = "8888";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.minus(bNum).equals(new HighNum("-6666")));

		a = "154.485";
		b = "88.88";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
//		System.out.println("aNum.minus(bNum)---"+aNum.minus(bNum).getNumString());
		assert (aNum.minus(bNum).equals(new HighNum("65.605")));
		
		a = "154.485";
		b = "148.188";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.minus(bNum).equals(new HighNum("6.297")));


		a = "221";
		b = "8876";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.minus(bNum).equals(new HighNum("-8655")));


		a = "2214";
		b = "8876";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.minus(bNum).getNumString().equals("-6662"));
		
		
	}

	@Test
	public void testPlus() {
		String a = "8876";
		String b = "2212";
		
		HighNum aNum = new HighNum(a);
		HighNum bNum = new HighNum(b);
		
		assert( aNum.plus( bNum ).getNumString().equals("11088") );

		a = "876543234567.8765456";
		b = "9876.34565445656";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);		
		assert( aNum.plus( bNum ).getNumString().equals("876543244444.22220005656") );
		
		a = "99876.543234567";
		b = "9876.94";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);		
		assert( aNum.plus( bNum ).getNumString().equals("109753.483234567") );
		
		a = "876543234";
		b = "2345649876.34565445656";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);		
		assert( aNum.plus( bNum ).getNumString().equals("3222193110.34565445656") );
		
		a = "876543234";
		b = "2345649876.34565445656";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);		
		assert( aNum.plus( bNum ).equals(new HighNum("3222193110.34565445656")) );
		
		a = "987659";
		b = "912349";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert( aNum.plus( bNum ).equals(new HighNum("1900008")) );
		
		a = "815581167";
		b = "999978999999999";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert( aNum.plus( bNum ).equals(new HighNum("999979815581166")) );
		
		a = "122344";
		b = "1234";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert( aNum.plus( bNum ).equals(new HighNum("123578")) );
		
		a = "14141419999999994";
		b = "878787124558";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert( aNum.plus( bNum ).equals(new HighNum("14142298787124552")) );
		
		a = "11.25";
		b = "88.75";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert( aNum.plus( bNum ).equals(new HighNum("100")) );
		
		a = "1.25";
		b = "10.75";		
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert( aNum.plus( bNum ).equals(new HighNum("12")) );
		
		
	}

	@Test
	public void testTimes() {
		String a = "8876";
		String b = "2212";
		
		HighNum aNum = new HighNum(a);
		HighNum bNum = new HighNum(b);		
		assert( aNum.times( bNum ).getNumString().equals("19633712") );
		
		a = "270000089";
		b = "4573";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("1234710406997")));
		
		a = "876543234567.8765456";
		b = "9876.34565445656";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("8657043965667744.668319402538059136")));

		a = "876543234567.8765456";
		b = "9876.34565445656";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("8657043965667744.668319402538059136")));

		a = "122344";
		b = "1234";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("150972496")));

		a = "270000089";
		b = "4573";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("1234710406997")));

		a = "27.0000089";
		b = "45.73";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("1234.710406997")));

		a = "561.9000089";
		b = "40005.0703";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("22478849.35761512567")));
		
		a = "2790.0000089";
		b = "45.88973";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("128032.347108418597")));
		
		a = "11.25";
		b = "88.75";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("998.4375")));
		
		a = "1.20";
		b = "0.50";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("0.6")));
		
		a = "12345679";
		b = "45";
		aNum = new HighNum(a);
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("555555555")));
		

		b = "54";
		bNum = new HighNum(b);
		assert (aNum.times(bNum).equals(new HighNum("666666666")));
	}

}
