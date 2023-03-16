package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StudentReportGUI {

	private JFrame frame;
	private JTextField txtStudentName;
	private JTextField txtCourseName;
	private JTextField txtLevel;
	private JTextField studentID;
	private JTable table;
	static int sID;
	static int count = 0;
	ArrayList<Integer> marks = new ArrayList<>();
	static int studentLoggedID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentReportGUI window = new StudentReportGUI("", "");
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

	public StudentReportGUI(String userRole, String userLoggedEmail) {
		initialize(userRole, userLoggedEmail);

	}

	void testRecordTable(int studentID) {
		CheckConnection cc = new CheckConnection();
		cc.check();
		CreateTable ct = new CreateTable();
		ct.createTestRecord();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// get data from test record
			String query = "SELECT * FROM testrecord WHERE student_id = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentID);
//			statement.setInt(2, Instructor.tutorID);
			ResultSet rs = statement.executeQuery();
			// add table
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);

			// add rows
			String[] colName = new String[] { "Std ID", "Std Name", "Module ID", "Module Name", "P.M", "F.M", "Mark" };
			for (int i = 0; i < colName.length; i++) {
				model.setColumnIdentifiers(colName);
				table.getColumnModel().getColumn(1).setPreferredWidth(150);
				table.getColumnModel().getColumn(3).setPreferredWidth(150);

			}

			String stdID = Integer.toString(studentID);
			String studentName, moduleID, moduleName, passMark, fullMark, studentMark;
			while (rs.next()) {
				studentName = rs.getString("student_name");
				moduleID = rs.getString("module_id");
				moduleName = rs.getString("module_name");
				passMark = rs.getString("pass_mark");
				fullMark = rs.getString("full_mark");
				studentMark = rs.getString("student_mark");
				String[] row = { stdID, studentName, moduleID, moduleName, passMark, fullMark, studentMark };
				model.addRow(row);
			}

			System.out.println("TEST RECORD TABLE");
			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	// get user logged email
	void getStudentLoggedID(String userLoggedEmail) {
		CheckConnection cc = new CheckConnection();
		try {
			// load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			// check connection
			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);

			// get data from test record
			String query = "SELECT ID FROM USERS WHERE email = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, userLoggedEmail);
			ResultSet rs = statement.executeQuery();
			rs.next();
			studentLoggedID = rs.getInt("ID");

			// connection close
			connection.close();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String userRole, String userLoggedEmail) {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 532, 322);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		System.out.println(userRole);
		getStudentLoggedID(userLoggedEmail);

		JLabel lblStudentProgress = new JLabel("Student Report");
		lblStudentProgress.setBounds(0, 0, 239, 34);
		lblStudentProgress.setVerticalAlignment(SwingConstants.TOP);
		lblStudentProgress.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentProgress.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));

		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(24, 44, 70, 23);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));

		JLabel lblNewLabel_1_1 = new JLabel("Course:");
		lblNewLabel_1_1.setBounds(24, 70, 70, 23);
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblStudentProgress);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_1 = new JLabel("Level:");
		lblNewLabel_1_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_1_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(24, 103, 70, 23);
		frame.getContentPane().add(lblNewLabel_1_1_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(24, 142, 471, 121);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		frame.setVisible(true);

		studentID = new JTextField();
		studentID.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		studentID.setColumns(10);
		studentID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		studentID.setBackground(new Color(240, 240, 240));
		studentID.setBounds(370, 46, 85, 23);
		studentID.setEnabled(true);
		frame.getContentPane().add(studentID);

		txtStudentName = new JTextField();
		txtStudentName.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
		txtStudentName.setEditable(false);
		txtStudentName.setColumns(10);
		txtStudentName.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtStudentName.setBackground(new Color(240, 240, 240));
		txtStudentName.setBounds(94, 44, 133, 23);
		frame.getContentPane().add(txtStudentName);

		txtCourseName = new JTextField();
		txtCourseName.setToolTipText("");
		txtCourseName.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
		txtCourseName.setEditable(false);
		txtCourseName.setColumns(10);
		txtCourseName.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtCourseName.setBackground(new Color(240, 240, 240));
		txtCourseName.setBounds(94, 72, 133, 23);
		frame.getContentPane().add(txtCourseName);

		txtLevel = new JTextField();
		txtLevel.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
		txtLevel.setEditable(false);
		txtLevel.setColumns(10);
		txtLevel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtLevel.setBackground(new Color(240, 240, 240));
		txtLevel.setBounds(94, 103, 133, 23);
		frame.getContentPane().add(txtLevel);

		if (userRole.equals("Student")) {
			String loggedID = Integer.toString(studentLoggedID);
			System.out.println(loggedID + " " + studentLoggedID);
			studentID.setText(loggedID);
			studentID.setEnabled(false);
		}

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// search
				String stdID = studentID.getText();

				if (stdID.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Enter Student ID.", "Alert", JOptionPane.WARNING_MESSAGE);
					return;
				}
				try {
					sID = Integer.parseInt(stdID);
//					studentMarks(sID);
					Instructor ins = new Instructor();
					ins.testData(sID);
					testRecordTable(sID);
					Student.studentData(sID);
					txtStudentName.setText(Student.studentName);
					txtCourseName.setText(Student.courseName);
					txtLevel.setText(Student.level);
				} catch (NumberFormatException nfe) {
					System.out.println("ERROR");
					JOptionPane.showMessageDialog(frame, "Invalid Input.", "Alert", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnSearch.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		btnSearch.setBounds(263, 103, 85, 21);
		frame.getContentPane().add(btnSearch);

		JButton btnLevelUP = new JButton("Level Up");
		btnLevelUP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// level up

				if (userRole.equals("Admin")) {
					Admin admin = new Admin();
					admin.generateStudentReport(sID);
					System.out.println("\nStart");
					for (int i = 0; i < admin.marks.size(); i++) {
						System.out.println("MARKS");
						System.out.println(admin.marks.get(i));
						if (admin.marks.get(i) < 40) {
							System.out.println("Ineligible");
							JOptionPane.showMessageDialog(frame, "Ineligible to level up.", "Alert",
									JOptionPane.WARNING_MESSAGE);
							return;
						}
					}
					admin.levelUpStudent(sID);
				} else {
					// access denied for student
					JOptionPane.showMessageDialog(frame, "Access Denied.", "Alert", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnLevelUP.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		btnLevelUP.setBounds(370, 103, 85, 21);
		frame.getContentPane().add(btnLevelUP);

		JLabel lblNewLabel_1_2 = new JLabel("Student ID:");
		lblNewLabel_1_2.setToolTipText("Student ID: ");
		lblNewLabel_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(263, 50, 108, 23);
		frame.getContentPane().add(lblNewLabel_1_2);

	}
}
