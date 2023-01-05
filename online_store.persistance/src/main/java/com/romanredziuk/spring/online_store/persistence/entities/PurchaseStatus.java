package com.romanredziuk.spring.online_store.persistence.entities;

public interface PurchaseStatus {

	void setId(Integer id);

	void setStatusName(String statusName);

	Integer getId();

	String getStatusName();

}
