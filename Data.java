/**
 * \file Data.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * This Class is a sample of the implementation of this project.
 * It demonstrates how to use the code to create and delete a database
 *
 * \
 Begin Data class
 **/

import java.sql.SQLException;

import jdbc.ConnectionMySQL;
import jdbc.Database;

public class Data {

	public static void main(String[] args) throws SQLException {

		ConnectionMySQL myConn = new ConnectionMySQL("George", "1234");
		try {
			myConn.startConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		Database Customers = new Database("customers", myConn);
		Customers.createDatabase();
		Customers.deleteDatabase();
		myConn.stopConnection();
	}
}
