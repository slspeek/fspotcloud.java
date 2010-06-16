package fspotcloud.server.control.reciever;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;

import fspotcloud.server.photo.Photo;
import fspotcloud.server.util.PMF;

public class PhotoReciever {
	public int recievePhotoData(Object[] list) {
		List<Photo> photoList = new ArrayList<Photo>();
		for (Object photo : list) {
			Object[] photo_as_array = (Object[]) photo;
			Photo p = recievePhoto(photo_as_array);
			photoList.add(p);
		}
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistentAll(photoList);
		} finally {
			pm.close();
		}
		return 0;
	}

	private Photo recievePhoto(Object[] photo_data) {
		String keyName = (String) photo_data[0];
		String desc = (String) photo_data[1];
		Object[] tags = (Object[]) photo_data[2];
		List<String> tagList = new ArrayList<String>();

		Photo photo = new Photo();
		photo.setName(keyName);
		photo.setDescription(desc);
		for (Object tag : tags) {
			tagList.add(String.valueOf(tag));
		}
		photo.setTagList(tagList);
		return photo;
	}

}
