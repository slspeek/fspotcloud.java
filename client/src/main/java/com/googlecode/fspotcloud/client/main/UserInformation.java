/*
 * Copyright 2010-2012 Steven L. Speek.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.client.main;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.googlecode.fspotcloud.shared.main.GetUserInfo;
import com.googlecode.fspotcloud.shared.main.UserInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.customware.gwt.dispatch.client.DispatchAsync;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
public class UserInformation {
    private static final Logger log = Logger.getLogger(UserInformation.class.getName());
    private final DispatchAsync dispatch;

    @Inject
    public UserInformation(DispatchAsync dispatch) {
        this.dispatch = dispatch;
    }

    public void getAsync() {
        getUserInfoAsync(new GetUserInfo(""),
            new AsyncCallback<UserInfo>() {
                @Override
                public void onFailure(Throwable caught) {
                    log.log(Level.SEVERE, "User info failure ", caught);
                }

                @Override
                public void onSuccess(UserInfo result) {
                    log.info("User is " + result.isLoggedIn() +
                        " with email: " + result.getEmail());
                }
            });
    }

    public void getUserInfoAsync(GetUserInfo info,
        AsyncCallback<UserInfo> callback) {
        dispatch.execute(info, callback);
    }
}
