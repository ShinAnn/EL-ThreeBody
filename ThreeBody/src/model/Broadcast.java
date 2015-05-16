package model;

/*
 * 包括 坐标广播，系统广播，玩家的消息
 */
public class Broadcast {
    
    private String from;
    private String to;
    private String content;
    
	public Broadcast(String from, String to, String content) {
		super();
		this.from = from;
		this.to = to;
		this.content = content;
	}
	public String getFrom() {
		return from;
	}
	public String getTo() {
		return to;
	}
	public String getContent() {
		return content;
	}

    
}
