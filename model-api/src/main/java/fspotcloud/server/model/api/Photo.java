package fspotcloud.server.model.api;

import fspotcloud.simplejpadao.HasSetId;
import java.util.Date;
import java.util.List;

public interface Photo extends HasSetId {

	final int IMAGE_TYPE_THUMB = 0;
	final int IMAGE_TYPE_BIG = 1;
	
	void setId(String name);

	void setDescription(String description);

	String getDescription();
	
	void setExifData(String data);
	
	String getExifData();

	void setTagList(List<String> tagList);

	List<String> getTagList();

	void setDate(Date date);

	Date getDate();

	void setThumb(byte[] thumb);

	byte[] getThumb();

	void setImage(byte[] image);

	byte[] getImage();

	void setImageLoaded(Boolean imageLoaded);

	Boolean isImageLoaded();
	
	
	void setThumbLoaded(Boolean thumbLoaded);
	
	Boolean isThumbLoaded();
	
	
	void setFullsizeLoaded(Boolean fullsizeLoaded);
	
	Boolean isFullsizeLoaded();
}