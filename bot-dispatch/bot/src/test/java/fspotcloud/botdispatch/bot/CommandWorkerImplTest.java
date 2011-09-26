package fspotcloud.botdispatch.bot;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

public class CommandWorkerImplTest extends TestCase {

	private ResultSender sender;
	private Dispatch dispatch;

	private Action<?> action;
	private String callbackId;
	Injector injector;
	ArgumentCaptor<Object[]> resultCaptor;
	ArgumentCaptor<String> remoteMethodCaptor;

	CommandWorkerImpl target;

	@Override
	protected void setUp() throws Exception {
		sender = mock(ResultSender.class);
		action = new TestAction("Richard");
		callbackId = "GNU";
		injector = Guice.createInjector(new BotModule(), new ActionsModule());
		dispatch = injector.getInstance(Dispatch.class);
		resultCaptor = ArgumentCaptor.forClass(Object[].class);
		remoteMethodCaptor = ArgumentCaptor.forClass(String.class);
		target = new CommandWorkerImpl(sender, dispatch, action, callbackId);
		super.setUp();
	}

	public void testRun() throws XmlRpcException, IOException,
			ClassNotFoundException {
		target.run();
		verify(sender).sendResult(remoteMethodCaptor.capture(),
				resultCaptor.capture());
		Object[] result = resultCaptor.getValue();
		byte[] resultInBytes = (byte[]) result[1];
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(
				resultInBytes));
		TestResult testResult = (TestResult) in.readObject();
		in.close();

		assertEquals("Hello to you, Richard", testResult.getMessage());
	}

}
