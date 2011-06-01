package fspotcloud.server.control.reciever;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;
import com.google.inject.Inject;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;
import fspotcloud.server.model.api.Tag;
import fspotcloud.server.model.api.Tags;
import fspotcloud.shared.photo.PhotoInfo;

public class PhotoReciever {
	private static final Logger log = Logger.getLogger(PhotoReciever.class
			.getName());

	private final Photos photoManager;
	private final Tags tagManager;

	@Inject
	public PhotoReciever(Photos photoManager, Tags tagManager) {
		this.photoManager = photoManager;
		this.tagManager = tagManager;
	}

	public int recieveImageData(String id, byte[] data, int imageType) {
		log.info("Recieved imagedata for : " + id);
		Photo photo = photoManager.getOrNew(id);
		List<String> tagIds = photo.getTagList();
		for (String tagId : tagIds) {
			Tag tag = tagManager.getById(tagId);
			tag.getCachedPhotoList().add(
					new PhotoInfo(photo.getId(), photo.getDescription(), photo
							.getDate()));
			tagManager.save(tag);
		}
		Blob blob = new Blob(data);
		// make thumb
		Blob thumb = new Blob(makeThumb(data));
		photo.setThumb(thumb);
		photo.setImage(blob);
		photo.setImageLoaded(true);
		photoManager.save(photo);
		return 0;
	}

	private byte[] makeThumb(byte[] imageData) {
		ImagesService imagesService = ImagesServiceFactory.getImagesService();
		Image oldImage = ImagesServiceFactory.makeImage(imageData);
		Transform resize = ImagesServiceFactory.makeResize(300, 200);
		Image newImage = imagesService.applyTransform(resize, oldImage);
		byte[] thumbData = newImage.getImageData();
		return thumbData;
	}

	public int recievePhotoData(Object[] list) {
		List<Photo> photoList = new ArrayList<Photo>();
		for (Object photo : list) {
			Object[] photo_as_array = (Object[]) photo;
			Photo p = recievePhoto(photo_as_array);
			photoList.add(p);
		}
		photoManager.saveAll(photoList);
		return 0;
	}

	private Photo recievePhoto(Object[] photo_data) {
		String keyName = (String) photo_data[0];
		String desc = (String) photo_data[1];
		Date date = (Date) photo_data[2];
		Object[] tags = (Object[]) photo_data[3];

		Photo photo = photoManager.getOrNew(keyName);
		photo.setDescription(desc);
		photo.setDate(date);
		List<String> tagList = new ArrayList<String>();
		for (Object tag : tags) {
			tagList.add(String.valueOf(tag));
		}
		photo.setTagList(tagList);
		return photo;
	}

}
