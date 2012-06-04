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
package com.googlecode.fspotcloud.server.main.handler;

import com.google.inject.Inject;
import com.googlecode.fspotcloud.server.model.api.User;
import com.googlecode.fspotcloud.server.model.api.UserDao;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.fspotcloud.shared.main.AuthenticationAction;
import com.googlecode.fspotcloud.shared.main.AuthenticationResult;
import com.googlecode.fspotcloud.shared.main.LogoutAction;
import com.googlecode.fspotcloud.user.ILoginMetaDataUpdater;
import com.googlecode.fspotcloud.user.ISessionEmail;
import com.googlecode.fspotcloud.user.LoginMetaData;
import com.googlecode.fspotcloud.user.UserService;
import javax.inject.Provider;
import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.DispatchException;


public class LogoutHandler extends SimpleActionHandler<LogoutAction, VoidResult> {
    private final Provider<ISessionEmail> sessionEmailProvider;
    private final ILoginMetaDataUpdater loginMetaDataUpdater;

    @Inject
    public LogoutHandler(Provider<ISessionEmail> sessionEmailProvider,
        ILoginMetaDataUpdater loginMetaDataUpdater) {
        this.sessionEmailProvider = sessionEmailProvider;
        this.loginMetaDataUpdater = loginMetaDataUpdater;
    }

    @Override
    public VoidResult execute(LogoutAction action, ExecutionContext context)
        throws DispatchException {
        ISessionEmail email = sessionEmailProvider.get();
        email.setEmail(null);
        loginMetaDataUpdater.clear();

        return new VoidResult();
    }
}
