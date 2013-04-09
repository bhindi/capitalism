package org.capitalism;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class TransactionTest {

	@Test
	public void test() {

		Human buyer = new Human();	
		Human seller = new Human();		
		
		seller.createConsumable();
		
		final BigDecimal totalMoney = Money.MoneySupply.getTotalMoney();
		final BigDecimal buyerWorth = buyer.getWorth();
		final BigDecimal sellerWorth = seller.getWorth();
		final int numSellerConsumables = seller.getNumConsumables();
		final int numBuyerConsumables = buyer.getNumConsumables();
	
		IConsumable product = seller.getItemsForSale().get(0);
		final int consumableId = product.getId();
		final double price = product.getPrice();
		
		TransactionTerms transactionTerms = new TransactionTerms(new BigDecimal(price*2), consumableId);
		TransactionAgreement agreement = buyer.proposeTransaction(seller, transactionTerms);
		
		new Transaction(buyer, seller, agreement);		

		final BigDecimal totalMoneyAfterTrans = Money.MoneySupply.getTotalMoney();
		final BigDecimal buyerPostWorth = buyer.getWorth();
		final BigDecimal sellerPostWorth = seller.getWorth();
		
		Assert.assertEquals(totalMoney, totalMoneyAfterTrans);
		Assert.assertEquals(buyerWorth.subtract(new BigDecimal(price*2)), buyerPostWorth);	
		Assert.assertEquals(sellerWorth.add(new BigDecimal(price*2)), sellerPostWorth);
		Assert.assertEquals(numSellerConsumables-1, seller.getNumConsumables());
		Assert.assertEquals(numBuyerConsumables+1, buyer.getNumConsumables());
		
	}
	
	@Test
	public void testInsufficientFundsTransaction() {

		
		Human buyer = new Human();
		Human seller = new Human();		

		seller.createConsumable();
				
		final BigDecimal buyerWorthPreTransaction = buyer.getWorth();
		final BigDecimal transactionValue = buyerWorthPreTransaction.add(new BigDecimal(5.0));
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
	
	@Test
	public void testUnderbid() {

		Human buyer = new Human();	
		Human seller = new Human();		
		
		seller.createConsumable();
		
		IConsumable product = seller.getItemsForSale().get(0);
		final int consumableId = product.getId();
		final double price = product.getPrice();
		
		TransactionTerms transactionTerms = new TransactionTerms(new BigDecimal(price*.1), consumableId);
		TransactionAgreement agreement = buyer.proposeTransaction(seller, transactionTerms);
		
		Transaction trans = new Transaction(buyer, seller, agreement);
		
		Assert.assertTrue(!trans.getProcessed());
	}	
	
}
