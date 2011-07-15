package fspotcloud.client.main.shared;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

public class ApplicationEventProvider implements Provider<ApplicationEvent>{
	
	final private ApplicationEventFactory factory;
	final private int actionType;
	
	
	@Inject
	public ApplicationEventProvider(ApplicationEventFactory factory,
			@Assisted int actionType) {
		super();
		this.factory = factory;
		this.actionType = actionType;
	}



	@Override
	public ApplicationEvent get() {
		return factory.get(actionType);
	}
}
