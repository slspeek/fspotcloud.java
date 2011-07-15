package fspotcloud.client.main.shared;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

public class SlideshowEventProvider implements Provider<SlideshowEvent>{
	
	final private SlideshowEventFactory factory;
	final private int actionType;
	
	@Inject
	public SlideshowEventProvider(SlideshowEventFactory factory,
			@Assisted int actionType) {
		super();
		this.factory = factory;
		this.actionType = actionType;
	}

	@Override
	public SlideshowEvent get() {
		return factory.get(actionType);
	}
}
