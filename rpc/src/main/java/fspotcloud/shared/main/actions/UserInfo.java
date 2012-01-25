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

    UserInfo(){}
    public UserInfo(String email, boolean isAdmin, boolean isLoggedId) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.isLoggedId = isLoggedId;
    }
    
    public String getEmail() {
        return email;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public boolean isIsLoggedId() {
        return isLoggedId;
    }
}
