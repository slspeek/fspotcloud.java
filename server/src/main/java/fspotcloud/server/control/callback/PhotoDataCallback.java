package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Blob;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.peer.rpc.actions.PhotoData;
import fspotcloud.shared.peer.rpc.actions.PhotoDataResult;
import fspotcloud.shared.photo.PhotoInfo;

public class PhotoDataCallback implements AsyncCallback<PhotoDataResult>, Serializable {

	private static final long serialVersionUID = 246810426240427570L;

	
	@Inject
	transient private  Photos photoManager;
	@Inject
	private transient Tags tagManager;
	
	public PhotoDataCallback(Photos photoManager, Tags tagManager) {
		super();
		this.photoManager = photoManager;
		this.tagManager = tagManager;
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
		String desc = photoData.getDescription();
		Date date = photoData.getDate();
		List<String> tags = photoData.getTagList();
		byte[] imageData = photoData.getImageData();
		byte[] thumbData = photoData.getThumbData();

		Photo photo = photoManager.getOrNew(keyName);
		photo.setDescription(desc);
		photo.setDate(date);
		photo.setTagList(tags);
		photo.setImage(new Blob(imageData));
		photo.setThumb(new Blob(thumbData));
		photo.setImageLoaded(true);
		photo.setThumbLoaded(true);
		for (String tagId : tags) {
			Tag tag = tagManager.getById(tagId);
			tag.getCachedPhotoList().add(
					new PhotoInfo(photo.getId(), photo.getDescription(),
							photo.getDate()));
			tagManager.save(tag);
		}
		return photo;
	}
}
