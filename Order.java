/**
 * \file Order.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * This Class is a sample of the implementation of this project.
 * It demonstrates how to use the code to create an OrderTable
 * in the database customers. The table is populated by given
 * file Order.data
 *
 * \
 Begin Order class
 **/

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import jdbc.ConnectionMySQL;
import jdbc.Database;
import jdbc.OrderTable;

public class Order {

	public static void main(String[] args) throws  IOException {

		ConnectionMySQL myConn = new ConnectionMySQL("George", "1234");
		try {
			myConn.startConnection();
			Database Customers = new Database("customers", myConn);
			Customers.createDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error !!! The Connection Was not possible");
			//e.printStackTrace();
		}
		
		ConnectionMySQL Conn = new ConnectionMySQL("George", "1234");
		try {
			Conn.startConnection("customers");
		}
		catch(SQLException | ClassNotFoundException e){
		}

		String path = "Order.data";
		OrderTable order = new OrderTable(path, "order", Conn);

		try {
		order.createTable();
		}catch(SQLException e) {
			System.err.println("The table was not possible to be created !!!");
		}
		
		try {
			try {
				order.populateTable();
				order.printTable();
			} catch (SQLException e) {
				// exception occurred.
				// e.printStackTrace();
			} finally {
				// releases any system resources associated with the stream
				order.reader.releaseResourses();
			}
		} catch (FileNotFoundException e) {
			System.out.println("No Table in " + path + " Found !!");
		}
		Conn.stopConnection();
		myConn.stopConnection();
	}
}
