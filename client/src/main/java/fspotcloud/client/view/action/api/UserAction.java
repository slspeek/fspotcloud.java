package fspotcloud.client.view.action.api;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;

public interface UserAction extends Runnable, ActionDef {
	
	ImageResource getIcon();
	
	Provider<? extends GwtEvent> getEventProvider();
	
}
