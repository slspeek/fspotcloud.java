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

	private static final int MAX_PHOTO_TICKS = 3;
	PhotoUpdateHandler handler;
	PhotoUpdateAction action;
	@Mock
	ControllerDispatchAsync controllerAsync;
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
				controllerAsync, recursive);
		PhotoUpdate update = new PhotoUpdate("1");
		List<PhotoUpdate> list = new ArrayList<PhotoUpdate>();
		list.add(update);
		action = new PhotoUpdateAction(list);
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
		verify(controllerAsync).execute(captorAction.capture(),
				captorCallback.capture());
		GetPhotoData request = captorAction.getValue();
		assertEquals(1, request.getImageKeys().size());
		assertEquals("1", request.getImageKeys().get(0));
		
		PhotoUpdateAction nextAction  = recursiveActionCaptor.getValue();
		assertEquals(1, nextAction.getUpdates().size());
		update = nextAction.getUpdates().get(0);
		assertEquals("2",update.getPhotoId());
	}

	public void testExecute() throws DispatchException {
		handler = new PhotoUpdateHandler(2, MAX_PHOTO_TICKS, new ImageSpecs(1, 1, 1, 1),
				controllerAsync, recursive);
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
		verify(controllerAsync, times(2)).execute(captorAction.capture(),
				captorCallback.capture());
		List<GetPhotoData> actionList = captorAction.getAllValues();
		assertEquals(2, actionList.size());
		assertEquals(MAX_PHOTO_TICKS, actionList.get(0).getImageKeys().size());
		assertEquals("1", actionList.get(0).getImageKeys().get(0));
		assertEquals("2", actionList.get(0).getImageKeys().get(1));
		assertEquals("3", actionList.get(0).getImageKeys().get(2));
		assertEquals("4", actionList.get(1).getImageKeys().get(0));
		assertEquals("5", actionList.get(1).getImageKeys().get(1));
		assertEquals("6", actionList.get(1).getImageKeys().get(2));
		
		PhotoUpdateAction nextAction  = recursiveActionCaptor.getValue();
		assertEquals(2, nextAction.getUpdates().size());
		update = nextAction.getUpdates().get(0);
		assertEquals("7",update.getPhotoId());
		update = nextAction.getUpdates().get(1);
		assertEquals("8",update.getPhotoId());
	}

	public void testExecute5() throws DispatchException {
		handler = new PhotoUpdateHandler(2, MAX_PHOTO_TICKS, new ImageSpecs(1, 1, 1, 1),
				controllerAsync, recursive);
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
		verify(controllerAsync, times(2)).execute(captorAction.capture(),
				captorCallback.capture());
		List<GetPhotoData> request = captorAction.getAllValues();
		assertEquals(MAX_PHOTO_TICKS, request.get(0).getImageKeys().size());
		assertEquals("1", request.get(0).getImageKeys().get(0));
		assertEquals("2", request.get(0).getImageKeys().get(1));
		assertEquals("3", request.get(0).getImageKeys().get(2));
		assertEquals("4", request.get(1).getImageKeys().get(0));
		assertEquals("5", request.get(1).getImageKeys().get(1));
		
	}

	
}
