package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
// COURSE MANAGEMENT SYSTEM (CMS)
public class CheckConnection {
	
	String urlStr = com.main.Main.urlStr;
	String usernameString = com.main.Main.usernameString;
	String password = com.main.Main.password;
	
	void check() {
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(urlStr, usernameString, password);
			
			String query = "Create Database CMS";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			
			if (!connection.isClosed()) {
				System.out.println("Opened!");
			}
			// connection close
			connection.close();
		}catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
}

