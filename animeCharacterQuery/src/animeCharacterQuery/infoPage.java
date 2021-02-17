package animeCharacterQuery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Image;

public class infoPage extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchWord;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					infoPage frame = new infoPage();
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
	public infoPage() {
		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//page title
		JLabel pageTitle = new JLabel("Page Title");
		pageTitle.setFont(new Font("Courier", Font.PLAIN, 20));
		pageTitle.setBounds(24, 49, 599, 36);
		contentPane.add(pageTitle);
		
		//divider
		JLabel divider = new JLabel("_____________________________________________________________________________________");
		divider.setForeground(Color.BLACK);
		divider.setBounds(24, 69, 596, 16);
		contentPane.add(divider);
		
		//search bar
		searchWord = new JTextField();
		searchWord.setFont(new Font("Courier", Font.PLAIN, 14));
		searchWord.setBounds(466, 6, 157, 36);
		contentPane.add(searchWord);
		searchWord.setColumns(10);
		
		//inserting image via JLabel
		JLabel titleimage = new JLabel("");
		titleimage.setVerticalAlignment(SwingConstants.CENTER);
		//resizing the image to scale
		ImageIcon imageIcon = new ImageIcon("/Volumes/SHARE/ComputerScience/HarryYu-IBCSdatabaseMiniProject/animeCharacterQuery/doctorGiovanni.png");
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(323, 485, java.awt.Image.SCALE_SMOOTH); 
		imageIcon = new ImageIcon(newimg);
		//if bounds of JLabel changed, change getScaleInstance() as well 
		titleimage.setIcon(imageIcon);
		titleimage.setBounds(300, 97, 323, 485);
		contentPane.add(titleimage);
	}
}
