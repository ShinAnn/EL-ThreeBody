package model.operation;

import dto.GameDTO;

public class TurnChange extends Operation implements Operable{

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public TurnChange(String operator, String receiver) {
		super(operator, receiver);
	}

	@Override
	public void process() {
		GameDTO dto=GameDTO.getInstance();
		
		//1.得到回合数
		int bout=dto.getBout();
		
		//2.得到玩家的数量
		int playerNum=dto.getPlayers().size();

		
		//3.将whoseTurn设为从服务器端得到的当前玩家
		dto.setWhoseTurn(dto.getPlayers().get(bout%playerNum));
		
		
		TurnChange tc=new TurnChange(operator, receiver);
		dto.depositOperation(tc);
	}
	
	public String toOperator(){
		
		return "本轮玩家是"+GameDTO.getInstance().getWhoseTurn();
	}
	
	public String toReceiver(){
		
		return "本轮玩家是"+GameDTO.getInstance().getWhoseTurn();
	}
	
	public String toOthers(){
		
		return "本轮玩家是"+GameDTO.getInstance().getWhoseTurn();
	}

}
