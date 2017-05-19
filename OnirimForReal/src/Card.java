import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class Card 
{
	private Image image;
	private boolean selected, visible, movable;
	private String type, color;
	private Rectangle myRect,oldRect;
	

	
	public Card(Image img, String t, String c)
	{
		image = img;
		selected = false;
		visible = false;
		movable = false;
		type = t;
		color = c;
		myRect = new Rectangle(-200,-200,100,140);
		oldRect = new Rectangle(-200,-200,100,140);
	}
	
	public Image getImage() {return image;}
	public boolean getSelected() {return selected;}
	public Rectangle getOldRect(){return oldRect;}
	public int getOriginalX() {return oldRect.x;}
	public int getOriginalY() {return oldRect.y;}
	public String getType() {return type;}
	public String getColor() {return color;}
	public boolean getVisible() {return visible;}
	public boolean getMovable() {return movable;}
	public Rectangle getRect() {return myRect;}
	public int getX(){return myRect.x;}
	public int getY(){return myRect.y;}

	public void updateOldRect(){oldRect.setLocation(myRect.getLocation());}
	public boolean isMouse(MouseEvent e){return myRect.contains(e.getPoint());}
	public void setSelected(boolean val) {selected = val;}
	public void setVisible(boolean val) {visible = val;}
	public void setMovable(boolean val) {movable = val;}
}