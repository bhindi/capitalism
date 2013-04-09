package org.capitalism;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

public class MoneyTest {

	@Test
	public void testMoneyIsUnique() {
		
		Money bankBalance = Money.MoneySupply.getMoney(new BigDecimal(3));
		Money deposit = Money.MoneySupply.getMoney(new BigDecimal(3));
		
		Assert.assertNotSame(bankBalance, deposit);
	}
	
	@Test
	public void testTotalMoneyIsFixed() {
	
		BigDecimal totalMoney = Money.MoneySupply.getTotalMoney();
		Money bankBalance = Money.MoneySupply.getMoney(new BigDecimal(3));
		BigDecimal totalMoneyAfterWithdrawal = Money.MoneySupply.getTotalMoney();
		
		Assert.assertEquals(totalMoney, bankBalance.getValue().add(totalMoneyAfterWithdrawal));
		
		Money.MoneySupply.giveMoney(bankBalance);
		BigDecimal totalMoneyAfterDeposit = Money.MoneySupply.getTotalMoney();
		Assert.assertEquals(totalMoney, totalMoneyAfterDeposit);
		Assert.assertEquals(bankBalance.getValue(), new BigDecimal(0.0));
		
	}
	
	@Test
	public void testMoneyCannotBeDuplicated() {
	
		Money bankBalance = Money.MoneySupply.getMoney(new BigDecimal(3));
		Money cheat = bankBalance;
		
		Assert.assertEquals(cheat.getValue(), bankBalance.getValue());
		Assert.assertEquals(cheat, bankBalance);
		
	}
	
	@Test
	public void testSplitMoney() {
	
		Money bankBalance = Money.MoneySupply.getMoney(new BigDecimal(3));
		Money withdrawal = bankBalance.split(new BigDecimal(1.0));
		
		Assert.assertEquals(new BigDecimal(1.0), withdrawal.getValue());
		Assert.assertEquals(new BigDecimal(2.0), bankBalance.getValue());

	}	
	
	@Test
	public void testSplitInsufficientMoney() {
	
		Money bankBalance = Money.MoneySupply.getMoney(new BigDecimal(3));
		Money withdrawal = bankBalance.split(new BigDecimal(6.0));
		
		Assert.assertEquals(new BigDecimal(0.0), withdrawal.getValue());
		Assert.assertEquals(new BigDecimal(3.0), bankBalance.getValue());

	}		
	
	@Test
	public void testAddMoney() {
	
		BigDecimal totalMoney = Money.MoneySupply.getTotalMoney();
		
		Money bankBalance = Money.MoneySupply.getMoney(new BigDecimal(3));
		Money deposit = Money.MoneySupply.getMoney(new BigDecimal(6));
		bankBalance.add(deposit);

		BigDecimal totalMoneyAfterWithdrawal = Money.MoneySupply.getTotalMoney();

		Assert.assertEquals(new BigDecimal(0.0), deposit.getValue());
		Assert.assertEquals(new BigDecimal(9.0), bankBalance.getValue());
		Assert.assertEquals(totalMoney, bankBalance.getValue().add(totalMoneyAfterWithdrawal));

	}	
	
	@Test
	public void testMoneySupplyLimit() {

		BigDecimal totalMoney = Money.MoneySupply.getTotalMoney();
	
		Assert.assertEquals(new BigDecimal(0.0), Money.MoneySupply.getMoney(totalMoney.add(new BigDecimal(1))).getValue());
		
		
	}
}