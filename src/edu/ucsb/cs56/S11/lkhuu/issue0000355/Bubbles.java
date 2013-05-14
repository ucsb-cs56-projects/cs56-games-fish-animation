package edu.ucsb.cs56.S11.lkhuu.issue0000355;

import java.awt.geom.GeneralPath; // combinations of lines and curves
import java.awt.geom.AffineTransform; // translation, rotation, scale
import java.awt.Shape; // general class for shapes
import java.awt.geom.Point2D; 
import java.awt.geom.Line2D; 
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;

/**
 * @author Christina Morris, Mathew Glodack
 * @version CS56, S13, 5/13/13, project01
 */

/**
 * Creates a Bubbles object to hold X position,Y position, and diamter of Bubble
 */

public class Bubbles
{
    int x, y, diameter;

    /**Bubbles Constructor
     * @param x sets the x position
     * @param y sets the y position
     * @param diameter sets the diameter of the Bubble
     */
    Bubbles(int x, int y, int diameter){
	this.x = x;
	this.y = y;
	this.diameter = diameter;
    }
    /**Gets the X position of the Bubble
     * @return X the position of the Bubble
     */
    int getX(){return x;}
    
    /**Gets the Y position of the Bubble
     * @return Y the position of the Bubble
     */
    int getY(){return y;}
    
    /**Gets the Diameter
     * @return Returns the diameter of the object
     */
    int getDiameter(){return diameter;}
    
    /**Sets the X position of the Bubbles object
     * @param x sets the X position of the Bubbles ojbect
     */
void setX(int x) { this.x = x;}
    
    /**Sets the Y position of the Bubbles object
     * @param y sets the Y position of the Bubbles object
     */
    void setY(int y) { this.y = y;}
    
    /**Changes the position of the Bubble
     * @param m changes the position of the Bubbles object
     */
    void move(int m) { y-= m;}
}
	
