import java.awt.Image;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;

public class EnemyProjectile 
{

	private Ellipse2D bulletMask;
	private ImageIcon imgBullet, imgWIP1;
	private Image imgWIP2;
	private int xPos, yPos; 

	
	public EnemyProjectile()
	{

		imgWIP1 = new ImageIcon("enemyImgs/enemyBullet.png");
		imgWIP2 = imgWIP1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgBullet = new ImageIcon(imgWIP2);


		xPos = 0;
		yPos = 0;


		bulletMask = new Ellipse2D.Double(xPos + imgBullet.getIconWidth(), yPos,30,30 );


	}
	
	public EnemyProjectile(int index)
	{

		imgWIP1 = new ImageIcon("enemyImgs/enemyBullet.png");
		imgWIP2 = imgWIP1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		imgBullet = new ImageIcon(imgWIP2);


		xPos = 85;

		if (index == 0)
		{
			yPos = 300;
		}
		else if (index == 1)
		{
			yPos = 485;
		}


		bulletMask = new Ellipse2D.Double(xPos + imgBullet.getIconWidth(), yPos,30,30 );


	}

	public ImageIcon getImage()
	{
		return imgBullet; 
	}

	public Ellipse2D getMask()
	{
		return bulletMask; 
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


	public Ellipse2D getMoveMASK(int bulletX)
	{


		xPos += 10;



		bulletMask = new Ellipse2D.Double(xPos,
				yPos,30,30);

		return bulletMask;


	}



}
