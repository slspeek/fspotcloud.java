package fspotcloud.client.view.action;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.view.action.api.AllUserActions;
import fspotcloud.client.view.action.api.UserAction;

public class KeyDispatcherProvider implements Provider<KeyDispatcher> {

	final private KeyDispatcher keyDispatcher = new KeyDispatcher();
	final private AllUserActions actions;
	
	@Inject
	public KeyDispatcherProvider(AllUserActions actions) {
		this.actions = actions;
		registerShortcuts();
	}

	private void registerShortcuts() {
		((AllShortcuts)actions).init();
		for (UserAction action: actions.allActions()) {
			keyDispatcher.register(action);
		}
	}
	@Override
	public KeyDispatcher get() {
		return keyDispatcher;
	}

}
