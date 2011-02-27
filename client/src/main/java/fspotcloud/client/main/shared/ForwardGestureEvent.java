package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.GwtEvent;

public class ForwardGestureEvent extends GwtEvent<ForwardGestureHandler> {
	
    public static Type<ForwardGestureHandler> TYPE = new Type<ForwardGestureHandler>();

    public Type<ForwardGestureHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(ForwardGestureHandler handler) {
        handler.onForwardGesture(this);
    }
}