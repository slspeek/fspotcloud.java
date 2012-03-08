package com.googlecode.fspotcloud.user;

public interface UserService {

    String createLoginURL(String destinationURL);
    
    String createLogoutURL(String destinationURL);

    String getEmail();

    boolean isUserLoggedIn();

    boolean isUserAdmin();
}
