package fspotcloud.server.control.reciever;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.google.inject.Inject;

import fspotcloud.server.model.api.Photo;
import fspotcloud.server.model.api.Photos;

public class PhotoReciever {
	private static final Logger log = Logger.getLogger(PhotoReciever.class
			.getName());

	private final Photos photoManager;

	@Inject
	public PhotoReciever(Photos photoManager) {
		this.photoManager = photoManager;
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
