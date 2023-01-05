package com.romanredziuk.spring.online_store.core.facades.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.core.facades.PurchaseFacade;
import com.romanredziuk.spring.online_store.core.facades.UserFacade;
import com.romanredziuk.spring.online_store.persistence.dao.PurchaseDao;
import com.romanredziuk.spring.online_store.persistence.dto.converters.PurchaseDtoToPurchaseConverter;
import com.romanredziuk.spring.online_store.persistence.entities.Product;
import com.romanredziuk.spring.online_store.persistence.entities.Purchase;
import com.romanredziuk.spring.online_store.persistence.entities.PurchaseStatus;
import com.romanredziuk.spring.online_store.persistence.entities.User;
import com.romanredziuk.spring.online_store.persistence.entities.impl.DefaultPurchase;
import com.romanredziuk.spring.online_store.persistence.entities.impl.DefaultPurchaseStatus;

@Service
public class DefaultPurchaseFacade implements PurchaseFacade {
	
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private PurchaseDtoToPurchaseConverter purchaseConverter;
	
	@Autowired
	private UserFacade userFacade;
	
	@Value("${referrer.reward.rate}")
	private Double reffererRewardRate;
	
	@Override
	public void createPurchase(User user, Product product) {
		Purchase purchase = new DefaultPurchase();
		purchase.setCustomer(user);
		purchase.setProducts(new ArrayList<>(Arrays.asList(product)));
		
		var purchaseStatus = new DefaultPurchaseStatus();
		purchaseStatus.setId(1); // the initial, the first purchase status
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseDao.savePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
	}

	@Override
	public List<Purchase> getNotCompletedPurchases() {
		return purchaseConverter.convertPurchaseDtosToPurchases(purchaseDao.getNotCompletedPurchases(LAST_STATUS_OF_ORDER_FULFILMENT_ID));
	}

	@Override
	public void markFulfilmentStageForPurchaseIdAsCompleted(Integer purchaseId) {
		Purchase purchase = purchaseConverter.convertPurchaseDtoToPurchase(purchaseDao.getPurchaseById(purchaseId));
		PurchaseStatus purchaseStatus = purchase.getPurchaseStatus();
		int newPurchaseStatusId = purchaseStatus.getId() + 1;
		purchaseStatus.setId(newPurchaseStatusId);
		purchase.setPurchaseStatus(purchaseStatus);
		
		purchaseDao.updatePurchase(purchaseConverter.convertPurchaseToPurchaseDto(purchase));
		
		if (LAST_STATUS_OF_ORDER_FULFILMENT_ID.equals(newPurchaseStatusId) 
				&& purchase.getCustomer().getReferrerUser() != null) {
			User referrerUser = purchase.getCustomer().getReferrerUser();
			double shareFromPurchase = purchase.getTotalPurchaseCost() * reffererRewardRate;
			referrerUser.setMoney(referrerUser.getMoney() + shareFromPurchase);
			userFacade.updateUser(referrerUser);
		}
	}

}
