package model.operation;

import dto.GameDTO;
import model.Coordinate;
import model.Player;

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
	public void process() {
		//如果某个坐标与广播的坐标相同
		//该坐标对应玩家就输了，通过从players中移除实现
		GameDTO dto = GameDTO.getInstance();
		for(Player player:dto.getPlayers()){
			if(player.getCoordinate().equals(coordinate)){
				dto.getPlayers().remove(player);
				Broadcast broadcast=new Broadcast(operator, receiver, coordinate);
				dto.depositOperation(broadcast);
			}
		}
		
	}
	
	public String toOperator(){
		
		return  this.operator+"发布了广播";
		
	}
	
	public String toReceiver (){
		
		return null;
	}
	
	public String toOthers(){
		
		return null;
	}

}
