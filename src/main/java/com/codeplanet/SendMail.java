package com.codeplanet;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class SendMail
{
	public void send(String to,String subject,String body)
	{
		Properties prop= System.getProperties();
		prop.put("mail.smtp.host", "smpt.gmail.com");
		prop.put("mail.smpt.port","465");
		prop.put("mail.smpt.ssl.enable", "true");
		prop.put("mail.smpt.auth","true");
		String from="ankitdhali786@gmail.com";
		Session session = Session.getInstance(prop, new SimpleAuthenticator(from,"8094487286"));
		session.setDebug(true);
		try 
		{
			MimeMessage message = new MimeMessage(session);
			message.setSubject(subject);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setText(body);
			Transport.send(message);
		}
		catch(Exception e)
		{
			
		}
	}
}
class SimpleAuthenticator extends Authenticator
{
	String username=null;
	String password=null;
	public SimpleAuthenticator(String username, String password) 
	{
		this.username=username;
		this.password=password;
	}
	public PasswordAuthentication getPasswordAuthentication()
	{
		return new PasswordAuthentication(username, password);
	}
}