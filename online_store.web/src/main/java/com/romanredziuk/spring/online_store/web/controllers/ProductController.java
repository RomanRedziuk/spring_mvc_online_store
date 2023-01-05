package com.romanredziuk.spring.online_store.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanredziuk.spring.online_store.core.facades.ProductFacade;
import com.romanredziuk.spring.online_store.persistence.entities.Product;

@Controller
public class ProductController {
	
	@Autowired
	private ProductFacade productFacade;
	
	@GetMapping("/product")
	public String doGet(@RequestParam String guid, Model model) {
		Product p = productFacade.getProductByGuid(guid);
		model.addAttribute("product", p);
		return "pdp";
	}

}
