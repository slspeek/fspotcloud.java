package com.googlecode.fspotcloud.user.emailconfirmation;


public interface SecretGenerator {
    String getSecret(String user);
}
