import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class User extends Thread{
	Room game;   
	Socket socket;  
	BufferedReader input;
	PrintWriter output;
	int player;
	User other;
	boolean start=true;
	public User(Room game,Socket socket,int player) {
		this.game=game;
		this.socket=socket;
		this.player=player;
		try {
			input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			output=new PrintWriter(socket.getOutputStream(),true);
			//output.println("START "+player);
		}catch(IOException e) {
			System.out.println("연결이 끊어졌습니다.");
		}
	}
	public void setOther(User other) {
		this.other=other;
	}
	public void run() {
		try {
		//	output.println("PRINT 게임을 시작합니다.");
			while(true) {
				String command=input.readLine();
				StringTokenizer st=new StringTokenizer(command," ");
				String a=st.nextToken();
				String mx=st.nextToken();
				String sx=st.nextToken();
				String sy=st.nextToken();
				String bx=st.nextToken();
				String by=st.nextToken();
				
				if(command==null) {
					continue;
				}
				
				if(a.equals("MOVE")) {
					System.out.println(command);
					int x=Integer.parseInt(mx);
					if(player==1) {
					other.output.println("MOVE "+mx+" "+sx+" "+sy+" "+bx+" "+by);
					}else {
						other.output.println("MOVE "+mx+" "+sx+" "+"e"+" "+"e"+" "+"e");
					}
					}
				else if(a.equals("LOGIN")) {
					if(player==1) {
					game.setP1(mx);
					}else {
					game.setP2(mx);
					}
					if(game.getP2()==null) {
					output.println("SET "+game.getP1()+" "+game.getP2()+" "+player+" "+"e"+" "+"e");
					
					}else {
						output.println("SET "+game.getP1()+" "+game.getP2()+" "+player+" "+"e"+" "+"e");
						other.output.println("SETOTHER "+game.getP1()+" "+game.getP2()+" "+"e"+" "+"e"+" "+"e");
					}
					System.out.println("11p :"+game.getP1()+" 2p : "+game.getP2());
				}else if(a.equals("QUIT")) {
					return;
				}else if(a.equals("READY")) {
					if(Integer.parseInt(mx)==1) {
						game.setReadyP1();
						System.out.println("ddd");
						output.println("READY "+game.getReadyP1()+" "+game.getP1()+" "+"e"+" "+"e"+" "+"e");
						other.output.println("READY "+game.getReadyP1()+" "+game.getP1()+" "+"e"+" "+"e"+" "+"e");
					}else {
						game.setReadyP2();
						output.println("READY "+game.getReadyP2()+" "+game.getP2()+" "+"e"+" "+"e"+" "+"e");
						other.output.println("READY "+game.getReadyP2()+" "+game.getP2()+" "+"e"+" "+"e"+" "+"e");
					}
				}else if(a.equals("WIN")) {
					game.setwin(Integer.parseInt(mx));
					if(game.getwin()==1) {
					output.println("WIN "+game.getP1()+" "+"e"+" "+"e"+" "+"e"+" "+"e");
					other.output.println("WIN "+game.getP1()+" "+"e"+" "+"e"+" "+"e"+" "+"e");
					}else if(game.getwin()==2) {
					output.println("WIN "+game.getP2()+" "+"e"+" "+"e"+" "+"e"+" "+"e");
					other.output.println("WIN "+game.getP2()+" "+"e"+" "+"e"+" "+"e"+" "+"e");
					}
				}
				if(game.readyP1&&game.readyP2&&start) {
					output.println("START "+"start"+" "+"e"+" "+"e"+" "+"e"+" "+"e");
					other.output.println("START "+"start"+" "+"e"+" "+"e"+" "+"e"+" "+"e");
					start=true;
					game.setReadyP1();
					game.setReadyP2();
				}
			}  
		}catch(IOException e) {
			System.out.println("연결이 끊어졌습니다.");
		}finally {
			try {
				socket.close();
			}catch(IOException e) {
				
			}
		}
	}

}
