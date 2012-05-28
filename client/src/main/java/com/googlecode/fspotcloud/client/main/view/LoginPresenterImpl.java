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
package com.googlecode.fspotcloud.client.main.view;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.googlecode.fspotcloud.client.main.view.api.LoginView;
import com.googlecode.fspotcloud.shared.main.AuthenticationAction;
import com.googlecode.fspotcloud.shared.main.AuthenticationResult;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.customware.gwt.dispatch.client.DispatchAsync;
import net.customware.gwt.dispatch.server.Dispatch;


public class LoginPresenterImpl extends AbstractActivity implements LoginView.LoginPresenter {
    private static final Logger log = Logger.getLogger(LoginPresenterImpl.class.getName());
    private final LoginView view;
    private final DispatchAsync dispatch;

    @Inject
    public LoginPresenterImpl(LoginView loginView, DispatchAsync dispatch) {
        this.view = loginView;
        this.dispatch = dispatch;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        this.view.setPresenter(this);
        panel.setWidget(view);
        view.focusUserNameField();
    }

    @Override
    public void onUserFieldKeyUp(KeyUpEvent e) {
        log.info("Code: " + e.getNativeKeyCode());

        if (e.getNativeKeyCode() == 13) {
            view.focusPasswordField();
        }
    }

    @Override
    public void onPasswordFieldKeyUp(KeyUpEvent e) {
        if (e.getNativeKeyCode() == 13) {
            submitToServer();
        }
    }

    private void submitToServer() {
        String userName = view.getUserNameField().getText();
        String password = view.getPasswordField().getText();
        AuthenticationAction auth = new AuthenticationAction(userName, password);
        dispatch.execute(auth,
            new AsyncCallback<AuthenticationResult>() {
                @Override
                public void onFailure(Throwable caught) {
                    log.log(Level.WARNING, "Auth request could not be made",
                        caught);
                }

                @Override
                public void onSuccess(AuthenticationResult result) {
                    if (result.getSuccess()) {
                    }
                }
            });
    }
}
