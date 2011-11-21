package fspotcloud.taskqueuedispatch;

import static org.junit.Assert.*;

import org.apache.commons.lang.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

public class AsyncCommandTest {

	TestAction action  = new TestAction("Aute");
	TestCallback callback = new TestCallback();
	AsyncCommand<TestAction, TestResult> command;
	@Before
	public void setUp() throws Exception {
		command = new AsyncCommand<TestAction, TestResult>(callback, action);
	}

	@Test
	public void serialize() {
		byte[] bytes = SerializationUtils.serialize(command);
		AsyncCommand deserializedCommand = (AsyncCommand) SerializationUtils.deserialize(bytes);
		assertEquals(action, deserializedCommand.getAction());
	}
	@Test
	public void testGetCallback() {
		assertEquals(action, command.getAction());
	}

	@Test
	public void testGetAction() {
		assertEquals(callback, command.getCallback());
	}

}
