package fspotcloud.botdispatch.controller.callback;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.jdo.PersistenceManager;

import org.mockito.ArgumentCaptor;

import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provider;

import fspotcloud.botdispatch.controller.inject.ControllerModule;
import fspotcloud.botdispatch.controller.inject.NullControllerHook;
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
import fspotcloud.botdispatch.test.ThrowingAction;

public class ControllerIntegrationTest extends DatastoreTest {

	Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();
	DispatchException error = new ActionException("Wrong");
	Commands commandManager;
	TestAction action = new TestAction("Your name here");
	TestResult result = new TestResult("Hey you");
	ThrowingAction throwing = new ThrowingAction("Demian");
	byte[] serializedResult;
	byte[] serializedError;
	AsyncCallback<TestResult> callback = new TestAsyncCallback();
	Injector injector;
	HeavyReport report;
	Controller controller;
	ResultHandlerFactory handlerFactory;
	ErrorHandlerFactory errorHandlerFactory;
	ArgumentCaptor<Throwable> captor;

	public void setUp() {
		super.setUp();
		report = mock(HeavyReport.class);
		injector = Guice.createInjector(new ControllerModule(),
				new HeavyReportModule(report), new CommandModelModule());
		handlerFactory = injector.getInstance(ResultHandlerFactory.class);
		errorHandlerFactory = injector.getInstance(ErrorHandlerFactory.class);
		captor = ArgumentCaptor.forClass(Throwable.class);
		commandManager = new CommandManager(pmProvider);
		controller = new Controller(commandManager, handlerFactory,
				errorHandlerFactory, new NullControllerHook());
		// Serialize to a byte array
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(result);
			out.close();

			// Get the bytes of the serialized object
			serializedResult = bos.toByteArray();
			// Serialize to a byte array
			bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(error);
			out.close();

			// Get the bytes of the serialized object
			serializedError = bos.toByteArray();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void testCallback() throws IOException {
		Command cmd = commandManager.createAndSave(action, callback);
		Object[] back = controller.callback(cmd.getId(), serializedResult);
		assertEquals(-1L, back[0]);
		verify(report).report("Hey you");
	}
	
	public void testOnError() throws IOException {
		Command cmd = commandManager.createAndSave(throwing, callback);
		Object[] back = controller.callback(cmd.getId(), serializedError);
		//assertEquals(-1L, back[0]);
		verify(report).error(captor.capture());
	}


	public void testDoubleCallback() throws IOException {
		Command cmd1 = commandManager.createAndSave(action, callback);
		Command cmd2 = commandManager.createAndSave(action, callback);
		Object[] back = controller.callback(cmd1.getId(), serializedResult);
		assertEquals(cmd2.getId(), back[0]);
		verify(report).report("Hey you");
	}
}
