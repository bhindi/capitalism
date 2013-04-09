package org.capitalism;

import java.math.BigDecimal;

public class Money {

	public static class MoneySupply {

		private static BigDecimal totalMoney = new BigDecimal(100000.0);

		public static Money getMoney(BigDecimal amount) {

			totalMoney.setScale(1, BigDecimal.ROUND_HALF_UP);
			
			Money money = new Money(new BigDecimal(0.0));
			if (totalMoney.compareTo(amount) > 0) {
				totalMoney = totalMoney.subtract(amount);
				money = new Money(amount);
			}
			return money;
		}

		public static BigDecimal getTotalMoney() {
			return totalMoney;
		}

		public static void giveMoney(Money money) {
			totalMoney = totalMoney.add(money.getValue());
			money.value = new BigDecimal(0.0);
		}

	}

	BigDecimal value;
	
	private Money(BigDecimal amount) {
		value = amount;
	}	

	public BigDecimal getValue() {

		return value;
	}

	public Money split(BigDecimal amount) {
		
		if (value.compareTo(amount) > 0) {
			
			value = value.subtract(amount);
			return new Money(amount);
		}
		return new Money(new BigDecimal(0.0));
	}

	public void add(Money deposit) {

		value = value.add(deposit.getValue());
		deposit.value = new BigDecimal(0.0);
		
	}


}