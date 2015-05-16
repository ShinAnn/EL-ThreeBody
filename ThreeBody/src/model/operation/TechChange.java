package model.operation;

import model.Player;
import dto.GameDTO;

public class TechChange extends Operation implements Operable {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	public enum Type{
		INCREASE,
		DECREASE
	}

	private Type type;
	private int amount;
	
	public TechChange(String operator, String receiver,Type type,int amount) {
		super(operator, receiver);
		this.type=type;
		this.amount=amount;
	}

	@Override
	public void process() {
		GameDTO dto=GameDTO.getInstance();
		Player pReceiver=null;
		
		for(Player player:dto.getPlayers()){
			if(player.getAccount().getId().equals(this.receiver)){
				pReceiver=player;
			}
		}
		
		int change=0;
		switch(type){
		case INCREASE:
			change = this.amount;
		case DECREASE:
			change = -this.amount;
		}
		
		int nowTechPoint = pReceiver.getTechPoint();
		pReceiver.setTechPoint(nowTechPoint+change);
	}
	
}
