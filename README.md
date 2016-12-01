cs56-fish-animation
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

F16 final remarks

Currently the game runs, and we have the following classes:
Game logics: Score, Score Manager, ScoreComparer, ScumOftheSea, JellyFish, Fish, Shark, SoundEffects, Character
GUI: Menu, GeneralPathWrapper
Mixed: FishAnimationEnvironment


The main function to start the program is in Menu.java. Menu.java takes care of building the main menu of the game, and some Jbuttons are tied to some functions in FishAnimationEnvironment.java, where the bulk of the game is defined. Currently, FishAnimationEnvironemnt.java is a mix of Game Logics and GUI stuffs, so it is pretty hard to read through and understand. A code refactoring on this java file is highly recommanded. Try to separate the game logic from GUI completely.  
