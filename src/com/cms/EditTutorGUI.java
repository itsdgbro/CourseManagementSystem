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

public class EditTutorGUI {

	private JFrame frame;
	private JTextField tutorID;
	private JTextField tutorEmail;
	private JTextField tutorPhoneNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditTutorGUI window = new EditTutorGUI();
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
	public EditTutorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 296, 345);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblEditTutorDetails = new JLabel("Edit Tutor Details");
		lblEditTutorDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditTutorDetails.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		lblEditTutorDetails.setBounds(0, 10, 282, 36);
		frame.getContentPane().add(lblEditTutorDetails);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(29, 56, 233, 284);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tutor ID");
		lblNewLabel_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(34, 21, 126, 23);
		panel.add(lblNewLabel_1);
		
		tutorID = new JTextField();
		tutorID.setBackground(new Color(240, 240, 240));
		tutorID.setColumns(10);
		tutorID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		tutorID.setBounds(34, 41, 166, 31);
		panel.add(tutorID);
		
		JLabel lblNewLabel_1_2 = new JLabel("Email");
		lblNewLabel_1_2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(34, 82, 126, 23);
		panel.add(lblNewLabel_1_2);
		
		tutorEmail = new JTextField();
		tutorEmail.setBackground(new Color(240, 240, 240));
		tutorEmail.setColumns(10);
		tutorEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		tutorEmail.setBounds(34, 103, 166, 31);
		panel.add(tutorEmail);
		
		JLabel lblNewLabel_1_3 = new JLabel("Phone number");
		lblNewLabel_1_3.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel_1_3.setBounds(34, 144, 184, 23);
		panel.add(lblNewLabel_1_3);
		
		tutorPhoneNumber = new JTextField();
		tutorPhoneNumber.setBackground(new Color(240, 240, 240));
		tutorPhoneNumber.setColumns(10);
		tutorPhoneNumber.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		tutorPhoneNumber.setBounds(34, 165, 166, 31);
		panel.add(tutorPhoneNumber);
		
		JButton tutorUpdate = new JButton("Update");
		tutorUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// edit 
				String regexEmail = "[A-z a-z][a-z]+[0-9]*@[a-z]+\\.[a-z]{3}";
				String id = tutorID.getText();
				String email = tutorEmail.getText();
				String phoneNumber = tutorPhoneNumber.getText();
				
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
					JOptionPane.showMessageDialog(frame,"Enter 10 digit numbert.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if(!Pattern.matches(regexEmail, email)) {
					JOptionPane.showMessageDialog(frame,"Invalid Email.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					int newID = Integer.parseInt(id);
					Admin admin = new Admin();
					admin.editTutor(newID, email, phoneNumber);
					frame.setVisible(false);
					
				}catch(NumberFormatException en) {
					System.out.println(en);
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		tutorUpdate.setBounds(72, 208, 85, 23);
		panel.add(tutorUpdate);
		
		
		frame.setVisible(true);
		
		
	}
}
