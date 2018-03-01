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
 * @author: Xiaocheng Stephen Hu
*/


public enum SoundEffect{
	JELLYFISH("resources/jellyfish.wav"),
	FISH("resources/fish.wav"),
//	BOAT("resources/boat.wav"),
	FINISH("resources/finish.wav"),
	BGM("resources/BGM.wav");
	
	public static enum Volume{
		MUTE, LOW, MEDIUM, HIGH
	}

	public static Volume volume = Volume.MEDIUM;
	
	private SoundEffect(String fileName) {
		try{
			URL url = this.getClass().getClassLoader().getResource(fileName);
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

	public void reduceVolume(){
		if(clip.isRunning()) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(gainControl.getValue()-2.0f);
		}
	}

	public void increaseVolume(){
		if(clip.isRunning()) {
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(gainControl.getValue()+2.0f);
		}
	}
	
	public boolean isRunning(){
		return clip.isRunning();
	}
	
	static void init(){
		values();
	}

}