package fspotcloud.client.main;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.main.view.api.TreeView;

public class TreeFocusActionTest extends TestCase {

	Mockery context;
	TreeFocusAction action;
TreeView view ;

	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		view = context.mock(TreeView.class);
		action = new TreeFocusAction(view);
		super.setUp();
	}

	public void testBack() {
		context.checking(new Expectations() {
			{
				oneOf(view).requestFocus();
			}
		});
		action.run();
		context.assertIsSatisfied();
	}

}
