package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class GetImageData implements Action<ImageDataResult>, Serializable {

	private static final long serialVersionUID = 8799309102602466167L;

	private String photoId;
	private int width;

	private int height;
	private int type;

	public GetImageData(String photoId, int width, int height, int type) {
		super();
		this.photoId = photoId;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	public String getPhotoId() {
		return photoId;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getType() {
		return type;
	}

}
