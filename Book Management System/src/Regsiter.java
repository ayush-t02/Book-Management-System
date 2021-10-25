import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Regsiter extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Regsiter frame = new Regsiter();
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
	public Regsiter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registration");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lblNewLabel.setBounds(281, 21, 112, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1_2 = new JLabel("Designation:");
		lblNewLabel_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(196, 92, 89, 30);
		contentPane.add(lblNewLabel_1_2);
		
		JComboBox designation = new JComboBox(new Object[]{});
		designation.setBounds(318, 94, 176, 30);
		contentPane.add(designation);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(196, 133, 75, 30);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(318, 135, 176, 30);
		contentPane.add(name);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password:");
		lblNewLabel_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(196, 174, 75, 30);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email ID:");
		lblNewLabel_1_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(196, 215, 75, 30);
		contentPane.add(lblNewLabel_1_1_1);
		
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(318, 217, 176, 30);
		contentPane.add(email);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(318, 176, 176, 30);
		contentPane.add(password);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		btnNewButton.setBounds(281, 280, 89, 37);
		contentPane.add(btnNewButton);
	}
}
