package fspotcloud.server.control;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.server.model.api.Commands;
import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;

public class ControllerTest extends TestCase {

	public static TestSuite suite() {
		return new TestSuite(ControllerTest.class);
	}

	Mockery context = new Mockery();

	public final void testGetCommand() {
		final Commands commandsMock = context.mock(Commands.class);
		final PeerDatabases peerDatabases = context.mock(PeerDatabases.class);
		final PeerDatabase pd = new PeerDatabaseDO();
		
		final Controller controller = new Controller(commandsMock, peerDatabases);
		context.checking(new Expectations() {
			{
				oneOf(commandsMock).popOldestCommand();
				oneOf(peerDatabases).get();will(returnValue(pd));
				oneOf(peerDatabases).save(pd);
			}
		});
		Object[] cmd = controller.getCommand();
		assertNotNull(cmd);
		context.assertIsSatisfied();
	}

}
