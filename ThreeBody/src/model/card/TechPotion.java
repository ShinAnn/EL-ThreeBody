package model.card;

import model.operation.ResourceChange;
import model.operation.ResourceChange.Type;
import model.operation.TechChange;
import dto.GameDTO;

/*
 * 科技药水
 * 通过消耗资源来增长科技
 */
public class TechPotion extends Card{

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public TechPotion(String operator, String receiver) {
		super(operator, receiver);
	}

	@Override
	public void process() {
		GameDTO dto=GameDTO.getInstance();
	
		//pay  resources
		ResourceChange rc=new ResourceChange(operator, receiver, Type.DECREASE, this.requiredResource);
		dto.depositOperation(rc);
		//get techPoint
		TechChange tc=new TechChange(operator, receiver, TechChange.Type.INCREASE, this.requiredTechPoint);
		dto.depositOperation(tc);
		

	}

}
