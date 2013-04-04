package org.capitalism;

public class Product implements IConsumable {

	double value;
	
	public Product(Money money) {

		value = money.getValue();
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
	
	
}
