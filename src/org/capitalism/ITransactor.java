package org.capitalism;

import java.util.ArrayList;

public interface ITransactor {

	Money deduct(double transactionValue);

	void increment(Money deduction);

	double getWorth();

	TransactionAgreement proposeTransaction(ITransactor seller,
			TransactionTerms transactionTerms);

	boolean acceptTransaction(TransactionTerms transactionTerms);

	ArrayList<Product> getItemsForSale();

	IConsumable remove(int consumableId);

	void give(IConsumable iConsumable);

}
