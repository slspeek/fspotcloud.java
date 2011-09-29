package fspotcloud.botdispatch.model.command;

import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.appengine.api.datastore.Key;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Provider;

import fspotcloud.botdispatch.model.DatastoreTest;
import fspotcloud.botdispatch.model.PersistenceManagerProvider;
import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;
import fspotcloud.botdispatch.test.TestAction;
import fspotcloud.botdispatch.test.TestAsyncCallback;

public class CommandManagerTest extends DatastoreTest {

	public CommandManagerTest(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();

	public static TestSuite suite() {
		return new TestSuite(CommandManagerTest.class);
	}

	Commands commandManager;
	Action<?> action;
	TestAsyncCallback callback = new TestAsyncCallback();
	
	public void setUp()  {
		super.setUp();
		action = new TestAction("Jim");
		commandManager = new CommandManager(pmProvider);
	}

	public void testCreate() {
		Command cmdDO = commandManager.createAndSave(action, callback);
		Command retrieved = commandManager.popFirstCommand();
		assertEquals(cmdDO.getAction(), retrieved.getAction());
		assertNotNull(retrieved.getCallback());
		assertEquals(TestAsyncCallback.class, retrieved.getCallback().getClass());
	}
	
	
//	
//	public void testCountZero() {
//		assertEquals(0, commandManager.getCountUnderAThousend());
//	}
//
//	public void testCountTwo() {
//		commandManager.createAndSave(action, callback);
//		commandManager.createAndSave(action, callback);
//		assertEquals(2, commandManager.getCountUnderAThousend());
//	}
	
	public void testGetById() {
		Command cmd = commandManager.createAndSave(action, callback);
		System.out.println(cmd);
		
		long callbackId = cmd.getId();
		Command retrieved = commandManager.getById(callbackId);
		System.out.println(retrieved);
		assertNotNull(retrieved.getCtime());
		assertNotNull(retrieved.getCallback());
		
		assertEquals(TestAsyncCallback.class, retrieved.getCallback().getClass());
		
	}
}
