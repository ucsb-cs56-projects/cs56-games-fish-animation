package edu.ucsb.cs56.projects.games.fish_animation;

public enum Character {
	WHALE("Killer Whale"),
	SHARK("shark");
	
	private String chararcterName;
	
	private Character(String name){
		this.chararcterName = name;
	}
	
	public String getName(){
		return chararcterName;
	}
}
