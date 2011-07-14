package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SlideshowEvent extends GwtEvent<SlideshowEvent.Handler> {

	final public static Type<SlideshowEvent.Handler> TYPE = new Type<SlideshowEvent.Handler>();

	final public static int ACTION_START = 0;
	final public static int ACTION_STOP = 1;
	final public static int ACTION_FASTER = 2;
	final public static int ACTION_SLOWER = 3;
	final private int actionType;

	public SlideshowEvent(int actionType) {
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

	public int getActionType() {
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
		return getActionType();
	}
}