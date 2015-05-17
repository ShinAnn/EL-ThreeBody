package model.operation;


public class SendMessage extends Operation {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;

	public SendMessage(String operator, String receiver, String msg) {
		super(operator, receiver);
		this.msg = msg;
	}

	public String toOperator(){
		
		return this.operator+"向"+this.receiver+"发送了一条消息： "+msg;
		
	}
	
	public String toReceiver(){
		
		return this.operator+"向"+this.receiver+"发送了一条消息： "+msg;
	}
	
}
