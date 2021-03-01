package animeCharacterQuery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class listPage extends JFrame {

	private JPanel contentPane;
	private JTextField searchWord;
	private JTable characterInfo;
	int sortcount = 0;
	databaseupdate dataop = new databaseupdate();
	private DefaultTableModel tableinfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					listPage frame = new listPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public listPage() {

		initGUI();

		//load in the database
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (Exception e){
			//in case the database doesnt exist
			e.printStackTrace();
		}

	}
	
	public void searchforkeyword(String keyword) {
		//this.tableinfo = dataop.display(keyword);
		characterInfo.setModel(dataop.display(keyword));
	}
	
	
	public void changesort(String sortinfo) {
		//this.tableinfo = dataop.sort(sortinfo);
		characterInfo.setModel(dataop.sort(sortinfo));
	}

	public void initGUI() {

		//do prefix sum for the entire history database

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchWord = new JTextField();
		searchWord.setFont(new Font("Courier", Font.PLAIN, 14));
		searchWord.setBounds(40, 40, 400, 35);
		contentPane.add(searchWord);
		searchWord.setColumns(10);

		characterInfo = new JTable();
//		/tableinfo = dataop.display("");
		characterInfo.setModel(dataop.display(""));
		characterInfo.setBounds(40, 132, 400, 590);
		contentPane.add(characterInfo);
		//display all character on JList with the current sorting mech
		
		JScrollPane scrollCharacterInfo = new JScrollPane(characterInfo);
		scrollCharacterInfo.setBounds(40, 132, 400, 590);
		contentPane.add(scrollCharacterInfo);
		
		JButton searchButton = new JButton("search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchforkeyword(searchWord.getText());
				//mostsearchedcalc();
			}
		});

		searchButton.setBackground(Color.WHITE);
		searchButton.setFont(new Font("Courier", Font.PLAIN, 14));
		searchButton.setBounds(452, 40, 149, 35);
		contentPane.add(searchButton);
		
		JLabel sortingMech = new JLabel("First Name", SwingConstants.CENTER);
		sortingMech.setFont(new Font("Courier", Font.PLAIN, 14));
		sortingMech.setBounds(452, 124, 149, 35);
		contentPane.add(sortingMech);
		//displaysortingmech(sort);
		
		JButton setSort = new JButton("sort");
		setSort.setFont(new Font("Courier", Font.PLAIN, 14));
		setSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortcount += 1;
				if(sortcount % 3 == 0) {
					changesort("firstname");
					sortingMech.setText("First Name");
				} else if(sortcount % 3 == 1) {
					changesort("lastname");
					sortingMech.setText("Last Name");
				} else if(sortcount % 3 == 2) {
					changesort("animefrom");
					sortingMech.setText("Origin Anime");
				} else {
					System.out.println("error");
				}
			}
		});
		setSort.setBounds(452, 157, 149, 35);
		contentPane.add(setSort);
		
		JLabel searchSuggestion = new JLabel("Most Searched");
		searchSuggestion.setFont(new Font("Courier", Font.PLAIN, 14));
		searchSuggestion.setBounds(40, 87, 400, 33);
		contentPane.add(searchSuggestion);
		//display most searched word
			//record the previous search && add to the prefix sum calc
			//suggest the current most searched
		//mostsearchedcalc();
		JButton infoPageButton = new JButton("browse");
		infoPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to infoPage
				infoPage info = new infoPage("pieck");
				info.setVisible(true);
			}
		});
		infoPageButton.setFont(new Font("Courier", Font.PLAIN, 14));
		infoPageButton.setBounds(452, 226, 149, 35);
		contentPane.add(infoPageButton);
		
		JButton editPageButton = new JButton("edit");
		editPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to editPage
			}
		});
		editPageButton.setFont(new Font("Courier", Font.PLAIN, 14));
		editPageButton.setBounds(452, 273, 149, 35);
		contentPane.add(editPageButton);
		
		JButton createPageButton = new JButton("create");
		createPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to createPage
				addCharacter newcharacter = new addCharacter();
				newcharacter.setVisible(true);
			}
		});
		createPageButton.setFont(new Font("Courier", Font.PLAIN, 14));
		createPageButton.setBounds(452, 348, 149, 35);
		contentPane.add(createPageButton);
		
		JButton deletePage = new JButton("delete");
		deletePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//deleteSelection();
			}
		});
		deletePage.setFont(new Font("Courier", Font.PLAIN, 14));
		deletePage.setBounds(452, 395, 149, 35);
		contentPane.add(deletePage);

	}
	
	
	
}
