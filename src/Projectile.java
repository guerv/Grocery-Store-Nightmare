import java.awt.Image;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

public class Projectile 
{

	private ImageIcon img, imgWIP1;
	private Image imgWIP2;
	private int xPos, yPos;
	private Ellipse2D mask; 
	public final int DOWN = 1, UP = 3, LEFT = 2, RIGHT = 4;

	
	public Projectile(int playerX, int playerY, ImageIcon playerImg)
	{
		
		//Ellipse2D projectile = new Ellipse2D.Double(playerX + (playerImgs[lookDir-1].getIconWidth()/2),
		//playerY + (playerImgs[lookDir-1].getIconHeight()/2), 5, 5);
		
		imgWIP1 = new ImageIcon("stoneProj.png");
		imgWIP2 = imgWIP1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		img = new ImageIcon(imgWIP2);
		
		xPos = playerX+ (playerImg.getIconWidth()/2);
		yPos = playerY+ (playerImg.getIconHeight()/2);
		mask = new Ellipse2D.Double(playerX + (playerImg.getIconWidth()/2),
				playerY + (playerImg.getIconHeight()/2),30,30);
		
	}
	
	public ImageIcon getImage()
	{
		return img; 
	}
	
	public Ellipse2D getMask()
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
	public void setX(int bulletX)
	{
		xPos = bulletX;
	}
	public void setY(int bulletY)
	{
		yPos = bulletY;
	}
	
	
	public Ellipse2D getMoveMASK(int bulletDir, int bulletX, int bulletY, int dirBullets)
	{
		
		if (dirBullets == UP)
		{
			yPos -= 10; 
		}
		else if (dirBullets == DOWN)
		{
			yPos += 10;

		}
		
		else if (dirBullets == LEFT)
		{
			xPos -= 10; 

		}
		else if(dirBullets == RIGHT)
		{
			xPos += 10; 

		}
	
		mask = new Ellipse2D.Double(xPos,
				yPos,30,30);
		
		return mask;

		
	}

	
	
	

	public Ellipse2D getMoveUP(int dirBullets)
	{
		
		if (dirBullets == UP)
		{
			yPos -= 10; 
		}
	
		
		mask = new Ellipse2D.Double(xPos,
				yPos,10,10);
		
		return mask;
		
	}
	
	public int getMoveDOWN(int dirBullets)
	{
		
		
		if (dirBullets == DOWN)
		{
			yPos += 10;

		}
		
		return yPos;

		
		
	}
	public int getMoveLEFT(int dirBullets)
	{
		
		
		if (dirBullets == LEFT)
		{
			xPos -= 10; 

		}
		
		return xPos;

		
		
	}
	public int getMoveRIGHT(int dirBullets)
	{
		
		if(dirBullets == RIGHT)
		{
			xPos += 10; 
		}
		
		return xPos;

		
		
	}
	
	

}
