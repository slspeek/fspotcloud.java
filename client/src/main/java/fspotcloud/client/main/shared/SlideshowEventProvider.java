package fspotcloud.client.main.shared;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.shared.SlideshowEvent.ActionType;

public class SlideshowEventProvider implements Provider<SlideshowEvent>{
	
	final private SlideshowEventFactory factory;
	final private SlideshowEvent.ActionType actionType;
	
	@Inject
	public SlideshowEventProvider(SlideshowEventFactory factory,
			@Assisted ActionType actionType) {
		super();
		this.factory = factory;
		this.actionType = actionType;
	}

	@Override
	public SlideshowEvent get() {
		return factory.get(actionType);
	}
}
