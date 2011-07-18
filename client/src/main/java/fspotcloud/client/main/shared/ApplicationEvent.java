package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class ApplicationEvent extends GwtEvent<ApplicationEvent.Handler> {

	final public static Type<ApplicationEvent.Handler> TYPE = new Type<ApplicationEvent.Handler>();

	public static enum ActionType { HELP,TOGGLE_TREE_VISIBLE, DEMO, TREE_FOCUS}
	final private ActionType actionType;

	@Inject 
	public ApplicationEvent(@Assisted ActionType actionType) {
		this.actionType = actionType;
	}

	public static interface Handler extends EventHandler {
		void onEvent(ApplicationEvent e);
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
	
	public boolean equals(Object o) {
		if (o instanceof ApplicationEvent) {
			ApplicationEvent other = (ApplicationEvent) o;
			if (getActionType() == other.getActionType()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public int hashCode() {
		return getActionType().toString().hashCode();
	}
}