package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(new Color(0, 0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(500, 400));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		JLabel lblNewLabel = new JLabel("Welcome to Login Panel");
		lblNewLabel.setBounds(53, 30, 470, 44);
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 32));

		JTextField userEmail = new JTextField();
		userEmail.setBackground(new Color(240, 240, 240));
		userEmail.setBounds(150, 137, 166, 25);
		userEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		userEmail.setColumns(10);

		JButton btnCreateNewAccount = new JButton("Signup");
		btnCreateNewAccount.setBounds(240, 251, 76, 27);
		btnCreateNewAccount.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(150, 170, 166, 36);
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Consolas", Font.BOLD, 18));

		JPasswordField userPassword = new JPasswordField();
		userPassword.setBackground(new Color(240, 240, 240));
		userPassword.setBounds(150, 201, 166, 25);
		userPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));

		JLabel lblUsername = new JLabel("Email");
		lblUsername.setBounds(150, 107, 167, 36);
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setFont(new Font("Consolas", Font.BOLD, 18));

		btnCreateNewAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Signup();
				frame.setVisible(false);
			}
		});

		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(150, 251, 76, 27);
		btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Login
				// first check user selection
					String username = userEmail.getText();
					String password = new String(userPassword.getPassword());

					try {
						InputFieldIsEmptyException.checkInput(username);
						InputFieldIsEmptyException.checkInput(password);
						VerifyLogin tb = new VerifyLogin(username,password);
						boolean isUser = tb.retriveUser();
						if(!isUser) {
							JOptionPane.showMessageDialog(frame,"Invalid Email or Passowrd.","Alert",JOptionPane.WARNING_MESSAGE);
							return;
						}
						System.out.println("Log In");
						String userLoggedIn = tb.email;
						new Dashboard(userLoggedIn);
						frame.setVisible(false);
					}catch(CustomException ce) {
						System.out.println(ce);
						JOptionPane.showMessageDialog(frame,ce,"Alert",JOptionPane.WARNING_MESSAGE);
					}
					
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(lblUsername);
		frame.getContentPane().add(userEmail);
		frame.getContentPane().add(lblPassword);
		frame.getContentPane().add(userPassword);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(btnCreateNewAccount);

	}
}
