package fspotcloud.client.main;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.main.view.api.TreeView;
import fspotcloud.client.place.api.Navigator;

public class TreeFocusActionTest extends TestCase {

	Mockery context;
	TreeFocusAction action;
	TreeView view;
	Navigator navigator;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		view = context.mock(TreeView.class);
		navigator = context.mock(Navigator.class);
		action = new TreeFocusAction(navigator, view);
		super.setUp();
	}

	public void testBack() {
		context.checking(new Expectations() {
			{
				oneOf(navigator).setTreeVisible(true);
				oneOf(view).requestFocus();
			}
		});
		action.run();
		context.assertIsSatisfied();
	}

}
