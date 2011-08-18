package fspotcloud.client.view.action;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import fspotcloud.client.main.api.Initializable;
import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.UserEventHandler;
import fspotcloud.client.main.event.navigation.NavigationEvent;
import fspotcloud.client.main.event.navigation.NavigationType;
import fspotcloud.client.place.api.Navigator;

public class NavigationEventHandler implements NavigationEvent.Handler,
		Initializable {

	final private Navigator navigator;
	final private EventBus eventBus;

	@Inject
	public NavigationEventHandler(Navigator navigator, EventBus eventBus) {
		this.navigator = navigator;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(UserEvent e) {
		switch ((NavigationType)e.getActionDef()) {
		case BACK:
			navigator.goAsync(false);
			break;
		case NEXT:
			navigator.goAsync(true);
			break;
		case HOME:
			navigator.goEndAsync(true);
			break;
		case END:
			navigator.goEndAsync(false);
			break;
		default:
			break;
		}

	}

	public void init() {
		eventBus.addHandler(NavigationEvent.TYPE, this);
	}

	
}
