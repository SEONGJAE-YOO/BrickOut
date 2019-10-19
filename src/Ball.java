

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Ball {
	static final int RADIUS=10;
	int x=500;
	int y;
	int vx=0;
	int vy=5;
	int player;

	static boolean restart=true;
	private game game;
	private BufferedImage image;
	public Ball(game g,int i) {
		this.game=g;
		this.player=i;
		File input=new File("C:/Users/LG/eclipse-workspace/BrickOut/src");
		try {
			image=ImageIO.read(input);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(player==1) {
			 y=700;
		 }else if(player==2) {
			 y=200;
		 }
	}
	public void move() {
		if(x+vx<0) {
			vx=-vx;
		}
		if(x+vx>game.getWidth()-2*RADIUS) {
			vx=-vx;
		}
		if(y+vy<10) {
			if(player==1) {
				
				y=700;
				vy=5;
				}else {
					y=200;
					vy=-5;
				}
				x=500;
				vx=0;
				game.lifeP2--;
		}
		if(y+vy>game.getHeight()-2*RADIUS) {
			
			if(player==1) {
			
			y=700;
			vy=5;
			}else {
				y=200;
				vy=-5;
			}
			x=500;
			vx=0;
			game.lifeP1--;
		}
		x=x+vx;
		y=y+vy;
		
		if(collision1()) {
			
			if(x+RADIUS<game.board.x+20) {
				if(vx>=5) {
					vx=-5;
				}else if(vx>0) {
					vx=vx-3;
				}else if(vx==0) {
					vx=-5;
				}
				else if(vx>-5) {
					vx=vx-1;
				}
				else if(vx<-5) {
					vx=vx-3;
				}
			} else if(x+RADIUS<game.board.x+40) {
				if(vx>=5) {
					vx=vx-3; 
				}else if(vx>0) {
					vx=vx-1;
				}else if(vx==0) {
					vx=-3;
				}
				else if(vx>-5) {
					vx=vx-1;
				}
				else if(vx<-5) {
					vx=vx-3;
				}
				} else if(x+RADIUS<game.board.x+80) {
					if(vx>=5) {
						vx=vx+3;
					}else if(vx>0) {
						vx=vx+1;
					}else if(vx==0) {
						vx=3;
					}
					else if(vx>-5) {
						vx=vx+1;
					}
					else if(vx<-5) {
						vx=vx+3;
					}
				} else if(x+RADIUS<game.board.x+100) {
					if(vx>=5) {
						vx=vx+3;
					}else if(vx>0) {
						vx=vx+1;
					}else if(vx==0) {
						vx=5;
					}
					else if(vx>-5) {
						vx=vx+3;
					}
					else if(vx<-5) {
						vx=5;
					}
				}
			
			vy=-vy;
			System.out.println(vx);
		}
		if(collision2().getWidth()!=0) {
			if(y+RADIUS>collision2().getY()&&y+RADIUS<collision2().getY()+collision2().getHeight()) {
				vx=-vx;
			}
			else {
				vy=-vy;
			}
			System.out.println(x+" "+y);
		}
		if(collision3()) {
				
				if(x+RADIUS<game.Oboard.x+20) {
					if(vx>=5) {
						vx=-5;
					}else if(vx>0) {
						vx=vx-3;
					}else if(vx==0) {
						vx=-5;
					}
					else if(vx>-5) {
						vx=vx-1;
					}
					else if(vx<-5) {
						vx=vx-3;
					}
				} else if(x+RADIUS<game.Oboard.x+40) {
					if(vx>=5) {
						vx=vx-3; 
					}else if(vx>0) {
						vx=vx-1;
					}else if(vx==0) {
						vx=-3;
					}
					else if(vx>-5) {
						vx=vx-1;
					}
					else if(vx<-5) {
						vx=vx-3;
					}
					} else if(x+RADIUS<game.Oboard.x+80) {
						if(vx>=5) {
							vx=vx+3;
						}else if(vx>0) {
							vx=vx+1;
						}else if(vx==0) {
							vx=3;
						}
						else if(vx>-5) {
							vx=vx+1;
						}
						else if(vx<-5) {
							vx=vx+3;
						}
					} else if(x+RADIUS<game.Oboard.x+100) {
						if(vx>=5) {
							vx=vx+3;
						}else if(vx>0) {
							vx=vx+1;
						}else if(vx==0) {
							vx=5;
						}
						else if(vx>-5) {
							vx=vx+3;
						}
						else if(vx<-5) {
							vx=5;
						}
					}
				
				vy=-vy;
				System.out.println(vx);
		}
		
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(image, x, y, 2*RADIUS,2*RADIUS, game);
		
	}
	public Rectangle getBall() {
		return new Rectangle(x,y,2*RADIUS,2*RADIUS);
	}
	private boolean collision1() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return game.board.getBoard().intersects(getBall());
		
	}
	private boolean collision3() {
		try {
			Thread.sleep(5);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		return game.Oboard.getOtherBoard().intersects(getBall());
	}
	protected Rectangle collision2() {
		for(int i=0;i<10;i++) {
			for(int j=0;j<11;j++) {
					if(game.makeBlock.getblock(i, j).intersects(getBall())) {
						System.out.println(game.makeBlock.getblock(i, j).x+" "+game.makeBlock.getblock(i, j).y);
						return game.makeBlock.getblock(i, j);
					}
			}
		}
		return new Rectangle(0,0,0,0);
		
	}
	public int getBallX() {
		return x;
	}
	public int getBallY() {
		return y;
	}
	public void setBallX(int x) {
		this.x=x;
	}
	public void setBallY(int y) {
		this.y=y;
	}
	

}
