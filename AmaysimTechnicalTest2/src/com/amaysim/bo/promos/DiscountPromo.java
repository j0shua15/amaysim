package com.amaysim.bo.promos;

import com.amaysim.bo.ShoppingCart;

public class DiscountPromo implements IPromo {

	private String name;
	private int percent;

	public DiscountPromo(String name, int percent) {
		super();
		this.name = name;
		this.percent = percent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}

	@Override
	public void applyPromo(ShoppingCart cart) {
		System.out.println("Applying Special Promo: " + this.name);
		double newTotal = cart.getTotal() * (100 - percent) / 100;
		cart.setTotal(newTotal);
	}
	
	public double applyPromo(double amount){
		System.out.println("Applying Special Promo: " + this.name);
		double newTotal = amount * (100 - percent) / 100;
		return newTotal;
	}

}
