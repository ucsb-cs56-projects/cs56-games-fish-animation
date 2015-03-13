package edu.ucsb.cs56.projects.games.fish_animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.net.*;
import java.net.URL;

/**
   Implements a GUI that allows the user to choose diffculty,
   find instructions, or exit the menu.

   @author Casey Barbello
   @author Daryl Pham
   @author Jenna Cryan
   @author Josephine Vo
   @author Shadee Barzin
   @author Michele Haque
   @version for CS56, Winter 15, UCSB
*/

class Menu implements ActionListener {
    JButton Play, Instruction, Exit, Resume, Easy, Medium, Hard, VeryHard, Back, Menu;
    JFrame frame, instruct;
    int type;
    JTextArea instrLabel, instrText;
    
    public static void main (String[] args) {
	Menu menu = new Menu();
	menu.makegui();
    }
    
    /**
       Main GUI interface for the first section of the Menu.  
       Allows for user to select exit, play, or instruction.
    */
    public void makegui () {
	// Create new frame for game
	frame = new JFrame();
	frame.setSize(1366, 768);
	frame.setTitle("Main Menu");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// Center frame in screen
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	frame.setLocationRelativeTo(null);
	
	// Create background panel to hold background image
	frame.setContentPane(new JPanel() {
		URL bkgdURL = getClass().getResource("/resources/CoralReef.jpg");
		ImageIcon bkgdIcon = new ImageIcon(bkgdURL);
		Image bkgdImage = bkgdIcon.getImage();

		public void paintComponent(Graphics g){
		    super.paintComponent(g);
		    g.drawImage(bkgdImage,0,0,1366,768, this);
		}

	    });

	// Add title of game to menu
	JLabel Title = new JLabel();
	Title.setFont(new Font("arial", Font.BOLD, 100));
	Title.setForeground(new Color(0xD6FFFF));
	Title.setText("Fish Animation Game");

	// Add the different buttons to the menu
	Play = new JButton("Play Game");
	Instruction = new JButton("Instructions");
	Exit = new JButton("Exit");
	
	// Set the size of the buttons
	Play.setPreferredSize(new Dimension(400, 200));
	Instruction.setPreferredSize(new Dimension(400, 200));
	Exit.setPreferredSize(new Dimension(400, 200));
	
	// Add action listeners to the buttons
	Play.addActionListener(this);
	Instruction.addActionListener(this);
	Exit.addActionListener(this);
	
	// Set up the layout of the GUI
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
	Title.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Title);
	Play.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Play);
	frame.add(Instruction);
	Instruction.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Exit);
	Exit.setAlignmentX(frame.CENTER_ALIGNMENT);

	// Make frame visible
	frame.setVisible(true);
    }
    
    
    /**
       GUI section that allows for user to select difficulty level
     */
    public void setDifficulty () {
	
	frame.setSize(1366, 768);
	frame.setTitle("Choose a Difficulty");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Adds the different difficulty buttons
	Menu = new JButton("Main Menu");
	Easy = new JButton("Easy");
	Medium = new JButton("Medium");
	Hard = new JButton("Hard");
	VeryHard = new JButton("Very Hard");
	Resume = new JButton("Resume Game");
	
	//Sets the size of the buttons
	Menu.setPreferredSize(new Dimension(200, 75));
	Easy.setPreferredSize(new Dimension(200, 75));
	Medium.setPreferredSize(new Dimension(200, 75));
	Hard.setPreferredSize(new Dimension(200, 75));
	VeryHard.setPreferredSize(new Dimension(200,75));
	Resume.setPreferredSize(new Dimension(200,75));
	
	//Gives the buttons actionlisteners
	Menu.addActionListener(this);
	Easy.addActionListener(this);
	Medium.addActionListener(this);
	Hard.addActionListener(this);
	VeryHard.addActionListener(this);
	Resume.addActionListener(this);

	//Puts the buttons onto the frame in BorderLayout format
	frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
	Menu.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Menu);
	Easy.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Easy);
	Medium.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Medium);
	Hard.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Hard);
	VeryHard.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(VeryHard);
	Resume.setAlignmentX(frame.CENTER_ALIGNMENT);
	frame.add(Resume);
	frame.setVisible(true);
    }
    
    /**
       GUI that shows the user how to play the game.  Provides
       instructions and a description of the game overall.
    */
    public void HowToPlay() {
	
	// Creates new frame for the new menu that will pop up
	instruct = new JFrame();
	instruct.setSize(683, 384);

	// Center frame in screen
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	instruct.setLocation(dim.width/2-instruct.getSize().width/2, dim.height/2-instruct.getSize().height/2);
	instruct.setLocationRelativeTo(null);

	// Create new panel to hold instructions and label
	JPanel instructPanel = new JPanel();
	instructPanel.setLayout(new BorderLayout());
	instruct.setContentPane(instructPanel);

	// Add instructions
	instrLabel = new JTextArea("How to Play the Fish Animation Game:\n");
	instrLabel.setFont(new Font("arial",Font.BOLD,20));
	instrText = new JTextArea("The goal of the game is to eat as many fish as you can.\n"
			  + "To begin eating the fish, click on the shark and hold down the mouse to move your shark's mouth towards the fish.\n"
			  + "There is no time limit, but watch out! Jellyfish are out to get you!\n"
			  + "If you eat the jellyfish, you'll lose points.\n"
			  + "If you get too many negative points, you lose the game :(\n"
			  + "There are three different difficulties, so test your skills on all of them! Good eating!");
	instrText.setRows(6);
	instrText.setColumns(30);
	instrText.setFont(new Font("arial",Font.PLAIN,15));
	instrText.setLineWrap(false);
	instrText.setWrapStyleWord(false);

	// Add button to the instruction popup
	Back = new JButton("Menu");
	
	// Add action listener to the button to go back to the mainmenu
	Back.addActionListener(this);
	Back.setPreferredSize(new Dimension(150, 75));

	// Add text areas and button to panel
	instruct.add(instrLabel, BorderLayout.NORTH);
	instruct.add(instrText, BorderLayout.CENTER);
	instruct.add(Back, BorderLayout.SOUTH);
	instruct.setFocusable(true);
	instruct.setVisible(true);
	instruct.requestFocusInWindow();
    }
    
    
    /** 
	Method that takes in Mouse actions and converts them into
	actions that the program will perform. The actions performed 
	will occur when any of the buttons are pressed.  
    */
    
    public void actionPerformed(ActionEvent event) {
	if(event.getSource() == Play) {
	    Play.setVisible(false);
	    Instruction.setVisible(false);
	    Exit.setVisible(false);
	    setDifficulty();
	}
	if(event.getSource() == Instruction) {
	    frame.remove(Play);
	    frame.remove(Instruction);
	    frame.remove(Exit);
	    frame.setVisible(false);
	    HowToPlay();
	}
	if(event.getSource() == Exit) {
	    System.exit(0);
	}
	if(event.getSource() == Easy) {
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(3, false);
	}
	if(event.getSource() == Medium) {
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(7, false);
	}
	if(event.getSource() == Hard) {
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(14, false);
	}
	if(event.getSource() == VeryHard) {
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(22, false);
	}
	if(event.getSource() == Resume) {
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(0, true);
	}
	if(event.getSource() == Back) {
	    instruct.remove(Back);
	    instruct.remove(instrText);
	    instruct.remove(instrLabel);
	    instruct.setVisible(false);
	    makegui();
	}
	if(event.getSource() == Menu) {
	    frame.remove(Menu);
	    frame.remove(Easy);
	    frame.remove(Medium);
	    frame.remove(Hard);
	    frame.remove(Resume);
	    frame.setVisible(false);
	    makegui();
	}
    }
}
