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

import com.googlecode.fspotcloud.shared.main.AuthenticationAction;
import com.googlecode.fspotcloud.shared.main.AuthenticationResult;
import javax.inject.Inject;
import org.jukito.JukitoRunner;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(JukitoRunner.class)
public class AuthenticationHandlerTest {
    @Inject
    AuthenticationHandler handler;

    @Test
    public void testExecute() throws Exception {
        AuthenticationAction action = new AuthenticationAction("foo", "secret");
        AuthenticationResult result = handler.execute(action, null);
        assertFalse(result.getSuccess());
    }
}