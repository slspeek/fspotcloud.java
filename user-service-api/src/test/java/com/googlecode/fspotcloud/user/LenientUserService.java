/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.user;

import com.googlecode.fspotcloud.user.UserService;

/**
 *
 * @author steven
 */
public class LenientUserService implements UserService {

    @Override
    public String createLoginURL(String destinationURL) {
        return "";
    }

    @Override
    public String createLogoutURL(String destinationURL) {
        return "";
    }

    @Override
    public String getEmail() {
        return "";
    }

    @Override
    public boolean isUserLoggedIn() {
        return true;
    }

    @Override
    public boolean isUserAdmin() {
        return true;
    }
    
}