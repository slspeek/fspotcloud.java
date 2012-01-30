/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.shared.main.actions;

import com.google.gwt.user.client.rpc.IsSerializable;
import java.io.Serializable;
import net.customware.gwt.dispatch.shared.Result;

/**
 *
 * @author steven
 */
public class UserInfo implements Result, IsSerializable, Serializable {

    private String email;
    private boolean isAdmin;
    private boolean isLoggedId;
    private String logoutUrl;
    private String loginUrl;

    UserInfo() {
    }

    public UserInfo(String email, boolean isAdmin, boolean isLoggedId, String loginUrl, String logoutUrl) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.isLoggedId = isLoggedId;
        this.loginUrl = loginUrl;
        this.logoutUrl = logoutUrl;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isLoggedIn() {
        return isLoggedId;
    }

    public String createLogoutUrl() {
        return logoutUrl;
    }

    public String createLoginUrl() {
        return loginUrl;
    }
}
