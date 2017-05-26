import java.util.ArrayList;

public class PlayingArea extends Area{

	ArrayList<Card> currentSet = new ArrayList<Card>();
	public PlayingArea(int x, int y, int w, int h)
	{
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}
	public void addCard(Card c)
	{
		cards.add(c);
		if(currentSet.size()>=3)
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
			if(!c.getType().contains(cards.get(cards.size()-1).getType())&&!(c.getType().contains("door")||c.getType().contains("dream")))
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
	public void clearCurrentSet(){currentSet.clear();}
	public boolean validSet()// checks to see if the sum of a set is less than or equal to four
	{
		if(currentSet.size()==3)
		{
			boolean checkOne = compareCards(currentSet.get(0),currentSet.get(1));
			boolean checkTwo =compareCards(currentSet.get(1),currentSet.get(2));
			boolean checkThree = compareCards(currentSet.get(2),currentSet.get(0));
			if(checkOne&&checkTwo&&checkThree)
			{
				return true;
			}
		}
		return false;
	}
	public void organizePlay()
	{
		int rows=0;
		int coloums=0;
		for (int i = 0; i < cards.size(); i++) 
		{
			if (i%14==0&&i!=0) 
			{
				rows++;
				coloums=0;
			}
			cards.get(i).getRect().setLocation(130+(65*coloums),10+(100*rows));
			coloums++;
		}
	}
}
