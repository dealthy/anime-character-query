package animeCharacterQuery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addCharacter extends JFrame {

	private JPanel contentPane;
	private JTextField $firstname;
	private JTextField $lastname;
	private JTextField $animefrom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCharacter frame = new addCharacter();
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
	public addCharacter() {

		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 430);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Courier", Font.PLAIN, 14));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//first name label
		JLabel firstnamelabel = new JLabel("first name: ");
		firstnamelabel.setFont(new Font("Courier", Font.PLAIN, 14));
		firstnamelabel.setBounds(61, 86, 113, 23);
		contentPane.add(firstnamelabel);
		
		//input last label
		JLabel lastnamelabel = new JLabel("last name: ");
		lastnamelabel.setFont(new Font("Courier", Font.PLAIN, 14));
		lastnamelabel.setBounds(61, 132, 113, 23);
		contentPane.add(lastnamelabel);
		
		//anime from label
		JLabel animefromlabel = new JLabel("anime from: ");
		animefromlabel.setFont(new Font("Courier", Font.PLAIN, 14));
		animefromlabel.setBounds(61, 179, 113, 23);
		contentPane.add(animefromlabel);
		
		//first name label
		$firstname = new JTextField();
		$firstname.setBounds(173, 79, 248, 34);
		contentPane.add($firstname);
		$firstname.setColumns(10);
		
		$lastname = new JTextField();
		$lastname.setColumns(10);
		$lastname.setBounds(173, 125, 248, 34);
		contentPane.add($lastname);
		
		$animefrom = new JTextField();
		$animefrom.setColumns(10);
		$animefrom.setBounds(173, 167, 248, 34);
		contentPane.add($animefrom);
		
		JButton createbutton = new JButton("create");
		createbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createnewcharacter();
			}
		});
		createbutton.setFont(new Font("Courier", Font.PLAIN, 14));
		createbutton.setBounds(267, 213, 154, 29);
		contentPane.add(createbutton);
		
	}


	private void createnewcharacter() {
		
	}
}