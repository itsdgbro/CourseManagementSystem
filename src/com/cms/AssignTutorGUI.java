package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AssignTutorGUI {

	private JFrame frame;
	private JTextField moduleID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignTutorGUI window = new AssignTutorGUI();
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
	public AssignTutorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 336, 255);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(frame);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblA = new JLabel("Assign Tutor");
		lblA.setBounds(0, 0, 194, 34);
		lblA.setVerticalAlignment(SwingConstants.TOP);
		lblA.setHorizontalAlignment(SwingConstants.CENTER);
		lblA.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		frame.getContentPane().add(lblA);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 38, 298, 170);
		panel.setBackground(new Color(240, 240, 240));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Module ID");
		lblNewLabel_1_1_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(86, 10, 100, 23);
		panel.add(lblNewLabel_1_1_1);
		
		
	
		Instructor ins = new Instructor();
		ins.getInstructors();
		System.out.println("HI");
		String[] array = Instructor.arr.toArray(new String[Instructor.arr.size()]);
		Instructor.arr.clear();
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		System.out.println(array.length);
		
		 
		JComboBox<String> choseTutor = new JComboBox<String>(array);
		choseTutor.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		choseTutor.setBorder(null);
		choseTutor.setBounds(86, 89, 136, 23);
		panel.add(choseTutor);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Tutor");
		lblNewLabel_1_1_1_1.setFont(new Font("Microsoft YaHei", Font.PLAIN, 20));
		lblNewLabel_1_1_1_1.setBounds(86, 65, 84, 23);
		panel.add(lblNewLabel_1_1_1_1);		
		
		moduleID = new JTextField();
		moduleID.setColumns(10);
		moduleID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(240, 240, 240)));
		moduleID.setBounds(86, 32, 136, 23);
		panel.add(moduleID);
		
		JButton assignBtn = new JButton("Assign");
		assignBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// assign
				String tutor = (String) choseTutor.getSelectedItem();
				String mID = moduleID.getText();
				if (mID.isEmpty() || tutor.equals("Choose Instructor")) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					int id = Integer.parseInt(mID);
					Admin admin = new Admin();
					admin.assignTutor(id,tutor);
					
				} catch (NumberFormatException nfe) {
					System.out.println("ERROR");
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		assignBtn.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		assignBtn.setBounds(106, 143, 85, 21);
		panel.add(assignBtn);

		
		
		
	}
}
