package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student extends Users{
	static String numOfStudents;
	// for enroll
	static int enrollStudentID; 
	String enrollCourseName, date;
	ArrayList<String> arr = new ArrayList<>();
	static String studentName, courseName, level, studentLoggedEmail;
	
	public Student() {
		
	}
		
	public Student(String enrollCourseName) {
		this.enrollCourseName = enrollCourseName;
	}
	
	public Student(String firstName, String lastName, String email, String phoneNUmber, String password, String role) {
		super(firstName, lastName, email, phoneNUmber, password, role);
		// TODO Auto-generated constructor stub
	}
	
	public static void numberOfStudents() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String sql = "SELECT * FROM USERS WHERE role = ?";
			
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, "student");
			ResultSet rs = st.executeQuery();
			
			int count = 0;
			while(rs.next()) {
			    count++;
			}
			numOfStudents = Integer.toString(count);
			System.out.println(numOfStudents);
			// connection close 
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// enroll student
	void enrollStudent() {
		System.out.println(studentLoggedEmail);
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createEnrollTable();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);
			
			LocalDate ld =  LocalDate.now();
			date = ld.toString();
			
			// getStudentID
			String query = "SELECT ID FROM USERS WHERE email = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, studentLoggedEmail);
			ResultSet rs = statement.executeQuery();
			rs.next();
			enrollStudentID = rs.getInt("ID");
			
			
			// enroll student 
			String query3 = "UPDATE ENROLLED SET course_name = ?, date = ? WHERE ID = ?;";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement3.setString(1, enrollCourseName);
			statement3.setString(2, date);
			statement3.setInt(3, enrollStudentID);
			statement3.executeUpdate();

			System.out.println("STUDENT ENROLLED");
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
		
	static boolean alreadyEnrolled(String email) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createEnrollTable();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);
			
			// check enroll student 
			String query = "SELECT * FROM ENROLLED WHERE email = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			rs.next();
			if (rs.getString("course_name") == null) {
				System.out.println(1);
				return false;
			}
			System.out.println(2);
			
			System.out.println("Check STUDENT ENROLLED");
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return true;
	}

	// for student progress 
	static void studentData(int studentID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createTestRecord();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);
			
			// get STUDENT NAME, COURSE AND LEVEL
			String query = "SELECT * FROM ENROLLED WHERE ID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentID);
			ResultSet rs = statement.executeQuery();
			rs.next();
			studentName = rs.getString("student_name");
			courseName = rs.getString("course_name");
			level = rs.getString("level");
			System.out.println(studentName+" "+courseName+" "+level);
			
			System.out.println("STUDENT PROGRESS DATA");
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
