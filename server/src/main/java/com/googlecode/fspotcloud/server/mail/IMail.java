package com.googlecode.fspotcloud.server.mail;

/**
 * Created with IntelliJ IDEA.
 * User: steven
 * Date: 13-6-12
 * Time: 22:54
 * To change this template use File | Settings | File Templates.
 */
public interface IMail {

    void send(String recipient, String title, String body);
}
