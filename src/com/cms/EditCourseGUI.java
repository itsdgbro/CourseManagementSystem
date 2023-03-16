package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EditCourseGUI {

	private JFrame frame;
	private JTextField oldCourseName;
	private JTextField updatedCourseName;
	private JTextField courseSeat;
	private JTextField courseBatch;
	String newCourseName;  
	int newSeats;  
	int newBatch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditCourseGUI window = new EditCourseGUI();
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
	public EditCourseGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 317, 411);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Edit Course Details");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 318, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(81, Short.MAX_VALUE))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Course name");
		lblNewLabel_1.setBounds(33, 10, 126, 23);
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		
		oldCourseName = new JTextField();
		oldCourseName.setBackground(new Color(240, 240, 240));
		oldCourseName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		oldCourseName.setBounds(33, 29, 188, 31);
		oldCourseName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("New course name");
		lblNewLabel_1_1.setBounds(33, 70, 201, 23);
		lblNewLabel_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		
		updatedCourseName = new JTextField();
		updatedCourseName.setBackground(new Color(240, 240, 240));
		updatedCourseName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		updatedCourseName.setBounds(33, 91, 188, 31);
		updatedCourseName.setColumns(10);
		panel.setLayout(null);
		panel.add(lblNewLabel_1_1);
		panel.add(updatedCourseName);
		panel.add(oldCourseName);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Total seats");
		lblNewLabel_1_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		lblNewLabel_1_2.setBounds(33, 132, 126, 23);
		panel.add(lblNewLabel_1_2);
		
		courseSeat = new JTextField();
		courseSeat.setBackground(new Color(240, 240, 240));
		courseSeat.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		courseSeat.setColumns(10);
		courseSeat.setBounds(33, 152, 188, 31);
		panel.add(courseSeat);
		
		JLabel lblNewLabel_1_3 = new JLabel("Batch");
		lblNewLabel_1_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 17));
		lblNewLabel_1_3.setBounds(33, 193, 126, 23);
		panel.add(lblNewLabel_1_3);
		
		courseBatch = new JTextField();
		courseBatch.setBackground(new Color(240, 240, 240));
		courseBatch.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		courseBatch.setColumns(10);
		courseBatch.setBounds(33, 213, 188, 31);
		panel.add(courseBatch);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Edit/Update the course
				newCourseName = updatedCourseName.getText();
				String inputSeat = courseSeat.getText();
				String inputBatch = courseBatch.getText();
				String inputCourseName = oldCourseName.getText();
				
				if (newCourseName.isEmpty() || inputSeat.isEmpty() || inputBatch.isEmpty() || inputCourseName.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					newSeats = Integer.parseInt(inputSeat);
					newBatch = Integer.parseInt(inputBatch);
					System.out.println(newSeats+" "+newBatch+" "+inputCourseName);
					Admin admin = new Admin();
					admin.editCourse(newSeats, newBatch, newCourseName, inputCourseName);
					frame.setVisible(false);
				}catch(NumberFormatException en) {
					System.out.println("Invalid input");
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		updateBtn.setBounds(75, 254, 91, 23);
		panel.add(updateBtn);
		
		
		frame.getContentPane().setLayout(groupLayout);
		frame.setVisible(true);
		
	}
}
