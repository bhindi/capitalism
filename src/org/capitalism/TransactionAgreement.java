package org.capitalism;

public class TransactionAgreement {
	
	public final boolean transactionApproved;
	public final TransactionTerms terms;

	public TransactionAgreement(ITransactor proposer, ITransactor proposee,
			TransactionTerms transactionTerms) {

		transactionApproved = proposee.acceptTransaction(transactionTerms);
		terms = transactionTerms;
		
	}

}
