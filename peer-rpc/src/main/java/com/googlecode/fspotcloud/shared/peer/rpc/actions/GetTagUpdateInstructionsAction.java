package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;
import java.util.SortedSet;

import com.googlecode.fspotcloud.shared.photo.PhotoInfo;

import net.customware.gwt.dispatch.shared.Action;

public class GetTagUpdateInstructionsAction implements Action<TagUpdateInstructionsResult>, Serializable {
	private static final long serialVersionUID = -2428269504170714946L;
	
	final private String tagId;
	final private SortedSet<PhotoInfo> photosOnServer;
	
	public GetTagUpdateInstructionsAction(String tagId,
			SortedSet<PhotoInfo> photosOnServer) {
		super();
		this.tagId = tagId;
		this.photosOnServer = photosOnServer;
	}

	public String getTagId() {
		return tagId;
	}

	public SortedSet<PhotoInfo> getPhotosOnServer() {
		return photosOnServer;
	}
}
