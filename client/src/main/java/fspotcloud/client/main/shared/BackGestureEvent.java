package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.GwtEvent;

public class BackGestureEvent extends GwtEvent<BackGestureHandler> {
	
    public static Type<BackGestureHandler> TYPE = new Type<BackGestureHandler>();

    public Type<BackGestureHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(BackGestureHandler handler) {
        handler.onBackGesture(this);
    }
}