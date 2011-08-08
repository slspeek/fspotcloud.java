package fspotcloud.server.model.command;

import java.util.List;

import javax.jdo.PersistenceManager;

import junit.framework.TestSuite;

import com.google.common.collect.ImmutableList;
import com.google.inject.Provider;

import fspotcloud.server.model.DatastoreTest;
import fspotcloud.server.model.PersistenceManagerProvider;
import fspotcloud.server.model.api.Command;
import fspotcloud.server.model.api.Commands;

public class CommandManagerTest extends DatastoreTest {

	Provider<PersistenceManager> pmProvider = new PersistenceManagerProvider();

	public static TestSuite suite() {
		return new TestSuite(CommandManagerTest.class);
	}

	Commands commandManager;
	String cmd = "format";
	String cmd2 = "fdisk";
	List<String> args = ImmutableList.of("a", "b", "c");
	List<String> args2 = ImmutableList.of("aa", "-l", "d");

	public void setUp() throws Exception {
		super.setUp();
		commandManager = new CommandManager(pmProvider);
	}

	public void testCreate() {
		Command cmdDO = commandManager.create();
		assertNotNull(cmdDO);
		cmdDO.setCmd(cmd);
		cmdDO.setArgs(args);
		commandManager.save(cmdDO);
	}
	
	public void testCreate2() {
		Command cmdDO = commandManager.create();
		assertNotNull(cmdDO);
		cmdDO.setCmd(cmd2);
		cmdDO.setArgs(args2);
		commandManager.save(cmdDO);
	}

	public void testPopOldestCommand() {
		testCreate();
		Object[] command = commandManager.popOldestCommand();
		assertEquals(cmd, command[0]);
		Object[] array = (Object[]) command[1];
		List list = ImmutableList.of(array);
		
		assertEquals(args, list);
	}

	public void testAllReadyExists() {
		assertFalse(commandManager.allReadyExists(cmd, args));
		testCreate();
		assertTrue(commandManager.allReadyExists(cmd, args));
		assertFalse(commandManager.allReadyExists(cmd2, args2));
		testCreate2();
		assertTrue(commandManager.allReadyExists(cmd2, args2));
	}
	
	public void testCountZero() {
		assertEquals(0, commandManager.getCountUnderAThousend());
	}

}
