package fspotcloud.server.control.task.photoimport;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import junit.framework.TestCase;

import org.mockito.ArgumentCaptor;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.callback.PhotoDataCallback;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;

public class DefaultPhotoImportSchedulerTest extends TestCase {

	private static final String TAG_ID = "2";
	private static final int MAX_PHOTO_TICKS = 2;
	private static final int MAX_TICKS = 4;
	PhotoImportScheduler delayed;
	ControllerDispatchAsync dispatch;

	DefaultPhotoImportScheduler scheduler;
	ArgumentCaptor<GetPhotoData> actionCaptor1;
	ArgumentCaptor<PhotoDataCallback> callbackCaptor1;
	ArgumentCaptor<GetPhotoData> actionCaptor2;
	ArgumentCaptor<PhotoDataCallback> callbackCaptor2;

	
	protected void setUp() throws Exception {
		super.setUp();
		delayed = mock(PhotoImportScheduler.class);
		dispatch = mock(ControllerDispatchAsync.class);
		scheduler = new DefaultPhotoImportScheduler(dispatch, MAX_TICKS, MAX_PHOTO_TICKS, "1024x768",
				"512x384", delayed);
		actionCaptor1 = ArgumentCaptor.forClass(GetPhotoData.class);
		callbackCaptor1 = ArgumentCaptor.forClass(PhotoDataCallback.class);
		actionCaptor2 = ArgumentCaptor.forClass(GetPhotoData.class);
		callbackCaptor2 = ArgumentCaptor.forClass(PhotoDataCallback.class);
	}

	public void testWORecursingSchedulePhotoImport() {
		scheduler.schedulePhotoImport(TAG_ID, "", 0, MAX_PHOTO_TICKS);
		verify(dispatch).execute(actionCaptor1.capture(), callbackCaptor1.capture());
		assertEquals(MAX_PHOTO_TICKS, actionCaptor1.getValue().getCount());
		
	}
	
	public void testSchedulePhotoImport() {
		scheduler.schedulePhotoImport(TAG_ID, "", 0, 9);
		verify(dispatch, times(MAX_TICKS)).execute(actionCaptor1.capture(), callbackCaptor1.capture());
		assertEquals(MAX_PHOTO_TICKS, actionCaptor1.getAllValues().get(0).getCount());
		assertEquals(MAX_PHOTO_TICKS, actionCaptor1.getAllValues().get(1).getCount());
		verify(delayed).schedulePhotoImport(TAG_ID, "", MAX_TICKS * MAX_PHOTO_TICKS, 1);
	}
}
