package org.capitalism;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Human implements ITransactor, IProducer {

	Money worth = Money.MoneySupply.getMoney(new BigDecimal(50));

	Producer consumableData = new Producer(new BigDecimal(
			new Random().nextDouble() * .1).multiply(worth.getValue()));
	
	Transactor transactor;

	public Human() {
		FiscalProfile fiscalProfile = new FiscalProfile(
				IProducer.ProfitType.NEUTRAL_PROFIT,
				IConsumer.SpenderType.NEUTRAL_SPENDER);
		transactor = new Transactor(fiscalProfile);
	}

	public Human(FiscalProfile fiscalProfile) {
		transactor = new Transactor(fiscalProfile);
	}

	@Override
	public boolean acceptTransaction(TransactionTerms terms) {

		return transactor.acceptTransaction(terms, consumableData);
	}

	private ITransactor chooseSeller(ArrayList<ITransactor> humans) {

		return transactor.chooseSeller(humans, this);
	}

	// TODO: This code is hard to follow
	private void consume(ITransactor seller, ITransactor buyer) {

		transactor.consume(seller, buyer);
	}

	public void createConsumable() {
		Money money = deductMoney(consumableData.getProductValue());
		consumableData.createConsumable(money);
	}

	@Override
	public Money deductTransactionPayment(BigDecimal transactionValue) {
		return deductMoney(transactionValue);
	}

	private Money deductMoney(BigDecimal moneyToDeduct) {
		return worth.split(moneyToDeduct);
	}

	@Override
	public ArrayList<Product> getItemsForSale() {

		// for now everything is on sale
		return consumableData.getProducts();
	}

	public int getNumConsumables() {

		return consumableData.getProducts().size();
	}

	@Override
	public BigDecimal getMonetaryWorth() {
		return worth.getValue();
	}

	@Override
	public void takeConsumableFromTransaction(IConsumable consumable) {
		consumableData.addConsumable(consumable);
	}

	@Override
	public void depositTransactionPayment(Money deduction) {
		worth.add(deduction);
	}

	public void live(ArrayList<ITransactor> nearbyHumans) {

		// the sole purpose of a human is to maximize their worth
		produce();
		consume(chooseSeller(nearbyHumans), this);

	}

	private void produce() {
		if (new Random().nextBoolean()) {
			createConsumable();
		}

	}

	@Override
	public TransactionAgreement proposeTransaction(ITransactor seller,
			TransactionTerms transactionTerms) {
		return transactor.proposeTransaction(seller, this, transactionTerms);
	}

	@Override
	public IConsumable provideConsumableForTransaction(int consumableId) {
		return consumableData.removeConsumable(consumableId);
	}

}
