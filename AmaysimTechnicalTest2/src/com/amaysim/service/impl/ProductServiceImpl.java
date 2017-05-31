package com.amaysim.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amaysim.bo.Product;
import com.amaysim.service.ProductService;

public class ProductServiceImpl implements ProductService {

	private static Map<String, Product> products;

	public ProductServiceImpl() {
		products = new HashMap<String, Product>();
	}

	@Override
	public List<Product> getProducts() {
		return new ArrayList<Product>(products.values());
	}

	@Override
	public Product getProduct(String code) {
		if (!products.containsKey(code)) {
			System.out.println("Product does not exist.");
			return null;
		} else {
			return products.get(code);
		}
	}

	@Override
	public boolean addProduct(String code, Product product) {
		if (products.containsKey(product.getCode())) {
			System.out.println("Product already exists.");
			return false;
		} else {
			products.put(product.getCode(), product);
			return true;
		}

	}

	@Override
	public boolean editProduct(String code, Product product) {
		if (!products.containsKey(product.getCode())) {
			System.out.println("Product does not exist.");
			return false;
		} else {
			// will overwrite existing code
			products.put(product.getCode(), product); 
			return true;
		}
	}

	@Override
	public boolean deleteProduct(String code) {
		if (!products.containsKey(code)) {
			System.out.println("Product does not exist.");
			return false;
		} else {
			products.remove(code);
			return true;
		}
	}

}
