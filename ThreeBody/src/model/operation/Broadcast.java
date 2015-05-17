package model.operation;

import java.util.LinkedList;
import java.util.List;

import model.Coordinate;
import model.Player;
import dto.GameDTO;

public class Broadcast extends Operation implements Operable{

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	Coordinate coordinate;
	
	public Broadcast(String operator, String receiver,Coordinate coordinate) {
		super(operator, receiver);
		this.coordinate=coordinate;
	}

	@Override
	public List<Operation> process() {
		//如果某个坐标与广播的坐标相同
		//该坐标对应玩家就输了，通过从players中移除实现
		GameDTO dto = GameDTO.getInstance();
		List<Operation> subOperations = null;
		for(Player player:dto.getPlayers()){
			if(player.getCoordinate().equals(coordinate)){
				// TODO 未定
				Lose lose = new Lose(null,null,player);
				subOperations = new LinkedList<Operation>();
				subOperations.add(lose);
			}
		}
		return subOperations;
	}
	
	public String toOperator(){
		return this.operator+"发布了广播:"+coordinate.toString();
	}
	
	public String toReceiver (){
		return "有人发了广播:"+coordinate.toString();
	}
	
	public String toOthers(){
		return "有人发了广播:"+coordinate.toString();
	}

}
