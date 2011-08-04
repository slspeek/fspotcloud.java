package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class NavigationEvent extends GwtEvent<NavigationEvent.Handler> {

	final public static Type<NavigationEvent.Handler> TYPE = new Type<NavigationEvent.Handler>();

	public static enum ActionType { NEXT,BACK, HOME, END, GOTO_LATEST, PAGE_UP,PAGE_DOWN}
	
	final private ActionType actionType;

	@Inject
	public NavigationEvent(@Assisted ActionType actionType) {
		this.actionType = actionType;
	}

	public static interface Handler extends EventHandler {
		void onEvent(NavigationEvent e);
	}

	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onEvent(this);
	}

	public ActionType getActionType() {
		return actionType;
	}
}