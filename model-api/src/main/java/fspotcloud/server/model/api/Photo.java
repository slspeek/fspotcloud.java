package fspotcloud.server.model.api;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Blob;

public interface Photo {

	final int IMAGE_TYPE_THUMB = 0;
	final int IMAGE_TYPE_BIG = 1;
	
	void setId(String name);

	String getId();

	void setDescription(String description);

	String getDescription();

	void setTagList(List<String> tagList);

	List<String> getTagList();

	void setDate(Date date);

	Date getDate();

	void setThumb(Blob thumb);

	Blob getThumb();

	void setImage(Blob image);

	Blob getImage();

	void setImageLoaded(Boolean imageLoaded);

	Boolean isImageLoaded();
	
	
	void setThumbLoaded(Boolean thumbLoaded);
	
	Boolean isThumbLoaded();
	
	
	void setFullsizeLoaded(Boolean fullsizeLoaded);
	
	Boolean isFullsizeLoaded();
	
	

}