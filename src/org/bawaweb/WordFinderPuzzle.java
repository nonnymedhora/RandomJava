package org.bawaweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Navroz
 * Identifies locations of words in the Words.txt file
 * which exists in the matrix of characters in the Matrix.txt file
 * 
 * ex:
 * Words.txt
 * __________
 * silvia
 * argo
 * latent
 * dust
 * gist
 * tied
 * dares
 * pares
 * yan
 * dent
 * not
 * zebra
 * 
 * 
 * ex:
 * Matrix.txt
 * ___________
 * 
 * a i v l i s p 
 * r b n a i a r 
 * g i s t r w g 
 * o i i e r c t
 * d e s n r c n
 * d u s t o a e
 * h y t y y t d
 * 
 * 
 * 
 * Output
 * _____________________________________________________________________________________
 * 
 * 
 * silvia	->	Horizontal RightToLeft from Row 1, Col 6 to Row 1, Col 1
 * argo		->	Vertical TopToDown from Row 1, Col 1 to Row 4, Col 1
 * latent	->	Vertical TopToDown from Row 1, Col 4 to Row 6, Col 4
 * dust		->	Horizontal LeftToRight from Row 6, Col 1 to Row 6, Col 4
 * gist		->	Horizontal LeftToRight from Row 3, Col 1 to Row 3, Col 4
 * tied		->	Diagonal RightToLeftAndTopToDown from Row 3, Col 4 to Row 6, Col 1
 * dares	->	Diagonal RightToLeftAndDownToTop from Row 7, Col 7 to Row 3, Col 3
 * pares	->	Diagonal RightToLeftAndTopToDown from Row 1, Col 7 to Row 5, Col 3
 * yan		->	Diagonal LeftToRightAndDownToTop from Row 7, Col 5 to Row 5, Col 7
 * dent		->	Vertical DownToTop from Row 7, Col 7 to Row 4, Col 7
 * not		->	Diagonal LeftToRightAndTopToDown from Row 5, Col 4 to Row 7, Col 6
 * zebra NOT FOUND in Matrix
 * ______________________________________________________________________________________
 * 
 * Note -> Must be a Square Matrix	
 * 
 */
public class WordFinderPuzzle {
	
	public WordFinderPuzzle() {}


	public static void main(String[] args) {
		WordFinderPuzzle wfp = new WordFinderPuzzle();		
		String[] theWords = wfp.getWords();
		char[][] theMatrix = wfp.getMatrix();		
		wfp.locateWordsInMatrix(theWords, theMatrix);
	}


	private char[][] getMatrix() {
		List<char[]> charsList = new ArrayList<char[]>();
		try {
			Scanner in = new Scanner(new FileInputStream(new File("C:\\Temp\\RandomJava\\dirs\\Matrix.txt")));
			while( in.hasNextLine() ) {
				char[] chs = in.nextLine().replaceAll(" ","").toCharArray();
//				char[] chs = stripSpaces(in.nextLine().trim().toCharArray());
				charsList.add(chs);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return extractMatrix(charsList);
	}


	private char[][] extractMatrix(List<char[]> charsList) {
		char[][] aMatrix;
		final int size = charsList.size();
		aMatrix = new char[size][];
		for(int i = 0; i < size; i++) {
			aMatrix[i] = charsList.get(i);
		}
		return aMatrix;
	}


	private char[] stripSpaces(char[] charArray) {
		char[] cleaned = null;
		List<Character> aList = new ArrayList<Character>();
		for(int i = 0; i < charArray.length; i++) {
			if ( !(charArray[i] == ' ')) {
				aList.add( charArray[i]);
			}
		}
		final int size = aList.size();
		cleaned = new char[size];
		for(int i = 0; i < size; i++) {
			cleaned[i] = aList.get(i);
		}
		return cleaned;
	}


	private String[] getWords() {
		List<String> wordsList = new ArrayList<String>();
		try {
			Scanner in = new Scanner(new FileInputStream(new File("C:\\Temp\\RandomJava\\dirs\\Words.txt")));
			while( in.hasNextLine() ) {
				wordsList.add(in.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return extractWords(wordsList);
	}


	private String[] extractWords(List<String> wordsList) {
		final int size = wordsList.size();
		String[] theWords = new String[size];
		for(int i = 0; i < size; i++) {
			theWords[i] = wordsList.get(i);
		}
		return theWords;
	}
	
	private void locateWordsInMatrix(final String[] words, final char[][] matrix) {
		// for each word
		for( int i = 0; i < words.length; i++) {
			String aWord = words[i];
			findWordInMatrix( aWord, matrix);
		}
	}


	private void findWordInMatrix(String word, char[][] aMatrix) {
		char[] letters = word.toCharArray();
		boolean wordFound = false;
		
		final int length = aMatrix.length;
		for (int row = 0; row < length; row++) {
			for(int col = 0; col < length; col++) {
				if( letters[0] == aMatrix[row][col]) {
					// found first letter
					wordFound = searchLeftToRightHorizontal(letters, aMatrix, row, col);
					if( wordFound ) {		//dust	->	Horizontal LeftToRight from Row 6, Col 1 to Row 6, Col 4
						System.out.println(word + "\t->\tHorizontal LeftToRight from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row+1) + ", Col " + (col+word.length()));
						return;
					}
					else {
						wordFound = searchRightToLeftHorizontal(letters, aMatrix, row, col);
						if( wordFound ){	//silvia	->	Horizontal RightToLeft from Row 1, Col 6 to Row 1, Col 1
							System.out.println(word + "\t->\tHorizontal RightToLeft from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row+1) + ", Col " + (col-(word.length()-1)+1));	
							return;
						} else {
							wordFound = searchTopToDownVertical(letters, aMatrix, row, col);
							if( wordFound ) {	//argo	->	Vertical TopToDown from Row 1, Col 1 to Row 4, Col 1
								System.out.println(word + "\t->\tVertical TopToDown from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row+word.length()) + ", Col " + (col+1));	
								return;
							} else {
								wordFound = searchDownToTopVertical(letters, aMatrix, row, col);
								if( wordFound ) {	//dent	->	Vertical DownToTop from Row 7, Col 7 to Row 4, Col 7
									System.out.println(word + "\t->\tVertical DownToTop from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row-(word.length()-1)+1) + ", Col " + (col+1));	
									return;
								} else {
									wordFound = searchLeftToRightAndTopToDownDiagonal(letters, aMatrix, row, col);
									if( wordFound ) {	//not	->	Diagonal LeftToRightAndTopToDown from Row 5, Col 4 to Row 7, Col 6
										System.out.println(word + "\t->\tDiagonal LeftToRightAndTopToDown from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row+word.length()) + ", Col " + (col+word.length()));	
										return;
									} else {
										wordFound = searchLeftToRightAndDownToTopDiagonal(letters, aMatrix, row, col);
										if( wordFound ) {	//yan	->	Diagonal LeftToRightAndDownToTop from Row 7, Col 5 to Row 5, Col 7
											System.out.println(word + "\t->\tDiagonal LeftToRightAndDownToTop from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row-(word.length()-1)+1) + ", Col " + (col+word.length()));	
											return;
										} else {
											wordFound = searchRightToLeftAndTopToDownDiagonal(letters, aMatrix, row, col);
											if( wordFound ) {	//pares	->	Diagonal RightToLeftAndTopToDown from Row 1, Col 7 to Row 5, Col 3
												System.out.println(word + "\t->\tDiagonal RightToLeftAndTopToDown from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row+(word.length())) + ", Col " + (col-(word.length()-1)+1));	
												return;
											} else {
												wordFound = searchRightToLeftAndDownToTopDiagonal(letters, aMatrix, row, col);
												if( wordFound ) {	//dares	->	Diagonal RightToLeftAndDownToTop from Row 7, Col 7 to Row 3, Col 3
													System.out.println(word + "\t->\tDiagonal RightToLeftAndDownToTop from Row " + (row+1) + ", Col " + (col+1) + " to Row " + (row-(word.length()-1)+1) + ", Col " + (col-(word.length()-1)+1));	
													return;
												}
											}
										}
									}
								}
							}
						}
					}
				} 
			}
		}
		System.out.println(word+" NOT FOUND in Matrix");
		
	}
	

	/**
	 * Checks for the word in the matrix of characters
	 * direction --- right to left, and down to top - diagonally
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return found		-	true if found
	 */	
	private boolean searchRightToLeftAndDownToTopDiagonal(final char[] letters, final char[][] aMatrix, final int theRow, final int theCol) {
		boolean found = false;
		if (canProceedRightLeftDownTopDiagonaly(letters.length, aMatrix.length, theRow, theCol)) {
			int row = theRow;
			int col = theCol;
			for (int index = 0; index < letters.length; index++) {
				boolean letterFound = (letters[index] == aMatrix[row][col]);
				found = found || letterFound;

				if (!letterFound)
					return false;
				else {
					row -=1 ;
					col -=1 ;
				}

			}
		}
		return found;
	}


	/**
	 * Determines if there is space from the point in matrix for the word - moving diagonally right to left and top to down
	 * @param wordLength		-	the length of the word
	 * @param theRow			-	the row for the start of the word
	 * @param theCol			-	the col for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedRightLeftDownTopDiagonaly(final int wordLength, final int matrixSize, final int theRow, final int theCol) {
		return canProceedRightToLeftHorizontally(wordLength, theCol ) && canProceedDownToTopVertically(wordLength, theRow);
	}


	/**
	 * Checks for the word in the matrix of characters
	 * direction --- right to left, and top to down - diagonally
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return found		-	true if found
	 */
	private boolean searchRightToLeftAndTopToDownDiagonal(final char[] letters, final char[][] aMatrix, final int theRow, final int theCol) {
		boolean found = false;
		if (canProceedRightLeftTopDownDiagonaly(letters.length, aMatrix.length, theRow, theCol)) {
			int row = theRow;
			int col = theCol;
			for (int index = 0; index < letters.length; index++) {
				boolean letterFound = (letters[index] == aMatrix[row][col]);
				found = found || letterFound;

				if (!letterFound)
					return false;
				else {
					row += 1;
					col -= 1;
				}

			}
		}
		return found;
	}


	/**
	 * Determines if there is space from the point in matrix for the word - moving diagonally right to left and top to down
	 * @param wordLength		-	the length of the word
	 * @param theRow			-	the row for the start of the word
	 * @param theCol			-	the col for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedRightLeftTopDownDiagonaly(final int wordLength, final int matrixSize, final int theRow, final int theCol) {
		return canProceedRightToLeftHorizontally(wordLength, theCol ) && canProceedTopToDownVertically(wordLength, matrixSize, theRow);
	}


	/**
	 * Checks for the word in the matrix of characters
	 * direction --- left to right, and down to top - diagonally
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return found		-	true if found
	 */
	private boolean searchLeftToRightAndDownToTopDiagonal(final char[] letters, final char[][] aMatrix, final int theRow, final int theCol) {
		boolean found = false;
		if (canProceedLeftRightDownTopDiagonaly(letters.length, aMatrix.length, theRow, theCol)) {			
			int row = theRow;
			int col = theCol;
			
			for (int index = 0; index < letters.length; index++) {
				boolean letterFound = (letters[index] == aMatrix[row][col]);
				found = found || letterFound;

				if (!letterFound)
					return false;
				else {
					row -= 1;
					col += 1;
				}

			}
		}
		return found;
	}


	/**
	 * Determines if there is space from the point in matrix for the word - moving diagonally left to right and down to top
	 * @param wordLength		-	the length of the word
	 * @param theRow			-	the row for the start of the word
	 * @param theCol			-	the col for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedLeftRightDownTopDiagonaly(final int wordLength, final int matrixSize, final int theRow, final int theCol) {
		return canProceedLeftToRightHorizontally(wordLength, matrixSize, theCol) && canProceedDownToTopVertically(wordLength, theRow);
	}


	/**
	 * Checks for the word in the matrix of characters
	 * direction --- left to right, and top to down - diagonally
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return found		-	true if found
	 */
	private boolean searchLeftToRightAndTopToDownDiagonal(final char[] letters, final char[][] aMatrix, final int theRow, final int theCol) {
		boolean found = false;
		if (canProceedLeftRightTopDownDiagonaly(letters.length, aMatrix.length, theRow, theCol)) {			
			int row = theRow;
			int col = theCol;
			for (int index = 0; index < letters.length; index++) {
				boolean letterFound = (letters[index] == aMatrix[row][col]);
				found = found || letterFound;

				if (!letterFound)
					return false;
				else {
					row += 1;
					col += 1;
				}

			}
		}
		return found;
	}


	/**
	 * Determines if there is space from the point in matrix for the word - moving diagonally left to right and top to down
	 * @param wordLength		-	the length of the word
	 * @param theRow			-	the row for the start of the word
	 * @param theCol			-	the col for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedLeftRightTopDownDiagonaly(final int wordLength, final int matrixSize, final int theRow, final int theCol) {
		return	canProceedLeftToRightHorizontally(wordLength, matrixSize, theCol) && canProceedTopToDownVertically(wordLength, matrixSize, theRow);
	}


	/**
	 * Checks for the word in the matrix of characters
	 * direction --- down to top, vertical (along col)
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return	true if found	-->
	 */
	private boolean searchDownToTopVertical( final char[] letters, final char[][] aMatrix, final int theRow, final int theCol ) {
		boolean found = false;
		int row = theRow;
		if(canProceedDownToTopVertically(letters.length, theRow)) {
			for(int index = 0; index < letters.length; index++) {
				boolean letterFound = ( letters[index] == aMatrix[row][theCol] );
				found = found || letterFound;
				
				if( !letterFound ) 
					return false;
				else	
					row -= 1;
			}
		}
		return found;
	}



	/**
	 * Determines if there is space from the point in matrix for the word - moving down to top vertically 
	 * @param wordLength	-	the length of the word
	 * @param theRow			-	the row for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedDownToTopVertically(final int wordLength, final int theRow) {
		return theRow - (wordLength-1) >= 0;
	}


	/**
	 * Checks for the word in the matrix of characters
	 * direction --- top to down, vertical (along col)
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return	true if found	-->
	 */
	private boolean searchTopToDownVertical( final char[] letters, final char[][] aMatrix, final int theRow, final int theCol ) {
		boolean found = false;
		int row = theRow;
		if(canProceedTopToDownVertically(letters.length, aMatrix.length, theRow)) {
			for(int index = 0; index < letters.length; index++) {
				boolean letterFound = ( letters[index] == aMatrix[row][theCol] );
				found = found || letterFound;
				
				if( !letterFound ) 
					return false;
				else
					row += 1;
			}
		}
		return found;
	}


	/**
	 * Determines if there is space from the point in matrix for the word - moving top to down vertically 
	 * @param wordLength	-	the length of the word
	 * @param matrixSize	- 	the dimension of the matrix
	 * @param row			-	the row for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedTopToDownVertically(final int wordLength, final int matrixSize, final int row) {
		return	( matrixSize - row >= wordLength-1 );
	}
	
	


	/**
	 * Checks for the word in the matrix of characters
	 * direction --- right to left, horizontal (along row)
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return	true if found	-->
	 */
	private boolean searchRightToLeftHorizontal( final char[] letters, final char[][] aMatrix, final int theRow, final int theCol ) {
		boolean found = false;
		int col = theCol;
		if(canProceedRightToLeftHorizontally(letters.length, theCol)) {
			for(int index = 0; index < letters.length; index++) {
				boolean letterFound = ( letters[index] == aMatrix[theRow][col] );
				found = found || letterFound;
				
				if( !letterFound ) 
					return false;
				else	
					col -= 1;
			}
		}
		return found;
	}



	/**
	 * Determines if there is space from the point in matrix for the word - moving right to left horizontally 
	 * @param wordLength	-	the length of the word
	 * @param theCol		-	the col for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedRightToLeftHorizontally(int wordLength, final int theCol) {
		return theCol - (wordLength-1) >= 0;
	}


	/**
	 * Checks for the word in the matrix of characters
	 * direction --- left to right, horizontal (along row)
	 * 
	 * @param letters		-	the letters to locate
	 * @param aMatrix		-	the matrix of characters
	 * @param theRow		- 	the initial row to start from
	 * @param theCol		- 	the initial col to start from
	 * @return found		-	true if found
	 */
	private boolean searchLeftToRightHorizontal(final char[] letters, final char[][] aMatrix, final int theRow, final int theCol) {
		boolean found = false;
		int col = theCol;
		if (canProceedLeftToRightHorizontally(letters.length, aMatrix.length, theCol)) {
			for (int index = 0; index < letters.length; index++) {
				boolean letterFound = (letters[index] == aMatrix[theRow][col]);
				found = found || letterFound;

				if (!letterFound)
					return false;
				else
					col += 1;
			}
		}
		return found;
	}
	

	/**
	 * Determines if there is space from the point in matrix for the word - moving left to right horizontally 
	 * @param wordLength	-	the length of the word
	 * @param matrixSize	- 	the dimension of the matrix
	 * @param col			-	the col for the start of the word
	 * @return	true if there is space
	 */
	private boolean canProceedLeftToRightHorizontally(final int wordLength, final int matrixSize, final int col) {
		return (matrixSize - col >= wordLength - 1);
	}

}
