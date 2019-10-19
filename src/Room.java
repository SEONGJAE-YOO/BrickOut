
public class Room {
	String p1;
	String p2;
	int win;
	boolean readyP1=false;
	boolean readyP2=false;
	public String getP1() {
		return p1;
	}
	public String getP2() {
		return p2;
	}
	public int getwin() {
		return win;
	}
	public String getReadyP1() {
		if(readyP1) {
			return "ready";
		}else {
			return "set";
		}
	}
	public String getReadyP2() {
		if(readyP2) {
			return "ready";
		}else {
			return "set";
		}
	}
	
	public void setP1(String p1) {
		this.p1=p1;
	}
	public void setP2(String p2) {
		this.p2=p2;
	}
	public void setwin(int win) {
		this.win=win;
	}
	public void setReadyP1() {
		this.readyP1=!this.readyP1;
	}
	public void setReadyP2() {
		this.readyP2=!this.readyP2;
	}
	
}
