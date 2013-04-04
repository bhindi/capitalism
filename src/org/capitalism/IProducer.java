package org.capitalism;

public interface IProducer {

	void createConsumable();

	TransactionAgreement proposeTransaction(ITransactor seller,
			TransactionTerms transactionTerms);

}
