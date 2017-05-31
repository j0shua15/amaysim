package com.amaysim.bd;

import java.util.ArrayList;
import java.util.List;

import com.amaysim.bo.ShoppingCart;
import com.amaysim.bo.offers.ISpecialOffer;

public class SpecialOfferBD {

	private List<ISpecialOffer> specialOffers;

	public SpecialOfferBD() {
		specialOffers = new ArrayList();
	}

	public void applyOffers(ShoppingCart cart) {
		for(ISpecialOffer so : specialOffers){
			if (so.isApplicable(cart)){
				so.applyOffer(cart);
			}
		}		
	}
	
	public <T extends ISpecialOffer> void addOffer(T specialOffer){
		specialOffers.add(specialOffer);
	}
	
	public List<ISpecialOffer> getSpecialOffers() {
		return specialOffers;
	}

	public void setSpecialOffers(List<ISpecialOffer> specialOffers) {
		this.specialOffers = specialOffers;
	}
}
