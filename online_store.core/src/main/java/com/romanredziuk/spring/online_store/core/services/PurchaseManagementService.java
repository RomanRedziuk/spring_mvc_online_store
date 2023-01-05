package com.romanredziuk.spring.online_store.core.services;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.entities.Purchase;

public interface PurchaseManagementService {

	void addPurchase(Purchase purchase);

	List<Purchase> getPurchasesByUserId(int userId);
	
	List<Purchase> getPurchases();
}
