package edu.ucsb.cs56.projects.games.fish_animation;

import java.util.*;

import com.sun.media.sound.SoftCubicResampler;

import java.io.*;

public class ScoreManager {
    
    private ArrayList<Score> scores;
    private static final String EASY_SCORE_FILE = "easy_scores.dat"; //file of saved scores
    private static final String MEDIUM_SCORE_FILE = "medium_scores.dat";
    private static final String HARD_SCORE_FILE = "hard_scores.dat";
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public ScoreManager() {
        //initialising the scores-arraylist
        scores = new ArrayList<Score>();
    }

    public ArrayList<Score> getScores(int difficulty) {
        loadScoreFile(difficulty);
        sort();
        return scores;
    }

    private void sort() {
        ScoreComparer comparator = new ScoreComparer();
        Collections.sort(scores, comparator);
    }

    public void addScore(int timer, int score, int difficulty, String playerName, Character c) {

	//Difficulties: Easy = 3; Medium = 7; Hard = 14
        loadScoreFile(difficulty);
        scores.add(new Score(timer, score, playerName, c));
        updateScoreFile(difficulty);
    }

    //loads the score file
    public void loadScoreFile(int difficulty) {
        try {
	    switch(difficulty) {
	    case 3:
		inputStream = new ObjectInputStream(new FileInputStream(EASY_SCORE_FILE));
		break;
	    case 7:
		inputStream = new ObjectInputStream(new FileInputStream(MEDIUM_SCORE_FILE));
		break;
	    case 14:
		inputStream = new ObjectInputStream(new FileInputStream(HARD_SCORE_FILE));
		break;
	    }
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
          //  System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
          //  System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
          //  System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }


    //update the score file
    public void updateScoreFile(int difficulty) {
        try {
	    switch(difficulty) {
	    case 3:
		outputStream = new ObjectOutputStream(new FileOutputStream(EASY_SCORE_FILE));
		break;
	    case 7:
		outputStream = new ObjectOutputStream(new FileOutputStream(MEDIUM_SCORE_FILE));
		break;
	    case 14:
		outputStream = new ObjectOutputStream(new FileOutputStream(HARD_SCORE_FILE));
		break;
	    }
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }

    public String getHighScoreString(int difficulty) {
        String highscoreString = "Rank"  + "\t"+ "Name" + "\t" + "Character"  + "\t" + "Time" + "\t" + "Fish Eaten \n";
	int max = 10;

        ArrayList<Score> scores;
        scores = getScores(difficulty);

        int i = 0;
        int x = scores.size();

	//if more than 10 scores in the file, then only include top 10 scores
        if (x > max) {
            x = max;
        }
        while (i < x) {
        	Score temp = scores.get(i);
            highscoreString += (i + 1) + "." + "\t" + temp.getPlayerName() + "\t" + temp.getCharacter() + "\t" + temp.getTime() + "\t" + temp.getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
}
