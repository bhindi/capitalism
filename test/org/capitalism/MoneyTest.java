package org.capitalism;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class MoneyTest {

	@Test
	public void testMoneyIsUnique() {
		
		Money bankBalance = Money.MoneySupply.getMoney(3);
		Money deposit = Money.MoneySupply.getMoney(3);
		
		Assert.assertNotSame(bankBalance, deposit);
	}
	
	@Test
	public void testTotalMoneyIsFixed() {
	
		double totalMoney = Money.MoneySupply.getTotalMoney();
		Money bankBalance = Money.MoneySupply.getMoney(3);
		double totalMoneyAfterWithdrawal = Money.MoneySupply.getTotalMoney();
		
		Assert.assertEquals(totalMoney, bankBalance.getValue() + totalMoneyAfterWithdrawal);
		
		Money.MoneySupply.giveMoney(bankBalance);
		double totalMoneyAfterDeposit = Money.MoneySupply.getTotalMoney();
		Assert.assertEquals(totalMoney, totalMoneyAfterDeposit);
		Assert.assertEquals(bankBalance.getValue(), 0.0);
		
	}
	
	@Test
	public void testMoneyCannotBeDuplicated() {
	
		Money bankBalance = Money.MoneySupply.getMoney(3);
		Money cheat = bankBalance;
		
		Assert.assertEquals(cheat.getValue(), bankBalance.getValue());
		Assert.assertEquals(cheat, bankBalance);
		
	}
	
	@Test
	public void testSplitMoney() {
	
		Money bankBalance = Money.MoneySupply.getMoney(3);
		Money withdrawal = bankBalance.split(new BigDecimal(1.0));
		
		Assert.assertEquals(1.0, withdrawal.getValue());
		Assert.assertEquals(2.0, bankBalance.getValue());

	}	
	
	@Test
	public void testSplitInsufficientMoney() {
	
		Money bankBalance = Money.MoneySupply.getMoney(3);
		Money withdrawal = bankBalance.split(new BigDecimal(6.0));
		
		Assert.assertEquals(0.0, withdrawal.getValue());
		Assert.assertEquals(3.0, bankBalance.getValue());

	}		
	
	@Test
	public void testAddMoney() {
	
		double totalMoney = Money.MoneySupply.getTotalMoney();
		
		Money bankBalance = Money.MoneySupply.getMoney(3);
		Money deposit = Money.MoneySupply.getMoney(6);
		bankBalance.add(deposit);

		double totalMoneyAfterWithdrawal = Money.MoneySupply.getTotalMoney();

		Assert.assertEquals(0.0, deposit.getValue());
		Assert.assertEquals(9.0, bankBalance.getValue());
		Assert.assertEquals(totalMoney, bankBalance.getValue() + totalMoneyAfterWithdrawal);

	}	
	
	@Test
	public void testMoneySupplyLimit() {

		double totalMoney = Money.MoneySupply.getTotalMoney();
	
		Assert.assertEquals(0.0, Money.MoneySupply.getMoney(totalMoney+1).getValue());
		
		
	}
}