package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dashboard {

	private JFrame frame;
	private JTable courseTable;
	private JTable tutorTable;
	private JTable studentTable;
	private JTable userTable;
	private JTable moduleTable;
	static String userLoggedEmail;
	static String userRole;
	static String userName;
	CardLayout cl = new CardLayout(0, 0);
	int countCourse;
	
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard("");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the application.
	 */
	public Dashboard(String userLoggedEmail) {
		Dashboard.userLoggedEmail = userLoggedEmail;
		initialize();
	}
	
	Dashboard(){
		initialize();
	}
	
	// user type
	public static void userType() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String sql = "SELECT * FROM USERS WHERE email = ?";
			
			PreparedStatement st = connection.prepareStatement(sql);
			st.setString(1, userLoggedEmail);
			ResultSet rs = st.executeQuery();
			
			rs.next();
			userRole = rs.getString("role");
			System.out.println(userRole);
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
//		return role;
	}
	
	//course table
	void courseData() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			String query = "SELECT * FROM COURSES;";
			
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			DefaultTableModel model = (DefaultTableModel) courseTable.getModel();
			model.setRowCount(0);
			
			// Add column title
			String[] colName = new String[] {"ID", "Course Name", "Seat", "Batch", "Duration"};
			for (int i = 0; i< colName.length; i++) {
				model.setColumnIdentifiers(colName);
			}
			
			
			// Add rows
			String std_id, course_name, seat, batch, duration;
			while(set.next()) {
				std_id = set.getString("id");
				course_name = set.getString("course_name");
				seat = set.getString("seats");
				batch = set.getString("batch");
				duration = set.getString("duration");
				String[] row = {std_id,course_name,seat,batch,duration};
				model.addRow(row);
			}
			
			// connection close 
			connection.close();
		}catch(ClassNotFoundException ee){
			System.out.println(ee);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	void countCourse() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String sql = "SELECT COUNT(*) FROM COURSES;";
			
			PreparedStatement st = connection.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			rs.next();
			countCourse = rs.getInt("COUNT(*)");
			System.out.println(countCourse);
			// connection close 
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// TUTOR table
	void tutorData() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			String query = "SELECT * FROM USERS WHERE ROLE = 'Instructor';";
			
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			DefaultTableModel model = (DefaultTableModel) tutorTable.getModel();
			model.setRowCount(0);
			
			// Add column title
			String[] colName = new String[] {"ID", "Tutor Name", "Email", "Phone number"};
			for (int i = 0; i< colName.length; i++) {
				model.setColumnIdentifiers(colName);
			}
			
			
			// Add rows
			String tutor_id, tutorName, email, phoneNumber;
			while(set.next()) {
				tutor_id = set.getString("id");
				tutorName = set.getString("first_name")+" "+set.getString("last_name");
				email = set.getString("email");
				phoneNumber = set.getString("phone_number");
				String[] row = {tutor_id,tutorName,email,phoneNumber};
				model.addRow(row);
			}
			
			// connection close 
			connection.close();
		}catch(ClassNotFoundException ee){
			System.out.println(ee);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// enroll table
	void enrollTable() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			String query = "SELECT * FROM ENROLLED;";
			
			Statement statement = connection.createStatement();
			ResultSet set = statement.executeQuery(query);
			DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
			model.setRowCount(0);
			
			// Add column title
			String[] colName = new String[] {"ID", "Student Name", "Email", "Phone number", "Date", "Course","Level"};
			for (int i = 0; i< colName.length; i++) {
				model.setColumnIdentifiers(colName);
				
			}
			
			// Add rows
			String student_id, studentName, email, phoneNumber,date,courseName,level;
			while(set.next()) {
				student_id = set.getString("id");
				studentName = set.getString("student_name");
				email = set.getString("email");
				phoneNumber = set.getString("phone_number");
				date = set.getString("date");
				courseName = set.getString("course_name");
				level = set.getString("level");
				String[] row = {student_id,studentName,email,phoneNumber, date, courseName,level};
				model.addRow(row);
			}
			
			// connection close 
			connection.close();
		}catch(ClassNotFoundException ee){
			System.out.println(ee);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	// name of user
	void userLoggedActivity() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			String query = "SELECT * FROM USERS WHERE email = ?";
			
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, userLoggedEmail);
			ResultSet rs = st.executeQuery();
			DefaultTableModel model = (DefaultTableModel)userTable.getModel();
			model.setRowCount(0);
			String[] colName = new String[] {"Activity"};
			for (int i = 0; i< colName.length; i++) {
				model.setColumnIdentifiers(colName);
			}
			
			// add rows
			while(rs.next()) {
				userName = rs.getString("role")+" "+rs.getString("first_name")+" "+rs.getString("last_name")+" has logged in.";
				String[] row = {userName};
				model.addRow(row);
			}
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	
	void moduleTable() {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");	
			
			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr+"/CMS", cc.usernameString, cc.password);
			
			// module table as condition
			if (userRole.equals("Student")) {
				String query = "SELECT * FROM ENROLLED WHERE email = ?";
				PreparedStatement statement = connection.prepareStatement(query);
				statement.setString(1, userLoggedEmail);
				ResultSet rs = statement.executeQuery();
				rs.next();
				String courseName = rs.getString("course_name");
				String level = rs.getString("level");
				System.out.println(userLoggedEmail+" "+level);
				String query1 = "SELECT * FROM MODULES WHERE course_name = ? AND level = ?;";
				PreparedStatement statement1 = connection.prepareStatement(query1);
				statement1.setString(1, courseName);
				statement1.setString(2, level);
				ResultSet set = statement1.executeQuery();
				
				DefaultTableModel model = (DefaultTableModel)moduleTable.getModel();
				model.setRowCount(0);
				String[] colName = new String[] {"ID","Module Name","Course Name","Credit Score","Level"};
				for (int i = 0; i< colName.length; i++) {
					model.setColumnIdentifiers(colName);
					moduleTable.getColumnModel().getColumn(0).setPreferredWidth(10);
					moduleTable.getColumnModel().getColumn(1).setPreferredWidth(150);
				}
				
				String ID, moduleName, moduleCourseName, creditScore, moduleLevel;
				// add rows
				while(set.next()) {
					ID = Integer.toString(set.getInt("ID"));
					moduleName = set.getString("module_name");
					moduleCourseName = set.getString("course_name");
					creditScore = set.getString("credits");
					moduleLevel = set.getString("level");
					String[] row = {ID,moduleName,moduleCourseName,creditScore,moduleLevel};
					model.addRow(row);
				}
				
			} else {
				// All module table
				String query = "SELECT * FROM MODULES";
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				DefaultTableModel model = (DefaultTableModel)moduleTable.getModel();
				model.setRowCount(0);
				String[] colName = new String[] {"ID","Module Name","Course Name","Credit Score","Level"};
				for (int i = 0; i< colName.length; i++) {
					model.setColumnIdentifiers(colName);
					moduleTable.getColumnModel().getColumn(0).setPreferredWidth(10);
					moduleTable.getColumnModel().getColumn(1).setPreferredWidth(150);
				}
				
				String ID, moduleName, courseName, creditScore,level;
				// add rows
				while(rs.next()) {
					ID = Integer.toString(rs.getInt("ID"));
					moduleName = rs.getString("module_name");
					courseName = rs.getString("course_name");
					creditScore = rs.getString("credits");
					level = rs.getString("level");
					String[] row = {ID,moduleName,courseName,creditScore,level};
					model.addRow(row);
				}
			
			}
			System.out.println("Module Table");
			// connection close 
			connection.close();
		}catch(ClassNotFoundException e){
			System.out.println(e);
		}catch(SQLException ex) {
			System.out.println(ex);
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 914, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		System.out.println(userLoggedEmail);
		userType();
		new Instructor(userLoggedEmail);
		Student.studentLoggedEmail = userLoggedEmail;
		
		JPanel navPanel = new JPanel();
		navPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		navPanel.setBounds(0, 0, 900, 55);
		frame.getContentPane().add(navPanel);
		navPanel.setLayout(null);
		
		JLabel userNav = new JLabel("User: "+userRole);
		userNav.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// user profile
				new UserProfileGUI(userLoggedEmail);
			}
		});
		userNav.setForeground(new Color(91, 89, 89));
		userNav.setFont(new Font("Roboto", Font.BOLD, 14));
		userNav.setBounds(781, 16, 115, 29);
		navPanel.add(userNav);
		
		JLabel lblCourseManagementSystem = new JLabel("Course Management System");
		lblCourseManagementSystem.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCourseManagementSystem.setBounds(12, 13, 341, 29);
		navPanel.add(lblCourseManagementSystem);
		
		JPanel bodyPanel = new JPanel();
		bodyPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		bodyPanel.setBounds(0, 55, 900, 421);
		frame.getContentPane().add(bodyPanel);
		bodyPanel.setLayout(null);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 895, 425);
		splitPane.setEnabled(false);	// not resize-able
		splitPane.setDividerSize(5);
		bodyPanel.add(splitPane);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(211, 211, 211));
		leftPanel.setPreferredSize(new Dimension(130,100));
		splitPane.setLeftComponent(leftPanel);
		
		JPanel rightPanel = new JPanel();
		splitPane.setRightComponent(rightPanel);
		rightPanel.setLayout(cl);
		
		JPanel pnlCard1 = new JPanel();
		pnlCard1.setBackground(new Color(240, 240, 240));
		rightPanel.add(pnlCard1, "name_441887323268300");
		pnlCard1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(91, 56, 137, 83);
		pnlCard1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Total Courses");
		lblNewLabel_1.setBounds(12, 13, 113, 19);
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 15));
		panel.add(lblNewLabel_1);
		
		countCourse();
		String nC = Integer.toString(countCourse);
		JLabel numOfCourses = new JLabel(nC);
		numOfCourses.setBounds(61, 30, 30, 40);
		numOfCourses.setFont(new Font("Tahoma", Font.BOLD, 30));
		panel.add(numOfCourses);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBounds(306, 56, 137, 83);
		pnlCard1.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("Total Tutors");
		lblNewLabel_1_1.setBounds(12, 13, 113, 19);
		lblNewLabel_1_1.setFont(new Font("Consolas", Font.BOLD, 15));
		panel_1.add(lblNewLabel_1_1);
		
		Instructor.numberOfTutors();
		String nI = Instructor.numberOfTutors;
		JLabel numOfTutors = new JLabel(nI);
		numOfTutors.setFont(new Font("Tahoma", Font.BOLD, 30));
		numOfTutors.setBounds(51, 30, 30, 40);
		panel_1.add(numOfTutors);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBounds(523, 56, 137, 83);
		pnlCard1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Total Students");
		lblNewLabel_1_2.setBounds(12, 13, 113, 19);
		lblNewLabel_1_2.setFont(new Font("Consolas", Font.BOLD, 15));
		panel_2.add(lblNewLabel_1_2);
		
		Student.numberOfStudents();
		String nS = Student.numOfStudents;
		System.out.println(nS);
		JLabel numOfStudents = new JLabel(nS);
		numOfStudents.setFont(new Font("Tahoma", Font.BOLD, 30));
		numOfStudents.setBounds(58, 30, 30, 40);
		panel_2.add(numOfStudents);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBounds(0, 0, 768, 36);
		pnlCard1.add(panel_3);
		
		JLabel lblNewLabel = new JLabel("Dashboard");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_3.add(lblNewLabel);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(91, 181, 571, 196);
		pnlCard1.add(scrollPane_2);
		
		userTable = new JTable();
		userTable.setShowHorizontalLines(false);
		userTable.setShowVerticalLines(false);
		userTable.setEnabled(false);

		userLoggedActivity();
		
		scrollPane_2.setViewportView(userTable);
		
		JPanel pnlCard2 = new JPanel();
		pnlCard2.setBackground(new Color(240, 240, 240));
		rightPanel.add(pnlCard2, "name_441923584040700");
		pnlCard2.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setBounds(0, 0, 793, 36);
		panel_3_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlCard2.add(panel_3_1);
		
		JLabel lblCourses = new JLabel("Courses");
		lblCourses.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_3_1.add(lblCourses);
		
		JButton addCourseBtn = new JButton("Add Course");
		addCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new AddCourseGUI();
					frame.setVisible(false);
				}
			}
		});
		addCourseBtn.setBounds(179, 51, 113, 25);
		pnlCard2.add(addCourseBtn);
		
		JButton editCourseBtn = new JButton("Edit Course");
		editCourseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new EditCourseGUI();
				}
			}
		});
		editCourseBtn.setBounds(343, 51, 113, 25);
		pnlCard2.add(editCourseBtn);
		
		JButton btnDeleteCourse = new JButton("Delete Course");
		btnDeleteCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new DeleteCourseGUI();
				}
			}
		});
		btnDeleteCourse.setBounds(505, 51, 120, 25);
		pnlCard2.add(btnDeleteCourse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 94, 738, 319);
		pnlCard2.add(scrollPane);
		
		courseTable = new JTable();
		courseTable.setShowHorizontalLines(false);
		courseTable.setShowVerticalLines(false);
		courseTable.setEnabled(false);
		scrollPane.setViewportView(courseTable);
		
		JPanel pnlCard3 = new JPanel();
		pnlCard3.setBackground(new Color(240, 240, 240));
		rightPanel.add(pnlCard3, "name_449845754565900");
		pnlCard3.setLayout(null);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setBounds(0, 0, 793, 36);
		panel_3_1_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlCard3.add(panel_3_1_1);
		
		JLabel lblTutors = new JLabel("Tutors");
		lblTutors.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_3_1_1.add(lblTutors);
		
		JButton btnEditTutors = new JButton("Edit Tutors");
		btnEditTutors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// edit tutor
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new EditTutorGUI();
				}
			}
		});
		btnEditTutors.setBounds(97, 46, 113, 25);
		pnlCard3.add(btnEditTutors);
		
		JButton btnDeleteTutors = new JButton("Delete Tutors");
		btnDeleteTutors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// delete tutor
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new DeleteTutorGUI();
				}
			}
		});
		btnDeleteTutors.setBounds(248, 46, 120, 25);
		pnlCard3.add(btnDeleteTutors);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 94, 738, 319);
		pnlCard3.add(scrollPane_1);
		
		tutorTable = new JTable();
		tutorTable.setShowHorizontalLines(false);
		tutorTable.setShowVerticalLines(false);
		tutorTable.setEnabled(false);
		scrollPane_1.setViewportView(tutorTable);
		
		JButton btnTutorModules = new JButton("Tutor Modules");
		btnTutorModules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// tutor modules
				new TutorAssignedModulesGUI();
			}
		});
		btnTutorModules.setBounds(560, 46, 120, 25);
		pnlCard3.add(btnTutorModules);
		
		JButton btnAssignTutors = new JButton("Assign Tutors");
		btnAssignTutors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Assign Tutor
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new AssignTutorGUI();
				}
			}
		});
		btnAssignTutors.setBounds(404, 46, 120, 25);
		pnlCard3.add(btnAssignTutors);
		
		JPanel pnlCard4 = new JPanel();
		pnlCard4.setBackground(new Color(240, 240, 240));
		rightPanel.add(pnlCard4, "name_449847075383100");
		pnlCard4.setLayout(null);
		
		JPanel panel_3_1_1_1 = new JPanel();
		panel_3_1_1_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3_1_1_1.setBounds(0, 0, 793, 36);
		pnlCard4.add(panel_3_1_1_1);
		
		JLabel lblCourses_1_1 = new JLabel("Students");
		lblCourses_1_1.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_3_1_1_1.add(lblCourses_1_1);
		
		JButton btnEditStudent = new JButton("Edit Student");
		btnEditStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// edit student
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new EditStudentGUI();
				}
			}
		});
		btnEditStudent.setBounds(22, 46, 113, 25);
		pnlCard4.add(btnEditStudent);
		
		JButton btnDeleteStudent = new JButton("Delete Student");
		btnDeleteStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// delete student
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new DeleteStudentGUI();
				}
			}
		});
		btnDeleteStudent.setBounds(161, 46, 120, 25);
		pnlCard4.add(btnDeleteStudent);
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setBounds(10, 94, 738, 319);
		pnlCard4.add(scrollPane_1_1);
		
		studentTable = new JTable();
		studentTable.setShowHorizontalLines(false);
		studentTable.setShowVerticalLines(false);
		studentTable.setEnabled(false);
		scrollPane_1_1.setViewportView(studentTable);
		
		JButton btnEnrollStudent = new JButton("Enroll Student");
		btnEnrollStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// enroll students
				if (!userRole.equals("Student")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				} else { 
					System.out.println("HELLO");
					System.out.println(Student.alreadyEnrolled(userLoggedEmail));
					if (Student.alreadyEnrolled(userLoggedEmail)){
						JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
					} else {
						new EnrollGUI();											
					}
				}
			}
		});
		btnEnrollStudent.setBounds(308, 46, 120, 25);
		pnlCard4.add(btnEnrollStudent);
		
		JButton studentReport = new JButton("Create Report");
		studentReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// student report
				if (!userRole.equals("Instructor")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				} else {
					new StudentMarkingGUI();
				}
			}
		});
		studentReport.setBounds(602, 46, 120, 25);
		pnlCard4.add(studentReport);
		
		JButton viewProgress = new JButton("View Progress");
		viewProgress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// view progress as per user
				if (userRole.equals("Admin") || userRole.equals("Student")) {
					new StudentReportGUI(userRole,userLoggedEmail);					
				}
				else {
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		viewProgress.setBounds(455, 46, 120, 25);
		pnlCard4.add(viewProgress);
		
		JPanel pnlCard5 = new JPanel();
		pnlCard5.setBackground(new Color(240, 240, 240));
		rightPanel.add(pnlCard5, "name_449859408152500");
		pnlCard5.setLayout(null);
		
		JPanel panel_3_1_1_2 = new JPanel();
		panel_3_1_1_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3_1_1_2.setBounds(0, 0, 793, 36);
		pnlCard5.add(panel_3_1_1_2);
		
		JLabel lblCourses_1_2 = new JLabel("Modules");
		lblCourses_1_2.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_3_1_1_2.add(lblCourses_1_2);
		
		JScrollPane scrollPane_1_1_1 = new JScrollPane();
		scrollPane_1_1_1.setBounds(10, 94, 738, 319);
		pnlCard5.add(scrollPane_1_1_1);
		
		moduleTable = new JTable();
		moduleTable.setEnabled(false);
		moduleTable.setShowHorizontalLines(false);
		moduleTable.setShowVerticalLines(false);
		scrollPane_1_1_1.setViewportView(moduleTable);
		
		JButton btnEditModule = new JButton("Edit Module");
		btnEditModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// edit module
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new EditModuleGUI();
				}
			}
		});
		btnEditModule.setBounds(221, 46, 113, 25);
		pnlCard5.add(btnEditModule);
		
		JButton btnDeleteModule = new JButton("Delete Module");
		btnDeleteModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// delete module
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new DeleteModuleGUI();
				}				
			}
		});
		btnDeleteModule.setBounds(503, 46, 120, 25);
		pnlCard5.add(btnDeleteModule);
		
		JButton btnAddModule = new JButton("Add Module");
		btnAddModule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add new module
				if (!userRole.equals("Admin")) {
					System.out.println("Access Denied");
					JOptionPane.showMessageDialog(frame,"Access Denied.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				else {
					new AddModuleGUI();
				}
			}
		});
		btnAddModule.setBounds(358, 46, 120, 25);
		pnlCard5.add(btnAddModule);
		
		JPanel pnlCard6 = new JPanel();
		pnlCard6.setBackground(new Color(160, 82, 45));
		rightPanel.add(pnlCard6, "name_449859438842000");
		pnlCard6.setLayout(null);
		
		JPanel panel_3_1_1_3 = new JPanel();
		panel_3_1_1_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3_1_1_3.setBounds(0, 0, 793, 36);
		pnlCard6.add(panel_3_1_1_3);
		
		JLabel lblCourses_1_3 = new JLabel("Log out");
		lblCourses_1_3.setFont(new Font("Consolas", Font.BOLD, 20));
		panel_3_1_1_3.add(lblCourses_1_3);
		frame.setVisible(true);
		
		JButton btnNewButton = new JButton("Dashboard");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 1
				cl.show(rightPanel, "name_441887323268300");
			}
		});
		btnNewButton.setBounds(12, 45, 104, 34);
		btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		
		JButton btnCourses = new JButton("Courses");
		btnCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 2
				cl.show(rightPanel, "name_441923584040700");
				courseData();
			}   
		});
		btnCourses.setBounds(12, 102, 104, 34);
		btnCourses.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCourses.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		
		JButton btnStudents = new JButton("Students");
		btnStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 4
				cl.show(rightPanel, "name_449847075383100");
//				dropTableEnroll();
				enrollTable();
				InsertData id = new InsertData();
				id.userStudentIntoEnrollTable();
			}
		});
		btnStudents.setBounds(12, 220, 104, 34);
		btnStudents.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnStudents.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		
		JButton btnInstructors = new JButton("Tutors");
		btnInstructors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 3
				cl.show(rightPanel, "name_449845754565900");
				tutorData();
			}
		});
		btnInstructors.setBounds(12, 161, 104, 34);
		btnInstructors.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnInstructors.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 6
				cl.show(rightPanel, "name_449859438842000");
				new Login();
				frame.setVisible(false);
			}
		});
		btnLogOut.setBounds(12, 333, 104, 34);
		btnLogOut.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnLogOut.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		
		JButton btnAccount = new JButton("Modules");
		btnAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 5
				cl.show(rightPanel, "name_449859408152500");
				moduleTable();
			}
		});
		btnAccount.setBounds(12, 277, 104, 34);
		btnAccount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnAccount.setFont(new Font("MS UI Gothic", Font.BOLD, 13));
		leftPanel.setLayout(null);
		leftPanel.add(btnNewButton);
		leftPanel.add(btnCourses);
		leftPanel.add(btnStudents);
		leftPanel.add(btnInstructors);
		leftPanel.add(btnLogOut);
		leftPanel.add(btnAccount);		
	}
}
