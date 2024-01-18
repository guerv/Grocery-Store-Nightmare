import javax.swing.ImageIcon;

public class PlayerHealth
{

	private ImageIcon imgFullHeart, imgEmptyHeart;
	private int xPos, yPos;
	private int health;
	
	public PlayerHealth()
	{
		
		health = 3;
		imgFullHeart = new ImageIcon("heart.png");
		imgEmptyHeart = new ImageIcon("deadHeart.png");
		
		xPos = 10;
		yPos = 10; 
	}
	
	public int getX()
	{
		return xPos;
	}
	
	public int getY()
	{
		return yPos;
	}
	
	public ImageIcon getImgFull()
	{
		return imgFullHeart;
	}
	
	public ImageIcon getImgEmpty()
	{
		return imgEmptyHeart;
	}
	
	public int getHealth()
	{
		return health; 
	}
	
	public void setX(int index)
	{
		xPos += (index*imgFullHeart.getIconWidth());
	}
	
	
	
}
