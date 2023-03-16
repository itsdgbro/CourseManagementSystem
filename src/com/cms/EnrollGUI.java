package com.cms;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EnrollGUI {

	private JFrame frame;
	private JTextField studentIDText;
	static String courseName;
	int newStudentID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnrollGUI window = new EnrollGUI();
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
	public EnrollGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 293, 258);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		InsertData id = new InsertData(courseName, newStudentID);
		id.userStudentIntoEnrollTable();
		
		JLabel lblEnrollStudent = new JLabel("Enroll Student");
		lblEnrollStudent.setVerticalAlignment(SwingConstants.TOP);
		lblEnrollStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnrollStudent.setFont(new Font("Microsoft YaHei", Font.BOLD, 30));
		lblEnrollStudent.setBounds(0, 10, 279, 36);
		frame.getContentPane().add(lblEnrollStudent);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(25, 56, 233, 232);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(10, 10, 126, 23);
		panel.add(lblNewLabel_1);
		
		studentIDText = new JTextField();
		studentIDText.setBackground(new Color(240, 240, 240));
		studentIDText.setColumns(10);
		studentIDText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		studentIDText.setBounds(10, 31, 201, 23);
		panel.add(studentIDText);
		
		JLabel lblNewLabel_1_1 = new JLabel("Course");
		lblNewLabel_1_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(10, 64, 201, 23);
		panel.add(lblNewLabel_1_1);
		
		String[] array = id.arr.toArray(new String[id.arr.size()]);
		JComboBox<Object> courseChose = new JComboBox<Object>(array);
		courseChose.setBorder(null);
		courseChose.setBounds(10, 86, 201, 23);
		courseChose.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		panel.add(courseChose);
		
		Student std  = new Student();
		std.enrollStudent();
		String studentID = Integer.toString(Student.enrollStudentID);
		studentIDText.setEnabled(false);
		studentIDText.setText(studentID);
		
		JButton enrollBtn = new JButton("Enroll");
		enrollBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// enroll
				courseName = (String) courseChose.getSelectedItem();
				if (courseName.equals("Choose course")) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				try{
					Student std  = new Student(courseName);
					std.enrollStudent();
					id.userStudentIntoEnrollTable();
					
				} catch (NumberFormatException nfe) {
					System.out.println("ERROR");
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		enrollBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		enrollBtn.setBounds(62, 119, 85, 21);
		panel.add(enrollBtn);
		
		
		
		
	}
}
