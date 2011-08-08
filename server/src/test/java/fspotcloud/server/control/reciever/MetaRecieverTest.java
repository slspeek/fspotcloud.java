package fspotcloud.server.control.reciever;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.server.control.task.DataScheduler;
import fspotcloud.server.control.task.DataSchedulerFactory;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.server.model.peerdatabase.PeerDatabaseDO;

public class MetaRecieverTest extends TestCase {

	MetaReciever reciever;
	Mockery context;
	PeerDatabaseDO pd;
	PeerDatabases peerDatabases;
	DataSchedulerFactory factory;
	DataScheduler scheduler;
	DataScheduler scheduler2;

	public static TestSuite suite() {
		return new TestSuite(MetaRecieverTest.class);
	}

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		peerDatabases = context.mock(PeerDatabases.class);
		factory = context.mock(DataSchedulerFactory.class);
		pd = new PeerDatabaseDO();
		reciever = new MetaReciever(peerDatabases, factory);
		scheduler = context.mock(DataScheduler.class);
		scheduler2 = context.mock(DataScheduler.class, "Tag");
		super.setUp();
	}

	public void testRecieveMetaData() {
		context.checking(new Expectations() {
			

			{
				oneOf(peerDatabases).get();
				will(returnValue(pd));
				oneOf(factory).get("Photo");will(returnValue(scheduler));
				oneOf(factory).get("Tag");will(returnValue(scheduler2));
				oneOf(peerDatabases).save(pd);
				oneOf(scheduler).scheduleDataImport(0, 10);
				oneOf(scheduler2).scheduleDataImport(0, 10);
				
			}
		});

		reciever.recieveMetaData(new Object[] {10, 10});
		context.assertIsSatisfied();
		context.checking(new Expectations() {
			{
				oneOf(peerDatabases).get();
				will(returnValue(pd));
				oneOf(factory).get("Photo");will(returnValue(scheduler));
				oneOf(factory).get("Tag");will(returnValue(scheduler2));
				oneOf(peerDatabases).save(pd);
				oneOf(scheduler).scheduleDataImport(10, 11);
				oneOf(scheduler2).scheduleDataImport(0, 10);
			}
		});
		reciever.recieveMetaData(new Object[] {21, 10});
		context.assertIsSatisfied();

	}

}
