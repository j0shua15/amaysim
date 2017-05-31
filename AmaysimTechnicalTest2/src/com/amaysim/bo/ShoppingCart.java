package com.amaysim.bo;

import java.util.ArrayList;
import java.util.List;

import com.amaysim.bo.promos.IPromo;

public class ShoppingCart {
	private List<Product> items;
	private double total;

	public ShoppingCart() {
		items = new ArrayList();
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Product> getItems() {
		return items;
	}

	public void setItems(List<Product> items) {
		this.items = items;
	}

	public void addItem(Product product) {
		items.add(product);
	}

	public void calculateTotal() {
		for (Product product : items) {
			total += product.getPrice();
		}
	}

	public <P extends IPromo> void addPromo(P promo) {
		total = promo.applyPromo(total);
	}

}
