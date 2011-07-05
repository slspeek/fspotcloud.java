package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;

import fspotcloud.client.main.shared.NavigationEvent;

public class NavigationAction implements GestureAction {

	final private EventBus eventBus;
	final private int actionType;

	public NavigationAction(int actionType, EventBus eventBus) {
		this.eventBus = eventBus;
		this.actionType = actionType;
	}

	@Override
	public void perform() {
		eventBus.fireEvent(new NavigationEvent(actionType));
	}

}
