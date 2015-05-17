package model.card;

import java.util.List;

import config.CardConfig;
import config.GameConfig;
import model.Coordinate;
import model.Player;
import model.operation.CoordinateGet;
import model.operation.CoordinateGetFail;
import model.operation.Operation;
import model.operation.ResourceChange;
import dto.GameDTO;

/**
 * 
 * @author Sissel
 * 高级智子
 */
public class Sophon extends Card{

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 坐标的第几个
	 */
	private int position;

	/**
	 * 
	 * @param operator
	 * @param receiver
	 * @param position 想要获取的玩家的第几个坐标
	 * @throws Exception 
	 */
	public Sophon(String operator, String receiver,int position) {
		super(operator, receiver);
		// TODO 游戏平衡配置
		this.position = position;
		this.name="智子";
		GameConfig gc=new GameConfig();
		List<CardConfig> cardList=gc.getCardsConfig();
		this.lifetime=cardList.get(4).getLifetime();
		this.requiredResource=cardList.get(4).getRequiredResource();
		this.requiredTechPoint=cardList.get(4).getRequiredTechPoint();
	}

	@Override
	public List<Operation> process(List<Operation> subOperations) {
		
		GameDTO dto=GameDTO.getInstance();
		
		//得到操作者与被操作者
		Player pOperator = this.findOperator(dto);
		Player pReceiver = this.findReceiver(dto);
//		System.out.println(pOperator.getAccount().getId()+","+pReceiver.getAccount().getId());
		//消耗相应的资源，通过放置新Operation来实现
		ResourceChange rc = new ResourceChange(operator, receiver, ResourceChange.Type.DECREASE, this.requiredResource);
		subOperations.add(rc);
		
		//执行获取坐标操作
		Coordinate coordinate = pReceiver.getCoordinate();
		int result = coordinate.getCoordinateElement(position);
		if(result == Coordinate.PROTECTED){
			CoordinateGetFail cgf = new CoordinateGetFail(operator,receiver);
			subOperations.add(cgf);
		}else{
			// 设定operator的已发现的坐标值
//			System.out.println("111");
			pOperator.findCoordinate(pReceiver, position, result);
//			System.out.println(pReceiver.getAccount().getId());
			CoordinateGet cg = new CoordinateGet(operator,receiver,position,result);
//			System.out.println(receiver);
			subOperations.add(cg);
		}
		
		return subOperations;
	}
	
	


}
