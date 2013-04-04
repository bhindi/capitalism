package org.capitalism;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class TransactorTest {

	@Test
	public void testDeduct() {
		ITransactor man = new Human();
		double worth = man.getWorth();
		man.deduct(4);
		
		Assert.assertEquals( worth - 4, man.getWorth());
	}
	
	@Test
	public void testIncrement() {
		ITransactor man = new Human();
		double worth = man.getWorth();
		Money add = Money.MoneySupply.getMoney(new BigDecimal(5));
		man.increment(add);
		
		Assert.assertEquals( worth + 5, man.getWorth());
	}

}
