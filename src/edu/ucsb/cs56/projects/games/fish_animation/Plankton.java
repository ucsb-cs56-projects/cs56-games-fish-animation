package edu.ucsb.cs56.projects.games.fish_animation;

import java.awt.geom.GeneralPath; // combinations of lines and curves
import java.awt.geom.Line2D; 
import java.awt.geom.Ellipse2D;

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
public class Plankton extends ScumOfTheSea{
    /** 
	Constructor
	@param x x coord of the middle of the Fish
	@param y y coord of the middle of the Fish
	@param width of the Fish
	@param height of the Fish
    */
    
    private double xPos,yPos;
    private boolean moveL;
    private int count;

    private double body_width = 5, body_height = 10;
    private double larm_x, larm_y, rarm_x, rarm_y, lfoot_x, lfoot_y, rfoot_x, rfoot_y;
    
    /**
       draws the specified fish
    */
    public Plankton(double x, double y, boolean moveL) {
	this.xPos = x;
	this.yPos = y;
	this.moveL = moveL;
	count = 0;

	Ellipse2D.Double body =
	    new Ellipse2D.Double(xPos - body_width, yPos - body_height,
				 2*body_width, 2*body_height);
	Line2D.Double larm =
	    new Line2D.Double(xPos - body_width, yPos,
			      xPos - body_width - body_width/2, yPos - body_height/2);
	Line2D.Double rarm =
	    new Line2D.Double(xPos + body_width, yPos,
			      xPos + body_width + body_width/2, yPos - body_height/2);
	Line2D.Double lfoot =
	    new Line2D.Double(xPos - body_width/2, yPos + 3*body_height/4,
			      xPos - body_width/2, yPos + 5*body_height/4);
	Line2D.Double rfoot =
	    new Line2D.Double(xPos + body_width/2, yPos + 3*body_height/4,
			      xPos + body_width/2, yPos + 5*body_height/4);

	GeneralPath wPlank = this.get();
	wPlank.append(body, false);
	wPlank.append(larm, false);
	wPlank.append(rarm, false);
	wPlank.append(lfoot, false);
	wPlank.append(rfoot, false);
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
	this.xPos = x;
    }

    /**
       Y is set in the constructor
    */
    @Override 
    public void setY(double y) {
	this.yPos = y;
    }
    

    /**
       plankton moves in constructor
    */
    @Override 
    public void move(double m) {
	moveL = !(moveL);
    }

    public boolean moveLeft() {
	return moveL;
    }
}
