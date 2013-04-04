package org.capitalism;

import java.util.ArrayList;
import java.util.Random;

public class Human implements ITransactor, IProducer {

	Money worth = Money.MoneySupply.getMoney(50);
	ArrayList<Product> products = new ArrayList<Product>();
	double productEquity = 0;

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
	
	public double getEquity() {
		
		return worth.getValue() + productEquity;
	}
	
	@Override
	public void createConsumable() {

		Money money = deduct(5);
		Product p = new Product(money);
		products.add(p);
		productEquity += p.getValue();

	}

	public void live(ArrayList<Human> nearbyHumans) {

		// the sole purpose of a human is to maximize their worth
		produce();
		consume(chooseSeller(nearbyHumans));

	}

	private ITransactor chooseSeller(ArrayList<Human> humans) {

		if(humans.size() == 1) {
			return null;
		}
		
		int transactorIndex = new Random().nextInt(humans.size());
		Human human = humans.get(transactorIndex);
		if(human == this) {
			transactorIndex++;
			transactorIndex%= humans.size();
			human = humans.get(transactorIndex);
		}
		
		return human;
	}
	
//TODO: This code is hard to follow
	private void consume(ITransactor seller) {

		final int numItemsForSale = seller.getItemsForSale().size();

		if (numItemsForSale > 0) {

			//pick item to purchase
			IConsumable itemToPurchase = seller.getItemsForSale().get(
					new Random().nextInt(numItemsForSale));

			TransactionTerms terms = new TransactionTerms(5.0,
					itemToPurchase.getId());
			
			TransactionAgreement agreement = proposeTransaction(seller, terms);
			new Transaction(this, seller, agreement);
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
	public boolean acceptTransaction(TransactionTerms transactionTerms) {

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
				productEquity -= products.get(i).getValue();
				return products.remove(i);
			}
		}
		return null;
	}

	@Override
	public void give(IConsumable consumable) {

		if (consumable != null) {
			products.add((Product) consumable);
			productEquity += consumable.getValue();
		}

	}

	public int getNumConsumables() {

		return products.size();
	}

}
