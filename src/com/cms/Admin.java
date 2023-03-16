package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends Users {

	// course
	String courseName, courseSeats, courseBatch, courseDuration = "3";

	// modules
	String moduleName, creditScore = "20", instructor, level;

	ArrayList<Integer> marks = new ArrayList<>();
	private String studentLevel = Student.level;
	private String newLevel;

	// constructor
	Admin() {

	}

	public Admin(String firstName, String lastName, String email, String phoneNUmber, String password, String role) {
		super(firstName, lastName, email, phoneNUmber, password, role);
		// TODO Auto-generated constructor stub
	}

	// add new course
	void addCourse() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createCourseTable();

		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			String query = "INSERT INTO COURSES(course_name,seats,batch,duration) VALUES (?,?,?,?);";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, courseName);
			statement.setString(2, courseSeats);
			statement.setString(3, courseBatch);
			statement.setString(4, courseDuration);
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

	// edit course (name, modules names etc)
	void editCourse(int newSeats, int newBatch, String newCourseName, String oldCourseName) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// update course
			String query = "UPDATE COURSES SET course_name = ?, seats = ?, batch = ? WHERE course_name = ?;";
			PreparedStatement statement = connection.prepareStatement(query);

			String stringSeat = Integer.toString(newSeats);
			String stringBatch = Integer.toString(newBatch);

			statement.setString(1, newCourseName);
			statement.setString(2, stringSeat);
			statement.setString(3, stringBatch);
			statement.setString(4, oldCourseName);

			// update on module
			String query1 = "UPDATE MODULES SET course_name = ? WHERE course_name = ?;";
			PreparedStatement statement1 = connection.prepareStatement(query1);
			statement1.setString(1, newCourseName);
			statement1.setString(2, oldCourseName);
			
			// update in corresponding enrolled table
			String query2 = "UPDATE ENROLLED SET course_name = ? WHERE course_name = ?;";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setString(1, newCourseName);
			statement2.setString(2, oldCourseName);

			statement.executeUpdate();
			statement1.executeUpdate();
			statement2.executeUpdate();

			System.out.println("Course Updated");
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// delete course
	void deleteCourse(int courseID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// extract course name
			String query1 = "SELECT * FROM COURSES WHERE ID = " + courseID + ";";
			PreparedStatement statement1 = connection.prepareStatement(query1);
			ResultSet rs = statement1.executeQuery(query1);
			rs.next();
			courseName = rs.getString("course_name");
			System.out.println(courseName);

			// deleteCourse
			String query2 = "DELETE FROM COURSES WHERE ID = ?;";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setInt(1, courseID);
			statement2.executeUpdate();

			// delete corresponding module
			String query3 = "DELETE FROM MODULES WHERE course_name = ?;";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement3.setString(1, courseName);
			statement3.executeUpdate();

			// set value null to corresponding enrolled;
			String query4 = "UPDATE ENROLLED SET course_name = null, date = null WHERE course_name = ?;";
			PreparedStatement statement4 = connection.prepareStatement(query4);
			statement4.setString(1, courseName);
			statement4.executeUpdate();
			

			System.out.println("COURSE DELETED");
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// add modules inside course
	void addModules() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createModuleTable();

		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			String query = "INSERT INTO MODULES(module_name,course_name,credits,instructor_assigned,level) VALUES (?,?,?,?,?);";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, moduleName);
			statement.setString(2, courseName);
			statement.setString(3, creditScore);
			statement.setString(4, instructor);
			statement.setString(5, level);
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

	// edit module
	void editModule(int id, String moduleName) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// update module name
			String query = "UPDATE modules SET module_name = ? WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, moduleName);
			statement.setInt(2, id);
			statement.executeUpdate();
			System.out.println("Module updated");
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// add new module
	void addNewModule(String moduleName, String courseName, String level) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createModuleTable();

		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			String query = "INSERT INTO MODULES(module_name,course_name,credits,level) VALUES (?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, moduleName);
			statement.setString(2, courseName);
			statement.setString(3, creditScore);
			statement.setString(4, level);
			statement.executeUpdate();
			System.out.println("NEW MODULE ADDED");
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// delete course
	void deleteModule(int moduleID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// delete module
			String query3 = "DELETE FROM MODULES WHERE id = ?;";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement3.setInt(1, moduleID);
			statement3.executeUpdate();

			System.out.println("MODULE DELETED");
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// edit tutor detail
	void editTutor(int newTutorID, String newTutorEmail, String newTutorPhoneNumber) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			String query = "UPDATE USERS SET email = ?, phone_number = ? WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);

			statement.setString(1, newTutorEmail);
			statement.setString(2, newTutorPhoneNumber);
			statement.setInt(3, newTutorID);
			statement.executeUpdate();
			System.out.println("TUTOR Updated");
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// Delete Tutor
	void deleteTutor(int newTutorID) {
		String tutorName;
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// extract tutor name
			String query1 = "SELECT * FROM USERS WHERE ID = " + newTutorID + ";";
			PreparedStatement statement1 = connection.prepareStatement(query1);
			ResultSet rs = statement1.executeQuery(query1);
			rs.next();
			tutorName = rs.getString("first_name") + " " + rs.getString("last_name");
			System.out.println(tutorName);

			// delete tutor from users
			String query2 = "DELETE FROM USERS WHERE role = 'Instructor' AND ID = ?;";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setInt(1, newTutorID);
			statement2.executeUpdate();

			// delete on corresponding module
			String query3 = "UPDATE MODULES SET instructor_assigned = NULL WHERE instructor_assigned = ?;";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement3.setString(1, tutorName);
			statement3.executeUpdate();

			System.out.println("TUTOR DELETED");
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	void assignTutor(int moduleID, String tutorName) {
		System.out.println(moduleID + " " + tutorName);
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createModuleTable();

		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			String query = "UPDATE MODULES SET instructor_assigned = ? WHERE ID = ?;";

			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, tutorName);
			statement.setInt(2, moduleID);
			statement.executeUpdate();
			System.out.println("TUTOR ASSIGNED");
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// edit student detail
	void editStudent(int studentID, String newStudentEmail, String newStudentPhoneNumber) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// edit in user
			String query1 = "UPDATE USERS SET email = ?, phone_number = ? WHERE ID = ?;";
			PreparedStatement statement1 = connection.prepareStatement(query1);
			statement1.setString(1, newStudentEmail);
			statement1.setString(2, newStudentPhoneNumber);
			statement1.setInt(3, studentID);
			statement1.executeUpdate();

			// edit in enrolled table
			String query = "UPDATE ENROLLED SET email = ?, phone_number = ? WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newStudentEmail);
			statement.setString(2, newStudentPhoneNumber);
			statement.setInt(3, studentID);
			statement.executeUpdate();

			System.out.println("STUDENT Updated");
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// Delete Tutor
	void deleteStudent(int studentID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// delete student from users
			String query2 = "DELETE FROM USERS WHERE role = 'Student' AND ID = ?;";
			PreparedStatement statement2 = connection.prepareStatement(query2);
			statement2.setInt(1, studentID);
			statement2.executeUpdate();

			// delete corresponding enroll table
			String query3 = "DELETE FROM ENROLLED WHERE ID = ?;";
			PreparedStatement statement3 = connection.prepareStatement(query3);
			statement3.setInt(1, studentID);
			statement3.executeUpdate();

			System.out.println("STUDENT DELETED");
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// student report level up
	void generateStudentReport(int studentID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createTestRecord();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// get Student marks
			String query = "SELECT * FROM TESTRECORD WHERE student_id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentID);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				int mark = rs.getInt("student_mark");
				marks.add(mark);
			}

			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	void levelUpStudent(int studentID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createTestRecord();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			System.out.println("Student Level: " + studentLevel);
			int defaultLevel = Integer.parseInt(studentLevel);
			System.out.println("OLD LEVEL: " + defaultLevel);
			if (defaultLevel == 4) {
				newLevel = "5";
			} else if (defaultLevel == 5) {
				newLevel = "6";
			} else if (defaultLevel == 6) {
				newLevel = "Graduated";
			}

			// level up student
			String query1 = "UPDATE ENROLLED SET level = ? WHERE ID = ?;";
			PreparedStatement st = connection.prepareStatement(query1);
			st.setString(1, newLevel);
			st.setInt(2, studentID);

			System.out.println("NEW LEVEL: " + newLevel);
			System.out.println("LEVEL UP");
			st.executeUpdate();
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
