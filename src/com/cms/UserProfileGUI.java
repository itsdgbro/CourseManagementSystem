package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserProfileGUI {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField oldPassword;
	private JPasswordField newPassword;
	private JTextField txtContact;
	static String userEmail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserProfileGUI window = new UserProfileGUI();
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
	public UserProfileGUI() {
		initialize();
	}
	
	public UserProfileGUI(String userEmail) {
		UserProfileGUI.userEmail = userEmail;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 527, 213);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserProfile = new JLabel("User Profile");
		lblUserProfile.setBounds(0, 0, 513, 34);
		lblUserProfile.setVerticalAlignment(SwingConstants.TOP);
		lblUserProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserProfile.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		frame.getContentPane().add(lblUserProfile);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(22, 63, 78, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email:");
		lblNewLabel_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(22, 96, 78, 23);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel txtContactField = new JLabel("Concact:");
		txtContactField.setVerticalAlignment(SwingConstants.BOTTOM);
		txtContactField.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		txtContactField.setBounds(22, 127, 78, 23);
		frame.getContentPane().add(txtContactField);
		
		JLabel lblNewLabel_1_2 = new JLabel("General");
		lblNewLabel_1_2.setForeground(new Color(128, 128, 128));
		lblNewLabel_1_2.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
		lblNewLabel_1_2.setBounds(22, 39, 126, 23);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Security");
		lblNewLabel_1_2_1.setForeground(new Color(128, 128, 128));
		lblNewLabel_1_2_1.setFont(new Font("Microsoft YaHei", Font.BOLD, 17));
		lblNewLabel_1_2_1.setBounds(254, 39, 126, 23);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_3 = new JLabel("Old Password:");
		lblNewLabel_1_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(254, 66, 126, 23);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("New Password:");
		lblNewLabel_1_3_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		lblNewLabel_1_3_1.setBounds(254, 96, 126, 23);
		frame.getContentPane().add(lblNewLabel_1_3_1);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBackground(new Color(240, 240, 240));
		txtName.setFont(new Font("Microsoft YaHei", Font.BOLD, 16));
		txtName.setColumns(10);
		txtName.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtName.setBounds(93, 66, 133, 23);
		frame.getContentPane().add(txtName);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setBackground(new Color(240, 240, 240));
		txtEmail.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		txtEmail.setColumns(10);
		txtEmail.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtEmail.setBounds(93, 96, 133, 23);
		frame.getContentPane().add(txtEmail);
		
		oldPassword = new JPasswordField();
		oldPassword.setBackground(new Color(240, 240, 240));
		oldPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		oldPassword.setBounds(380, 67, 116, 19);
		frame.getContentPane().add(oldPassword);
		
		newPassword = new JPasswordField();
		newPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		newPassword.setBackground(new Color(240, 240, 240));
		newPassword.setBounds(380, 100, 116, 19);
		frame.getContentPane().add(newPassword);
		
		txtContact = new JTextField();
		txtContact.setEditable(false);
		txtContact.setBackground(new Color(240, 240, 240));
		txtContact.setFont(new Font("Microsoft YaHei", Font.PLAIN, 16));
		txtContact.setColumns(10);
		txtContact.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		txtContact.setBounds(93, 129, 133, 23);
		frame.getContentPane().add(txtContact);
		frame.setVisible(true);
		
		Users user = new Users();
		user.userProfile(userEmail);
		String userName = Users.userName;
		String userContact = Users.userContact;
		System.out.println(userName);
		txtName.setText(userName);
		txtEmail.setText(userEmail);
		txtContact.setText(userContact);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update
				String oldPass = new String(oldPassword.getPassword());
				String newPass = new String(newPassword.getPassword());
				String userPassword = Users.userPassword;

				if (oldPass.isEmpty() || newPass.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (!oldPass.equals(userPassword)) {
					JOptionPane.showMessageDialog(frame,"Invalid Password.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				user.updateUserInfo(newPass, userEmail);
			
			}
		});
		btnNewButton.setBounds(380, 132, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
	}
}
