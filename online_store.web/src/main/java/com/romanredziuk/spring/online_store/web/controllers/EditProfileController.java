package com.romanredziuk.spring.online_store.web.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.romanredziuk.spring.online_store.core.facades.UserFacade;
import com.romanredziuk.spring.online_store.core.services.Validator;
import com.romanredziuk.spring.online_store.core.services.impl.CorePasswordValidator;
import com.romanredziuk.spring.online_store.persistence.entities.User;
import com.romanredziuk.spring.online_store.web.utils.PBKDF2WithHmacSHA1EncryptionService;

@Controller
@RequestMapping("/edit-profile")
public class EditProfileController {
	
	@Autowired
	private UserFacade userFacade;
	
	@Autowired
	private Validator passValidator;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PBKDF2WithHmacSHA1EncryptionService encryptionService;
	
	@GetMapping
	public String doGet(HttpSession session) {
		User loggedInUser = (User) session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
		
		if (loggedInUser == null) {
			return "redirect:/signin";
		} else {
			return "editProfile";
		}
	}
	
	@PostMapping
	public String doPost(HttpSession session, @RequestParam String firstName, @RequestParam String lastName, 
			@RequestParam("email") String emailParameter, 
			@RequestParam String password, @RequestParam("newPassword") String newPasswordParameter) {
		User loggedInUser = (User)session.getAttribute(SignInController.LOGGED_IN_USER_ATTR);
		// need to do this to extract latest state of the user
		User user = userFacade.getUserById(loggedInUser.getId());
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(emailParameter);
		
		User userByEmail = userFacade.getUserByEmail(user.getEmail());
		
		if (userByEmail != null && !emailParameter.equals(loggedInUser.getEmail())) {
			session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.email.exists", null, Locale.getDefault()));
			return "redirect:/edit-profile";
		}
		
		if (!encryptionService.validatePassword(password, loggedInUser.getPassword())) {
			session.setAttribute("errMsg", messageSource.getMessage("signup.err.msg.old.password.wrong", null, Locale.getDefault()));
			return "redirect:/edit-profile";
		}
		
		if (newPasswordParameter != null && !newPasswordParameter.isEmpty()) {
			List<String> errorMessages = passValidator.validate(newPasswordParameter);
			if (errorMessages.size() != 0) {
				String errMsg = messageSource.getMessage("signup.err.msg.general.error", null, Locale.getDefault());
				if (errorMessages.contains(CorePasswordValidator.LENGTH_OR_SPECIAL_CHARACTER_ERROR)) {
					errMsg = messageSource.getMessage("signup.err.msg.special.character", null, Locale.getDefault());
				}
				if (errorMessages.contains(CorePasswordValidator.MOST_COMMON_PASSWORD)) {
					errMsg = messageSource.getMessage("signup.err.msg.common.password", null, Locale.getDefault());
				}
				session.setAttribute("errMsg", errMsg);
				return "redirect:/edit-profile";
			}
		}
		
		if (newPasswordParameter != null && !newPasswordParameter.isEmpty()) {
			user.setPassword(encryptionService.generatePasswordWithSaltAndHash(newPasswordParameter));
		}
	
		userFacade.updateUser(user);
		return "redirect:/my-profile";
	}

}
