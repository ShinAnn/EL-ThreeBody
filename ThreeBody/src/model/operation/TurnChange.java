package model.operation;

import java.util.LinkedList;
import java.util.List;

import model.Player;
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
	public List<Operation> process() {
		GameDTO dto = GameDTO.getInstance();
		Player whoseTurn = null;
		List<Operation> subOperations = new LinkedList<Operation>();
		
		//1.得到回合数
		int bout = dto.getBout();
		dto.setBout(bout+1);
		
		//2.得到玩家的数量
		int playerNum = dto.getPlayers().size();

		//3.将whoseTurn设为从服务器端得到的当前玩家
		for(int i = bout % playerNum;;i++){
			whoseTurn = dto.getPlayers().get(i);
			// 如果p没输就轮到p，否则轮到下一个
			if(!whoseTurn.isLost()){
				dto.setWhoseTurn(whoseTurn);
				break;
			}
		}
		
		// 将现在的玩家的资源和科技改变
		String id = whoseTurn.getAccount().getId();
		subOperations.add(new ResourceChange(
				id, 
				null, 
				ResourceChange.Type.INCREASE, 
				whoseTurn.getRole().getRsrRestoreSpeed()));
		subOperations.add(new TechChange(
				id, 
				null, 
				TechChange.Type.INCREASE, 
				whoseTurn.getRole().getTchDevelopSpeed()));
		
		return subOperations;
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
