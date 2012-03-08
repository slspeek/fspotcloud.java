package com.googlecode.fspotcloud.client.main;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Provider;

import com.googlecode.fspotcloud.client.main.IGlobalShortcutController.Mode;
import com.googlecode.fspotcloud.client.main.event.ActionFamily;
import com.googlecode.fspotcloud.client.main.event.ActionMap;
import com.googlecode.fspotcloud.client.view.action.KeyDispatcher;
import com.googlecode.fspotcloud.client.view.action.api.ShortcutHandler;
import com.googlecode.fspotcloud.client.view.action.api.UserAction;

public class GlobalShortcutControllerFactory implements
		Provider<GlobalShortcutController> {

	final ActionFamily family;
	final Map<Mode, ShortcutHandler> map;
	final private KeyDispatcher slideshowDispatcher = new KeyDispatcher();
	final private KeyDispatcher tagViewDispatcher = new KeyDispatcher();
	final private KeyDispatcher aboutDispatcher = new KeyDispatcher();
	final private KeyDispatcher treeFocusDispatcher = new KeyDispatcher();
	final private GlobalShortcutController controller;

	@Inject
	public GlobalShortcutControllerFactory(ActionFamily family) {
		super();
		this.family = family;
		map = new HashMap<Mode, ShortcutHandler>();
		map.put(Mode.SLIDESHOW, slideshowDispatcher);
		map.put(Mode.ABOUT, aboutDispatcher);
		map.put(Mode.TAG_VIEW, tagViewDispatcher);
		map.put(Mode.TREE_VIEW, treeFocusDispatcher);
		
		putAll("Slideshow", slideshowDispatcher);
		putAll("Application", slideshowDispatcher);
		putAll("About", slideshowDispatcher);
		
		putAll("About", aboutDispatcher);
		putAll("Application", aboutDispatcher);
		
		putAll("Application", treeFocusDispatcher);
		putAll("Raster", treeFocusDispatcher);
		putAll("Slideshow", treeFocusDispatcher);

		putAll("Navigation", tagViewDispatcher);
		putAll("Raster", tagViewDispatcher);
		putAll("About", tagViewDispatcher);
		putAll("Application", tagViewDispatcher);
		putAll("Slideshow", tagViewDispatcher);
		controller = new GlobalShortcutController(map);
	}

	public GlobalShortcutController get() {
		return controller;
	}

	private void putAll(String name, KeyDispatcher dispatcher) {
		ActionMap actions = family.get(name);
		for (UserAction action : actions.allActions()) {
			dispatcher.register(action);
		}
	}

}
