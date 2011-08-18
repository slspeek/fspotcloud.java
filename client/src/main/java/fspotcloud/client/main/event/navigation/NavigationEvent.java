package fspotcloud.client.main.event.navigation;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.UserEventHandler;
import fspotcloud.client.view.action.api.ActionDef;

public class NavigationEvent extends UserEvent<NavigationEvent.Handler> {

	final public static Type<NavigationEvent.Handler> TYPE = new Type<NavigationEvent.Handler>();

	public static interface Handler extends UserEventHandler {
		
	}

	@Inject
	public NavigationEvent(@Assisted ActionDef actionType) {
			super(actionType);
	}


	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onEvent(this);
	}
}