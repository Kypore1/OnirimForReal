import java.awt.Rectangle;
import java.util.ArrayList;

public class Area 
{
	private Rectangle myRect = new Rectangle();
	protected ArrayList<Card> cards = new ArrayList<Card>();
	
	public Area(int x, int y,int w,int h)
	{
		myRect = new Rectangle(x,y,w,h);
	}
	public Rectangle getMyRect() 
	{
		return myRect;
	}
	public ArrayList<Card> getCards() 
	{
		return cards;
	}
	public int getX()
	{
		return myRect.x;
	}
	public int getY()
	{
		return myRect.y;
	}
	public void addCard(Card c)
	{
		cards.add(c);
	}
	public void removeCard(Card c)
	{
		cards.remove(c);
	}
	
}
