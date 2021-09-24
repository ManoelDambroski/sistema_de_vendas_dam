
package com.dambroski.services;

import java.time.Instant;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.dambroski.domain.Pedido;


public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sen}")
	private String sender;

	@Value("${default.rec}")
	private String recipient;

	@Autowired
	private TemplateEngine templateEngnine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessage(pedido);
		sendEmail(sm);
	}

	@Override
	public void sendOrderConfimationHtmlEmail(Pedido pedido) {
		MimeMessage mime;
		try {
			mime = prepareMimeMessage(pedido);
			sendHtmlEmail(mime);
		} catch (MessagingException e) {
			sendOrderConfirmationEmail(pedido);
		}

	}

	protected String htmlFromTemplatePedido(Pedido pedido) {
		Context context = new Context();
		context.setVariable("pedido", pedido);
		return templateEngnine.process("ConfirmacaoPedido", context);

	}

	protected MimeMessage prepareMimeMessage(Pedido pedido) throws MessagingException {
		MimeMessage mime = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mime, true);
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado n: " + pedido.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(pedido), true);
		return mime;
	}

	protected SimpleMailMessage prepareSimpleMailMessage(Pedido pedido) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(sender);
		sm.setFrom(recipient);
		sm.setSubject("Pedido n: " + pedido.getId());
		sm.setText(pedido.toString());
		sm.setSentDate(Date.from(Instant.now()));
		return sm;
	}

}

