package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.event.ActionFamily;
import fspotcloud.client.main.event.ActionMap;
import fspotcloud.client.view.action.api.UserAction;

public class KeyDispatcherProvider implements Provider<KeyDispatcher> {

	private static final Logger log = Logger
			.getLogger(KeyDispatcherProvider.class.getName());
	final private KeyDispatcher keyDispatcher = new KeyDispatcher();
	final private ActionFamily actions;

	@Inject
	public KeyDispatcherProvider(ActionFamily actions) {
		this.actions = actions;
		log.info("created");
		registerShortcuts();
	}

	private void registerShortcuts() {
		for (ActionMap actionMap: actions.allMaps()) {
			for (UserAction action : actionMap.allActions()) {
				if (action != null) {
					keyDispatcher.register(action);
				} else {
					log.warning("action was null");
				}
			}	
		}
		
	}

	@Override
	public KeyDispatcher get() {
		return keyDispatcher;
	}

}
