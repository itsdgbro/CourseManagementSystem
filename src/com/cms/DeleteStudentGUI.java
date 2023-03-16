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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteStudentGUI {

	private JFrame frame;
	private JTextField studentID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteStudentGUI window = new DeleteStudentGUI();
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
	public DeleteStudentGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 262, 215);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDeleteStudent = new JLabel("Delete Student");
		lblDeleteStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteStudent.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 25));
		lblDeleteStudent.setBounds(0, 0, 248, 46);
		frame.getContentPane().add(lblDeleteStudent);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(23, 45, 206, 158);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(25, 10, 159, 29);
		panel.add(lblNewLabel_1);
		
		studentID = new JTextField();
		studentID.setBackground(new Color(240, 240, 240));
		studentID.setColumns(10);
		studentID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		studentID.setBounds(25, 28, 159, 27);
		panel.add(studentID);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// delete
				String id = studentID.getText();
				try {
					int stdID = Integer.parseInt(id);
					Admin admin = new Admin();
					admin.deleteStudent(stdID);
				} catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					System.out.print(ne);
				}
			}
		});
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteBtn.setBounds(57, 81, 85, 21);
		panel.add(deleteBtn);
		
		
		frame.setVisible(true);
		
		
	}

}
