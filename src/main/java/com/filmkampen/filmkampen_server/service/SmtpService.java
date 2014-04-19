package com.filmkampen.filmkampen_server.service;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;

import com.filmkampen.filmkampen_server.manager.SmtpManager;

@Component
public class SmtpService {

    private static final String SMTP_HOST_NAME = "smtp.sendgrid.net";

    @Resource
    private SmtpManager smtpManager;

    public void sendMail(String toEmail) throws Exception {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");

        Authenticator auth = new SMTPAuthenticator();
        Session mailSession = Session.getDefaultInstance(props, auth);
        // uncomment for debugging infos to stdout
        // mailSession.setDebug(true);
        Transport transport = mailSession.getTransport();

        MimeMessage message = new MimeMessage(mailSession);

        Multipart multipart = new MimeMultipart("alternative");

        BodyPart part1 = new MimeBodyPart();
        part1.setContent("<b>Your password has been reset to sdkfjdffds</b>", "text/html");

        multipart.addBodyPart(part1);

        message.setContent(multipart);
        message.setFrom(new InternetAddress("Film@kampen.com"));
        message.setSubject("Your password has been reset");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));

        transport.connect();
        transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
        transport.close();
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            String username = smtpManager.getUsername();
            String password = smtpManager.getPassword();
            return new PasswordAuthentication(username, password);
        }
    }
}
