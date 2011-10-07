package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class PhotoDataResult implements Result, Serializable {

	private static final long serialVersionUID = -2531344462382063416L;

	private List<PhotoData> photoDataList;

	public PhotoDataResult(List<PhotoData> photoDataList) {
		super();
		this.photoDataList = photoDataList;
	}

	public List<PhotoData> getPhotoDataList() {
		return photoDataList;
	}

}
