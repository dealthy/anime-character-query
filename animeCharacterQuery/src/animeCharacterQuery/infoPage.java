package animeCharacterQuery;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import animeCharacterQuery.Scalr.Method;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

public class infoPage extends JFrame {
	
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchWord;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//default character
			String character = "nanamichiaki";
			public void run() {
				try {
					infoPage frame = new infoPage(character);
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
	public infoPage(String character) {
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
		JLabel pageTitle = new JLabel(character);
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
		
		//search for the image path for the corresponding character
		File imagepath = new File("/Volumes/SHARE/ComputerScience/HarryYu-IBCSdatabaseMiniProject/animeCharacterQuery/" + character + ".jpeg");
		//inserting image via JLabel
		JLabel titleimage = new JLabel("");
		titleimage.setVerticalAlignment(SwingConstants.CENTER);
		/*
		//resizing the image to scale
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage(); 
		Image newimg = image.getScaledInstance(589, 485, java.awt.Image.SCALE_AREA_AVERAGING); 
		imageIcon = new ImageIcon(newimg);
		//if bounds of JLabel changed, change getScaleInstance() as well
		 * 
		 */
		try {
			BufferedImage image = ImageIO.read(imagepath);
			System.out.println(image.getWidth());
			int prespace = ( 650 - image.getWidth() ) / 2 ;
			System.out.println(prespace);
			//BufferedImage scaledImg = Scalr.resize(image, 589, image.getWidth());
			BufferedImage scaledImg = Scalr.resize(image, Method.QUALITY, 589, 485, Scalr.OP_ANTIALIAS);
			ImageIcon imageicon = new ImageIcon(scaledImg);
			titleimage.setIcon(imageicon);
			titleimage.setBounds(prespace, 97, 589, 485);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		contentPane.add(titleimage);
		
		
	}
}
