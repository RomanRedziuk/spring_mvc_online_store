package com.romanredziuk.spring.online_store.persistence.entities.impl;

import java.util.ArrayList;
import java.util.List;

import com.romanredziuk.spring.online_store.persistence.entities.Product;
import com.romanredziuk.spring.online_store.persistence.entities.Purchase;
import com.romanredziuk.spring.online_store.persistence.entities.PurchaseStatus;
import com.romanredziuk.spring.online_store.persistence.entities.User;


public class DefaultPurchase implements Purchase {

	private static final int AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER = 16;
	
	private Integer id;
	private String creditCardNumber;
	private List<Product> products;
	private User customer;
	private PurchaseStatus purchaseStatus;
	

	@Override
	public boolean isCreditCardNumberValid(String creditCardNumber) {
		return creditCardNumber.toCharArray().length == AMOUNT_OF_DIGITS_IN_CREDIT_CARD_NUMBER && 
				!creditCardNumber.contains(" ") && Long.parseLong(creditCardNumber) > 0;
	}

	@Override
	public void setCreditCardNumber(String creditCardNumber) {
		if (creditCardNumber == null) {
			return;
		}
		this.creditCardNumber = creditCardNumber;
	}

	@Override
	public void setProducts(List<Product> products) {
		this.products = new ArrayList<>(products);
	}

	@Override
	public void setCustomer(User customer) {
		this.customer = customer;
	}


	@Override
	public User getCustomer() {
		return this.customer;
	}
	
	@Override
	public String toString() {
		return "DefaultPurchase [creditCardNumber=" + creditCardNumber + ", products=" + products + ", customer="
				+ customer + ", purchaseStatus=" + purchaseStatus + "]";
	}

	@Override
	public List<Product> getProducts() {
		ArrayList<Product> copy = new ArrayList<Product>(this.products);
		return copy;
	}

	@Override
	public void setPurchaseStatus(PurchaseStatus purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	@Override
	public PurchaseStatus getPurchaseStatus() {
		return this.purchaseStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	@Override
	public double getTotalPurchaseCost() {
		return products.stream().mapToDouble(product -> product.getPrice()).sum();
	}


}
