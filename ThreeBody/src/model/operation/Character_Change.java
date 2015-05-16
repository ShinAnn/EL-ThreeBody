package model.operation;

import model.Player;
import model.role.Earth;
import dto.GameDTO;


public class Character_Change extends Operation implements Operable {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public Character_Change(String operator, String receiver) {
		super(operator, receiver);
	}

	@Override
	public void process() {
		
		GameDTO dto=GameDTO.getInstance();
		
		//得到操作者（三体）
		Player pOperator=null;
		for(Player player:dto.getPlayers()){
			if(player.getAccount().getId().equals(this.operator)){
				pOperator=player;
			}
		}
		
		//将操作者设置为地球
		//1新建一个地球
		Earth earth=new Earth();		
		//2得到三体玩家的科技值与资源值
		int techPoint=pOperator.getTechPoint();
		int resource=pOperator.getResource();
		//3.将操作者变为地球玩家
		pOperator.setRole(earth);
		//4.将科技值与资源值赋给新地球(待定，取决于变成地球后会不会被初始化)
		pOperator.setResource(resource);
		pOperator.setTechPoint(techPoint);
		
		Character_Change cc=new Character_Change(operator, receiver);
		dto.depositOperation(cc);
		
	}
	
	public String toOperator(){
		
		return this.operator+"从三体转化为地球";
	}
	
	public String toReceiver(){
		
		return null;
	}
	
	public String toOthers(){
		
		return null;
	}
}
