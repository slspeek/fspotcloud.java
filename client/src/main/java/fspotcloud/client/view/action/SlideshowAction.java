package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.shared.SlideshowEvent;

public class SlideshowAction implements GestureAction {

	final private EventBus eventBus;
	final private int actionType;

	@Inject
	public SlideshowAction(int actionType, EventBus eventBus) {
		this.eventBus = eventBus;
		this.actionType = actionType;
	}

	@Override
	public void perform() {
		eventBus.fireEvent(new SlideshowEvent(actionType));
	}

}
