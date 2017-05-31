package com.amaysim.service;

import java.util.List;

import com.amaysim.bo.Product;

public interface ProductService {
	List<Product> getProducts();
	boolean addProduct(String code, Product product); 
	boolean editProduct(String code, Product product); 
	boolean deleteProduct(String code);
	Product getProduct(String code);
	
}
