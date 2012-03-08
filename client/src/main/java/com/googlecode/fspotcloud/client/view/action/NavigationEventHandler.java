package com.googlecode.fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;

import com.googlecode.fspotcloud.client.main.api.Initializable;
import com.googlecode.fspotcloud.client.main.event.UserEvent;
import com.googlecode.fspotcloud.client.main.event.navigation.NavigationEvent;
import com.googlecode.fspotcloud.client.main.event.navigation.NavigationType;
import com.googlecode.fspotcloud.client.place.api.Navigator;
import com.googlecode.fspotcloud.client.place.api.Navigator.Direction;
import com.googlecode.fspotcloud.client.place.api.Navigator.Unit;

public class NavigationEventHandler implements NavigationEvent.Handler,
		Initializable {

	private static final Logger log = Logger
	.getLogger(NavigationEventHandler.class.getName());
	final private Navigator navigator;
	final private EventBus eventBus;

	@Inject
	public NavigationEventHandler(Navigator navigator, EventBus eventBus) {
		this.navigator = navigator;
		this.eventBus = eventBus;
	}

	@Override
	public void onEvent(UserEvent e) 
	{
		log.info("On Naviagtion Event: " + e.getActionDef());
		switch ((NavigationType)e.getActionDef()) {
		case BACK:
			navigator.goAsync(Direction.BACKWARD, Unit.SINGLE);
			break;
		case NEXT:
			navigator.goAsync(Direction.FORWARD, Unit.SINGLE);
			break;
		case HOME:
			navigator.goAsync(Direction.BACKWARD, Unit.BORDER);
			break;
		case END:
			navigator.goAsync(Direction.FORWARD, Unit.BORDER);
			break;
		case PAGE_DOWN:
			navigator.goAsync(Direction.FORWARD, Unit.PAGE);
			break;
		case PAGE_UP:
			navigator.goAsync(Direction.BACKWARD, Unit.PAGE);
			break;
		case ROW_DOWN:
			navigator.goAsync(Direction.FORWARD, Unit.ROW);
			break;
		case ROW_UP:
			navigator.goAsync(Direction.BACKWARD, Unit.ROW);
			break;
		default:
			break;
		}

	}

	public void init() {
		eventBus.addHandler(NavigationEvent.TYPE, this);
	}

	
}
