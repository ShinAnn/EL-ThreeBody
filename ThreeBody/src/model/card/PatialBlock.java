package model.card;

import java.util.List;

import config.CardConfig;
import config.GameConfig;
import model.Coordinate;
import model.Player;
import model.operation.Operation;
import model.operation.ResourceChange;
import model.operation.ResourceChange.Type;
import dto.GameDTO;

public class PatialBlock extends Card{
	
	/*
	 * 首先要知道玩家想要保护第几个坐标
	 */

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	private  int position;
	
	public PatialBlock(String operator, String receiver,int position)  {
		super(operator, receiver);
		this.position=position;	
		this.name="局部黑域";
		GameConfig gc=new GameConfig();
		List<CardConfig> cardList=gc.getCardsConfig();
		this.lifetime=cardList.get(1).getLifetime();
		this.requiredResource=cardList.get(1).getRequiredResource();
		this.requiredTechPoint=cardList.get(1).getRequiredTechPoint();
	}

	@Override
	public List<Operation> process(List<Operation> subOperations) {
		GameDTO dto = GameDTO.getInstance();
		
		//get operator == receiver
		Player pOperator = this.findOperator(dto);
		
		//pay the resources
		ResourceChange rc = new ResourceChange(operator, receiver, Type.DECREASE, this.requiredResource);
		subOperations.add(rc);
		
		//set the coordinate according to position
		pOperator.getCoordinate().setProtected(position, true);
		
		return subOperations;
	}
	

}
