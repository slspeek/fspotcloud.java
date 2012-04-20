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
package com.googlecode.fspotcloud.server.admin.actions;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.model.jpa.tag.TagEntity;
import com.googlecode.fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import com.googlecode.fspotcloud.server.model.api.Tag;
import com.googlecode.fspotcloud.server.model.api.Tags;
import com.googlecode.fspotcloud.shared.dashboard.actions.ImportTag;
import com.googlecode.fspotcloud.user.AdminPermission;

import com.googlecode.taskqueuedispatch.SerializableAsyncCallback;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import org.mockito.MockitoAnnotations;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ImportTagHandlerTest {
    private static final int COUNT = 10;
    private static final int PREVIOUS_COUNT = 6;
    private static final String TAG_ID = "Foo";
    @Mock
    private Tags tagManager;
    @Mock
    private ControllerDispatchAsync dispatch;
    @Mock
    AdminPermission adminPermission;
    ImportTagHandler handler;
    ImportTag action = new ImportTag(TAG_ID, PREVIOUS_COUNT);
    Tag tag;
    ArgumentCaptor<PhotoUpdateAction> actionCaptor;
    ArgumentCaptor<SerializableAsyncCallback> callbackCaptor;

    @BeforeMethod
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        handler = new ImportTagHandler(tagManager, dispatch, adminPermission);
        tag = new TagEntity();
        tag.setId(TAG_ID);
        tag.setCount(COUNT);
        when(tagManager.find(TAG_ID)).thenReturn(tag);
    }


    @Test
    public void testExecute() throws DispatchException {
    }


    @Test
    public void testExecuteAllreadyImported() throws DispatchException {
    }
}
