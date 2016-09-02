package com.theoryx.xseed.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {

	private static final String HOST_CODE = "@@host";
	private static final String PORT_CODE = "@@port";
	private static final String APP_CODE = "/@@app";
	//private static final String HOST = "localhost";
	//private static final String HOST = "10.0.11.162";
	private static final String HOST = "78.128.33.7";
	private static final String PORT = "8080";
	private static final String APP = "/xseed-web";
	private static final String EMAIL = "@@email";
	private static final String TOKEN = "@@token";
	private static final String FORGOTTEN_PASSWORD = "forgotten";
	private static final String INVITATION = "invitation";
	private static final String ADMIN = "admin";

	@Override
	public void sendEmail(String from, String to, String subject, String content) throws MessagingException {
		String email_password = System.getenv("XSEED_EMAIL_PASS");
		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.gmail.com");
		//props.put("mail.smtp.host", "74.125.206.109");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session ses = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, email_password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(ses);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setContent(content, "text/html; charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			throw e;
		}
	}

	@Override
	public boolean sendChangePasswordLink(String from, String to, String subject, String token) {
		try {
			sendEmail(from, to, subject, generateContentWithToken(FORGOTTEN_PASSWORD, to, token));
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendInvitationLinkForStartup(String from, String to, String subject, String token, String startup) {
		String content = generateContentWithToken(INVITATION, to, token).replaceAll("@@startup", startup);
		try {
			sendEmail(from, to, subject, content);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean sendAdminCreationEmail(String from, String to, String subject, String token) {
		String content = generateContentWithToken(ADMIN, to, token);
		try {
			sendEmail(from, to, subject, content);
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String generateContentWithToken(String type, String email, String token) {
		String email_template = "";
		if (type.equals(ADMIN)) {
			email_template = "messages/admin_token.txt";
		}
		if (type.equals(INVITATION)) {
			email_template = "messages/invitation.txt";
		}
		if (type.equals(FORGOTTEN_PASSWORD)) {
			email_template = "messages/forgottenpassword.txt";
		}
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(getClass().getClassLoader().getResourceAsStream(email_template), "UTF8"))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				if (line.equals("")) {
					sb.append("<br></br>");
				} else {
					sb.append(line);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String content = sb.toString();
		content = content.replaceAll(TOKEN, token).replaceAll(EMAIL, email).
				replaceAll(HOST_CODE, HOST).replaceAll(PORT_CODE, PORT).replaceAll(APP_CODE, APP);
		return content;
	}

}
