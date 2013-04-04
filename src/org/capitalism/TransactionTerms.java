package org.capitalism;

public class TransactionTerms {

	public TransactionTerms(double value, int consumableId) {

		this.transactionValue = value;
		this.consumableId = consumableId;
	}

	final public double transactionValue;
	final public int consumableId;

}
