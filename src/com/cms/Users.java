package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Users {
	
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private String role;
	ArrayList<String> checkDuplicate = new ArrayList<>();
	static String userName;
	static String userContact;
	static String userPassword;

	
	public Users() {
		// TODO Auto-generated constructor stub
	}

	public Users(String firstName, String lastName, String email, String phoneNumber, String password, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.role = role;
	}
	
	public static Users getUserFromRS(ResultSet user) {
		try {
			return new Users(
					user.getString("first_name"),user.getString("last_name"),user.getString("email"),user.getString("phone_number"),"",user.getString("role"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	// check duplicate email
	void checkDuplicateEmail() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createUserTable();
		
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String query = "SELECT * FROM Users";
			
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			
			while(set.next()) {
				checkDuplicate.add(set.getString("email"));
			}
			System.out.println(checkDuplicate);
			
			// connection close 
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// user profile
	void userProfile(String userEmail) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			// get user data
			String query = "SELECT * FROM USERS WHERE email = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userEmail);
			ResultSet set = statement.executeQuery();
			while(set.next()){
				userName = set.getString("first_name")+" "+set.getString("last_name");
				userContact = set.getString("phone_number");
				userPassword = set.getString("password");
			}
			System.out.println("USER DATA");
			
			// connection close 
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// update user
	void updateUserInfo(String newPassword, String userEmail) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			// get user data
			String query = "UPDATE USERS SET password = ? WHERE email = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, newPassword);
			statement.setString(2, userEmail);
			statement.executeUpdate();
			// connection close 
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
}
