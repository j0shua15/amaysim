package com.amaysim.bo.offers;

import com.amaysim.bo.ShoppingCart;

public interface ISpecialOffer {
	public boolean isApplicable(ShoppingCart cart);
	public void applyOffer(ShoppingCart cart);
}
