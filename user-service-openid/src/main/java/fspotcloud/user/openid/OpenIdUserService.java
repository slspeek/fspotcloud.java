/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.user.openid;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.servlet.RequestScoped;
import com.google.inject.servlet.SessionScoped;
import fspotcloud.user.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author steven
 */
public class OpenIdUserService implements UserService {

    @Inject
    Provider<HttpSession> sessionProvider;
    @Inject
    @AdminEmail
    String adminEmail;
    @Inject
    Provider<HttpServletRequest> requestProvider;

    @Override
    public String createLoginURL(String destinationURL) {
        destinationURL = toAbsoluteURL(destinationURL);
        return "index.jsp?dest=" + destinationURL;
    }

    @Override
    public String createLogoutURL(String destinationURL) {
        destinationURL = toAbsoluteURL(destinationURL);
        return "index.jsp?logout=true&dest=" + destinationURL;
    }

    private String toAbsoluteURL(String url) {
        HttpServletRequest request = requestProvider.get();
        String result = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath() + "/" + url;
        return result;

    }

    @Override
    public String getEmail() {
        HttpSession session = sessionProvider.get();
        List<String> emails = (List<String>) session.getAttribute("email");
        if (emails != null && !emails.isEmpty()) {
            return emails.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean isUserLoggedIn() {
        return getEmail() != null;
    }

    @Override
    public boolean isUserAdmin() {
        return adminEmail.equals(getEmail());
    }
}
