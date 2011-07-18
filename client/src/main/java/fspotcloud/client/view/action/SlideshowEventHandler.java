package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.SlideshowEvent;
import fspotcloud.client.place.api.Slideshow;

public class SlideshowEventHandler implements SlideshowEvent.Handler {

	final private Slideshow slideshow;
	final private EventBus eventBus;

	@Inject
	public SlideshowEventHandler(Slideshow slideshow, EventBus eventBus) {
		this.slideshow = slideshow;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(SlideshowEvent e) {
		switch (e.getActionType()) {
		case START:
			slideshow.start();
			break;
		case STOP:
			slideshow.stop();
			break;
		case FASTER:
			slideshow.faster();
			break;
		case SLOWER:
			slideshow.slower();
			break;
		default:
			break;
		}

	}

	public void init() {
		eventBus.addHandler(SlideshowEvent.TYPE, this);
	}
}
