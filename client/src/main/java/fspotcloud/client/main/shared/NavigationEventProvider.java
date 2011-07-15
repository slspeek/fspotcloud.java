package fspotcloud.client.main.shared;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

public class NavigationEventProvider implements Provider<NavigationEvent>{
	
	final private NavigationEventFactory factory;
	final private int actionType;
	
	
	@Inject
	public NavigationEventProvider(NavigationEventFactory factory,
			@Assisted int actionType) {
		super();
		this.factory = factory;
		this.actionType = actionType;
	}



	@Override
	public NavigationEvent get() {
		return factory.get(actionType);
	}
}
