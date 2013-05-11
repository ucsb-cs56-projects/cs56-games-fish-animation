package edu.ucsb.cs56.S11.lkhuu.issue0000355;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
   Creates a JFrame that animates Fish
   @author Lawrence Khuu
   @version for CS56, lab08, Spring 2011
**/


public class FishAnimation extends JFrame{
    Thread animate;
    DrawingPanel fishPanel = new DrawingPanel();
    //  DrawingPanel bubblesPanel = new DrawingPanel(); //added bubble panel
    int maxX = 250;
    int maxY = 250;
    int maxWidth = 100;
    int boatX = maxX;
    int numFish = 10+(int)(Math.random()*40);
    int numBubbles = 10+(int)(Math.random()*10);
    ArrayList<Fish> fishArray = new ArrayList<Fish>();
    ArrayList<Bubbles> bubblesArray = new ArrayList<Bubbles>();
  
    private void addNewFish(Fish fish){
	fishArray.add(fish);
    }

    private void addNewBubbles(Bubbles bubble){
	bubblesArray.add(bubble);
    }

    private Fish createRandomFish(int xBound, int yBound, int maxWidth){
	int randomX = (int)(Math.random()*xBound);
	int randomY = (int)(Math.random()*yBound);
	int randomWidth = 10 + (int)(Math.random()*(maxWidth-10));
	return new Fish(randomX,randomY,randomWidth,randomWidth/5);
    }

    private Bubbles createBubbles( int x, int y, int diameter){
	int randomX = (int)(Math.random()*x);
	int randomY = (int)(Math.random()*y);
	return new Bubbles(randomX, randomY, diameter);
    }
   
    /**
       Constructor for FishAnimation. Creates a JFrame and a Jpanel that is placed inside of it. Animation is done on the JPanel
     **/
    public FishAnimation(){//constructor
	for(int i=0; i<numFish; i++){
	    addNewFish(createRandomFish(maxX,maxY,maxWidth));
	}
	for(int i=0; i<numBubbles; i++){
	    addNewBubbles(createBubbles(maxX,maxY,20));
	}
	getContentPane().add(fishPanel);
	//  	getContentPane().add(bubblesPanel); //added bubble panel
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
	    Image image = new ImageIcon("Seaweed.jpg").getImage();
	    Image boat = new ImageIcon("cartoon-boat.jpg").getImage();
	    g2.setColor(Color.BLUE);
	    g2.fillRect(0,0,this.getWidth(),this.getHeight());
	    for ( int i=0; i < this.getWidth()-125; i+=125 ) {
		g.drawImage(image, i, this.getHeight()-83, this);
	    }
	    
	    g.drawImage(boat, boatX, -135, this);
	    boatX-=1;
	    if(boatX == -250)
	    boatX = this.getWidth();
	    Color b = new Color(127, 255, 212);
	    g.setColor(b);
	    //	    g.drawOval(100, 100, 50, 50);
	    for(int i=0; i<bubblesArray.size(); i++){
		g.fillOval(bubblesArray.get(i).getX(), bubblesArray.get(i).getY(), bubblesArray.get(i).getDiameter(), bubblesArray.get(i).getDiameter());
	    }
	    g2.setColor(Color.YELLOW);
	    for(int i=0; i<fishArray.size(); i++){
		g2.draw(fishArray.get(i));
	    }
	}
    }//end DrawingPanel

    class Animate extends Thread{
	public void run(){//begin run method
	    try{
		while(true){
		    displayFish(40); //changed to display fish
		    //  displayBubbles(40);
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
	
	class BubblesInfo{ //for display bubbles
	    int x, y, diameter;
	    BubblesInfo(int x, int y, int diameter){
		this.x = x;
		this.y = y;
		this.diameter = diameter;
	    }
	    int getX(){return x;}
	    int getY(){return y;}
	    int getDiameter(){return diameter;}
	}
	
	    
	
	/**
	   Creates each frame
	**/
	void displayFish(int delay) //changed to display fish
	    throws InterruptedException{
	    ArrayList<FishInfo> info = new ArrayList<FishInfo>();
	    for(int i=0; i<fishArray.size();i++){
		info.add(new FishInfo(fishArray.get(i).getXPos(),fishArray.get(i).getYPos(),fishArray.get(i).getWidth(),fishArray.get(i).getHeight()));
	    }
	    fishArray.clear();
	    //bubblesinfo array
	    //    ArrayList<BubblesInfo> bub = new ArrayList<BubblesInfo>();
	    for(int i=0; i<bubblesArray.size();i++){
		if ( bubblesArray.get(i).getY() <= -20){
		    bubblesArray.get(i).setX((int) (Math.random()*fishPanel.getWidth()));
		    bubblesArray.get(i).setY(fishPanel.getHeight());
		}
		else
		    bubblesArray.get(i).move(1);
	    }
			
	 //)(new Bubbles(bubblesArray.get(i).getX(),bubblesArray.get(i).getY()-10, bubblesArray.get(i).getDiameter()));    
	
	    
	    // bubblesArray.clear();
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


	/*	void displayBubbles(int delay) //this moves bubbles
	    throws InterruptedException{
		ArrayList<BubblesInfo> bub = new ArrayList<BubblesInfo>();
		for(int i=0; i<bubblesArray.size();i++){
		    bub.add(new BubblesInfo(bubblesArray.get(i).getX(),bubblesArray.get(i).getY()+10, bubblesArray.get(i).getDiameter()));    
		}
	    bubblesArray.clear();
	    //		for ( i = 0; i < numBubbles; i++ ){
	    //		    bub.add(new Bubbles(Math.random()*bubblesPanel.getWidth(),0,50));
	    //	}
	    bubblesPanel.repaint();
	    
	    if(Thread.currentThread().interrupted())
		throw(new InterruptedException());
	    Thread.currentThread().sleep(delay);
	    }*/
    }//end inner class named Animate

    /**
       Main class for running FishAnimation
    **/
    public static void main(String[] args){
        new FishAnimation();
    }//end main


}//end class Animate
