package fspotcloud.client.main.view.factory;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.shared.SlideshowStatusEvent;
import fspotcloud.client.main.view.SlideshowPresenterImpl;
import fspotcloud.client.main.view.api.SlideshowView;
import fspotcloud.client.main.view.api.SlideshowView.SlideshowPresenter;
import fspotcloud.client.place.api.Slideshow;

public class SlideshowPresenterFactoryImpl implements Provider<SlideshowPresenter> {

	private final SlideshowView view;
	private final Slideshow slideshow;
	private final EventBus eventBus;
	
	@Inject
	public SlideshowPresenterFactoryImpl(SlideshowView view,
			Slideshow slideshow, EventBus eventBus) {
		super();
		this.view = view;
		this.slideshow = slideshow;
		this.eventBus = eventBus;
	}


	@Override
	public SlideshowPresenter get() {
		SlideshowPresenterImpl presenter = new SlideshowPresenterImpl(view);
		eventBus.addHandler(SlideshowStatusEvent.TYPE, presenter);
		presenter.redraw(slideshow.delay(), slideshow.isRunning());
		return presenter;
	}


	
}
