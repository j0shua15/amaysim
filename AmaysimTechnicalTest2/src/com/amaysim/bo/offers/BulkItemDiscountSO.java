package com.amaysim.bo.offers;

import java.util.Collections;

import com.amaysim.bo.Product;
import com.amaysim.bo.ShoppingCart;

public class BulkItemDiscountSO implements ISpecialOffer {

	private String name;
	private int criteria;
	private double discountedPrice;
	private Product product;
	private int productCount;

	public BulkItemDiscountSO(String name, int criteria,
			double discountedPrice, Product product) {
		super();
		this.name = name;
		this.criteria = criteria;
		this.discountedPrice = discountedPrice;
		this.product = product;
	}

	public int getCriteria() {
		return criteria;
	}

	public void setCriteria(int criteria) {
		this.criteria = criteria;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public boolean isApplicable(ShoppingCart cart) {
		productCount = Collections.frequency(cart.getItems(), product);
		return (productCount >= criteria);
	}

	@Override
	public void applyOffer(ShoppingCart cart) {
		System.out.println("Applying Special Offer: " + this.name);
		double newTotal = cart.getTotal() - 
				( (product.getPrice() - discountedPrice) * productCount);
		cart.setTotal(newTotal);
	}
}
