import java.awt.Image;

public class Deck extends Area
{
	private Image image;
	public Deck(int x, int y, int w, int h,Image img) 
	{
		super(x, y, w, h);
		image = img;
	}
	public Image getImage() {return image;}
	public int findIndexOfCard(String type, String color)
	{
		for(int i=0; i<cards.size();i++)
		{
			if(cards.get(i).getType().contains(type)&&cards.get(i).getColor().contains(color))
			{
				return i;
			}
		}
		return -1;
	}


}
