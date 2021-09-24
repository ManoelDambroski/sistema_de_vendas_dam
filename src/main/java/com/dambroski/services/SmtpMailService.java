package com.dambroski.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpMailService extends AbstractEmailService {

	private final static Logger LOG = LoggerFactory.getLogger(SmtpMailService.class);

	@Autowired
	private MailSender mailSender;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Envio de email");
		mailSender.send(msg);
		LOG.info("Email enviado");

	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Envio de email");
		javaMailSender.send(msg);
		LOG.info("Email enviado");

	}

}

