package fspotcloud.client.view.action;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.view.action.api.UserAction;

public class KeyDispatcherTest extends TestCase {

	Mockery context;
	
	protected void setUp() throws Exception {
		super.setUp();
		context = new Mockery();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testOne() {
		KeyDispatcher dispatcher = new KeyDispatcher();
		final Runnable action = context.mock(Runnable.class);
		dispatcher.register(new Shortcut("","", new KeyStroke('a'), new KeyStroke('d'), null, action));
		context.checking(new Expectations() {
			{
				exactly(2).of(action).run();
			}
		});
		assertFalse(dispatcher.handle('w'));
		assertFalse(dispatcher.handle('x'));
		assertTrue(dispatcher.handle('a'));
		assertTrue(dispatcher.handle('d'));
		context.assertIsSatisfied();
	}
}
