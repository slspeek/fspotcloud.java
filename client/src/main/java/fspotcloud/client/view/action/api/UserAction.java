package fspotcloud.client.view.action.api;

import com.google.gwt.resources.client.ImageResource;

import fspotcloud.client.view.action.KeyStroke;

public interface UserAction extends Runnable {
	KeyStroke getAlternateKey();

	KeyStroke getKey();
	
	String getCaption();

	String getDescription();

	ImageResource getIcon();
}
