package fspotcloud.server.control.task.tagdelete;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.AssertJUnit;
import static org.mockito.Mockito.*;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import fspotcloud.server.control.task.actions.intern.DeleteTags;
import fspotcloud.server.model.api.Tags;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.test.MockitoTestCase;

public class DeleteTagsHandlerTest {

	DeleteTagsHandler target;
	
	@Mock
	TaskQueueDispatch dispatchAsync;
	@Mock
	Tags tagManager;
	@Captor
	ArgumentCaptor<DeleteTags> newAction;
	
	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		target = new DeleteTagsHandler(dispatchAsync, tagManager);
		System.out.println(tagManager);
	}

	@Test
	public void testRecursionStop() throws DispatchException {
		when(tagManager.deleteAll()).thenReturn(true);
		target.execute(new DeleteTags(), null);
		verifyNoMoreInteractions(dispatchAsync);
	}
	
	@Test
	public void testRecursion() throws DispatchException {
		when(tagManager.deleteAll()).thenReturn(false);
		target.execute(new DeleteTags(), null);
		verify(dispatchAsync).execute(newAction.capture());
		AssertJUnit.assertNotNull(newAction.getValue());
	}
}
