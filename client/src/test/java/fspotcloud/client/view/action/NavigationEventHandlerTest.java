package fspotcloud.client.view.action;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

import fspotcloud.client.main.shared.NavigationEvent;
import fspotcloud.client.place.api.Navigator;

public class NavigationEventHandlerTest extends TestCase {

	Mockery context;
	Navigator navigator;
	NavigationEventHandler handler;
	EventBus eventBus;
	NavigationEvent event;

	@Override
	protected void setUp() throws Exception {
		eventBus = new SimpleEventBus();
		context = new Mockery();
		navigator = context.mock(Navigator.class);
		handler = new NavigationEventHandler(navigator, eventBus);
		handler.init();
		super.setUp();
	}

	public void testBack() {
		event = new NavigationEvent(NavigationEvent.ActionType.BACK);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goAsync(with(false));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testForward() {
		event = new NavigationEvent(NavigationEvent.ActionType.NEXT);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goAsync(with(true));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testHome() {
		event = new NavigationEvent(NavigationEvent.ActionType.HOME);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goEndAsync(with(true));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testEnd() {
		event = new NavigationEvent(NavigationEvent.ActionType.END);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goEndAsync(with(false));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}
}
