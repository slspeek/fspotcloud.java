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
package com.googlecode.fspotcloud.server.admin.handler;

import static com.google.common.collect.Lists.newArrayList;

import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.GetAdminTagTreeAction;
import com.googlecode.fspotcloud.shared.main.actions.TagTreeResult;
import com.googlecode.fspotcloud.shared.tag.TagNode;
import com.googlecode.fspotcloud.user.IAdminPermission;

import org.jukito.JukitoRunner;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;

import java.util.List;

import javax.inject.Inject;


/**
 * DOCUMENT ME!
 *
 * @author steven
*/
@RunWith(JukitoRunner.class)
public class GetAdminTagTreeHandlerTest {
    @Inject
    GetAdminTagTreeHandler handler;
    private final GetAdminTagTreeAction action = new GetAdminTagTreeAction();

    @Test
    public void testNormalExecuteNoTags(
        Tags tagManager, IAdminPermission adminPermission)
        throws Exception {
        TagTreeResult result = handler.execute(action, null);
        verify(tagManager).getTags();
        assertTrue(result.getTree().isEmpty());
    }


    @Test
    public void testNormalExecuteOneTags(
        Tags tagManager, IAdminPermission adminPermission)
        throws Exception {
        List<TagNode> list = newArrayList();
        list.add(new TagNode("1"));
        when(tagManager.getTags()).thenReturn(list);

        TagTreeResult result = handler.execute(action, null);
        verify(tagManager).getTags();
        assertEquals(1, result.getTree().size());
    }


    @Test(expected = SecurityException.class)
    public void testUnAuthorizedExecute(
        Tags tagManager, IAdminPermission adminPermission)
        throws Exception {
        doThrow(new SecurityException()).when(adminPermission)
            .checkAdminPermission();

        TagTreeResult result = handler.execute(action, null);
        fail();
    }
}
