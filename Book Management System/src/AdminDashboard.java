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
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AdminDashboard extends JFrame {

	private JPanel contentPane;
	private JTextField bookname;
	private JTextField bookedition;
	private JTextField bookprice;
	private JTextField booktag;
	private JTextField bookid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboard frame = new AdminDashboard();
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
	LoginPage LoginPage;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	
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
	public AdminDashboard() {
		Connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 455);
		setLocationRelativeTo(null);
		setTitle("Admin Dashboard");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(27, 20, 100));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Dashboard");
		lblNewLabel.setFont(new Font("Overpass Mono", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(0, 255, 197));
		lblNewLabel.setBounds(278, 0, 169, 37);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new RoundedPanel(20);
		panel_1.setBackground(new Color(0, 255, 197));
		panel_1.setBounds(8, 48, 318, 255);
		contentPane.add(panel_1);
		panel_1.setOpaque(false);
		panel_1.setLayout(null);
		
		JLabel namelbl = new JLabel("Book Name:\r\n");
		namelbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		namelbl.setForeground(Color.BLACK);
		namelbl.setBounds(32, 29, 84, 29);
		panel_1.add(namelbl);
		
		bookname = new JTextField();
		bookname.setColumns(10);
		bookname.setBounds(148, 30, 135, 29);
		panel_1.add(bookname);
		
		JLabel editionlbl = new JLabel("Book Edition:\r\n");
		editionlbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		editionlbl.setForeground(Color.BLACK);
		editionlbl.setBounds(32, 69, 96, 29);
		panel_1.add(editionlbl);
		
		JLabel pricelbl = new JLabel("Price:");
		pricelbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		pricelbl.setForeground(Color.BLACK);
		pricelbl.setBounds(32, 109, 55, 29);
		panel_1.add(pricelbl);
		
		bookedition = new JTextField();
		bookedition.setColumns(10);
		bookedition.setBounds(148, 70, 135, 29);
		panel_1.add(bookedition);
		
		bookprice = new JTextField();
		bookprice.setColumns(10);
		bookprice.setBounds(148, 110, 135, 29);
		panel_1.add(bookprice);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String bname,edition, price, tag;
				bname = bookname.getText();
				edition = bookedition.getText();
				price = bookprice.getText();
				tag = booktag.getText();
				
				try {
					pst = con.prepareStatement("insert into dashboard(Name,Edition,Price,Tag)values(?,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, tag);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Your record has been added!");
					table_load();
					bookname.setText("");
				    bookedition.setText("");
					bookprice.setText("");
					booktag.setText("");
					bookname.requestFocus();
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		save.setBounds(18, 200, 89, 38);
		panel_1.add(save);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String bname,edition,price,bid, tag;
				bname = bookname.getText();
				edition = bookedition.getText();
				price = bookprice.getText();
				bid  = bookid.getText();
				tag = booktag.getText();
				
				 try {
						pst = con.prepareStatement("update dashboard set Name= ?,Edition=?,Price=?,Tag=? where ID =?");
						pst.setString(1, bname);
			            pst.setString(2, edition);
			            pst.setString(3, price);
			            pst.setString(4, tag);
			            pst.setString(5, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Updated!");
			            table_load();
			           
			            bookname.setText("");
			            bookedition.setText("");
			            bookprice.setText("");
			            booktag.setText("");
			            bookname.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
			}
		});
		update.setBounds(117, 200, 89, 38);
		panel_1.add(update);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bid;
				bid  = bookid.getText();
				
				 try {
						pst = con.prepareStatement("delete from dashboard where ID =?");
				
			            pst.setString(1, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record deleted!");
			            table_load();
			           
			            bookname.setText("");
			            bookedition.setText("");
			            bookprice.setText("");
			            booktag.setText("");
			            bookname.requestFocus();
					}
	 
		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}				
			}
		});
		delete.setBounds(219, 200, 89, 38);
		panel_1.add(delete);
		
		JLabel taglbl = new JLabel("Tag:");
		taglbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		taglbl.setForeground(Color.BLACK);
		taglbl.setBounds(33, 149, 55, 29);
		panel_1.add(taglbl);
		
		booktag = new JTextField();
		booktag.setColumns(10);
		booktag.setBounds(148, 150, 135, 29);
		panel_1.add(booktag);
		
		JPanel panel_2_1 = new RoundedPanel(20);
		panel_2_1.setBackground(new Color(0, 255, 197));
		panel_2_1.setBounds(8, 314, 318, 86);
		contentPane.add(panel_2_1);
		panel_2_1.setOpaque(false);
		panel_2_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID:");
		lblNewLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setBounds(44, 36, 73, 24);
		panel_2_1.add(lblNewLabel_2);
		
		bookid = new JTextField();
		bookid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
			          
			            String id = bookid.getText();

			                pst = con.prepareStatement("select Name,Edition,Price,Tag from dashboard where ID = ?");
			                pst.setString(1, id);
			                ResultSet rs = pst.executeQuery();

			            if(rs.next()==true)
			            {
			                String name = rs.getString(1);
			                String edition = rs.getString(2);
			                String price = rs.getString(3);
			                String tag = rs.getString(4);
			                
			                bookname.setText(name);
			                bookedition.setText(edition);
			                bookprice.setText(price);
			                booktag.setText(tag);
			            }   
			            else
			            {
			            	bookname.setText("");
			            	bookedition.setText("");
			                bookprice.setText("");
			                booktag.setText("");
			            }
			        } 
				
				 catch (SQLException ex) {
			           
			        }
			}
		});
		bookid.setColumns(10);
		bookid.setBounds(127, 35, 148, 28);
		panel_2_1.add(bookid);
		
		JPanel panel_2 = new RoundedPanel(20);
		panel_2.setBackground(new Color(0, 255, 197));
		panel_2.setLayout(null);
		panel_2.setBounds(334, 314, 381, 86);
		panel_2.setOpaque(false);
		contentPane.add(panel_2);
		
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
		reco.setBounds(95, 29, 211, 30);
		panel_2.add(reco);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(336, 52, 376, 251);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage = new LoginPage();
				LoginPage.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(8, 11, 89, 23);
		contentPane.add(btnNewButton);
		table_load();
	}

}
