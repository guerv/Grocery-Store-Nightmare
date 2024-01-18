/* ICS3U1-04 CPT: Grocery Store Nightmare
 * 
 * Natalia Guevara
 * 
 * Mr. Conway
 * 
 * -- DESCRIPTION --
 * "Grocery Store Nightmare" starts off with its title page, which stays on screen for 5 seconds before disappearing. This gives the player enough time to 
 * read the controls of the game labelled on the title page, where the W/A/S/D keys are used to control the player's projectiles, and the arrow keys are
 * used to move the player itself. 
 * After the title page is done being displayed, the player is placed inside of a room with 4 doors, leading up, down, left, and right. Each door leads to 
 * a different room, and while the map of this room is not available to the player, there only exists 10 rooms for the player to explore. Additionally, the
 * player cannot leave the bounds of the room, being limited to a select space.
 * From the original room - the upper door leads to room 2, which is locked and requires a key to open. If the player has the boss key, this room can be accessed.
 * 							4 walker enemies will appear, each with a health of 120. Once the player defeats every enemy in this room, the player will have beaten
 * 							the game. Winning the game will show a JOptionPane that displays to the player that they have won and will promptly quit.
 * From the original room - the door on the right leads to room 3, which has a randomly generated item that serves to benefit the player, although
 * its effects are unknown.
 * From the original room - the door on the left leads to room 4, which has 4 enemies that follow the player, seeking to deplete the player's hearts. 
 * 							The player must dodge around the enemies and aim their projectiles to hit each enemy until they disappear. There is no escape 
 * 							from the room, and the doors only appear once each enemy is killed. After each enemy is killed, the doors open and the player
 * 							is free to move to either the original room or room 5.
 * From room 4 - the door on the left leads to room 5, which also has another randomly generated item meant to help the player. This item will always be 
 * 				different from the other item generated. Room 5 also has 4 bandaids which, when touched with empty heart containers, will add an extra heart
 * From the original room - the lower door leads to room 6, which has a different type of enemy. This time, two blocks shoot projectiles forward, which 
 * 							interferes with the player's path to the lower door. If the player moves too slow, the enemy's projectiles will pile up and 
 * 							the player will have to time their movements to dodge the bullets. However, if the player immediately touches the button in the
 * 							lower right side of the room, the bullets will stop producing and the player can leave the room. 
 * From room 6 - the room below room 6 is room 7, which also has 4 walker enemies that the player has to kill. When all enemies disappear, the player can 
 * 				leave the room. 
 * From room 7 - the room on the right is room 8, which also has 4 walker enemies, with more health points, meaning it will take longer to kill them. 
 * 				Once each enemy is defeated, the player can leave the room. 
 * From room 7 - the room on the left is room 9. This room is a puzzle room, in which the player can only leave once they successfully answer the computer's
 * 				questions. The first question is simple, asking which direction they came from (ANS1 = right). The second question may require some trial and error,
 * 				but after some deduction, the player can say that the room with the boss key is on the left (ANS2 = left). The third question requires
 * 				solving using mathematics to prove the player's wisdom, the question being 2log5 + 2log2, which, when simplified, is 2 (ANS3 = 2). If
 * 				at least one of the questions are answered incorrectly, the player must answer every question again. Once the player answers each question
 * 				successfully, the computer congratulates the player and allows the player to leave the room.
 * From room 9 - the room on the left is room 10, which has the boss key. Once the player touches this key, the player can enter the boss room (room 2)
 * 
 * If the player loses all of their hearts, all timers will stop and for a brief period, the player will stay still before a JOptionPane appears saying
 * that the player has died. Afterwards, the program will quit. 
 * 
 * Each enemy can have a different skin. Every time the player runs the game, the enemies will have different skins randomly generated from the Enemy() class; 
 * 
 * Every time the player runs the game, the two items in room 3 and 5 will be different, thus allowing for a unique player experience each time. However,
 * since the item pool is small, the player will likely get repeat items throughout different playthroughs. 
 * 1st Item (ramen) -> +1 Heart container
 * 2nd Item (pebbles) -> Player can shoot projectiles faster, but player damage is reduced
 * 3rd Item (boulders) -> Player shoots projectiles slower, but player damage is greatly increased
 * 4th Item (Mixed Blood Bag) -> can either cause the player 2+ hearts containers or lose 1 heart container entirely 						
 * 5th Item (jagged rock) -> -2 heart containers but dramatically increases player damage
 * 6th Item (tougher skin) -> +1 heart container
 * 7th Item (sneakers) -> faster player speed
 * 8th Item (beta design) -> enemies move slower!
 * 
 * 
 * -Rules/Instructions- 
 * > player cannot move outside the boundaries of the walls
 * > if player loses all hearts, the player is dead
 * > touching an enemy or enemy projectile causes the player to lose a heart (heart containers stay)
 *
 * All assets were (regrettably) drawn digitally.
 * 
 * --DETAILS--
 * - 
 * 
 * 
 */



import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// Extend JPanel and implement ActionListener and KeyListener
@SuppressWarnings("serial")
public class MilkMain extends JPanel implements ActionListener, KeyListener
{
	
	// Field for random class
	private Random rnd; 
	
	// Fields for self-made classes
	private Projectile proj;
	private EnemyProjectile enProj;
	private Button butt; 
	
	// Fields for ArrayLists holding either multiple instances from a class or primitive/abstract datatype
	private ArrayList<Bandaid> bandaids;  // for healing

	//	for enemies+ enemy projectiles
	private ArrayList<Enemy> enemy;
	private ArrayList<Enemy> enemyShooter;
	private ArrayList<Enemy> enemyMask;
	private ArrayList <ImageIcon>enemyBulletsImg;
	private ArrayList<EnemyProjectile> enemyBulletsMask;
	
	private ArrayList<PlayerHealth> playerHeartsEMPTY, playerHeartsFULL; // for health system
	
	// 	for each player Bullet
	private ArrayList<ImageIcon>bullets; 
	private ArrayList<Projectile>bulletsMask;
	private ArrayList<Integer>dirBullets; 


	// set all other fields
	private Items[] roundItems;
	private ImageIcon playerImg, backgroundImgWIP, bgImg, computerImg, keyImg;
	private Image bgWIP2;
	private int playerX, playerY, spawnLeftX, spawnLeftY, spawnRightX, spawnRightY,spawnDownX, spawnDownY, spawnUpX, spawnUpY, computerX, computerY;
	private Timer playerMovement, roomCheck, bulletMovement, enemyBulletMovement, enemyTimer, invincibleTimer, deathTimer, enemyBulletsTimer, bulletInterval, healCheck, startTimer; 
	private boolean isUp, isDown, isRight, isLeft; 
	private Rectangle2D wallsMask, playerMask; 
	private Rectangle2D computerMask, keyMask; 
	private Rectangle2D doorLeft, doorUp, doorDown, doorRight;
	private int currentRoom;
	private int lookDir, bulletDir; 
	private ImageIcon[] playerImgs; 

	private ImageIcon[] buttonImgsOPEN, buttonImgsCLOSE; 
	private int bulletX, bulletY, enemyDirX, enemyDirY; 
	private int currentHeart, currentEHeartIndex;
	private int enemySpeed;
	private boolean isFired, canFire, enemyIsFired, hereOnce4, hereOnce6, hereOnce7, hereOnce8, hereOnce9, hereOnce2, isInvin, clearRoom; 
	private boolean isCollect1, isCollect2;
	private String answer1, answer2;
	private int answer3;
	private Boolean gotKey;
	private int keyX, keyY;
	
	private ImageIcon titleImg;
	private boolean isStart;

	private int playerDamage, playerSpeed;

	// Sets direction 
	public final int DOWN = 1, UP = 3, LEFT = 2, RIGHT = 4;

	// runs constructor
	public static void main(String[] args) 
	{
		new MilkMain(); // calling constructor
	}

	public MilkMain()
	{

		// initialize classes
		butt = new Button(); 
		enProj = new EnemyProjectile();  
		rnd = new Random(); 

		// initialize an array to store the random items for the current run of the game
		roundItems = new Items[2];

		for (int i = 0; i < roundItems.length; i++)
		{
			roundItems[i] = new Items();

		}

		// initializes the answers for the computer puzzle
		answer1 = "";
		answer2 = "";
		answer3 = -1;
		
		
		
		// sets all boolean values to false/true respectively 
		isStart = true;
		
		isFired = false;
		isInvin = false; 

		// checks if an item was collected
		isCollect1 = false;
		isCollect2 = false; 

		//	to see if room was cleared
		hereOnce2 = false;
		hereOnce4 = false;
		hereOnce6 = false;
		hereOnce7 = false;
		hereOnce9 = false;

		clearRoom = false; // additional clear room to clear up errors

		canFire = true;
		gotKey = false;
		
		isUp = false;
		isDown = false;
		isLeft = false;
		isRight = false; 

		enemyIsFired = false;
		
		
		// sets up button images (differs when pushed or not) - for room 6
		buttonImgsOPEN = new ImageIcon[2];
		buttonImgsCLOSE = new ImageIcon[2];


		// intialize all ArrayLists
		bandaids = new ArrayList<Bandaid>();

		for (int i = 0; i < 4; i++)
		{

			bandaids.add(new Bandaid());
			bandaids.get(i).setX(i);  // sets bandaid to a different x coordinate with each new bandaid
			bandaids.get(i).setMask(0);

		}


		bullets = new ArrayList<ImageIcon>(); 

		bulletsMask = new ArrayList<Projectile>(); 


		dirBullets = new ArrayList<Integer>();

		enemy = new ArrayList<Enemy>(); 
		enemyShooter = new ArrayList<Enemy>(); 

		enemyMask = new ArrayList<Enemy>(); 
		enemyBulletsMask = new ArrayList<EnemyProjectile>();
		enemyBulletsImg = new ArrayList<ImageIcon>();

		playerHeartsEMPTY = new ArrayList<PlayerHealth>();
		playerHeartsFULL = new ArrayList<PlayerHealth>();


		for (int i = 0; i < 4; i++)
		{
			playerHeartsEMPTY.add(new PlayerHealth()); 
			playerHeartsFULL.add(new PlayerHealth());

			playerHeartsEMPTY.get(i).setX(i); // sets heart container xPos
			playerHeartsFULL.get(i).setX(i); // sets full heart xPos

			currentEHeartIndex = i; // the greatest heart container currently

		}


		// enemy speed starts at 2
		enemySpeed = 2;
		
		// sets enemy directions
		enemyDirX = RIGHT;
		enemyDirY = DOWN;

		// initialize bullet direction
		bulletDir = 0; 

		// have 3 hearts means current red heart should be 3
		currentHeart = 3;

		// sets player damage at 10
		playerDamage = 10; 
		//sets player speed at 0
		playerSpeed = 0;
		//looks down
		lookDir = 1; 
		
		//sets current room at start
		currentRoom = 9;


	
		// sets each player image (changes to a different image depending on player direction)
		playerImgs = new ImageIcon[4];

		for (int i = 0; i < playerImgs.length;i++)
		{

			playerImgs[i] = new ImageIcon("playerImgs/playerMilk" + (i+1) + ".png");

		}



		// to help with mask dimensions
		playerImg = new ImageIcon("playerImgs/playerMilkD.png");

		// Sets background for main game
		backgroundImgWIP = new ImageIcon("gameBg.png");
		bgWIP2 = backgroundImgWIP.getImage().getScaledInstance(900, 700, Image.SCALE_SMOOTH);
		bgImg = new ImageIcon(bgWIP2);

		// sets player coordinates at middle of screen
		playerX = 300;
		playerY = 200; 
		
		proj = new Projectile(playerX, playerY, playerImgs[lookDir-1]); 


		//sets computer image
		computerImg = new ImageIcon("computer.png");

		// sets key image
		keyImg = new ImageIcon("bossKey.png");

		// sets title image
		titleImg = new ImageIcon("titlepage.png");

		// sets all timers
		startTimer = new Timer(5000,this); // start screen
		playerMovement = new Timer(50, this); // player movement
		roomCheck = new Timer (100, this); // checks what room player is in
		bulletMovement = new Timer(35,this); // to move player projectiles
		bulletInterval = new Timer(500,this); // controls speed at which projectile is launched
		enemyBulletMovement = new Timer(75,this); // controls movement of enemy projectiles
		enemyTimer = new Timer(50, this); // controls enemy speed
		invincibleTimer = new Timer(1000,this); // makes player invincible for one second after getting hit
		deathTimer = new Timer(1500, this); // after finishes, shows death sequence
		enemyBulletsTimer = new Timer(1500,this); // controls interval at which enemy bullets is shot
		healCheck = new Timer(250, this); // checks if touches a bandaid



		// Set properties of JPanel
		setLayout(null);
		
		// Add the listener for the keyboard
		addKeyListener(this);
		setFocusable(true);
		requestFocus();

		// add JFrame which adds JPanel
		JFrame frame = new JFrame(); 
		frame.setContentPane(this);
		frame.setSize(900, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GROCERY STORE NIGHTMARE");
		frame.setResizable(false);
		frame.setVisible(true);


		
		// get x and y coordinates so that masks and images are set at middle/ differs depending on frame size
		computerX = getWidth()/2 - computerImg.getIconWidth()/2;
		computerY = getHeight()/2 - computerImg.getIconHeight()/2;
		computerMask = new Rectangle2D.Double(computerX, computerY, computerImg.getIconWidth(), computerImg.getIconHeight());

		keyX = getWidth()/2 - keyImg.getIconWidth()/2;
		keyY = getHeight()/2 - keyImg.getIconHeight()/2;
		keyMask = new Rectangle2D.Double(keyX, keyY, keyImg.getIconWidth(), keyImg.getIconHeight());


		wallsMask = new Rectangle2D.Double(75,75, 750, 512);
		playerMask = new Rectangle2D.Double(playerX,playerY, playerImgs[lookDir-1].getIconWidth(), playerImgs[lookDir-1].getIconHeight());

		doorLeft = new Rectangle2D.Double(0, getHeight()/2 - playerImg.getIconHeight()/2, 85,80);
		doorRight = new Rectangle2D.Double(getWidth() - 85, getHeight()/2 - playerImg.getIconHeight()/2, 85,80);
		doorDown = new Rectangle2D.Double(getWidth()/2 - (85/2), getHeight() - 97, 85,90);
		doorUp = new Rectangle2D.Double(getWidth()/2 - (85/2), 0, 85,90);


		// when a door is touched, spawns player at a specific position
		spawnLeftX = 85;
		spawnLeftY = getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2;

		spawnRightX = 730;
		spawnRightY = getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2;

		spawnDownX = getWidth()/2 - playerImgs[lookDir-1].getIconWidth()/2;
		spawnDownY = 485;

		spawnUpX = getWidth()/2 - playerImgs[lookDir-1].getIconWidth()/2;
		spawnUpY = 100;


		// player projectile will spawn at player's coords
		bulletX = playerX;
		bulletY = playerY; 


		// Start the title screen
		startTimer.start();


	}

	public void actionPerformed(ActionEvent e) 
	{

		// runs after 5 seconds are done - starts game
		if (e.getSource() == startTimer)
		{
			isStart = false;
			startTimer.stop();
			playerMovement.start(); 
			roomCheck.start();
			bulletInterval.start(); 
		}

		//moves player
		if (e.getSource() == playerMovement)
		{

			if (isUp == true && playerY + playerImgs[lookDir-1].getIconHeight() >= 100) // moves if in boundaries and going up 
			{
				playerY -= (10 + playerSpeed); 
			}
			else if (isDown == true && playerY + playerImgs[lookDir-1].getIconHeight() <= 575)// moves if in boundaries and going down 
			{
				playerY += (10 + playerSpeed); 
			}
			else if (isLeft == true && playerX >= 75)// moves if in boundaries and going left 
			{
				playerX -= (10 + playerSpeed); 
			}
			else if (isRight == true && playerX + playerImgs[lookDir-1].getIconWidth() <= 825)// moves if in boundaries and going right 
			{
				playerX += (10 + playerSpeed); 
			}


			// changes mask with every movement
			playerMask = new Rectangle2D.Double(playerX,playerY, playerImgs[lookDir-1].getIconWidth(), playerImgs[lookDir-1].getIconHeight());



			if (currentRoom == 6)
			{

				if (clearRoom == false && hereOnce6 == false)
				{
					// once player touches button to stop enemy projectiles
					if (playerMask.intersects(butt.getMask()))
					{

						enemyBulletsTimer.stop(); //stops enemy projectiles
						clearRoom = true;	// clears room
						hereOnce6 = true; 

					}
				}
			}

			else if (currentRoom == 3 || currentRoom == 5) // where items are
			{
				for (int i = 0; i < roundItems.length; i++) // could be either the first or second item
				{
					if (playerMask.intersects(roundItems[i].getMask())) // checks if picked up item
					{


						if (roundItems[i].getIndex() == 0) // ramen (heart container +1)
						{

							playerHeartsEMPTY.add(new PlayerHealth());

							currentEHeartIndex++;

							playerHeartsEMPTY.get(currentEHeartIndex).setX(currentEHeartIndex);


						}

						else if (roundItems[i].getIndex() == 1) // pebbles (dmg-- speed of projectiles++)
						{

							playerDamage -= 7;

							bulletInterval.stop();
							bulletInterval = new Timer(50,this);
							bulletInterval.start(); 

						}

						else if (roundItems[i].getIndex() == 2) // boulder (dmg++ speed of projectiles--)
						{

							playerDamage += 25;

							bulletInterval.stop();
							bulletInterval = new Timer(1500,this);
							bulletInterval.start();

						}

						else if (roundItems[i].getIndex() == 3) // mixed blood bag (could be 2+/1- health container)
						{

							int compatibleBlood = rnd.nextInt(2); 

							if (compatibleBlood == 0) // if not compatible (deletes 1 heart)
							{

								playerHeartsEMPTY.remove(currentEHeartIndex);

								currentEHeartIndex--; 

							}
							else if (compatibleBlood == 1) // if compatible (adds 2 hearts)
							{

								for (int j = 0; j < 2; j++)
								{
									playerHeartsEMPTY.add(new PlayerHealth());

									currentEHeartIndex++; 

									playerHeartsEMPTY.get(currentEHeartIndex).setX(currentEHeartIndex);


								}



							}

							while (currentEHeartIndex < currentHeart) // if current heart (red heart) is greater than heart containers, remove red hearts until equal
							{

								playerHeartsFULL.remove(currentHeart);

								currentHeart--;

								if (currentHeart == 0) // if causes 0 hearts, kill the player
								{
									roomCheck.stop(); 
									bulletMovement.stop(); 
									playerMovement.stop(); 
									enemyBulletsTimer.stop(); 
									enemyBulletMovement.stop(); 

									deathTimer.start(); 								
								}


							}

						}

						else if (roundItems[i].getIndex() == 4) // pointy stone (-2 HP but ++DMG)
						{

							for (int j = 0; j < 2; j++)
							{	
								playerHeartsEMPTY.remove(currentEHeartIndex);
								currentEHeartIndex--; 
							}

							playerDamage += 50;

							while (currentEHeartIndex < currentHeart) // if current heart (red heart) is greater than heart containers, remove red hearts until equal
							{

								playerHeartsFULL.remove(currentHeart);

								currentHeart--;
								if (currentHeart == 0) // if causes 0 hearts, kill the player
								{
									roomCheck.stop(); 
									bulletMovement.stop(); 
									playerMovement.stop(); 
									enemyBulletsTimer.stop(); 
									enemyBulletMovement.stop(); 

									deathTimer.start(); 								
								}

							}

						}

						else if (roundItems[i].getIndex() == 5) // thick skin (+1 HP)
						{

							playerHeartsEMPTY.add(new PlayerHealth());

							currentEHeartIndex++; 

							playerHeartsEMPTY.get(currentEHeartIndex).setX(currentEHeartIndex);


						}

						else if (roundItems[i].getIndex() == 6) // sneakers (++PlayerSpeed)
						{

							playerSpeed = 6;

							playerMovement.stop();
							playerMovement = new Timer(40,this);
							playerMovement.start();

						}

						else if (roundItems[i].getIndex() == 7) // beta design (enemy moves slower)
						{
							enemySpeed = 1;
						}

						roundItems[i].setMask(); // sets mask to be untouchable after being touched

						// if intersected with either item 1/2; causes respective boolean to turn to true
						if (i == 0) 
						{
							isCollect1 = true;
						}
						else if (i == 1)
						{
							isCollect2 = true;
						}

					}


				}


			}

			// checking computer 
			else if (currentRoom == 9)
			{


				if (playerMask.intersects(computerMask)) // player touches computer
				{
					computerMask = new Rectangle2D.Double(0,0,0,0); // removes computer mask

					// stop player from moving in JOP
					isUp = false;
					isDown = false;
					isRight = false;
					isLeft = false;

					playerMovement.stop();

					// show intro/questions in JOP
					JOptionPane.showMessageDialog(null, "Hi!\nAnswer each of my very easy questions to escape!", "QuizOS", JOptionPane.PLAIN_MESSAGE);

					answer1 = JOptionPane.showInputDialog(null, "1. Where did you come from?\n(Possible Answers: left, right, down, up)", "QuizOS", JOptionPane.QUESTION_MESSAGE);
					answer2 = JOptionPane.showInputDialog(null, "2. Where is the key to the boss?\n(Possible Answers: left, right, down, up)", "QuizOS", JOptionPane.QUESTION_MESSAGE);

					// runs until an integer value is entered for answer3
					do {
						try
						{
							answer3 = Integer.parseInt(JOptionPane.showInputDialog(null, "3. Prove your wisdom! Solve this problem!\n2log5 + 2log2", "QuizOS", JOptionPane.QUESTION_MESSAGE));
						}
						catch(Exception r)
						{
							JOptionPane.showMessageDialog(null, "Please input a number response!", "QuizOS", JOptionPane.ERROR_MESSAGE);
						}
						
					} while (answer3 == -1);

					// checks if all answers are right
					if (answer1.equalsIgnoreCase("right") && answer2.equalsIgnoreCase("left") && answer3 == 2)
					{

						JOptionPane.showMessageDialog(null, "Amazing! You pass!", "QuizOS", JOptionPane.PLAIN_MESSAGE);

						hereOnce9 = true; //clears room
					}
					else // must try again (mask returns and player has to intersect computer)
					{
						JOptionPane.showMessageDialog(null, "Try again!", "QuizOS", JOptionPane.PLAIN_MESSAGE);
						computerMask = new Rectangle2D.Double(computerX, computerY, computerImg.getIconWidth(), computerImg.getIconHeight());


					}
					playerMovement.start(); // starts player movement after JOP finishes running

				}
				 

			}

			// checks for key
			else if (currentRoom == 10)
			{
				if (playerMask.intersects(keyMask))
				{
					keyMask = new Rectangle2D.Double(0,0,0,0); // removes key mask
					gotKey = true; // can access room 2 now 
				}
			}




		}


		// enemy movement
		if (e.getSource() == enemyTimer)
		{

			// each enemy follows player depending on x/y pos
			for (int i = 0; i < enemy.size(); i++)
			{


				if (playerX < enemy.get(i).getX()) 
				{

					enemy.get(i).setX(enemy.get(i).getX() - enemySpeed); // uses enemy speed
					enemyDirX = LEFT; // sets direction of current enemy

				}
				else if (playerX > enemy.get(i).getX())
				{

					enemy.get(i).setX(enemy.get(i).getX() + enemySpeed);
					enemyDirX = RIGHT;// sets direction of current enemy

				}

				if (playerY < enemy.get(i).getY())
				{

					enemy.get(i).setY(enemy.get(i).getY() - enemySpeed);
					enemyDirY = UP;
					// sets direction of current enemy
				}

				else if (playerY > enemy.get(i).getY())
				{

					enemy.get(i).setY(enemy.get(i).getY() + enemySpeed);
					enemyDirY = DOWN;// sets direction of current enemy

				}


				// moves current enemy mask depending on new x/ypos
				enemyMask.get(i).moveMask(enemy.get(i).getX(), enemy.get(i).getY(), 
						enemy.get(i).getNode().getIconWidth(), enemy.get(i).getNode().getIconHeight());

				//helps to move enemies away from each other
				for (int j = 0; j < enemy.size(); j++)
				{

					// checks if current enemy intersects all other enemies
					while (enemyMask.get(i).getMask().intersects(enemyMask.get(j).getMask()) && j != i)
					{

						// depending on current enemy direction, will move either left or right/ up or down until no longer intersecting
						if (enemyDirX == LEFT)
						{
							enemy.get(i).setX(enemy.get(i).getX() + 20);
						}
						else if (enemyDirX == RIGHT)
						{
							enemy.get(i).setX(enemy.get(i).getX() - 20);
						}

						if (enemyDirY == UP)
						{
							enemy.get(i).setY(enemy.get(i).getY() + 20);
						}
						else if (enemyDirY == DOWN)
						{
							enemy.get(i).setY(enemy.get(i).getY() - 20);
						}

						
						// sets mask after movement
						enemyMask.get(i).moveMask(enemy.get(i).getX(),
								enemy.get(i).getY(), enemy.get(i).getNode().getIconWidth(),
								enemy.get(i).getNode().getIconHeight());
					}

				}



				// checks if player touches enemy
				if (isInvin == false)
				{
					if (playerMask.intersects(enemyMask.get(i).getMask()))
					{

						playerHeartsFULL.remove(currentHeart); // removes a heart
						currentHeart--; 
						isInvin = true; // makes player invincible 

						if (currentHeart != 0)
						{
							invincibleTimer.start();
						}
						else // if player hearts == 0, stops all timers and starts death sequence
						{
							enemyTimer.stop();
							roomCheck.stop(); 
							bulletMovement.stop(); 
							playerMovement.stop(); 
							deathTimer.start(); 
						}
						break; 

					}
				}

			}

			if (enemy.size() == 0) // if all enemies defeated 
			{
				clearRoom = true; // room is cleared

				if (currentRoom == 2) // final boss room --> if all enemies are killed here, winning JOP appears and then game exits
				{
					JOptionPane.showMessageDialog(null, "Congratulations!\nYou beat the final bosses and beat the nightmare!", "Winner!", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}


			}

		}


		// Checks what current room is when any door is intersected 
		if(e.getSource() == roomCheck)
		{
		
			// if any door is touched
			if(playerMask.intersects(doorDown) || playerMask.intersects(doorUp)||
					playerMask.intersects(doorLeft)||playerMask.intersects(doorRight))
			{

				// remove all player projectiles
				for (int i = 0; i < bulletsMask.size(); i++)
				{
					bulletsMask.remove(i);
					dirBullets.remove(i);
					bullets.remove(i);
					i--;
				}
				//remove all enemy projectiles
				for (int i = 0; i < enemyBulletsMask.size(); i++)
				{
					enemyBulletsMask.remove(i);
					i--;
				}

				// no longer firing 
				isFired = false;


				// checks all rooms that can be accessed by right door
				if (playerMask.intersects(doorRight))
				{

					// sets x/ypos accordingly 
					playerX = spawnLeftX;
					playerY = spawnLeftY; 

					// sets current room --> tells paint what to draw according to the current room
					if (currentRoom == 1)
					{
						currentRoom = 3; 

						if (isCollect1 == false)
						{
							roundItems[0].setItem(currentRoom); // sets item for the first item room
							roundItems[0].setIndex(currentRoom);
						}

					}
					else if (currentRoom == 4)
					{
						enemyTimer.stop();
						currentRoom = 1;

					}
					else if (currentRoom == 5)
					{
						currentRoom = 4;
					}
					else if (currentRoom == 7)
					{
						currentRoom = 8;

						if (hereOnce8 == false) // adds 4 walker enemies 
						{

							enemyTimer.start();
							clearRoom = false;

							for (int i = 0; i < 4; i++)
							{
								enemy.add(new Enemy());
								enemy.get(i).setLocation(currentRoom, i);
								enemy.get(i).setHealth(80);

								enemyMask.add(new Enemy());

								enemyMask.get(i).moveMask(enemy.get(i).getX(),
										enemy.get(i).getY(), enemy.get(i).getNode().getIconWidth(),
										enemy.get(i).getNode().getIconHeight());

							}

							hereOnce8 = true; // room8 was accessed 
						}
					}
					else if (currentRoom == 9)
					{
						currentRoom = 7;
					}
					else if (currentRoom == 10)
					{
						currentRoom = 9;
					}

				}

				// checks all rooms that can be accessed by left door
				else if(playerMask.intersects(doorLeft))
				{

					// sets x/ypos accordingly
					playerX = spawnRightX;
					playerY = spawnRightY; 

					if (currentRoom == 1)
					{
						currentRoom = 4; 

						enemyTimer.start();
						healCheck.stop(); // stops timer after returning to prior room

						if (hereOnce4 == false) // sets 4 walker enemies
						{
							clearRoom = false;

							for (int i = 0; i < 4; i++)
							{
								enemy.add(new Enemy());
								enemy.get(i).setLocation(currentRoom, i);
								enemy.get(i).setHealth(50);

								enemyMask.add(new Enemy());

								enemyMask.get(i).moveMask(enemy.get(i).getX(),
										enemy.get(i).getY(), enemy.get(i).getNode().getIconWidth(),
										enemy.get(i).getNode().getIconHeight());

							}

							hereOnce4 = true; // room4 was accessed
						}
					}
					else if (currentRoom == 3)
					{
						currentRoom = 1;
					}
					else if (currentRoom == 4)
					{
						currentRoom = 5;

						if (isCollect2 == false)
						{
							roundItems[1].setItem(currentRoom); // sets second item room
							roundItems[1].setIndex(currentRoom);
						}

						healCheck.start(); //bandaids are here, start the timer

					}
					else if (currentRoom == 8)
					{
						currentRoom = 7;
					}
					else if (currentRoom == 7)
					{
						currentRoom = 9;


					}
					else if (currentRoom == 9)
					{
						currentRoom = 10;
					}

				}
				
				// checks all rooms that can be accessed by upper door

				else if(playerMask.intersects(doorUp))
				{

					if (currentRoom == 6 || currentRoom == 7 || (currentRoom == 1 && gotKey == true)) 
					{
						playerX = spawnDownX;
						playerY = spawnDownY; 
					}


					if (currentRoom == 1)
					{
						if (gotKey == true) // if has key, allowed to enter room
						{
							currentRoom = 2; 

							enemyTimer.start();
							hereOnce2 = false;

							for (int i = 0; i < 4; i++) //adds 4 walker enemies
							{
								enemy.add(new Enemy());
								enemy.get(i).setLocation(currentRoom, i);
								enemy.get(i).setHealth(120);

								enemyMask.add(new Enemy());

								enemyMask.get(i).moveMask(enemy.get(i).getX(),
										enemy.get(i).getY(), enemy.get(i).getNode().getIconWidth(),
										enemy.get(i).getNode().getIconHeight());
							}

						}
						else // does not have key --> tells player to get key 
						{
							playerMovement.stop();
							isUp = false;
							isRight = false;
							isLeft = false;
							isDown = false;

							JOptionPane.showMessageDialog(null, "Find the boss key first!", "Locked!", JOptionPane.ERROR_MESSAGE);

							playerY += 20;

							playerMask = new Rectangle2D.Double(playerX,playerY, playerImgs[lookDir-1].getIconWidth(), playerImgs[lookDir-1].getIconHeight());

							playerMovement.start();
						}
					}
					else if (currentRoom == 6)
					{
						currentRoom = 1;
					}
					else if (currentRoom == 7)
					{
						currentRoom = 6;
					}


				}

				// checks all rooms that can be accessed by lower door
				else if(playerMask.intersects(doorDown))
				{

					// Sets x/ypos accordingly
					playerX = spawnUpX;
					playerY = spawnUpY; 

					if (currentRoom == 1)
					{

						currentRoom = 6; 


						if (hereOnce6 == false)
						{
							clearRoom = false;

							//System.out.println("wtrar");
							enemyBulletMovement.start(); 
							enemyBulletsTimer.start();

							for (int i = 0; i < 2; i++) // adds 2 shooter enemies and a button if not yet cleared
							{
								enemyShooter.add(new Enemy());
								enemyShooter.get(i).setLocation(currentRoom, i);

								buttonImgsOPEN[i] = butt.getOpenImg(); 
								buttonImgsCLOSE[i] = butt.getCloseImg(); 


							}

						}



					}
					else if (currentRoom == 6)
					{
						currentRoom = 7;

						if (hereOnce7 == false) // if not yet cleared, adds 4 walker enemies to room
						{

							enemyTimer.start();
							clearRoom = false;

							for (int i = 0; i < 4; i++)
							{
								enemy.add(new Enemy());
								enemy.get(i).setLocation(currentRoom, i);
								enemy.get(i).setHealth(50);

								enemyMask.add(new Enemy());

								enemyMask.get(i).moveMask(enemy.get(i).getX(),
										enemy.get(i).getY(), enemy.get(i).getNode().getIconWidth(),
										enemy.get(i).getNode().getIconHeight());

							}

							hereOnce7 = true; //room7 was accessed
						}

					}

					else if (currentRoom == 2)
					{
						currentRoom = 1;
					}
				}

			}

			for (int i = 0; i < bandaids.size(); i++)
			{
				bandaids.get(i).setMask(currentRoom); // removes mask if no longer in room 5
			}

		}




		// player projectile movement
		if(e.getSource() == bulletMovement)
		{
			for (int i = 0; i < bulletsMask.size(); i++)
			{
				// moves mask continually 
				bulletsMask.get(i).getMoveMASK(bulletDir, 
						bulletX, bulletY, dirBullets.get(i)); 

				// if touches wall, remove projectile
				if (!(bulletsMask.get(i).getMask()
						.intersects(wallsMask)))
				{

					bulletsMask.remove(i);
					bullets.remove(i);
					dirBullets.remove(i);

					i--;
				}

				// in any of the walker enemy rooms
				if (currentRoom == 4 || currentRoom == 7 || 
						currentRoom == 8 || currentRoom == 2) 
				{
					for (int j = 0; j < enemy.size(); j++)
					{
						if (i >= 0)
						{
							// checks if bullet touches enemy
							if (bulletsMask.get(i).getMask()
									.intersects(enemyMask.get(j)
											.getMask())) 
							{
								// removes projectile
								bulletsMask.remove(i);
								bullets.remove(i);
								dirBullets.remove(i);

								i--;

								// removes health from current enemy
								enemy.get(j).setHealth(enemy.get(j)
										.getHealth()-playerDamage);

								// if reaches 0 or below health, enemy 
								//	is removed from array list
								if (enemy.get(j).getHealth() <= 0) 
								{
									enemy.remove(j);
									enemyMask.remove(j);

									j--;
								}
							}
						}
					}
				}
			}			
		}


		// after buffer runs, allows player to fire
		if (e.getSource() == bulletInterval)
		{
			canFire = true; 
		}

		// invincible for 1 second, then no longer
		if (e.getSource() == invincibleTimer)
		{
			isInvin = false;
			invincibleTimer.stop();

		}

		//if died, shows JOP saying player is dead and then quits game
		if (e.getSource() == deathTimer)
		{
			JOptionPane.showMessageDialog(null, "You DIED!", "GAME OVER", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}

		//buffer for enemy projectiles
		if (e.getSource() == enemyBulletsTimer)
		{
			enemyIsFired = true;
		}

		// timer for enemy projectile movement
		if (e.getSource() == enemyBulletMovement)
		{

			if (enemyIsFired == true) // once buffer says yes, bullet can go
			{
				for (int i = 0; i < 2; i++) // adds to both the top and bottom shooter enemy 
				{
					enemyBulletsMask.add(new EnemyProjectile(i));
					enemyBulletsImg.add(enProj.getImage());
				}
			}

			enemyIsFired = false; // shot, buffer runs

			for (int i = 0; i < enemyBulletsMask.size(); i++) // keeps track of every enemy bullet
			{
				enemyBulletsMask.get(i).getMoveMASK(enemyBulletsMask.get(i).getX()); // moves mask and image

				if(!(enemyBulletsMask.get(i).getMask().intersects(wallsMask))) // removes bullet if touches the wall
				{
					enemyBulletsMask.remove(i);
					enemyBulletsImg.remove(i);
					i--;
				}

				else if(enemyBulletsMask.get(i).getMask().intersects(playerMask)) // if enemy bullet touches player
				{

					// removes enemy bullet
					enemyBulletsMask.remove(i);
					i--;

					// remove one heart from player
					playerHeartsFULL.remove(currentHeart);
					currentHeart--; 
					
					// set invincible
					isInvin = true;
					if (currentHeart != 0)
					{
						invincibleTimer.start();
						break; 
					}
					
					else // if current heart is at 0, player is dead
					{
						roomCheck.stop(); 
						bulletMovement.stop(); 
						playerMovement.stop(); 
						enemyBulletsTimer.stop(); 
						enemyBulletMovement.stop(); 

						deathTimer.start(); 
					}

				}

			}



		}


		// checks if player has touched a bandaid
		if (e.getSource() == healCheck)
		{
			for (int i = 0; i < bandaids.size(); i++) // for every bandaid
			{
				if (playerMask.intersects(bandaids.get(i).getMask()) && currentHeart != currentEHeartIndex) // runs if player touches bandaid and heart containers arent full
				{
					bandaids.remove(i); // remove one bandaid
					i--; 

					// add one heart
					currentHeart++; 
					playerHeartsFULL.add(new PlayerHealth());
					playerHeartsFULL.get(currentHeart).setX(currentHeart);

				}

			}
		}

		// repaint to paint method
		repaint();

	}

	public void keyTyped(KeyEvent e) {}

	public void keyPressed(KeyEvent e) 
	{

		// sets direction of player (looking/going up/down/left/right)
		// only goes up/left/down/right if pressed by an arrow key
		if (e.getKeyCode() == KeyEvent.VK_UP || (e.getKeyCode() == KeyEvent.VK_W && isFired == false))
		{
			lookDir = UP;

			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				isUp = true;
			}			

		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN || (e.getKeyCode() == KeyEvent.VK_S && isFired == false))
		{

			lookDir = DOWN;
			if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				isDown = true;
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT || (e.getKeyCode() == KeyEvent.VK_A && isFired == false))
		{
			lookDir = LEFT;
			if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				isLeft = true;
			}
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT || (e.getKeyCode() == KeyEvent.VK_D && isFired == false))
		{
			lookDir = RIGHT;
			if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				isRight = true;
			}
		}



		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S|| e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D)
		{

			// adds a bullet with each press of W/A/S/D 

			isFired = true; 

			if (canFire == true)	// adds according to buffer
			{
				if(e.getKeyCode() == KeyEvent.VK_W)
				{	
					bulletDir = UP; 


					dirBullets.add(UP);



				}
				else if(e.getKeyCode() == KeyEvent.VK_S)
				{	
					bulletDir = DOWN; 
					dirBullets.add(DOWN);



				}
				else if(e.getKeyCode() == KeyEvent.VK_A)
				{	
					bulletDir = LEFT; 
					dirBullets.add(LEFT);


				}
				else if(e.getKeyCode() == KeyEvent.VK_D)
				{	
					bulletDir = RIGHT; 
					dirBullets.add(RIGHT);

				}

				bulletsMask.add(new Projectile(playerX, playerY, playerImgs[lookDir-1]));
				bullets.add(proj.getImage());
			}

			canFire = false; // when a bullet is added, cannot add another until buffer is done

			bulletMovement.start(); 

		}



	}

	public void keyReleased(KeyEvent e) 
	{

		// stop moving up/down/left/right if key released
		
		if (e.getKeyCode() == KeyEvent.VK_UP) 
		{
			isUp = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			isDown = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			isLeft = false;
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			isRight = false;
		}

	}

	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;

		
		// draw background
		g2.drawImage(bgImg.getImage(), 0, 0, this);
	
		// draw contents of rooms and doors depending on the current room (decided in roomchek 
		if (currentRoom == 1)
		{

			doorLeft = new Rectangle2D.Double(0, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
			doorRight = new Rectangle2D.Double(getWidth() - 85, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
			doorDown = new Rectangle2D.Double(getWidth()/2 - (85/2), getHeight() - 97, 85,90);
			doorUp = new Rectangle2D.Double(getWidth()/2 - (85/2), 0, 85,90);

			g2.fill(doorLeft);
			g2.fill(doorRight);
			g2.fill(doorDown);
			g2.fill(doorUp);
		}

		else if (currentRoom == 2)
		{
			doorLeft = new Rectangle2D.Double(0, 0, 1,1);
			doorRight = new Rectangle2D.Double(0, 0, 1,1);
			doorUp = new Rectangle2D.Double(0, 0, 1,1);

			if (hereOnce2 == false)
			{
				doorDown = new Rectangle2D.Double(0, 0, 1,1);
			}
			else
			{
				doorDown = new Rectangle2D.Double(getWidth()/2 - (85/2), getHeight() - 97, 85,90);
			}

			for (int i = 0; i < enemy.size(); i++)
			{

				g2.drawImage(enemy.get(i).getNode().getImage(), enemy.get(i).getX(), enemy.get(i).getY(), this); 

			}


			g2.fill(doorDown);

		}
		else if (currentRoom == 3)
		{

			doorRight = new Rectangle2D.Double(0, 0, 1,1);
			doorDown = new Rectangle2D.Double(0, 0, 1,1);
			doorUp = new Rectangle2D.Double(0, 0, 1,1);

			doorLeft = new Rectangle2D.Double(0, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);


			if (isCollect1 == false)
			{
				g2.drawImage(roundItems[0].getImage().getImage(), roundItems[0].getX(), roundItems[0].getY(), this);
			}


			g2.fill(doorLeft);
		}
		else if (currentRoom == 4)
		{

			doorDown = new Rectangle2D.Double(0, 0, 1,1);
			doorUp = new Rectangle2D.Double(0, 0, 1,1);

			if (clearRoom == false)
			{
				doorLeft = new Rectangle2D.Double(0, 0, 1,1);
				doorRight = new Rectangle2D.Double(0, 0, 1,1);
			}

			else
			{
				doorLeft = new Rectangle2D.Double(0, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
				doorRight = new Rectangle2D.Double(getWidth() - 85, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);

				//				g2.draw(bandaids.get(0).getMask());
				//				g2.drawImage(bandaids.get(0).getImage().getImage(), bandaids.get(0).getX(),bandaids.get(0).getY(), this);
				//

			}
			for (int i = 0; i < enemy.size(); i++)
			{

				g2.drawImage(enemy.get(i).getNode().getImage(), enemy.get(i).getX(), enemy.get(i).getY(), this); 
				//g2.fill(enemyMask.get(i).getMask());

			}

			g2.fill(doorLeft);
			g2.fill(doorRight);

		}
		else if (currentRoom == 5)
		{

			doorLeft = new Rectangle2D.Double(0, 0, 1,1);
			doorDown = new Rectangle2D.Double(0, 0, 1,1);
			doorUp = new Rectangle2D.Double(0, 0, 1,1);

			doorRight = new Rectangle2D.Double(getWidth() - 85, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);


			for (int i = 0; i < bandaids.size(); i++)
			{
				g2.drawImage(bandaids.get(i).getImage().getImage(), bandaids.get(i).getX(), bandaids.get(i).getY(), this);
			}

			if (isCollect2 == false)
			{
				g2.drawImage(roundItems[1].getImage().getImage(), roundItems[1].getX(), roundItems[1].getY(), this);
			}

			g2.fill(doorRight);
		}
		else if (currentRoom == 6) // enemy
		{

			doorLeft = new Rectangle2D.Double(0, 0, 1,1);
			doorRight = new Rectangle2D.Double(0, 0, 1,1);

			if (clearRoom == false)
			{
				doorDown = new Rectangle2D.Double(0, 0, 1,1);
				doorUp = new Rectangle2D.Double(0, 0, 1,1);
			}
			else
			{

				doorDown = new Rectangle2D.Double(getWidth()/2 - (85/2), getHeight() - 97, 85,90);
				doorUp = new Rectangle2D.Double(getWidth()/2 - (85/2), 0, 85,90);

				//				g2.draw(bandaids.get(bandaidIndex).getMask());
				//				g2.drawImage(bandaids.get(bandaidIndex).getImage().getImage(), bandaids.get(bandaidIndex).getX(),bandaids.get(bandaidIndex).getY(), this);

			}

			for (int i = 0; i < enemyBulletsMask.size(); i++)
			{

				g2.drawImage(enemyBulletsImg.get(i).getImage(), enemyBulletsMask.get(i).getX(), enemyBulletsMask.get(i).getY(), this);
			}

			for (int i = 0; i < enemyShooter.size(); i++)
			{

				g2.drawImage(enemyShooter.get(i).getShooterImage().getImage(), enemyShooter.get(i).getX(), enemyShooter.get(i).getY(), this); 
				//g2.fill(enemyMask.get(i).getMask());
			}



			g2.drawImage(butt.getCloseImg().getImage(), butt.getX(), butt.getY(), this);

			if(clearRoom == false)
			{
				g2.drawImage(butt.getOpenImg().getImage(), butt.getX(), butt.getY(), this);
			}


			g2.fill(doorUp);
			g2.fill(doorDown);
		}
		else if (currentRoom == 7) // enemy
		{
			doorDown = new Rectangle2D.Double(0, 0, 1,1);

			if (clearRoom == false)
			{
				doorLeft = new Rectangle2D.Double(0, 0, 1,1);
				doorRight = new Rectangle2D.Double(0, 0, 1,1);
				doorUp = new Rectangle2D.Double(0, 0, 1,1);
			}
			else
			{
				doorUp = new Rectangle2D.Double(getWidth()/2 - (85/2), 0, 85,90);
				doorRight = new Rectangle2D.Double(getWidth() - 85, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
				doorLeft = new Rectangle2D.Double(0, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
			}

			for (int i = 0; i < enemy.size(); i++)
			{

				g2.drawImage(enemy.get(i).getNode().getImage(), enemy.get(i).getX(), enemy.get(i).getY(), this); 

			}


			g2.fill(doorLeft);
			g2.fill(doorRight);
			g2.fill(doorDown);
			g2.fill(doorUp);
		}
		else if (currentRoom == 8) 
		{

			if (clearRoom == false)
			{
				doorLeft = new Rectangle2D.Double(0, 0, 1,1);
				doorRight = new Rectangle2D.Double(0, 0, 1,1);
				doorUp = new Rectangle2D.Double(0, 0, 1,1);
				doorRight = new Rectangle2D.Double(0, 0, 1,1);

			}
			else
			{
				doorLeft = new Rectangle2D.Double(0, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
			}

			for (int i = 0; i < enemy.size(); i++)
			{

				g2.drawImage(enemy.get(i).getNode().getImage(), enemy.get(i).getX(), enemy.get(i).getY(), this); 

			}

			g2.fill(doorLeft);
		}
		else if (currentRoom == 9) // COMPUTER
		{

			doorDown = new Rectangle2D.Double(0, 0, 1,1);
			doorUp = new Rectangle2D.Double(0, 0, 1,1);

			if (hereOnce9 == true)
			{
				doorLeft = new Rectangle2D.Double(0, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
				doorRight = new Rectangle2D.Double(getWidth() - 85, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);
			} 
			else 
			{
				doorLeft = new Rectangle2D.Double(0, 0, 1,1);
				doorRight = new Rectangle2D.Double(0, 0, 1,1);
			}


			g2.drawImage(computerImg.getImage(), computerX, computerY, this);

			g2.fill(doorLeft);
			g2.fill(doorRight);

		}
		else if (currentRoom == 10)
		{

			doorLeft = new Rectangle2D.Double(0, 0, 1,1);
			doorDown = new Rectangle2D.Double(0, 0, 1,1);
			doorUp = new Rectangle2D.Double(0, 0, 1,1);

			doorRight = new Rectangle2D.Double(getWidth() - 85, getHeight()/2 - playerImgs[lookDir-1].getIconHeight()/2, 85,80);

			if (gotKey == false)
			{
				g2.drawImage(keyImg.getImage(), keyX, keyY, this);
			}

			g2.fill(doorRight);

		}



		g2.drawImage(playerImgs[lookDir-1].getImage(), playerX, playerY, this);


		for(int i = 0; i < bulletsMask.size(); i++)
		{

			g2.drawImage(bullets.get(i).getImage(), bulletsMask.get(i).getX(), bulletsMask.get(i).getY(), this);
		}

		for (int i = 1; i < playerHeartsEMPTY.size(); i++)
		{

			g2.drawImage(playerHeartsEMPTY.get(i).getImgEmpty().getImage(), playerHeartsEMPTY.get(i).getX(), playerHeartsEMPTY.get(i).getY(), this);

		}
		for (int i = 1; i < playerHeartsFULL.size(); i++)
		{

			g2.drawImage(playerHeartsFULL.get(i).getImgFull().getImage(), playerHeartsFULL.get(i).getX(), playerHeartsFULL.get(i).getY(), this);

		}
		
		if (isStart == true)
		{
			g2.drawImage(titleImg.getImage(), 0, 0, this);
		}

	}


}