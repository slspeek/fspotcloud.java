package com.googlecode.fspotcloud.user;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpSession;

public class LoginMetaDataProvider implements Provider<LoginMetaData> {
    public static final String LOGIN_META_DATA = "login-meta-data";
    @Inject
    HttpSession session;


    @Override
    public LoginMetaData get() {
        LoginMetaData stored = (LoginMetaData) session.getAttribute(LOGIN_META_DATA);
        if (stored == null) {
            stored = new LoginMetaData();
            session.setAttribute(LOGIN_META_DATA, stored);
        }
        return stored;
    }
}
