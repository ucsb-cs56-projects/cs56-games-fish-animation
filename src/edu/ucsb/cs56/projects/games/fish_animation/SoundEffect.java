package edu.ucsb.cs56.projects.games.fish_animation;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * All sound effects and BGM
 * using enum encapsulates all sounds effect
 * 
 * @author: Huiyang He
 * @author: Ziheng Zhang
*/


public enum SoundEffect{
	JELLYFISH("resources/jellyfish.wav"),
	FISH("resources/fish.wav"),
//	BOAT("resources/boat.wav"),
	FINISH("resources/finish.wav"),
	BGM("resources/BGM.wav");
	
	private SoundEffect(String fileName) {
		try{
			URL url = this.getClass().getClassLoader().getResource(fileName);
			System.out.println(url != null);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} catch(LineUnavailableException e){
			e.printStackTrace();
		}
	}

	public static enum Volume{
		MUTE, LOW, MEDIUM, HIGH
	}

	public static Volume volume = Volume.MEDIUM;

	private Clip clip;
	
	public void play(){
		if (volume != Volume.MUTE) {
			clip.start();
		}	
	}
	
	public void playEffects(){
		if (volume != Volume.MUTE) {
			if(clip.isRunning())
				clip.stop();
			clip.setFramePosition(0);
			clip.start();
		}	
	}
	
	public void pause(){
		if(clip.isRunning())
			clip.stop();
	}
	
	public void stop(){
		if(clip.isRunning())
			clip.stop();
		clip.setFramePosition(0);
	}
	
	public boolean isRunning(){
		return clip.isRunning();
	}
	
	static void init(){
		values();
	}

}
