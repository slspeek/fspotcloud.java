package com.googlecode.fspotcloud.user.emailconfirmation;

import com.googlecode.fspotcloud.user.inject.ServerAddress;

import javax.inject.Inject;
import javax.inject.Provider;

public class ConfirmationMailGenerator {

    @Inject  @ServerAddress
    private Provider<String> serverAddressProvider;

    public String getMailBody(String user, String secret) {
        String result = "Hello " + user + ",\n";
        result += "Please click this link to confirm your email address:\n";
        result +=  serverAddressProvider.get() + "/confirm?email=" + user +"&secret=" + secret;
        result += "\n";
        result += "The F-Spot Cloud Team";
        return result;
    }


}
