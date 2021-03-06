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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class addCharacter extends JFrame implements KeyListener{

	private JPanel contentPane;
	private JTextField firstname;
	private JTextField lastname;
	private JTextField animefrom;
	private boolean commandonpress;
	

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
		
		//first name field
		firstname = new JTextField();
		firstname.setBounds(173, 79, 248, 34);
		contentPane.add(firstname);
		firstname.setColumns(10);
		firstname.addKeyListener(this);
		
		//lastname field
		lastname = new JTextField();
		lastname.setColumns(10);
		lastname.setBounds(173, 125, 248, 34);
		contentPane.add(lastname);
		lastname.addKeyListener(this);

		
		//origin anime field
		animefrom = new JTextField();
		animefrom.setColumns(10);
		animefrom.setBounds(173, 167, 248, 34);
		contentPane.add(animefrom);
		animefrom.addKeyListener(this);

		
		//button to create
		JButton createbutton = new JButton("create");
		createbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				listPage.dataop.firstname = firstname.getText();
				listPage.dataop.lastname = lastname.getText();
				listPage.dataop.animefrom = animefrom.getText();
				dispose();
				
			}
		});
		createbutton.setFont(new Font("Courier", Font.PLAIN, 14));
		createbutton.setBounds(267, 213, 154, 29);
		contentPane.add(createbutton);
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
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
