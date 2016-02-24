package edu.ucsb.cs56.projects.games.fish_animation;

import java.util.*;
import java.io.*;

public class ScoreManager {
    
    private ArrayList<Score> scores;
    private static final String HIGHSCORE_FILE = "scores.dat"; //file of saved scores
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public ScoreManager() {
        //initialising the scores-arraylist
        scores = new ArrayList<Score>();
    }

    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }

    private void sort() {
        ScoreComparer comparator = new ScoreComparer();
        Collections.sort(scores, comparator);
    }

    public void addScore(int timer, int score) {
        loadScoreFile();
        scores.add(new Score(timer, score));
        updateScoreFile();
    }

    //loads the score file
    public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
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
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
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

    public String getHighScoreString() {
        String highscoreString = "Rank" + "\t" + "Time" + "\t\t" + "Fish Eaten \n";
	int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();

	//if more than 10 scores in the file, then only include top 10 scores
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += (i + 1) + "." + "\t" + scores.get(i).getTime() + "\t" + "\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
    }
}
