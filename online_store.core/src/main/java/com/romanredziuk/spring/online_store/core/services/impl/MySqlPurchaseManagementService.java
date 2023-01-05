package com.romanredziuk.spring.online_store.core.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.core.services.PurchaseManagementService;
import com.romanredziuk.spring.online_store.persistence.dao.PurchaseDao;
import com.romanredziuk.spring.online_store.persistence.dto.PurchaseDto;
import com.romanredziuk.spring.online_store.persistence.dto.converters.PurchaseDtoToPurchaseConverter;
import com.romanredziuk.spring.online_store.persistence.entities.Purchase;

@Service
public class MySqlPurchaseManagementService implements PurchaseManagementService {
	
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private PurchaseDtoToPurchaseConverter purchaseConverter;
	
	@Override
	public void addPurchase(Purchase purchase) {
		purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
	}

	@Override
	public List<Purchase> getPurchasesByUserId(int userId) {
		List<PurchaseDto> purchasesDtos = purchaseDao.getPurchasesByUserId(userId);
		return purchaseConverter.convertPurchaseDtosToPurchases(purchasesDtos);
	}

	@Override
	public List<Purchase> getPurchases() {
		List<PurchaseDto> purchasesDtos = purchaseDao.getPurchases();
		return purchaseConverter.convertPurchaseDtosToPurchases(purchasesDtos);
	}

}
