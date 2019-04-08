/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 * 
 * 45 seconds 	to 1 billion		(1,000,000,000)
 * ~12.5 HOURS 	to 1 trillion 		(1,000,000,000,000)
 *
 */
public class JustCounting {

	private static final int HUNDRED	=	100;
	private static final int THOUSAND	=	1000;
	private static final int TEN_K		=	10000;
	private static final int HUNDRED_K	=	100000;
	private static final int MILLION	=	1000000;
	private static final int TEN_M		=	10000000;
	private static final int HUNDRED_M	=	100000000;
	private static final int BILLION	=	1000000000;
	
	private static final long TEN_B		=	10000000000l;
	private static final long HUNDRED_B	=	100000000000l;
	private static final long TRILLION	=	1000000000000l;
	
	public static void main(String[] args) {
		long h 	=  	-1l;
		long t	=	-1l;
		long tk	=	-1l;
		long hk	=	-1l;
		long m	=	-1l;
		long tm	=	-1l;
		long hm	=	-1l;
		long b	=	-1l;
		
		long tb	=	-1l;
		long hb	=	-1l;
		long tr	=	-1l;
		
		
		
		
		long now = System.currentTimeMillis();
		System.out.println("Now is "+now);
		
		
		for(long i = 1l; i <= TRILLION; i++) {
			if( i == HUNDRED )
				h	=	System.currentTimeMillis();
			if( i == THOUSAND )
				t	=	System.currentTimeMillis();
			if( i == TEN_K )
				tk	=	System.currentTimeMillis();
			if( i == HUNDRED_K )
				hk	=	System.currentTimeMillis();
			if( i == MILLION )
				m	=	System.currentTimeMillis();
			if( i == TEN_M )
				tm	=	System.currentTimeMillis();
			if( i == HUNDRED_M )
				hm	=	System.currentTimeMillis();
			if( i == BILLION )
				b 	=	System.currentTimeMillis();
			
			if( i == TEN_B )
				tb	=	System.currentTimeMillis();
			if( i == HUNDRED_B )
				hb	=	System.currentTimeMillis();
			if( i == TRILLION )
				tr	=	System.currentTimeMillis();
			
		}
		
		System.out.println("Time to count to " + HUNDRED 	+ " is " + (h-now) 	+ " milliseconds");
		System.out.println("Time to count to " + THOUSAND 	+ " is " + (t-now) 	+ " milliseconds");
		System.out.println("Time to count to " + TEN_K 		+ " is " + (tk-now) + " milliseconds");
		System.out.println("Time to count to " + HUNDRED_K 	+ " is " + (hk-now) + " milliseconds");
		System.out.println("Time to count to " + MILLION 	+ " is " + (m-now) 	+ " milliseconds");
		System.out.println("Time to count to " + TEN_M 		+ " is " + (tm-now) + " milliseconds");
		System.out.println("Time to count to " + HUNDRED_M 	+ " is " + (hm-now) + " milliseconds");
		System.out.println("Time to count to " + BILLION 	+ " is " + (b-now) 	+ " milliseconds");
		
		System.out.println("Time to count to " + TEN_B 		+ " is " + (tb-now) + " milliseconds");
		System.out.println("Time to count to " + HUNDRED_B 	+ " is " + (hb-now) + " milliseconds");
		System.out.println("Time to count to " + TRILLION 	+ " is " + (tr-now) + " milliseconds");
		

	}

}
