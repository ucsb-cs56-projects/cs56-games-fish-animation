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

public class JellyFish
{
    int x, y;
    boolean move;
    int count;
    JellyFish(int x, int y){
	this.x = x;
	this.y = y;
	this.move = false;
	count = 0;
    }
    int getX(){return x;}
    int getY(){return y;}
    void setX(int x) { this.x = x;}
    void setY(int y) { this.y = y;}
    void setMove(boolean b) {this.move = b; }
    boolean CheckJellyFish(){return move;}
    void moveY(int m) { y-= m;}
    void setCount() {this.count+=1;}
    int getCount() {return count;}
}
	
