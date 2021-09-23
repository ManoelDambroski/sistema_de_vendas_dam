package com.dambroski.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.dambroski.domain.Pedido;



public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfimationHtmlEmail(Pedido pedido);

	void sendHtmlEmail(MimeMessage msg);
}
