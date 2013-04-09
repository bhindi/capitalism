package org.capitalism;

import java.math.BigDecimal;
import java.util.Random;

public class Product implements IConsumable {

	BigDecimal value;
	final BigDecimal priceMultiplier;
	
	public Product(Money money) {

		value = money.getValue();
		priceMultiplier = new BigDecimal(new Random().nextDouble() + 1);
		Money.MoneySupply.giveMoney(money);
	}

	@Override
	public int getId() {
		return this.hashCode();
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public BigDecimal getPrice() {
		return value.multiply(priceMultiplier);
	}
	
	
}
