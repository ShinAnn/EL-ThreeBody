package model.operation;

import java.util.Scanner;

import dto.GameDTO;

public class SendMessage extends Operation implements Operable {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public SendMessage(String operator, String receiver) {
		super(operator, receiver);
	}

	@Override
	public void process() {
		GameDTO dto=GameDTO.getInstance();
		SendMessage sm=new SendMessage(operator, receiver);
		dto.depositOperation(sm);
	}
	
	public String toOperator(){
		Scanner scan = new Scanner(System.in);
		String s=scan.nextLine();
		return this.operator+"向"+this.receiver+"发送了一条消息： "+s;
		
	}
	
	public String toReceiver(){
		Scanner scan = new Scanner(System.in);
		String s=scan.nextLine();
		return this.operator+"向"+this.receiver+"发送了一条消息： "+s;
		
	}
	
	
	
}
