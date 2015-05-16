package model.role;

import model.card.Card;
import model.card.ResourcePotion;

/*
 * 角色：归一者
 * 目标：歼灭除了自身以外全部玩家
 * 科技值：初始值：满格  增长率：0
 * 资源：初始值：中 增长率：与地球三体都相同
 * 技能限制：无法使用获取身份的技能
 * 广播：同地球
*/
public class Unifier extends Role {
	
	public Unifier() {
		super();
		this.initialResource=15;
		this.initialTechPoint=80;
		this.tchDevelopSpeed=0;
		this.rsrRestoreSpeed=10;
	}

	@Override
	public boolean isAvailable(Card card) {
		if(card.getClass().equals(ResourcePotion.class)){
			return false;
		}else{
			return true;
		}
	}

}
