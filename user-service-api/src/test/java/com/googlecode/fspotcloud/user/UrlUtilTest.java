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
package com.googlecode.fspotcloud.user;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.jukito.JukitoRunner;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
@RunWith(JukitoRunner.class)
public class UrlUtilTest {
    @Inject
    UrlUtil util;
    @Inject
    HttpServletRequest request;

    @Before
    public void setUp(HttpServletRequest request) {
        when(request.getScheme()).thenReturn("http");
        when(request.getContextPath()).thenReturn("/context");
        when(request.getServerPort()).thenReturn(8080);
        when(request.getServerName()).thenReturn("localhost");
    }

    @Test
    public void toAbsoluteURL() throws Exception {
        String result = util.toAbsoluteURL("");
        assertEquals("http://localhost:8080/context/", result);
    }
}
