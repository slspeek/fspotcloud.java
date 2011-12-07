package fspotcloud.server.control.task.tagdelete;

import static org.mockito.Mockito.*;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import fspotcloud.server.control.task.actions.intern.DeleteTags;
import fspotcloud.server.model.api.Tags;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.test.MockitoTestCase;

public class DeleteTagsHandlerTest extends MockitoTestCase {

	DeleteTagsHandler target;
	
	@Mock
	TaskQueueDispatch dispatchAsync;
	@Mock
	Tags tagManager;
	@Captor
	ArgumentCaptor<DeleteTags> newAction;
	
	protected void setUp() throws Exception {
		super.setUp();
		target = new DeleteTagsHandler(dispatchAsync, tagManager);
		System.out.println(tagManager);
	}

	public void testRecursionStop() throws DispatchException {
		when(tagManager.deleteAll()).thenReturn(true);
		target.execute(new DeleteTags(), null);
		verifyNoMoreInteractions(dispatchAsync);
	}
	
	public void testRecursion() throws DispatchException {
		when(tagManager.deleteAll()).thenReturn(false);
		target.execute(new DeleteTags(), null);
		verify(dispatchAsync).execute(newAction.capture());
		assertNotNull(newAction.getValue());
	}
}
