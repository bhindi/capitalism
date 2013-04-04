package org.capitalism;

public class Product implements IConsumable {

	double value;
	
	public Product(Money money) {

		value = money.getValue();
	}

	@Override
	public int getId() {
		return this.hashCode();
	}
}
