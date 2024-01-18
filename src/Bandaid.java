import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Bandaid 
{

	private ImageIcon bandaidImg;
	private Rectangle2D bandaidMask;
	private int xPos, yPos;
	private int index;

	public Bandaid()
	{
		xPos = 100;
		yPos = 500;

		index = -1; 

		bandaidImg = new ImageIcon("bandaid.png");
		bandaidMask = new Rectangle2D.Double(xPos, yPos, bandaidImg.getIconWidth(), bandaidImg.getIconHeight());

	}

	public void setIndex(int i)
	{
		index = i;
	}
	public int getIndex()
	{
		return index;
	}

	public int getX()
	{
		return xPos;
	}
	public int getY()
	{
		return yPos;
	}

	public void setX(int index)
	{
		xPos *= (index+1);
	}

	public void setMask(int room)
	{
		if (room == 5)
		{
			bandaidMask = new Rectangle2D.Double(xPos, yPos, bandaidImg.getIconWidth(), bandaidImg.getIconHeight());;
		}
		else
		{
			bandaidMask = new Rectangle2D.Double(0, 0, 0, 0);
		}
		
	}
	public ImageIcon getImage()
	{
		return bandaidImg;
	}

	public Rectangle2D getMask()
	{
		return bandaidMask;
	}

}
