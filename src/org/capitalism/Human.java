package org.capitalism;

import java.util.ArrayList;
import java.util.Random;

public class Human implements ITransactor, IProducer {

	Money worth = Money.MoneySupply.getMoney(50);
	ArrayList<Product> products = new ArrayList<Product>();

	@Override
	public Money deduct(double transactionValue) {

		return worth.split(transactionValue);
	}

	@Override
	public void increment(Money deduction) {
		worth.add(deduction);
	}

	@Override
	public double getWorth() {
		return worth.getValue();
	}

	@Override
	public void createConsumable() {

		Money money = deduct(5);
		products.add(new Product(money));

	}

	public void live(ArrayList<Human> humans) {

		// the sole purpose of a human is to maximize their worth
		produce();
		consume(chooseSeller(humans));

	}

	private ITransactor chooseSeller(ArrayList<Human> humans) {

		int transactorIndex = new Random().nextInt(humans.size());
		return humans.get(transactorIndex);
	}
	
//TODO: This code is hard to follow
	private void consume(ITransactor seller) {

		final int numItems = seller.getItemsForSale().size();

		if (numItems > 0) {

			//pick item to purchase
			IConsumable itemToPurchase = seller.getItemsForSale().get(
					new Random().nextInt(numItems));

			TransactionTerms terms = new TransactionTerms(5.0,
					itemToPurchase.getId());
			
			TransactionAgreement agreement = proposeTransaction(seller, terms);

			if (agreement.transactionApproved) {
				
				new Transaction(this, seller, agreement);
			}
		}
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
	public boolean proposeTransaction(TransactionTerms transactionTerms) {

		// evaluate transaction terms
		// for now just say yes to everything
		return true;
	}

	@Override
	public ArrayList<Product> getItemsForSale() {

		// for now everything is on sale
		return products;
	}

	@Override
	public IConsumable get(int consumableId) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == consumableId) {
				return products.remove(i);
			}
		}
		return null;
	}

	@Override
	public void give(IConsumable consumable) {

		if (consumable != null) {
			products.add((Product) consumable);
		}

	}

	public int getNumConsumables() {

		return products.size();
	}

}
