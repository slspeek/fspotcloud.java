package fspotcloud.client.view.action;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.UserAction;

public class ActionsFactory {
	
	private ShortcutAssistedFactory factory;

	@Inject
	public ActionsFactory(ShortcutAssistedFactory factory) {
		super();
		this.factory = factory;
	}
	
	protected UserAction create(String caption, int key, Integer altKey,
			String description, ImageResource icon, Provider<? extends GwtEvent> provider) {
		UserAction userAction;
		if (altKey != null) {
			userAction = factory.get(caption, description, new KeyStroke(key),
					new KeyStroke(altKey), icon, provider);
		} else {
			userAction = factory.get(caption, description, new KeyStroke(key),
					null, icon, provider);
		}
		return userAction;
		
	}
}