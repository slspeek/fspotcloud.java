package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.Navigator;
import fspotcloud.client.main.shared.NavigationEvent;

public class NavigationEventHandler implements NavigationEvent.Handler {

	final private Navigator navigator;
	final private EventBus eventBus;

	@Inject
	public NavigationEventHandler(Navigator navigator, EventBus eventBus) {
		this.navigator = navigator;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(NavigationEvent e) {
		switch (e.getActionType()) {
		case NavigationEvent.BACK:
			navigator.go(false);
			break;
		case NavigationEvent.FORWARD:
			navigator.go(true);
			break;
		case NavigationEvent.BEGIN:
			navigator.goEnd(true);
			break;
		case NavigationEvent.END:
			navigator.goEnd(false);
			break;
		default:
			break;
		}

	}

	public void init() {
		eventBus.addHandler(NavigationEvent.TYPE, this);
	}
}
