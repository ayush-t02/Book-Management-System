import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;

import LoginPage.RoundedPanel;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Registration {

	private JFrame frame;
	private JTextField name;
	private JPasswordField password;
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
		frame.getContentPane().setBackground(new Color(27, 20, 100));
		frame.setBounds(100, 100, 725, 390);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Registration Page");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(218, 133, 86, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(342, 135, 176, 30);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(218, 174, 86, 30);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(342, 176, 176, 30);
		frame.getContentPane().add(password);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email ID:");
		lblNewLabel_1_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(218, 215, 86, 30);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(342, 217, 176, 30);
		frame.getContentPane().add(email);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnRegister.setBounds(318, 268, 101, 36);
		frame.getContentPane().add(btnRegister);
		
		JLabel lblNewLabel_1_2 = new JLabel("Designation:");
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(218, 92, 99, 30);
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
		des.setBounds(342, 94, 176, 30);
		frame.getContentPane().add(des);
		
		JPanel panel = new RoundedPanel(25);
		panel.setBackground(new Color(0, 255, 197));
		panel.setBounds(200, 29, 335, 289);
		panel.setOpaque(false);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setForeground(Color.BLACK);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Overpass Mono", Font.BOLD, 18));
		
	}

	public void setVisible(boolean b) {
		if (b == true) {
			frame.setVisible(true);
		}
	}
}