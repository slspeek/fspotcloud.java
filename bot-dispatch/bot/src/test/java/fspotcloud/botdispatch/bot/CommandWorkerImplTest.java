package fspotcloud.botdispatch.bot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import junit.framework.TestCase;
import net.customware.gwt.dispatch.server.Dispatch;
import net.customware.gwt.dispatch.shared.Action;

import org.apache.xmlrpc.XmlRpcException;
import org.mockito.ArgumentCaptor;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.test.ActionsModule;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestResult;

public class CommandWorkerImplTest extends TestCase {

	private Dispatch dispatch;

	private Action<?> action;
	Injector injector;

	CommandWorkerImpl target;

	@Override
	protected void setUp() throws Exception {
		action = new TestAction("Richard");
		injector = Guice.createInjector(new ActionsModule());
		dispatch = injector.getInstance(Dispatch.class);
		target = new CommandWorkerImpl(dispatch, action);
		super.setUp();
	}

	public void testRun() throws XmlRpcException, IOException,
			ClassNotFoundException {
		byte[] resultInBytes = target.doExecute();
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
				resultInBytes));
		TestResult testResult = (TestResult) in.readObject();
		in.close();
		assertEquals("Hello to you, Richard", testResult.getMessage());
	}

}
