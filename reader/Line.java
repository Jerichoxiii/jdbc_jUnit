/**
 * \file Line.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * \brief manipulates a String which belongs in a single line
 *
 * This class sets the line and separates the elements that construct it
 *
 * \
 Begin Line class
 **/

package reader;

public class Line {

   protected Word[] words;
   protected String line;
   protected char Seperator;

    /**This constructor initiates the object line and requires the number of the elements
     * and the symbol (seperator) which separates the words
      * @param seperator
     * @param number
     */
    Line (char seperator , int number){
        // Defines the character which separates the words
        this.Seperator = seperator;
        this.words = new Word[number];
    }

    /**This method sets the String which describes a line
     *
     * @param line
     */
    protected void setLine(String line ){
       this.line = line;
    }

    /**This method returns a String that describes the current line
     *
     * @return
     */
    public String getLine(){
        return this.line;
    }

    /**This method reads the line and extracts the words in the line.
     *
     * @throws NullPointerException
     * @throws StringIndexOutOfBoundsException
     */
    public void extractWords() throws NullPointerException, StringIndexOutOfBoundsException {
        int indexChar = 0;
        for (int i = 0; i < this.words.length; i++) {
            words[i] = new Word();
            while (line.charAt(indexChar) != this.Seperator) {
                this.words[i].Word = this.words[i].Word + line.charAt(indexChar++);
                if ((line.length() - 1) < indexChar)
                    break;
            }
            this.words[i].deleteSpaces();
            indexChar++;
        }
    }

    /**This method returns the word in the particular index that is given
     *
     * @param index
     * @return
     */
     public String getWord(int index){
       return this.words[index].Word;
    }
}
