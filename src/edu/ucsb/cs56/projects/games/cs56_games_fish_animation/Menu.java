package edu.ucsb.cs56.projects.games.cs56_games_fish_animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;

class Menu implements ActionListener {
	JButton Play, Instruction, Exit, Easy, Medium, Hard, Back;
	JFrame frame;
	
	public static void main (String[] args) {
		Menu menu = new Menu();
		menu.makegui();
	}
	
	public void makegui (){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Play = new JButton("Play Game");
		Instruction = new JButton("Instructions");
		Exit = new JButton("Exit");
		
		Play.setPreferredSize(new Dimension(200,75));
		Instruction.setPreferredSize(new Dimension(200,75));
		Exit.setPreferredSize(new Dimension(200, 75));
		
		Play.addActionListener(this);
		Instruction.addActionListener(this);
		Exit.addActionListener(this);
		
		//MyDrawPanel drawpanel = new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.WEST, Play);
		frame.getContentPane().add(BorderLayout.CENTER, Instruction);
		frame.getContentPane().add(BorderLayout.EAST, Exit);
		//GUI.getContentPane().add(BorderLayout.CENTER, drawpanel);
		frame.setSize(600,75);
		frame.setVisible(true);
	}
	
		
	
	public void setDifficulty (){
		JFrame difficulty = new JFrame();
		difficulty.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Easy = new JButton("Easy");
		Medium = new JButton("Medium");
		Hard = new JButton("Hard");
		
		Easy.setPreferredSize(new Dimension(200,75));
		Medium.setPreferredSize(new Dimension(200,75));
		Hard.setPreferredSize(new Dimension(200, 75));
		
		Easy.addActionListener(this);
		Medium.addActionListener(this);
		Hard.addActionListener(this);
		
		//MyDrawPanel drawpanel = new MyDrawPanel();
		
		frame.getContentPane().add(BorderLayout.WEST, Easy);
		frame.getContentPane().add(BorderLayout.CENTER, Medium);
		frame.getContentPane().add(BorderLayout.EAST, Hard);
		frame.setSize(600,75);
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent event){
		if(event.getSource() == Easy){
		    new FishAnimationEnvironment("Easy");
		}
		if(event.getSource() == Medium){
		    new FishAnimationEnvironment("Medium");
		}
		if(event.getSource() == Hard){
		    new FishAnimationEnvironment("Hard");
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
		Instruction.setText("Soon...");
		}
	}
}

