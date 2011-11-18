package fspotcloud.server.control.task.photoimport;

import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletTestCase;
import com.meterware.servletunit.ServletUnitClient;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class PhotoImportTaskServletTest extends ServletTestCase {

	private static final String TAGID = "FOO";
	private static final String MINKEY = "BAR";
	private static final String LIMIT = "10";
	private static final String OFFSET = "2";

	public PhotoImportTaskServletTest(String name) {
		super(name);
	}

	ServletRunner sr;
	PhotoImportScheduler scheduler;

	protected void setUp() throws Exception {
		super.setUp();
		sr = new ServletRunner();
		sr.registerServlet("myServlet", PhotoImportTaskServlet.class.getName());
		scheduler = mock(PhotoImportScheduler.class);
	}

	public void testOne() throws Exception {
		ServletUnitClient sc = sr.newClient();
		WebRequest request = new PostMethodWebRequest(
				"http://test.meterware.com/myServlet");
		request.setParameter("offset", OFFSET);
		request.setParameter("limit", LIMIT);
		request.setParameter("minKey", MINKEY);
		request.setParameter("tagId", TAGID);
		InvocationContext ic = sc.newInvocation( request );
		PhotoImportTaskServlet servlet  = (PhotoImportTaskServlet) ic.getServlet();
		servlet.scheduler = scheduler;
		servlet.service(ic.getRequest(), ic.getResponse());
		WebResponse response = ic.getServletResponse();
		assertTrue(response.confirm("DataTask ran."));
		assertNotNull(response);
		verify(scheduler).schedulePhotoImport(TAGID, MINKEY, 2, 10);
		
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		sr.shutDown();
	}

}
