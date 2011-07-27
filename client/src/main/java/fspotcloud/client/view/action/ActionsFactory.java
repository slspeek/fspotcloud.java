package fspotcloud.client.view.action;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fspotcloud.client.main.ui.Resources;
import fspotcloud.client.view.action.api.ShortcutAssistedFactory;
import fspotcloud.client.view.action.api.UserAction;

public class ActionsFactory {
	
	private ShortcutAssistedFactory factory;
	final protected Resources resources; 

	@Inject
	public ActionsFactory(ShortcutAssistedFactory factory, Resources resources) {
		super();
		this.factory = factory;
		this.resources = resources;
	}
	
	protected UserAction create(String id, String caption, int key, Integer altKey,
			String description, ImageResource icon, Provider<? extends GwtEvent> provider) {
		UserAction userAction;
		if (altKey != null) {
			userAction = factory.get(id, caption, description, new KeyStroke(key),
					new KeyStroke(altKey), icon, provider);
		} else {
			userAction = factory.get(id, caption, description, new KeyStroke(key),
					null, icon, provider);
		}
		return userAction;
		
	}
}
