package com.amaysim.service;

import junit.framework.TestCase;

import com.amaysim.bo.Product;
import com.amaysim.service.impl.ProductServiceImpl;


public class ProductServiceImplTest extends TestCase{

	
	public void addProducts() {
		ProductServiceImpl productService = new ProductServiceImpl();
		productService.addProduct(  "ult_small", new Product("ult_small", "Unlimited 1GB", 24.90));
		productService.addProduct( "ult_medium", new Product("ult_medium", "Unlimited 2GB", 29.90) );
		productService.addProduct(  "ult_large", new Product("ult_large", "Unlimited 5GB", 44.90) );
		productService.addProduct(        "1gb", new Product("1gb", "1 GB Data-pack", 9.90) );
		
		System.out.println("Available Products: ");
		for (Product prod : productService.getProducts()){
			System.out.println(prod.toString());
		}
	}
}
