package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class TutorAssignedModulesGUI {

	private JFrame frame;
	private JTextField searchText;
	private JTable table;
	int tutorID;
	String tutorName, courseName;
	static ArrayList<Integer> arr = new ArrayList<>();
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TutorAssignedModulesGUI window = new TutorAssignedModulesGUI();
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
	public TutorAssignedModulesGUI() {
		initialize();
	}
	
	void tutorInfo() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);
			
			// extract tutor name
			String query = "SELECT * FROM USERS WHERE ID = ?;";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, tutorID);
			ResultSet rs = statement.executeQuery();
			rs.next();
			tutorName = rs.getString("first_name")+" "+rs.getString("last_name");
			System.out.println(tutorName);
			
			DefaultTableModel model = (DefaultTableModel)table.getModel();
			model.setRowCount(0);

			// get tutor modules 
			String query2 = "SELECT * FROM MODULES WHERE instructor_assigned = ?;";
			PreparedStatement statement1 = connection.prepareStatement(query2);
			statement1.setString(1, tutorName);
			ResultSet rs2 = statement1.executeQuery();
			
			// Add column title
			String[] colName = new String[] {"Modules"};
			for (int i = 0; i< colName.length; i++) {
				model.setColumnIdentifiers(colName);
			}
			
			// Add rows
			String moduleName;
			while(rs2.next()) {
				moduleName = rs2.getString("module_name");
				System.out.println(moduleName);
				String[] row = {moduleName};
				model.addRow(row);
			}
			
			System.out.println("TUTOR INFO");
			connection.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
	
	
	void tutorID() {
		CheckConnection cc = new CheckConnection();
		cc.check();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = DriverManager.getConnection(cc.urlStr + "/CMS", cc.usernameString, cc.password);
			
			// instructor id
			String insQuery = "SELECT * FROM USERS WHERE role = 'instructor';";
			Statement st = connection.createStatement();
			ResultSet rsq = st.executeQuery(insQuery);
			while(rsq.next()) {
				arr.add(rsq.getInt("ID"));
			}
			
			System.out.println("Updated");
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
		frame.setBounds(100, 100, 437, 401);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblTutorModules = new JLabel("Tutor Modules");
		lblTutorModules.setVerticalAlignment(SwingConstants.TOP);
		lblTutorModules.setHorizontalAlignment(SwingConstants.CENTER);
		lblTutorModules.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		lblTutorModules.setBounds(0, 10, 200, 36);
		frame.getContentPane().add(lblTutorModules);
		
		JLabel lblNewLabel = new JLabel("Tutor Name:");
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 56, 100, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel tutorNameText = new JLabel();
		tutorNameText.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		tutorNameText.setBounds(109, 56, 100, 28);
		frame.getContentPane().add(tutorNameText);
		
		JLabel lblCourse = new JLabel("Course:");
		lblCourse.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblCourse.setBounds(10, 85, 68, 28);
		frame.getContentPane().add(lblCourse);
		
		JLabel courseNameText = new JLabel("");
		courseNameText.setFont(new Font("Microsoft YaHei", Font.BOLD, 15));
		courseNameText.setBounds(74, 85, 100, 28);
		frame.getContentPane().add(courseNameText);
		
		JLabel lblEnterTutorId = new JLabel("Enter Tutor ID");
		lblEnterTutorId.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblEnterTutorId.setBounds(219, 56, 171, 28);
		frame.getContentPane().add(lblEnterTutorId);
		
		searchText = new JTextField();
		searchText.setBounds(219, 85, 105, 21);
		frame.getContentPane().add(searchText);
		searchText.setColumns(10);
		
		
		JLabel lblNewLabel_1 = new JLabel("Modules");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 19));
		lblNewLabel_1.setBounds(0, 120, 423, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 149, 403, 205);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setShowVerticalLines(false);
		table.setEnabled(false);
		scrollPane.setViewportView(table);
		frame.setVisible(true);

		System.out.println(arr);
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// search
				tutorID();
				String text = searchText.getText();
				
				if (text.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					table.setVisible(false);
					return;
				}
				
				try {
					tutorID = Integer.parseInt(text);
					if (!arr.contains(tutorID)) {
						JOptionPane.showMessageDialog(frame,"Not Found.","Alert",JOptionPane.WARNING_MESSAGE);
						table.setVisible(false);
						return;
					}
					tutorInfo();
					table.setVisible(true);
					tutorNameText.setText(tutorName);
					courseNameText.setText(courseName);
				} catch (NumberFormatException nfe) {
					System.out.println(nfe);
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnNewButton.setBounds(327, 85, 80, 21);
		frame.getContentPane().add(btnNewButton);
	}
}
