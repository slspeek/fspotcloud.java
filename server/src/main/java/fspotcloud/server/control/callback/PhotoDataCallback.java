package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.shared.peer.rpc.actions.PhotoData;
import fspotcloud.shared.peer.rpc.actions.PhotoDataResult;

public class PhotoDataCallback implements AsyncCallback<PhotoDataResult>, Serializable {

	private static final long serialVersionUID = 246810426240427570L;

	
	@Inject
	transient private  Photos photoManager;
	
	
	public PhotoDataCallback(Photos photoManager) {
		super();
		this.photoManager = photoManager;
	}
	
	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(PhotoDataResult result) {
		List<Photo> photoList = new ArrayList<Photo>();
		for (PhotoData photoData: result.getPhotoDataList()) {
			Photo p = recievePhoto(photoData);
			photoList.add(p);
		}
		photoManager.saveAll(photoList);
	}

	private Photo recievePhoto(PhotoData photoData) {
		String keyName = photoData.getPhotoId();
		String desc = photoData.getDesscription();
		Date date = photoData.getDate();
		List<String> tags = photoData.getTagList();

		Photo photo = photoManager.getOrNew(keyName);
		photo.setDescription(desc);
		photo.setDate(date);
		photo.setTagList(tags);
		return photo;
	}
}
