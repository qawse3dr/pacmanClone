package pacman;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class turningPoint extends Gameobjects{
	//about 63 turning points
	
	public static List<turningPoint> turningPoints  = new ArrayList<turningPoint>();
	public turningPoint(int x, int y, movement[] posMove){
		availableMoves = posMove;
		this.x = x;
		this.y = y+3*16;
		this.rect = new Rectangle((int)this.x,(int)this.y,16,16);
		turningPoints.add(this);
		Gameobjects.gameObjects.add(this);
		
		
	}@Override
	public void draw(Graphics g) {
		//g.setColor(Color.BLUE);
		//g.drawRect(x, y, 5, 5);
		
	}

	@Override
	public void tick() {
		
		
	}
	

}
