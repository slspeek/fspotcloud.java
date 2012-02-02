package fspotcloud.user.gae;

import com.google.inject.Provider;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class UserServiceGae implements fspotcloud.user.UserService {

    @Inject
    com.google.appengine.api.users.UserService delegate;
    @Inject
    Provider<HttpServletRequest> requestProvider;

    private String toAbsoluteURL(String url) {
        HttpServletRequest request = requestProvider.get();
        String result = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/" + url;
        return result;

    }

    @Override
    public String createLoginURL(String string) {
        string = toAbsoluteURL(string);
        return delegate.createLoginURL(string);
    }

    @Override
    public String createLogoutURL(String string) {
        string = toAbsoluteURL(string);
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
