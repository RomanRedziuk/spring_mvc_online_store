package com.romanredziuk.spring.online_store.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.core.services.ProductManagementService;
import com.romanredziuk.spring.online_store.persistence.dao.ProductDao;
import com.romanredziuk.spring.online_store.persistence.dto.ProductDto;
import com.romanredziuk.spring.online_store.persistence.dto.converters.ProductDtoToProductConverter;
import com.romanredziuk.spring.online_store.persistence.entities.Product;

@Service
public class MySqlProductManagementService implements ProductManagementService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private ProductDtoToProductConverter productConverter;
	
	@Override
	public List<Product> getProducts() {
		List<ProductDto> productDtos = productDao.getProducts();
		return productConverter.convertProductDtosToProducts(productDtos);
	}

	@Override
	public Product getProductById(int id) {
		ProductDto productDto = productDao.getProductById(id);
		return productConverter.convertProductDtoToProduct(productDto);
	}

}
