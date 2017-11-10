package edu.ucsb.cs56.projects.games.fish_animation;

import java.awt.geom.GeneralPath; // combinations of lines and curves
import java.awt.geom.AffineTransform; // translation, rotation, scale
import java.awt.geom.Line2D; 
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;

/**
   A vector drawing of a Fish that implements
   the Shape interface, and so can be drawn, as well as
   rotated, scaled, etc.
      
   @author Lawrence Khuu
   @author Josephine Vo
   @author Jenna Cryan
   @author Abhijit Kulkarni
   @author Angela Yung
   @version for CS56, Winter 2016, UCSB
   
*/
public class Fish extends ScumOfTheSea{
    /** 
	Constructor
	@param x x coord of the middle of the Fish
	@param y y coord of the middle of the Fish
	@param width of the Fish
	@param height of the Fish
    */
    
    double xPos;
    double yPos;
    double width;
    double height;
    
    /**
       draws the specified fish
    */
    public Fish(double x, double y, double width, double height) {
	this.xPos = x;
	this.yPos = y;
	this.width = width;
	this.height = height;
	GeneralPath wholeFish = this.get();
	double currentX = x - (width / 2);
	double currentY = y;
	double nextX = 0;
	double nextY = 0;
	
  //URL fishURL = getClass().getResource("/resources/Fish.jpg");
  //Image fish = new ImageIcon(fishURL).getImage();

		for(double j = 0;j < 100; j += 1.0) { 
	    nextX = currentX + (((3.0 / 4.0) * width) / 100.0);
	    nextY = currentY + ((height / 2.0) * (Math.sin(Math.PI * ((j + 1.0) / 100.0)) - Math.sin(Math.PI * ((j - 1.0) / 100.0))));
	    wholeFish.append(new Line2D.Double(currentX, currentY, nextX, nextY), false);
	    currentX = nextX;
	    currentY = nextY;
	}

	nextX = currentX + (1.0 / 4.0) * width;
	nextY = y + (height / 2.0);
	wholeFish.append(new Line2D.Double(currentX, currentY, nextX, nextY), false);
	currentX = nextX;
	currentY = nextY;

	nextY = y - (height / 2.0);
	wholeFish.append(new Line2D.Double(currentX, currentY, nextX, nextY), false);
	currentX = nextX;
	currentY = nextY;
	
	nextX = currentX - (1.0 / 4.0) * width;
	nextY = y;
	wholeFish.append(new Line2D.Double(currentX, currentY, nextX, nextY), false);
	currentX = nextX;
	currentY = nextY;
	
        for(double j = 0; j < 100; j += 1.0) {
            nextX = currentX - (((3.0 / 4.0) * width) / 100.0);
            nextY = currentY - ((height / 2.0) * (Math.sin(Math.PI * ((j + 1.0) / 100.0)) - Math.sin(Math.PI * ((j - 1.0) / 100.0))));
            wholeFish.append(new Line2D.Double(currentX, currentY, nextX, nextY), false);
            currentX = nextX;
            currentY = nextY;
        }
    }
    
    /**
       gets the xLocation of the fish
    */
    @Override 
    public double getXPos() {
	return xPos;
    }
    
    /**
       gets the yLocation of the fish
    */
    @Override 
    public double getYPos() {
        return yPos;
    }
    
    /**
       X is set in the constructor
    */
    @Override 
    public void setX(double x) {
	
    }

    /**
       Y is set in the constructor
    */
    @Override 
    public void setY(double y) {
	
    }
    
    /**
       gets the Width of the fish
    */
    public double getWidth() {
        return width;
    }
    
    /**
       gets the height of the fish
    */
    public double getHeight() {
        return height;
    }

    /**
       fish moves in constructor
    */
    @Override 
    public void move(double m) {
	;
    }
}
