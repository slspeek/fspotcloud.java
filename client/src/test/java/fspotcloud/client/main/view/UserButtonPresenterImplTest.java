package fspotcloud.client.main.view;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import fspotcloud.client.main.view.api.UserButtonView;
import fspotcloud.client.view.action.api.UserAction;

public class UserButtonPresenterImplTest extends TestCase {

	Mockery context;
	UserAction action;
	UserButtonView view;
	UserButtonView.UserButtonPresenter presenter;
	
	
	@Override
	protected void setUp() throws Exception {
		context = new Mockery();
		action = context.mock(UserAction.class);
		view = context.mock(UserButtonView.class);
		super.setUp();
	}

	public void testConstructor() {
		presenter = new UserButtonPresenterImpl(action, view);
		context.assertIsSatisfied();
	}
	
	public void testInit() {
		testConstructor();
		context.checking(new Expectations() { {
			oneOf(action).getCaption();will(returnValue("Ape"));
			oneOf(view).setCaption("Ape");
			oneOf(view).setPresenter(presenter);
		} });
		presenter.init();
		context.assertIsSatisfied();
	}

	public void testButtonClicked() {
		testConstructor();
		context.checking(new Expectations() { {
			oneOf(action).run();
		} });
		presenter.buttonClicked();
		context.assertIsSatisfied();
	}

}
