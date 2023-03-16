package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertData {

	// user
	static String firstName, lastName, email, phoneNumber, password, role;

	ArrayList<String> arr = new ArrayList<String>();
	String courseName;
	int newStudentID;

	InsertData() {

	}

	public InsertData(String courseName, int newStudentID) {
		this.courseName = courseName;
		this.newStudentID = newStudentID;
	}

	void insertUserInfo(String firstName, String lastName, String email, String phoneNumber, String password,String role) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createUserTable();

		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			String query = "INSERT INTO USERS(first_name,last_name,email,phone_number,password,role) VALUES (?,?,?,?,?,?);";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, firstName);
			statement.setString(2, lastName);
			statement.setString(3, email);
			statement.setString(4, phoneNumber);
			statement.setString(5, password);
			statement.setString(6, role);
			statement.executeUpdate();
			System.out.println("INSERTED");
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	void userStudentIntoEnrollTable() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createEnrollTable();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			System.out.println("Query");
			// extract course names for drop down
			String query = "SELECT * FROM COURSES;";
			Statement st = connection.createStatement();
			ResultSet rsc = st.executeQuery(query);
			arr.add("Choose course");
			while (rsc.next()) {
				arr.add(rsc.getString("course_name"));
			}

			System.out.println("Query1");
			// extract students detail
			String query1 = "SELECT * FROM USERS WHERE role = 'Student';";
			PreparedStatement statement1 = connection.prepareStatement(query1);
			ResultSet rs = statement1.executeQuery(query1);

			System.out.println("Query2");
			String query2 = "INSERT IGNORE INTO ENROLLED(ID,student_name,email,phone_number,level) VALUES (?,?,?,?,?);";
			PreparedStatement statement2 = connection.prepareStatement(query2);

			String startLevel = "4";

			while (rs.next()) {
				int studentID = rs.getInt("ID");
				String studentName = rs.getString("first_name") + " " + rs.getString("last_name");
				System.out.println(studentName);
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				System.out.println(studentName + " " + email + " " + phoneNumber);

				statement2.setInt(1, studentID);
				statement2.setString(2, studentName);
				statement2.setString(3, email);
				statement2.setString(4, phoneNumber);
				statement2.setString(5, startLevel);
				statement2.executeUpdate();
			}

			System.out.println("Query3");
			// enroll student
			String query3 = "UPDATE ENROLLED SET course_name = ? WHERE ID = ?;";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement3.setString(1, courseName);
			statement3.setInt(2, newStudentID);
			statement3.executeUpdate();

			System.out.println("ENROLLED TABLE");
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
			System.out.println("HELLO");
		}
	}

}
