package org.capitalism;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

public class Transactor {

	FiscalProfile fiscalProfile;

	public Transactor(FiscalProfile fiscalProfile) {

		this.fiscalProfile = fiscalProfile;
	}

	public boolean acceptTransaction(TransactionTerms terms,
			final Producer consumableData) {

		IConsumable consumable = consumableData.get(terms.consumableId);
		final BigDecimal consumableValue = consumable.getValue();
		if (terms.transactionValue.compareTo(consumableValue
				.multiply(new BigDecimal(fiscalProfile.minProfit))) >= 0) {
			return true;
		}
		return false;

	}

	public BigDecimal calculateTranscationOfferPrice(IConsumable itemToPurchase) {

		BigDecimal itemPrice = itemToPurchase.getPrice();
		BigDecimal offerPrice = itemPrice
				.multiply(
						new BigDecimal(
								(fiscalProfile.maxPurchaseOfferPercentage - fiscalProfile.minPurchaseOfferPercentage)
										+ fiscalProfile.minPurchaseOfferPercentage));
		return offerPrice;
	}

	public ITransactor chooseSeller(ArrayList<ITransactor> transactors,
			ITransactor thisTransactor) {

		if (transactors.size() <= 1) {
			return null;
		}

		int transactorIndex = new Random().nextInt(transactors.size());
		ITransactor transactor = transactors.get(transactorIndex);
		if (transactor == thisTransactor) {
			transactorIndex++;
			transactorIndex %= transactors.size();
			transactor = transactors.get(transactorIndex);
		}

		return transactor;
	}

	public void consume(ITransactor seller, ITransactor buyer) {

		if (seller == null) {
			return;
		}

		final int numItemsForSale = seller.getItemsForSale().size();

		if (numItemsForSale > 0) {

			// pick item to purchase
			IConsumable itemToPurchase = seller.getItemsForSale().get(
					new Random().nextInt(numItemsForSale));

			BigDecimal offerPrice = calculateTranscationOfferPrice(itemToPurchase);

			TransactionTerms terms = new TransactionTerms(offerPrice,
					itemToPurchase.getId());

			TransactionAgreement agreement = proposeTransaction(seller, buyer, terms);
			new Transaction(buyer, seller, agreement);
		}
	}

	public TransactionAgreement proposeTransaction(ITransactor seller, ITransactor buyer,
			TransactionTerms transactionTerms) {

		return new TransactionAgreement(buyer, seller, transactionTerms);
	}

}
