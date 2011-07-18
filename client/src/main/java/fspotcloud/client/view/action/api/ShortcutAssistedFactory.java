package fspotcloud.client.view.action.api;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.view.action.KeyStroke;

public interface ShortcutAssistedFactory {
	UserAction get(@Assisted("caption") String caption,
			@Assisted("description") String description,
			@Assisted("key") KeyStroke key,
			@Assisted("altKey") KeyStroke alternateKey,
			@Assisted ImageResource imageResource,
			@Assisted Provider<? extends GwtEvent> eventProvider);
}
