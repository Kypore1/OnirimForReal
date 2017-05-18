import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;

public class Card 
{
	private int xOriginal, yOriginal;
	private Image image;
	private boolean selected, visible, movable;
	private String type, color;
	private Rectangle myRect;
	

	
	public Card(Image img, String t, String c)
	{
		image = img;
		selected = false;
		visible = false;
		movable = false;
		type = t;
		color = c;
		myRect = new Rectangle(-200,-200,100,140);
	}
	
	public Image getImage() {return image;}
	public boolean getSelected() {return selected;}
	public int getOriginalX() {return xOriginal;}
	public int getOriginalY() {return yOriginal;}
	public String getType() {return type;}
	public String getColor() {return color;}
	public boolean getVisible() {return visible;}
	public boolean getMovable() {return movable;}
	public Rectangle getRect() {return myRect;}


	public void setSelected(boolean val) {selected = val;}
	public void setVisible(boolean val) {visible = val;}
	public void setMovable(boolean val) {movable = val;}
}