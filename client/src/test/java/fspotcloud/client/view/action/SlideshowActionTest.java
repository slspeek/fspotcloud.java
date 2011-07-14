package fspotcloud.client.view.action;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

import fspotcloud.client.main.shared.SlideshowEvent;

public class SlideshowActionTest extends TestCase {

	Mockery context;
	SlideshowAction action;
	EventBus eventBus;
	SlideshowEvent.Handler handler;
	SlideshowEvent event;
	
	@Override
	protected void setUp() throws Exception {
		
		context = new Mockery();
		eventBus = new SimpleEventBus();
		handler = context.mock(SlideshowEvent.Handler.class);
		eventBus.addHandler(SlideshowEvent.TYPE, handler);
		super.setUp();
	}

	public void testSlideshowAction() {
		action = new SlideshowAction(SlideshowEvent.ACTION_FASTER, eventBus);
		assertNotNull(action);
	}

	public void testPerform() {
		action = new SlideshowAction(SlideshowEvent.ACTION_FASTER, eventBus);
		event = new SlideshowEvent(SlideshowEvent.ACTION_FASTER);
		context.checking(new Expectations() {
			{
				oneOf(handler).onEvent(with(event));

			}
		});
		action.run();
		context.assertIsSatisfied();
	}
	
	public void testPerformSlower() {
		action = new SlideshowAction(SlideshowEvent.ACTION_SLOWER, eventBus);
		event = new SlideshowEvent(SlideshowEvent.ACTION_SLOWER);
		context.checking(new Expectations() {
			{
				oneOf(handler).onEvent(with(event));

			}
		});
		action.run();
		context.assertIsSatisfied();
	}

}
