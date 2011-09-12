package fspotcloud.server.control.reciever;

import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Blob;
import com.google.inject.Inject;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;

public class ImageReciever {
	private static final Logger log = Logger.getLogger(ImageReciever.class
			.getName());

	private final Photos photoManager;
	private final Tags tagManager;

	@Inject
	public ImageReciever(Photos photoManager, Tags tagManager) {
		this.photoManager = photoManager;
		this.tagManager = tagManager;
	}

	public int recieveImageData(String id, String exif, byte[] data,
			int imageType) {
		log.info("Recieved imagedata for : " + id);
		Photo photo = photoManager.getOrNew(id);
		Blob blob = new Blob(data);
		photo.setExifData(exif);
		if (imageType == Photo.IMAGE_TYPE_BIG) {
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
		} else if (imageType == Photo.IMAGE_TYPE_THUMB) {
			photo.setThumb(blob);
			photo.setThumbLoaded(true);
		}
		photoManager.save(photo);
		return 0;
	}
}
