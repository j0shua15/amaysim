package com.amaysim.main;

import junit.framework.TestCase;

import com.amaysim.bd.SpecialOfferBD;
import com.amaysim.bo.Product;
import com.amaysim.bo.ShoppingCart;
import com.amaysim.bo.offers.BulkItemDiscountSO;
import com.amaysim.bo.offers.FreeItemSO;
import com.amaysim.bo.offers.ItemDealSO;
import com.amaysim.bo.promos.DiscountPromo;
import com.amaysim.service.impl.ProductServiceImpl;

public class AmaysimTechnicalExam2Test extends TestCase {

	ProductServiceImpl productService;
	SpecialOfferBD specialOfferBD;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		addProducts();
		addSpecialOffers();
	}

	public void addProducts() {
		productService = new ProductServiceImpl();
		productService.addProduct("ult_small", new Product("ult_small",
				"Unlimited 1GB", 24.90));
		productService.addProduct("ult_medium", new Product("ult_medium",
				"Unlimited 2GB", 29.90));
		productService.addProduct("ult_large", new Product("ult_large",
				"Unlimited 5GB", 44.90));
		productService.addProduct("1gb", new Product("1gb", "1 GB Data-pack",
				9.90));

		System.out.println("Available Products: ");
		for (Product prod : productService.getProducts()) {
			System.out.println(prod.toString());
		}
	}

	public void addSpecialOffers() {
		ItemDealSO _3For2Deal = new ItemDealSO(
				"3 for 2 deal on Unlimited 1GB Sims", 3,
				productService.getProduct("ult_small"));
		BulkItemDiscountSO _5GBBulkDiscount = new BulkItemDiscountSO(
				"Unlimited 5GB Sim Bulk Discount @39.90", 4, 39.90,
				productService.getProduct("ult_large"));
		FreeItemSO _1GBDataFree = new FreeItemSO(
				"1 GB Data-pack free for every Unlimited 2GB", 1,
				productService.getProduct("ult_medium"),
				productService.getProduct("1gb"));
		DiscountPromo iLoveAmaysim = new DiscountPromo("I<3AMAYSIM", 10);

		specialOfferBD = new SpecialOfferBD();
		specialOfferBD.addOffer(_3For2Deal);
		specialOfferBD.addOffer(_5GBBulkDiscount);
		specialOfferBD.addOffer(_1GBDataFree);
	}

	public void testScenario1() {
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_large"));
		
		System.out.println("Cart Items: ");
		for (Product p : cart.getItems()) {
			System.out.println(p.getName());
		}
		
		cart.calculateTotal();
		specialOfferBD.applyOffers(cart);
		
		assertEquals(94.70, cart.getTotal(), 0.01);
	}
	
	public void testScenario2() {
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_large"));
		cart.addItem(productService.getProduct("ult_large"));
		cart.addItem(productService.getProduct("ult_large"));
		cart.addItem(productService.getProduct("ult_large"));
		
		System.out.println("Cart Items: ");
		for (Product p : cart.getItems()) {
			System.out.println(p.getName());
		}
		
		cart.calculateTotal();
		specialOfferBD.applyOffers(cart);

		assertEquals(209.40, cart.getTotal(), 0.01);
	}
	
	public void testScenario3() {
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("ult_medium"));
		cart.addItem(productService.getProduct("ult_medium"));
		
		System.out.println("Cart Items: ");
		for (Product p : cart.getItems()) {
			System.out.println(p.getName());
		}
		
		cart.calculateTotal();
		specialOfferBD.applyOffers(cart);
		
		assertEquals(84.70, cart.getTotal(), 0.01);
	}
	
	public void testScenario4() {
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(productService.getProduct("ult_small"));
		cart.addItem(productService.getProduct("1gb"));
		
		System.out.println("Cart Items: ");
		for (Product p : cart.getItems()) {
			System.out.println(p.getName());
		}
		
		cart.calculateTotal();
		specialOfferBD.applyOffers(cart);
		DiscountPromo iLoveAmaysim = new DiscountPromo("I<3AMAYSIM", 10);
		cart.addPromo(iLoveAmaysim);
		
		assertEquals(31.32, cart.getTotal(), 0.01);
	}
}
