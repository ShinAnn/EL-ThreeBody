package model.operation;

import model.card.Card;

public class CardUse extends Operation implements Operable{
	
	/**
	 * default
	 */
	private static final long serialVersionUID = 1L;
	
	private Card card;
	
	public CardUse(String operator,String receiver,Card card) {
		super(operator, receiver);
		this.card = card;
	}

	/*
	 * (non-Javadoc)
	 * @see model.operation.Visible#toOperator()
	 * 对操作者可见
	 */
	@Override
	public String toOperator() {
		return this.operator + "使用了" + card.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see model.operation.Visible#toReceiver()
	 * 对被使用者不可见
	 */
	@Override
	public String toReceiver() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see model.operation.Visible#toOthers()
	 * 对其他人不可见
	 */
	@Override
	public String toOthers() {
		return null;
	}

	@Override
	public void process() {
		card.process();
	}

}
