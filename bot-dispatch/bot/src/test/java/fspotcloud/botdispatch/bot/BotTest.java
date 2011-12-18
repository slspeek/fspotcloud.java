package fspotcloud.botdispatch.bot;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.xmlrpc.XmlRpcException;

import com.google.inject.Provider;
public class BotTest extends TestCase {

	private static final int PAUSE = 1;
	BotDispatchServer server;
	Provider<BotDispatchServer> provider;
	Bot target;
	Pauser pauser;
	@Override
	protected void setUp() throws Exception {
		server = mock(BotDispatchServer.class);
		pauser = mock(Pauser.class);
		provider = new Provider<BotDispatchServer>() {

			@Override
			public BotDispatchServer get() {
				return server;
			}
			
		};
		target = new Bot(provider, pauser, PAUSE);
		super.setUp();
	}
	
	
	public void testRunOnce() throws XmlRpcException, IOException, ClassNotFoundException, InterruptedException {
		target.runForever(1);
		verify(server).runForever(1);
	}
	
	public void testRunOnceOnException() throws XmlRpcException, IOException, ClassNotFoundException, InterruptedException {
		doThrow(new RuntimeException()).when(server).runForever(1);
		target.runForever(1);
		verify(server).runForever(1);
	}
	
	public void testRunTwiceOnException() throws XmlRpcException, IOException, ClassNotFoundException, InterruptedException {
		doThrow(new RuntimeException()).when(server).runForever(2);
		target.runForever(2);
		verify(server, times(2)).runForever(2);
	}

}
