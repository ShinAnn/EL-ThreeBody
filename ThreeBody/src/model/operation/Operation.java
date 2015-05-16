package model.operation;

import java.io.Serializable;

public abstract class Operation implements Serializable{
    
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
//    public enum Type{
//        CARD_USE,
//        TECH_CHANGE,
//        RSR_CHANGE,
//        BROCAST,
//        LOSE,
//        CHARACTER_CHANGE,
//        COORDINATE_GET,
//		  COORDINATE_GET_FAIL
//        PRIVILEGE_USE,
//        TURN_CHANGE
//    }

    protected String operator;
    protected String receiver;
    
	public String toOperator() {
		return null;
	}

	public String toReceiver() {
		return null;
	}

	public String toOthers() {
		return null;
	}
	
	public String getOperator(){
		return this.operator;
	}
	
    public String getReceiver() {
		return receiver;
	}

	public Operation(String operator,String receiver){
    	this.operator = operator;
    	this.receiver = receiver;
    }

}
