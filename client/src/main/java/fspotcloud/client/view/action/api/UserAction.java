package fspotcloud.client.view.action.api;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.Event;

public interface UserAction extends Runnable, ActionDef {
	
	ImageResource getIcon();
	
	Provider<? extends Event> getEventProvider();
	
}
