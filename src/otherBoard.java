

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class otherBoard implements  MouseMotionListener,MouseListener{
	static final int width=100;
	static final int height=25;
	private game game;

    int mx;
    int my;
	int x=450;
	int y=30;
	int vx;
	int life=3;
	public otherBoard(game g) {
		this.game=g;
	}
	public void move() {
		
		if(x>0&&x<game.getWidth()-width) {
			x=x+vx;
		
		}
		else if(x<=0) {
			x=10;
		}
		else if(x>=game.getWidth()-width) {
			x=game.getWidth()-width-10;
		}
			
	}
	public void draw(Graphics2D g) {
		g.fillRect(x,y,width,height);
	}
	
	public void mouseDragged(MouseEvent e) {
		mx=e.getX();
		my=e.getY();
		x=mx-width/2;
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {
		vx=0;
	}
	public Rectangle getOtherBoard() {
		return new Rectangle(x,y,width,height);
	}
	public int getmX() {
		return x;
	}
	public void setmX(int x) {
		this.x=x;
	}
}
