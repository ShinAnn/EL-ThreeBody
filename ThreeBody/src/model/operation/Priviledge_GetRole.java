package model.operation;

import java.util.List;

import model.Player;
import dto.GameDTO;


public class Priviledge_GetRole extends Operation implements Operable {
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	public Priviledge_GetRole(String operator, String receiver) {
		super(operator, receiver);		
	}
	
	@Override
	public String toOperator() {
		return this.operator + "对"+this.receiver+"使用了特权，得知其身份";
	}

	@Override
	public List<Operation> process() {
		
		GameDTO dto=GameDTO.getInstance();
				
		//get operator and receiver
		Player pOperator=null;
		for(Player player:dto.getPlayers()){
			if(player.getAccount().getId().equals(this.operator)){
				pOperator  = player;
			}
		}
		
		Player pReceiver=null;
		for(Player player:dto.getPlayers()){
			if(player.getAccount().getId().equals(this.receiver)){
				pReceiver = player;
			}
		}
		
		//获知receiver的role
		pOperator.findRole(pReceiver, pReceiver.getRole());
		//再将operator的privilege设为false（初始为true）
		pOperator.setPrivilegeAvailable(false);
		
		return null;
		
	}
		
}
