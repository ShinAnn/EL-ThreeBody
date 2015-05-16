package model.card;

import java.util.Random;

import model.Player;
import model.operation.ResourceChange;
import model.operation.ResourceChange.Type;
import dto.GameDTO;



/*
 * 获取用户自行输入的资源数
 * 然后随机得到输入数的两倍资源或是得到的资源数为0
 */
public class ResourceGambling extends Card {

	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public ResourceGambling(String operator, String receiver, int requiredResource) {
		super(operator, receiver);
		this.requiredResource=requiredResource;
	}

	@Override
	public void process() {
		GameDTO dto=GameDTO.getInstance();
		
		//pay resources
		ResourceChange rc=new ResourceChange(operator, receiver, Type.DECREASE, this.requiredResource);
		dto.depositOperation(rc);
		
		//get 0 or 2 in random
		int randNum=1;
		while(randNum==1){
			Random rand=new Random();
			randNum=rand.nextInt(3);
		}
		
		//get resources
		ResourceChange rc1=new ResourceChange(operator, receiver, Type.INCREASE, randNum*(this.requiredResource));
		dto.depositOperation(rc1);
	}

}
