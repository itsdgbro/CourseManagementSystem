package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class DeleteCourseGUI {

	private JFrame frame;
	private JTextField textField;
	static int courseID;
	static String courseName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteCourseGUI window = new DeleteCourseGUI();
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
	public DeleteCourseGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 230, 241);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("Delete Course");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 214, 46);
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(23, 42, 191, 168);
		panel.setBackground(new Color(240, 240, 240));
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(new Color(240, 240, 240));
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		textField.setColumns(10);
		textField.setBounds(10, 44, 141, 27);
		panel.add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Course ID");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 22, 184, 29);
		panel.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// action delete
				String stringID = textField.getText();
				try {
					courseID = Integer.parseInt(stringID);
					Admin admin = new Admin();
					admin.deleteCourse(courseID);
				}catch(NumberFormatException en) {
					System.out.println("Enter number");
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBounds(23, 93, 110, 27);
		panel.add(btnNewButton);
		frame.getContentPane().add(panel);
		
		
		frame.getContentPane().add(lblNewLabel);
	}
}
