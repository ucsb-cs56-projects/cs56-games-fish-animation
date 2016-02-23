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

/**
   Implements a GUI that allows the user to choose diffculty,
   find instructions, or exit the menu.

   @author Casey Barbello
   @author Daryl Pham
   @author Jenna Cryan
   @author Josephine Vo
   @author Abhijit Kulkarni
   @author Angela Yung
   @version for CS56, Winter 16, UCSB
*/

class Menu implements ActionListener {

    JButton Play, Instruction, Exit, Resume, Easy, Medium, Hard, Back, Menu, Credit;
    JFrame frame, instruct, load;

    int type;
    JTextArea text;
    JPanel textpan;
    JLabel textLabel, pane, p2;
    
    public static void main (String[] args) {
	Menu menu = new Menu();

	menu.makegui();
    }
    
    /**
       Main GUI interface for the first section of the Menu.  
       Allows for user to select exit, play, or instruction.
    */
    public void makegui () {

       	frame = new JFrame();
	frame.setSize(800,625);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setTitle("Welcome to Fish Game!");
	URL back = getClass().getResource("/resources/background.gif");
	ImageIcon bg = new ImageIcon(back);
        pane = new JLabel(bg);
	//Adds the different buttons to the menu
	Play = new JButton("PLAY GAME");
	Instruction = new JButton("INSTRUCTIONS");
	Exit = new JButton("EXIT");
	Credit = new JButton("(C) 2016");
	frame.setLayout(null);
	pane.setLayout(null);
	//set coordinates and size of the buttons
	Play.setBounds(25,25,150,75);
	Instruction.setBounds(25,500,150,75);
	Exit.setBounds(625,500,150,75);
	Credit.setBounds(625,25,150,75);

	//Adds the action listeners to the buttons
	Play.addActionListener(this);
	Instruction.addActionListener(this);
	Exit.addActionListener(this);
	Credit.addActionListener(this);

	//sets the jbutton to transparent with a white border and the specific font
	Play.setOpaque(false);
	Play.setContentAreaFilled(false);
	Play.setBorder(BorderFactory.createLineBorder(Color.white));
	Play.setForeground(Color.white);
	Play.setFont(new Font("Century Gothic", Font.PLAIN, 18));

	//mouse listener for the hover effect
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
	Credit.setContentAreaFilled(false);
	Credit.setBorder(BorderFactory.createLineBorder(Color.white));
	Credit.setForeground(Color.white);
	Credit.setFont(new Font("Century Gothic", Font.PLAIN, 18));

	//mouse listener for the hover effect
	Credit.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
	        Credit.setForeground(new Color(255,175,75));
	        Credit.setBorder(BorderFactory.createLineBorder(new Color(255,175,75)));

	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
	        Credit.setForeground(Color.white);
	        Credit.setBorder(BorderFactory.createLineBorder(Color.white));
	    }
	});

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

	//doesn't allow tab focus
	Play.setFocusable(false);
	Instruction.setFocusable(false);
	Exit.setFocusable(false);
	Credit.setFocusable(false);
	//Sets up the background image
	pane.setSize(800,625);
	pane.add(Play);
	pane.add(Instruction);
	pane.add(Exit);
	pane.add(Credit);
	frame.setContentPane(pane);
	frame.setVisible(true);
    }
    
    
    /**
       GUI section that allows for user to select difficulty level
     */
    public void setDifficulty () {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		URL back = getClass().getResource("/resources/background.jpeg");
		ImageIcon bg = new ImageIcon(back);
		p2 = new JLabel(bg);
		p2.setLayout(null);
		//Adds the different difficulty buttons
		JLabel diff = new JLabel("<html><div style='text-align:center;'>Choose your<br>difficulty!</html>");
		diff.setFont(new Font("Century Gothic", Font.BOLD, 48));
		diff.setForeground(new Color(75,255,255));
		diff.setBounds(20,-75,400,400);
		Menu = new JButton("MAIN MENU");
		Easy = new JButton("EASY");
		Medium = new JButton("MEDIUM");
		Hard = new JButton("HARD");
		Resume = new JButton("RESUME");

		Menu.setBounds(475,475,150,80);
		Easy.setBounds(75,370,150,50);
		Medium.setBounds(75,445,150,50);
		Hard.setBounds(75,520,150,50);
		Resume.setBounds(635,30,150,80);
	
		Resume.setOpaque(false);
		Resume.setContentAreaFilled(false);
		Resume.setBorder(BorderFactory.createLineBorder(Color.white));
		Resume.setForeground(Color.white);
		Resume.setFont(new Font("Century Gothic", Font.PLAIN, 18));

		//mouse listener for the hover effect
		Resume.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        Resume.setForeground(new Color(75,255,255));
		        Resume.setBorder(BorderFactory.createLineBorder(new Color(75,255,255)));

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

		//mouse listener for the hover effect
		Menu.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		        Menu.setForeground(new Color(75,255,255));
		        Menu.setBorder(BorderFactory.createLineBorder(new Color(75,255,255)));
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

		//mouse listener for the hover effect
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

		//mouse listener for the hover effect
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

		//mouse listener for the hover effect
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
		//Set
		//Sets the size of the buttons
		

		//Gives the buttons actionlisteners
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
    
    /**
       GUI that shows the user how to play the game.  Provides
       instructions and a description of the game overall.
    */
    public void HowToPlay() {
	
	//Creates new frame for the new menu that will pop up
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	instruct = new JFrame();
	instruct.setSize(300,300);
	//Creates text label for instructions
	textLabel = new JLabel();
	textLabel.setText("<html><p>Hello! The goal of the game is to eat as many fish as you can. "
			  + "There's no time limit, but watch out! Jellyfish are out to get you! "
			  + "If you eat the jellyfish, you'll lose points! "
			  + "If you get too many negative points, you lose :( "
			  + "To eat the fish, click on the shark and hold down the mouse button to move your shark's mouth to the fish."
			  + "You can also use the arrow keys to move the fish as well."
			  + "There are three different difficulties, so test your skills on all of them! Good eating!</p></html>");

	//Adds button to the instruction popup
	Back = new JButton("Menu");
	
	//Adds action listener to the button to go back to the mainmenu
	Back.addActionListener(this);
	Back.setPreferredSize(new Dimension(150, 75));

	
	//Adds the layout for the button and the textArea
	instruct.getContentPane().add(BorderLayout.NORTH, textLabel);
	instruct.getContentPane().add(BorderLayout.SOUTH, Back);
	instruct.setVisible(true);
    }

   
    /** 
	Method that takes in Mouse actions and converts them into
	actions that the program will perform. The actions performed 
	will occur when any of the buttons are pressed.  
    */
    
    public void actionPerformed(ActionEvent event) {
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
	if(event.getSource() == Resume){
		frame.setVisible(false);
		FishAnimationEnvironment f = new FishAnimationEnvironment(0,true);
	}
	if(event.getSource() == Play) {
	    frame.remove(Play);
	    frame.remove(Instruction);
	    frame.remove(Exit);
	    frame.remove(Credit);
	    setDifficulty();
	}
	if(event.getSource() == Exit) {
	    System.exit(0);
	}
	if(event.getSource() == Instruction) {
	    frame.remove(Play);
	    frame.remove(Instruction);
	    frame.remove(Exit);
	    frame.remove(Credit);
	    frame.setVisible(false);
	    HowToPlay();
	}
	if(event.getSource() == Back) {
	    instruct.remove(Back);
	    instruct.remove(textLabel);
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

