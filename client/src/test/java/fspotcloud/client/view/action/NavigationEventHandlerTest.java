package fspotcloud.client.view.action;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.PlaceCalculator;
import fspotcloud.client.main.shared.NavigationEvent;

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
		event = new NavigationEvent(NavigationEvent.BACK);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goAsync(with(false));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}
	public void testToggleTabularview() {
		event = new NavigationEvent(NavigationEvent.TOGGLE_RASTER_VIEW);
		context.checking(new Expectations() {
			{
				oneOf(navigator).toggleRasterView();

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testForward() {
		event = new NavigationEvent(NavigationEvent.FORWARD);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goAsync(with(true));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testHome() {
		event = new NavigationEvent(NavigationEvent.BEGIN);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goEndAsync(with(true));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testEnd() {
		event = new NavigationEvent(NavigationEvent.END);
		context.checking(new Expectations() {
			{
				oneOf(navigator).goEndAsync(with(false));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testDescreaseRasterHeight() {
		event = new NavigationEvent(NavigationEvent.DECREASE_RASTER_HEIGHT);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterHeight(with(-1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testIncreaseRasterHeight() {
		event = new NavigationEvent(NavigationEvent.INCREASE_RASTER_HEIGHT);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterHeight(with(1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testDescreaseRasterWidth() {
		event = new NavigationEvent(NavigationEvent.DECREASE_RASTER_WIDTH);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterWidth(with(-1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testIncreaseRasterWidth() {
		event = new NavigationEvent(NavigationEvent.INCREASE_RASTER_WIDTH);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterWidth(with(1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testSetRasterXxY(final int x, final int y, final int eventType) {
		event = new NavigationEvent(eventType);
		context.checking(new Expectations() {
			{
				oneOf(navigator).setRasterDimension(with(x), with(x));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}
	public void testSetRaster2x2() {
		testSetRasterXxY(2, 2, NavigationEvent.SET_RASTER_2x2);
	}

	public void testSetRaster3x3() {
		testSetRasterXxY(3, 3, NavigationEvent.SET_RASTER_3x3);
	}

	public void testSetRaster4x4() {
		testSetRasterXxY(4, 4, NavigationEvent.SET_RASTER_4x4);
	}

	public void testSetRaster5x5() {
		testSetRasterXxY(5, 5, NavigationEvent.SET_RASTER_5x5);
	}

	public void testResetRaster() {
		event = new NavigationEvent(NavigationEvent.SET_DEFAULT_RASTER);
		context.checking(new Expectations() {
			{
				oneOf(navigator).resetRasterSize();

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();

	}
}
