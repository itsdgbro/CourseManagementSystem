package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Instructor extends Users{
	static String numberOfTutors;
	// array of tutors
	static ArrayList<String> arr = new ArrayList<>();
	static String courseName;
	static String loggedEmail;
	static int tutorID;
	
	// constructor
	Instructor(){
		//
	}
	
	public Instructor(String loggedEmail){
		Instructor.loggedEmail = loggedEmail;
		System.out.println("INSTRUCTOR");
	}

	public Instructor(String firstName, String lastName, String email, String phoneNUmber, String password, String role) {
		super(firstName, lastName, email, phoneNUmber, password, role);
		// TODO Auto-generated constructor stub
	}

	public static void numberOfTutors() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String sql = "SELECT * FROM USERS WHERE role = ?";
			
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, "Instructor");
			ResultSet rs = st.executeQuery();
			
			int count = 0;
			while(rs.next()) {
			    count++;
			}
			numberOfTutors = Integer.toString(count);
			System.out.println(numberOfTutors);
			// connection close 
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}

	void getInstructors() {
		CheckConnection cc = new CheckConnection();
//		Instructor ins = new Instructor();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/cms",cc.usernameString,cc.password);
			String query = "SELECT * FROM USERS WHERE role = 'Instructor';";
			
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			Instructor.arr.add("Choose Instructor");
			while (rs.next()) {
				String fullName = rs.getString("first_name")+" "+rs.getString("last_name");
				Instructor.arr.add(fullName);
			}
			System.out.println(Instructor.arr);
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e); 
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// students mark data
	void testData(int studentID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createTestRecord();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);
			
			// fetch student course and level
			String query1 = "SELECT * FROM ENROLLED WHERE ID = ?";
			PreparedStatement statement1 = connection.prepareStatement(query1);
			statement1.setInt(1, studentID);
			ResultSet rs1 = statement1.executeQuery();
			rs1.next();
			String studentName = rs1.getString("student_name");
			courseName = rs1.getString("course_name");
			String level = rs1.getString("level");
			System.out.println(studentName +" "+courseName+" "+level);
			
			// fetch modules of the corresponding course and level
			String query2 = "SELECT * FROM MODULES WHERE course_name = ? AND level = ?;";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setString(1, courseName);
			statement2.setString(2, level);
			ResultSet rs2 = statement2.executeQuery();
			
			String moduleName, instructorName;
			
			// store data in new table test report
			String query3 = "INSERT INTO TESTRECORD (student_id,student_name,module_id, module_name,instructor_name,pass_mark,full_mark) VALUES (?,?,?,?,?,?,?);";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			while(rs2.next()) {
				int moduleID = rs2.getInt("ID");
				moduleName = rs2.getString("module_name");
				instructorName = rs2.getString("instructor_assigned");
				
				statement3.setInt(1, studentID);
				statement3.setString(2, studentName);
				statement3.setInt(3,moduleID);
				statement3.setString(4, moduleName);
				statement3.setString(5, instructorName);
				statement3.setInt(6,40);
				statement3.setInt(7,100);
				statement3.executeUpdate();
			}
			System.out.println("INSERT INTO TESTRECORD");
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// assign mark
	void assignMark(int moduleID, int marking, int studentID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createTestRecord();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);
			
			// assign marks to student
			String query = "UPDATE testrecord SET student_mark = ? WHERE module_id = ? and student_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, marking);
			statement.setInt(2, moduleID);
			statement.setInt(3, studentID);
			statement.executeUpdate();
			System.out.println("Marks Assigned");
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}	
}
