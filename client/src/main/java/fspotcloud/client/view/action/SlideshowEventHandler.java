package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.Slideshow;
import fspotcloud.client.main.shared.SlideshowEvent;

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
		case SlideshowEvent.ACTION_START:
			slideshow.start();
			break;
		case SlideshowEvent.ACTION_STOP:
			slideshow.stop();
			break;
		case SlideshowEvent.ACTION_FASTER:
			slideshow.faster();
			break;
		case SlideshowEvent.ACTION_SLOWER:
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