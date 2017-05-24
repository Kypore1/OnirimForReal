import java.util.ArrayList;

public class PlayingArea extends Area{

	ArrayList<Card> cards = new ArrayList<Card>();
	ArrayList<Card> currentSet = new ArrayList<Card>();
	public PlayingArea(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	public void addCard(Card c)
	{
		cards.add(c);
		if(currentSet.size()>3)
		{
			currentSet.remove(0);
			currentSet.add(c);
		}
		else
		{
			currentSet.add(c);
		}
	}

	public boolean validLocationPlay(Card c)
	{
		if(cards.isEmpty())
		{
			return true;
		}
		else if(!cards.isEmpty())
		{
			if(c.getType().contains(cards.get(cards.size()-1).getType())&&!(c.getType().contains("door")||c.getType().contains("dream")))
				return true;
		}
		return false;
	}
	public boolean compareCards(Card a, Card b)
	{
		if(a.getColor().equals(b.getColor()))
			return true;
		return false;
	}
	
	public boolean validSet(Card a, Card b, Card c)// checks to see if the sum of a set is less than or equal to four
	{
		boolean checkOne = compareCards(a,b);
		boolean checkTwo =compareCards(a,c);
		boolean checkThree = compareCards(c,b);
		if(checkOne&&checkTwo&&checkThree)
		{
			return true;
		}
		return false;
	}
	public void organizePlay()
	{
		for (int i = 0; i < cards.size(); i++) 
		{
			cards.get(i).getRect().setLocation(10+(70*i),10);
		}
	}
}
