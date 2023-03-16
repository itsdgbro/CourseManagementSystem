package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {

	void createUserTable() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String query = "CREATE TABLE USERS (\r\n"
					+ "    ID int PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "    first_name varchar(50),\r\n"
					+ "    last_name varchar(50),\r\n"
					+ "	email varchar(50) UNIQUE,\r\n"
					+ "	phone_number varchar(10),\r\n"
					+ "	password varchar(50),\r\n"
					+ "	role varchar(50)\r\n"
					+ ");";
			
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Table Created");
			// connection close
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	void createCourseTable() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String query = "CREATE TABLE COURSES (\r\n"
					+ "    ID int PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "    course_name varchar(50) UNIQUE,\r\n"
					+ "	seats varchar(50),\r\n"
					+ "	batch varchar(10),\r\n"
					+ "	duration varchar(5)\r\n"
					+ ");";
			
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Table Created");
			// connection close
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		
	}
	
	void createModuleTable() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String query = "CREATE TABLE MODULES (\r\n"
					+ "    ID int PRIMARY KEY AUTO_INCREMENT,\r\n"
					+ "	module_name varchar(100) UNIQUE,\r\n"
					+ "	course_name varchar(50),\r\n"
					+ "	credits varchar(10),\r\n"
					+ "	instructor_assigned varchar(50),\r\n"
					+ "	level varchar(1)\r\n"
					+ ");";
			
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Table Created");
			// connection close
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
		
	}
	
	void createEnrollTable() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String query = "CREATE TABLE ENROLLED(\r\n"
					+ "	ID int PRIMARY KEY,\r\n"
					+ "	student_name varchar(50),\r\n"
					+ "	email varchar(50) UNIQUE,\r\n"
					+ "	phone_number varchar(10),\r\n"
					+ "	date DATE,	\r\n"
					+ "	course_name varchar(50),\r\n"
					+ "	level varchar(1)\r\n"
					+ ");";
			
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("Table Created");
			// connection close
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	void createTestRecord() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String query = "CREATE TABLE TESTRECORD(\r\n"
					+ "	student_id int,\r\n"
					+ "	student_name varchar(30),\r\n"
					+ "	module_id int,\r\n"
					+ "	module_name varchar(50),\r\n"
					+ "	instructor_name varchar(30),\r\n"
					+ "	pass_mark int,\r\n"
					+ "	full_mark int,\r\n"
					+ "	student_mark int,\r\n"
					+ "	PRIMARY KEY (student_id,module_id)\r\n"
					+ ");";
			
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("STUDENT REPORT Table Created");
			// connection close
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
}
