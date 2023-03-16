package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EditStudentGUI {

	private JFrame frame;
	private JTextField studentID;
	private JTextField stdEmail;
	private JTextField stdPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditStudentGUI window = new EditStudentGUI();
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
	public EditStudentGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 258, 296);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblEditStudentDetails = new JLabel("Edit Student Details");
		lblEditStudentDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditStudentDetails.setFont(new Font("Microsoft YaHei", Font.BOLD, 20));
		lblEditStudentDetails.setBounds(0, 10, 244, 36);
		frame.getContentPane().add(lblEditStudentDetails);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(22, 35, 230, 290);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(29, 21, 126, 23);
		panel.add(lblNewLabel_1);
		
		studentID = new JTextField();
		studentID.setBackground(new Color(240, 240, 240));
		studentID.setColumns(10);
		studentID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		studentID.setBounds(29, 41, 137, 23);
		panel.add(studentID);
		
		JLabel lblNewLabel_1_2 = new JLabel("New email");
		lblNewLabel_1_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(29, 74, 126, 23);
		panel.add(lblNewLabel_1_2);
		
		stdEmail = new JTextField();
		stdEmail.setBackground(new Color(240, 240, 240));
		stdEmail.setColumns(10);
		stdEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		stdEmail.setBounds(29, 91, 137, 23);
		panel.add(stdEmail);
		
		JLabel lblNewLabel_1_3 = new JLabel("Phone number");
		lblNewLabel_1_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(29, 124, 184, 23);
		panel.add(lblNewLabel_1_3);
		
		stdPhoneNumber = new JTextField();
		stdPhoneNumber.setBackground(new Color(240, 240, 240));
		stdPhoneNumber.setColumns(10);
		stdPhoneNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		stdPhoneNumber.setBounds(29, 142, 137, 23);
		panel.add(stdPhoneNumber);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update
				String regexEmail = "[A-z a-z][a-z]+[0-9]*@[a-z]+\\.[a-z]{3}";
				String id = studentID.getText();
				String email = stdEmail.getText();
				String phoneNumber = stdPhoneNumber.getText();
				Users user = new Users();
				user.checkDuplicateEmail();
				if (id.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(user.checkDuplicate.contains(email)) {
					JOptionPane.showMessageDialog(frame,"Email already exist.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (phoneNumber.length() != 10) {
					JOptionPane.showMessageDialog(frame,"Enter 10 digit number.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!Pattern.matches(regexEmail, email)) {
					JOptionPane.showMessageDialog(frame,"Invalid email.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					int stdId = Integer.parseInt(id);
					Admin admin = new Admin();
					admin.editStudent(stdId, email, phoneNumber);
					frame.setVisible(false);
					
				}catch(NumberFormatException en) {
					System.out.println("Invalid input");
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		updateBtn.setBounds(57, 182, 85, 21);
		panel.add(updateBtn);
		
		frame.setVisible(true);
		
	}

}
