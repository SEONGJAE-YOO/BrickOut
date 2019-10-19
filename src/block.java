

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

public class block {
	int x;
	int y;   
	int width;
	int height;
	boolean blockBreak;
	boolean player;
	block(int x, int y,boolean blockBreak,boolean player){
		this.x=x;
		this.y=y;
		this.blockBreak=blockBreak;
		this.player=player;
		this.width=70;
		this.height=30;
	}
	public Rectangle getblock() {
		return new Rectangle(x,y,width,height);
	}
	
}
