package model.operation;

import java.util.List;

import model.Player;
import dto.GameDTO;

/**
 * 
 * @author Sissel
 * @author CTG
 * operator为资源改变的对象
 */
public class ResourceChange extends Operation implements Operable{
	
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
	 * @param operator 资源改变的对象
	 * @param receiver 没什么卵用
	 * @param type 增加或减少
	 * @param amount 量
	 */
	public ResourceChange(String operator,String receiver,Type type,int amount) {
		super(operator,receiver);
		this.type = type;
		this.amount = amount;
	}

	@Override
	public List<Operation> process() {
		GameDTO dto = GameDTO.getInstance();
		Player pOperator = null;
		// 找到对应的玩家
		for (Player player : dto.getPlayers()) {
			if(player.getAccount().getId().equals(this.operator)){
				pOperator = player;
			}
		}
		
		int change = 0;
		switch(type){
		case INCREASE:
			change = this.amount;
			break;
		case DECREASE:
			change = -this.amount;
			break;
		}
		
		int nowResource = pOperator.getResource();
		pOperator.setResource(nowResource + change);
		
		return null;
	}

	@Override
	public String toOperator() {
		switch(type){
		case INCREASE:
			return operator + "的资源增加了" + amount;
		case DECREASE:
			return operator + "的资源减少了" + amount;
		}
		return null;
	}
	
}
