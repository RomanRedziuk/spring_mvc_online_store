package com.romanredziuk.spring.online_store.core.mail;

public interface MailSender {
	
	void sendEmail(String sendTo, String messageToSend);

}
