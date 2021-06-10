package com.example.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void forContact(String email,String fullname) {
		SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Demo E-commerce ");
		simpleMailMessage.setText("Thank You" + fullname+ " For Contact Us We Will Call You  ");
		javaMailSender.send(simpleMailMessage);
	}
	
	public void forBuyingProduct(String email,String fullname) {
		SimpleMailMessage simpleMailMessage= new SimpleMailMessage();
		simpleMailMessage.setTo(email);
		simpleMailMessage.setSubject("Demo E-commerce ");
		simpleMailMessage.setText("Thank  You " + fullname + " For Buying My Product ");
		javaMailSender.send(simpleMailMessage);
	}

}
