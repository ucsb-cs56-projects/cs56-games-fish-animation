package edu.ucsb.cs56.projects.games.fish_animation;

import java.awt.geom.GeneralPath; // combinations of lines and curves
import java.awt.geom.AffineTransform; // translation, rotation, scale
import java.awt.Shape; // general class for shapes
import java.awt.geom.Point2D; 
import java.awt.geom.Line2D; 
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.geom.PathIterator;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
   A shark class that creates a shark that faces 
   in the opposite direction of the fish moving
   across the screen.
      
   @author Casey Barbello
   @author Daryl Pham
   @author Jenna Cryan
   @author Josephine Vo
   @author Jazarie Thach
   @author Felicia Truong
   @version for CS56, Winter 2014, UCSB
*/

public class Shark extends ScumOfTheSea {
    /**
       Constructor
       @param xPos x coord of the middle of the Shark
       @param yPos y coord of the middle of the Shark
    */

    double xPos;
    double yPos;

    /**
       Holds the shark's position on the Panel
    */
    public Shark(double x, double y) {
	this.xPos = x;
	this.yPos = y;
    }

    /**
       Gets the xLocation of the shark
       @return the x position of the shark
    */
    @Override 
    public double getXPos() {
	return xPos;
    }

    /**
       Gets the yLocation of the shark
       @return the y position of the shark
    */
    @Override 
    public double getYPos() {
        return yPos;
    }
    
    /**
       Sets the xLocation of the shark
       @return set the x position of the shark
    */
    @Override 
    public void setX(double x) {
	xPos = x;
    }
	
    /**
       Sets the yLocation of the shark
       @return set the y position of the shark
    */
    @Override 
    public void setY(double y) {
	yPos = y;
    }
    
    /**
       Override abstract method in ScumOfTheSea
       shark does mot move by calling this method,
       shark movement dictated by mouse events
    */
    @Override 
    void move(double m){
	;
    }
}
