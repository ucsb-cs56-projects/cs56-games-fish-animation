package edu.ucsb.cs56.projects.games.fish_animation;

import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.net.*; //url
import java.util.concurrent.TimeUnit;
import java.lang.*;
import javax.imageio.*;
import javax.management.loading.PrivateClassLoader;

import java.io.*;

/**
 * Implements a GUI that allows the user to choose diffculty, find instructions,
 * or exit the menu.
 * 
 * @author Casey Barbello
 * @author Daryl Pham
 * @author Jenna Cryan
 * @author Josephine Vo
 * @author Abhijit Kulkarni
 * @author Angela Yung
 * @version for CS56, Winter 16, UCSB
 */

class Menu implements ActionListener {

	private JButton Play, Instruction, Exit, Resume, Easy, Medium, Hard, Back, Menu, PlayBGM, PauseBGM, HighScore, NextPage;
	private JTextField Credit;
	private JButton Character, CMenu, Kwhale, Shark;
	private JFrame frame, instruct;
	

	JLabel textLabel, pane, p2;

	boolean character_type = true; // true = shark, false = kwhale

	public static void main(String[] args) {
		Menu menu = new Menu();

		menu.makegui();

		// pre-load all sound effects by calling init method
		SoundEffect.init();
		// set the volume
		SoundEffect.volume = SoundEffect.Volume.MEDIUM;
		// start the bgm
		SoundEffect.FINISH.stop();
		SoundEffect.BGM.play();
	}

	/**
	 * Main GUI interface for the first section of the Menu. Allows for user to
	 * select exit, play, or instruction.
	 */
	public void makegui() {

		frame = new JFrame();
		frame.setSize(800, 625);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Welcome to Fish Game!");
		URL back = getClass().getResource("/resources/background.gif");
		ImageIcon bg = new ImageIcon(back);
		pane = new JLabel(bg);
		// Adds the different buttons to the menu
		Play = new JButton("PLAY GAME");
		Instruction = new JButton("INSTRUCTIONS");
		Exit = new JButton("EXIT");
		Credit = new JTextField("(C) 2016");
		Character = new JButton("CHARACTER");
		frame.setLayout(null);
		pane.setLayout(null);
		HighScore = new JButton("HIGH SCORES");

		// Adds image icon to control BGM
		PlayBGM = new JButton();
		PauseBGM = new JButton();

		try {
			Image imgPlay = ImageIO.read(getClass().getResource("/resources/play.png")).getScaledInstance(50, 50,
					java.awt.Image.SCALE_SMOOTH);
			Image imgPause = ImageIO.read(getClass().getResource("/resources/pause.png")).getScaledInstance(50, 50,
					java.awt.Image.SCALE_SMOOTH);
			PlayBGM.setIcon(new ImageIcon(imgPlay));
			PauseBGM.setIcon(new ImageIcon(imgPause));
		} catch (IOException ex) {
		}

		// set coordinates and size of the buttons
		Play.setBounds(25, 25, 150, 75);
		Instruction.setBounds(25, 500, 150, 75);
		HighScore.setBounds(25, 400, 150, 75);
		Exit.setBounds(625, 500, 150, 75);
		Credit.setBounds(625, 25, 150, 75);
		Character.setBounds(25, 125, 150, 75);
		PlayBGM.setBounds(625, 100, 50, 50);
		PauseBGM.setBounds(685, 100, 50, 50);

		// Adds the action listeners to the buttons
		Play.addActionListener(this);
		Instruction.addActionListener(this);
		HighScore.addActionListener(this);
		Exit.addActionListener(this);
		Character.addActionListener(this);
		PlayBGM.addActionListener(this);
		PauseBGM.addActionListener(this);

		HighScore.setOpaque(false);
		HighScore.setContentAreaFilled(false);
		HighScore.setBorder(BorderFactory.createLineBorder(Color.white));
		HighScore.setForeground(Color.white);
		HighScore.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		HighScore.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				HighScore.setForeground(Color.green);
				HighScore.setBorder(BorderFactory.createLineBorder(Color.green));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				HighScore.setForeground(Color.white);
				HighScore.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});
		
		PlayBGM.setOpaque(false);
		PlayBGM.setContentAreaFilled(false);
		PlayBGM.setBorder(BorderFactory.createLineBorder(Color.black));
		PlayBGM.setForeground(Color.white);
		PlayBGM.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		PlayBGM.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				PlayBGM.setForeground(Color.green);
				PlayBGM.setBorder(BorderFactory.createLineBorder(Color.green));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				PlayBGM.setForeground(Color.white);
				PlayBGM.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});

		PauseBGM.setOpaque(false);
		PauseBGM.setContentAreaFilled(false);
		PauseBGM.setBorder(BorderFactory.createLineBorder(Color.black));
		PauseBGM.setForeground(Color.white);
		PauseBGM.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		PauseBGM.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				PauseBGM.setForeground(Color.white);
				PauseBGM.setBorder(BorderFactory.createLineBorder(Color.green));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				PauseBGM.setForeground(Color.white);
				PauseBGM.setBorder(BorderFactory.createLineBorder(Color.black));
			}
		});

		// sets the jbutton to transparent with a white border and the specific
		// font
		Play.setOpaque(false);
		Play.setContentAreaFilled(false);
		Play.setBorder(BorderFactory.createLineBorder(Color.white));
		Play.setForeground(Color.white);
		Play.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// mouse listener for the hover effect
		Play.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Play.setForeground(Color.green);
				Play.setBorder(BorderFactory.createLineBorder(Color.green));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Play.setForeground(Color.white);
				Play.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		Credit.setOpaque(false);
		Credit.setBackground(new Color(0,0,0,0));
		Credit.setForeground(Color.white);
		Credit.setHorizontalAlignment(SwingConstants.CENTER);
		Credit.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// Instruction
		Instruction.setOpaque(false);
		Instruction.setContentAreaFilled(false);
		Instruction.setBorder(BorderFactory.createLineBorder(Color.white));
		Instruction.setForeground(Color.white);
		Instruction.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		Instruction.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Instruction.setForeground(Color.green);
				Instruction.setBorder(BorderFactory.createLineBorder(Color.green));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Instruction.setForeground(Color.white);
				Instruction.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		Exit.setOpaque(false);
		Exit.setContentAreaFilled(false);
		Exit.setBorder(BorderFactory.createLineBorder(Color.white));
		Exit.setForeground(Color.white);
		Exit.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		Exit.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Exit.setForeground(Color.red);
				Exit.setBorder(BorderFactory.createLineBorder(Color.red));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Exit.setForeground(Color.white);
				Exit.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		// Character
		Character.setOpaque(false);
		Character.setContentAreaFilled(false);
		Character.setBorder(BorderFactory.createLineBorder(Color.white));
		Character.setForeground(Color.white);
		Character.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		Character.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Character.setForeground(Color.green);
				Character.setBorder(BorderFactory.createLineBorder(Color.green));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Character.setForeground(Color.white);
				Character.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		// doesn't allow tab focus
		Play.setFocusable(false);
		Instruction.setFocusable(false);
		Exit.setFocusable(false);
		Credit.setFocusable(false);
		Character.setFocusable(false);
		// Sets up the background image
		pane.setSize(800, 625);
		pane.add(Play);
		pane.add(Instruction);
		pane.add(HighScore);
		pane.add(Exit);
		pane.add(Credit);
		pane.add(Character);
		pane.add(PlayBGM);
		pane.add(PauseBGM);
		frame.setContentPane(pane);
		frame.setVisible(true);
	}

	/**
	 * GUI section that allows for user to select difficulty level
	 */
	public void setDifficulty() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL back = getClass().getResource("/resources/background.jpeg");
		ImageIcon bg = new ImageIcon(back);
		p2 = new JLabel(bg);
		p2.setLayout(null);
		// Adds the different difficulty buttons
		JLabel diff = new JLabel("<html><div style='text-align:center;'>Choose your<br>difficulty!</html>");
		diff.setFont(new Font("Century Gothic", Font.BOLD, 48));
		diff.setForeground(new Color(75, 255, 255));
		diff.setBounds(20, -75, 400, 400);
		Menu = new JButton("MAIN MENU");
		Easy = new JButton("EASY");
		Medium = new JButton("MEDIUM");
		Hard = new JButton("HARD");
		Resume = new JButton("RESUME");

		Menu.setBounds(475, 475, 150, 80);
		Easy.setBounds(75, 370, 150, 50);
		Medium.setBounds(75, 445, 150, 50);
		Hard.setBounds(75, 520, 150, 50);
		Resume.setBounds(635, 30, 150, 80);

		Resume.setOpaque(false);
		Resume.setContentAreaFilled(false);
		Resume.setBorder(BorderFactory.createLineBorder(Color.white));
		Resume.setForeground(Color.white);
		Resume.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// mouse listener for the hover effect
		Resume.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Resume.setForeground(new Color(75, 255, 255));
				Resume.setBorder(BorderFactory.createLineBorder(new Color(75, 255, 255)));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Resume.setForeground(Color.white);
				Resume.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		Menu.setOpaque(false);
		Menu.setContentAreaFilled(false);
		Menu.setBorder(BorderFactory.createLineBorder(Color.white));
		Menu.setForeground(Color.white);
		Menu.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// mouse listener for the hover effect
		Menu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Menu.setForeground(new Color(75, 255, 255));
				Menu.setBorder(BorderFactory.createLineBorder(new Color(75, 255, 255)));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Menu.setForeground(Color.white);
				Menu.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		Easy.setOpaque(false);
		Easy.setContentAreaFilled(false);
		Easy.setBorder(BorderFactory.createLineBorder(Color.white));
		Easy.setForeground(Color.white);
		Easy.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// mouse listener for the hover effect
		Easy.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Easy.setForeground(Color.green);
				Easy.setBorder(BorderFactory.createLineBorder(Color.green));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Easy.setForeground(Color.white);
				Easy.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		Medium.setOpaque(false);
		Medium.setContentAreaFilled(false);
		Medium.setBorder(BorderFactory.createLineBorder(Color.white));
		Medium.setForeground(Color.white);
		Medium.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// mouse listener for the hover effect
		Medium.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Medium.setForeground(Color.yellow);
				Medium.setBorder(BorderFactory.createLineBorder(Color.yellow));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Medium.setForeground(Color.white);
				Medium.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		Hard.setOpaque(false);
		Hard.setContentAreaFilled(false);
		Hard.setBorder(BorderFactory.createLineBorder(Color.white));
		Hard.setForeground(Color.white);
		Hard.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// mouse listener for the hover effect
		Hard.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Hard.setForeground(Color.red);
				Hard.setBorder(BorderFactory.createLineBorder(Color.red));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Hard.setForeground(Color.white);
				Hard.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});
		// Set
		// Sets the size of the buttons

		// Gives the buttons actionlisteners
		Menu.addActionListener(this);
		Easy.addActionListener(this);
		Medium.addActionListener(this);
		Hard.addActionListener(this);
		Resume.addActionListener(this);
		Menu.setFocusable(false);
		Easy.setFocusable(false);
		Medium.setFocusable(false);
		Hard.setFocusable(false);
		Resume.setFocusable(false);

		p2.add(Menu);
		p2.add(Easy);
		p2.add(Medium);
		p2.add(Hard);
		p2.add(Resume);
		p2.add(diff);
		frame.setContentPane(p2);
		frame.setVisible(true);
	}

	public void setCharacter() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * URL sharkurl = getClass().getResource("/resources/shark.jpg");
		 * ImageIcon shark = new ImageIcon(shark).getImage(); URL killerwhaleurl
		 * = getClass().getResource("/resources/killerwhale.png"); ImageIcon
		 * killerwhale = new ImageIcon(killerwhale).getImage();
		 */

		// set the background
		URL back = getClass().getResource("/resources/background.jpeg");
		ImageIcon bg = new ImageIcon(back);
		p2 = new JLabel(bg);
		p2.setLayout(null);

		// text to choose character
		JLabel diff = new JLabel("<html><div style='text-align:center; '>Choose your<br>character</html>");
		diff.setFont(new Font("Century Gothic", Font.BOLD, 28));
		diff.setForeground(new Color(75, 255, 255));
		diff.setBounds(75, 75, 400, 200);

		// create the buttons
		Shark = new JButton();
		Kwhale = new JButton();
		try {
			Image shark = ImageIO.read(getClass().getResource("/resources/shark.jpg"));
			Image killerwhale = ImageIO.read(getClass().getResource("/resources/kwhale.gif"));
			Shark.setIcon(new ImageIcon(shark));
			Kwhale.setIcon(new ImageIcon(killerwhale));
		} catch (IOException e) {
		}

		CMenu = new JButton("MAIN MENU");
		CMenu.setBounds(475, 475, 150, 80);
		Shark.setBounds(75, 250, 210, 115);
		Kwhale.setBounds(75, 445, 210, 115);

		Shark.setOpaque(false);
		Shark.setContentAreaFilled(false);
		Shark.setBorder(BorderFactory.createLineBorder(Color.white));
		Shark.setForeground(Color.white);

		// mouse listener for the hover effect
		Shark.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Shark.setForeground(new Color(75, 255, 255));
				Shark.setBorder(BorderFactory.createLineBorder(new Color(75, 255, 255)));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Shark.setForeground(Color.white);
				Shark.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		Kwhale.setOpaque(false);
		Kwhale.setContentAreaFilled(false);
		Kwhale.setBorder(BorderFactory.createLineBorder(Color.white));
		Kwhale.setForeground(Color.white);

		// mouse listener for the hover effect
		Kwhale.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				Kwhale.setForeground(new Color(75, 255, 255));
				Kwhale.setBorder(BorderFactory.createLineBorder(new Color(75, 255, 255)));

			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				Kwhale.setForeground(Color.white);
				Kwhale.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		CMenu.setOpaque(false);
		CMenu.setContentAreaFilled(false);
		CMenu.setBorder(BorderFactory.createLineBorder(Color.white));
		CMenu.setForeground(Color.white);
		CMenu.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		// mouse listener for the hover effect
		CMenu.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				CMenu.setForeground(new Color(75, 255, 255));
				CMenu.setBorder(BorderFactory.createLineBorder(new Color(75, 255, 255)));
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				CMenu.setForeground(Color.white);
				CMenu.setBorder(BorderFactory.createLineBorder(Color.white));
			}
		});

		CMenu.addActionListener(this);
		Shark.addActionListener(this);
		Kwhale.addActionListener(this);

		CMenu.setFocusable(false);
		Shark.setFocusable(false);
		Kwhale.setFocusable(false);

		p2.add(CMenu);
		p2.add(Shark);
		p2.add(Kwhale);
		p2.add(diff);
		frame.setContentPane(p2);
		frame.setVisible(true);
	}

	/**
	 * GUI that shows the user how to play the game. Provides instructions and a
	 * description of the game overall.
	 */
	public void HowToPlay() {

		// Creates new frame for the new menu that will pop up
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instruct = new JFrame();
		// Creates text label for instructions
		textLabel = new JLabel();
		
		textLabel.setText("<html><h3>Welcome to Fish Animation !</h3>"
				+ "<p>Hello! The goal of the game is to eat as many fish as you can. "
				+ "Right now, the target is 50 points."
				+ "There's no time limit, but watch out! Jellyfish are out to get you! "
				+ "If you eat the jellyfish, you'll lose health! " 
				+ "If your health is 0 or you get too many negative points, you lose :( "
				+ "There are three different difficulties, so test your skills on all of them! Good eating!</p></html>");

		// Adds button to the instruction popup
		NextPage = new JButton("Next");

		// Adds action listener to the button to go back to the mainmenu
		NextPage.addActionListener(this);
		NextPage.setPreferredSize(new Dimension(150, 75));

		// Adds the layout for the button and the textArea
		instruct.setSize(350, 550);
		instruct.setAlwaysOnTop( true );
		instruct.setLocationByPlatform( true );
		instruct.setLocationRelativeTo(pane);
		instruct.getContentPane().add(BorderLayout.NORTH, textLabel);
		instruct.getContentPane().add(BorderLayout.SOUTH, NextPage);
		instruct.setVisible(true);
	}
	
	public void HighScores(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ScoreManager highscore = new ScoreManager();
		String message = "Easy\n";
		message += highscore.getHighScoreString(3);
		message += "Normal\n";
		message += highscore.getHighScoreString(7);
		message += "Hard\n";
		message += highscore.getHighScoreString(14);
		
		JFrame highscores = new JFrame();
		highscores.setSize(550, 300);
		highscores.setAlwaysOnTop( true );
		highscores.setLocationByPlatform( true );
		highscores.add(new TextArea(message));
		//highscores.pack();
		highscores.setLocationRelativeTo(pane);
		highscores.setVisible( true );
	}

	/**
	 * Method that takes in Mouse actions and converts them into actions that
	 * the program will perform. The actions performed will occur when any of
	 * the buttons are pressed.
	 */

	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == Easy) {
			frame.setVisible(false);
			FishAnimationEnvironment f = new FishAnimationEnvironment(character_type, 3, false);
		}
		if (event.getSource() == Medium) {
			frame.setVisible(false);
			FishAnimationEnvironment f = new FishAnimationEnvironment(character_type, 7, false);
		}
		if (event.getSource() == Hard) {
			frame.setVisible(false);
			FishAnimationEnvironment f = new FishAnimationEnvironment(character_type, 14, false);
		}
		if (event.getSource() == Resume) {
			frame.setVisible(false);
			FishAnimationEnvironment f = new FishAnimationEnvironment(character_type, 0, true);
		}
		if (event.getSource() == Shark) {
			character_type = true;
		}
		if (event.getSource() == Kwhale) {
			character_type = false;
		}

		if (event.getSource() == PauseBGM) {
			SoundEffect.BGM.pause();
		}
		if (event.getSource() == PlayBGM) {
			SoundEffect.BGM.play();
		}
		if (event.getSource() == Play) {
			frame.remove(Play);
			frame.remove(Instruction);
			frame.remove(Exit);
			frame.remove(Credit);
			frame.remove(Character);
			setDifficulty();
		}
		if (event.getSource() == Exit) {
			System.exit(0);
		}
		if (event.getSource() == Instruction) {
			HowToPlay();
		}if (event.getSource() == HighScore) {

			HighScores();
		}
		if(event.getSource() == NextPage){
			textLabel = new JLabel();
			String s = "<html><h3>How to Play</h3>" 
					+ "<p>To eat the fish, "
					+ "click on the shark and hold down the mouse button to move your shark's mouth to the fish.</p>"
					+ "<p>You can also use the arrow keys to move the fish as well."
					+ "<ul>"
					+ "<li> &#x2191 moves the shark upward for a short distance</li>"
					+ "<li> &#x2190 arrow moves the shark left for a short distance</li>"
					+ "<li> &#x2192 arrow moves the shark right for a short distance</li>"
					+ "<li> &#x2193 arrow moves the shark downward for a short distance</li>"
					+ "</ul>"
					+ "</p></html>";
			
			textLabel.setText(s);
			
			Back = new JButton("Menu");
			Back.addActionListener(this);
			Back.setPreferredSize(new Dimension(150, 75));
			instruct.getContentPane().removeAll();
			instruct.getContentPane().add(BorderLayout.NORTH, textLabel);
			instruct.getContentPane().add(BorderLayout.SOUTH, Back);
			instruct.getContentPane().revalidate();
			instruct.repaint();
		}
		if (event.getSource() == Back) {
			instruct.remove(Back);
			instruct.remove(textLabel);
			instruct.setVisible(false);
	//      makegui();
		}
		if (event.getSource() == Menu) {
			frame.remove(Menu);
			frame.remove(Easy);
			frame.remove(Medium);
			frame.remove(Hard);
			frame.remove(Resume);
			frame.setVisible(false);
			makegui();
		}
		if (event.getSource() == Character) {
			frame.remove(Play);
			frame.remove(Instruction);
			frame.remove(Exit);
			frame.remove(Credit);
			frame.remove(Character);
			setCharacter();
		}
		if (event.getSource() == CMenu) {
			frame.remove(Shark);
			frame.remove(Kwhale);
			frame.remove(CMenu);
			frame.setVisible(false);
			makegui();
		}
	}
}
