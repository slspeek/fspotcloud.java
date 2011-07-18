package fspotcloud.client.main;

import com.google.inject.Inject;

import fspotcloud.client.main.view.ApplicationEventHandler;
import fspotcloud.client.view.action.NavigationEventHandler;
import fspotcloud.client.view.action.RasterEventHandler;
import fspotcloud.client.view.action.SlideshowEventHandler;
import fspotcloud.client.view.action.ZoomViewEventHandler;

public class EventHandlersSetup {
	private final SlideshowEventHandler slideshow;
	private final NavigationEventHandler navigation;
	private final ApplicationEventHandler application;
	private final RasterEventHandler raster;
	final private ZoomViewEventHandler zoomViewEventHandler;

	@Inject
	public EventHandlersSetup(SlideshowEventHandler slideshow,
			NavigationEventHandler navigation,
			ApplicationEventHandler application, RasterEventHandler raster,
			ZoomViewEventHandler zoomViewEventHandler) {
		super();
		this.slideshow = slideshow;
		this.navigation = navigation;
		this.application = application;
		this.raster = raster;
		this.zoomViewEventHandler = zoomViewEventHandler;
	}

	public void setUp() {
		slideshow.init();
		navigation.init();
		application.init();
		raster.init();
		zoomViewEventHandler.init();
	}

}
