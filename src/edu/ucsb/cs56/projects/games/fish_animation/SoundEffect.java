package edu.ucsb.cs56.projects.games.fish_animation;

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

/**
 * All sound effects and BGM
 * using enum encapsulates all sounds effect
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
		if(this != BGM)
		{
			if(volume != Volume.MUTE){
				if(clip.isRunning())
					clip.stop();
				clip.setFramePosition(0);
				clip.start();
			}
		}
		else if(this == BGM)
		{
			if(volume != Volume.MUTE){
				if(SoundEffect.FINISH.isRunning())
				{
					SoundEffect.FINISH.stop();
					clip.setFramePosition(0);
				}
				clip.loop(99);
			}
		}
		else if(this == FINISH)
		{
			if(volume != Volume.MUTE){
				if(SoundEffect.BGM.isRunning())
				{
					SoundEffect.BGM.stop();
					clip.setFramePosition(0);
				}
				clip.loop(99);
			}
		}
	}
	
	public void stop(){
		if(clip.isRunning())
			clip.stop();
	}
	
	public boolean isRunning(){
		return clip.isRunning();
	}
	
	static void init(){
		values();
	}

}
