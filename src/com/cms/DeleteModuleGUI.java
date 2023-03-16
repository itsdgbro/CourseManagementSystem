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
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;

public class DeleteModuleGUI {

	private JFrame frame;
	private JTextField moduleID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteModuleGUI window = new DeleteModuleGUI();
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
	public DeleteModuleGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 249, 245);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblEditModule = new JLabel("Delete Module");
		lblEditModule.setBounds(0, 0, 235, 34);
		lblEditModule.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditModule.setFont(new Font("Microsoft YaHei", Font.BOLD, 25));
		frame.getContentPane().add(lblEditModule);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 240, 240));
		panel.setBounds(22, 44, 190, 150);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Module ID");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(14, 10, 126, 23);
		panel.add(lblNewLabel_1);
		
		moduleID = new JTextField();
		moduleID.setBackground(new Color(240, 240, 240));
		moduleID.setColumns(10);
		moduleID.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		moduleID.setBounds(14, 30, 141, 31);
		panel.add(moduleID);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mID = moduleID.getText();
				if (mID.isEmpty()) {
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				try {
					int id = Integer.parseInt(mID);
					Admin admin = new Admin();
					admin.deleteModule(id);
				}catch(InputMismatchException ime) {
					System.out.println(ime);
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}catch(NumberFormatException nfe) {
					System.out.println(nfe);
					JOptionPane.showMessageDialog(frame,"Invalid Input.","Alert",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 13));
		btnNewButton.setBounds(55, 96, 85, 21);
		panel.add(btnNewButton);
		
	}

}
