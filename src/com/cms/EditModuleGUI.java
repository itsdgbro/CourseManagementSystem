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

public class EditModuleGUI {

	private JFrame frame;
	private JTextField moduleID;
	private JTextField moduleName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditModuleGUI window = new EditModuleGUI();
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
	public EditModuleGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 251, 271);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblEditModule = new JLabel("Edit Module");
		lblEditModule.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditModule.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		lblEditModule.setBounds(0, 10, 213, 36);
		frame.getContentPane().add(lblEditModule);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(15, 46, 207, 172);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		moduleID = new JTextField();
		moduleID.setBackground(new Color(240, 240, 240));
		moduleID.setColumns(10);
		moduleID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		moduleID.setBounds(20, 30, 166, 31);
		panel.add(moduleID);
		
		JLabel lblNewLabel_1 = new JLabel("Module ID");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(20, 10, 126, 23);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Module Name");
		lblNewLabel_1_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel_1_2.setBounds(20, 67, 126, 23);
		panel.add(lblNewLabel_1_2);
		
		moduleName = new JTextField();
		moduleName.setBackground(new Color(240, 240, 240));
		moduleName.setColumns(10);
		moduleName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		moduleName.setBounds(20, 88, 166, 31);
		panel.add(moduleName);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mID = moduleID.getText();
				String mn = moduleName.getText();
				
				if (mID.isEmpty() || mn.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					int id = Integer.parseInt(mID);
					Admin admin = new Admin();
					admin.editModule(id, mn);
				} catch (NumberFormatException ie) {
					System.out.println(ie);
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(57, 129, 85, 21);
		panel.add(btnNewButton);
	}
}
