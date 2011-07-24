package fspotcloud.client.main.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.main.view.api.LoadNewLocation;

public class LoadNewLocationActionTest extends TestCase {

	Mockery context = new Mockery();
	LoadNewLocation loader;
	LoadNewLocationAction action;

	public void testLoadNewLocationAction() {
		loader = context.mock(LoadNewLocation.class);
		action = new LoadNewLocationAction(loader, "#");
		assertNotNull(action);
	}

	public void testRun() {
		testLoadNewLocationAction();
		context.checking(new Expectations() {

			{
				oneOf(loader).setLocation(with("#"));
			}
		});
		action.run();
		context.assertIsSatisfied();
	}

}
