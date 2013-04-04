package org.capitalism;

import junit.framework.Assert;

import org.junit.Test;

public class ConsumableTest {

	@Test
	public void testMoneySupplyDoesNotDecrease() {

		Human man = new Human();
		double worth = man.getWorth();
		testConsumableCreation(man);
		Assert.assertTrue(man.getWorth() < worth);

	}

	private void testConsumableCreation(IProducer producer) {

		final double totalMoney = Money.MoneySupply.getTotalMoney();

		producer.createConsumable();
		
		Assert.assertEquals(totalMoney, Money.MoneySupply.getTotalMoney());
	}
}
