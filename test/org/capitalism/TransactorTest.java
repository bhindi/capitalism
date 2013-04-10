package org.capitalism;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class TransactorTest {

	@Test
	public void testDeduct() {
		ITransactor man = new Human();
		BigDecimal worth = man.getMonetaryWorth();
		man.deductTransactionPayment(new BigDecimal(4));
		
		Assert.assertEquals( worth.subtract(new BigDecimal(4)), man.getMonetaryWorth());
	}
	
	@Test
	public void testIncrement() {
		ITransactor man = new Human();
		BigDecimal worth = man.getMonetaryWorth();
		Money add = Money.MoneySupply.getMoney(new BigDecimal (5));
		man.depositTransactionPayment(add);
		
		Assert.assertEquals( worth.add(new BigDecimal(5)), man.getMonetaryWorth());
	}

}
