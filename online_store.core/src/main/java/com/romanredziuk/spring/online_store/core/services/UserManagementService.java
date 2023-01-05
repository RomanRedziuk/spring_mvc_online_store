package com.romanredziuk.spring.online_store.core.services;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.entities.User;

public interface UserManagementService {

	String registerUser(User user);

	List<User> getUsers();

	User getUserByEmail(String userEmail);
	
	void resetPasswordForUser(User user);

}
