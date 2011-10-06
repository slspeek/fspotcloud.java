package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class GetImageData implements Action<ImageDataResult>, Serializable {

	private static final long serialVersionUID = 8799309102602466167L;

	private String photoId;

	public GetImageData(String photoId) {
		super();
		this.photoId = photoId;
	}

	public String getPhotoId() {
		return photoId;
	}

}
