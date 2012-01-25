package fspotcloud.user.gae;

import javax.inject.Inject;


public class UserServiceGae implements fspotcloud.user.UserService {

    @Inject com.google.appengine.api.users.UserService delegate;
    @Override
    public String createLoginURL(String string) {
        return delegate.createLoginURL(string);
    }

    @Override
    public String createLogoutURL(String string) {
        return delegate.createLogoutURL(string);
    }

    @Override
    public String getEmail() {
        if (isUserLoggedIn()) {
            return delegate.getCurrentUser().getEmail();
        } else {
            return null;
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return delegate.isUserLoggedIn();
    }

    @Override
    public boolean isUserAdmin() {
        return isUserLoggedIn() && delegate.isUserLoggedIn();
    }

}
