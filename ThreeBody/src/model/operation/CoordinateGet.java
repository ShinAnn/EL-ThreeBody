package model.operation;

public class CoordinateGet extends Operation {
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	private int number;
	private int value;

	public CoordinateGet(String operator, String receiver,int number,int value) {
		super(operator, receiver);
		this.number = number;
		this.value = value;
	}

	@Override
	public String toOperator() {
		return this.operator+" 已获得 "+this.receiver+" 第"+(number+1)+" 个坐标：꣺"+value;
	}

	@Override
	public String toReceiver() {
		return null;
	}

	@Override
	public String toOthers() {
		return null;
	}

}
