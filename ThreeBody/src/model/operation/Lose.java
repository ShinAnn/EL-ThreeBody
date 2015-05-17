package model.operation;

import java.util.List;

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
	public List<Operation> process() {
		player.setLost(true);
		
		return null;
	}
	
	public String toOperator(){
		return this.player.getAccount().getId()+"已阵亡！";
	}
	
	public String toReceiver(){
		return this.player.getAccount().getId()+"已阵亡！";
	}

	public String toOthers(){
		return this.player.getAccount().getId()+"已阵亡！";
	}
}
