package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.shared.SlideshowEvent;

public class SlideshowAction implements Runnable {

	final private EventBus eventBus;
	final private int actionType;

	@Inject
	public SlideshowAction(@Assisted int actionType, EventBus eventBus) {
		this.eventBus = eventBus;
		this.actionType = actionType;
	}

	@Override
	public void run() {
		eventBus.fireEvent(new SlideshowEvent(actionType));
	}

}
