import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class gameClient extends Thread implements ActionListener {
	private String me,other;
	private JFrame frame;
	private JPanel panel1;
	private JLabel message;
	private JButton login;
	private JButton ready;
	private JTextField id;
	private JLabel p1Id;
	private JLabel p2Id;
	private Socket socket;
	private CardLayout card;
	private game game;
	private JPanel panel2;
	private BufferedReader input;
	private PrintWriter output;
	private boolean start=false;
	private int player;
	private JLabel p1Ready;
	private JLabel p2Ready;
	private JLabel win;
	private JLabel lifeP1;
	private JLabel lifeP2;
	private JLabel IDname;
	public gameClient() throws UnknownHostException,IOException{
		socket=new Socket("localhost",9101);
		input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		output=new PrintWriter(socket.getOutputStream(),true);
		frame=new JFrame();
		panel1=new JPanel();
		panel2=new JPanel();
		game=new game();
		login=new JButton();
		id=new JTextField(10);
		p1Id=new JLabel();
		p2Id=new JLabel();
		card=new CardLayout();
		ready=new JButton();
		p1Ready=new JLabel();
		p2Ready=new JLabel();
		win=new JLabel();
		lifeP1=new JLabel();
		lifeP2=new JLabel();
		IDname=new JLabel();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(card);
		frame.add("login",panel1);
		frame.add("game", panel2);
		frame.setSize(500, 500);
		frame.setVisible(true);
		panel1.setLayout(null);
		panel1.setSize(450,300);
		login.setBounds(20,350, 440, 100);
		login.addActionListener(this);
		login.setText("로그인");
		panel1.add(login);
		panel1.add(id);
		id.setBounds(180, 300, 200, 50);
		id.setText("");
		IDname.setBounds(130,300,50,50);
		IDname.setText("닉네임");
		panel1.add(IDname);
		panel2.setLayout(null);
		p1Id.setBounds(900, 650, 50, 50);
		p1Id.setText("hello");
		p2Id.setBounds(900, 150, 50, 50);
		p2Id.setText("hi");
		game.setBounds(0, 0, 900, 900);
		panel2.add(game);
		panel2.add(p1Id);
		panel2.add(p2Id);
		ready.setBounds(900,820, 80, 50);
		ready.addActionListener(this);
		ready.setText("준비");
		panel2.add(ready);
		p2Ready.setBounds(900,180,50,50);
		p2Ready.setText("set");
		p1Ready.setBounds(900,680,50,50);
		p1Ready.setText("set");
		win.setBounds(900, 500, 150, 50);
		lifeP1.setBounds(980, 680, 50, 50);
		lifeP2.setBounds(980,180,50,50);
		panel2.add(win);
		panel2.add(p1Ready);
		panel2.add(p2Ready);
		panel2.add(lifeP1);
		panel2.add(lifeP2);
		//frame.setResizable(false);
		
		
	
	}
	public void run() {
		
		
		try {
			while(true) {
				
				String response=input.readLine();
				System.out.println("22");
				StringTokenizer st=new StringTokenizer(response," ");
				System.out.println(response);
				String a=st.nextToken();
				String mx=st.nextToken();
				String sx=st.nextToken();
				String sy=st.nextToken();
				String bx=st.nextToken();
				String by=st.nextToken();
				
				if(response==null) {
					continue;
				}
				if(a.equals("SET")) {
					p1Id.setText(mx);
					p2Id.setText(sx);
					player=Integer.parseInt(sy);
					System.out.println(mx);
				}
				else if(a.equals("SETOTHER")) {
					p1Id.setText(mx);
					p2Id.setText(sx);
				}
				else if(a.equals("READY")) {
					if(player==1) {
						if(sx.equals(p1Id.getText())) {
							p1Ready.setText(mx);
							game.SetPlayer(player);
						}else {
							p2Ready.setText(mx);
						}
					}else {
						if(sx.equals(p2Id.getText())) {
							p2Ready.setText(mx);
							game.SetPlayer(player);
						}else {
							p1Ready.setText(mx);
						}
					}
				}
				else if(a.equals("START")) {
					start=true;
					game.makeBlock.Reset();
					win.setText("");
				}else if(a.equals("WIN")) {
					win.setText(mx+"님이 이기셨습니다.");
				}
				game.lifeP1=3;
				game.lifeP2=3;
				game.makeBlock.count1=55;
				game.makeBlock.count2=55;
			while(start) {
				if(player==1) {
					output.println("MOVE "+game.board.getmX()+" "+game.ball1.getBallX()+" "+game.ball1.getBallY()+" "+game.ball2.getBallX()+" "+game.ball2.getBallY());
				}else {
					output.println("MOVE "+game.Oboard.getmX()+" "+player+" "+"e"+" "+"e"+" "+"e");
				}
				response=input.readLine();
				st=new StringTokenizer(response," ");
				System.out.println(response+"111");
				 a=st.nextToken();
				 mx=st.nextToken();
				 sx=st.nextToken();
				 sy=st.nextToken();
				 bx=st.nextToken();
				 by=st.nextToken();
				if(a.equals("MOVE")) {
				if(player==1) {
					game.Oboard.setmX(Integer.parseInt(mx));
					System.out.println(mx+"p1");
				}else {
					game.board.setmX(Integer.parseInt(mx));
					System.out.println(mx+"p2");
					game.ball1.setBallX(Integer.parseInt(sx));
					game.ball1.setBallY(Integer.parseInt(sy));
					game.ball2.setBallX(Integer.parseInt(bx));
					game.ball2.setBallY(Integer.parseInt(by));
				}
				}
			
				game.move();
				game.repaint();
				if(game.lifeP2==0||game.makeBlock.count1==0) {
					start=false;
					output.println("WIN "+"1"+" "+"e"+" "+"e"+" "+"e"+" "+"e");
				}else if(game.lifeP1==0||game.makeBlock.count2==0) {
					start=false;
					output.println("WIN "+"2"+" "+"e"+" "+"e"+" "+"e"+" "+"e");
				}
				lifeP1.setText(Integer.toString(game.lifeP1));
				lifeP2.setText(Integer.toString(game.lifeP2));
			}
			}
			}
			 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {socket.close();
		}catch(IOException e) {
			e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws UnknownHostException,IOException{
		gameClient client=new gameClient();
		client.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String text=e.getActionCommand();

		if(text.equals("로그인")) {
			
			if(!id.getText().equals("")) {
		
			card.show(frame.getContentPane(),"game");
			output.println("LOGIN "+id.getText()+" "+"e"+" "+"e"+" "+"e"+" "+"e");
			frame.setSize(1050,920);
		}
		}else if(text.equals("준비")) {
			output.println("READY "+" "+player+" "+"e"+" "+"e"+" "+"e"+" "+"e");
		}
	}

}
