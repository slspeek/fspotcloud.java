package fspotcloud.client.main.shared;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.shared.NavigationEvent.ActionType;

public class NavigationEventProvider implements Provider<NavigationEvent>{
	
	final private NavigationEventFactory factory;
	final private NavigationEvent.ActionType actionType;
	
	
	@Inject
	public NavigationEventProvider(NavigationEventFactory factory,
			@Assisted ActionType actionType) {
		super();
		this.factory = factory;
		this.actionType = actionType;
	}

	@Override
	public NavigationEvent get() {
		return factory.get(actionType);
	}
}
