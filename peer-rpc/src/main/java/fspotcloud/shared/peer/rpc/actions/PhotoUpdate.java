package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

public class PhotoUpdate implements Serializable {

	private static final long serialVersionUID = 9209829522681263384L;
	final private String photoId;

	public PhotoUpdate(String photoId) {
		super();
		this.photoId = photoId;
	}

	
	public String getPhotoId() {
		return photoId;
	}
}
