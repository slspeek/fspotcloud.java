/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fspotcloud.user;

import fspotcloud.user.UserService;
import javax.inject.Inject;
import javax.inject.Provider;

/**
 *
 * @author steven
 */
public class AdminPermission {

    private final Provider<UserService> userServiceProvider;

    @Inject
    public AdminPermission(Provider<UserService> userServiceProvider) {
        this.userServiceProvider = userServiceProvider;
    }

    public void chechAdminPermission() {
        UserService userService = userServiceProvider.get();
        if (!userService.isUserAdmin()) {
            throw new SecurityException("User is not logged in as admin");
        }
    }
}
