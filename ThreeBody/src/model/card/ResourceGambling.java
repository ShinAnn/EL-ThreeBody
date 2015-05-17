package model.card;

import java.util.List;
import java.util.Random;

import model.operation.Operation;
import model.operation.ResourceChange;
import model.operation.ResourceChange.Type;



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
		this.name = "赌博";
		this.requiredResource=requiredResource;
	}

	@Override
	public List<Operation> process(List<Operation> subOperations) {
		//pay resources
		ResourceChange rc=new ResourceChange(operator, receiver, Type.DECREASE, this.requiredResource);
		subOperations.add(rc);
		
		//get 0 or 2 in random
		int randNum=1;
		while(randNum==1){
			Random rand=new Random();
			randNum=rand.nextInt(3);
		}
		
		//get resources
		ResourceChange rc1=new ResourceChange(operator, receiver, Type.INCREASE, randNum*(this.requiredResource));
		subOperations.add(rc1);
		
		return subOperations;
	}

}
