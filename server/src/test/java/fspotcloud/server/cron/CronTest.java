package fspotcloud.server.cron;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.DispatchException;

import org.mockito.ArgumentCaptor;

public class CronTest extends TestCase {
	public static TestSuite suite() {
		return new TestSuite(CronTest.class);
	}

	HttpServletRequest request;
	HttpServletResponse response;
	Cron cron;
	Dispatch dispatch;
	ArgumentCaptor<Action> captor;

	@Override
	protected void setUp() throws Exception {
		response = mock(HttpServletResponse.class);
		request = mock(HttpServletRequest.class);
		dispatch = mock(Dispatch.class);
		captor = ArgumentCaptor.forClass(Action.class);
		cron = new Cron(dispatch);
		super.setUp();
	}

	public void testImportImageData() throws IOException, DispatchException {
		when(request.getParameter("action")).thenReturn("import-image-data");
		cron.doGet(request, response);
		verify(dispatch).execute(captor.capture());
		assertEquals("ImportImageData", captor.getValue().getClass()
				.getSimpleName());
	}

	public void testCountPhotos() throws IOException, DispatchException {
		when(request.getParameter("action")).thenReturn("count-photos");
		cron.doGet(request, response);
		verify(dispatch).execute(captor.capture());
		assertEquals("CountPhotos", captor.getValue().getClass()
				.getSimpleName());
	}

}
