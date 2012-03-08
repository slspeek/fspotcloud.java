package com.googlecode.fspotcloud.client.main.shared;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ZoomViewEvent extends GwtEvent<ZoomViewEvent.Handler> {

	final public static Type<ZoomViewEvent.Handler> TYPE = new Type<ZoomViewEvent.Handler>();
	final private String photoId;
	final private String tagId;

	public ZoomViewEvent(String tagId, String photoId) {
		this.tagId = tagId;
		this.photoId = photoId;
	}

	public static interface Handler extends EventHandler {
		void onEvent(ZoomViewEvent e);
	}

	public Type<Handler> getAssociatedType() {
		return TYPE;
	}

	protected void dispatch(Handler handler) {
		handler.onEvent(this);
	}

	public String getPhotoId() {
		return photoId;
	}

	public String getTagId() {
		return tagId;
	}
}