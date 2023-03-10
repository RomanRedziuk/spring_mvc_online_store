package com.romanredziuk.spring.online_store.persistence.entities;

import java.io.Serializable;

public interface Product extends Serializable {

	int getId();
	
	void setId(int id);

	String getProductName();
	
	void setProductName(String productName);
	
	String getCategoryName();
	
	void setCategoryName(String categoryName);
	
	double getPrice();

	void setPrice(double price);

	String getImgName();

	void setImgName(String imgName);

	void setDescription(String description);

	String getDescription();

	void setGuid(String guid);

	String getGuid();
}
