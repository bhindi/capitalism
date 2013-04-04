package org.capitalism;

import junit.framework.Assert;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void test() {

		Human buyer = new Human();	
		Human seller = new Human();		
		
		seller.createConsumable();
		
		final double totalMoney = Money.MoneySupply.getTotalMoney();
		final double buyerEquity = buyer.getWorth();
		final double sellerEquity = seller.getWorth();
		final int numSellerConsumables = seller.getNumConsumables();
		final int numBuyerConsumables = buyer.getNumConsumables();
	
		int consumableId = seller.getItemsForSale().get(0).getId();
		
		TransactionTerms transactionTerms = new TransactionTerms(5.0, consumableId);
		TransactionAgreement agreement = buyer.proposeTransaction(seller, transactionTerms);
		
		new Transaction(buyer, seller, agreement);
		
		final double totalMoneyAfterTrans = Money.MoneySupply.getTotalMoney();
		final double buyerPostEquity = buyer.getWorth();
		final double sellerPostEquity = seller.getWorth();
		
		Assert.assertEquals(totalMoney, totalMoneyAfterTrans);
		Assert.assertEquals(buyerEquity-5, buyerPostEquity);	
		Assert.assertEquals(sellerEquity+5, sellerPostEquity);	
		Assert.assertEquals(numSellerConsumables-1, seller.getNumConsumables());
		Assert.assertEquals(numBuyerConsumables+1, buyer.getNumConsumables());
	}
	
	@Test
	public void testInsufficientFundsTransaction() {

		
		Human buyer = new Human();
		Human seller = new Human();		

		seller.createConsumable();
				
		final double buyerWorthPreTransaction = buyer.getWorth();
		final double transactionValue = buyerWorthPreTransaction + 5.0;
		final int numSellerConsumables = seller.getNumConsumables();
		final int numBuyerConsumables = buyer.getNumConsumables();
		
		int consumableId = seller.getItemsForSale().get(0).getId();
		
		TransactionTerms transactionTerms = new TransactionTerms(transactionValue, consumableId);
		TransactionAgreement agreement = buyer.proposeTransaction(seller, transactionTerms);
		
		new Transaction(buyer, seller, agreement);

		
		Assert.assertEquals(buyerWorthPreTransaction, buyer.getWorth());	
		Assert.assertEquals(numSellerConsumables, seller.getNumConsumables());
		Assert.assertEquals(numBuyerConsumables, buyer.getNumConsumables());
	}
	
}
