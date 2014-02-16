package org.capitalism;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Producer {
	private int numItemsSold;
	private int numItemsPurchased;
	private BigDecimal productValue;
	private ArrayList<Product> products;
	private BigDecimal productEquity;

	public Producer(BigDecimal productValue) {
		this.numItemsSold = 0;
		this.numItemsPurchased = 0;
		this.productValue = productValue;
		this.products = new ArrayList<Product>();
		this.productEquity = new BigDecimal(0);
	}

	public int getNumItemsSold() {
		return numItemsSold;
	}

	public void setNumItemsSold(int numItemsSold) {
		this.numItemsSold = numItemsSold;
	}

	public int getNumItemsPurchased() {
		return numItemsPurchased;
	}

	public void setNumItemsPurchased(int numItemsPurchased) {
		this.numItemsPurchased = numItemsPurchased;
	}

	public BigDecimal getProductValue() {
		return productValue;
	}

	public void setProductValue(BigDecimal productValue) {
		this.productValue = productValue;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public BigDecimal getProductEquity() {
		return productEquity;
	}

	public void setProductEquity(BigDecimal productEquity) {
		this.productEquity = productEquity;
	}

	public void addConsumable(IConsumable consumable) {
		products.add((Product) consumable);
		numItemsPurchased++;
		productEquity = productEquity.add(consumable.getValue());

	}

	public IConsumable removeConsumable(int consumableId) {

		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == consumableId) {
				productEquity = productEquity.subtract(products.get(i)
						.getValue());
				numItemsSold++;
				return products.remove(i);
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public IConsumable get(int consumableId) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getId() == consumableId) {
				return products.get(i);
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public void createConsumable(Money money) {
		Product product = new Product(money);
		products.add(product);
		productEquity = productEquity.add(
				product.getValue());
	}
}