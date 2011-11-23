package fspotcloud.server.control.task.photoimport;

import static org.mockito.Mockito.*;
import junit.framework.TestCase;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.PhotoDataCallback;
import fspotcloud.server.control.task.actions.intern.PhotoImportScheduleAction;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.taskqueuedispatch.NullCallback;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class PhotoImportScheduleHandlerTest extends TestCase {

	private static final String TAG_ID = "2";
	private static final int MAX_PHOTO_TICKS = 2;
	private static final int MAX_TICKS = 4;
	TaskQueueDispatch delayed;
	ControllerDispatchAsync dispatch;

	PhotoImportScheduleHandler scheduler;
	ArgumentCaptor<GetPhotoData> actionCaptor1;
	ArgumentCaptor<PhotoDataCallback> callbackCaptor1;
	ArgumentCaptor<NullCallback> serverCallbackCaptor;
	

	
	protected void setUp() throws Exception {
		super.setUp();
		delayed = mock(TaskQueueDispatch.class);
		dispatch = mock(ControllerDispatchAsync.class);
		scheduler = new PhotoImportScheduleHandler( MAX_TICKS, MAX_PHOTO_TICKS,"1024x768",
				"512x384",  dispatch ,delayed);
		actionCaptor1 = ArgumentCaptor.forClass(GetPhotoData.class);
		callbackCaptor1 = ArgumentCaptor.forClass(PhotoDataCallback.class);
		serverCallbackCaptor = ArgumentCaptor.forClass(NullCallback.class);
	}

	public void testWORecursingSchedulePhotoImport() throws DispatchException {
		scheduler.execute(new PhotoImportScheduleAction(TAG_ID, "", 0, MAX_PHOTO_TICKS), null);
		verify(dispatch).execute(actionCaptor1.capture(), callbackCaptor1.capture());
		assertEquals(MAX_PHOTO_TICKS, actionCaptor1.getValue().getCount());
	}
	
	public void testSchedulePhotoImport() throws DispatchException {
		scheduler.execute(new PhotoImportScheduleAction(TAG_ID, "", 0, 9), null);
		verify(dispatch, times(MAX_TICKS)).execute(actionCaptor1.capture(), callbackCaptor1.capture());
		assertEquals(MAX_PHOTO_TICKS, actionCaptor1.getAllValues().get(0).getCount());
		assertEquals(MAX_PHOTO_TICKS, actionCaptor1.getAllValues().get(1).getCount());
		ArgumentCaptor<PhotoImportScheduleAction> captor = ArgumentCaptor.forClass(PhotoImportScheduleAction.class);
		
		verify(delayed).execute(captor.capture(), serverCallbackCaptor.capture());
		PhotoImportScheduleAction serverAction = captor.getValue();
		
		assertEquals(TAG_ID, serverAction.getTagId());
		assertEquals(MAX_TICKS * MAX_PHOTO_TICKS, serverAction.getOffset());
		assertEquals(1, serverAction.getLimit());
				
	}
}
