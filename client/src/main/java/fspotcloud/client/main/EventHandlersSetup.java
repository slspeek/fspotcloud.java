package fspotcloud.client.main;

import com.google.inject.Inject;

import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.view.action.ApplicationEventHandler;
import fspotcloud.client.view.action.NavigationEventHandler;
import fspotcloud.client.view.action.RasterEventHandler;
import fspotcloud.client.view.action.SlideshowEventHandler;
import fspotcloud.client.view.action.ZoomViewEventHandler;

public class EventHandlersSetup {
	private final Initializable slideshow;
	private final Initializable navigation;
	private final Initializable application;
	private final Initializable raster;
	final private Initializable zoomViewEventHandler;

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
