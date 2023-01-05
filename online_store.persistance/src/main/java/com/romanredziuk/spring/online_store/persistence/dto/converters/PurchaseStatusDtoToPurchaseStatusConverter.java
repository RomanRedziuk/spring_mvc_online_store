package com.romanredziuk.spring.online_store.persistence.dto.converters;

import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.persistence.dto.PurchaseStatusDto;
import com.romanredziuk.spring.online_store.persistence.entities.PurchaseStatus;
import com.romanredziuk.spring.online_store.persistence.entities.impl.DefaultPurchaseStatus;

@Service
public class PurchaseStatusDtoToPurchaseStatusConverter {

	public PurchaseStatus convertPurchaseStatusDtoToPurchaseStatus(PurchaseStatusDto purchaseStatusDto) {
		PurchaseStatus purchaseStatus = new DefaultPurchaseStatus();
		purchaseStatus.setId(purchaseStatusDto.getId());
		purchaseStatus.setStatusName(purchaseStatusDto.getStatusName());
		return purchaseStatus;
	}

	public PurchaseStatusDto convertPurchaseStatusToPurchaseStatusDto(PurchaseStatus purchaseStatus) {
		PurchaseStatusDto dto = new PurchaseStatusDto();
		dto.setId(purchaseStatus.getId());
		dto.setStatusName(purchaseStatus.getStatusName());
		return dto;
	}

}
