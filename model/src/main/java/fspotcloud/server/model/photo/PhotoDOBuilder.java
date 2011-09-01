package fspotcloud.server.model.photo;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;

import fspotcloud.server.model.api.Photo;

public class PhotoDOBuilder {

	public Photo create(Entity entity) {
		PhotoDO photo = new PhotoDO();
		photo.setId((String) entity.getProperty("id"));
		photo.setDescription((String) entity.getProperty("description"));
		Text exif = (Text) entity.getProperty("exifData");
		if (exif != null) {
			photo.setExifData(exif.getValue());
		}
		photo.setDate((Date) entity.getProperty("date"));
		photo.setTagList((List<String>) entity.getProperty("tagList"));
		photo.setFullsizeLoaded((Boolean) entity.getProperty("fullsizeLoaded"));
		photo.setImageLoaded((Boolean) entity.getProperty("imageLoaded"));
		photo.setThumbLoaded((Boolean) entity.getProperty("thumbLoaded"));

		return photo;
	}
}
