package model.role;

import java.util.List;

import config.GameConfig;
import config.RoleConfig;
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
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;

	public Unifier() {
		super();
		// 原80，100，0,30,测试用改成100，100,0,30
		
		GameConfig gc=new GameConfig();
		List<RoleConfig> roleList=gc.getRolesConfig();
		this.initialResource=roleList.get(1).getInitialResource();
		this.initialTechPoint=roleList.get(1).getInitialTechPoint();
		this.tchDevelopSpeed=roleList.get(1).getTchDevelopSpeed();
		this.rsrRestoreSpeed=roleList.get(1).getRsrRestoreSpeed();
	}

	@Override
	public boolean isAvailable(Card card) {
		if(card.getClass().equals(ResourcePotion.class)){
			return false;
		}else{
			return true;
		}
	}
	@Override
	public String toString(){
		return "归一者";
	}

}
