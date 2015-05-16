package model.operation;

import dto.GameDTO;
import model.Player;

public class Lose extends Operation implements Operable{

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	Player player;
	
	public Lose(String operator, String receiver,Player player) {
		super(operator, receiver);
		this.player=player;
	}

	@Override
	public void process() {
		
		GameDTO dto=GameDTO.getInstance();
		
		dto.getPlayers().remove(player);
		player.setLost(true);
		
		Lose lose=new Lose(operator, receiver, player);
		dto.depositOperation(lose);
	}
	
	public String toOperator(){
		
		return this.player+"已阵亡！";
		
	}
	
	public String toReceiver(){
		
		return this.player+"已阵亡！";
	}

	public String toOthers(){
		
		return this.player+"已阵亡！";
	}
}
