package model.card;

import model.Coordinate;
import model.Player;
import model.operation.CoordinateGet;
import model.operation.CoordinateGetFail;
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
	 */
	public Sophon(String operator, String receiver,int position) {
		super(operator, receiver);
		// TODO 游戏平衡配置
		this.position = position;
	}

	@Override
	public void process() {
		
		GameDTO dto=GameDTO.getInstance();
		
		//得到操作者与被操作者
		Player pOperator=this.findOperator(dto);
		Player pReceiver=this.findReceiver(dto);
		
		//消耗相应的资源，通过放置新Operation来实现
		ResourceChange rc = new ResourceChange(operator, receiver, ResourceChange.Type.DECREASE, this.requiredResource);
		dto.depositOperation(rc);
		
		//执行获取坐标操作
		Coordinate coordinate = pReceiver.getCoordinate();
		int result = coordinate.getCoordinateElement(position);
		if(result == Coordinate.PROTECTED){
			CoordinateGetFail cgf = new CoordinateGetFail(operator,receiver);
			dto.depositOperation(cgf);
		}else{
			// 设定operator的已发现的坐标值
			pOperator.findCoordinate(pReceiver, position, result);
			CoordinateGet cg = new CoordinateGet(operator,receiver,position,result);
			dto.depositOperation(cg);
		}
	}

}
