/**
 * 
 */
package org.bawaweb.prepInsta.mettl;

import java.util.LinkedHashMap;

/**
 * @author Navroz
 * 
 * https://prepinsta.com/mettl-coding-questions-5/
 * 
 * Ques. 1 Write a program to find out total marks obtained by a student 
 * if the student gets 3 marks for the correct answer 
 * and -1 for the wrong answer?
 *
 */
public class TotalMarks {
	
	
	private static final LinkedHashMap<Integer,String> ansKeyMap=new LinkedHashMap<Integer,String>();
	static {
		ansKeyMap.put(1,	"Mera");
		ansKeyMap.put(2,	"Bharat");
		ansKeyMap.put(3,	"Mahan");
		ansKeyMap.put(4,	"Hain");
		ansKeyMap.put(5,	"Jai");
		ansKeyMap.put(6,	"Hind");
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedHashMap<Integer, String> studentMap = new LinkedHashMap<Integer, String>();
		studentMap.put(1, "Mera");			//3
		studentMap.put(2, "Bhara");			//-1
		studentMap.put(3, "Mahaa");			//-1
		studentMap.put(4, "Haha");			//-1
		studentMap.put(5, "Jai");			//3
		studentMap.put(6, "Hind");			//3				==>	6
		
		System.out.println("TotalMarx is "+getTotalMarks(ansKeyMap,studentMap));

	}


	private static int getTotalMarks(LinkedHashMap<Integer,String> ansMap, LinkedHashMap<Integer, String> studMap) {
		int total = 0;
		for (int key : ansMap.keySet()) {
			if (ansMap.get(key).equals(studMap.get(key))) {
				total += 3;
			} else {
				total -= 1;
			}
		}
		return total;
	}

}
