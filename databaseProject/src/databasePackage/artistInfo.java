package databasePackage;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class artistInfo extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JList artistList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					artistInfo frame = new artistInfo();
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
	public artistInfo() {
		
		//initalize GUI components
		initGUI();
		
		//load in the database
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e){
			//in case the database doesnt exist
			e.printStackTrace();
		}
		
	}
	
	public void initGUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		artistList = new JList();
		artistList.setBounds(32, 31, 204, 209);
		contentPane.add(artistList);
		
		JButton btnSelectArtist = new JButton("Select Artist");
		btnSelectArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getList(null);
			}
		});
		btnSelectArtist.setBounds(288, 50, 117, 29);
		contentPane.add(btnSelectArtist);
		
		JButton btnFindArtist = new JButton("Find Artist");
		btnFindArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getList(nameField.getText());
			}
		});
		btnFindArtist.setBounds(288, 91, 117, 29);
		contentPane.add(btnFindArtist);
		
		nameField = new JTextField();
		nameField.setBounds(288, 132, 130, 26);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
	}

	private void getList(String target) {
		Connection conn; 

		try {

			// open a database connection

			String url = "jdbc:sqlite:/Volumes/SHARE/ComputerScience/resources/sqlite_databases/Chinook_Sqlite.sqlite";

			conn = DriverManager.getConnection(url);


			// let the database do its work

			String sql = "select * from Artist";

			//if search target is avaliable, then search for target
			if(target != null) {
				sql += " where name like '" + target + "'";
			}

			//order by Name has to be at the end, so it is added 
			//after the where statement
			sql += " order by name";

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(sql);


			/* 

			stmt.excuteUpdate(sql);
			update database command after changing it

			prepared statement

			*/


			// put the items in the JList

			DefaultListModel model = new DefaultListModel();

			artistList.setModel(model);

			while(rs.next()){

				model.addElement(rs.getString("name"));

			}


			// close the connection

			stmt.close();

			conn.close();


		} catch (Exception f) {

			f.printStackTrace();

		}
	}
}
