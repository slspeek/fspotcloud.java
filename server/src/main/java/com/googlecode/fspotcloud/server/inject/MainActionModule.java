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
package com.googlecode.fspotcloud.server.inject;

import com.googlecode.fspotcloud.server.main.handler.AuthenticationHandler;
import com.googlecode.fspotcloud.server.main.handler.GetTagTreeHandler;
import com.googlecode.fspotcloud.server.main.handler.GetUserInfoHandler;
import com.googlecode.fspotcloud.server.main.handler.SignUpHandler;
import com.googlecode.fspotcloud.shared.main.AuthenticationAction;
import com.googlecode.fspotcloud.shared.main.GetTagTreeAction;
import com.googlecode.fspotcloud.shared.main.GetUserInfo;
import com.googlecode.fspotcloud.shared.main.SignUpAction;
import net.customware.gwt.dispatch.server.guice.ActionHandlerModule;


public class MainActionModule extends ActionHandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(GetUserInfo.class, GetUserInfoHandler.class);
        bindHandler(GetTagTreeAction.class, GetTagTreeHandler.class);
        bindHandler(AuthenticationAction.class, AuthenticationHandler.class);
        bindHandler(SignUpAction.class, SignUpHandler.class);
    }
}
