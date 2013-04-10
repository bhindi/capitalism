package org.capitalism;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface ITransactor {

	ArrayList<Product> getItemsForSale();

	BigDecimal getMonetaryWorth();

	void depositTransactionPayment(Money deduction);

	Money deductTransactionPayment(BigDecimal transactionValue);

	IConsumable provideConsumableForTransaction(int consumableId);

	void takeConsumableFromTransaction(IConsumable iConsumable);

	TransactionAgreement proposeTransaction(ITransactor seller,
			TransactionTerms transactionTerms);

	boolean acceptTransaction(TransactionTerms transactionTerms);

}
