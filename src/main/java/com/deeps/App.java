package com.deeps;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class App {
	public static void main(String[] args) throws IOException {

		System.out.println("preparing to send message ...");
		String message = "Hello , Dear, this is message for security check . ";
		String subject = "CodersArea : Confirmation";
		String to = "shreedeepak8@gmail.com";
		String from = "nareshchandrabarik58pdp@gmail.com";

		sendMessage(message, subject, to, from);
		sendAttach(message, subject, to, from);
	}

	// this is responsible to send the message with attachment
	private static void sendAttach(String message, String subject, String to, String from) throws IOException {

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES: " + properties);

		// setting important information to properties object

		// host set

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object..

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nareshchandrabarik58pdp@gmail.com", "nareshdeeps@123");
			}

		});

		session.setDebug(true);

		// Step 2 : compose the message [text,multi media]

		MimeMessage m = new MimeMessage(session);

		try {
			// from message
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// attachement..
			
			// file path 
			
			String path="C:\\Users\\USER\\Desktop\\contact.png";
			
			MimeMultipart mimeMultipart = new MimeMultipart();
            //text
			//file
			
			MimeBodyPart textMime = new MimeBodyPart();
			
			MimeBodyPart fileMime = new MimeBodyPart();
			
			
			textMime.setText(message);
			File file=new File(path);
			
			fileMime.attachFile(file);
			
			mimeMultipart.addBodyPart(textMime);
			mimeMultipart.addBodyPart(fileMime);
		
			m.setContent(mimeMultipart);
			
			Transport.send(m);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Sent Success !!!-----------------------");

	}

//this is responsible to send the message with attachment
	private static void sendMessage(String message, String subject, String to, String from) {

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();
		System.out.println("PROPERTIES: " + properties);

		// setting important information to properties object

		// host set

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// Step 1: to get the session object..

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("nareshchandrabarik58pdp@gmail.com", "nareshdeeps@123");
			}

		});

		session.setDebug(true);

		// Step 2 : compose the message [text,multi media]

		MimeMessage m = new MimeMessage(session);

		try {
			// from message
			m.setFrom(from);

			// adding recipient to message
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject to message
			m.setSubject(subject);

			// attachement..
			m.setText(message);

			// send

			// step 3 : send the message using transport class
			Transport.send(m);

			System.out.println("Sent Success !!!-----------------------");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
