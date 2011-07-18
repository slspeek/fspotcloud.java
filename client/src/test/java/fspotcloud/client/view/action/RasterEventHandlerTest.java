package fspotcloud.client.view.action;

import junit.framework.TestCase;

import org.jmock.Expectations;
import org.jmock.Mockery;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.SimpleEventBus;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.shared.RasterEvent;

public class RasterEventHandlerTest extends TestCase {

	Mockery context;
	Navigator navigator;
	RasterEventHandler handler;
	EventBus eventBus;
	GwtEvent event;

	@Override
	protected void setUp() throws Exception {
		eventBus = new SimpleEventBus();
		context = new Mockery();
		navigator = context.mock(Navigator.class);
		handler = new RasterEventHandler(navigator, eventBus);
		handler.init();
		super.setUp();
	}


	public void testToggleTabularview() {
		event = new RasterEvent(RasterEvent.ActionType.TOGGLE_RASTER_VIEW);
		context.checking(new Expectations() {
			{
				oneOf(navigator).toggleRasterView();

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}


	public void testDescreaseRasterHeight() {
		event = new RasterEvent(RasterEvent.ActionType.DECREASE_RASTER_HEIGHT);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterHeight(with(-1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testIncreaseRasterHeight() {
		event = new RasterEvent(RasterEvent.ActionType.INCREASE_RASTER_HEIGHT);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterHeight(with(1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testDescreaseRasterWidth() {
		event = new RasterEvent(RasterEvent.ActionType.DECREASE_RASTER_WIDTH);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterWidth(with(-1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testIncreaseRasterWidth() {
		event = new RasterEvent(RasterEvent.ActionType.INCREASE_RASTER_WIDTH);
		context.checking(new Expectations() {
			{
				oneOf(navigator).increaseRasterWidth(with(1));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}

	public void testSetRasterXxY(final int x, final int y, final RasterEvent.ActionType eventType) {
		event = new RasterEvent(eventType);
		context.checking(new Expectations() {
			{
				oneOf(navigator).setRasterDimension(with(x), with(x));

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();
	}
	public void testSetRaster2x2() {
		testSetRasterXxY(2, 2, RasterEvent.ActionType.SET_RASTER_2x2);
	}

	public void testSetRaster3x3() {
		testSetRasterXxY(3, 3, RasterEvent.ActionType.SET_RASTER_3x3);
	}

	public void testSetRaster4x4() {
		testSetRasterXxY(4, 4, RasterEvent.ActionType.SET_RASTER_4x4);
	}

	public void testSetRaster5x5() {
		testSetRasterXxY(5, 5, RasterEvent.ActionType.SET_RASTER_5x5);
	}

	public void testResetRaster() {
		event = new RasterEvent(RasterEvent.ActionType.SET_DEFAULT_RASTER);
		context.checking(new Expectations() {
			{
				oneOf(navigator).resetRasterSize();

			}
		});
		eventBus.fireEvent(event);
		context.assertIsSatisfied();

	}
}
