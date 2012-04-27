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
package com.googlecode.fspotcloud.server.control.task.tagimport;

import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.server.control.callback.TagDataCallback;
import com.googlecode.fspotcloud.server.control.task.actions.intern.TagImportAction;
import com.googlecode.fspotcloud.server.control.task.handler.intern.TagImportHandler;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetTagDataAction;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;

import org.testng.AssertJUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;


public class TagImportHandlerTest {
    @Mock
    TaskQueueDispatch recursiveCall;
    int MAX_DATA_TICKS = 2;
    @Mock
    ControllerDispatchAsync dispatch;
    @Captor
    ArgumentCaptor<TagImportAction> nextCallCaptor;
    @Captor
    ArgumentCaptor<GetTagDataAction> scheduledActionCaptor;
    @Captor
    ArgumentCaptor<TagDataCallback> callbackCaptor;
    TagImportAction action = new TagImportAction(0, 10);
    TagImportHandler handler;

    @BeforeMethod
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        handler = new TagImportHandler(dispatch, recursiveCall, MAX_DATA_TICKS);
    }


    @Test
    public void testExecute() {
        handler.execute(action, null);
        verify(recursiveCall).execute(nextCallCaptor.capture());

        TagImportAction next = nextCallCaptor.getValue();
        AssertJUnit.assertEquals(4, next.getOffset());
        AssertJUnit.assertEquals(6, next.getLimit());
        verify(dispatch, times(2))
            .execute(scheduledActionCaptor.capture(), callbackCaptor.capture());

        List<GetTagDataAction> scheduled = scheduledActionCaptor.getAllValues();
        AssertJUnit.assertEquals(2, scheduled.size());

        GetTagDataAction dataR1 = scheduled.get(0);
        GetTagDataAction dataR2 = scheduled.get(1);
        AssertJUnit.assertEquals(0, dataR1.getOffset());
        AssertJUnit.assertEquals(2, dataR2.getOffset());
    }
}
