import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
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
import javax.swing.JLayeredPane;
import java.awt.Color;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField adminname;
	private JPasswordField adminpass;
	private JTextField studname;
	private JPasswordField studpass;

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
		
		UIDefaults uiDefaults = UIManager.getDefaults();
		uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(new Color(0, 255, 197)));
		JFrame.setDefaultLookAndFeelDecorated(true);
	}
	
	class RoundedPanel extends JPanel
    {
        private Color backgroundColor;
        private int cornerRadius = 15;

        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }

        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
        }

        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
            graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
        }
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
		setTitle("Login Page");
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(255, 215, 0));
//		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setBackground(new Color(27, 20, 100));
//		contentPane.setBackground(new Color(255, 165, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bookshop Management System - Group 29\r\n");
		lblNewLabel.setForeground(new Color(0, 255, 197));
		lblNewLabel.setFont(new Font("Overpass Mono", Font.BOLD, 24));
		lblNewLabel.setBounds(116, 11, 520, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_3_1 = new JLabel("Admin ");
		lblNewLabel_3_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_3_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_3_1.setBounds(138, 81, 60, 30);
		contentPane.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_1 = new JLabel("Username :");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1.setBounds(53, 129, 76, 31);
		contentPane.add(lblNewLabel_1);
		
		adminname = new JTextField();
		adminname.setColumns(10);
		adminname.setBounds(138, 132, 146, 29);
		contentPane.add(adminname);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password :");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(53, 171, 76, 31);
		contentPane.add(lblNewLabel_1_1);
		
		adminpass = new JPasswordField();
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
		lblNewLabel_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2.setBounds(91, 275, 157, 29);
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
		lblNewLabel_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 15));
		lblNewLabel_3.setBounds(513, 81, 60, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Username :");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(431, 129, 76, 31);
		contentPane.add(lblNewLabel_1_2);
		
		studname = new JTextField();
		studname.setColumns(10);
		studname.setBounds(513, 132, 146, 29);	
		contentPane.add(studname);
		
		studpass = new JPasswordField();
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
		lblNewLabel_2_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(460, 275, 159, 29);
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
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(431, 171, 76, 31);
		contentPane.add(lblNewLabel_1_1_1);
		
		JPanel panel = new RoundedPanel(20);
//		panel.setBackground(new Color(255, 165, 0));
		panel.setBackground(new Color(0, 255, 197));
//		panel.setBackground(Color.WHITE);
		panel.setBounds(37, 73, 270, 307);
		panel.setOpaque(false);
		contentPane.add(panel);
		
		JPanel panel_1 = new RoundedPanel(20);
//		panel_1.setBackground(new Color(255, 215, 0));
		panel_1.setBackground(new Color(0, 255, 197));
//		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(413, 75, 270, 305);
		panel_1.setOpaque(false);
		contentPane.add(panel_1);
}}