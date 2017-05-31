package com.amaysim.main;

import java.text.DecimalFormat;

import com.amaysim.bd.SpecialOfferBD;
import com.amaysim.bo.Product;
import com.amaysim.bo.ShoppingCart;
import com.amaysim.bo.offers.BulkItemDiscountSO;
import com.amaysim.bo.offers.FreeItemSO;
import com.amaysim.bo.offers.ItemDealSO;
import com.amaysim.bo.promos.DiscountPromo;
import com.amaysim.service.impl.ProductServiceImpl;

public class MainProgram {
	
	private static final DecimalFormat sf = new DecimalFormat("#.##");
	
	public static void main(String[] args) {
		
		ProductServiceImpl productService = new ProductServiceImpl();
		productService.addProduct(  "ult_small", new Product("ult_small", "Unlimited 1GB", 24.90f) );
		productService.addProduct( "ult_medium", new Product("ult_medium", "Unlimited 2GB", 29.90f) );
		productService.addProduct(  "ult_large", new Product("ult_large", "Unlimited 5GB", 44.90f) );
		productService.addProduct(        "1gb", new Product("1gb", "1 GB Data-pack", 9.90f) );
		
		System.out.println("Available Products: ");
		for (Product prod : productService.getProducts()){
			System.out.println(prod.toString());
		}
		
		ItemDealSO _3For2Deal = new ItemDealSO("3 for 2 deal on Unlimited 1GB Sims", 3, productService.getProduct("ult_small"));
		BulkItemDiscountSO _5GBBulkDiscount = new BulkItemDiscountSO("Unlimited 5GB Sim Bulk Discount @39.90", 4, 39.90, productService.getProduct("ult_large"));
		FreeItemSO _1GBDataFree = new FreeItemSO("1 GB Data-pack free for every Unlimited 2GB" , 1 , productService.getProduct("ult_medium"), productService.getProduct("1gb"));	
		
		SpecialOfferBD specialOfferBD = new SpecialOfferBD();
		specialOfferBD.addOffer(_3For2Deal);
		specialOfferBD.addOffer(_5GBBulkDiscount);
		specialOfferBD.addOffer(_1GBDataFree);

		System.out.println("=== Scenario 1 ===");
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_large"));
		cart.calculateTotal();
		specialOfferBD.applyOffers(cart);	
		System.out.println("Cart Total: " + Double.valueOf( sf.format(cart.getTotal()) ) );
		System.out.println("Cart Items: ");
		for (Product p : cart.getItems()) {
			System.out.println(p.getName());
		}
		
		System.out.println("=== Scenario 2 ===");
		ShoppingCart cart2 = new ShoppingCart();
		cart2.addItem(productService.getProduct("ult_small"));
		cart2.addItem(productService.getProduct("ult_small"));
		cart2.addItem(productService.getProduct("ult_large"));
		cart2.addItem(productService.getProduct("ult_large"));
		cart2.addItem(productService.getProduct("ult_large"));
		cart2.addItem(productService.getProduct("ult_large"));
		cart2.calculateTotal();
		specialOfferBD.applyOffers(cart2);
		System.out.println("Cart Total: " + Double.valueOf( sf.format(cart2.getTotal()) ) );
		System.out.println("Cart Items: ");
		for (Product p : cart2.getItems()) {
			System.out.println(p.getName());
		}
		
		System.out.println("=== Scenario 3 ===");
		ShoppingCart cart3 = new ShoppingCart();
		cart3.addItem(productService.getProduct("ult_small"));
		cart3.addItem(productService.getProduct("ult_medium"));
		cart3.addItem(productService.getProduct("ult_medium"));
		cart3.calculateTotal();
		specialOfferBD.applyOffers(cart3);
		System.out.println("Cart Total: " + Double.valueOf( sf.format(cart3.getTotal()) ) );
		System.out.println("Cart Items: ");
		for (Product p : cart3.getItems()) {
			System.out.println(p.getName());
		}
		
		System.out.println("=== Scenario 4 ===");
		ShoppingCart cart4 = new ShoppingCart();
		cart4.addItem(productService.getProduct("ult_small"));
		cart4.addItem(productService.getProduct("1gb"));
		cart4.calculateTotal();
		specialOfferBD.applyOffers(cart4);
		DiscountPromo iLoveAmaysim = new DiscountPromo("I<3AMAYSIM", 10);
		cart4.addPromo(iLoveAmaysim);
		System.out.println("Cart Total: " + Double.valueOf( sf.format(cart4.getTotal()) ) );
		System.out.println("Cart Items: ");
		for (Product p : cart4.getItems()) {
			System.out.println(p.getName());
		}
	}
}
