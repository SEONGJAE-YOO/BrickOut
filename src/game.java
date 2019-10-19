import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class game extends JPanel implements  MouseMotionListener,MouseListener{
	
	
	boolean pid=true;
	boolean item=false;
	boolean block=false;
	int player;
	makeBlock makeBlock;
	 Ball ball1;
	 Ball ball2;
	 board board;
	 otherBoard Oboard;
	 boolean inout=false;
	 Image img=null;
	 Graphics buffer=null;
	 int x;
	 int lifeP1=3;
	 int lifeP2=3;
	public game() throws IOException {
		ball1=new Ball(this,1);
		ball2=new Ball(this,2);
		board=new board(this);
		makeBlock=new makeBlock(this);
		Oboard=new otherBoard(this);
		setVisible(true);
		addMouseMotionListener(this);
		addMouseListener(this);
		this.setBackground(Color.white);
		this.player=player;
		
	}
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		if(img==null)
			img=createImage(this.getWidth(),this.getHeight());
		if(buffer==null)
			buffer=img.getGraphics();
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, this);
		makeBlock.draw(g2d);
		ball1.draw(g2d);
		ball2.draw(g2d);
		board.draw(g2d);
		Oboard.draw(g2d);
	}

	void move() {
		if(ball1.restart||ball2.restart) {
		ball1.move();
		ball2.move();
		}
		board.move();
		Oboard.move();
		makeBlock.move();
	}
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(player==1) {
		board.mouseDragged(e);	
		}else {
			Oboard.mouseDragged(e);
		}
		
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	
	}
public void SetPlayer(int player) {
	this.player=player;
}

}