/**
* \file ConnectionMySQL.java
* \author Georgios Papageorgiou
* \date 18 March 2018
*
* \brief Sets The connection with the databases
*
* This object is responsible to connect and dissconect from the databases
* \
 Begin ConnectionMySQL class
 **/

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionMySQL {

	// JDBC driver name and database URL
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final String DB_URL = "jdbc:mysql://localhost/";

	// Database credentials
	String USER;
	String PASS;
	public Connection conn ;
	public Statement stmt ;

	/**This constructor implements the Connection with the database and requires the
	 * User name and the password to asses in the database.
	 *@param user
	 *@param pass
	 */
	public ConnectionMySQL(String user, String pass) {
		this.USER = user;
		this.PASS = pass;
	}


	/**This This method starts the connection with the path inserted as parameter
	 *\ param path
	 */
	public void startConnection(String path) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Connecting to a selected database...");

		String fullPath = DB_URL + path;
		System.out.println("Connecting to " + fullPath);

		conn = DriverManager.getConnection(fullPath, USER, PASS);
		System.out.println("Connected database successfully...");
	}

	/**This This method starts the connection is connected in the default location
	 * of the databases
	 */
	public void startConnection() throws ClassNotFoundException, SQLException {
		startConnection("");
	}

	/**This method stops the connection with the database
	 *
	 */
	public void stopConnection() {
		if (stmt != null)
			try {
				stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("The connection with " + this.USER + " is unsuccessful !");
			}
		System.out.println("The connection with " + this.USER + " terminated successfully !");
	}
}