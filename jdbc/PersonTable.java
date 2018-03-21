/**
 * \file PersonTable.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * \brief creates the PersonTables in the databases
 *
 * This Class is a subClass of the Class Table and includes
 * all the features required for the implementation of the PersonTable
 * \
 Begin PersonTable class
 **/

package jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import reader.TableReader;

public class PersonTable extends Table {

	public TableReader reader;

	private final static String Fields = "CREATE TABLE person (personId Integer , " + " firstName VARCHAR(255), "
			+ " lastName VARCHAR(255), " + " street VARCHAR(255), " + " city VARCHAR(255) , "
			+ " PRIMARY KEY ( personId ))";

	/**This constructor implements a PersonTable by indicating the path of the Table that the information
	 * of the table will be read ,name and the connection in the database.
	 *
	 * @param path
	 * @param name
	 * @param Connection
	 * @throws FileNotFoundException
	 */
	public PersonTable(String path, String name, ConnectionMySQL Connection) throws FileNotFoundException {
		super(Fields, name, Connection);
		this.reader = new TableReader(path, ',', 5);
	}

	/**This constructor implements a PersonTable and requires the connection and the name of the table
	 * to be read in the database.
	 * @param name
	 * @param Connection
	 */
	public PersonTable(String name, ConnectionMySQL Connection){
		super(Fields,name, Connection);
	}

	/**This method is responsible to insert the data in the table and It ensures that each element has the
	 * correct form.
	 * @param personId
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @throws SQLException
	 * @throws NumberFormatException
	 */
	private void insertRecords(String personId, String firstName, String lastName, String street, String city)
			throws SQLException , NumberFormatException {
		System.out.println("Inserting records into the table...");
		Conn.stmt = Conn.conn.createStatement();

		Integer.parseInt(personId);

		String sql = "INSERT INTO person VALUES( " + personId + " , '" + firstName + "' , '" + lastName + "' , '"
				+ street + "' , '" + city + "' ) ;";
		Conn.stmt.executeUpdate(sql);
	}

	/**This method is responsible to print the table from the database.
	 *
	 * @throws SQLException
	 */
	public void printTable() throws SQLException {

		System.out.println("Print elements");
		System.out.println("Creating statement...");
		Conn.stmt = Conn.conn.createStatement();

		String sql = "SELECT personId, firstName , lastName , street , city FROM `person` ";

		ResultSet rs = Conn.stmt.executeQuery(sql);
		// STEP 5: Extract data from result set
		while (rs.next()) {
			// Retrieve by column name
			int id = rs.getInt("personId");
			String first = rs.getString("firstName");
			String last = rs.getString("lastName");
			String street = rs.getString("street");
			String city = rs.getString("city");
			// Display values
			System.out.print("id: " + id);
			System.out.print(", Name: " + first);
			System.out.print("Last: " + last);
			System.out.print(", Street: " + street);
			System.out.println(", City: " + city);
		}
		rs.close();
	}

	/**This method populates the data taken from a file , the path of which is
	 * given in the constructor.
	 * @throws IOException
	 */
	 public void populateTable() throws IOException {
		 this.reader.readNext();
		 while (this.reader.readNext() != null) {
			 try {
				 this.reader.line.extractWords();
				 String personId = this.reader.line.getWord(0);
				 String firstName = this.reader.line.getWord(1);
				 String LastName = this.reader.line.getWord(2);
				 String street = this.reader.line.getWord(3);
				 String city = this.reader.line.getWord(4);
				 this.insertRecords(personId, firstName, LastName, street, city);
			 } catch (StringIndexOutOfBoundsException e) {
				 System.err.println("Error to read the record");
			 } catch (SQLException e) {
				 System.err.println("Error inserting the record");
			 }
			 catch (NumberFormatException e) {
				 System.err.println("Wrong data type inserted");
			 }
		 } // End while
	 }
}
