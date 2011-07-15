package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class NavigationEvent extends GwtEvent<NavigationEvent.Handler> {

	final public static Type<NavigationEvent.Handler> TYPE = new Type<NavigationEvent.Handler>();

	final public static int FORWARD = 0;
	final public static int BACK = 1;
	final public static int BEGIN = 2;
	final public static int END = 3;
	final public static int INCREASE_RASTER_WIDTH = 4;
	final public static int DECREASE_RASTER_WIDTH = 5;
	final public static int INCREASE_RASTER_HEIGHT = 6;
	final public static int DECREASE_RASTER_HEIGHT = 7;
	final public static int TOGGLE_RASTER_VIEW = 8;
	final public static int SET_RASTER_2x2 = 9;
	final public static int SET_RASTER_3x3 = 10;
	final public static int SET_RASTER_4x4 = 11;
	final public static int SET_RASTER_5x5 = 12;
	final public static int SET_DEFAULT_RASTER = 13;
	final public static int GOTO_LATEST = 14;
	final public static int PAGE_UP = 15;
	final public static int PAGE_DOWN = 16;
	final public static int TOGGLE_TAG_TREE = 17;
	
	final private int actionType;

	@Inject
	public NavigationEvent(@Assisted int actionType) {
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

	public int getActionType() {
		return actionType;
	}
}