/**
* \file Database.java
* \author Georgios Papageorgiou
* \date 18 March 2018
*
* \brief Sets The database
*
* This object is responsible to create and delete a database.
* \
 Begin Database class
 **/

package jdbc;

import java.sql.SQLException;

public class Database {
	public String Name;
	ConnectionMySQL Conn;

	/**This constructor initiates the details of the database
	 *
	 * @param name
	 * @param Connection
	 */

	public Database(String name, ConnectionMySQL Connection) {
		this.Name = name;
		this.Conn = Connection;
	}

	/** This method create a new database
	 *
	 */
	public void createDatabase() {
		try {
			System.out.println("Creating database...");
			Conn.stmt = Conn.conn.createStatement();
			String sql = "CREATE DATABASE " + this.Name;
			Conn.stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");
		} catch (SQLException se) {
			// Handle errors for JDBC
			System.err.println("The creation of the database has failed !!!");
		}
	}

	/**This method deletes the database
	 *
	 */

	public void deleteDatabase() {
		try {
			System.out.println("Deleting database...");
			Conn.stmt = Conn.conn.createStatement();

			String sql = "DROP DATABASE " + this.Name;
			Conn.stmt.executeUpdate(sql);
			System.out.println("Database deleted successfully...");
		} catch (SQLException se) {
			System.err.println("Error deleting the database");
		}
	}
}