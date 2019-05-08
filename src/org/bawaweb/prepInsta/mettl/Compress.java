/**
 * 
 */
package org.bawaweb.prepInsta.mettl;

/**
 * @author Navroz
 * 
 * https://prepinsta.com/mettl-coding-questions-3/
 * 
 * 
 *  Given a function –	
 *  char *CompressString(char* str);

	The function accepts a string as an argument that may contain repetitive characters. 
	Implement the function to modify and return the input string, 
	such that each character once, along with the count of consecutive occurrence. 
	
	Do not append count if the character occurs only once.

	Note – 
    The string will only contain lowercase English Alphabets
    If you have to manipulate the input string in place you cant use another string

	Assumption – 
	No character will occur consecutively more than 9 times.
	
	Example – 

	Input – aaaaabbbccccccccdaa
	
	OutPut – a4b3c8da2
 *
 */
public class Compress {
	
	public static String compressString(final String aString) {
		if (aString == null) {
			return null;
		}
		if (aString.length() == 1) {
			return aString;
		} else {
			char lastChar = aString.charAt(0);
			int count = 1;

			String retString = String.valueOf(lastChar);

			for (int i = 1; i < aString.length(); i++) {
				char strChar = aString.charAt(i);

				if (strChar == lastChar) {
					count += 1;
				} else {
					if (count >= 2)
						retString += String.valueOf(count) + String.valueOf(strChar);
					else
						retString += String.valueOf(strChar);

					count = 1;
					lastChar = strChar;
				}

				if (i == aString.length() - 1) {
					if (count >= 2) {
						retString += String.valueOf(count);
					} else {
						retString += String.valueOf(strChar);
					}
				}

//				System.out.println("i is " + i + " and strChar is " + strChar + " and count is " + count + " and lastChar is " + lastChar + " and retStr is " + retString + "\n-------");
			}

			return retString;
		}
	}

	public static void main(String[] args) {
		String test = "aaaabbbccccccccdaa";
		System.out.println("For test " + test + "\n compressedString is " + compressString(test));
		String test1 = "laaadeeedaaaddeeedaaaa";
		System.out.println("For test1 " + test1 + "\n compressedString is " + compressString(test1));
		String test2 = "laadeedaa";
		System.out.println("For test2 " + test2 + "\n compressedString is " + compressString(test2));
	}

}
