package fspotcloud.server.control.task.photoupdate;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import fspotcloud.shared.peer.rpc.actions.PhotoDataResult;
import fspotcloud.shared.peer.rpc.actions.PhotoUpdate;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;
import fspotcloud.test.MockitoTestCase;

public class PhotoUpdateHandlerTest extends MockitoTestCase {

	PhotoUpdateHandler handler;
	PhotoUpdateAction action;
	@Mock
	ControllerDispatchAsync dispatchAsync;
	@Mock
	TaskQueueDispatch recursive;
	@Captor
	ArgumentCaptor<GetPhotoData> captorAction;
	@Captor
	ArgumentCaptor<AsyncCallback<PhotoDataResult>> captorCallback;
	@Captor
	ArgumentCaptor<PhotoUpdateAction> recursiveActionCaptor;

	protected void setUp() throws Exception {
		super.setUp();
		handler = new PhotoUpdateHandler(1, 1, new ImageSpecs(1, 1, 1, 1),
				dispatchAsync, recursive);
		PhotoUpdate update = new PhotoUpdate("1");
		List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
		list.add(update);
		action = new PhotoUpdateAction(list);
	}

	public void testExecuteBase() throws DispatchException {
		handler.execute(action, null);
		verifyNoMoreInteractions(recursive);
		verify(dispatchAsync).execute(captorAction.capture(),
				captorCallback.capture());
		GetPhotoData request = captorAction.getValue();
		assertEquals(1, request.getImageKeys().size());
	}
	
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
		verify(dispatchAsync).execute(captorAction.capture(),
				captorCallback.capture());
		GetPhotoData request = captorAction.getValue();
		assertEquals(1, request.getImageKeys().size());
		assertEquals("1", request.getImageKeys().get(0));
		
		PhotoUpdateAction nextAction  = recursiveActionCaptor.getValue();
		assertEquals(1, nextAction.getUpdates().size());
		update = nextAction.getUpdates().get(0);
		assertEquals("2",update.getPhotoId());
	}

}
