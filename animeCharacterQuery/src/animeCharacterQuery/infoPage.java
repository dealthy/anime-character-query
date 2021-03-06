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
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ItemEvent; 
import java.awt.event.ItemListener;

public class infoPage extends JFrame implements KeyListener{
	
	//private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchWord;
	private boolean commandonpress = false;

	
	//default constructor
	public infoPage(String character) {
		
		initGUI(character);
		
	}
	
	
	//keypress interface override to listen for command + W
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 157 ) {
			commandonpress = true;
		}
		if(e.getKeyCode() == 87 && commandonpress == true) {
			dispose();
		}
	}
	
	//gui initialiser
	public void initGUI(String character) {
		
		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 798);
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
		JLabel divider = new JLabel("________________________________________________________________________________________________________________________________________________");
		divider.setForeground(Color.BLACK);
		divider.setBounds(24, 69, 1050, 16);
		contentPane.add(divider);
				
		//searchword
		searchWord = new JTextField();
		searchWord.setFont(new Font("Courier", Font.PLAIN, 14));
		searchWord.setBounds(624, 6, 420, 36);
		contentPane.add(searchWord);
		searchWord.setColumns(10);
		searchWord.addKeyListener(this);
		//"ENTER" = search
				
		//main text window
		JTextPane infotext = new JTextPane();
		infotext.setBounds(24, 97, 600, 670);
		contentPane.add(infotext);
		infotext.addKeyListener(this);
				
		JScrollPane infotextscroll = new JScrollPane(infotext);
		infotextscroll.setBounds(24, 97, 600, 670);
		contentPane.add(infotextscroll);
				
		//search for the image path for the corresponding character
		File imagepath = new File("/Volumes/SHARE/ComputerScience/HarryYu-IBCSdatabaseMiniProject/animeCharacterQuery/" + character + ".jpeg");
		//inserting image via JLabel
		JLabel titleimage = new JLabel("");
		titleimage.setVerticalAlignment(SwingConstants.CENTER);
		//resizing the image to scale
		try {
			//try to load in the image
			BufferedImage image = ImageIO.read(imagepath);
			BufferedImage scaledImg = Scalr.resize(image, Method.QUALITY, 400, 485, Scalr.OP_ANTIALIAS);
			int prespace = ( 426 - scaledImg.getWidth() ) / 2 ;
			ImageIcon imageicon = new ImageIcon(scaledImg);
			titleimage.setIcon(imageicon);
			titleimage.setBounds((prespace + 624), 100, 400, 485);
			//if bounds of JLabel changed, change getScaleInstance() as well
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane.add(titleimage);
				
				
		//add text
		String content = "Chiaki normally has a quiet and sleepy disposition, but she becomes a lot more lively when discussing games and concentrates hard when playing. She has a number of unusual habits, such as dozing off while playing games and taking long pauses during her conversations to think about everything she wants to say. She sometimes even sleeps while standing and forgets to breathe when she's playing games. She also quite often uses gaming terms and references.\n\nChiaki is very calm and she once mentioned that she has never screamed. She often helps Hajime Hinata during the Class Trials and provides important points. She can be stern and bluntly honest, even at times forgetting to take other people's feelings into consideration.\n\nWhile Chiaki is extremely observant and analytical, she lacks knowledge on a variety of ordinary subjects due to the nature of her existence. For example, she doesn't know where milk comes from (she mentions that she knows how babies are made, though). She doesn't really understand how romance or love works, is very curious about it. She also feels a bit uncomfortable around animals, because they're unpredictable and feel warm when touched, which is \"a bit scary\" in her opinion. She is occasionally a bit playful and tries to make jokes or play little pranks on Hajime, though they're a bit weird and sometimes borderline creepy.\n\nChiaki usually ends her sentences with a negative note even though she actually means to compliment someone or cheering someone, such as \"...I think\", or \"Maybe...\". Hajime once said that sometimes he wishes that Chiaki would end her positive sentence on a positive note. However, she truly does care about and wants to protect everyone. She dislikes killing more than anything and she gets very serious about the topic. She is the nicest towards Monomi and she is very understanding towards Nagito Komaeda, even though his actions frustrate her.\n\nWhile she is based on some of human Chiaki's traits, AI Chiaki differs from her in some ways. Unlike the human, she appears constantly sleepy and her way of speaking is much less natural sounding. As an AI she also appears unusually calm and unfazed, even during her own execution, which is very different from the human Chiaki's much more human and painful reaction. She also has her own identity as an AI created by Chihiro, considering him her \"dad\" and Alter Ego and Usami her siblings. Some of Chiaki's behavior also appears a bit childlike, most notably her handwriting and drawings that resemble ones of a young child, which could be a result of her being a relatively young AI. However, she matures throughout the course of the game and in the end, she even seems to be able to resist her programming, albeit just a little bit.\n\nChiaki stayed unusually calm in upheaval, such as when Usami revealed herself and when Monokuma took over the island from Usami and began the Killing School Trip. She did not believe anyone would kill, however.\n\nWhen Hajime first sees her, she is deeply engrossed in a game at the Hotel Lobby, only stopping after Nagito calls her. She awkwardly introduces herself, Hajime stating that she isn't very good at conversations. Nagito then says that Chiaki is still playing the game, to which she agrees and she also states that she needs to compose what she's going to say in her head. Chiaki then states she's tired, ending the conversation.\n\nIn the first chapter, Chiaki was worried about Monokuma infiltrating the party that the Ultimate Imposter arranged, so she stood guard outside the old hotel lodge alongside Monomi in order to keep Monokuma out of it.\n\nChiaki encountered Fuyuhiko Kuzuryu, who happened to walk by the lodge (though she suspected that Fuyuhiko actually did want to join the party). Because Chiaki was his alibi, knowing he did not enter the lodge, he was exonerated from murder suspicion. Chiaki also played an important role along with Gundham Tanaka, discovering a space under the lodge which the culprit had used as a spot to commit the murder.\n\n";
		JLabel contenttext = new JLabel();
		infotext.setText(content);
		
		//search bar label
		JLabel searchintro = new JLabel("Search:");
		searchintro.setBounds(578, 14, 45, 16);
		contentPane.add(searchintro);
		
		//original planned blocking feature
		/*
		//toggle button for blocking or not
		JToggleButton blockstatebtn = new JToggleButton("block on");
		blockstatebtn.setBounds(24, 6, 161, 29);
		contentPane.add(blockstatebtn);
		ItemListener il = new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int state = e.getStateChange();
				if (state == ItemEvent.SELECTED) {
					blockstate = true;
					blockstatebtn.setText("block off");
				} else {
					blockstate = false;
				}
					blockstatebtn.setText("block on");
			}
		}; 
		blockstatebtn.addItemListener(il);
		*/
		
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//default character
			String character = "pieck";
			public void run() {
				try {
					infoPage frame = new infoPage(character);
					frame.setVisible(true);
					//frame.addKeyListener(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getKeyCode());
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
