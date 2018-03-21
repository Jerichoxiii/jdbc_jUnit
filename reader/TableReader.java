/**
 * \file TableReader.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * \brief reads the table
 *
 * This Class is responsible to read line by line from a given file
 *
 * \
 Begin TableReader class
 **/

package reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TableReader {

	private InputStream is ;
	private InputStreamReader isr ;
	private BufferedReader br ;
	public Line line ;

	/**This constructor initiates the table by indicating the path ,seperator and the number of the
	 * words has each line
	 *
	 * @param table
	 * @param seperator
	 * @param number
	 * @throws FileNotFoundException
	 */
	public TableReader(String table, char seperator, int number) throws FileNotFoundException {

		// open input stream test.txt for reading purpose.
		this.is = new FileInputStream(table);
		// create new input stream reader
		this.isr = new InputStreamReader(is);
		// create new buffered reader
		this.br = new BufferedReader(isr);
		// create line
		this.line = new Line(seperator,number);
	}


	/**This method reads the next available line from the file
	 *
	 * @return
	 * @throws IOException
	 */
	public String readNext() throws IOException {
		String line = this.br.readLine();
		this.line.setLine(line);
		return line;
	}

	/**This method releases the sources when the proses is complete
	 *
	 * @throws IOException
	 */
	public void releaseResourses() throws IOException{

		if (this.is != null)
			this.is.close();
		if (this.isr != null)
			this.isr.close();
		if (this.br != null)
			this.br.close();

	}
}
