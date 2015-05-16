package model.operation;

import java.util.List;

import model.Player;
import dto.GameDTO;

/**
 * 
 * @author Sissel
 * @author CTG
 *
 */
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
	
	/**
	 * 
	 * @param operator 科技改变的对象
	 * @param receiver 没什么卵用
	 * @param type 增加还是减少
	 * @param amount 量
	 */
	public TechChange(String operator, String receiver,Type type,int amount) {
		super(operator, receiver);
		this.type=type;
		this.amount=amount;
	}

	@Override
	public List<Operation> process() {
		GameDTO dto=GameDTO.getInstance();
		Player pOperator=null;
		
		for(Player player:dto.getPlayers()){
			if(player.getAccount().getId().equals(this.operator)){
				pOperator=player;
			}
		}
		
		int change=0;
		switch(type){
		case INCREASE:
			change = this.amount;
		case DECREASE:
			change = -this.amount;
		}
		
		int nowTechPoint = pOperator.getTechPoint();
		pOperator.setTechPoint(nowTechPoint+change);
		
		return null;
	}

	@Override
	public String toOperator() {
		switch(type){
		case INCREASE:
			return operator + "的科技增加了" + amount;
		case DECREASE:
			return operator + "的科技减少了" + amount;
		}
		return null;
	}
	
}
