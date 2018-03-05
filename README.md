# cs56-fish-animation
## Javadoc: https://bryan-wu.github.io/cs56-games-fish-animation/javadoc/
===================

A fish animation

project history
===============
```
YES | mastergberry | jovo123, jcryan | A fish animation
```
```
 W14 | jcneally 5pm | jovo123, jcryan | A fish animation
```
================

# F17 update

### Welcome to Fish Animation
* Hello! The goal of the game is to eat as many fish as you can
* To help developers add more features, the game is set to win whenver you get 50 points
* There's no time limit, but watch out! Jellyfish are out to get you!
* If you eat the jellyfish, you'll lose health!
* If you touch the ship, you die immediately! Take Care!
* If your health is 0 or you get too many negative points, you lose.
* There are three different difficulties, so test your skills on all of them! Good eating!

### How to Play
* To eat the fish
* click on the shark/whale and hold down the mouse button to move your shark/whale's mouth to the fish.
* You can also use the arrow keys to move the shark/whale as well.


A lot of GUI errors have been fixed and users can have better playing experiences right now. For example, the copybutton has been changed to text, which will not confuse users any more. Besides, the background depends on the difficuilty right now. We add health bar and score bar as well. More features will be added in the future. Don't wait to play with it. The game is awesome.


# F16 final remarks

### Currently the game runs, and we have the following classes:
* Game logics: Score, Score Manager, ScoreComparer, ScumOftheSea, JellyFish, Fish, Shark, SoundEffects, Character.
* GUI: Menu, GeneralPathWrapper.
* Mixed: FishAnimationEnvironment.


The main function to start the program is in Menu.java. Menu.java takes care of building the main menu of the game, and some Jbuttons on it are tied to some functions in FishAnimationEnvironment.java, where the bulk of the game is defined. Currently, FishAnimationEnvironemnt.java is a mix of Game Logics and GUI stuffs, so it is pretty hard to read through and understand. A code refactoring on this java file is highly recommanded: try to separate the game logic from GUI. The Score Class implements Serializable so that the scores can be stored and provides that game-stop feature, but it is more usual to record the highscores in a database. It is challanging, but a nice chance to learn how to drive the database inside a Java program using JDBC. 

# F17 final remarks
Right now, the game UI has been improved a lot. To start this project, you should read the instructions and play the game direcyly. During the process, you will find the issues or new bugs. There are a lot of classes so it may be hard for you to understand some parts. Right now, FishAnimationEnvironemnt.java is a mix of Game Logics and GUI stuffs. I believe most of your edits will in this file. Menu.java is another file that we edited a lot. It is better to fully understand these two files first. We did not change anything in the database part. It is challenging, but it will be fun. There are so many parts that you can improve. Just try. 
