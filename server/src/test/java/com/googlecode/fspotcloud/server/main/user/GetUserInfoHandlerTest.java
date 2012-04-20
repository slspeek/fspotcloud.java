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
package com.googlecode.fspotcloud.server.main.user;

import com.googlecode.fspotcloud.shared.main.actions.GetUserInfo;
import com.googlecode.fspotcloud.shared.main.actions.UserInfo;
import com.googlecode.fspotcloud.user.UserService;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class GetUserInfoHandlerTest {
    GetUserInfoHandler handler;
    @Mock
    UserService service;

    @BeforeMethod
    public void mockit() {
        MockitoAnnotations.initMocks(this);
        handler = new GetUserInfoHandler(service);
    }


    @Test
    public void execute() throws DispatchException {
        when(service.isUserLoggedIn()).thenReturn(Boolean.TRUE);
        when(service.isUserAdmin()).thenReturn(Boolean.FALSE);
        when(service.createLoginURL("later")).thenReturn("login_url");
        when(service.createLogoutURL("later")).thenReturn("logout_url");
        when(service.getEmail()).thenReturn("foo@bar.com");

        UserInfo info = handler.execute(new GetUserInfo(""), null);
        Assert.assertEquals("foo@bar.com", info.getEmail());
        Assert.assertTrue(info.isLoggedIn());
    }


    @Test
    public void execute2() throws DispatchException {
        when(service.isUserLoggedIn()).thenReturn(Boolean.FALSE);
        when(service.isUserAdmin()).thenReturn(Boolean.FALSE);
        when(service.createLoginURL("later")).thenReturn("login_url");
        when(service.createLogoutURL("later")).thenReturn("logout_url");
        when(service.getEmail()).thenReturn(null);

        UserInfo info = handler.execute(new GetUserInfo(""), null);
        Assert.assertFalse(info.isLoggedIn());
    }
}
