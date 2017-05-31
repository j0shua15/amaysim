package com.amaysim.bo.offers;

import java.util.Collections;

import com.amaysim.bo.Product;
import com.amaysim.bo.ShoppingCart;

public class FreeItemSO implements ISpecialOffer {

	private String name;
	private int criteria;
	private Product product;
	private int productCount;
	private Product freeProduct;

	public FreeItemSO(String name, int criteria, Product product,
			Product freeProduct) {
		super();
		this.name = name;
		this.criteria = criteria;
		this.product = product;
		this.freeProduct = freeProduct;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		for (int i = 0; i < productCount; i++) {
			cart.addItem(freeProduct);
		}
	}

}
