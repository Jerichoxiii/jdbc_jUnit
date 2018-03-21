/**
* \file Table.java
* \author Georgios Papageorgiou
* \date 18 March 2018
*
* \brief creates the Tables in the databases
*
* This is an abstract class Table and is responsible to create
* and return the table but also to search an element from the table.
* It is parent class of the PersonTable and OrderTable Classes
* \
 Begin Table class
 **/

package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Table {

	String Name ;
	String Fields ;
	ConnectionMySQL Conn;

	/** This constructor implements the class table and requires the information to
	 * setup the table in the database , the name and the connection which indicates
	 * the location.
	 * @param fields
	 * @param name
	 * @param Connection
	 */

	public Table(String fields,String name, ConnectionMySQL Connection) {
		this.Fields = fields;
		this.Conn = Connection;
		this.Name = name;
	}

	/**This method creates the table
	 *
	 * @throws SQLException
	 */
	public void createTable() throws SQLException {

		System.out.println("Creating table in given database...");
		Conn.stmt = Conn.conn.createStatement();
		String sql = this.Fields;
		try{
		Conn.stmt.executeUpdate(sql);
		System.out.println("Created table in given database...");
		}
		catch(SQLException e){
			System.err.println("The table was not possible to be created");
		}
	}

	/** This method returns the renault of searching specific personId from a table
	 *
	 * @param personId
	 * @return
	 * @throws SQLException
	 */
	public ResultSet searchPersonId(int personId) throws SQLException {
		Conn.stmt = Conn.conn.createStatement();
		String sql = "Select * FROM `" + this.Name + "` Where personId = " + personId ;	
		ResultSet rs = Conn.stmt.executeQuery(sql);
		return rs;
	}

	/**This method returns as resault all the table
	 *
	 * @return
	 * @throws SQLException
	 */
	public ResultSet getTable () throws SQLException {
		Conn.stmt = Conn.conn.createStatement();
		String sql = "Select * FROM `" + this.Name + "` ";
		ResultSet rs = Conn.stmt.executeQuery(sql);
		return rs;
	}
}
