package org.capitalism;

import java.util.Random;

public class Product implements IConsumable {

	double value;
	final double priceMultiplier;
	
	public Product(Money money) {

		value = money.getValue();
		priceMultiplier = new Random().nextDouble() + 1;
		Money.MoneySupply.giveMoney(money);
	}

	@Override
	public int getId() {
		return this.hashCode();
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public double getPrice() {
		return value*priceMultiplier;
	}
	
	
}
