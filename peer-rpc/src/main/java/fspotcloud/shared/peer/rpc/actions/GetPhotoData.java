package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

public class GetPhotoData implements Action<PhotoDataResult>, Serializable {

	private static final long serialVersionUID = 3730836943695700527L;

	final private ImageSpecs imageSpecs;
	final List<String> imageKeys;
	
	public GetPhotoData(ImageSpecs imageSpecs, List<String> imageKeys) {
		super();
		this.imageSpecs = imageSpecs;
		this.imageKeys = imageKeys;
	}

	public ImageSpecs getImageSpecs() {
		return imageSpecs;
	}

	public List<String> getImageKeys() {
		return imageKeys;
	}

}
