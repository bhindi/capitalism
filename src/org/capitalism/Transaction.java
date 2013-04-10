package org.capitalism;

import java.math.BigDecimal;

public class Transaction {

	private boolean transactionProcessed;
	
	public Transaction(ITransactor buyer, ITransactor seller,
			TransactionAgreement agreement) {
		
		transactionProcessed = false;
		
		if(agreement.transactionApproved) {
			
			Money deduction = buyer.deductTransactionPayment(agreement.terms.transactionValue);
			
			if(deduction.getValue().compareTo(new BigDecimal(0)) >0) {
				seller.depositTransactionPayment(deduction);
				buyer.takeConsumableFromTransaction(seller.provideConsumableForTransaction(agreement.terms.consumableId));
				transactionProcessed = true;
			}					
		}	
		
	}
	
	public boolean getProcessed() {
		return transactionProcessed;
	}

}
