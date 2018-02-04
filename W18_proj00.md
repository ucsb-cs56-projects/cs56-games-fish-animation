# BRYAN WU & XIAOCHENG STEPHEN HU

## (a) Brief Description
A HIGH QUALITY game where you play as a fish and ry to eat enough fish to reach 50 points while staying alive by avoiding jellyfish and boats. Four difficulties are avaiable: easy, medium, hard, crazy.
## (b) User Stories - Current Features
* As a player, I can use either the mouse or keyboard WASD to control the fish.
* As a player, I can choose between two fish characters to play.
* As a player, I can gain points and health by eating fish and lose points and health by eating jellyfish.
* As a player, I can choose between 4 difficulties for the game: easy, medium, hard, crazy.
* As a player, I can complete a game mode and have my score saved on the highscore board.
* As a player, I can get killed by a boat.
* As a player, I can resume the game even though I don't have a game in progress.
## (c) Brief Software Assessment
Yes the software runs well. It displays a smoothly animated fish upon launch. The main menu is working and the gameplay features controlling a fish using either a mouse of keyboard WASD to eat enough fish and earn 50 points without dying.
## (d) User Stories - Potential New Features
* As a player, I can purchase new characters, temporary power-ups, and permament perks from an in-game store.
* As a graphical interface of the game, I can prove myself not to be a so sketchy.
* As a player, I can grow my fish in size as I eat more fish so that I can eat larger fish.
* As a player, I can only eat fish smaller in size than mine (create fish larger than player's).
* As a player, I can evolve to gain new skills.
## (e) README.md Assessment
The README.md contains very little information for maintainence of the software. Clear descriptions of resolved issues and the general architecture of the game should be included, for example, the F17 update should focus more on stating whether or not their solutions to the "GUI errors" relates to the F16 final remarks on code refactoring for FishAnimationEnvironment.java rather than trying to advertise and explain the goal of the game.
## (f) build.xml Assessment
The build.xml has a few targets that needs a descriptions.
## (g) Current Issues Assessment
Most issues include a clear explanation for what can be improved. Some are ambiguous and require clarification.
## (h) Additional Issues - to be considered
https://github.com/ucsb-cs56-projects/cs56-games-fish-animation/issues/105
## (i) Assessment of Current Code
Some parts of the code are already structured in a clear fashion; however, there are a few classes that need refactoring:
* FishAnimationEnvironment.java currently contains code for both the main GUI, the score computation, AND the in-game graphics, which seems very cluttered. This needs to be re-organized into different classes. In addition, some of the code can be refactored and simplified with methods (such as object motion, etc.)
* Fish.java, Plankton.java have a lot of code in the constructors (apart from getters and setters), which should probably be replaced with methods.
* Shark.java, Jellyfish.java, Score.java, ScoreManager.java, ScoreComparer.java, and Character.java are fine.
* SoundEffect.java has clean code but doesn't seem to function (there's no background music during actual gameplay).
* Menu.java might have potential for simplifications.
## (j) Testing
The project has no JUnit tests at all. Testing should be added to the project; each test should focus on one particular class (constructor, method) and should be independent of other tests. 
