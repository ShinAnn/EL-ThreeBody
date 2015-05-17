package model.role;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import model.card.Card;

public abstract class Role implements Serializable{
    
    /**
	 * default
	 */
	
	private static final long serialVersionUID = 1L;
	protected int initialTechPoint;
    protected int initialResource;
    protected int tchDevelopSpeed;
    protected int rsrRestoreSpeed;
    
    /*
     * 判断这张牌是否能被Character所用
     */
    public abstract boolean isAvailable(Card card);
    
    /*
     * getters
     */
    public int getInitialTechPoint() {
        return this.initialTechPoint;
    }
    public int getInitialResource() {
        return this.initialResource;
    }
    public int getTchDevelopSpeed() {
		return tchDevelopSpeed;
	}
	public int getRsrRestoreSpeed() {
		return rsrRestoreSpeed;
	}

	/**
	 * 工厂方法，返回包含各个种类指定数目的Role数组
	 * @param earthNum 地球的数目
	 * @param tbNum 三体的数目
	 * @param uniNum 归一者的数目
	 * @return
	 */
	public static Role[] generateRoles(int earthNum, int tbNum, int uniNum){
		List<Role> roles = new LinkedList<Role>();
		for (int i = 0; i < earthNum; i++) {
			roles.add(new Earth());
		}
		for (int i = 0; i < tbNum; i++) {
			roles.add(new ThreeBody());
		}
		for (int i = 0; i < uniNum; i++) {
			roles.add(new Unifier());
		}
		return roles.toArray(new Role[0]);
	}
	
	@Override
	public String toString(){
		return this.getClass().getName();
	}

	@Override
	public boolean equals(Object arg0) {
		return this.toString().equals(arg0.getClass().getName());
	}
    
}
