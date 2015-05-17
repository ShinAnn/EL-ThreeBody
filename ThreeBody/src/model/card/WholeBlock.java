package model.card;

import java.util.List;

import config.CardConfig;
import config.GameConfig;
import model.Player;
import model.operation.Operation;
import model.operation.ResourceChange;
import model.operation.ResourceChange.Type;
import dto.GameDTO;

public class WholeBlock extends Card{
	
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	

	public WholeBlock(String operator, String receiver) {
		super(operator, receiver);
		
		GameConfig gc=new GameConfig();
		List<CardConfig> cardList=gc.getCardsConfig();
		this.lifetime = cardList.get(6).getLifetime();
		this.requiredResource=cardList.get(6).getRequiredResource();
		this.requiredTechPoint=cardList.get(6).getRequiredTechPoint();
		
		this.name = "全局黑域";
	}


	/*
	 * 将玩家的所有坐标全部设为PROTECTED
	 */

	@Override
	public List<Operation> process(List<Operation> subOperations) {
		
		GameDTO dto = GameDTO.getInstance();
		
		//get the operator, now operator==receiver
		Player pOperator = this.findOperator(dto);
		
		//pay the resources
		ResourceChange rc = new ResourceChange(operator, receiver, Type.DECREASE, this.requiredResource);
		subOperations.add(rc);
		
		//set the coordinate ==10086(PROTECTED)
		for (int i = 0; i < 4; i++) {
			pOperator.getCoordinate().setProtected(i, true);
		}
		
		return subOperations;
	}
	

}
