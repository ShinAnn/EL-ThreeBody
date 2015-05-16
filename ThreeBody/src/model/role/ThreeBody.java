package model.role;

import model.card.Card;

/*
 * 角色：三体
 * 目标： 获得地球的坐标，侵占地球，侵占后坐标仍是原来的坐标，但是其他属性会变为地球类，
 *		      此时的敌对方为其他的三体以及归一者
 * 科技值：初始值：中（高于地球，低于三体） 增长率：较慢
 * 资源值：初始值：最少	增长率：与地球归一者相同
 * 技能限制：无限制
 * 广播：只能获得坐标，可以发送（结果是侵占，玩家点击确认发送）
 * 
 */
public class ThreeBody extends Role{

	public ThreeBody() {
		super();
		this.initialResource=10;
		this.initialTechPoint=30;
		this.tchDevelopSpeed=10;
		this.rsrRestoreSpeed=10;
	}
	public  boolean isAvailable(Card card){
		return true;
	}


}
