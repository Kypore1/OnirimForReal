
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
	public int[] totalDiscard()
	{
		int[] toRet = new int[13];
		for(int i =0;i<cards.size();i++)
		{
			if(cards.get(i).getColor().contains("red"))
			{
				if(cards.get(i).getType().contains("Sun"))
				{
					toRet[0]++;
				}
				if(cards.get(i).getType().contains("Moon"))
				{
					toRet[1]++;
				}
				if(cards.get(i).getType().contains("Key"))
				{
					toRet[2]++;
				}
			}
			if(cards.get(i).getColor().contains("blue"))
			{
				if(cards.get(i).getType().contains("Sun"))
				{
					toRet[3]++;
				}
				if(cards.get(i).getType().contains("Moon"))
				{
					toRet[4]++;
				}
				if(cards.get(i).getType().contains("Key"))
				{
					toRet[5]++;
				}
			}
			if(cards.get(i).getColor().contains("green"))
			{
				if(cards.get(i).getType().contains("Sun"))
				{
					toRet[6]++;
				}
				if(cards.get(i).getType().contains("Moon"))
				{
					toRet[7]++;
				}
				if(cards.get(i).getType().contains("Key"))
				{
					toRet[8]++;
				}
			}
			if(cards.get(i).getColor().contains("tan"))
			{
				if(cards.get(i).getType().contains("Sun"))
				{
					toRet[9]++;
				}
				if(cards.get(i).getType().contains("Moon"))
				{
					toRet[10]++;
				}
				if(cards.get(i).getType().contains("Key"))
				{
					toRet[11]++;
				}
			}
			if(cards.get(i).getType().contains("nightmare"))
			{
				toRet[12]++;
			}
		}
		return toRet;
	}

}
