package fspotcloud.server.control.reciever;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.TaskOptions;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;

public class MetaRecieverTest extends TestCase {

	MetaReciever reciever;

	Mockery context;

	public static TestSuite suite() {
		return new TestSuite(MetaRecieverTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		super.setUp();
	}

	public MetaReciever create(PeerDatabases defaultPeer, Queue queue) {
		return new MetaReciever(defaultPeer, queue);
	}

	public void testRecieveMetaData() {
		final Queue queue = context.mock(Queue.class);
		final PeerDatabases peerDatabases = context.mock(PeerDatabases.class);
		final PeerDatabase pd = new PeerDatabaseDO();
		final TaskOptions firstOptions = withUrl("/control/task/photoData")
				.param("offset", String.valueOf(0)).param("limit",
						String.valueOf(10));
		final TaskOptions secondOptions = withUrl("/control/task/photoData")
				.param("offset", String.valueOf(10)).param("limit",
						String.valueOf(11));

		context.checking(new Expectations() {
			{
				oneOf(peerDatabases).get();
				will(returnValue(pd));
				oneOf(queue).add(firstOptions);
				oneOf(peerDatabases).save(pd);
			}
		});
		reciever = create(peerDatabases, queue);
		reciever.recieveMetaData(10);
		context.assertIsSatisfied();
		context.checking(new Expectations() {
			{
				oneOf(peerDatabases).get();
				will(returnValue(pd));
				oneOf(queue).add(secondOptions);
				oneOf(peerDatabases).save(pd);
			}
		});
		reciever.recieveMetaData(21);
		context.assertIsSatisfied();

	}

	public void testTaskOptions() {
		final TaskOptions firstOptions = withUrl("control/task/photoData")
				.param("offset", String.valueOf(0)).param("limit",
						String.valueOf(10));
		final TaskOptions secondOptions = withUrl("control/task/photoData")
				.param("offset", String.valueOf(0)).param("limit",
						String.valueOf(10));
		assertEquals(firstOptions, secondOptions);
	}
}
