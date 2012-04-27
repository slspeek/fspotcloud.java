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
package com.googlecode.fspotcloud.server.control.task.photoupdate;

import com.googlecode.botdispatch.SerializableAsyncCallback;
import com.googlecode.botdispatch.controller.dispatch.ControllerDispatchAsync;

import com.googlecode.fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import com.googlecode.fspotcloud.server.control.task.handler.intern.PhotoUpdateHandler;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.GetPhotoDataAction;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoDataResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoUpdate;

import com.googlecode.taskqueuedispatch.TaskQueueDispatch;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.mockito.MockitoAnnotations;

import org.testng.AssertJUnit;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class PhotoUpdateHandlerTest {
    private static final int MAX_PHOTO_TICKS = 3;
    PhotoUpdateHandler handler;
    PhotoUpdateAction action;
    @Mock
    ControllerDispatchAsync controllerAsync;
    @Mock
    TaskQueueDispatch recursive;
    @Captor
    ArgumentCaptor<GetPhotoDataAction> captorAction;
    @Captor
    ArgumentCaptor<SerializableAsyncCallback<PhotoDataResult>> captorCallback;
    @Captor
    ArgumentCaptor<PhotoUpdateAction> recursiveActionCaptor;

    @BeforeMethod
    protected void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        handler = new PhotoUpdateHandler(
                1, 1, new ImageSpecs(1, 1, 1, 1), controllerAsync, recursive);

        PhotoUpdate update = new PhotoUpdate("1");
        List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
        list.add(update);
        action = new PhotoUpdateAction(list);
    }


    @Test
    public void testExecuteRecursive() throws DispatchException {
        PhotoUpdate update = new PhotoUpdate("1");
        update = new PhotoUpdate("1");

        List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
        list.add(update);
        update = new PhotoUpdate("2");
        list.add(update);
        action = new PhotoUpdateAction(list);
        handler.execute(action, null);
        verify(recursive).execute(recursiveActionCaptor.capture());
        verify(controllerAsync)
            .execute(captorAction.capture(), captorCallback.capture());

        GetPhotoDataAction request = captorAction.getValue();
        AssertJUnit.assertEquals(1, request.getImageKeys().size());
        AssertJUnit.assertEquals("1", request.getImageKeys().get(0));

        PhotoUpdateAction nextAction = recursiveActionCaptor.getValue();
        AssertJUnit.assertEquals(1, nextAction.getUpdates().size());
        update = nextAction.getUpdates().get(0);
        AssertJUnit.assertEquals("2", update.getPhotoId());
    }


    @Test
    public void testExecute() throws DispatchException {
        handler = new PhotoUpdateHandler(
                2, MAX_PHOTO_TICKS, new ImageSpecs(1, 1, 1, 1), controllerAsync,
                recursive);

        PhotoUpdate update = new PhotoUpdate("1");
        update = new PhotoUpdate("1");

        List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
        list.add(update);
        update = new PhotoUpdate("2");
        list.add(update);
        update = new PhotoUpdate("3");
        list.add(update);
        update = new PhotoUpdate("4");
        list.add(update);
        update = new PhotoUpdate("5");
        list.add(update);
        update = new PhotoUpdate("6");
        list.add(update);
        update = new PhotoUpdate("7");
        list.add(update);
        update = new PhotoUpdate("8");
        list.add(update);
        action = new PhotoUpdateAction(list);
        handler.execute(action, null);
        verify(recursive).execute(recursiveActionCaptor.capture());
        verify(controllerAsync, times(2))
            .execute(captorAction.capture(), captorCallback.capture());

        List<GetPhotoDataAction> actionList = captorAction.getAllValues();
        AssertJUnit.assertEquals(2, actionList.size());
        AssertJUnit.assertEquals(
            MAX_PHOTO_TICKS, actionList.get(0).getImageKeys().size());
        AssertJUnit.assertEquals("1", actionList.get(0).getImageKeys().get(0));
        AssertJUnit.assertEquals("2", actionList.get(0).getImageKeys().get(1));
        AssertJUnit.assertEquals("3", actionList.get(0).getImageKeys().get(2));
        AssertJUnit.assertEquals("4", actionList.get(1).getImageKeys().get(0));
        AssertJUnit.assertEquals("5", actionList.get(1).getImageKeys().get(1));
        AssertJUnit.assertEquals("6", actionList.get(1).getImageKeys().get(2));

        PhotoUpdateAction nextAction = recursiveActionCaptor.getValue();
        AssertJUnit.assertEquals(2, nextAction.getUpdates().size());
        update = nextAction.getUpdates().get(0);
        AssertJUnit.assertEquals("7", update.getPhotoId());
        update = nextAction.getUpdates().get(1);
        AssertJUnit.assertEquals("8", update.getPhotoId());
    }


    @Test
    public void testExecute5() throws DispatchException {
        handler = new PhotoUpdateHandler(
                2, MAX_PHOTO_TICKS, new ImageSpecs(1, 1, 1, 1), controllerAsync,
                recursive);

        PhotoUpdate update = new PhotoUpdate("1");
        update = new PhotoUpdate("1");

        List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
        list.add(update);
        update = new PhotoUpdate("2");
        list.add(update);
        update = new PhotoUpdate("3");
        list.add(update);
        update = new PhotoUpdate("4");
        list.add(update);
        update = new PhotoUpdate("5");
        list.add(update);
        action = new PhotoUpdateAction(list);
        handler.execute(action, null);
        verifyNoMoreInteractions(recursive);
        verify(controllerAsync, times(2))
            .execute(captorAction.capture(), captorCallback.capture());

        List<GetPhotoDataAction> request = captorAction.getAllValues();
        AssertJUnit.assertEquals(
            MAX_PHOTO_TICKS, request.get(0).getImageKeys().size());
        AssertJUnit.assertEquals("1", request.get(0).getImageKeys().get(0));
        AssertJUnit.assertEquals("2", request.get(0).getImageKeys().get(1));
        AssertJUnit.assertEquals("3", request.get(0).getImageKeys().get(2));
        AssertJUnit.assertEquals("4", request.get(1).getImageKeys().get(0));
        AssertJUnit.assertEquals("5", request.get(1).getImageKeys().get(1));
    }
}
