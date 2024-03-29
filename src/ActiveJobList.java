
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;

public class ActiveJobList extends JFrame {

	private JPanel contentPane;
	Connection connection = null;
	private JTable table;
	public String Status;

	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ActiveJobList frame = new ActiveJobList();
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
	public ActiveJobList() {
		//this allows me to set the screen straight in the middle when opened rather than the top left
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);

		//just setting the window size with the colours
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//standard label
		JLabel lblNewLabel = new JLabel("Active Jobs List");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 1064, 14);
		contentPane.add(lblNewLabel);

		//creating a button that cancels the page and goes back to menu to make it interactive
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(new Color(192, 192, 192));
		//action listener for when the button is clicked it goes to the menu so it loads the Menu frame
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton);
		
		//creating a button that updates the job status
		JButton btnNewButton_1 = new JButton("Update Job Status");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//connecting to the DB
				connection = sqlConnection.getConnection();
				try {
					/*
					updating the values in the table from progress to complete depending on the one you click
					using the tblModel and DefaultTableModel and then loads the same frame to reset things
					 */
					DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
					String JobID = tblModel.getValueAt(table.getSelectedRow(), 0).toString();
					String Status = "Complete";
					String sql = "UPDATE `job` SET `status` = '" + Status + "' WHERE `job_id` = '" + JobID +"'";
					PreparedStatement pst = connection.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Updated");
					ActiveJobList thisFrame = new ActiveJobList();
					thisFrame.setVisible(true);
					dispose();
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(911, 327, 163, 23);
		contentPane.add(btnNewButton_1);

		//creating a button that refreshes the table to show what is in the DB for that table
		JButton RefreshJobs = new JButton("RefreshJobs");
		RefreshJobs.setForeground(Color.BLUE);
		RefreshJobs.setBackground(new Color(192, 192, 192));
		RefreshJobs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				this populates the table with the data in the DB using the query SELECT * ...
				 */
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM job WHERE status = 'Progress'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		RefreshJobs.setBounds(132, 327, 137, 23);
		contentPane.add(RefreshJobs);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 55, 1064, 261);
		contentPane.add(scrollPane);

		/*
		fixing the table columns and creating it
		 */
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"job_id", "customer_id", "payment_id", "date_created", "deadline", "urgency", "status", "special_instructions"
				}
			));

		//creating a button that when you click new job it takes you to the frame to create a job
		JButton btnNewButton_2 = new JButton("New Job");
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerSearch csFrame = new CustomerSearch();
				csFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setBounds(812, 327, 89, 23);
		contentPane.add(btnNewButton_2);
		
		
	}
}
