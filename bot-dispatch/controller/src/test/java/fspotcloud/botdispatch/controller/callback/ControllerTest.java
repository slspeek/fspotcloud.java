package fspotcloud.botdispatch.controller.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.model.CommandModelModule;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandDO;
import fspotcloud.botdispatch.model.command.NullCommand;
import fspotcloud.botdispatch.test.HeavyReport;
import fspotcloud.botdispatch.test.HeavyReportModule;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestAsyncCallback;
import fspotcloud.botdispatch.test.TestResult;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class ControllerTest extends TestCase {

	Commands commandManager;
	TestAction action = new TestAction("Your name here");
	TestResult result = new TestResult("Hey you");
	byte[] serializedResult;
	AsyncCallback<TestResult> callback = new TestAsyncCallback();
	Injector injector;
	HeavyReport report;
	Controller controller;
	ResultHandlerFactory handlerFactory;
	CommandDO command;

	public void setUp() throws Exception {
		super.setUp();
		report = mock(HeavyReport.class);
		commandManager = mock(Commands.class);
		injector = Guice.createInjector(new ControllerModule(),
				new HeavyReportModule(report), new TestCommandModelModule(commandManager));
		handlerFactory = injector.getInstance(ResultHandlerFactory.class);
		
		command = new CommandDO(action, callback);
		command.setId(1);
		when(commandManager.getById(1)).thenReturn(command);
		when(commandManager.getAndLockFirstCommand()).thenReturn(new NullCommand());
		controller = new Controller(commandManager, handlerFactory);
		// Serialize to a byte array
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(result);
		out.close();

		// Get the bytes of the serialized object
		serializedResult = bos.toByteArray();

	}

	public void testCallback() throws IOException {
		assertNotNull(callback);
		Object[] back = controller.callback(1, serializedResult);
		Long l = (Long) back[0];
		assertEquals(-1, (int)l.longValue());
		assertEquals("Hey you", ((TestAsyncCallback) callback).getResult()
				.getMessage());
		verify(report).report("Hey you");
		verify(commandManager).delete(command);
	}

}
