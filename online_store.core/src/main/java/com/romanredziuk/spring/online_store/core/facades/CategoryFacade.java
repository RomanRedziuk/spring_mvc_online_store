package com.romanredziuk.spring.online_store.core.facades;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.entities.Category;

public interface CategoryFacade {

	List<Category> getCategories();

}
