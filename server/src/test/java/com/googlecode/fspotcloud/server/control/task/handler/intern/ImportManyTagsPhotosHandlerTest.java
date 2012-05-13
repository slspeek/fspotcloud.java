/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.googlecode.fspotcloud.server.control.task.handler.intern;

import com.googlecode.fspotcloud.server.control.task.actions.intern.ImportManyTagsPhotosAction;
import com.googlecode.fspotcloud.server.model.api.Photos;
import com.googlecode.fspotcloud.shared.dashboard.VoidResult;
import com.googlecode.taskqueuedispatch.TaskQueueDispatch;
import javax.inject.Inject;
import net.customware.gwt.dispatch.server.ExecutionContext;
import org.jukito.JukitoRunner;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import static com.google.common.collect.Lists.*;
import com.google.inject.name.Names;
import com.googlecode.fspotcloud.shared.dashboard.UserImportsTagAction;
import org.jukito.JukitoModule;
import static org.mockito.Mockito.*;

/**
 *
 * @author steven
 */
@RunWith(JukitoRunner.class)
public class ImportManyTagsPhotosHandlerTest {
    
     public static class Module extends JukitoModule {
    protected void configureTest() {
        bind(Integer.class).annotatedWith(Names.named("maxTicks")).toInstance(new Integer(2));
    }
  }

    
    @Inject
    ImportManyTagsPhotosHandler instance;
   
        ImportManyTagsPhotosAction action = new ImportManyTagsPhotosAction(newArrayList("1", "2", "3"));
        ImportManyTagsPhotosAction secondAction = new ImportManyTagsPhotosAction(newArrayList("3"));
    /**
     * Test of execute method, of class ImportManyTagsPhotosHandler.
     */
    @Test
    public void testExecute(TaskQueueDispatch dispatchAsync) throws Exception {
        System.out.println("execute");
        ExecutionContext context = null;
        VoidResult expResult = new VoidResult();
        VoidResult result = instance.execute(action, context);
        assertEquals(expResult, result);
        
        verify(dispatchAsync).execute(new UserImportsTagAction("1"));
        verify(dispatchAsync).execute(new UserImportsTagAction("2"));
        verify(dispatchAsync).execute(secondAction);
        verifyNoMoreInteractions(dispatchAsync);
    }
}
