package fspotcloud.botdispatch.model.command;

import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;
import net.customware.gwt.dispatch.shared.Action;
import net.customware.gwt.dispatch.shared.Result;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Provider;

import fspotcloud.botdispatch.bot.TestAction;
import fspotcloud.botdispatch.model.DatastoreTest;
import fspotcloud.botdispatch.model.PersistenceManagerProvider;
import fspotcloud.botdispatch.model.api.Command;
import fspotcloud.botdispatch.model.api.Commands;

public class CommandManagerTest extends DatastoreTest {

	Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();

	public static TestSuite suite() {
		return new TestSuite(CommandManagerTest.class);
	}

	Commands commandManager;
	Action<?> action;
	AsyncCallback<Result> callback;
	
	public void setUp() throws Exception {
		super.setUp();
		action = new TestAction("Jim");
		commandManager = new CommandManager(pmProvider);
	}

	public void testCreate() {
		Command cmdDO = commandManager.createAndSave(action, callback);
		Command retieved = commandManager.popFirstCommand();
		assertEquals(cmdDO.getAction(), retieved.getAction());
	}
	
	
	public void testCountZero() {
		assertEquals(0, commandManager.getCountUnderAThousend());
	}

	public void testCountTwo() {
		commandManager.createAndSave(action, callback);
		commandManager.createAndSave(action, callback);
		assertEquals(2, commandManager.getCountUnderAThousend());
	}
}
