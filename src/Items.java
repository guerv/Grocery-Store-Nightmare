import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.ImageIcon;

public class Items 
{

	private Random rnd;
	private ImageIcon[] items;
	private Rectangle2D[] itemsMask;

	private ImageIcon[] roundItems;
	private Rectangle2D[] roundItemsMask;

	private ImageIcon currItem;
	private Rectangle2D currItemMask; 

	private int xPos, yPos, rndIndex1, rndIndex2, rndIndex3, currIndex; 


	public Items()
	{


		currIndex = -1; 

		rnd = new Random(); 
		items = new ImageIcon[8];
		itemsMask = new Rectangle2D[8];

		roundItems = new ImageIcon[3];
		roundItemsMask = new Rectangle2D[3];

		currItem = null;
		currItemMask = new Rectangle2D.Double(0, 0, 0, 0);

		xPos = 450;
		yPos = 300; // CENTRE OF SCREEN?? 


		for (int i = 0; i < items.length; i++)
		{

			items[i] = new ImageIcon("items/item" + (i+1) + ".png");
			itemsMask[i] = new Rectangle2D.Double(xPos, yPos, items[i].getIconWidth(), items[i].getIconHeight());

		}

		rndIndex1 = rnd.nextInt(8); //IAODJasd BIRNG IT BACK
		rndIndex2 = rnd.nextInt(8);

		while (rndIndex2 == rndIndex1)
		{
			rndIndex2 = rnd.nextInt(8);
		}

		rndIndex3 = rnd.nextInt(8);

		while (rndIndex3 == rndIndex2)
		{
			rndIndex3 = rnd.nextInt(8);
		}

		roundItems[0] = items[rndIndex1]; // TESTING
		roundItems[1] = items[rndIndex2];
		roundItems[2] = items[rndIndex3];

		roundItemsMask[0] = itemsMask[rndIndex1];
		roundItemsMask[1] = itemsMask[rndIndex2];
		roundItemsMask[2] = itemsMask[rndIndex3];


	}



	public void setIndex(int room)
	{
		if (room == 3)
		{
			currIndex = rndIndex1;
		}

		else if(room == 5)
		{
			currIndex = rndIndex2;
		}
	}

	public int getIndex()
	{
		return currIndex;
	}

	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}

	public void setItem(int room) 
	{
		if (room == 3)
		{
			currItem = roundItems[0];
			currItemMask = roundItemsMask[0];
		}

		else if(room == 5)
		{
			currItem = roundItems[1];
			currItemMask = roundItemsMask[1];
		}
	}

	public ImageIcon getImage()
	{
		return currItem;	
	}

	public void setMask()
	{


		currItemMask = new Rectangle2D.Double(0, 0, 0, 0);

	}

	public Rectangle2D getMask()
	{
		return currItemMask;	
	}


}

