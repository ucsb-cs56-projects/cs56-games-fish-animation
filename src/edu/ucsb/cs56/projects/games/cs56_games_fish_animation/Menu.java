package edu.ucsb.cs56.projects.games.cs56_games_fish_animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;

/**
   Implements a GUI that allows the user to choose diffculty,
   find instructions, or exit the menu.
   @author Casey Barbello
   @author Daryl Pham
   @version for CS56, proj02, Spring 2013, UCSB
**/

class Menu implements ActionListener {
    JButton Play, Instruction, Exit, Resume, Easy, Medium, Hard, Back;
    JFrame frame, instruct;
    int type;
    JTextArea text;
    
    public static void main (String[] args) {
	Menu menu = new Menu();
	menu.makegui();
    }
    
    /**Main GUI interface for the first section of the Menu.  
		Allows for user to select exit, play, or instruction.
	*/
    public void makegui (){
	frame = new JFrame();
	frame.setSize(600,75);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Adds the different buttons to the menu
	Play = new JButton("Play Game");
	Instruction = new JButton("Instructions");
	Exit = new JButton("Exit");

		//Sets the size of the buttons
		Play.setPreferredSize(new Dimension(200,75));
		Instruction.setPreferredSize(new Dimension(200,75));
		Exit.setPreferredSize(new Dimension(200, 75));
		
		//Adds the action listeners to the buttons
		Play.addActionListener(this);
		Instruction.addActionListener(this);
		Exit.addActionListener(this);
		
		//Sets up the layout of the GUI
		frame.getContentPane().add(BorderLayout.WEST, Play);
		frame.getContentPane().add(BorderLayout.CENTER, Instruction);
		frame.getContentPane().add(BorderLayout.EAST, Exit);
		frame.setSize(600,75);
		frame.setVisible(true);
    }
    
    
    /**GUI section that allows for user to select difficulty level
     */
    public void setDifficulty (){

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//Adds the different difficulty buttons
	Easy = new JButton("Easy");
	Medium = new JButton("Medium");
	Hard = new JButton("Hard");
	Resume = new JButton("Resume Game");
	
	//Sets the size of the buttons
	Easy.setPreferredSize(new Dimension(200,75));
	Medium.setPreferredSize(new Dimension(200,75));
	Hard.setPreferredSize(new Dimension(200, 75));
	Resume.setPreferredSize(new Dimension(200,75));
	
	//Gives the buttons actionlisteners
	Easy.addActionListener(this);
	Medium.addActionListener(this);
	Hard.addActionListener(this);
	Resume.addActionListener(this);

	//Puts the buttons onto the frame in BorderLayout format
	frame.getContentPane().add(BorderLayout.WEST, Easy);
	frame.getContentPane().add(BorderLayout.CENTER, Medium);
	frame.getContentPane().add(BorderLayout.EAST, Hard);
	frame.getContentPane().add(BorderLayout.SOUTH, Resume);
	frame.setSize(600,150);
	frame.setVisible(true);
    }
    
    /**GUI that shows the user how to play the game.  Provides
    instructions and a description of the game overall.
    */
    public void HowToPlay(){
		
		//Creates new frame for the new menu that will pop up
		instruct = new JFrame();
		instruct.setSize(300,335);
		
		//Sets the font of the text in the instruction
		instruct.setFont(new Font("Verdana", Font.PLAIN, 12));
	    String str = "Hello!\n\n     This is our Shark game!  The point\n     of the game is to eat as many fish\n     as you can! But watch out! Jellyfish\n     are out to get you! Touch one of them,\n     and you'll lose points! To eat the\n     fish, just move your shark's mouth\n     to the fish. To move the shark, you\n     must click and hold the mouse button\n     and slide the mouse around the screen.\n     There are three different difficulties,\n     so test your skills on all of them!\n\n\t\tGood eating!";				
		text = new JTextArea(str);
	    
	    //Adds button to the instruction popup
		Back = new JButton("Menu");
		
		//Adds action listener to the button to go back to the mainmenu
		Back.addActionListener(this);
		Back.setPreferredSize(new Dimension(150,75));
		
		//Adds the layout for the button and the textArea
		instruct.getContentPane().add(BorderLayout.NORTH, text);
		instruct.getContentPane().add(BorderLayout.SOUTH, Back);
		instruct.setVisible(true);
	}
    
    
    /** Method that takes in Mouse actions and converts them into
		actions that the program will perform. The actions performed 
		will occur when any of the buttons are pressed.  
	*/
		
    public void actionPerformed(ActionEvent event){
	if(event.getSource() == Easy){
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(3, false);
	}
	if(event.getSource() == Medium){
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(7, false);
	}
	if(event.getSource() == Hard){
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(14, false);
	}
	if(event.getSource() == Resume){
	    frame.setVisible(false);
	    FishAnimationEnvironment f = new FishAnimationEnvironment(0, true);
	}
	if(event.getSource() == Play){
	    frame.remove(Play);
	    frame.remove(Instruction);
	    frame.remove(Exit);
	    setDifficulty();
	}
	if(event.getSource() == Exit){
	    System.exit(0);
	}
	if(event.getSource() == Instruction){
	    frame.remove(Play);
	    frame.remove(Instruction);
	    frame.remove(Exit);
	    frame.setVisible(false);
	    HowToPlay();
	}
	if(event.getSource() == Back){
		instruct.remove(Back);
		instruct.remove(text);
		instruct.setVisible(false);
		makegui();
	}
    }
}

