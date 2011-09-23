package fspotcloud.client.main.event.navigation;

import com.google.gwt.event.dom.client.KeyCodes;

import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.api.ActionDef;

public enum NavigationType implements ActionDef {

	HOME("home", "Home", "Go to the first image of the category", new KeyStroke(KeyCodes.KEY_HOME), null),
	BACK("back", "Back", "Previous image in this category",new KeyStroke(KeyCodes.KEY_LEFT), null),
	NEXT("next", "Next", "Next image in this category", new KeyStroke(KeyCodes.KEY_RIGHT), null),
	END("end", "End","Go to the last image of the category", new KeyStroke( KeyCodes.KEY_END), null);

	
	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private String caption;
	final private String id;
	final private String description;

	private NavigationType(String id, String caption,
			String description,KeyStroke key, KeyStroke alternateKey) {
		this.key = key;
		this.alternateKey = alternateKey;
		this.caption = caption;
		this.id = id;
		this.description = description;
	}

	@Override
	public KeyStroke getAlternateKey() {
		return alternateKey;
	}

	@Override
	public KeyStroke getKey() {
		return key;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public String getId() {
		return id;
	}

}
