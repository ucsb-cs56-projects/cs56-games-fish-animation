package edu.ucsb.cs56.projects.games.fish_animation;;

import java.util.Comparator;

public class ScoreComparer implements Comparator<Score> {
    public int compare(Score score1, Score score2) {
	
	int time1 = score1.getTime();
	int sc1 = score1.getScore();
	
	int time2 = score2.getTime();
	int sc2 = score2.getScore();

	//return  0 if equal time and eaten fish
	//return  1 if score2 is higher than score1
	//return -1 if score1 is higher than score2 
	if (time1 < time2) {
	    return -1;
	}
	else if (time2 < time1){
	    return 1;
	}
	else {
	    if(sc1 > sc2) {
		return -1;
	    }
	    else if(sc2 > sc1) {
		return 1;
	    }
	    else {
		return 0;
	    }
	}
    }
}
