package fspotcloud.client.main.event.about;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import fspotcloud.client.main.event.UserEvent;
import fspotcloud.client.main.event.UserEventHandler;
import fspotcloud.client.view.action.api.ActionDef;

public class AboutEvent extends UserEvent<AboutEvent.Handler> {

	final public static Type<AboutEvent.Handler> TYPE = new Type<AboutEvent.Handler>();

	@Inject
	public AboutEvent(@Assisted ActionDef actionType) {
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