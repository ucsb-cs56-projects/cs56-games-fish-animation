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

W16 final remarks

What the code does: Each sea creature class extends ScumOfTheSea to be represented in the ocean. The menu class takes care of everything up to the start of the game. The FishAnimationEnvironment is where the bulk of the game is defined. The features that it supports are saving the game as well as saving high scores at the end of the game (this is defined in ScoreManager.java as well as Score.java and ScoreComparer.java. Even though there is a move() function defined in each class of the sea creatures, a lot of the sea creatures are animated not using the move(). For refactoring, the layout in Menu.java can be improved on by using the card layout and the gridbag layout. The code cann be made more modular as well. The FishAnimationEnvironment currently just has a bunch of classes defined so it is a bit difficult to go through and understand. The utilization of the  abstact class could be done better as well, since a lot of the methods defined in the inherited classes don't get used.
