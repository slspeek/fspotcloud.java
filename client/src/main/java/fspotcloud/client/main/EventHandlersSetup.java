package fspotcloud.client.main;

import com.google.inject.Inject;

import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.view.action.AboutEventHandler;
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
	private final Initializable about;
	final private Initializable zoomViewEventHandler;

	@Inject
	public EventHandlersSetup(SlideshowEventHandler slideshow,
			NavigationEventHandler navigation,
			ApplicationEventHandler application,
			AboutEventHandler about,
			RasterEventHandler raster,
			ZoomViewEventHandler zoomViewEventHandler) {
		super();
		this.about = about;
		this.slideshow = slideshow;
		this.navigation = navigation;
		this.application = application;
		this.raster = raster;
		this.zoomViewEventHandler = zoomViewEventHandler;
	}

	public void setUp() {
		about.init();
		slideshow.init();
		navigation.init();
		application.init();
		raster.init();
		zoomViewEventHandler.init();
	}

}
