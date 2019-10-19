import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
public class gameServer {

	public static void main(String args[]) throws IOException {
		ServerSocket ss=new ServerSocket(9101);

		System.out.println("서버 실행");
		try{
			while(true) {
				Room game=new Room();
				User user1=new User(game,ss.accept(),1);
				user1.start();
				User user2=new User(game,ss.accept(),2);
				user2.start();
				user1.setOther(user2);
				user2.setOther(user1);
				
				
			}
			}finally {
			ss.close();
		}
		
	}
	

}
