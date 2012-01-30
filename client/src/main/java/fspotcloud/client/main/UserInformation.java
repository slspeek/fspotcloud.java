/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.client.main;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import fspotcloud.shared.main.actions.GetUserInfo;
import fspotcloud.shared.main.actions.UserInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.customware.gwt.dispatch.client.DispatchAsync;

/**
 *
 * @author steven
 */
public class UserInformation {
    
    private static final Logger log = Logger.getLogger(UserInformation.class
			.getName());
    private final DispatchAsync dispatch;

    @Inject
    public UserInformation(DispatchAsync dispatch) {
        this.dispatch = dispatch;
    }
    
    public void getAsync() {
        getUserInfoAsync(new AsyncCallback<UserInfo>() {

            @Override
            public void onFailure(Throwable caught) {
                log.log(Level.SEVERE, "User info failure ", caught);
            }

            @Override
            public void onSuccess(UserInfo result) {
                log.info("User is " + result.isLoggedIn() + " with email: " +result.getEmail());
            }
        });
    }
    
    public void getUserInfoAsync(AsyncCallback<UserInfo> callback) {
        dispatch.execute(new GetUserInfo(), callback);
    }
    
}
