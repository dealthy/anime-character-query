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
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

public class infoPage extends JFrame {

	private JPanel contentPane;
	private JTextField searchWord;
	infoPage frame = new infoPage();
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 800);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchWord = new JTextField();
		searchWord.setEditable(false);
		searchWord.setBounds(423, 6, 197, 31); 
		searchWord.setColumns(10);
		
		JLabel pageTitle = new JLabel("Page Title");
		pageTitle.setFont(new Font("Courier", Font.PLAIN, 20));
		pageTitle.setBounds(24, 49, 599, 36);
		contentPane.add(pageTitle);
		
		JLabel divider = new JLabel("_____________________________________________________________________________________");
		divider.setForeground(Color.BLACK);
		divider.setBounds(24, 69, 596, 16);
		contentPane.add(divider);

		//image component added with manual code
		frame.add(new JLabel(new ImageIcon("Path/To/Your/Image.png")));

	}
}
