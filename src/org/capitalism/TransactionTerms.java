package org.capitalism;

import java.math.BigDecimal;

public class TransactionTerms {

	public TransactionTerms(BigDecimal value, int consumableId) {

		this.transactionValue = value;
		this.consumableId = consumableId;
	}

	final public BigDecimal transactionValue;
	final public int consumableId;

}
