package fspotcloud.server.admin.actions;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;

import fspotcloud.server.control.task.actions.intern.PhotoImportScheduleAction;
import fspotcloud.server.model.api.Tags;
import fspotcloud.server.model.tag.TagDO;
import fspotcloud.shared.dashboard.actions.ImportTag;
import fspotcloud.taskqueuedispatch.SerializableAsyncCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
public class ImportTagHandlerTest extends TestCase {

	
	private static final int COUNT = 10;
	private static final int PREVIOUS_COUNT = 6;
	private static final String TAG_ID = "Foo";
	private Tags tagManager;
	private TaskQueueDispatch dispatch;
	ImportTagHandler handler;
	ImportTag action = new ImportTag(TAG_ID, PREVIOUS_COUNT);
	TagDO tag;
	ArgumentCaptor<PhotoImportScheduleAction> actionCaptor;
	ArgumentCaptor<SerializableAsyncCallback> callbackCaptor;

	protected void setUp() throws Exception {
		super.setUp();
		tagManager = mock(Tags.class);
		dispatch = mock(TaskQueueDispatch.class);
		handler = new ImportTagHandler(tagManager, dispatch);
		tag = new TagDO();
		tag.setId(TAG_ID);
		tag.setCount(COUNT);
		when(tagManager.getById(TAG_ID)).thenReturn(tag);
		actionCaptor = ArgumentCaptor.forClass(PhotoImportScheduleAction.class);
		callbackCaptor = ArgumentCaptor.forClass(SerializableAsyncCallback.class);
	}

	public void testExecute() throws DispatchException {
		handler.execute(action, null);
		verify(dispatch).execute(actionCaptor.capture(), callbackCaptor.capture());
		PhotoImportScheduleAction action = actionCaptor.getValue();
		assertEquals(TAG_ID, action.getTagId());
		
		verify(tagManager).save(tag);
	}
	
	public void testExecuteAllreadyImported() throws DispatchException {
		tag.setImportIssued(true);
		handler.execute(action, null);
		verify(dispatch).execute(actionCaptor.capture(), callbackCaptor.capture());
		PhotoImportScheduleAction action = actionCaptor.getValue();
		assertEquals(TAG_ID, action.getTagId());
		//verifyNoMoreInteractions(manager);
	}

}
