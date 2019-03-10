/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 *
 */
public class MakeItCrash {


	public MakeItCrash() throws Exception {
		throw new Exception();
		
	}
	
	public static void main(String[] args) throws Exception {
		MakeItCrash mic = new MakeItCrash();

	}

}

/**
OUTPUT
for
------------------------------
public static void main(String[] args) throws Exception {
    java.util.List aList = new java.util.ArrayList();
		
		while( true )
				aList.add(1);
}
------------------------------
Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
at java.util.Arrays.copyOf(Arrays.java:3210)
at java.util.Arrays.copyOf(Arrays.java:3181)
at java.util.ArrayList.grow(ArrayList.java:261)
at java.util.ArrayList.ensureExplicitCapacity(ArrayList.java:235)
at java.util.ArrayList.ensureCapacityInternal(ArrayList.java:227)
at java.util.ArrayList.add(ArrayList.java:458)
at org.bawaweb.MakeItCrash.main(MakeItCrash.java:16)
=============================================================================
for
----------------------------------------------------------


	public MakeItCrash() throws Exception {
		throw new Exception();
		
	}
	
	public static void main(String[] args) throws Exception {
		MakeItCrash mic = new MakeItCrash();

	}

----------------------------------------------------------
Exception in thread "main" java.lang.Exception
	at org.bawaweb.MakeItCrash.<init>(MakeItCrash.java:14)
	at org.bawaweb.MakeItCrash.main(MakeItCrash.java:19)
=================================================================================	
**/