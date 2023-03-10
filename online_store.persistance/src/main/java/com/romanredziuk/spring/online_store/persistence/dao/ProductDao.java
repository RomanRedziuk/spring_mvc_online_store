package com.romanredziuk.spring.online_store.persistence.dao;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.dto.ProductDto;


public interface ProductDao {
	
	List<ProductDto> getProducts();

	ProductDto getProductById(int productId);

	List<ProductDto> getProductsLikeName(String searchQuery);

	List<ProductDto> getProductsByCategoryId(Integer id);

	List<ProductDto> getProductsByCategoryIdPaginationLimit(Integer categoryId, Integer page, Integer paginationLimit);

	Integer getProductCountForCategory(Integer categoryId);

	Integer getProductCountForSearch(String searchQuery);

	List<ProductDto> getProductsLikeNameForPageWithLimit(String searchQuery, Integer page, Integer paginationLimit);

	ProductDto getProductByGuid(String guid);
	
}
