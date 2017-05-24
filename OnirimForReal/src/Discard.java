
public class Discard extends Area
{

	public Discard(int x, int y, int w, int h) 
	{
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	public void organizeDiscard()
	{
		for (int i = 0; i < cards.size(); i++) 
		{
			cards.get(i).getRect().setLocation(10,10+(20*i));
		}
	}

}
