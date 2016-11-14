package edu.ucsb.cs56.projects.games.fish_animation;

import java.io.Serializable;

public class Score implements Serializable {
  private int eaten;
  private int time;
  private String name;
  private Character character;
  
  public int getScore() {
    return eaten;
  }

  public int getTime() {
    return time;
  }
  
  public String getPlayerName(){
	  return name;
  }
  
  public String getCharacter(){
	  return character.getName();
  }

  public Score(int timer, int eaten, String playerName, Character c) {
    this.eaten = eaten;
    this.time = timer;
    this.name = playerName;
    this.character = c;
  }
  
}
