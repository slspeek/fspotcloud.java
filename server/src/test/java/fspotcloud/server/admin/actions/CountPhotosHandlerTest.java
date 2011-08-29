package fspotcloud.server.admin.actions;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskOptions;

import fspotcloud.shared.dashboard.actions.CountPhotos;

public class CountPhotosHandlerTest extends TestCase {

	CountPhotos action = new CountPhotos();
	Queue queue;
	CountPhotosHandler handler;

	public static TestSuite suite() {
		return new TestSuite(CountPhotosHandlerTest.class);
	}

	public void setUp() throws Exception {
		queue = mock(Queue.class);
		handler = new CountPhotosHandler(queue);
	}

	public void testCountPhotos() {
		try {
			handler.execute(action, null);
		} catch (DispatchException e) {
			e.printStackTrace();
		}
		verify(queue).add(any(TaskOptions.class));
	}

	public void testException() {
		when(queue.add(any(TaskOptions.class))).thenThrow(
				RuntimeException.class);
		try {
			handler.execute(action, null);
			fail();
		} catch (DispatchException e) {

		}

	}

}
