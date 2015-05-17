package model.operation;

import java.util.LinkedList;
import java.util.List;

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

	@Override
	public String toOperator() {
		return this.operator + "使用了" + card.getName();
	}

	@Override
	public String toReceiver() {
		return null;
	}

	@Override
	public String toOthers() {
		return null;
	}

	@Override
	public List<Operation> process() {
		List<Operation> subOperations = new LinkedList<Operation>();
		return card.process(subOperations);
	}

}
