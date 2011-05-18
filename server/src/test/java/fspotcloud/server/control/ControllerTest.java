package fspotcloud.server.control;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.server.model.api.Commands;

public class ControllerTest extends TestCase {

	public static TestSuite suite() {
		return new TestSuite(ControllerTest.class);
	}

	Mockery context = new Mockery();

	public final void testGetCommand() {
		final Commands commandsMock = context.mock(Commands.class);
		final Controller controller = new Controller(commandsMock);
		context.checking(new Expectations() {
			{
				oneOf(commandsMock).popOldestCommand();
			}
		});
		Object[] cmd = controller.getCommand();
		assertNotNull(cmd);
		context.assertIsSatisfied();
	}

}
