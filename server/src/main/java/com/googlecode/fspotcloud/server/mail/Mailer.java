/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it
                and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free
                Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is
                distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied
                warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public
                License for more details.
 * You should have received a copy of the GNU General Public License
 * along
                with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330,
                Boston, MA 02111-1307, USA.
 *
 */
            
package com.googlecode.fspotcloud.server.mail;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Mailer implements IMail {
    private String fromAddress;

    @Inject
    public Mailer(@FromAddress
    String fromAddress) {
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
