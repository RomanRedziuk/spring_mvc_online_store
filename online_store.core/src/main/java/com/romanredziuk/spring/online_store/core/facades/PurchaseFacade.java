package com.romanredziuk.spring.online_store.core.facades;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.entities.Product;
import com.romanredziuk.spring.online_store.persistence.entities.Purchase;
import com.romanredziuk.spring.online_store.persistence.entities.User;

public interface PurchaseFacade {

	Integer LAST_STATUS_OF_ORDER_FULFILMENT_ID = 6;
	
	void createPurchase(User attribute, Product productId);

	List<Purchase> getNotCompletedPurchases();

	void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId);

}
