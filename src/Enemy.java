import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy 
{

	private ImageIcon enemy, enemyShoot;
	private int xPos, yPos; 
	private int index; 
	private int health;
	private Random rnd; 
	private Rectangle2D mask; 

	public Enemy()
	{

		rnd = new Random();
		index = rnd.nextInt(4) + 1;
		enemy = new ImageIcon("enemyImgs/enemy" + index + ".png");
		
		enemyShoot = new ImageIcon("enemyImgs/pschoo.png");

		xPos = 0;
		yPos = 0;
		
		health = 0;
		
		mask = new Rectangle2D.Double(xPos, yPos, 0,0);

	}

	public void setLocation(int room, int currEnemy)
	{
		if (room == 4 )
		{
			if (currEnemy == 0)
			{
				xPos = 85;
				yPos = 100;
			}
			else if (currEnemy == 1)
			{
				xPos = 400;
				yPos = 100;
			}
			else if (currEnemy == 2)
			{
				xPos = 85;
				yPos = 485;
			} 
			else if (currEnemy == 3)
			{
				xPos = 400;
				yPos = 485;
			} 
		}
		
		else if (room == 6)
		{
			xPos = 85;

			if (currEnemy == 0)
			{
				yPos = 300;
			}
			else if (currEnemy == 1)
			{
				yPos = 485;
			}
		}
		
		else if (room == 7)
		{
			if (currEnemy == 0)
			{
				xPos = 85;
				yPos = 130;
			}
			else if (currEnemy == 1)
			{
				xPos = 610;
				yPos = 130;
			}
			else if (currEnemy == 2)
			{
				xPos = 85;
				yPos = 485;
			} 
			else if (currEnemy == 3)
			{
				xPos = 610;
				yPos = 485;
			} 
		}
		
		else if (room == 8)
		{
			if (currEnemy == 0)
			{
				xPos = 150;
				yPos = 100;
			}
			else if (currEnemy == 1)
			{
				xPos = 610;
				yPos = 100;
			}
			else if (currEnemy == 2)
			{
				xPos = 150;
				yPos = 485;
			} 
			else if (currEnemy == 3)
			{
				xPos = 610;
				yPos = 485;
			} 
		}
		else if (room == 2)
		{
			if (currEnemy == 0)
			{
				xPos = 85;
				yPos = 100;
			}
			else if (currEnemy == 1)
			{
				xPos = 610;
				yPos = 100;
			}
			else if (currEnemy == 2)
			{
				xPos = 85;
				yPos = 300;
			} 
			else if (currEnemy == 3)
			{
				xPos = 610;
				yPos = 300;
			} 
		}
	}
	
	public Rectangle2D moveMask(int x, int y, int width, int height)
	
	{
		
		mask = new Rectangle2D.Double(x, y, width, height);
		
		return mask; 
		
		
	}
	
	public Rectangle2D getMask()
	{
		return mask; 
	}

	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}
	
	public void setX(int x)
	{
		xPos = x; 
	}
	public void setY(int y)
	{
		yPos = y; 
	}

	public ImageIcon getNode()
	{
		return enemy; 
	}
	
	public ImageIcon getShooterImage()
	{
		return enemyShoot;
	}
	
	public int getHealth()
	{
		return health; 
	}
	
	public void setHealth(int newHealth)
	{
		health = newHealth; 
	}

}
