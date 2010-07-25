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

import fspotcloud.server.model.photo.PhotoDO;
import fspotcloud.server.model.photo.PhotoManager;

public class PhotoReciever {
	private static final Logger log = Logger.getLogger(PhotoReciever.class
			.getName());
	
	private final PhotoManager photoManager;
	
	@Inject
	public PhotoReciever(PhotoManager photoManager) {
		this.photoManager = photoManager;
	}
	
	public int recieveImageData(String id, byte[] data) {
		log.info("Recieved imagedata for : " + id);
		PhotoDO photo = photoManager.getOrNew(id);
		Blob blob = new Blob(data);
		//make thumb
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
		List<PhotoDO> photoList = new ArrayList<PhotoDO>();
		for (Object photo : list) {
			Object[] photo_as_array = (Object[]) photo;
			PhotoDO p = recievePhoto(photo_as_array);
			photoList.add(p);
		}
		photoManager.saveAll(photoList);
		return 0;
	}

	private PhotoDO recievePhoto(Object[] photo_data) {
		String keyName = (String) photo_data[0];
		String desc = (String) photo_data[1];
		Date date = (Date) photo_data[2];
		Object[] tags = (Object[]) photo_data[3];
		
		PhotoDO photo = photoManager.getOrNew(keyName);
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
