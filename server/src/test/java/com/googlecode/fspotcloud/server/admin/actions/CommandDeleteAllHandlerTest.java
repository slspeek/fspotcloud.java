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
package com.googlecode.fspotcloud.server.admin.actions;

import com.googlecode.botdispatch.model.api.Commands;

import com.googlecode.fspotcloud.shared.dashboard.actions.CommandDeleteAll;
import com.googlecode.fspotcloud.user.UserService;

import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.inject.Provider;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
public class CommandDeleteAllHandlerTest {
    @Mock
    Commands commandManager;
    @Mock
    UserService userService;
    CommandDeleteAllHandler handler;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Provider<UserService> userServiceProvider = new Provider<UserService>() {
                @Override
                public UserService get() {
                    return userService;
                }
            };

        handler = new CommandDeleteAllHandler(
                commandManager, userServiceProvider);
    }


    /**
     * Test of execute method, of class CommandDeleteAllHandler.
     *
     * @throws Exception DOCUMENT ME!
     */
    @Test(expectedExceptions = SecurityException.class)
    public void testExecuteNotBeingAdmin() throws Exception {
        when(userService.isUserAdmin()).thenReturn(Boolean.FALSE);
        handler.execute(new CommandDeleteAll(), null);
        verifyNoMoreInteractions(commandManager, userService);
    }


    @Test
    void execute() throws DispatchException {
        when(userService.isUserAdmin()).thenReturn(Boolean.TRUE);
        handler.execute(new CommandDeleteAll(), null);
        verify(commandManager).deleteAll();
        verifyNoMoreInteractions(commandManager);
        verify(userService).isUserAdmin();
    }


    @Test(expectedExceptions = ActionException.class)
    void commandManagerFails() throws DispatchException {
        when(userService.isUserAdmin()).thenReturn(Boolean.TRUE);
        doThrow(new RuntimeException()).when(commandManager).deleteAll();
        handler.execute(new CommandDeleteAll(), null);
    }
}
