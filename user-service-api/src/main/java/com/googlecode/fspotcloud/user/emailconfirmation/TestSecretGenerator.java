package com.googlecode.fspotcloud.user.emailconfirmation;

public class TestSecretGenerator implements SecretGenerator{

    @Override
    public String getSecret(String user) {
        //return user;
        return "thiswillgetharder";
    }
}
