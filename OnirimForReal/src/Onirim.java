import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
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
		
		public boolean ignoreNightmare,nightmareInPlay;;
		public boolean firstDraw = true;
		
		public boolean endGame,lost;
		
		public Deck deck = new Deck(10,850,100,140,cardBack);
		public Discard discard = new Discard(10,10,100,700);
		public PlayingArea play = new PlayingArea(130,10,1000,700);
		
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
			for (int i = 0; i < 3; i++)  // add keys
			{
				limbo.add(new Card (redKey,"locationKey","red"));
				limbo.add(new Card (blueKey,"locationKey","blue"));
				limbo.add(new Card (greenKey,"locationKey","green"));
				limbo.add(new Card (tanKey,"locationKey","tan"));
			}
			for(int i = 0; i<2;i++)
			{
				limbo.add(new Card(redDoor, "door", "red"));
				limbo.add(new Card(blueDoor, "door", "blue"));
				limbo.add(new Card(greenDoor, "door", "green"));
				limbo.add(new Card(tanDoor, "door", "tan"));
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
			if(!checkForLoss())
			{
				g.drawImage(deck.getImage(), deck.getX(), deck.getY(), 100, 140,this);
				for(int i=0;i<hand.size();i++)
				{
					g.drawImage(hand.get(i).getImage(), hand.get(i).getX(), hand.get(i).getY(), 100, 140,this);
				}
				for (int i = 0; i < limbo.size(); i++) 
				{
					g.drawImage(limbo.get(i).getImage(), limbo.get(i).getX(), limbo.get(i).getY(), 100, 140,this);
				}
				for (int i = 0; i < discard.getCards().size(); i++) 
				{
					g.drawImage(discard.getCards().get(i).getImage(), discard.getCards().get(i).getX(), discard.getCards().get(i).getY(), 100, 140,this);
				}
				for (int i = 0; i < play.getCards().size(); i++) 
				{
					g.drawImage(play.getCards().get(i).getImage(), play.getCards().get(i).getX(), play.getCards().get(i).getY(), 100, 140,this);
				}
				for(int i=0;i<doors.size();i++)
				{
					g.drawImage(doors.get(i).getImage(), doors.get(i).getX(), doors.get(i).getY(), 100, 140,this);
				}
				g.setColor(Color.WHITE);
				g.setStroke(new BasicStroke(4));
				g.draw(discard.getMyRect());
				g.draw(play.getMyRect());
			}
			else
			{
				g.drawString("Over", 10, 10);
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
			firstDraw=false;
			ignoreNightmare=false;
			shuffleDeck();
		}
		public boolean checkForLoss()
		{
			if(deck.getCards().size()<=0&&hand.size()!=5)
			{
				if(doors.size()==8)
				{
					lost=false;
					endGame=true;
					return true;
				}
				else 
				{
					lost=true;
					endGame=true;
					return true;
				}
			}
			return false;
		}
		public void discardHand()
		{
			if(nightmareInPlay)
			{
				while(hand.size()!=0)
					discard.addCard(hand.remove(0));
				discard.addCard(limbo.remove(limbo.size()-1));
				nightmareInPlay=false;
				ignoreNightmare=true;
			}
		}
		public void discardTopFiveDeck()//TODO make sure you dont draw another nightmare
		{
			if(nightmareInPlay)
			{
				int counter = 5;
				int pos = 0;
				while(counter!=0)
				{
					if(deck.getCards().size()==0)
					{
						endGame=true;
					}
					if(pos>=deck.getCards().size())
						break;
					if(!(deck.getCards().get(pos).getType().contains("nightmare")||deck.getCards().get(pos).getType().contains("door")))
					{
						discard.addCard(deck.getCards().remove(pos));
						counter--;
					}
					else 
						pos++;
				}
				ignoreNightmare=true;
				discard.addCard(limbo.remove(limbo.size()-1));
				nightmareInPlay=false;
			}
		}
		public void discardKey(Point p)
		{
			if(nightmareInPlay)
				{
				for (int i=0;i<hand.size();i++) 
				{
					if (hand.get(i).getType().contains("Key")&&hand.get(i).getRect().contains(p))
					{
						discard.addCard(hand.remove(i));
						discard.addCard(limbo.remove(limbo.size()-1));
						nightmareInPlay=false;
						ignoreNightmare=true;
						break;
					}
				}
			}
		}
		public void discardDoor(Point p)
		{
			if(nightmareInPlay)
			{
				for (int i=0;i<doors.size();i++) 
				{
					if (doors.get(i).getRect().contains(p))
					{
						deck.addCard(doors.remove(i));
						discard.addCard(limbo.remove(limbo.size()-1));
						nightmareInPlay=false;
						ignoreNightmare=true;
						shuffleDeck();
						break;
					}
				}
			}
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
			if (!(hand.size()>4)&&!nightmareInPlay) 
			{
				return true;
			}
			return false;
		}
		public void organizeHand()
		{
			for (int i = 0; i < hand.size(); i++) 
			{
				hand.get(i).getRect().setLocation(130+(105*i),850);
			}
		}
		public void organizeDoors()
		{
			for (int i = 0; i < doors.size(); i++) 
			{
				doors.get(i).getRect().setLocation(1150,10+(60*i));
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
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				
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
					if (firstDraw||hand.size()==0) 
					{
						fillHand();
					}
					else
					{
						hand.add(deck.drawCard());
						if (hand.get(hand.size()-1).getType().contains("nightmare")||hand.get(hand.size()-1).getType().contains("door")) 
						{
							limbo.add(hand.remove(hand.size()-1));
							limbo.get(limbo.size()-1).getRect().setLocation(500+(100*limbo.size()), 850);
							if(limbo.get(limbo.size()-1).getType().contains("nightmare"))
								nightmareInPlay=true;
						}
					}
					organizeHand();
				}
				else if (nightmareInPlay)
				{
					discardKey(e.getPoint());
					
					discardDoor(e.getPoint());
					
					if(deck.isMouse(e))
						discardTopFiveDeck();
					
					for (int i = 0; i < hand.size(); i++) 
						if(hand.get(i).getRect().contains(e.getPoint()))
							discardHand();
					
					discard.organizeDiscard();
					
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
				if(hand.size()==5 && limbo.size()>0)
				{
					shuffleDeck();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) 
			{
				for (int i = 0; i < hand.size(); i++) 
				{
					if (hand.get(i).getSelected()) 
					{
						if (discard.isMouse(e))
						{
							discard.addCard(hand.remove(i));
							discard.organizeDiscard();
							organizeHand();
						}
						else if(play.isMouse(e)&&play.validLocationPlay(hand.get(i)))
						{
							play.addCard(hand.remove(i));
							play.organizePlay();
							if(play.validSet())
							{
								doors.add(deck.getCards().remove(deck.findIndexOfCard("door", play.getCards().get(play.getCards().size()-1).getColor())));
								organizeDoors();
								play.clearCurrentSet();
							}
						}
						else
						{
							hand.get(i).setSelected(false);
							hand.get(i).getRect().setLocation(hand.get(i).getOldRect().getLocation());
						}
					}
				}
					
			}
		}
}
