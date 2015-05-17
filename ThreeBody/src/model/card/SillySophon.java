package model.card;


import java.util.List;

import config.CardConfig;
import config.GameConfig;
import model.Coordinate;
import model.Player;
import model.operation.CoordinateGetFail;
import model.operation.Operation;
import model.operation.ResourceChange;
import dto.GameDTO;

public class SillySophon extends Card {

	/**
	* default
	*/
	private static final long serialVersionUID = 1L;
	
	private int position;
	
	public SillySophon(String operator, String receiver,int position)  {
		super(operator, receiver);
		this.position=position;
		
		GameConfig gc=new GameConfig();
		List<CardConfig> cardList=gc.getCardsConfig();
		this.lifetime=cardList.get(3).getLifetime();
		this.requiredResource=cardList.get(3).getRequiredResource();
		this.requiredTechPoint=cardList.get(3).getRequiredTechPoint();
	}

	@Override
	public List<Operation> process(List<Operation> subOperations) {
		GameDTO dto = GameDTO.getInstance();
		
		//得到操作者与被操作者
		Player pReceiver=this.findReceiver(dto);
	
		//消耗资源
		ResourceChange rc=new ResourceChange(operator,receiver,ResourceChange.Type.DECREASE,this.getResource());
		subOperations.add(rc);
		
		//执行获取坐标操作
		Coordinate coordinate=pReceiver.getCoordinate();
		int result=coordinate.getCoordinateElement(position);
		if(result==Coordinate.UNKNOWN){
			CoordinateGetFail cgf=new CoordinateGetFail(operator, receiver);
			subOperations.add(cgf);
		}else{
		//TODO
		//获取玩家点击的答案
		//将玩家点击的答案与result比对,判断是否正确
		//如果正确，则同于sophon
		//如果不正确，则同于CoordinateGetFail
		}
		return subOperations;
	}
	
}
