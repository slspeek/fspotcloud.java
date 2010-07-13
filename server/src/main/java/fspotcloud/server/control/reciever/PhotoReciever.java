package fspotcloud.server.control.reciever;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Blob;

import fspotcloud.server.model.photo.Photo;
import fspotcloud.server.model.photo.PhotoManager;
import fspotcloud.server.util.PMF;

public class PhotoReciever {
	
	private final PhotoManager photoManager = new PhotoManager();
	
	public int recieveImageData(String id, byte[] data) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Photo photo = pm.getObjectById(Photo.class, id);
		Blob blob = new Blob(data);
		//make thumb
		Blob thumb = new Blob(makeThumb(data));
		photo.setThumb(thumb);
		photo.setImage(blob);
		photo.setImageLoaded(true);
		try {
			pm.makePersistent(photo);
		} finally {
			pm.close();
		}
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
		PersistenceManager pm = PMF.get().getPersistenceManager();
		List<Photo> photoList = new ArrayList<Photo>();
		for (Object photo : list) {
			Object[] photo_as_array = (Object[]) photo;
			Photo p = recievePhoto(photo_as_array, pm);
			photoList.add(p);
		}
		
		try {
			pm.makePersistentAll(photoList);
		} finally {
			pm.close();
		}
		return 0;
	}

	private Photo recievePhoto(Object[] photo_data, PersistenceManager pm) {
		String keyName = (String) photo_data[0];
		String desc = (String) photo_data[1];
		Date date = (Date) photo_data[2];
		Object[] tags = (Object[]) photo_data[3];

		Photo photo = photoManager.getOrNew(keyName, pm);
		
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
