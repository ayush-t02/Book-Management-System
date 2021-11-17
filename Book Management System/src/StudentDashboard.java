import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StudentDashboard extends JFrame {

	private JPanel contentPane;
	private JTextField bookid;
	private JTextField bookname;
	private JTextField bookprice;
	private JTextField bookedition;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentDashboard frame = new StudentDashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from dashboard");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public StudentDashboard() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 822, 446);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		setTitle("Student Dashboard");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(154, 205, 50));
		contentPane.setLayout(null);
		
		JPanel panel_1 = new RoundedPanel(20);
		panel_1.setBounds(15, 55, 316, 229);
		panel_1.setBackground(new Color(255, 165, 0));
		contentPane.add(panel_1);
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book ID: ");
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel.setBounds(30, 23, 62, 24);
		panel_1.add(lblNewLabel);
		
		bookid = new JTextField();
		bookid.setBounds(130, 23, 120, 29);
		panel_1.add(bookid);
		bookid.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
		                String id = bookid.getText();

		                pst = con.prepareStatement("select Name,Edition,Price from dashboard where ID = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
		                String name = rs.getString(1);
		                String edition = rs.getString(2);
		                String price = rs.getString(3);
		                
		                bookname.setText(name);
		                bookedition.setText(edition);
		                bookprice.setText(price);
		            }
		            else {
		            	JOptionPane.showMessageDialog(null, "Please enter correct book id!");
		            }
		        } 		
				
			 catch (SQLException ex) {
		           
		        }
			}
		});
		btnNewButton.setBounds(43, 63, 89, 29);
		panel_1.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bookid.setText("");
				bookname.setText("");
                bookedition.setText("");
                bookprice.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnClear.setBounds(140, 63, 89, 29);
		panel_1.add(btnClear);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name: ");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(30, 114, 92, 24);
		panel_1.add(lblNewLabel_1);
		
		bookname = new JTextField();
		bookname.setColumns(10);
		bookname.setBounds(130, 114, 120, 29);
		panel_1.add(bookname);
		
		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(30, 149, 63, 24);
		panel_1.add(lblNewLabel_2);
		
		bookprice = new JTextField();
		bookprice.setColumns(10);
		bookprice.setBounds(130, 149, 120, 29);
		panel_1.add(bookprice);
		
		JLabel lblNewLabel_3 = new JLabel("Edition:");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(30, 184, 63, 24);
		panel_1.add(lblNewLabel_3);
		
		bookedition = new JTextField();
		bookedition.setColumns(10);
		bookedition.setBounds(130, 184, 120, 29);
		panel_1.add(bookedition);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(347, 55, 433, 229);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new RoundedPanel(20);
		panel_2.setBounds(17, 295, 765, 95);
		panel_2.setBackground(new Color(255, 165, 0));
		contentPane.add(panel_2);
		panel_2.setOpaque(false);
		panel_2.setLayout(null);
		
		final JComboBox reco = new JComboBox(new Object[]{"Choose a Tag"});
        try {
        	 pst = con.prepareStatement("select distinct Tag from dashboard");
        	 ResultSet rs = pst.executeQuery();
        	 while (rs.next()) {  
        	     reco.addItem(rs.getString("Tag"));  
        	 }
		}catch(Exception e){
			
		}
        
		reco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Tag = (String) reco.getSelectedItem();
					try {
						pst = con.prepareStatement("select * from dashboard where Tag=?");
						pst.setString(1, Tag);
						rs = pst.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
			}
		});
		reco.setBounds(134, 33, 468, 31);
		panel_2.add(reco);
		
		JLabel lblNewLabel_4 = new JLabel("Student Dashboard");
		lblNewLabel_4.setFont(new Font("Segoe UI", Font.BOLD, 17));
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setBounds(317, 8, 174, 24);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("Logout");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage = new LoginPage();
				LoginPage.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton_1.setBounds(15, 11, 89, 23);
		contentPane.add(btnNewButton_1);
		
		table_load();
	}
}
