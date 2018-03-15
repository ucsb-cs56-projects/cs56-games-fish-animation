package edu.ucsb.cs56.projects.games.fish_animation;

import java.awt.Shape; // general class for shapes
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A class for all the scum o tha sea har har har
 * that creates sea scum, be it sharks or jellyfish
 * all have a position in Posieden's kingdom
 * 
 * @author Josephine Vo
 * @author Jennifer Cryan
 * @version for CS56, Winter 2014, UCSB
*/

abstract public class ScumOfTheSea extends GeneralPathWrapper implements Shape{
  
    /**
      * Constructor
      * @param xPos x coord of the middle of the Shark
      * @param yPos y coord of the middle of the Shark
    */

    double xPos;
    double yPos;

    //Holds the sea-scum's position on the Panel

    //Gets the xLocation of yar scurvy-dog
    public abstract double getXPos();

    //Gets the yLocation of the sea-scum
    public abstract double getYPos();

    //Sets the xLocation of the sea-swine
    public abstract void setX(double x);

    //Sets the yLocation of the sea-wench
    public abstract void setY(double y);
  	
    //Moves the positon of the lily-livered kraken by m points
    abstract void move(double m);

}
