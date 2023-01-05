package com.romanredziuk.spring.online_store.persistence.dao;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.dto.PurchaseDto;


public interface PurchaseDao {
	
	void savePurchase(PurchaseDto order);

	List<PurchaseDto> getPurchasesByUserId(int userId);
	
	List<PurchaseDto> getPurchases();

	List<PurchaseDto> getNotCompletedPurchases(Integer lastFulfilmentStageId);

	PurchaseDto getPurchaseById(Integer purchaseId);

	void updatePurchase(PurchaseDto convertPurchaseToPurchaseDto);

}
