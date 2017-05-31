package com.amaysim.bo.promos;

import com.amaysim.bo.ShoppingCart;


public interface IPromo {
	public void applyPromo(ShoppingCart cart);
	public double applyPromo(double amount);
}
