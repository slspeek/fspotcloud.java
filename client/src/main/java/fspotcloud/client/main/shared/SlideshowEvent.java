package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class SlideshowEvent extends GwtEvent<SlideshowEvent.Handler> {

	final public static Type<SlideshowEvent.Handler> TYPE = new Type<SlideshowEvent.Handler>();

	static public enum ActionType { START, STOP, FASTER, SLOWER }
	final private ActionType actionType;

	@Inject 
	public SlideshowEvent(@Assisted ActionType actionType) {
		this.actionType = actionType;
	}

	public static interface Handler extends EventHandler {
		void onEvent(SlideshowEvent e);
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
		if (o instanceof SlideshowEvent) {
			SlideshowEvent other = (SlideshowEvent) o;
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