package com.romanredziuk.spring.online_store.core.facades.impl;

import static com.romanredziuk.spring.online_store.persistence.dto.RoleDto.CUSTOMER_ROLE_NAME;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.core.facades.UserFacade;
import com.romanredziuk.spring.online_store.core.services.AffiliateMarketingService;
import com.romanredziuk.spring.online_store.persistence.dao.UserDao;
import com.romanredziuk.spring.online_store.persistence.dto.converters.UserDtoToUserConverter;
import com.romanredziuk.spring.online_store.persistence.entities.User;

@Service
public class DefaultUserFacade implements UserFacade {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDtoToUserConverter userConverter;
	
	@Autowired
	private AffiliateMarketingService marketingService;
	

	@Override
	public void registerUser(User user, String referrerCode) {
		user.setRoleName(CUSTOMER_ROLE_NAME);
		user.setPartnerCode(marketingService.generateUniquePartnerCode());
		user.setReferrerUser(userConverter.convertUserDtoToUser(userDao.getUserByPartnerCode(referrerCode)));
		userDao.saveUser(userConverter.convertUserToUserDto(user));
	}


	@Override
	public User getUserByEmail(String email) {
		return userConverter.convertUserDtoToUser(userDao.getUserByEmail(email));
	}

	@Override
	public List<User> getUsers() {
		return userConverter.convertUserDtosToUsers(userDao.getUsers());
	}

	@Override
	public User getUserById(Integer userId) {
		return userConverter.convertUserDtoToUser(userDao.getUserById(userId));
	}

	@Override
	public void updateUser(User referrerUser) {
		userDao.updateUser(userConverter.convertUserToUserDto(referrerUser));
	}

	@Override
	public List<User> getReferralsForUser(User loggedInUser) {
		return userConverter.convertUserDtosToUsers(userDao.getReferralsByUserId(loggedInUser.getId()));
	}
}
