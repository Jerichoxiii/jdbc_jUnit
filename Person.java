/**
 * \file Print.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * This Class is a sample of the implementation of this project.
 * It demonstrates how to use the code to create an PersonTable
 * in the database customers. The table is populated by given
 * file Order.data
 *
 * \
 Begin Print class
 **/

import java.io.FileNotFoundException;
import java.sql.SQLException;
import jdbc.*;

public class Person {

	public static void main(String[] args) throws Exception {

		ConnectionMySQL myConn = new ConnectionMySQL("George", "1234");
		try {
			myConn.startConnection();
			Database Customers = new Database("customers", myConn);
			Customers.createDatabase();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		String path = "Person.data";
		ConnectionMySQL Conn = new ConnectionMySQL("George", "1234");

		Conn.startConnection("customers");

		PersonTable person = new PersonTable(path, "person", Conn);

		// System.out.println(person.fields);
		try {
			person.createTable();
		} catch (SQLException e) {
			System.err.println("Error the table is not possible to be created !!!");
		}
		try {
			try {
				person.populateTable();
				person.printTable();
			} catch (SQLException e) {
				// exception occurred.
				e.printStackTrace();
			} finally {
				// releases any system resources associated with the stream
				person.reader.releaseResourses();
			}
		} catch (FileNotFoundException e) {
			System.err.println("No Table in " + path + " Found !!");
		}
	}
}