package com.romanredziuk.spring.online_store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanredziuk.spring.online_store.core.facades.PurchaseFacade;

@Controller
public class FulfilmentController {
	
	@Autowired
	private PurchaseFacade purchaseFacade;
	
	@PostMapping("/management-fulfilment")
	public String doPost(@RequestParam Integer purchaseId) {
		purchaseFacade
			.markFulfilmentStageForPurchaseIdAsCompleted(purchaseId);
		return "redirect:/management-orders"; 
	}

}
