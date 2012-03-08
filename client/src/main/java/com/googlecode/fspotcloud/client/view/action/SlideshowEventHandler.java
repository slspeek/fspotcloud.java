package com.googlecode.fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.api.Initializable;
import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.slideshow.SlideshowEvent;
import com.googlecode.fspotcloud.client.main.event.slideshow.SlideshowType;
import com.googlecode.fspotcloud.client.place.api.Slideshow;

public class SlideshowEventHandler implements SlideshowEvent.Handler, Initializable {

	final private Slideshow slideshow;
	final private EventBus eventBus;

	@Inject
	public SlideshowEventHandler(Slideshow slideshow, EventBus eventBus) {
		this.slideshow = slideshow;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(UserEvent<?> e) {
		switch ((SlideshowType)e.getActionDef()) {
		case SLIDESHOW_START:
			slideshow.start();
			break;
		case SLIDESHOW__END:
			slideshow.stop();
			break;
		case SLIDESHOW_PAUSE:
			slideshow.pause();
			break;
		case SLIDESHOW_FASTER:
			slideshow.faster();
			break;
		case SLIDESHOW_SLOWER:
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
