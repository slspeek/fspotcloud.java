/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.control.task.handler.intern;

import com.googlecode.fspotcloud.server.control.task.actions.intern.DeleteAllPhotosAction;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import javax.inject.Inject;
import net.customware.gwt.dispatch.server.ExecutionContext;
import org.jukito.JukitoRunner;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
/**
 *
 * @author steven
 */
@RunWith(JukitoRunner.class)
public class DeleteAllPhotosHandlerTest {

    @Inject
    private DeleteAllPhotosHandler instance;

    @Test
    public void testExecuteWithRecursion(Photos photos, TaskQueueDispatch dispatch) throws Exception {
        DeleteAllPhotosAction action = new DeleteAllPhotosAction();
        ExecutionContext context = null;
        VoidResult expResult = new VoidResult();
        VoidResult result = instance.execute(action, context);
        assertEquals(expResult, result);
        verify(dispatch).execute(new DeleteAllPhotosAction());
        verify(photos).deleteBulk(30);
        verify(photos).isEmpty();
        verifyNoMoreInteractions(dispatch, photos);
    }
    
    @Test
    public void testExecuteWithoutRecursion(Photos photos, TaskQueueDispatch dispatch) throws Exception {
        when(photos.isEmpty()).thenReturn(Boolean.TRUE);
        DeleteAllPhotosAction action = new DeleteAllPhotosAction();
        ExecutionContext context = null;
        VoidResult expResult = new VoidResult();
        VoidResult result = instance.execute(action, context);
        assertEquals(expResult, result);
        verify(photos).deleteBulk(30);
        verify(photos).isEmpty();
        verifyNoMoreInteractions(dispatch, photos);
    }
}
