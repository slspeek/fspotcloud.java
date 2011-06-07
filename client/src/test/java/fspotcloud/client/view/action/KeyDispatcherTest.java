package fspotcloud.client.view.action;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.common.collect.ImmutableList;

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
		final GestureAction action = context.mock(GestureAction.class);
		dispatcher.register(action, ImmutableList.of((int)'a', (int)'d'));
		context.checking(new Expectations() {
			{
				exactly(2).of(action).perform();
			}
		});
		assertFalse(dispatcher.handle('w'));
		assertFalse(dispatcher.handle('x'));
		assertTrue(dispatcher.handle('a'));
		assertTrue(dispatcher.handle('d'));
		context.assertIsSatisfied();
	}
}
