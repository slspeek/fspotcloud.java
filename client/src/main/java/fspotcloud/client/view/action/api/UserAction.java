package fspotcloud.client.view.action.api;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;

import fspotcloud.client.view.action.KeyStroke;

public interface UserAction extends Runnable {
	KeyStroke getAlternateKey();

	KeyStroke getKey();
	
	String getCaption();

	String getDescription();

	ImageResource getIcon();
	
	Provider<? extends GwtEvent> getEventProvider();
}
