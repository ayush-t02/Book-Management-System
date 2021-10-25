import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField adminname;
	private JTextField adminpass;
	private JTextField studname;
	private JTextField studpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	AdminDashboard AdminDashboard;
	StudentDashboard StudentDashboard;
	Registration Registration;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	public void Connect(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/displaydb", "root", "");
		}
		catch(ClassNotFoundException ex){
			
		}
		catch(SQLException ex){
			
		}
	}
	public LoginPage() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 441);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bookshop Management System - Group 29\r\n");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
		lblNewLabel.setBounds(116, 18, 520, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3_1 = new JLabel("Admin ");
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(138, 81, 60, 30);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(53, 129, 76, 31);
		contentPane.add(lblNewLabel_1);
		
		adminname = new JTextField();
		adminname.setColumns(10);
		adminname.setBounds(138, 132, 146, 29);
		contentPane.add(adminname);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(53, 171, 76, 31);
		contentPane.add(lblNewLabel_1_1);
		
		adminpass = new JTextField();
		adminpass.setColumns(10);
		adminpass.setBounds(138, 174, 146, 29);
		contentPane.add(adminpass);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username = adminname.getText(); 
					String password = adminpass.getText();
					pst  = con.prepareStatement("select Username, Password from adminlogin where Username=? and Password=?"); 
					pst.setString(1, username);
	                pst.setString(2, password);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true){
						AdminDashboard = new AdminDashboard();
						AdminDashboard.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Incorrect username or password!");
						adminname.setText("");
						adminpass.setText("");
					}
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton.setBounds(116, 213, 89, 35);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("New Admin? Register here");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(91, 275, 146, 29);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration = new Registration();
				Registration.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton_1.setBounds(116, 315, 89, 35);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Student");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_3.setBounds(503, 81, 60, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username :");
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(431, 129, 76, 31);
		contentPane.add(lblNewLabel_1_2);
		
		studname = new JTextField();
		studname.setColumns(10);
		studname.setBounds(513, 132, 146, 29);	
		contentPane.add(studname);
		
		studpass = new JTextField();
		studpass.setColumns(10);
		studpass.setBounds(513, 174, 146, 29);
		contentPane.add(studpass);
		
		JButton btnNewButton_2 = new JButton("Login");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String username =studname.getText(); 
					String password = studpass.getText();
					pst  = con.prepareStatement("select Username, Password from studlogin where Username=? and Password=?"); 
					pst.setString(1, username);
	                pst.setString(2, password);
					ResultSet rs = pst.executeQuery();
					if(rs.next()==true){
						StudentDashboard = new StudentDashboard();
						StudentDashboard.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Enter correct username or password!");
						studname.setText("");
						studpass.setText("");
					}
					} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton_2.setBounds(497, 213, 89, 35);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("New Student? Register here");
		lblNewLabel_2_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		lblNewLabel_2_1.setBounds(473, 275, 146, 29);
		contentPane.add(lblNewLabel_2_1);
		
		JButton btnNewButton_1_1 = new JButton("Register");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration = new Registration();
				Registration.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		btnNewButton_1_1.setBounds(497, 315, 89, 35);
		contentPane.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password :");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(431, 171, 76, 31);
		contentPane.add(lblNewLabel_1_1_1);
	}
}
