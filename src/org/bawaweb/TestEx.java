/**
 * 
 */
package org.bawaweb;

/**
 * @author Navroz
 *
 */
public class TestEx {

	public static void main(String[] args) throws Exception {
		try {
			try {
				throw new Exception ("1");
			} catch (Exception e) {
				throw new Exception ("2");
			}
		} finally  {
			throw new Exception ("3");
		}

	}

}
/*
 * OUTPUT
Exception in thread "main" java.lang.Exception: 3
	at org.bawaweb.TestEx.main(TestEx.java:20)

 * 
 **************/
