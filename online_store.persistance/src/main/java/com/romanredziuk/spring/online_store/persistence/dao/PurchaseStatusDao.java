package com.romanredziuk.spring.online_store.persistence.dao;

import com.romanredziuk.spring.online_store.persistence.dto.PurchaseStatusDto;

public interface PurchaseStatusDao {

	PurchaseStatusDto getPurchaseStatusById(Integer id);

}
