package fspotcloud.botdispatch.bot;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import junit.framework.TestCase;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Guice;
import com.google.inject.Injector;

import fspotcloud.botdispatch.test.ActionsModule;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestResult;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class BotDispatchServerTest extends TestCase {

	Pauser pauser;
	CommandWorkerFactory factory;
	Injector injector = Guice.createInjector(new BotModule(),
			new ActionsModule());
	RemoteExecutor remote;
	BotDispatchServerImpl target;
	TestAction action = new TestAction("Josie");
	TestResult result = new TestResult("Hello to you, Josie");
	private byte[] serializedAction;
	private byte[] serializedResult;

	protected void setUp() throws Exception {
		pauser = mock(Pauser.class);
		remote = mock(RemoteExecutor.class);
		factory = injector.getInstance(CommandWorkerFactory.class);
		// Serialize to a byte array
	    ByteArrayOutputStream bos = new ByteArrayOutputStream() ;
	    ObjectOutputStream out = new ObjectOutputStream(bos) ;
	    out.writeObject(action);
	    out.close();
		serializedAction = bos.toByteArray(); 
		bos = new ByteArrayOutputStream() ;
	    out = new ObjectOutputStream(bos) ;
	    out.writeObject(result);
	    out.close();
		serializedResult = bos.toByteArray(); 
		
		target = new BotDispatchServerImpl(remote, factory, pauser, 10000);
		super.setUp();
	}

	public void testEmptyStart() throws XmlRpcException, IOException, ClassNotFoundException {
		when(remote.execute(-1L,null)).thenReturn(new Object[] { -1L, null});
		target.runForever(1);
		verify(pauser).pause(10000);
	}
	
	public void testStart() throws XmlRpcException, IOException, ClassNotFoundException {
		when(remote.execute(-1L,null)).thenReturn(new Object[] { 1000L, serializedAction});
		when(remote.execute(1000L,serializedResult)).thenReturn(new Object[] { -1L, null});
		target.runForever(2);
		verify(remote).execute(1000L, serializedResult);
		verify(pauser).pause(10000);
	}

	

}
