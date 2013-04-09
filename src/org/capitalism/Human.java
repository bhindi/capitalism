package org.capitalism;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Human implements ITransactor, IProducer {

	Money worth = Money.MoneySupply.getMoney(new BigDecimal(50));
	ArrayList<Product> products = new ArrayList<Product>();
	BigDecimal productEquity = new BigDecimal(0);
	private final BigDecimal productValue = new BigDecimal(
			new Random().nextDouble() * .1).multiply(worth.getValue());
	private final FiscalProfile fiscalProfile;
	int numItemsSold = 0;
	int numItemsPurchased = 0;

	public Human(FiscalProfile fiscalProfile) {

		this.fiscalProfile = fiscalProfile;
	}

	public Human() {
		this.fiscalProfile = new FiscalProfile(
				IProducer.ProfitType.NEUTRAL_PROFIT,
				IConsumer.SpenderType.NEUTRAL_SPENDER);
	}

	@Override
	public Money deduct(BigDecimal transactionValue) {

		return worth.split(transactionValue);
	}

	@Override
	public void increment(Money deduction) {
		worth.add(deduction);
	}

	@Override
	public BigDecimal getWorth() {
		return worth.getValue();
	}

	public BigDecimal getEquity() {

		return worth.getValue().add(productEquity);
	}

	@Override
	public void createConsumable() {

		Money money = deduct(productValue);
		Product p = new Product(money);
		products.add(p);
		productEquity = productEquity.add(p.getValue());

	}

	public void live(ArrayList<Human> nearbyHumans) {

		// the sole purpose of a human is to maximize their worth
		produce();
		consume(chooseSeller(nearbyHumans));

	}

	private ITransactor chooseSeller(ArrayList<Human> humans) {

		if (humans.size() == 1) {
			return null;
		}

		int transactorIndex = new Random().nextInt(humans.size());
		Human human = humans.get(transactorIndex);
		if (human == this) {
			transactorIndex++;
			transactorIndex %= humans.size();
			human = humans.get(transactorIndex);
		}

		return human;
	}

	// TODO: This code is hard to follow
	private void consume(ITransactor seller) {

		final int numItemsForSale = seller.getItemsForSale().size();

		if (numItemsForSale > 0) {

			// pick item to purchase
			IConsumable itemToPurchase = seller.getItemsForSale().get(
					new Random().nextInt(numItemsForSale));

			BigDecimal offerPrice = calculateTransactionOfferPrice(itemToPurchase);

			TransactionTerms terms = new TransactionTerms(offerPrice,
					itemToPurchase.getId());

			TransactionAgreement agreement = proposeTransaction(seller, terms);
			new Transaction(this, seller, agreement);
		}
	}

	private BigDecimal calculateTransactionOfferPrice(IConsumable itemToPurchase) {

		BigDecimal itemPrice = itemToPurchase.getPrice();
		BigDecimal offerPrice = itemPrice.multiply(new BigDecimal(new Random().nextDouble())).multiply( new BigDecimal(
						(fiscalProfile.maxPurchaseOfferPercentage - fiscalProfile.minPurchaseOfferPercentage) + fiscalProfile.minPurchaseOfferPercentage));
		return offerPrice;
	}

	private void produce() {
		if (new Random().nextBoolean()) {
			createConsumable();
		}

	}

	@Override
	public TransactionAgreement proposeTransaction(ITransactor seller,
			TransactionTerms transactionTerms) {
		return new TransactionAgreement(this, seller, transactionTerms);
	}

	@Override
	public boolean acceptTransaction(TransactionTerms terms) {

		IConsumable consumable = get(terms.consumableId);
		if (consumable != null) {
			final BigDecimal consumableValue = consumable.getValue();
			if (terms.transactionValue.compareTo(consumableValue.multiply(new BigDecimal(
					fiscalProfile.minProfit))) >= 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<Product> getItemsForSale() {

		// for now everything is on sale
		return products;
	}

	@Override
	public IConsumable remove(int consumableId) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == consumableId) {
				productEquity = productEquity.subtract(products.get(i).getValue());
				numItemsSold++;
				return products.remove(i);
			}
		}
		return null;
	}

	public IConsumable get(int consumableId) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == consumableId) {
				return products.get(i);
			}
		}
		return null;
	}

	@Override
	public void give(IConsumable consumable) {

		if (consumable != null) {
			products.add((Product) consumable);
			numItemsPurchased++;
			productEquity = productEquity.add(consumable.getValue());
		}

	}

	public int getNumConsumables() {

		return products.size();
	}

}
