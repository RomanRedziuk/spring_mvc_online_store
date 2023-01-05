package com.romanredziuk.spring.online_store.persistence.dao;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.dto.CategoryDto;

public interface CategoryDao {

	CategoryDto getCategoryByCategoryId(int id);

	List<CategoryDto> getCategories();

}
