package fspotcloud.botdispatch.controller.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.jdo.PersistenceManager;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.model.CommandModelModule;
import fspotcloud.botdispatch.model.DatastoreTest;
import fspotcloud.botdispatch.model.PersistenceManagerProvider;
import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.model.command.CommandManager;
import fspotcloud.botdispatch.test.HeavyReport;
import fspotcloud.botdispatch.test.HeavyReportModule;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestAsyncCallback;
import fspotcloud.botdispatch.test.TestResult;
public class ControllerIntegrationTest extends DatastoreTest {
	public ControllerIntegrationTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();
	Commands commandManager;
	TestAction action = new TestAction("Your name here");
	TestResult result = new TestResult("Hey you");
	byte[] serializedResult;
	AsyncCallback<TestResult> callback = new TestAsyncCallback();
	Injector injector;
	HeavyReport report;
	Controller controller;
	ResultHandlerFactory handlerFactory;

	public void setUp() {
		super.setUp();
		report = mock(HeavyReport.class);
		injector = Guice.createInjector(new ControllerModule(),
				new HeavyReportModule(report), new CommandModelModule());
		handlerFactory = injector.getInstance(ResultHandlerFactory.class);
		commandManager = new CommandManager(pmProvider);
		controller = new Controller(commandManager, handlerFactory);
		// Serialize to a byte array
		try {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(bos);
		out.writeObject(result);
		out.close();

		// Get the bytes of the serialized object
		serializedResult = bos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void testCallback() throws IOException {
		Command cmd = commandManager.createAndSave(action, callback);
		long id = cmd.getId();
		Object[] back = controller.callback(cmd.getId(), serializedResult);
		assertEquals(id, back[0]);
		verify(report).report("Hey you");
	}

}
