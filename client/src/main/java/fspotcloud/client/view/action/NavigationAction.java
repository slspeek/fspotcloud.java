package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.shared.NavigationEvent;

public class NavigationAction implements Runnable {

	final private EventBus eventBus;
	final private int actionType;

	@Inject
	public NavigationAction(@Assisted int actionType, EventBus eventBus) {
		this.eventBus = eventBus;
		this.actionType = actionType;
	}

	@Override
	public void run() {
		eventBus.fireEvent(new NavigationEvent(actionType));
	}

}
