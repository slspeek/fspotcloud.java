package fspotcloud.client.main.event.raster;

import com.google.inject.Provider;

import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.UserEventHandler;
import fspotcloud.client.view.action.api.ActionDef;

public class RasterEventProvider implements Provider<UserEvent<? extends UserEventHandler>> {

	private final ActionDef action;
	
	public RasterEventProvider(ActionDef action) {
		this.action = action;
	}
	@Override
	public UserEvent<? extends UserEventHandler> get() {
		return new RasterEvent(action);
	}

}
