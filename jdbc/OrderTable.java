/**
 * \file OrderTable.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * \brief creates the OrderTables in the databases
 *
 * This Class is a subClass of the Class Table and includes
 * all the features required for the implementation of the OrderTable
 * \
 Begin OrderTable class
 **/


package jdbc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import reader.TableReader;

public class OrderTable extends Table {

	public TableReader reader;
	private final static String FIELDS = "CREATE TABLE `order` " + "(OrderId Integer, " + " OrderNumber Integer, "
			+ " personId Integer, " + " PRIMARY KEY ( OrderId ))";


	/**This constructor implements a OrderTable by indicating the path of the Table that the information
	 * of the table will be read ,name and the connection in the database.
	 *
	 * @param path
	 * @param name
	 * @param Connection
	 * @throws FileNotFoundException
	 */
	public OrderTable(String path, String name, ConnectionMySQL Connection) throws FileNotFoundException {
		super(FIELDS,name, Connection);
		this.reader = new TableReader(path, '|', 3);
	}
	/**This constructor implements a Ordertable and requires the connection and the name of the table
	 * to be read in the database.
	 * @param name
	 * @param Connection
	 */
	public OrderTable( String name, ConnectionMySQL Connection){
		super(FIELDS,name, Connection);
	}

	/**This method is responsible to insert the individual elements in the OrderTable
	 *
	 * @param OrderId
	 * @param OrderNumber
	 * @param PersonId
	 * @throws SQLException
	 * @throws NumberFormatException
	 */

	private void insertRecords(String OrderId, String OrderNumber, String PersonId) throws SQLException ,NumberFormatException{
		System.out.println("Inserting records into the table...");
		Conn.stmt = Conn.conn.createStatement();
		Integer.parseInt(OrderId);
		Integer.parseInt(OrderNumber);
		Integer.parseInt(PersonId);
		String sql = "INSERT INTO `order` " + "VALUES(" + OrderId + "," + OrderNumber + "," + PersonId + ");";
		Conn.stmt.executeUpdate(sql);
	}

	/**This method populates the data taken from a file , the path of which is
	 * given in the constructor.
	 * @throws IOException
	 */
	public void populateTable() throws IOException {
		this.reader.readNext();
		while (this.reader.readNext() != null) {
			this.reader.line.extractWords();

			String orderId = this.reader.line.getWord(0);
			String orderNumber = this.reader.line.getWord(1);
			String personId = this.reader.line.getWord(2);
			try {
				this.insertRecords(orderId, orderNumber, personId);
			} catch (NumberFormatException e) {
				System.err.println("Error : Non numerical value");
			} catch (SQLException e) {
				System.out.println("Error , Is not possible to add record !!");
			}
		}
	}
	/**This method is responsible to print the table from the database.
	 *
	 * @throws SQLException
	 */
	public void printTable() throws SQLException {

		System.out.println("Print elements");
		System.out.println("Creating statement...");
		Conn.stmt = Conn.conn.createStatement();
		String sql = "SELECT OrderId, OrderNumber , PersonId FROM `order` ;";
		ResultSet rs = Conn.stmt.executeQuery(sql);
		// STEP 5: Extract data from result set

		while (rs.next()) {
			// Retrieve by column name
			int OrderId = rs.getInt("OrderId");
			int OrderNumber = rs.getInt("OrderNumber");
			int PersonId = rs.getInt("PersonId");
			// Display values
			System.out.print(" OrderId: " + OrderId);
			System.out.print(", orderNumber: " + OrderNumber);
			System.out.println(", personID: " + PersonId);
		}
		rs.close();
	}
}
