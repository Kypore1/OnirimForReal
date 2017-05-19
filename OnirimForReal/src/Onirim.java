import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Onirim extends JFrame {

		Image tanSun = Toolkit.getDefaultToolkit().getImage("Tan sun.png");
		Image tanMoon = Toolkit.getDefaultToolkit().getImage("Tan Moon.png");
		Image tanKey = Toolkit.getDefaultToolkit().getImage("Tan Key.png");
		Image tanDoor = Toolkit.getDefaultToolkit().getImage("tanDoor.png");
		
		Image redSun = Toolkit.getDefaultToolkit().getImage("Red sun.png");
		Image redMoon = Toolkit.getDefaultToolkit().getImage("Red Moon.png");
		Image redKey = Toolkit.getDefaultToolkit().getImage("Red Key.png");
		Image redDoor = Toolkit.getDefaultToolkit().getImage("redDoor.png");
		
		Image blueSun = Toolkit.getDefaultToolkit().getImage("Blue Sun.png");
		Image blueMoon = Toolkit.getDefaultToolkit().getImage("Blue Moon.png");
		Image blueKey= Toolkit.getDefaultToolkit().getImage("Blue Key.png");
		Image blueDoor = Toolkit.getDefaultToolkit().getImage("blueDoor.png");
		
		Image greenSun = Toolkit.getDefaultToolkit().getImage("Green sun.png");
		Image greenMoon = Toolkit.getDefaultToolkit().getImage("Green Moon.png");
		Image greenKey = Toolkit.getDefaultToolkit().getImage("Green Key.png");
		Image greenDoor = Toolkit.getDefaultToolkit().getImage("greenDoor.png");
		
		Image cardBack = Toolkit.getDefaultToolkit().getImage("Zard Back.png");
		Image nightmare = Toolkit.getDefaultToolkit().getImage("Unknown.png");
		
		public ArrayList<Card> hand = new ArrayList<Card>();
		public ArrayList<Card> doors = new ArrayList<Card>();
		public ArrayList<Card> limbo = new ArrayList<Card>();
		
		public boolean ignoreNightmare;
		public boolean firstDraw = true;
		
		public Deck deck = new Deck(10,850,100,140,cardBack);
		
		public Random rand = new Random();
		
		public static void main(String[] args) 
		{
			Onirim obj = new Onirim();
			obj.makeEnvironment();
			obj.addCards();
		}
		
		public void addCards()
		{
			for (int i = 0; i < 9; i++) // add suns
				limbo.add(new Card (redSun,"locationSun","red"));
			for (int i = 0; i < 8; i++) 
				limbo.add(new Card (blueSun,"locationSun","blue"));
			for (int i = 0; i < 7; i++) 
				limbo.add(new Card (greenSun,"locationSun","green"));
			for (int i = 0; i < 6; i++) 
				limbo.add(new Card (tanSun,"locationSun","tan"));
			
			for (int i = 0; i < 4; i++) // add moons
			{
				limbo.add(new Card (redMoon,"locationMoon","red"));
				limbo.add(new Card (blueMoon,"locationMoon","blue"));
				limbo.add(new Card (greenMoon,"locationMoon","green"));
				limbo.add(new Card (tanMoon,"locationMoon","tan"));
			}
			for (int i = 0; i < 9; i++)  // add keys
			{
				limbo.add(new Card (redSun,"locationKey","red"));
				limbo.add(new Card (blueKey,"locationKey","blue"));
				limbo.add(new Card (greenKey,"locationKey","green"));
				limbo.add(new Card (tanKey,"locationKey","tan"));
			}
			for (int i = 0; i < 10; i++)
				limbo.add(new Card (nightmare,"nightmare",""));
			shuffleDeck();
		}
		
		public void makeEnvironment()
		{
			setTitle("Onirim");
			setBounds(0,0,1280,1024);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MyPanel pan = new MyPanel();
			addMouseListener(new Mousey());
			addMouseMotionListener(new Mousey());
			pan.setBackground(Color.DARK_GRAY);
			getContentPane().add(pan);
			setResizable(false);
			setVisible(true);
		}
		
		private class MyPanel extends JPanel
		{
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				drawStuff((Graphics2D)g);
			}
		}
		
		public void drawStuff(Graphics2D g)
		{
			g.drawImage(deck.getImage(), deck.getX(), deck.getY(), 100, 140,this);
			if(!hand.isEmpty())
			{
				for(int i=0;i<hand.size();i++)
				{
					g.drawImage(hand.get(i).getImage(), hand.get(i).getX(), hand.get(i).getY(), 100, 140,this);
				}
			}
			repaint();
		}
		public void fillHand()
		{
			while(hand.size()!=5)
			{
				if((firstDraw||ignoreNightmare)&&(deck.getCards().get(0).getType().contains("nightmare")||deck.getCards().get(0).getType().contains("door")))
				{
					limbo.add(deck.drawCard());
				}
				else if(hand.size()<5)
					hand.add(deck.drawCard());

			}
			shuffleDeck();
		}
		public void shuffleDeck()
		{
			while(!limbo.isEmpty())
			{
				deck.addCard(limbo.remove(0));
			}
			int rando = 0;
			rando = rand.nextInt(deck.getCards().size());
			Card temp = null;
			for(int i = 0; i <deck.getCards().size(); i++)
			{
				temp = deck.getCards().get(i);
				deck.getCards().set(i, deck.getCards().get(rando));
				deck.getCards().set(rando, temp);
				rando = rand.nextInt(deck.getCards().size());
			}
		}
		public boolean canDraw()
		{
			if (!(hand.size()>4)) 
			{
				return true;
			}
			return false;
		}
		public void organizeHand()
		{
			for (int i = 0; i < hand.size(); i++) 
			{
				hand.get(i).getRect().setLocation(130+(40*i),850);
			}
		}
		
		private class Mousey implements MouseListener, MouseMotionListener
		{	
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				for(int i=0;i<hand.size();i++)
					if (hand.get(i).getSelected()) 
					{
						hand.get(i).getRect().setLocation(e.getX()-50, e.getY()-70);
					}
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}

			@Override
			public void mouseExited(MouseEvent e) 
			{
				
			}

			@Override
			public void mousePressed(MouseEvent e) 
			{
				if(deck.isMouse(e)&&canDraw()) 
				{
					if (firstDraw) 
					{
						fillHand();
					}
					else
					{
						hand.add(deck.drawCard());
						if (hand.get(hand.size()-1).getType().contains("nightmare")||hand.get(hand.size()-1).getType().contains("door")) 
						{
							limbo.add(hand.remove(hand.size()-1));
						}
					}
					organizeHand();
				}
				for(int i=hand.size()-1;-1<i;i--)
				{
					if(hand.get(i).isMouse(e)) 
					{
						hand.get(i).updateOldRect();
						hand.get(i).setSelected(true);
						break;
					}
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) 
			{
				for(Card c:hand)
					if (c.getSelected()) 
					{
						c.setSelected(false);
						c.getRect().setLocation(c.getOldRect().getLocation());
					}
			}
		}
}
