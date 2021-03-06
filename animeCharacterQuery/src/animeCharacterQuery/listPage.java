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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class listPage extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JTextField searchWord;
	private JTable characterInfo;
	int sortcount = 0;
	public static databaseupdate dataop = new databaseupdate();
	private boolean commandonpress = false;
	private JLabel searchsuggestion;

	//launch the application + GUI
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

	//default constructor
	public listPage() throws SQLException {

		//GUI init
		initGUI();


	}
	
	//seach & sort have extra methods dedicated for it because the GUI init cannot be reloaded which
	//will cause other compoenets to reload as well
	
	//searching function
	public void searchforkeyword(String keyword) throws SQLException {
		if(dataop.animefrom != "") {
			dataop.create();
			dataop.firstname = "";
			dataop.lastname = "";
			dataop.animefrom = "";
		}
		characterInfo.setModel(dataop.display(keyword));
		String[] suggestions = dataop.searchadv();
		searchsuggestion.setText("Most Searched: " + suggestions[0] + " / Most Recent Search: " + suggestions[1]);
		dataop.addhistory(keyword);
	}
	
	//changing sort pattern
	public void changesort(String sortinfo) {
		characterInfo.setModel(dataop.sort(sortinfo));
	}

	public void initGUI() throws SQLException {

		//do prefix sum for the entire history database

		//init contentpane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//searchword field
		searchWord = new JTextField();
		searchWord.setFont(new Font("Courier", Font.PLAIN, 14));
		searchWord.setBounds(40, 40, 400, 35);
		contentPane.add(searchWord);
		searchWord.setColumns(10);
		//command + w close window, enter to search
		searchWord.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 157 ) {
					commandonpress = true;
				}
				if(e.getKeyCode() == 87 && commandonpress == true) {
					dispose();
				}
				if(e.getKeyCode() == 10 && searchWord.getText() != "") {
					try {
						searchforkeyword(searchWord.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		//if "ENTER" key is detected, search automatically start

		//main table
		characterInfo = new JTable();
		characterInfo.setModel(dataop.display(""));
		characterInfo.setBounds(40, 132, 400, 590);
		contentPane.add(characterInfo);
		//command + w close window
		searchWord.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 157 ) {
					commandonpress = true;
				}
				if(e.getKeyCode() == 87 && commandonpress == true) {
					dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});

		//scroll pane for the table
		JScrollPane scrollCharacterInfo = new JScrollPane(characterInfo);
		scrollCharacterInfo.setBounds(40, 132, 400, 590);
		contentPane.add(scrollCharacterInfo);
		
		//button to start search
		JButton searchButton = new JButton("search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchforkeyword(searchWord.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//mostsearchedcalc();
			}
		});
		searchButton.setBackground(Color.WHITE);
		searchButton.setFont(new Font("Courier", Font.PLAIN, 14));
		searchButton.setBounds(452, 40, 149, 35);
		contentPane.add(searchButton);
		
		//sort mech change button
		JButton setSort = new JButton("First Name");
		setSort.setFont(new Font("Courier", Font.PLAIN, 14));
		setSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortcount += 1;
				if(sortcount % 3 == 0) {
					changesort("firstname");
					setSort.setText("First Name");
				} else if(sortcount % 3 == 1) {
					changesort("lastname");
					setSort.setText("Last Name");
				} else if(sortcount % 3 == 2) {
					changesort("animefrom");
					setSort.setText("Origin Anime");
				} else {
					System.out.println("error");
				}
			}
		});
		setSort.setBounds(452, 157, 149, 35);
		contentPane.add(setSort);
		
		//suggest the most searched + most recent searched item
		searchsuggestion = new JLabel("");
		String[] suggestions = dataop.searchadv();
		searchsuggestion.setText("Most Searched: " + suggestions[0] + " / Most Recent Search: " + suggestions[1]);
		searchsuggestion.setFont(new Font("Courier", Font.PLAIN, 14));
		searchsuggestion.setBounds(40, 87, 561, 33);
		contentPane.add(searchsuggestion);
		
		//open infopage
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
		
		//open infopage editor mode
		JButton editPageButton = new JButton("edit");
		editPageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//go to infopage editor mode
			}
		});
		editPageButton.setFont(new Font("Courier", Font.PLAIN, 14));
		editPageButton.setBounds(452, 273, 149, 35);
		contentPane.add(editPageButton);
		
		//go to create screen
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
		
		//delete the selected item
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 157 ) {
			commandonpress = true;
		}
		if(e.getKeyCode() == 87 && commandonpress == true) {
			dispose();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
