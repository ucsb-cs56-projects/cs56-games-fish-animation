package edu.ucsb.cs56.S11.lkhuu.issue0000355;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
   Creates a JFrame that animates Fish
   @author Lawrence Khuu
   @author Casey Barbello
   @author Daryl Pham
   @version for CS56, proj01, Spring 2013, UCSB
**/


public class FishAnimation extends JFrame{
    Thread animate;
    DrawingPanel fishPanel = new DrawingPanel();
    int maxX = 1000;
    int maxY = 750;
    int maxWidth = 100;
    int numFish = 10+(int)(Math.random()*40);
    ArrayList<Fish> fishArray = new ArrayList<Fish>();    
  
    private void addNewFish(Fish fish){
	fishArray.add(fish);
    }


    private Fish createRandomFish(int xBound, int yBound, int maxWidth){
	int randomX = (int)(Math.random()*xBound);
	int randomY = (int)(Math.random()*yBound);
	int randomWidth = 10 + (int)(Math.random()*(maxWidth-10));
	return new Fish(randomX,randomY,randomWidth,randomWidth/5);
    }
   
    /**
       Constructor for FishAnimation. Creates a JFrame and a Jpanel that is placed inside of it. Animation is done on the JPanel
     **/
    public FishAnimation(){//constructor
	for(int i=0; i<numFish; i++){
	    addNewFish(createRandomFish(maxX,maxY,maxWidth));
	}
	getContentPane().add(fishPanel);
	animate = new Animate();
	animate.start();
	setDefaultCloseOperation(EXIT_ON_CLOSE);    
	setSize(maxX,maxY);
	setVisible(true);
    }//end constructor
 
    /**
       Innerclass for a custom Jpanel for animation
    **/
    class DrawingPanel extends JPanel{
	public void paintComponent(Graphics g){
	    Graphics2D g2 = (Graphics2D) g;
	    g2.setColor(Color.BLUE);
	    g2.fillRect(0,0,this.getWidth(),this.getHeight());	    
	    g2.setColor(Color.YELLOW);
	    for(int i=0; i<fishArray.size(); i++){
		g2.draw(fishArray.get(i));
	    }
	    Image shark = new ImageIcon("shark.jpg").getImage();
	    Shark s = new Shark(25, 250);
	    g2.drawImage(shark, s.getXPos(), s.getYPos(), this);
	}
    }//end DrawingPanel

    class Animate extends Thread{
	public void run(){//begin run method
	    try{
		while(true){
		    display(40);
		}//end while loop
	    }catch(Exception ex){
		if(ex instanceof InterruptedException){}
		else{//Unexpected exception occurred.
		    System.out.println(ex);
		    System.exit(1);//terminate program
		}//end else
	    }//end catch
	}//end run
	
	class FishInfo{
	    double x,y,width,height;
	    FishInfo(double x, double y, double width, double height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	    }
	    double getX(){return x;}
	    double getY(){return y;}
	    double getWidth(){return width;}
	    double getHeight(){return height;}
	}	   
	
	/**
	   Creates each frame
	**/
	void display(int delay) 
	    throws InterruptedException{
	    ArrayList<FishInfo> info = new ArrayList<FishInfo>();

	    for(int i=0; i<fishArray.size();i++){
		info.add(new FishInfo(fishArray.get(i).getXPos(),fishArray.get(i).getYPos(),fishArray.get(i).getWidth(),fishArray.get(i).getHeight()));
	    }
	    fishArray.clear();

	    double currentSpeed = (numFish%10)+1;
	    for(int i=0; i<info.size();i++){
		if((currentSpeed-1)<2)
		    currentSpeed = (numFish%10)+1;
		else{
		    currentSpeed--;
		}	
		double newX = info.get(i).getX()-currentSpeed;
		if(newX<0)
		    addNewFish(new Fish(fishPanel.getWidth(),(Math.random()*fishPanel.getHeight()),info.get(i).getWidth(),info.get(i).getHeight()));
		else
		    addNewFish(new Fish(newX,info.get(i).getY(),info.get(i).getWidth(),info.get(i).getHeight()));
            }
	    fishPanel.repaint();
	    
	    if(Thread.currentThread().interrupted())
		throw(new InterruptedException());
	    Thread.currentThread().sleep(delay);
	}//end display method
    }//end inner class named Animate

    /**
       Main class for running FishAnimation
    **/
    public static void main(String[] args){
        new FishAnimation();
    }//end main


}//end class Animate
