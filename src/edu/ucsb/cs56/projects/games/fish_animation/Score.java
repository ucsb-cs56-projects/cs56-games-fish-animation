package edu.ucsb.cs56.projects.games.fish_animation;

import java.io.Serializable;

public class Score implements Serializable {
  private int eaten;
  private int time;
  
  public int getScore() {
    return eaten;
  }

  public int getTime() {
    return time;
  }

  public Score(int timer, int eaten) {
    this.eaten = eaten;
    this.time = timer;
  }
}
