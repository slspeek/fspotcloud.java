package fspotcloud.client.main.shared;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

public class RasterEventProvider implements Provider<RasterEvent>{
	
	final private RasterEventFactory factory;
	final private RasterEvent.ActionType actionType;
	
	
	@Inject
	public RasterEventProvider(RasterEventFactory factory,
			@Assisted RasterEvent.ActionType actionType) {
		super();
		this.factory = factory;
		this.actionType = actionType;
	}

	@Override
	public RasterEvent get() {
		return factory.get(actionType);
	}
}
