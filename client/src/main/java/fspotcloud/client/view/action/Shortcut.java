package fspotcloud.client.view.action;

import java.util.logging.Logger;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.view.action.api.UserAction;

public class Shortcut implements UserAction {
	private static final Logger log = Logger
			.getLogger(Shortcut.class.getName());
	final private String description;
	final private KeyStroke key;
	final private KeyStroke alternateKey;
	final private ImageResource imageResource;
	final private String caption;
	final private Provider<? extends GwtEvent> eventProvider;
	
	final private EventBus eventBus;

	@Inject
	public Shortcut(@Assisted("caption") String caption, @Assisted("description")  String description, @Assisted("key") KeyStroke key,@Assisted("altKey") 
			KeyStroke alternateKey, @Assisted ImageResource imageResource,
			@Assisted Provider<? extends GwtEvent> eventProvider, EventBus eventBus) {
		super();
		this.caption = caption;
		this.description = description;
		this.key = key;
		this.alternateKey = alternateKey;
		this.imageResource = imageResource;
		this.eventProvider = eventProvider;
		this.eventBus = eventBus;
	}
	
	public Provider<? extends GwtEvent> getEventProvider() {
		return eventProvider;
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
		GwtEvent event = eventProvider.get();
		eventBus.fireEvent(event);
	}

	@Override
	public String getCaption() {
		return caption;
	}

}
