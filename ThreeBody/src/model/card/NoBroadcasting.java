package model.card;

import model.Player;
import model.operation.ResourceChange;
import model.operation.ResourceChange.Type;
import dto.GameDTO;

/*
 * 对某个玩家禁一轮广播坐标功能
 */
public class NoBroadcasting extends Card{	
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;



	public NoBroadcasting(String operator, String receiver) {
		super(operator, receiver);

	}

	@Override
	public void process() {
		
		GameDTO dto=GameDTO.getInstance();
		
		//get the  receiver
		Player pReceiver=this.findReceiver(dto);
		
		//pay the resource
		ResourceChange rc=new ResourceChange(operator, receiver, Type.DECREASE, this.requiredResource);
		dto.depositOperation(rc);
		
		//将receiver的broadcast设为false，在lifetime轮中
		pReceiver.setBroadcast(false);
		
	}
	
}
