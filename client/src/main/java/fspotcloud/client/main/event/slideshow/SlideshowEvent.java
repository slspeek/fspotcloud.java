package fspotcloud.client.main.event.slideshow;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.UserEventHandler;
import fspotcloud.client.view.action.api.ActionDef;

public class SlideshowEvent extends UserEvent<SlideshowEvent.Handler> {

	final public static Type<SlideshowEvent.Handler> TYPE = new Type<SlideshowEvent.Handler>();

	@Inject 
	public SlideshowEvent(@Assisted ActionDef actionType) {
		super(actionType);
	}

	public static interface Handler extends UserEventHandler {
		
	}

	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onEvent(this);
	}
	
}