package com.googlecode.fspotcloud.server.mail;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer implements IMail {


    private String fromAddress;

    @Inject
    public Mailer(@FromAddress String fromAddress) {
        this.fromAddress = fromAddress;
    }


    @Override
    public void send(String recipient, String subject, String body) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromAddress));
            msg.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(recipient));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);

        } catch (AddressException e) {
            // ...
        } catch (MessagingException e) {
            // ...
        }
    }
}
