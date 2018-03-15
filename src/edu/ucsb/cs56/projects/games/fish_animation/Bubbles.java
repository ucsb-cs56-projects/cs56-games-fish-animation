package edu.ucsb.cs56.projects.games.fish_animation;


/**
 * Creates a Bubbles object to hold X position, 
 * Y position, and diamter of Bubble
 * 
 * @author Christina Morris
 * @author Mathew Glodack
 * @author Josephine Vo
 * @author Jenna Cryan
 * @version CS56, Winter 2014, UCSB
 */


public class Bubbles extends ScumOfTheSea
{
    private double x, y;
    private int diameter;
    
    /** 
	* Bubbles Constructor
	* @param x sets the x position
	* @param y sets the y position
	* @param diameter sets the diameter of the Bubble
    */
    Bubbles(double x, double y, int diameter) {
	this.x = x;
	this.y = y;
	this.diameter = diameter;
    }

    /** 
	* Gets the X position of the Bubble
	* @return X the position of the Bubble
    */
    @Override 
    public double getXPos() {
	return x;
    }
    
    /** 
	* Gets the Y position of the Bubble
	* @return Y the position of the Bubble
    */
    @Override 
    public double getYPos() {
	return y;
    }
    
    /** 
	* Gets the Diameter
	* @return Returns the diameter of the object
    */
    int getDiameter() {
	return diameter;
    }
    
    /** 
	* Sets the X position of the Bubbles object
	* @param x sets the X position of the Bubbles ojbect
    */
    @Override 
    public void setX(double x) {
	this.x = x;
    }
    
    /** 
	* Sets the Y position of the Bubbles object
	* @param y sets the Y position of the Bubbles object
    */
    @Override 
    public void setY(double y) {
	this.y = y;
    }
    
    /** 
	* Changes the position of the Bubble
	* @param m changes the position of the Bubbles object
    */
    @Override 
    public void move(double m) {
	y -= m;
    }
}
	
