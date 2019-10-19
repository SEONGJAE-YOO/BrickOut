import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.JFrame;
public class makeBlock extends JFrame {
	block[][] block;
	int px=10;
	int py=250;
	boolean blockBreak=true;
	boolean item=true;
	int itemKind=0;
	boolean player=true;
	game game;
	int count1=55;
	int count2=55;
	makeBlock(game g){
		this.game=g;
		block=new block[10][11];
		for(int x=0;x<10;x++) {
			for(int y=0;y<11;y++) {
								
					block[x][y]=new block(px,py,blockBreak,player);
					px=px+81;
			}
			player=!player;
			py=py+38;
			px=10;
		}
		px=10;
		py=330;
		System.out.println("red "+count1+"blue "+count2);
	}
	public void draw(Graphics2D g) {
		for(int x=0;x<10;x++) {
			for(int y=0;y<11;y++) {
				if(block[x][y].player) {
					g.setColor(Color.LIGHT_GRAY);
				}
				else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(block[x][y].x, block[x][y].y, block[x][y].width, block[x][y].height);

			}
		}
	}
	public void move() {
		if(game.ball1.collision2().getWidth()!=0||game.ball2.collision2().getWidth()!=0) {
			System.out.println("e");
			for(int i=0;i<10;i++) {
				for(int j=0;j<11;j++) {
					if(game.ball1.x+game.ball1.RADIUS>block[i][j].x-10&&game.ball1.x+game.ball1.RADIUS<=block[i][j].x+block[i][j].width+10) {
						if((game.ball1.y-10<block[i][j].y+block[i][j].height&&game.ball1.y-10>block[i][j].y)||(game.ball1.y+2*game.ball1.RADIUS+10>block[i][j].y&&game.ball1.y+2*game.ball1.RADIUS+10<block[i][j].y+block[i][j].height)) {
						block[i][j].width=0;
						block[i][j].height=0;
						block[i][j].blockBreak=false;
						if(block[i][j].player) {
							count1--;
						}else {
							count2--;
						}
						}
					}
					if(game.ball2.x+game.ball2.RADIUS>block[i][j].x-10&&game.ball2.x+game.ball2.RADIUS<=block[i][j].x+block[i][j].width+10) {
						if((game.ball2.y-10<block[i][j].y+block[i][j].height&&game.ball2.y-10>block[i][j].y)||(game.ball2.y+2*game.ball2.RADIUS+10>block[i][j].y&&game.ball2.y+2*game.ball2.RADIUS+10<block[i][j].y+block[i][j].height)) {
						block[i][j].width=0;
						block[i][j].height=0;
						block[i][j].blockBreak=false;
						if(block[i][j].player) {
							count1--;
						}else {
							count2--;
						}
						}
					}
				}
			}
		}
		
	}
	public Rectangle getblock(int x,int y) {
		
		return new Rectangle(block[x][y].x,block[x][y].y,block[x][y].width,block[x][y].height);
	}
	public int getCount1() {
		return count1;
	}
	public int getCount2() {
		return count2;
	}
	public void Reset() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<11;j++) {
				if(!block[i][j].blockBreak) {
					block[i][j].width=70;
					block[i][j].height=30;
					block[i][j].blockBreak=true;
				}

			}
		}
	}

	
}
