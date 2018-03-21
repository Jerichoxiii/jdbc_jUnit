/**
 * \file Print.java
 * \author Georgios Papageorgiou
 * \date 18 March 2018
 *
 * This Class is a sample of the implementation of this project.
 * This example demonstrates how to read the and use the elements
 * of the existing tables in the tables in the database.
 *
 * \
 Begin Print class
 **/


import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionMySQL;
import jdbc.Database;
import jdbc.OrderTable;
import jdbc.PersonTable;

public class Print {
	
	public static void main(String [] args) throws ClassNotFoundException, SQLException {

		ConnectionMySQL myConn = new ConnectionMySQL("George", "1234");
		try {
			myConn.startConnection();
			Database Customers = new Database("customers", myConn);
			Customers.createDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error !!! The Connection Was not possible");
		}
		
		ConnectionMySQL Conn = new ConnectionMySQL("George", "1234");
		Conn.startConnection("customers");
		
		OrderTable order = new OrderTable("order", Conn);
		PersonTable person = new PersonTable("person", Conn);

		try {
			ResultSet rsPerson = person.getTable();
			ResultSet rsOrder = order.getTable();

			while (rsPerson.next()) {

				int id = rsPerson.getInt("personId");
				ResultSet rslt = order.searchPersonId(id);
				if (rslt.next()) {
					String first = rsPerson.getString("firstName");
					String last = rsPerson.getString("lastName");
					String street = rsPerson.getString("street");
					String city = rsPerson.getString("city");
					// Display values
					System.out.print("id: " + id);
					System.out.print(", Name: " + first);
					System.out.print("Last: " + last);
					System.out.print(", Street: " + street);
					System.out.println(", City: " + city);
				}
			}
			rsPerson.first();

			while (rsOrder.next()) {

				// Retrieve by column name
				int OrderId = rsOrder.getInt("OrderId");
				int OrderNumber = rsOrder.getInt("OrderNumber");
				int PersonId = rsOrder.getInt("PersonId");
				// Display values
				System.out.print(" OrderId: " + OrderId);
				System.out.print(", orderNumber: " + OrderNumber);
				System.out.print(", personID: " + PersonId);
				ResultSet rslt = person.searchPersonId(PersonId);
				if (rslt.next()) {
					String first = rslt.getString("firstName");
					System.out.print(" Name " + first );
				}
				System.out.println();
			}
			rsOrder.close();
		}
		catch(SQLException e){
			System.err.println("Error !!! No Tables found in the database");
		}
	}
}
