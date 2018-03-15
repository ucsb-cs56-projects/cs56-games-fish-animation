package edu.ucsb.cs56.projects.games.fish_animation;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputAdapter;

/**
 * Creates a JFrame that animates Fish and allows for a shark to eat the fish.
 * Tracks the amount of fish eaten with an elapsed amount of time.
 *
 * @author Bryan Wu
 * @author Xiaocheng Stephen Hu
 * @author Lawrence Khuu
 * @author Casey Barbello
 * @author Daryl Pham
 * @author Christina Morris
 * @author Mathew Glodack
 * @author Jenna Cryan
 * @author Josephine Vo
 * @author Abhijit Kulkarni
 * @author Angela Yung
 * @author Ziheng Song
 * @author Yuhao Zhang
 * @version for CS56, Winter 2018, UCSB
 */

public class FishAnimationEnvironment extends JFrame implements Serializable {

	static {
		System.setProperty("java.awt.headless", "true");
	}
	private Thread animate;
	private DrawingPanel fishPanel = new DrawingPanel();
	private JFrame animation = new JFrame();
	private ScoreManager highscore = new ScoreManager();
	private int maxX = 1366, maxY = 768; // Default height and width of the game
											// at start
	private int posX = maxX / 2, posY = maxY / 2; // used to position the shark
													// at the origin
	private int maxWidth = 100; // max width of the fish
	private int boatX = maxX;// hold the position of the boat
	private int maxD = 10; // holds the maximum diameter of the bubbles

	private int health;
	private int maxHealth;
	private int eaten = 0; // number of fish eaten
	private int numFish = 75; // number of fish in environment
	private int numBubbles = 10 + (int) (Math.random() * 20); // creates a
																// random amount
																// of bubbles
	private int numJellyFish; // holds the number of Jellyfish to be created
	private int numBonus;
	private int timer, timerload = 0;
	private long time1 = System.nanoTime() / 1000000000; // Used to get the
															// start time of the
															// game
	private long time2, pausestart, pausetime = 0; // time that the pause button
													// was pressed for
	private int delay = 20; // The pause button delay
	private boolean stop = false; // used to know when to pause the animation
									// and when not to
	private boolean load = false; // used to determine whether or not the game
									// loads from the serialized form

	private boolean character_type; // determines character, (shark, kwhale)

	private long lastPress = 0;
	private int choice = -1;
	private String message = "";
	private boolean gameover = false;
	private int dx = 0, dy = 0;
	// create ArrayLists for fish, bubbles, and jellyfish.
	private ArrayList<Fish> fishArray = new ArrayList<Fish>();
	private ArrayList<Bubbles> bubblesArray = new ArrayList<Bubbles>();
	private ArrayList<JellyFish> jellyfish = new ArrayList<JellyFish>();

	// ArrayList of bonus snack sprites
    private ArrayList<Bonus> bonuses = new ArrayList<>();
    private List<String> bonusSpriteFileNames = Arrays.asList("yp_beer.png", "yp_moonpie.png", "yp_pizzaslice.png",
            "yp_strawberry.png", "yp_turkey.png", "yp_icecream.png");
    private ArrayList<URL> bonusURLList = new ArrayList<>();
    private ArrayList<Image> bonusImageList = new ArrayList<>();
    private Image bonusImage;

	// ArrayList of various seaweed and rock sprites
    private List<String> seaFloorSpriteFileNames = Arrays.asList("fishTile_014.png", "fishTile_015.png", "fishTile_016.png",
            "fishTile_017.png", "fishTile_032.png", "fishTile_033.png", "fishTile_034.png","fishTile_035.png",
            "fishTile_050.png", "fishTile_051.png", "fishTile_052.png", "fishTile_053.png", "fishTile_084.png",
            "fishTile_085.png");
    private ArrayList<URL> seaFloorURLList = new ArrayList<>();
    private ArrayList<Image> seaFloorImageList = new ArrayList<>();
    private Image seaFloorObjectImage;

    // ArrayList of various fish sprites
    private List<String> fishSpriteFileNames = Arrays.asList("fishTile_073.png", "fishTile_075.png", "fishTile_077.png",
            "fishTile_079.png", "fishTile_081.png");
	private ArrayList<URL> fishURLList = new ArrayList<>();
	private ArrayList<Image> fishImageList = new ArrayList<>();
    private Image fishImage;



    // Images in the game
	//difficulty1
	private URL reefURL1 = getClass().getResource("/resources/temp_reef.png");
	private Image reef1 = new ImageIcon(reefURL1).getImage();
	//difficulty2
	//private URL reefURL2 = getClass().getResource("/resources/CoralReef2.jpg");
	//private Image reef2 = new ImageIcon(reefURL2).getImage();
    private Image reef2 = reef1;
	//difficulty3
	//private URL reefURL3 = getClass().getResource("/resources/CoralReef3.jpg");
	//private Image reef3 = new ImageIcon(reefURL3).getImage();
    private Image reef3 = reef1;
	//difficulty4
	//private URL reefURL4 = getClass().getResource("/resources/CoralReef4.jpg");
	//private Image reef4 = new ImageIcon(reefURL4).getImage();
    private Image reef4 = reef1;

	// miscellaneous
	private URL boatURL = getClass().getResource("/resources/cartoon-boat.png");
	private Image boat = new ImageIcon(boatURL).getImage();

	// whale left and right images
    private URL whale_right_URL = getClass().getResource("/resources/whale_right.png");
    private Image whale_right_image = new ImageIcon(whale_right_URL).getImage();
    private URL whale_left_URL = getClass().getResource("/resources/whale_left.png");
    private Image whale_left_image = new ImageIcon(whale_left_URL).getImage();

    // shark left and right images
    private URL shark_right_URL = getClass().getResource("/resources/shark_right.png");
    private Image shark_right_image = new ImageIcon(shark_right_URL).getImage();
    private URL shark_left_URL = getClass().getResource("/resources/shark_left.png");
    private Image shark_left_image = new ImageIcon(shark_left_URL).getImage();

	private Toolkit toolkit = Toolkit.getDefaultToolkit();
	private URL cursorURL = getClass().getResource("/resources/cursor.png");
	private Image cursorImage = new ImageIcon(cursorURL).getImage();
			//toolkit.getImage("resources/left_ptr.png");
	private Cursor c = toolkit.createCustomCursor(cursorImage , new Point(fishPanel.getX(),
           	fishPanel.getY()), "img");

    // boolean for keeping track of direction fish is facing
    private boolean facingRight = true;

	private int difficulty;
	
	private String playerName = "CS56";

	/**
	 * Method gameFinished occurs once the player wins or loses. Upon ending the
	 * game, the player now has the option of restarting the game or quitting.
	 * 
	 * @param won
	 *            true if the player won, false if the player lost
	 */
	
	private void gameFinished(boolean won) {
		// change the BGM if the game is finished
		SoundEffect.BGM.stop();
		SoundEffect.FINISH.play();
		Character c = character_type ? Character.SHARK : Character.WHALE;
		
		if (won) {
			message = "Congratulations!\nYou have reached 50 points and won the game!\nPlease enter your name:";
			playerName = JOptionPane.showInputDialog(fishPanel, message, "You win!", JOptionPane.INFORMATION_MESSAGE);
			highscore.addScore(timer, eaten, difficulty, playerName, c);
			message += highscore.getHighScoreString(difficulty);
		} else {
			message = "Sorry, you lose. You got " + eaten + " points!";
		}
		message += "\n\tPlay again?";

		Thread t = new Thread(new Runnable() {
			public void run() {
				choice = JOptionPane.showOptionDialog(fishPanel, new JTextArea(message), "Gameover!",
						JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
				if (choice == JOptionPane.YES_OPTION) {
					Menu game = new Menu();
					SoundEffect.FINISH.stop();
					SoundEffect.BGM.play();
					game.setDifficulty();
					animation.setVisible(false);
				} else {
					gameover = true;
					System.exit(0);
				}
			}
		});
		t.start();

	}

	/**
	 * Method createRandomFish
	 * 
	 * @param x
	 *            for the x position
	 * @param y
	 *            for the y position
	 * @param maxWidth
	 *            for the width of fish
	 */
	private Fish createRandomFish(int x, int y, int maxWidth) {
		int randomX = (int) (Math.random() * x);
		int randomY = (int) (Math.random() * y);
		int randomWidth = 10 + (int) (Math.random() * (maxWidth - 10));
		return new Fish(randomX, randomY, randomWidth, randomWidth / 5);
	}

	/**
	 * Method createsBubbles
	 * 
	 * @param x
	 *            for the x position
	 * @param y
	 *            for the y position
	 * @param diameter
	 *            for the diameter of the bubble
	 */
	private Bubbles createBubbles(int x, int y, int diameter) {
		int randomX = (int) (Math.random() * x);
		int randomY = (int) (Math.random() * y);
		int randomD = (int) (Math.random() * diameter);
		return new Bubbles(randomX, randomY, randomD);
	}

	/**
	 * Constructor for FishAnimationEnvironment. Creates a JFrame and a Jpanel
	 * that is placed inside of it. Animation is done on the JPanel
	 */
	/*
	 * public FishAnimationEnvironment() { for(int i = 0; i < numFish; i++){
	 * addNewFish(createRandomFish(maxX, maxY, maxWidth)); } }
	 */

	/**
	 * Custom Constructor for FishAnimationEnvironment. Creates a JFrame and a
	 * Jpanel that is placed inside of it. Animation is done on the JPanel. This
	 * Constructor takes in input from the Menu GUI for the difficulty and
	 * creates Jellyfish based on the selected difficulty.
	 */
	public FishAnimationEnvironment(boolean character_type, int difficulty, boolean l) {
		//change characters' healths
		if (character_type){
			maxHealth = 50;
			health = 50;
		}
		else{
			maxHealth = 100;
			health = 100;
		}
			
		this.difficulty = difficulty;
		this.character_type = character_type;
		numJellyFish = difficulty;
		numBonus = 14 - difficulty;
		load = l;
		if (load) {
			// deserialize the score, fish, shark, boat, and jellyfish
			try {
				FileInputStream fileStream = new FileInputStream("saved.ser");
				ObjectInputStream os = new ObjectInputStream(fileStream);
				this.character_type = os.readBoolean();
				eaten = os.readInt();
				numJellyFish = os.readInt();
				numBonus = os.readInt();
				timerload = os.readInt();
				posX = os.readInt();
				posY = os.readInt();
				boatX = os.readInt();
				this.difficulty = os.readInt();
				health = os.readInt();

				for (int k = 0; k < numFish; k++) {
					Fish f = new Fish(os.readDouble(), os.readDouble(), os.readDouble(), os.readDouble());
					fishArray.add(f);
				}
				for (int i = 0; i < numJellyFish; i++) {
					int x = os.readInt();
					int y = os.readInt();
					double speed = os.readDouble();
					JellyFish j = new JellyFish(x, y, speed);
					jellyfish.add(j);
				}
				for (int i = 0; i < numBonus; i++) {
					int x = os.readInt();
					int y = os.readInt();
					Bonus p = new Bonus(x, y, true);
					bonuses.add(p);
				}
				os.close();
			} catch (Exception ex) {
				ex.printStackTrace();
				load = false;
				numJellyFish = 3;
			}
		}

		// Adds the fish into the Array only if the load button was not selected
		if (!load) {
			for (int i = 0; i < numFish; i++) {
				fishArray.add((createRandomFish(maxX, maxY, maxWidth)));
			}
		}

		// Adds the random amount of Bubbles into the ArrayList
		for (int i = 0; i < numBubbles; i++) {
			bubblesArray.add((createBubbles(maxX, maxY, maxD)));
		}

		// Adds the Jellyfish into the game
		if (!load) {
			for (int i = 0; i < numJellyFish; i++) {
				JellyFish j = new JellyFish((int) (Math.random() * 12345) % maxX, maxY,
						(Math.random() * 123) % 50 + 15);
				jellyfish.add(j);
			}
		}

		if (!load) {
			for (int i = 0; i < numBonus; i++) {
				Bonus p = new Bonus((int) (Math.random() * 12345) % maxX, (int) (Math.random() * 12345) % maxY,
						true);
				bonuses.add(p);
			}
		}

		animation.getContentPane().add(BorderLayout.CENTER, fishPanel);
		animate = new Animate();
		animate.start();
		animation.setDefaultCloseOperation(EXIT_ON_CLOSE);
		animation.setSize(maxX, maxY);
		animation.setCursor(c);
		animation.setVisible(true);
		GameMenu game = new GameMenu();
		game.makemenu();

		// Generate random bonuses
        int randomBonuses;
        for (int i = 0; i < bonusSpriteFileNames.size(); i++) {
            randomBonuses = (int)(Math.random() * bonusSpriteFileNames.size());
            bonusURLList.add(getClass().getResource("/resources/"
                    + bonusSpriteFileNames.get(randomBonuses)));
            bonusImage = new ImageIcon(bonusURLList.get(i)).getImage();
            bonusImageList.add(bonusImage);
        }

		// Generate random sea floor objects
        int randomSeaFloor;
        for (int i = 0; i < seaFloorSpriteFileNames.size(); i++) {
            randomSeaFloor = (int)(Math.random() * seaFloorSpriteFileNames.size());
            seaFloorURLList.add(getClass().getResource("/resources/fish_sprites/"
                    + seaFloorSpriteFileNames.get(randomSeaFloor)));
            seaFloorObjectImage = new ImageIcon(seaFloorURLList.get(i)).getImage();
            seaFloorImageList.add(seaFloorObjectImage);
        }

        // Generate random fish
        int randomFish;
        for (int i = 0; i < fishSpriteFileNames.size(); i++) {
            randomFish = (int)(Math.random() * fishSpriteFileNames.size());
            fishURLList.add(getClass().getResource("/resources/fish_sprites/"
                    + fishSpriteFileNames.get(randomFish)));
            fishImage = new ImageIcon(fishURLList.get(i)).getImage();
            fishImageList.add(fishImage);
        }
	}// end constructor

	/**
	 * Innerclass for a custom Jpanel for animation. Draws out the background
	 * and the in-game components including a timer, the score, the shark, the
	 * fish, the jellyfish, the seaweed, the boat, and the bubbles.
	 */

	class DrawingPanel extends JPanel {

		public void paintComponent(Graphics g) {
			// fishPanel.requestFocus(); //needed to be called each Panel
			requestFocus();
			// Sets background color and adds background image
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLUE);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			super.paintComponent(g); // replace current painting
			
			//set different backgorund here
			//easy
			if (difficulty == 3) {
				g.drawImage(reef1, 0, 0, this);
			}//medium
			else if (difficulty == 7) {
				g.drawImage(reef2, 0, 0, this);
			}
			else if (difficulty == 14)//hard
			{
				g.drawImage(reef3, 0, 0, this);
			}
			else if (difficulty == 30)//crazy
			{
				g.drawImage(reef4, 0, 0, this);
			}

			// Draws the seaweed at the specified points
			for (int i = 0; i < this.getWidth(); i += 64) {
                g.drawImage(seaFloorImageList.get(i%seaFloorImageList.size()), i, this.getHeight() - 64, this);
			}

			// Draws the fish based off the fish info array
			for (int i = 0; i < fishArray.size(); i++) {
				g2.drawImage(fishImageList.get(i%fishImageList.size()), (int)fishArray.get(i).getXPos(),
                        (int)fishArray.get(i).getYPos(), this);
			}

			// Draws the image of the boat and also animates it
			g.drawImage(boat, boatX, -135, this);
			if (!stop) {
				boatX -= 10;
				if (boatX <= -250) {
					boatX = this.getWidth();
				}
				if ((boatX > posX - 200 && boatX + 120 < posX + 40) && (60 > posY - 60 && 0 < posY + 25)) {
					health = 0;
				}
			}

			// Draws the image of the Shark
			if (posX < 0)
				posX = maxX;
			else if (posX > maxX)
				posX = 30;
			if (posY < 30)
				posY = 30;
			else if (posY > maxY - 100)
				posY = maxY - 100;
			Shark s = new Shark(posX, posY);
			int newXPos = (int) s.getXPos() - 160;
			int newYPos = (int) s.getYPos() - 130;

			if (character_type){
			    if (facingRight)
				    g2.drawImage(shark_right_image, newXPos, newYPos, this);
                else
                    g2.drawImage(shark_left_image, newXPos, newYPos, this);
			}
			else{
                if (facingRight)
				    g2.drawImage(whale_right_image, newXPos, newYPos, this);
                else
                    g2.drawImage(whale_left_image, newXPos, newYPos, this);
			}

			// Draws the bubbles with the blue gradient
			Color b = new Color(127, 255, 212);
			for (int i = 0; i < bubblesArray.size(); i++) {
				double yD = bubblesArray.get(i).getYPos();
				int yI = (int) yD;
				double xD = bubblesArray.get(i).getXPos();
				int xI = (int) xD;
				int BubblesY = yI - bubblesArray.get(i).getDiameter() / 2;
				int BubblesY2 = yI + bubblesArray.get(i).getDiameter() / 2;
				int BubblesX = xI - bubblesArray.get(i).getDiameter() / 2;
				int BubblesX2 = xI + bubblesArray.get(i).getDiameter() / 2;

				// Creates a gradient color for the Bubble
				GradientPaint gradient = new GradientPaint(BubblesX, BubblesY, Color.BLUE, BubblesX2, BubblesY2, b);
				g2.setPaint(gradient);

				// Draws a Bubbles object onto the screen
				int xb = (int) bubblesArray.get(i).getXPos();
				int yb = (int) bubblesArray.get(i).getYPos();
				int db = (int) bubblesArray.get(i).getDiameter();
				g2.fillOval(xb, yb, db, db);
			}

			// Sets the Color to PINK for the jellyfish
			g2.setColor(Color.PINK);
			for (int i = 0; i < jellyfish.size(); i++) {
				int jNewXPos = (int) jellyfish.get(i).getXPos();
				int jNewYPos = (int) jellyfish.get(i).getYPos();
				if (jellyfish.get(i).checkJellyFish()) {
					// Draws the Body of the JellyFish
					g2.fillArc(jNewXPos, jNewYPos, 50, 40, 0, 180);
					g2.setColor(Color.BLACK);
					g2.drawArc(jNewXPos, jNewYPos, 50, 40, 0, 180);

					// Draws the Tentacles of the JellyFish
                    g2.setColor(Color.PINK);
					for (int j = (int) jellyfish.get(i).getXPos() + 5; j < jellyfish.get(i).getXPos() + 50; j += 5) {
						g2.drawLine(j, jNewYPos + 75, j, jNewYPos + 10);
					}
				} else {
					// Draws the Body of the JellyFish
					g2.fillArc(jNewXPos + 5, jNewYPos, 40, 50, 0, 180);
                    g2.setColor(Color.BLACK);
                    g2.drawArc(jNewXPos + 5, jNewYPos, 40, 50, 0, 180);

					// Draws the Tentacles of the JellyFish
                    g2.setColor(Color.PINK);
					for (int j = (int) jellyfish.get(i).getXPos() + 10; j < jellyfish.get(i).getXPos() + 45; j += 5) {
						g2.drawLine(j, jNewYPos + 95, j, jNewYPos + 10);
					}
				}
			}

            //fishImageList.add(fishImage);
			for (int i = 0; i < bonuses.size(); i++) {
                g2.drawImage(bonusImageList.get(i%bonusImageList.size()), (int) bonuses.get(i).getXPos(),
                        (int)bonuses.get(i).getYPos(), this);
			}
			
			g2.setStroke(new BasicStroke(2));


			// displays the score bar
			g.setFont(new Font("Corsiva Hebrew", Font.PLAIN, 20));
			g.setColor(Color.GREEN);
			g.drawRect(20, 70, 350, 20);
			g.fillRect(20, 70, (int)(350 * eaten/50), 20);
			
			// displays the number of points
			g.setFont(new Font("Corsiva Hebrew", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			String str1 = "Points: " + eaten + "/50";
			g.drawString(str1, 135, 87);


			// display the health bar
			
			g.setFont(new Font("Corsiva Hebrew", Font.PLAIN, 20));
			g.setColor(Color.RED);
			g.drawRect(20, 95, 350, 20);
			g.fillRect(20, 95, (int)(350 * health/maxHealth), 20);

			// display the points of health inside the health bar
			g.setFont(new Font("Corsiva Hebrew", Font.PLAIN, 20));
			g.setColor(Color.WHITE);
			String maxHealthString = (maxHealth == 50) ? "/50" : "/100";
			String heal = "Health: " + health + maxHealthString;
			g.drawString(heal, 135, 112);
			
			// displays the current elapsed time of the game in seconds
			g.setFont(new Font("Corsiva Hebrew", Font.PLAIN, 30));
			g.setColor(Color.RED);
			String str2 = "Seconds Elapsed: " + timer;
			g.drawString(str2, 20, 60);

		}
	} // end DrawingPanel

	/**
	 * Class that animates the game to get the fish and bubbles to move.
	 */
	class Animate extends Thread {
		public void run() {

			// Starts the Action listener to listen for mouse events in the
			// panel
			MouseHandler handler = new MouseHandler();
			fishPanel.addMouseListener(handler);
			fishPanel.addMouseMotionListener(handler);

			fishPanel.setCursor (c);

			// Starts the Key Listener to listen for key events in the panel
			KeyHandler keyH = new KeyHandler();
			fishPanel.addKeyListener(keyH);
			// fishPanel.requestFocus();

			try {
				while (true) {
					display(delay, fishPanel);
				} // end while loop
			} catch (Exception ex) {
				if (!(ex instanceof InterruptedException)) { // Unexpected
																// exception
																// occurred.
					System.out.println(ex);
					System.exit(1); // terminate program
				} // end if
			} // end catch
		} // end run

		/**
		 * Class that holds the information for each separate fish in order to
		 * animate the fish and know its whereabouts on the screen.
		 */
		class FishInfo {
			private double x, y, width, height;

			FishInfo(double x, double y, double width, double height) {
				this.x = x;
				this.y = y;
				this.width = width;
				this.height = height;
			}

			public double getXPos() {
				return x;
			}

			public double getYPos() {
				return y;
			}

			public double getWidth() {
				return width;
			}

			public double getHeight() {
				return height;
			}
		}

		/**
		 * Creates each frame and also checks if the fish have been eaten.
		 * Removes fish and creates a new fish if it has been eaten.
		 **/
		public void display(int delay, DrawingPanel fishPanel)

				throws InterruptedException {
			// uses the stop boolean variable to pause
			if (!stop) {
				ArrayList<FishInfo> info = new ArrayList<FishInfo>();

				for (int i = 0; i < fishArray.size(); i++) {

					// get x position, y position, width, and height of fish
					int xf = (int) fishArray.get(i).getXPos();
					int yf = (int) fishArray.get(i).getYPos();
					int wf = (int) fishArray.get(i).getWidth();
					int hf = (int) fishArray.get(i).getHeight();

					/*
					 * check if each fish is at sharks mouth and remove from
					 * screen and increment eaten if true
					 */
					if (character_type) {
                        if (facingRight) {
                            if ((xf > posX - 100 && xf < posX - 50) && (yf > posY - 65 && yf < posY)) {
                                info.add(new FishInfo(fishPanel.getWidth(), Math.random() * maxY, wf, hf));
                                if (health < maxHealth)
                                        health += 2;
                                eaten++;
                                SoundEffect.FISH.playEffects();
                            } else {
                                info.add(new FishInfo(xf, yf, wf, hf));
                            }
                        } else {
                            if ((xf > posX - 200 && xf < posX - 150) && (yf > posY - 65 && yf < posY)) {
                                info.add(new FishInfo(fishPanel.getWidth(), Math.random() * maxY, wf, hf));
                                if (health < maxHealth)
                                        health += 2;
                                eaten++;
                                SoundEffect.FISH.playEffects();
                            } else {
                                info.add(new FishInfo(xf, yf, wf, hf));
                            }
                        }
                    }
                    else {
                        if (facingRight) {
                            if ((xf > posX - 100 && xf < posX - 50) && (yf > posY - 100 && yf < posY - 30)) {
                                info.add(new FishInfo(fishPanel.getWidth(), Math.random() * maxY, wf, hf));
                                if (health < maxHealth)
                                        health += 1;
                                eaten++;
                                SoundEffect.FISH.playEffects();
                            } else {
                                info.add(new FishInfo(xf, yf, wf, hf));
                            }
                        } else {
                            if ((xf > posX - 150 && xf < posX - 100) && (yf > posY - 100 && yf < posY - 30)) {
                                info.add(new FishInfo(fishPanel.getWidth(), Math.random() * maxY, wf, hf));
                                if (health < maxHealth)
                                        health += 1;
                                eaten++;
                                SoundEffect.FISH.playEffects();
                            } else {
                                info.add(new FishInfo(xf, yf, wf, hf));
                            }
                        }
                    }
				}

				for (int i = 0; i < jellyfish.size(); i++) {
					// get x and y positions of jellyfish
					int xj = (int) jellyfish.get(i).getXPos();
					int yj = (int) jellyfish.get(i).getYPos();

					// check if shark is touching a jellyfish and delete
					// jellyfish and penalize if true
					if ((xj - 20 > posX - 200 && xj + 20 < posX + 40) && (yj + 60 > posY - 60 && yj < posY + 25)) {
						// prevent score below 0
					    if (eaten - 5 >= 0)
							eaten -= 5;
					    else
					        eaten = 0;
						health -= 10;
						SoundEffect.JELLYFISH.playEffects();
						// reset jellyfish position
						jellyfish.get(i).setX(((int) ((Math.random() * 12345) % fishPanel.getWidth())));
						jellyfish.get(i).setY((int) fishPanel.getHeight());
					}
				}

				for (int i = 0; i < bonuses.size(); i++) {
					int xp = (int) bonuses.get(i).getXPos();
					int yp = (int) bonuses.get(i).getYPos();

					if ((xp - 20 > posX - 200 && xp + 20 < posX + 40) && (yp + 60 > posY - 60 && yp < posY + 25)) {
						eaten += 5;
						// reset bonuses position
						int newx = ((int) ((Math.random() * 12345) % fishPanel.getWidth()));
						int newy = (int) ((Math.random() * 12345) % maxY);
						Bonus newp = new Bonus(newx, newy, true);
						bonuses.set(i, newp);
					}
				}
				fishArray.clear();

				// Displays the Bubbles of the bubblesArray ArrayList
				for (int i = 0; i < bubblesArray.size(); i++) {
					if (bubblesArray.get(i).getYPos() <= -20) {
						bubblesArray.get(i).setX((int) (Math.random() * fishPanel.getWidth()));
						bubblesArray.get(i).setY(fishPanel.getHeight());
					} else
						// moves the bubbles 2 pixels
						bubblesArray.get(i).move(2);

				}
				// Displays the jellyfish ArrayList
				for (int i = 0; i < jellyfish.size(); i++) {
					if (jellyfish.get(i).getYPos() <= -100) {
						jellyfish.get(i).setX(((int) ((Math.random() * 12345) % fishPanel.getWidth())));
						jellyfish.get(i).setY((int) fishPanel.getHeight());
					}

					// If the jellyfish is true it moves 10 pixels and setMove
					// to false
					if (jellyfish.get(i).checkJellyFish() && jellyfish.get(i).getCount() % 20 == 0) {
						jellyfish.get(i).move(jellyfish.get(i).getSpeed());
						jellyfish.get(i).setMove(false);
					}

					// Sets move from false to true
					if (jellyfish.get(i).getCount() % 20 == 10) {
						jellyfish.get(i).setMove(true);
					}
					jellyfish.get(i).setCount();
				}

				for (int i = 0; i < bonuses.size(); i++) {
					if (bonuses.get(i).moveLeft()) {
						Bonus newp = new Bonus(bonuses.get(i).getXPos(), bonuses.get(i).getYPos() - 5, false);
						bonuses.set(i, newp);
					} else {
						Bonus newp = new Bonus(bonuses.get(i).getXPos(), bonuses.get(i).getYPos() + 5, true);
						bonuses.set(i, newp);
					}
				}

				// Speed of the fish moving across the screen
				double currentSpeed = numFish % 6;
				for (int i = 0; i < info.size(); i++) {
					if ((currentSpeed - 1) < 2) {
						currentSpeed = (numFish % 10) + 1;
					} else {
						currentSpeed--;
					}
					double newX = info.get(i).getXPos() - currentSpeed;
					int wp = fishPanel.getWidth();
					int hp = fishPanel.getHeight();

					if (newX < -50) {
						fishArray.add((new Fish(wp, ((Math.random() * hp) % hp), info.get(i).getWidth(),
								info.get(i).getHeight())));
					} else {
						fishArray.add((new Fish(newX, info.get(i).getYPos(), info.get(i).getWidth(),
								info.get(i).getHeight())));
					}
				}

				/*
				 * Some math to calculate the real time minus the amount of time
				 * the game was paused in order to keep the game at the correct
				 * time always
				 */
				if (!stop) {
					time2 = System.nanoTime() / 1000000000 - pausetime;
					timer = (int) (time2 - time1) + timerload;
				}

				fishPanel.repaint();
				if (eaten >= 50 && health > 0) {
					stop = true;
					gameFinished(true);
				} else if (health <= 0) {
					stop = true;
					gameFinished(false);
				}

				if (Thread.currentThread().interrupted()) {
					throw (new InterruptedException());
				}

				Thread.currentThread().sleep(delay);
			} // end display method
		}
	} // end inner class named Animate

	/**
	 * Class to handle key events. Some methods are present but not defined
	 * because every method of the implemented must be present to avoid compiler
	 * error. This lets the shark move using arrow keys.
	 */
	public class KeyHandler implements KeyListener {
		public void keyPressed(KeyEvent e) {

			if (!stop && (System.currentTimeMillis() - lastPress > 2)) {
			
				int press = e.getKeyCode();
				if (press == KeyEvent.VK_LEFT) {
					dx = -10;
					facingRight = false;
				} else if (press == KeyEvent.VK_RIGHT) {
					dx = 10;
					facingRight = true;
				} else if (press == KeyEvent.VK_DOWN) {
						dy = 10;
				} else if (press == KeyEvent.VK_UP) {
						dy = -10;
				} else if (press == KeyEvent.VK_MINUS) {
					 SoundEffect.BGM.reduceVolume();
					 SoundEffect.FISH.reduceVolume();
					 SoundEffect.JELLYFISH.reduceVolume();
					 SoundEffect.FINISH.reduceVolume();
				} else if (press == KeyEvent.VK_EQUALS) {
					 SoundEffect.BGM.increaseVolume();
					 SoundEffect.FISH.increaseVolume();
					 SoundEffect.JELLYFISH.increaseVolume();
					 SoundEffect.FINISH.increaseVolume();
				}
				posX += dx;
				posY += dy;
				// switch(press){
				// case KeyEvent.VK_LEFT:
				// posX-=dx;
				// break;
				// case KeyEvent.VK_RIGHT:
				// posX+=dx;
				// break;
				// case KeyEvent.VK_UP:
				// posY-=dy;
				// break;
				// case KeyEvent.VK_DOWN:
				// posY+=dy;
				// break;
				// }
				lastPress = System.currentTimeMillis();
			
			}
		}

		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
			if (stop == false) {
				int press = e.getKeyCode();
				if (press == KeyEvent.VK_RIGHT || press == KeyEvent.VK_LEFT)
					dx = 0;
				else if (press == KeyEvent.VK_DOWN || press == KeyEvent.VK_UP)
					dy = 0;
				repaint();
			}
		}
	}

	/**
	 * Class to handle mouse events. Some methods are present but not defined
	 * because every method of the implemented class must be present to avoid
	 * compiler error
     *
     * implements MouseListener, MouseMotionListener
     *
	 */
	public class MouseHandler extends MouseInputAdapter {
        int previousX;

		public void mousePressed(MouseEvent e) {
			// present to avoid compiler error
            previousX = e.getX();
		}

		public void mouseEntered(MouseEvent e) {
			// present to avoid compiler error
		}

		public void mouseExited(MouseEvent e) {
			// present to avoid compiler error
		}

		public void mouseReleased(MouseEvent e) {
			fishPanel.requestFocus();
		}

		public void mouseMoved(MouseEvent e) {
			// present to avoid compiler error
		}

		public void mouseDragged(MouseEvent e) {
			if (stop == false) {
				posX = e.getX();
				if (posX < previousX)
				    facingRight = false;
				else
					facingRight = true;
				posY = e.getY();
			}
			previousX = posX;
		}
	}

	/**
	 * Class that creates an in game menu in order to Pause, resume, and save
	 * the game.
	 */
	class GameMenu implements ActionListener {
		private JButton Pause;
		private URL pauseURL = getClass().getResource("/resources/PauseButton.jpg");
		private URL playURL = getClass().getResource("/resources/PlayButton.png");
		private ImageIcon pause = new ImageIcon(pauseURL);
		private ImageIcon play = new ImageIcon(playURL);
		private JEditorPane resumeError = new JEditorPane();

		private JButton Save = new JButton("Save & Exit");
		private JButton Exit = new JButton("Exit");
		private JPanel allTheButtons;
		

		public void main(String[] args) {
			GameMenu menu = new GameMenu();
			menu.makemenu();
		}

		/**
		 * Main GUI interface for the in-Game GUI. This menu allows for user to
		 * select pause, resume, or save&exit.
		 */
		public void makemenu() {

			Pause = new JButton(pause);
			Pause.addActionListener(this);
			Save.addActionListener(this);
			Exit.addActionListener(this);

			allTheButtons = new JPanel(new GridLayout(1, 3));
			animation.getContentPane().add(BorderLayout.SOUTH, allTheButtons);
			allTheButtons.add(Pause);
			allTheButtons.add(Save);
			allTheButtons.add(Exit);
			animation.setVisible(true);
		}
		

		/**
		 * Method to allow for actions that are performed on the button to be
		 * taken in and used as the source for an event to take place
		 */
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == Pause) {
				if (stop == false) {
					stop = true;
					SoundEffect.BGM.pause();
					allTheButtons.remove(Pause);
					allTheButtons.remove(Save);
					allTheButtons.remove(Exit);
					Pause = new JButton(play);
					Pause.addActionListener(this);
					allTheButtons.add(Pause);
					allTheButtons.add(Save);
					allTheButtons.add(Exit);
					animation.setVisible(true);
					pausestart = System.nanoTime() / 1000000000;
				} else {
					stop = false;
					SoundEffect.BGM.play();
					allTheButtons.remove(Pause);
					allTheButtons.remove(Save);
					allTheButtons.remove(Exit);
					Pause = new JButton(pause);
					Pause.addActionListener(this);
					allTheButtons.add(Pause);
					allTheButtons.add(Save);
					allTheButtons.add(Exit);
					animation.setVisible(true);
					pausetime += (System.nanoTime() / 1000000000 - pausestart);
				}
			}

			/*
			 * serialize if save was clicked. Exits the game as well after the
			 * save. Saves all of the positions of all the different items that
			 * are on the screen including the shark, fish, jelly, and the boat
			 */
			if (event.getSource() == Save) {
				try {
					FileOutputStream fs = new FileOutputStream("saved.ser");
					ObjectOutputStream os = new ObjectOutputStream(fs);
					os.flush();
					os.writeBoolean(character_type);
					os.writeInt(eaten);
					os.writeInt(numJellyFish);
					os.writeInt(numBonus);
					os.writeInt(timer);
					os.writeInt(posX);
					os.writeInt(posY);
					os.writeInt(boatX);
					os.writeInt(difficulty);
					os.writeInt(health);
					for (int k = 0; k < numFish; k++) {
						os.writeDouble(fishArray.get(k).getXPos());
						os.writeDouble(fishArray.get(k).getYPos());
						os.writeDouble(fishArray.get(k).getWidth());
						os.writeDouble(fishArray.get(k).getHeight());
					}
					for (int i = 0; i < numJellyFish; i++) {
						int toWriteX = (int) jellyfish.get(i).getXPos();
						int toWriteY = (int) jellyfish.get(i).getYPos();
						os.writeInt(toWriteX);
						os.writeInt(toWriteY);
						os.writeDouble(jellyfish.get(i).getSpeed());
					}
					for (int i = 0; i < numBonus; i++) {
						int toWriteX = (int) bonuses.get(i).getXPos();
						int toWriteY = (int) bonuses.get(i).getYPos();
						os.writeInt(toWriteX);
						os.writeInt(toWriteY);
					}
					os.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				eaten = 0;
				numJellyFish = 0;
				System.exit(0);
			}
			// If exit is clicked exits the game
			if (event.getSource() == Exit) {
				System.exit(0);
			}
		}

	}

} // end class Animate
