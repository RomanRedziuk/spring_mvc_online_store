package com.romanredziuk.spring.online_store.core.services;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.entities.Product;

public interface ProductManagementService {

	List<Product> getProducts();

	Product getProductById(int id);
	
}
