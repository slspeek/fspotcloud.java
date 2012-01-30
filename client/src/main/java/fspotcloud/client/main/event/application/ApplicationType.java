package fspotcloud.client.main.event.application;

import com.google.gwt.event.dom.client.KeyCodes;

import fspotcloud.client.view.action.KeyStroke;
import fspotcloud.client.view.action.api.ActionDef;

public enum ApplicationType implements ActionDef {

	TOGGLE_HELP("help", "Help", "Display a popup with the keyboard shortcuts",
			new KeyStroke('H'), null),
	ZOOM_IN("zoom-in", "Zoom in", "Zoom into the current image",
					new KeyStroke(107), null),
					ZOOM_OUT("zoom-out", "Zoom out", "Zoom out of the current image",
							new KeyStroke(109), null),
					
	START_DEMO(
			"demo", "Demo", "Play a demo", new KeyStroke('7'), null), TREE_FOCUS(
			"tree", "Focus tree", "Puts keyboard focus on the category tree",
			new KeyStroke(KeyCodes.KEY_ENTER), null),
			DASHBOARD("dashboard", "Dashboard",
			"Go to the dashboard (admin only)", new KeyStroke('D'), null),
	ABOUT(
			"about", "About", "About this open source project", new KeyStroke(
					'A'), null),
	HIDE_CONTROLS (
			"hide-controls", "Hide controls", "Hide treeview", new KeyStroke(
					'F'), null),
        LOGIN ("login", "Login", "Login to see more", new KeyStroke('N'), null),
        LOGOUT("logout", "Logout", "Logout", new KeyStroke('M'), null);


	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private String caption;
	final private String id;
	final private String description;

	private ApplicationType(String id, String caption, String description,
			KeyStroke key, KeyStroke alternateKey) {
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
