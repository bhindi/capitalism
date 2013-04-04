package org.capitalism;

public class Money {

	static class MoneySupply {

		private static double totalMoney = 100000.0;

		public static Money getMoney(double amount) {

			Money money = new Money(0.0);
			if (totalMoney >= amount) {
				totalMoney -= amount;
				money = new Money(amount);
			}
			return money;
		}

		public static double getTotalMoney() {
			return totalMoney;
		}

	}

	double value;

	private Money(double amount) {
		value = amount;
	}

	public double getValue() {

		return value;
	}

	public Money split(double amount) {
		
		if (value >= amount) {
			
			double delta = value - amount;
			
			value = delta;
			return new Money(amount);
		}
		return new Money(0.0);
	}

	public void add(Money deposit) {

		value += deposit.getValue();
		deposit.value = 0.0;
		
	}


}