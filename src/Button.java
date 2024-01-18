import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Button 
{

	private ImageIcon imgOpen, imgClose;
	private int xPos, yPos; 
	private Rectangle2D buttonMask;


//	public Button()
//	{
//		imgOpen = new ImageIcon("openButton.png");
//		imgClose = new ImageIcon("closeButton.png");
//
//		xPos = 0;
//
//		yPos = 0;	
//
//		buttonMask = new Rectangle2D.Double(xPos, yPos, imgClose.getIconWidth(),imgClose.getIconHeight());
//
//	}


	public Button()
	{
		imgOpen = new ImageIcon("openButton.png");
		imgClose = new ImageIcon("closeButton.png");

		xPos = 720;


		yPos = 510; 


		buttonMask = new Rectangle2D.Double(xPos, yPos, imgClose.getIconWidth(),imgClose.getIconHeight());

	}



	public int getX()
	{
		return xPos;
	}

	public int getY()
	{
		return yPos;
	}

	public ImageIcon getOpenImg()
	{
		return imgOpen;
	}
	public ImageIcon getCloseImg()
	{
		return imgClose;
	}

	public Rectangle2D getMask()
	{
		return buttonMask;
	}


}
