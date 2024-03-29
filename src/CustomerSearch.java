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
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import java.util.Calendar;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;

public class CustomerSearch extends JFrame {
	java.util.Date date;
	java.sql.Date sqldate;
	private JPanel contentPane;
	private JTextField NameSearch;
	private JTable table;
	public String CustomerName;
	private JRadioButton Normal;
	private JRadioButton Urgent;
	private JDateChooser Date;
	private String urgency;
	private int CustomerID;
	private String Status = "Progress";
	private JTextField SpecialInstruction;
	Connection connection = null;
	int getValue;
	private JComboBox comboBox;
	private JTextField NumberField;
	
	/**
	 * Launch the application.
	 */
	//this is for test purposes 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerSearch frame = new CustomerSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//this method generates a job number and makes sure it is unique
	public void generateJobno(String passQuery) {
		connection = sqlConnection.getConnection();
		try {
			
			Statement st = connection.createStatement();
			ResultSet set = st.executeQuery(passQuery);
			
			if(set.next()) {
				getValue = Integer.parseInt(set.getString(1));
			}
			
		}catch(Exception E) {
			
		}	
	}
	
	
	
	

	/**
	 * Create the frame.
	 */
	public CustomerSearch() {
		//calender library so when the job is made it notes the time it is recorded and when the deadline is by the exact second
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date());               // sets calendar time/date
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2-getWidth()/2, size.height/2 - getHeight()/2);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 400);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer Search");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 1164, 14);
		contentPane.add(lblNewLabel);
		//creating a button that goes back to menu
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menuFrame = new Menu();
				menuFrame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(10, 327, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(90, 75, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		NameSearch = new JTextField();
		NameSearch.setBackground(new Color(192, 192, 192));
		NameSearch.setBounds(154, 72, 155, 20);
		contentPane.add(NameSearch);
		NameSearch.setColumns(10);
		//creating a button that populates the table and you can filter the search to a name so you don't have to scroll through the whole table
		JButton btnNewButton_2 = new JButton("Search Customer");
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBackground(new Color(192, 192, 192));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String name = NameSearch.getText();
					String query = "SELECT * FROM customer WHERE `customer_name` = '" +name + "'";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_2.setBounds(341, 71, 138, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 724, 206);
		contentPane.add(scrollPane);
		//creating a table customer
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Customer_id", "customer_name", "contact_name", "phone", "address", "status", "agreed_discount", "discount_rate"
				}
			));
		
		JLabel lblNewLabel_2 = new JLabel("Customer_ID");
		lblNewLabel_2.setBounds(744, 75, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel IDLabel = new JLabel();
		IDLabel.setBounds(863, 75, 46, 14);
		contentPane.add(IDLabel);
		
		JLabel lblNewLabel_5 = new JLabel("Urgency");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(744, 140, 59, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Speical requests");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(744, 183, 108, 14);
		contentPane.add(lblNewLabel_6);
		
		//Jbutton to select the type of urgency and it sets the time for example with this one is sets deadline to 24hours later
		Normal = new JRadioButton("Normal");
		Normal.setBackground(new Color(192, 192, 192));
		Normal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Normal.isSelected()) {
					Urgent.setSelected(false);
					urgency = "normal";
					cal.add(Calendar.HOUR_OF_DAY, 24);
					
				}
			}
		});
		Normal.setBounds(809, 136, 109, 23);
		contentPane.add(Normal);
		//creating a button that is for urgent and the deadline is in 6 hours
		Urgent = new JRadioButton("Urgent");
		Urgent.setBackground(new Color(192, 192, 192));
		Urgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Urgent.isSelected()) {
					Normal.setSelected(false);
					urgency = "urgent";
					cal.add(Calendar.HOUR_OF_DAY, 6);
				}
			}
		});
		Urgent.setBounds(926, 136, 109, 23);
		contentPane.add(Urgent);
		
		SpecialInstruction = new JTextField();
		SpecialInstruction.setBackground(new Color(192, 192, 192));
		SpecialInstruction.setBounds(862, 180, 148, 20);
		contentPane.add(SpecialInstruction);
		SpecialInstruction.setColumns(10);
		//sets the variables name and id to row and column selected by clicking on it
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
				CustomerName = tblModel.getValueAt(table.getSelectedRow(), 1).toString();
				CustomerID =(int) tblModel.getValueAt(table.getSelectedRow(), 0);
				IDLabel.setText(CustomerName);
	        }
	    });
		//creating a button that creates the actual job for the specific customer 
		JButton btnNewButton_3 = new JButton("CreateJob");
		btnNewButton_3.setForeground(Color.BLUE);
		btnNewButton_3.setBackground(new Color(192, 192, 192));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				//java.sql.Date date = (java.sql.Date) cal.getTime();
				
				try {
					//converting a String to an integer
					int Number = Integer.parseInt(NumberField.getText());
					//method for the job number
					//generateJobno("SELECT COUNT(`job_id`)+1 FROM `job`");
					//String JNumber = new SimpleDateFormat("ddMM").format(new Date())+getValue;
					String query = "INSERT INTO `job`(`customer_id`,`deadline`, `urgency`, `status`, `special_instructions`, `Price`, `number`) VALUES (?,?, ?, ?,?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					//pst.setString(1,JNumber);
					pst.setInt(1,CustomerID);
					pst.setString(2, sdf.format(cal.getTime()));
					pst.setString(3, urgency);
					pst.setString(4, Status);
					pst.setString(5,SpecialInstruction.getText());
					pst.setFloat(6, 0);
					pst.setInt(7, Number);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Job Created");
					addTasks tasksFrame = new addTasks();
					tasksFrame.setVisible(true);
					dispose();
					}
				catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_3.setBounds(1059, 327, 115, 23);
		contentPane.add(btnNewButton_3);
		//this loads the table with all customers rather than filtering by name 
		JButton btnNewButton_1 = new JButton("All Customers");
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBackground(new Color(192, 192, 192));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = sqlConnection.getConnection();
				try {
					String query = "SELECT * FROM customer";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				}catch(Exception E) {
					JOptionPane.showMessageDialog(null,E);
				}
			}
		});
		btnNewButton_1.setBounds(585, 327, 149, 23);
		contentPane.add(btnNewButton_1);
		
		NumberField = new JTextField();
		NumberField.setBackground(new Color(192, 192, 192));
		NumberField.setBounds(863, 224, 86, 20);
		contentPane.add(NumberField);
		NumberField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Number Of Jobs");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(744, 227, 108, 14);
		contentPane.add(lblNewLabel_3);
		
		
		
		
		
		
	}
}