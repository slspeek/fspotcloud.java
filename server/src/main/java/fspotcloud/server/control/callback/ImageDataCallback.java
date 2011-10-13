package fspotcloud.server.control.callback;

import java.io.Serializable;
import java.util.List;

import com.google.appengine.api.datastore.Blob;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.peer.rpc.actions.ImageDataResult;
import fspotcloud.shared.photo.PhotoInfo;

public class ImageDataCallback implements AsyncCallback<ImageDataResult>, Serializable {

	private static final long serialVersionUID = -5064880106229845372L;

	@Inject
	private  transient Photos photoManager;
	@Inject
	private transient Tags tagManager;
		
	private int type;
	private String photoId;
	
	public ImageDataCallback(Photos photoManager, Tags tagManager, int type,
			String photoId) {
		super();
		this.photoManager = photoManager;
		this.tagManager = tagManager;
		this.type = type;
		this.photoId = photoId;
	}

	public ImageDataCallback(int type,
			String photoId) {
		super();
		this.type = type;
		this.photoId = photoId;
	}

	public ImageDataCallback() {
	}
	
	@Override
	public void onFailure(Throwable caught) {
	}

	@Override
	public void onSuccess(ImageDataResult result) {
		Photo photo = photoManager.getOrNew(photoId);
		Blob blob = new Blob(result.getImageBinary());
		photo.setExifData(result.getExif());
		if (type == Photo.IMAGE_TYPE_BIG) {
			List<String> tagIds = photo.getTagList();
			for (String tagId : tagIds) {
				Tag tag = tagManager.getById(tagId);
				tag.getCachedPhotoList().add(
						new PhotoInfo(photo.getId(), photo.getDescription(),
								photo.getDate()));
				tagManager.save(tag);
			}
			
			photo.setImage(blob);
			photo.setImageLoaded(true);
		} else if (type == Photo.IMAGE_TYPE_THUMB) {
			photo.setThumb(blob);
			photo.setThumbLoaded(true);
		}
		photoManager.save(photo);
		
	}
}
