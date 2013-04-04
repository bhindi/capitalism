package org.capitalism;

public class Transaction {

	public Transaction(ITransactor buyer, ITransactor seller,
			TransactionAgreement agreement) {
		
		if(agreement.transactionApproved) {
			
			Money deduction = buyer.deduct(agreement.terms.transactionValue);
			
			if(deduction.getValue() > 0) {
				seller.increment(deduction);
				buyer.give(seller.get(agreement.terms.consumableId));
				
			}					
		}

	}

}
