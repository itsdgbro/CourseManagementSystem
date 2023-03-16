package com.cms;

import java.awt.EventQueue;

import javax.swing.JComboBox;
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

public class AddModuleGUI {

	private JFrame frame;
	private JTextField moduleName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddModuleGUI window = new AddModuleGUI();
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
	public AddModuleGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 307, 330);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		JLabel lblAddModule = new JLabel("Add Module");
		lblAddModule.setBounds(0, 10, 283, 34);
		lblAddModule.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddModule.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		frame.getContentPane().add(lblAddModule);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(34, 44, 232, 236);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Course");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(31, 22, 126, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Module name");
		lblNewLabel_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel_1_1.setBounds(31, 78, 143, 23);
		panel.add(lblNewLabel_1_1);
		
		moduleName = new JTextField();
		moduleName.setColumns(10);
		moduleName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(240, 240, 240)));
		moduleName.setBounds(31, 102, 166, 23);
		panel.add(moduleName);
		frame.setVisible(true);
		
		InsertData id = new InsertData();
		id.userStudentIntoEnrollTable();
		String[] array = id.arr.toArray(new String[id.arr.size()]);
		JComboBox<String> courseChose = new JComboBox<String>(array);
		courseChose.setBorder(null);
		courseChose.setBounds(31, 45, 166, 23);
		courseChose.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		panel.add(courseChose);
		
		JComboBox<Object> moduleLevel = new JComboBox<Object>(new Object[]{"Select","4","5","6"});
		moduleLevel.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
		moduleLevel.setBorder(null);
		moduleLevel.setBounds(125, 142, 72, 23);
		panel.add(moduleLevel);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// add
				
				String modulename = moduleName.getText();
				String course = (String) courseChose.getSelectedItem();
				String level = (String) moduleLevel.getSelectedItem();
				
				if (course.equals("Choose course")) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (modulename.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (level.equals("Select")) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				Admin admin = new Admin();
				admin.addNewModule(modulename,course,level);
			}
		});
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 15));
		btnNewButton.setBounds(72, 192, 85, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Level");
		lblNewLabel_1_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel_1_1_1.setBounds(31, 142, 73, 23);
		panel.add(lblNewLabel_1_1_1);

		
		
	}
}
