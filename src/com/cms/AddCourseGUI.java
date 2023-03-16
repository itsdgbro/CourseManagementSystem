package com.cms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.util.ArrayList;
import java.util.List;

public class AddCourseGUI {

	private JFrame frame;
	private JTextField jCourseName;
	private JTextField jSeats;
	private JTextField jBatch;
	private JTextField year1Module1;
	private JTextField year1Module2;
	private JTextField year1Module3;
	private JTextField year2Module1;
	private JTextField year2Module2;
	private JTextField year2Module3;
	private JTextField year3Module1;
	private JTextField year3Module2;
	private JTextField year3Module3;
	
 	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourseGUI window = new AddCourseGUI();
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
	public AddCourseGUI() {
		initialize();
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 718, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		Instructor ins = new Instructor();
		ins.getInstructors();
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 48, 692, 504);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(240, 240, 240));
		
		JLabel lblNewLabel = new JLabel("Course Details");
		lblNewLabel.setBounds(12, 17, 260, 36);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Course name");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(23, 13, 142, 22);
		panel.add(lblNewLabel_1);
		
		jCourseName = new JTextField();
		jCourseName.setBackground(new Color(240, 240, 240));
		jCourseName.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		jCourseName.setBounds(23, 34, 189, 22);
		panel.add(jCourseName);
		jCourseName.setColumns(10);
		
		jSeats = new JTextField();
		jSeats.setBackground(new Color(240, 240, 240));
		jSeats.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		jSeats.setColumns(10);
		jSeats.setBounds(245, 34, 189, 22);
		panel.add(jSeats);
		
		JLabel lblNewLabel_1_1 = new JLabel("No. of seats");
		lblNewLabel_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(245, 13, 142, 22);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Batch");
		lblNewLabel_1_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(475, 13, 142, 22);
		panel.add(lblNewLabel_1_1_1);
		
		jBatch = new JTextField();
		jBatch.setBackground(new Color(240, 240, 240));
		jBatch.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		jBatch.setColumns(10);
		jBatch.setBounds(475, 34, 189, 22);
		panel.add(jBatch);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 63, 692, 448);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("1st Year");
		lblNewLabel_1_2.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(22, 9, 142, 22);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Module 1");
		lblNewLabel_1_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(22, 33, 142, 22);
		panel_1.add(lblNewLabel_1_3);
		
		year1Module1 = new JTextField();
		year1Module1.setBackground(new Color(240, 240, 240));
		year1Module1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year1Module1.setColumns(10);
		year1Module1.setBounds(22, 54, 189, 22);
		panel_1.add(year1Module1);
		
		year1Module2 = new JTextField();
		year1Module2.setBackground(new Color(240, 240, 240));
		year1Module2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year1Module2.setColumns(10);
		year1Module2.setBounds(248, 54, 189, 22);
		panel_1.add(year1Module2);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Module 2");
		lblNewLabel_1_1_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(248, 33, 142, 22);
		panel_1.add(lblNewLabel_1_1_2);
		
		year1Module3 = new JTextField();
		year1Module3.setBackground(new Color(240, 240, 240));
		year1Module3.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year1Module3.setColumns(10);
		year1Module3.setBounds(475, 54, 189, 22);
		panel_1.add(year1Module3);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Module 3");
		lblNewLabel_1_1_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1.setBounds(475, 33, 142, 22);
		panel_1.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_4 = new JLabel("Module 1 Tutor");
		lblNewLabel_1_4.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_4.setBounds(22, 89, 142, 22);
		panel_1.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Module 2 Tutor");
		lblNewLabel_1_1_3.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_3.setBounds(248, 89, 142, 22);
		panel_1.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Module 3 Tutor");
		lblNewLabel_1_1_1_2.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_2.setBounds(475, 89, 142, 22);
		panel_1.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("2nd Year");
		lblNewLabel_1_2_1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblNewLabel_1_2_1.setBounds(22, 148, 142, 22);
		panel_1.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Module 1");
		lblNewLabel_1_3_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(22, 172, 142, 22);
		panel_1.add(lblNewLabel_1_3_1);
		
		year2Module1 = new JTextField();
		year2Module1.setBackground(new Color(240, 240, 240));
		year2Module1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year2Module1.setColumns(10);
		year2Module1.setBounds(22, 193, 189, 22);
		panel_1.add(year2Module1);
		
		year2Module2 = new JTextField();
		year2Module2.setBackground(new Color(240, 240, 240));
		year2Module2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year2Module2.setColumns(10);
		year2Module2.setBounds(248, 193, 189, 22);
		panel_1.add(year2Module2);
		
		JLabel lblNewLabel_1_1_2_1 = new JLabel("Module 2");
		lblNewLabel_1_1_2_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(248, 172, 142, 22);
		panel_1.add(lblNewLabel_1_1_2_1);
		
		year2Module3 = new JTextField();
		year2Module3.setBackground(new Color(240, 240, 240));
		year2Module3.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year2Module3.setColumns(10);
		year2Module3.setBounds(475, 193, 189, 22);
		panel_1.add(year2Module3);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Module 3");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1.setBounds(475, 172, 142, 22);
		panel_1.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Module 1 Tutor");
		lblNewLabel_1_4_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_4_1.setBounds(22, 228, 142, 22);
		panel_1.add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_1_1_3_1 = new JLabel("Module 2 Tutor");
		lblNewLabel_1_1_3_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_3_1.setBounds(248, 228, 142, 22);
		panel_1.add(lblNewLabel_1_1_3_1);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("Module 3 Tutor");
		lblNewLabel_1_1_1_2_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_2_1.setBounds(475, 228, 142, 22);
		panel_1.add(lblNewLabel_1_1_1_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("3rd Year");
		lblNewLabel_1_2_1_1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 18));
		lblNewLabel_1_2_1_1.setBounds(22, 287, 142, 22);
		panel_1.add(lblNewLabel_1_2_1_1);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Module 1");
		lblNewLabel_1_3_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_3_1_1.setBounds(22, 311, 142, 22);
		panel_1.add(lblNewLabel_1_3_1_1);
		
		year3Module1 = new JTextField();
		year3Module1.setBackground(new Color(240, 240, 240));
		year3Module1.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year3Module1.setColumns(10);
		year3Module1.setBounds(22, 332, 189, 22);
		panel_1.add(year3Module1);
		
		year3Module2 = new JTextField();
		year3Module2.setBackground(new Color(240, 240, 240));
		year3Module2.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year3Module2.setColumns(10);
		year3Module2.setBounds(248, 332, 189, 22);
		panel_1.add(year3Module2);
		
		JLabel lblNewLabel_1_1_2_1_1 = new JLabel("Module 2");
		lblNewLabel_1_1_2_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1_1.setBounds(248, 311, 142, 22);
		panel_1.add(lblNewLabel_1_1_2_1_1);
		
		year3Module3 = new JTextField();
		year3Module3.setBackground(new Color(240, 240, 240));
		year3Module3.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(192, 192, 192)));
		year3Module3.setColumns(10);
		year3Module3.setBounds(475, 332, 189, 22);
		panel_1.add(year3Module3);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Module 3");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_1_1_1.setBounds(475, 311, 142, 22);
		panel_1.add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Module 1 Tutor");
		lblNewLabel_1_4_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_4_1_1.setBounds(22, 367, 142, 22);
		panel_1.add(lblNewLabel_1_4_1_1);
		
		JLabel lblNewLabel_1_1_3_1_1 = new JLabel("Module 2 Tutor");
		lblNewLabel_1_1_3_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_3_1_1.setBounds(248, 367, 142, 22);
		panel_1.add(lblNewLabel_1_1_3_1_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("Module 3 Tutor");
		lblNewLabel_1_1_1_2_1_1.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 16));
		lblNewLabel_1_1_1_2_1_1.setBounds(475, 367, 142, 22);
		panel_1.add(lblNewLabel_1_1_1_2_1_1);
		
		String[] array = Instructor.arr.toArray(new String[Instructor.arr.size()]);
		Instructor.arr.clear();
		JComboBox<String> comboBox1 = new JComboBox<String>(array);
		comboBox1.setBounds(22, 112, 189, 20);
		panel_1.add(comboBox1);
		
		JComboBox<String> comboBox2 = new JComboBox<String>(array);
		comboBox2.setBounds(248, 112, 189, 20);
		panel_1.add(comboBox2);
		
		JComboBox<String> comboBox3 = new JComboBox<String>(array);
		comboBox3.setBounds(475, 112, 189, 20);
		panel_1.add(comboBox3);
		
		JComboBox<String> comboBox4 = new JComboBox<String>(array);
		comboBox4.setBounds(22, 252, 189, 20);
		panel_1.add(comboBox4);
		
		JComboBox<String> comboBox5 = new JComboBox<String>(array);
		comboBox5.setBounds(248, 252, 189, 20);
		panel_1.add(comboBox5);
		
		JComboBox<String> comboBox6 = new JComboBox<String>(array);
		comboBox6.setBounds(475, 252, 189, 20);
		panel_1.add(comboBox6);
		
		JComboBox<String> comboBox7 = new JComboBox<String>(array);
		comboBox7.setBounds(22, 389, 189, 20);
		panel_1.add(comboBox7);
		
		JComboBox<String> comboBox8 = new JComboBox<String>(array);
		comboBox8.setBounds(248, 389, 189, 20);
		panel_1.add(comboBox8);
		
		JComboBox<String> comboBox9 = new JComboBox<String>(array);
		comboBox9.setBounds(475, 389, 189, 20);
		panel_1.add(comboBox9);
		
		JButton btnNewButton = new JButton("Add Course");
		btnNewButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnNewButton.setBounds(477, 10, 121, 31);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ADD Course
				String courseName = jCourseName.getText();
				String courseSeat = jSeats.getText();
				String courseBatch = jBatch.getText();
				
				// ADD Modules
//				String[] moduleNames = {year1Module1.getText(), year1Module2.getText(), year1Module3.getText(), year2Module1.getText(), year2Module2.getText(), year2Module3.getText(), year3Module1.getText(), year3Module2.getText(), year3Module3.getText()};
				List<Module> modules = new ArrayList<Module>();
				modules.add(new Module(year1Module1.getText(), courseName, "4"));
				modules.add(new Module(year1Module2.getText(), courseName, "4"));
				modules.add(new Module(year1Module3.getText(), courseName, "4"));
				modules.add(new Module(year2Module1.getText(), courseName, "5"));
				modules.add(new Module(year2Module2.getText(), courseName, "5"));
				modules.add(new Module(year2Module3.getText(), courseName, "5"));
				modules.add(new Module(year3Module1.getText(), courseName, "6"));
				modules.add(new Module(year3Module2.getText(), courseName, "6"));
				modules.add(new Module(year3Module3.getText(), courseName, "6"));
				
				if (courseName.isEmpty()) {
				    // empty courseName
					System.out.println("Course Name Empty");
					JOptionPane.showMessageDialog(frame,"Course name missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (courseSeat.isEmpty()) {
				    // empty courseSeat
					System.out.println("Course seat Empty");
					JOptionPane.showMessageDialog(frame,"Course seat missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (courseBatch.isEmpty()) {
				    // empty courseBatch
					System.out.println("Course Batch Empty");
					JOptionPane.showMessageDialog(frame,"Course batch missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year1Module1.getText().isEmpty()) {
				    // empty year1Module1
					System.out.println("Year 1 Module 1 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y1M1 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year1Module2.getText().isEmpty()) {
				    // empty year1Module2
					System.out.println("Year 1 Module 2 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y1M2 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year1Module3.getText().isEmpty()) {
				    // empty year1Module3
					JOptionPane.showMessageDialog(frame,"Y1M3 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year2Module1.getText().isEmpty()) {
				    // empty year2Module1
					System.out.println("Year 2 Module 1 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y2M1 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year2Module2.getText().isEmpty()) {
				    // empty year2Module2
					System.out.println("Year 2 Module 2 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y2M2 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year2Module3.getText().isEmpty()) {
				    // empty year2Module3
					System.out.println("Year 2 Module 3 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y2M3 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year3Module1.getText().isEmpty()) {
				    // empty year3Module1
					System.out.println("Year 3 Module 1 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y3M1 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year3Module2.getText().isEmpty()) {
				    // empty year3Module2
					System.out.println("Year 3 Module 2 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y3M2 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				if (year3Module3.getText().isEmpty()) {
				    // empty year3Module3
					System.out.println("Year 3 Module 3 Name Empty");
					JOptionPane.showMessageDialog(frame,"Y3M3 missing.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}

				String box1 = (String) comboBox1.getSelectedItem();
				String box2 = (String) comboBox2.getSelectedItem();
				String box3 = (String) comboBox3.getSelectedItem();
				String box4 = (String) comboBox4.getSelectedItem();
				String box5 = (String) comboBox5.getSelectedItem();
				String box6 = (String) comboBox6.getSelectedItem();
				String box7 = (String) comboBox7.getSelectedItem();
				String box8 = (String) comboBox8.getSelectedItem();
				String box9 = (String) comboBox9.getSelectedItem();
				
				
				String[] instructors = {box1,box2,box3,box4,box5,box6,box7,box8,box9};
				
				if (box1.equals("Choose Instructor") || box2.equals("Choose Instructor") || box3.equals("Choose Instructor") || box4.equals("Choose Instructor") || box5.equals("Choose Instructor") || box6.equals("Choose Instructor")) {
					System.out.println("Instructor not selected");
					JOptionPane.showMessageDialog(frame,"Select Instructor.","Alert",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				
				Admin ad = new Admin();
				ad.courseName = courseName;
				ad.courseSeats = courseSeat;
				ad.courseBatch = courseBatch;
				ad.addCourse();
				
				for (int i = 0; i < modules.size(); i++) {
					ad.moduleName = modules.get(i).moduleName;
					ad.instructor=instructors[i];
					ad.level = modules.get(i).level;
					ad.addModules();
				}
				
				new Dashboard();
				frame.setVisible(false);
				
			}
		});
		
		frame.getContentPane().add(lblNewLabel);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// back
				new Dashboard();
				frame.setVisible(false);
			}
		});
		btnBack.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
		btnBack.setBounds(608, 10, 76, 31);
		frame.getContentPane().add(btnBack);	
	}
	
}
