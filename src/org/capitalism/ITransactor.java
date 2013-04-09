package org.capitalism;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ITransactor {

	Money deduct(BigDecimal transactionValue);

	void increment(Money deduction);

	double getWorth();

	TransactionAgreement proposeTransaction(ITransactor seller,
			TransactionTerms transactionTerms);

	boolean acceptTransaction(TransactionTerms transactionTerms);

	ArrayList<Product> getItemsForSale();

	IConsumable remove(int consumableId);

	void give(IConsumable iConsumable);

}
