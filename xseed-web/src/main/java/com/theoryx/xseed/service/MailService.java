package com.theoryx.xseed.service;

import javax.mail.MessagingException;

public interface MailService {

	/**
	 * Sends an email
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @exception MessagingException
	 * @return nothing
	 */
	void sendEmail(String from, String to, String subject, String content) throws MessagingException;

	/**
	 * Creates an email for password activation and calls sendEmail method
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param token
	 * @return boolean. if the email was sent successfully - true, otherwise - false
	 */
	boolean sendChangePasswordLink(String from, String to, String subject, String token);

	/**
	 * Creates an email for startup invitation and calls sendEmail method
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param token
	 * @param startup
	 * @return boolean. if the email was sent successfully - true, otherwise - false
	 */
	boolean sendInvitationLinkForStartup(String from, String to, String subject, String token, String startup);

	/**
	 * Creates an email for activating admin profile and calls sendEmail method
	 * 
	 * @param from
	 * @param to
	 * @param subject
	 * @param token
	 * @return boolean. if the email was sent successfully - true, otherwise - false
	 */
	boolean sendAdminCreationEmail(String from, String to, String subject, String token);

}
