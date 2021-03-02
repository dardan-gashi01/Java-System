import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BAPERS");
		lblNewLabel.setBounds(305, 11, 68, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Office Manager");
		lblNewLabel_1.setBounds(48, 60, 89, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Shift Manager");
		lblNewLabel_2.setBounds(225, 60, 89, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Technician");
		lblNewLabel_3.setBounds(413, 60, 67, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Receptionist");
		lblNewLabel_4.setBounds(575, 60, 68, 14);
		contentPane.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login LoginFrame = new Login();
				LoginFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(549, 327, 125, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Place Job");
		btnNewButton_1.setBounds(549, 85, 125, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Take Payment");
		btnNewButton_2.setBounds(549, 135, 125, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Create Customer");
		btnNewButton_3.setBounds(549, 185, 125, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Reports");
		btnNewButton_4.setBounds(199, 85, 125, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("View Active Jobs");
		btnNewButton_5.setBounds(376, 85, 125, 23);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Create Account");
		btnNewButton_6.setBounds(25, 85, 125, 23);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Update Account");
		btnNewButton_7.setBounds(25, 135, 125, 23);
		contentPane.add(btnNewButton_7);
		
		JButton btnNewButton_8 = new JButton("Manage Customers");
		btnNewButton_8.setBounds(25, 185, 125, 23);
		contentPane.add(btnNewButton_8);
		
		JButton btnNewButton_9 = new JButton("View All Jobs");
		btnNewButton_9.setBounds(376, 135, 125, 23);
		contentPane.add(btnNewButton_9);
		
		JButton btnNewButton_10 = new JButton("Update Job Status");
		btnNewButton_10.setBounds(376, 185, 125, 23);
		contentPane.add(btnNewButton_10);
		
		JButton btnNewButton_11 = new JButton("Create Backup");
		btnNewButton_11.setBounds(25, 244, 125, 23);
		contentPane.add(btnNewButton_11);
		
		JButton btnNewButton_12 = new JButton("Restore Database");
		btnNewButton_12.setBounds(25, 300, 125, 23);
		contentPane.add(btnNewButton_12);
	}
}