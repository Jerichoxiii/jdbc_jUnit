/**
 * \file Word.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * \brief reads the table
 *
 * This class implements the word in from a text it is responsible to built the
 * word character by character and delete the spaces before and after the word
 *
 * \
 Begin Word class
 **/


package reader;

 class Word {
	protected String Word;

	 /**This constructor implements the object word by inserting the word as a string
	  *
	  * @param word
	  */
	protected Word(String word) {

		this.Word = word;
	}

	 /**This constructor implements the object word by using an empty string
	  *
	  */
	Word() {
		this.Word = "";
	}

	 /**This method deletes the spaces before and after a word . If there are spaces
	  * between the words they are ignored
	  *
	  * @throws StringIndexOutOfBoundsException
	  */
	protected void deleteSpaces() throws  StringIndexOutOfBoundsException {

		int counterFirst = 0;

		while (this.Word.charAt(counterFirst) == ' ')
			counterFirst++;

		this.Word = this.Word.substring(counterFirst);
		int counterLast = this.Word.length() - 1;

		while (this.Word.charAt(counterLast) == ' ') {
			this.Word = this.Word.substring(0, counterLast);
			counterLast--;
		}
	}

	 /**This method inserts characters in the word . It inserts the characters in the end
	  * of the String.
	  * @param character
	  */
	protected void addChar(char character) {
		this.Word = this.Word + character;
	}
	}