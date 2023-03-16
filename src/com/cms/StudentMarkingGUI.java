package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class StudentMarkingGUI {

	private JFrame frame;
	private JTextField studentID;
	private JTextField course;
	private JTextField moduleID;
	private JTextField marks;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMarkingGUI window = new StudentMarkingGUI();
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
	public StudentMarkingGUI() {
		initialize();
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 503, 314);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JLabel lblStudentReport = new JLabel("Student Report");
		lblStudentReport.setBounds(0, 0, 235, 40);
		lblStudentReport.setVerticalAlignment(SwingConstants.TOP);
		lblStudentReport.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentReport.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		frame.getContentPane().add(lblStudentReport);
		frame.setVisible(true);

		studentID = new JTextField();
		studentID.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		studentID.setBackground(new Color(240, 240, 240));
		studentID.setBounds(120, 47, 108, 23);
		frame.getContentPane().add(studentID);
		studentID.setColumns(10);
		studentID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));

		Instructor ins = new Instructor();

		JButton assignBtn = new JButton("Assign");
		assignBtn.setBounds(358, 116, 85, 21);
		frame.getContentPane().add(assignBtn);
		assignBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// assign

				String mID = moduleID.getText();
				String score = marks.getText();
				String stID = studentID.getText();
				if (score.isEmpty() || mID.isEmpty()) {
					JOptionPane.showMessageDialog(frame, "Invalid Input.", "Alert", JOptionPane.WARNING_MESSAGE);
					return;
				}

				try {

					int moduleID = Integer.parseInt(mID);
					int marking = Integer.parseInt(score);
					int studentID = Integer.parseInt(stID);
					if (marking < 0 || marking > 100) {
						JOptionPane.showMessageDialog(frame, "Invalid Input.", "Alert", JOptionPane.WARNING_MESSAGE);
						return;
					}
					ins.assignMark(moduleID, marking, studentID);
					return;
				} catch (NumberFormatException nfe) {
					System.out.println("ERROR");
					JOptionPane.showMessageDialog(frame, "Invalid Input.", "Alert", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		assignBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));

		JLabel lblNewLabel_1_2 = new JLabel("Student ID:");
		lblNewLabel_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2.setToolTipText("Student ID: ");
		lblNewLabel_1_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(24, 50, 108, 23);
		frame.getContentPane().add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_2_1 = new JLabel("Module ID:");
		lblNewLabel_1_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_1.setToolTipText("Student ID: ");
		lblNewLabel_1_2_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_2_1.setBounds(266, 50, 108, 23);
		frame.getContentPane().add(lblNewLabel_1_2_1);

		course = new JTextField();
		course.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		course.setBackground(new Color(240, 240, 240));
		course.setColumns(10);
		course.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(240, 240, 240)));
		course.setBounds(120, 85, 108, 23);
		frame.getContentPane().add(course);

		JLabel lblNewLabel_1_2_2 = new JLabel("Course:");
		lblNewLabel_1_2_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_2.setToolTipText("Student ID: ");
		lblNewLabel_1_2_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_2_2.setBounds(24, 83, 108, 23);
		frame.getContentPane().add(lblNewLabel_1_2_2);

		JLabel lblNewLabel_1_2_3 = new JLabel("Marks");
		lblNewLabel_1_2_3.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_2_3.setToolTipText("Student ID: ");
		lblNewLabel_1_2_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_2_3.setBounds(266, 83, 108, 23);
		frame.getContentPane().add(lblNewLabel_1_2_3);

		moduleID = new JTextField();
		moduleID.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		moduleID.setBackground(new Color(240, 240, 240));
		moduleID.setColumns(10);
		moduleID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		moduleID.setBounds(358, 47, 108, 23);
		frame.getContentPane().add(moduleID);

		marks = new JTextField();
		marks.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		marks.setBackground(new Color(240, 240, 240));
		marks.setColumns(10);
		marks.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		marks.setBounds(358, 80, 108, 23);
		frame.getContentPane().add(marks);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// search
				String stdID = studentID.getText();

				try {
					int studentID = Integer.parseInt(stdID);
					ins.testData(studentID);
					testRecordTable(studentID);
					course.setText(Instructor.courseName);
				} catch (NumberFormatException nfe) {
					System.out.println("ERROR");
					JOptionPane.showMessageDialog(frame, "Enter Student ID.", "Alert", JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnSearch.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnSearch.setBounds(120, 116, 85, 21);
		frame.getContentPane().add(btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPane.setBounds(10, 146, 469, 121);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

	}
}
