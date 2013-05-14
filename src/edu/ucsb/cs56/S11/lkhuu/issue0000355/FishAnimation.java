package edu.ucsb.cs56.S11.lkhuu.issue0000355;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.lang.Object.*;

/**
   Creates a JFrame that animates Fish
   @author Lawrence Khuu
   @version for CS56, lab08, Spring 2011
   Updated Fish Animation for S13
   @author Mathew Glodack, Christina Morris
   @version CS56, S13, project01
**/

public class FishAnimation extends JFrame{
    Thread animate;
    DrawingPanel fishPanel = new DrawingPanel();
    int maxX = 250;
    int maxY = 250;
    int maxWidth = 100;
    int boatX = maxX;//hold the position of the boat
    int maxD = 10;//holds the maximum diameter of the bubbles
    int numFish = 10+(int)(Math.random()*40);
    int numBubbles = 10+(int)(Math.random()*20);//creates a random amount of bubbles
    ArrayList<Fish> fishArray = new ArrayList<Fish>();
    //Creates an ArrayList of Bubbles to display
    ArrayList<Bubbles> bubblesArray = new ArrayList<Bubbles>();
    //Creates an ArrayList of JellyFish to display
    ArrayList<JellyFish> jellyfish = new ArrayList<JellyFish>();
  
    private void addNewFish(Fish fish){
	fishArray.add(fish);
    }

    /**Method addNewBubbles adds Bubbles to the ArrayList
     *@param bubble a Bubbles object
     */
    private void addNewBubbles(Bubbles bubble){
	bubblesArray.add(bubble);
    }

    private Fish createRandomFish(int xBound, int yBound, int maxWidth){
	int randomX = (int)(Math.random()*xBound);
	int randomY = (int)(Math.random()*yBound);
	int randomWidth = 10 + (int)(Math.random()*(maxWidth-10));
	return new Fish(randomX,randomY,randomWidth,randomWidth/5);
    }
   
    /** Method createsBubbles
     * @param x for the x position 
     * @param y for the y position
     * @param diameter for the diameter of the bubble
     */
    private Bubbles createBubbles( int x, int y, int diameter){
	int randomX = (int)(Math.random()*x);
	int randomY = (int)(Math.random()*y);
	int randomD = (int)(Math.random()*diameter);
	return new Bubbles(randomX, randomY, randomD);
    }
   
    /**
       Constructor for FishAnimation. Creates a JFrame and a Jpanel that is placed inside of it. Animation is done on the JPanel
     **/
    public FishAnimation(){//constructor
	for(int i=0; i<numFish; i++){
	    addNewFish(createRandomFish(maxX,maxY,maxWidth));
	}
	//Adds the random amount of Bubbles into the ArrayList
	for(int i=0; i<numBubbles; i++){
	    addNewBubbles(createBubbles(maxX,maxY,maxD));
	}
	//Creates two JellyFish name mathew and christina
	JellyFish mathew = new JellyFish(maxX-50,maxY-100);
	JellyFish christina = new JellyFish(maxX-100,maxY-50);
	//Adds mathew and christina into the jellyfish ArrayList
	jellyfish.add(mathew);
	jellyfish.add(christina);
	
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
	    Image image = new ImageIcon("Seaweed.jpg").getImage();//Gets the Seaweed.jpg and names it image
	    Image boat = new ImageIcon("cartoon-boat.jpg").getImage();//Gets the cartoon-boat.jpg and names it boat
	    g2.setColor(Color.BLUE);
	    g2.fillRect(0,0,this.getWidth(),this.getHeight());
	    for ( int i=0; i < this.getWidth()-125; i+=125 ) {
		g.drawImage(image, i, this.getHeight()-83, this);
	    }
	    //Draws the image of the boat
	    g.drawImage(boat, boatX, -135, this);
	    boatX-=1;//Changes the X position of the boat
	    if(boatX == -250)//if boat reaches the end of the Frame
		boatX = this.getWidth();//Set the Boat Position to the width of the frame
      
	    Color b = new Color(127, 255, 212);
	    for(int i=0; i<bubblesArray.size(); i++){
		int BubblesY = bubblesArray.get(i).getY()-bubblesArray.get(i).getDiameter()/2;
		int BubblesY2 = bubblesArray.get(i).getY()+bubblesArray.get(i).getDiameter()/2;
		int BubblesX = bubblesArray.get(i).getX()-bubblesArray.get(i).getDiameter()/2;
		int BubblesX2 = bubblesArray.get(i).getX()+bubblesArray.get(i).getDiameter()/2;
		//Creates a gradient color for the Bubble
		GradientPaint gradient = new GradientPaint(BubblesX, BubblesY, Color.BLUE, BubblesX2, BubblesY2, b);
		//Sets the paint to the gradient color
		g2.setPaint(gradient);
		//Draws a Bubbles object onto the screen
		g2.fillOval(bubblesArray.get(i).getX(), bubblesArray.get(i).getY(), bubblesArray.get(i).getDiameter(), bubblesArray.get(i).getDiameter());
	    }
	    //Sets the Color to PINK for the jellyfish
	    g2.setColor(Color.PINK);
	    for(int i = 0; i<jellyfish.size(); i++){
		if(jellyfish.get(i).CheckJellyFish()==true){
		    //Draws the Body of the JellyFish
		    g2.fillArc(jellyfish.get(i).getX(),jellyfish.get(i).getY(),50,40,0,180);
		    for(int j = jellyfish.get(i).getX()+5;j<jellyfish.get(i).getX()+50;j+=5){
			//Draws the Tentacles of the JellyFish
			g2.drawLine(j,jellyfish.get(i).getY()+75,j,jellyfish.get(i).getY()+10);}
		}
		else{
		    //Draws the Body of the JellyFish
		    g2.fillArc(jellyfish.get(i).getX()+5,jellyfish.get(i).getY(),40,50,0,180); 
		    for(int j = jellyfish.get(i).getX()+10;j<jellyfish.get(i).getX()+45;j+=5){
			//Draws the Tentacles of the JellyFish
			g2.drawLine(j,jellyfish.get(i).getY()+95,j,jellyfish.get(i).getY()+10);}
		}	    
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
		
	/**
	   Creates each frame
	*/
	void displayFish(int delay) //changed to display fish
	    throws InterruptedException{
	    ArrayList<FishInfo> info = new ArrayList<FishInfo>();
	    for(int i=0; i<fishArray.size();i++){
		info.add(new FishInfo(fishArray.get(i).getXPos(),fishArray.get(i).getYPos(),fishArray.get(i).getWidth(),fishArray.get(i).getHeight()));
	    }
	    fishArray.clear();
	    //Displays the Bubbles of the bubblesArray ArrayList
	    for(int i=0; i<bubblesArray.size();i++){
		if ( bubblesArray.get(i).getY() <= -20){
		    bubblesArray.get(i).setX((int) (Math.random()*fishPanel.getWidth()));
		    bubblesArray.get(i).setY(fishPanel.getHeight());
		}
		else
		    //moves the bubbles 2 pixels
		    bubblesArray.get(i).move(2);
	     
	    }
	    //Displays the jellyfish ArrayList
	    for(int i=0; i<jellyfish.size();i++){
	      if(jellyfish.get(i).getY()<=-100){
		    jellyfish.get(i).setY(fishPanel.getHeight());
		}
	      //If the jellyfish is true it moves 10 pixels and setMove to false
		if(jellyfish.get(i).CheckJellyFish()==true){
		  if ( jellyfish.get(i).getCount()%20 == 0 )
		      {
			  jellyfish.get(i).moveY(10);
			  jellyfish.get(i).setMove(false);
		      }
	      }
		//Sets move from false to true
	      if ( jellyfish.get(i).getCount()%20 == 10 )
		      jellyfish.get(i).setMove(true);
		  jellyfish.get(i).setCount();
	  }
		
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
