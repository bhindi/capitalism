package org.capitalism;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class ConsumableTest {

	@Test
	public void testWorthDecreases() {

		Human man = new Human();
		double worth = man.getWorth();
		testConsumableCreation(man);
		Assert.assertTrue(man.getWorth() < worth);

	}

	private void testConsumableCreation(IProducer producer) {

		final BigDecimal moneySupplyBeforeConsumableCreation = Money.MoneySupply.getTotalMoney();

		producer.createConsumable();
		
		Assert.assertTrue(moneySupplyBeforeConsumableCreation.compareTo(Money.MoneySupply.getTotalMoney()) < 0);
	}
}
