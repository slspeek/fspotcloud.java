package fspotcloud.taskqueuedispatch;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import net.customware.gwt.dispatch.server.Dispatch;

import org.apache.commons.lang.SerializationUtils;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import com.meterware.servletunit.InvocationContext;
import com.meterware.servletunit.ServletRunner;
import com.meterware.servletunit.ServletTestCase;
import com.meterware.servletunit.ServletUnitClient;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class TaskQueueDispatchServletTest extends ServletTestCase {
	
	private static final String AUTE = "Aute";
	private static final String HI_AUTE = "Hi, Aute";
	ServletRunner sr;
	Dispatch dispatch;
	Injector injector;
	TestAction action;
	
	public TaskQueueDispatchServletTest(String name) {
		super(name);
	}

	@Before
	public void setUp() throws Exception {
		sr = new ServletRunner();
		sr.registerServlet("/taskqueue", TaskQueueDispatchServlet.class.getName());
		dispatch = mock(Dispatch.class);
		action = new TestAction(AUTE);
		when(dispatch.execute(action)).thenReturn(new TestResult(HI_AUTE));
		injector = Guice.createInjector(new TestModule());
	}

	@Test
	public void testOne() throws MalformedURLException, IOException, SAXException, ServletException {
		
		TestCallback callback = new TestCallback();
		AsyncCommand<TestAction, TestResult> command;
		command = new AsyncCommand<TestAction, TestResult>(callback, action);
		byte[] bytes = SerializationUtils.serialize(command);
		InputStream in = new ByteArrayInputStream(bytes);
		ServletUnitClient sc = sr.newClient();
		WebRequest request = new PostMethodWebRequest("http://test.meterware.com/taskqueue", in, "image/jpeg");
		InvocationContext ic = sc.newInvocation( request );
		TaskQueueDispatchServlet servlet = (TaskQueueDispatchServlet) ic.getServlet();
		servlet.dispatch = dispatch;
		servlet.injector = injector;
		
		
		servlet.service(ic.getRequest(), ic.getResponse());
		WebResponse response = ic.getServletResponse();
		
		
	}
	
}
