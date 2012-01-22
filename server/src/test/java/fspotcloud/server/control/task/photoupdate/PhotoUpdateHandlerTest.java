package fspotcloud.server.control.task.photoupdate;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import java.util.ArrayList;
import java.util.List;

import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fspotcloud.botdispatch.controller.dispatch.ControllerDispatchAsync;
import fspotcloud.server.control.task.actions.intern.PhotoUpdateAction;
import fspotcloud.shared.peer.rpc.actions.GetPhotoData;
import fspotcloud.shared.peer.rpc.actions.ImageSpecs;
import fspotcloud.shared.peer.rpc.actions.PhotoDataResult;
import fspotcloud.shared.peer.rpc.actions.PhotoUpdate;
import fspotcloud.taskqueuedispatch.TaskQueueDispatch;

public class PhotoUpdateHandlerTest {

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

	@BeforeMethod
	protected void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		handler = new PhotoUpdateHandler(1, 1, new ImageSpecs(1, 1, 1, 1),
				controllerAsync, recursive);
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
		verify(controllerAsync).execute(captorAction.capture(),
				captorCallback.capture());
		GetPhotoData request = captorAction.getValue();
		AssertJUnit.assertEquals(1, request.getImageKeys().size());
		AssertJUnit.assertEquals("1", request.getImageKeys().get(0));
		
		PhotoUpdateAction nextAction  = recursiveActionCaptor.getValue();
		AssertJUnit.assertEquals(1, nextAction.getUpdates().size());
		update = nextAction.getUpdates().get(0);
		AssertJUnit.assertEquals("2",update.getPhotoId());
	}

	@Test
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
		AssertJUnit.assertEquals(2, actionList.size());
		AssertJUnit.assertEquals(MAX_PHOTO_TICKS, actionList.get(0).getImageKeys().size());
		AssertJUnit.assertEquals("1", actionList.get(0).getImageKeys().get(0));
		AssertJUnit.assertEquals("2", actionList.get(0).getImageKeys().get(1));
		AssertJUnit.assertEquals("3", actionList.get(0).getImageKeys().get(2));
		AssertJUnit.assertEquals("4", actionList.get(1).getImageKeys().get(0));
		AssertJUnit.assertEquals("5", actionList.get(1).getImageKeys().get(1));
		AssertJUnit.assertEquals("6", actionList.get(1).getImageKeys().get(2));
		
		PhotoUpdateAction nextAction  = recursiveActionCaptor.getValue();
		AssertJUnit.assertEquals(2, nextAction.getUpdates().size());
		update = nextAction.getUpdates().get(0);
		AssertJUnit.assertEquals("7",update.getPhotoId());
		update = nextAction.getUpdates().get(1);
		AssertJUnit.assertEquals("8",update.getPhotoId());
	}

	@Test
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
		AssertJUnit.assertEquals(MAX_PHOTO_TICKS, request.get(0).getImageKeys().size());
		AssertJUnit.assertEquals("1", request.get(0).getImageKeys().get(0));
		AssertJUnit.assertEquals("2", request.get(0).getImageKeys().get(1));
		AssertJUnit.assertEquals("3", request.get(0).getImageKeys().get(2));
		AssertJUnit.assertEquals("4", request.get(1).getImageKeys().get(0));
		AssertJUnit.assertEquals("5", request.get(1).getImageKeys().get(1));
	}

	
}
