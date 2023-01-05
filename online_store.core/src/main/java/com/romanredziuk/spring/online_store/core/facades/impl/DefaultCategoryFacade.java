package com.romanredziuk.spring.online_store.core.facades.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.core.facades.CategoryFacade;
import com.romanredziuk.spring.online_store.persistence.dao.CategoryDao;
import com.romanredziuk.spring.online_store.persistence.dto.converters.CategoryDtoToCategoryConverter;
import com.romanredziuk.spring.online_store.persistence.entities.Category;

@Service
public class DefaultCategoryFacade implements CategoryFacade {
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private CategoryDtoToCategoryConverter categoryConverter;


	@Override
	public List<Category> getCategories() {
		return categoryConverter.convertCategoryDtosToCategories(categoryDao.getCategories());
	}

}
