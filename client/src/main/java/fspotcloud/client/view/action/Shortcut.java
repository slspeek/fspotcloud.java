package fspotcloud.client.view.action;

import com.google.gwt.resources.client.ImageResource;

import fspotcloud.client.view.action.api.UserAction;

public class Shortcut implements UserAction {

	final private String description;
	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private ImageResource imageResource;
	final private Runnable runnable;
	final private String caption;

	public Shortcut(String caption,String description, KeyStroke key,
			KeyStroke alternateKey, ImageResource imageResource,
			Runnable runnable) {
		super();
		this.caption = caption;
		this.description = description;
		this.key = key;
		this.alternateKey = alternateKey;
		this.imageResource = imageResource;
		this.runnable = runnable;
	}

	public String getDescription() {
		return description;
	}

	public KeyStroke getKey() {
		return key;
	}

	public KeyStroke getAlternateKey() {
		return alternateKey;
	}

	public ImageResource getIcon() {
		return imageResource;
	}

	@Override
	public void run() {
		runnable.run();
	}

	@Override
	public String getCaption() {
		return caption;
	}

}
