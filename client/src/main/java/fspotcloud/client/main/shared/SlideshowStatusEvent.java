package fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class SlideshowStatusEvent extends
		GwtEvent<SlideshowStatusEvent.Handler> implements SlideshowStatus {

	final public static Type<SlideshowStatusEvent.Handler> TYPE = new Type<SlideshowStatusEvent.Handler>();

	public static interface Handler extends EventHandler {
		void onEvent(SlideshowStatusEvent e);
	}

	final private float delay;

	final private boolean running;

	public SlideshowStatusEvent(boolean running, float delay) {
		this.running = running;
		this.delay = delay;
	}

	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onEvent(this);
	}

	public float getDelay() {
		return delay;
	}

	public boolean isRunning() {
		return running;
	}
}