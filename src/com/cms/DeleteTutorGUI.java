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

public class DeleteTutorGUI {

	private JFrame frame;
	private JTextField tutorID;
	String tutorName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteTutorGUI window = new DeleteTutorGUI();
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
	public DeleteTutorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 267, 214);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDeleteTutor = new JLabel("Delete Tutor");
		lblDeleteTutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteTutor.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 30));
		lblDeleteTutor.setBounds(0, 0, 256, 46);
		frame.getContentPane().add(lblDeleteTutor);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(36, 56, 186, 170);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Tutor ID");
		lblNewLabel_1.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 0, 159, 29);
		panel.add(lblNewLabel_1);
		
		tutorID = new JTextField();
		tutorID.setBackground(new Color(240, 240, 240));
		tutorID.setColumns(10);
		tutorID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		tutorID.setBounds(10, 21, 159, 27);
		panel.add(tutorID);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Delete 
				String id = tutorID.getText();
				try {
					int newTutorID = Integer.parseInt(id);
					Admin admin = new Admin();
					admin.deleteTutor(newTutorID);
				} catch (NumberFormatException ne) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					System.out.print(ne);
				}
			}
		});
		deleteBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		deleteBtn.setBounds(45, 61, 85, 21);
		panel.add(deleteBtn);
		
		frame.setVisible(true);
		
	}

}
