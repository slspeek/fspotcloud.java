package fspotcloud.client.main;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.place.api.Navigator;

public class ToggleFullscreenTest extends TestCase {

	Mockery context;
	ToggleFullscreenAction action;
	Navigator navigator;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		navigator = context.mock(Navigator.class);
		action = new ToggleFullscreenAction(navigator);
		super.setUp();
	}

	public void testBack() {
		context.checking(new Expectations() {
			{
				oneOf(navigator).toggleShowTagTree();
			}
		});
		action.run();
		context.assertIsSatisfied();
	}
}
