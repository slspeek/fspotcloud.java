package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class RasterEvent extends GwtEvent<RasterEvent.Handler> {

	final public static Type<RasterEvent.Handler> TYPE = new Type<RasterEvent.Handler>();

	public static enum ActionType {
		INCREASE_RASTER_WIDTH, DECREASE_RASTER_WIDTH, INCREASE_RASTER_HEIGHT,
		DECREASE_RASTER_HEIGHT, TOGGLE_RASTER_VIEW, SET_RASTER_2x2,
		SET_RASTER_3x3, SET_RASTER_4x4, SET_RASTER_5x5,
		SET_DEFAULT_RASTER
	}

	final private ActionType actionType;

	@Inject
	public RasterEvent(@Assisted ActionType actionType) {
		this.actionType = actionType;
	}

	public static interface Handler extends EventHandler {
		void onEvent(RasterEvent e);
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