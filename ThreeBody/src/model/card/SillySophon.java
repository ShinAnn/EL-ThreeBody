package model.card;


import model.Coordinate;
import model.Player;
import model.operation.CoordinateGet;
import model.operation.CoordinateGetFail;
import model.operation.ResourceChange;
import dto.GameDTO;

public class SillySophon extends Card {

	/**
	* default
	*/
	private static final long serialVersionUID = 1L;
	
	private int position;
	
	public SillySophon(String operator, String receiver,int position) {
		super(operator, receiver);
		this.position=position;
	}

	@Override
	public void process() {
		GameDTO dto=GameDTO.getInstance();
		
		//得到操作者与被操作者
		Player pOperator=this.findOperator(dto);
		Player pReceiver=this.findReceiver(dto);
	
		//消耗资源
		ResourceChange rc=new ResourceChange(operator,receiver,ResourceChange.Type.DECREASE,this.getResource());
		dto.depositOperation(rc);
		
		//执行获取坐标操作
		Coordinate coordinate=pReceiver.getCoordinate();
		int result=coordinate.getCoordinateElement(position);
		if(result==Coordinate.UNKNOWN){
			CoordinateGetFail cgf=new CoordinateGetFail(operator, receiver);
			dto.depositOperation(cgf);
		}else{
		//TODO
		//获取玩家点击的答案
		//将玩家点击的答案与result比对,判断是否正确
		//如果正确，则同于sophon
		//如果不正确，则同于CoordinateGetFail
		}
	}
}
