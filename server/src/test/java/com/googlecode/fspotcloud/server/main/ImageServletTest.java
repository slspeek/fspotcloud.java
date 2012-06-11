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
package com.googlecode.fspotcloud.server.main;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Sets.newHashSet;
import com.googlecode.fspotcloud.model.jpa.photo.PhotoEntity;
import com.googlecode.fspotcloud.server.model.api.Photo;
import com.googlecode.fspotcloud.server.model.api.PhotoDao;
import com.googlecode.fspotcloud.server.model.tag.IUserGroupHelper;
import com.googlecode.fspotcloud.user.UserService;
import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletTestCase;
import com.meterware.servletunit.ServletUnitClient;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
public class ImageServletTest extends ServletTestCase {
    private byte[] THUMB = new byte[] { 1, 2, 3 };
    ServletRunner sr;

    public ImageServletTest(String name) {
        super(name);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        sr = new ServletRunner();
        sr.registerServlet("image", ImageServlet.class.getName());
    }

    @Test
    public void testThumb() throws Exception {
        PhotoDao photoDao = mock(PhotoDao.class);
        Photo photo = new PhotoEntity();
        photo.setId("1");
        photo.setThumb(THUMB);
        photo.setTagList(newArrayList("a"));
        when(photoDao.find("1")).thenReturn(photo);

        IUserGroupHelper helper = mock(IUserGroupHelper.class);
        when(helper.containsOneOf(newHashSet("a"))).thenReturn(true);

        UserService service = mock(UserService.class);
        when(service.isUserAdmin()).thenReturn(true);

        ServletUnitClient sc = sr.newClient();
        WebRequest request = new GetMethodWebRequest(
                "http://test.meterware.com/image");
        request.setParameter("id", "1");
        request.setParameter("thumb", "1");

        InvocationContext ic = sc.newInvocation(request);
        ImageServlet servlet = (ImageServlet) ic.getServlet();
        servlet.photoManager = photoDao;
        servlet.userGroupHelper = helper;
        servlet.userService = service;
        servlet.service(ic.getRequest(), ic.getResponse());

        WebResponse response = ic.getServletResponse();
        assertNotNull("No response received", response);
        assertEquals("content type", "image/jpeg", response.getContentType());

        InputStream in = response.getInputStream();
        byte[] expected = new byte[3];
        in.read(expected);
        assertTrue(Arrays.equals(THUMB, expected));
    }

    @Test
    public void testNormalImage() throws Exception {
        PhotoDao photoDao = mock(PhotoDao.class);
        Photo photo = new PhotoEntity();
        photo.setId("1");
        photo.setImage(THUMB);
        photo.setTagList(newArrayList("a"));
        when(photoDao.find("1")).thenReturn(photo);

        IUserGroupHelper helper = mock(IUserGroupHelper.class);
        when(helper.containsOneOf(newHashSet("a"))).thenReturn(true);

        UserService service = mock(UserService.class);
        when(service.isUserAdmin()).thenReturn(true);

        ServletUnitClient sc = sr.newClient();
        WebRequest request = new GetMethodWebRequest(
                "http://test.meterware.com/image");
        request.setParameter("id", "1");

        InvocationContext ic = sc.newInvocation(request);
        ImageServlet servlet = (ImageServlet) ic.getServlet();
        servlet.photoManager = photoDao;
        servlet.userGroupHelper = helper;
        servlet.userService = service;
        servlet.service(ic.getRequest(), ic.getResponse());

        WebResponse response = ic.getServletResponse();
        assertNotNull("No response received", response);
        assertEquals("content type", "image/jpeg", response.getContentType());

        InputStream in = response.getInputStream();
        byte[] expected = new byte[3];
        in.read(expected);
        assertTrue(Arrays.equals(THUMB, expected));
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
        sr.shutDown();
    }
}
