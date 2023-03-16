package com.cms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class VerifyLogin {
	String email;
	String password;

	public VerifyLogin(String email, String password) {
		this.email = email;
		this.password = password;
	}

	boolean retriveUser() {
		CheckConnection cc = new CheckConnection();
		boolean userValid = false;
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			String sql = "SELECT * FROM USERS WHERE email = ? AND password = ?";

			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				String dbEmail = rs.getString("email");
				String dbPassword = rs.getString("password");
				if (email.matches(dbEmail) && password.matches(dbPassword)) {
					System.out.println("Matched");
					userValid = true;
				}

				System.out.println(email);
				System.out.println(password);
			}

			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return userValid;
	}
}
