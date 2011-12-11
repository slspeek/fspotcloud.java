package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

public class PhotoRemovedFromTag implements Serializable {

	private static final long serialVersionUID = 2281991078773646131L;
	final private String photoId;
	final private String tagId;

	public PhotoRemovedFromTag(String photoId, String tagId) {
		super();
		this.photoId = photoId;
		this.tagId = tagId;
	}

	public String getPhotoId() {
		return photoId;
	}

	public String getTagId() {
		return tagId;
	}
}
