package com.romanredziuk.spring.online_store.core.facades;

import java.util.List;

import com.romanredziuk.spring.online_store.persistence.entities.User;

public interface UserFacade {

	void registerUser(User user, String partnerCode);

	User getUserByEmail(String email);
	
	List<User> getUsers();

	User getUserById(Integer userId);

	void updateUser(User referrerUser);

	List<User> getReferralsForUser(User loggedInUser);
}
