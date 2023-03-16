package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class Signup {

	private JFrame frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Signup window = new Signup();
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
	public Signup() {
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setBounds(100, 100, 566, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		
		JLabel lblNewLabel = new JLabel("Welcome to Sign Up Panel");
		lblNewLabel.setBounds(64, 22, 470, 44);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 32));
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(91, 126, 143, 21);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 18));
		
		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(287, 126, 208, 21);
		lblNewLabel_2.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Consolas", Font.BOLD, 18));
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(287, 189, 165, 21);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Consolas", Font.BOLD, 18));
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setBounds(91, 256, 165, 21);
		lblNewLabel_4.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setFont(new Font("Consolas", Font.BOLD, 18));
		
		JLabel lblNewLabel_5 = new JLabel("Confirm Password");
		lblNewLabel_5.setBounds(287, 256, 208, 21);
		lblNewLabel_5.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_5.setForeground(new Color(0, 0, 0));
		lblNewLabel_5.setFont(new Font("Consolas", Font.BOLD, 18));
		
		JTextField jFirstName = new JTextField();
		jFirstName.setBackground(new Color(240, 240, 240));
		jFirstName.setBounds(91, 145, 165, 20);
		jFirstName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		jFirstName.setColumns(10);
		
		JTextField jLastName = new JTextField();
		jLastName.setBackground(new Color(240, 240, 240));
		jLastName.setBounds(287, 145, 165, 20);
		jLastName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		jLastName.setColumns(10);
		
		JTextField txtPhoneNumber = new JTextField();
		txtPhoneNumber.setBackground(new Color(240, 240, 240));
		txtPhoneNumber.setBounds(91, 210, 165, 20);
		txtPhoneNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		txtPhoneNumber.setColumns(10);

		JTextField txtEmail = new JTextField();
		txtEmail.setBackground(new Color(240, 240, 240));
		txtEmail.setBounds(287, 210, 165, 20);
		txtEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		txtEmail.setColumns(10);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setBackground(new Color(240, 240, 240));
		passwordField.setBounds(91, 277, 165, 20);
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		
		JPasswordField passwordField_1 = new JPasswordField();
		passwordField_1.setBackground(new Color(240, 240, 240));
		passwordField_1.setBounds(287, 277, 165, 20);
		passwordField_1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		frame.setVisible(true);
		
		Choice userMode = new Choice();
		userMode.setBounds(287, 84, 165, 20);
		userMode.setFont(new Font("Consolas", Font.PLAIN, 12));
		userMode.add("Select user mode");
		userMode.add("Student");
		userMode.add("Instructor");
		userMode.add("Admin");
		
		// SIGNUP BUTTON
		JButton btnSignup = new JButton("Signup");
		btnSignup.setBounds(91, 317, 165, 25);
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// EMAIL REGEX VERIFY
				String regexEmail = "[A-z a-z][a-z]+[0-9]*@[a-z]+\\.[a-z]{3}";
				String firstName = jFirstName.getText();
				String lastName = jLastName.getText();
				String phoneNumber = txtPhoneNumber.getText();
				String email = txtEmail.getText();
				String password = String.valueOf(passwordField.getPassword());
				String confirmPassword = String.valueOf(passwordField_1.getPassword());
				String role = userMode.getSelectedItem();
				
				System.out.println(role);
				Users user = new Users();
				user.checkDuplicateEmail();
				if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || email.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (phoneNumber.length() != 10) {
					JOptionPane.showMessageDialog(frame,"Enter 10 digit number.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!Pattern.matches(regexEmail, email)) {
					JOptionPane.showMessageDialog(frame,"Invalid Email.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!password.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(frame,"Password not matched.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(user.checkDuplicate.contains(email)) {
					JOptionPane.showMessageDialog(frame,"Email already exist.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (role == "Select user mode") {
					JOptionPane.showMessageDialog(frame,"Select User.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					InsertData id = new InsertData();
					id.insertUserInfo(firstName, lastName, email, phoneNumber, password, role);
					frame.setVisible(true);
				}
			}
		});
		btnSignup.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(287, 317, 165, 25);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login();
				frame.setVisible(false);
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		
		JLabel lblNewLabel_3_1 = new JLabel("Phone Number");
		lblNewLabel_3_1.setBounds(91, 189, 165, 21);
		lblNewLabel_3_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3_1.setForeground(Color.BLACK);
		lblNewLabel_3_1.setFont(new Font("Consolas", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_1 = new JLabel("Sign up");
		lblNewLabel_1_1.setBounds(91, 78, 176, 32);
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Dubai", Font.PLAIN, 18));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(lblNewLabel_1_1);
		frame.getContentPane().add(userMode);
		frame.getContentPane().add(lblNewLabel_1);
		frame.getContentPane().add(jFirstName);
		frame.getContentPane().add(lblNewLabel_2);
		frame.getContentPane().add(jLastName);
		frame.getContentPane().add(lblNewLabel_3_1);
		frame.getContentPane().add(lblNewLabel_3);
		frame.getContentPane().add(txtPhoneNumber);
		frame.getContentPane().add(txtEmail);
		frame.getContentPane().add(lblNewLabel_4);
		frame.getContentPane().add(lblNewLabel_5);
		frame.getContentPane().add(passwordField);
		frame.getContentPane().add(passwordField_1);
		frame.getContentPane().add(btnSignup);
		frame.getContentPane().add(btnLogin);
		
		
	}
}