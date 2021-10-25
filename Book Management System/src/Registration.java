import java.awt.EventQueue;

import javax.swing.JFrame;
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
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Registration {

	private JFrame frame;
	private JTextField name;
	private JTextField password;
	private JTextField email;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration window = new Registration();
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
	public Registration() {
		initialize();
		Connect();
	}
	
	LoginPage LoginPage;
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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 390);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(304, 11, 112, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(217, 116, 75, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(341, 118, 176, 30);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(217, 157, 75, 30);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(341, 159, 176, 30);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email ID:");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(217, 198, 75, 30);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(341, 200, 176, 30);
		frame.getContentPane().add(email);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnRegister.setBounds(309, 251, 89, 36);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblNewLabel_1_2 = new JLabel("Designation:");
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(217, 75, 89, 30);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		String [] items = { "Select Designation", "Admin", "Student"};
		final JComboBox des = new JComboBox(items);
		des.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String string = (String) des.getSelectedItem();
				if(string == "Admin") {
					
					btnRegister.addActionListener(new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent e) {
							String emailid,username, pass;
							
							emailid = email.getText();
							username = name.getText();
							pass = password.getText();
							
							if(pass.equals("") || username.equals("") || emailid.equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter required information!"); 
								 return;
							}
							
							try {
								pst = con.prepareStatement("insert into adminlogin(Email,Username,Password)values(?,?,?)");
								pst.setString(1, emailid);
								pst.setString(2, username);
								pst.setString(3, pass);
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Registration is done");
								email.setText("");
								name.setText("");
								password.setText("");
								email.requestFocus();
								
								LoginPage = new LoginPage();
								LoginPage.setVisible(true);
								frame.dispose();
								
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					
				}else {
					
					btnRegister.addActionListener(new ActionListener() {
						@SuppressWarnings("deprecation")
						public void actionPerformed(ActionEvent e) {
							String emailid,username, pass;
							
							emailid = email.getText();
							username = name.getText();
							pass = password.getText();
							
							if(pass.equals("") || username.equals("") || emailid.equals("")) {
								JOptionPane.showMessageDialog(null, "Please enter required information"); 
								 return;
							}
							
							try {
								pst = con.prepareStatement("insert into studlogin(Email,Username,Password)values(?,?,?)");
								pst.setString(1, emailid);
								pst.setString(2, username);
								pst.setString(3, pass);
								pst.executeUpdate();
								JOptionPane.showMessageDialog(null, "Registration is done");
								email.setText("");
								name.setText("");
								password.setText("");
								email.requestFocus();
								
								LoginPage = new LoginPage();
								LoginPage.setVisible(true);
								frame.dispose();
								
							}catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					});
					
				}
			}
		});
		des.setBounds(341, 77, 176, 30);
		frame.getContentPane().add(des);
	}

	public void setVisible(boolean b) {
		if (b == true) {
			frame.setVisible(true);
		}
	}
}