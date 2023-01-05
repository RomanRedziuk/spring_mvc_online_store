package com.romanredziuk.spring.online_store.core.mail.impl;

import org.springframework.stereotype.Service;

import com.romanredziuk.spring.online_store.core.mail.MailSender;

@Service
public class DefaultMailSender implements MailSender {

	@Override
	public void sendEmail(String sendTo, String messageToSend) {
		// sending email here
	}

}
