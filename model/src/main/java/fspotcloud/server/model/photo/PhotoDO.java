/**
 * 
 */
package fspotcloud.server.model.photo;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;

import fspotcloud.server.model.api.Photo;

/**
 * @author slspeek@gmail.com
 * 
 */
@PersistenceCapable(detachable="true")
public class PhotoDO implements Photo {
	@PrimaryKey
	private String name;

	@Persistent
	private String description;

	@Persistent
	private Date date;

	@Persistent
	private List<String> tagList;

	@Persistent
	private Boolean imageLoaded;
	
	@Persistent
	private Boolean thumbLoaded;

	@Persistent
	private Boolean fullsizeLoaded;

	@Persistent
	private Blob image;
	
	@Persistent
	private Blob thumb;
	
	public void setId(String name) {
		this.name = name;
	}

	public String getId() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setThumb(Blob thumb) {
		this.thumb = thumb;
	}

	public Blob getThumb() {
		return thumb;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public Blob getImage() {
		return image;
	}

	public void setImageLoaded(Boolean imageLoaded) {
		this.imageLoaded = imageLoaded;
	}

	public Boolean isImageLoaded() {
		return imageLoaded;
	}

	public void setThumbLoaded(Boolean thumbLoaded) {
		this.thumbLoaded = thumbLoaded;
	}

	public Boolean isThumbLoaded() {
		return thumbLoaded;
	}

	public void setFullsizeLoaded(Boolean fullsizeLoaded) {
		this.fullsizeLoaded = fullsizeLoaded;
	}

	public Boolean isFullsizeLoaded() {
		return fullsizeLoaded;
	}

}
