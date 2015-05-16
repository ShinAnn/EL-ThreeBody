package model.card;

import model.operation.ResourceChange;
import model.operation.TechChange;
import dto.GameDTO;



/*
 *资源药水 
 *通过消耗科技来增长资源
 */
public class ResourcePotion extends Card {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public ResourcePotion(String operator, String receiver) {
		super(operator, receiver);
;
	}

	@Override
	public void process() {
		GameDTO dto=GameDTO.getInstance();
		
		//pay techPoint
		TechChange tc=new TechChange(operator, receiver, TechChange.Type.DECREASE, this.requiredTechPoint);
		dto.depositOperation(tc);
		//get resources
		ResourceChange rc=new ResourceChange(operator, receiver, ResourceChange.Type.INCREASE, this.requiredResource);
		dto.depositOperation(rc);
		
	}
	
}
