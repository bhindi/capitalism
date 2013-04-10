package org.capitalism;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class ConsumableTest {

	@Test
	public void testWorthDecreases() {

		Human man = new Human();
		BigDecimal worth = man.getWorth();
		testConsumableCreation(man);
		Assert.assertTrue(man.getWorth().compareTo(worth) < 0);

	}

	private void testConsumableCreation(Human producer) {

		final BigDecimal moneySupplyBeforeConsumableCreation = Money.MoneySupply.getTotalMoney();

		producer.createConsumable();
		
		Assert.assertNotNull(moneySupplyBeforeConsumableCreation);
		Assert.assertNotNull(Money.MoneySupply.getTotalMoney());
		Assert.assertTrue(moneySupplyBeforeConsumableCreation.compareTo(Money.MoneySupply.getTotalMoney()) < 0 );
	}
}
