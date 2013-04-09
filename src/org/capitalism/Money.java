package org.capitalism;

import java.math.BigDecimal;

public class Money {

	public static class MoneySupply {

		private static BigDecimal totalMoney = new BigDecimal(100000.0);

		public static Money getMoney(double amount) {

			totalMoney.setScale(1, BigDecimal.ROUND_HALF_UP);
			
			Money money = new Money(0.0);
			if (totalMoney.doubleValue() >= amount) {
				totalMoney = totalMoney.subtract(new BigDecimal(amount));
				money = new Money(amount);
			}
			return money;
		}

		public static double getTotalMoney() {
			return totalMoney.doubleValue();
		}

		public static void giveMoney(Money money) {
			totalMoney = totalMoney.add(new BigDecimal(money.getValue()));
			money.value = new BigDecimal(0.0);
		}

	}

	BigDecimal value;

	private Money(double amount) {
		value = new BigDecimal(amount);
	}
	
	private Money(BigDecimal amount) {
		value = amount;
	}	

	public double getValue() {

		return value.doubleValue();
	}

	public Money split(BigDecimal amount) {
		
		if (value.compareTo(amount) > 0) {
			
			value = value.subtract(amount);
			return new Money(amount);
		}
		return new Money(new BigDecimal(0.0));
	}

	public void add(Money deposit) {

		value = value.add(new BigDecimal(deposit.getValue()));
		deposit.value = new BigDecimal(0.0);
		
	}


}