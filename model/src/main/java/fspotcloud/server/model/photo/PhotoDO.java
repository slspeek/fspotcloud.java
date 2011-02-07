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
	private Blob image;
	
	@Persistent
	private Blob thumb;
	
	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#setName(java.lang.String)
	 */
	public void setId(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#getName()
	 */
	public String getId() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#setDescription(java.lang.String)
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#getDescription()
	 */
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#setTagList(java.util.List)
	 */
	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#getTagList()
	 */
	public List<String> getTagList() {
		return tagList;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#setDate(java.util.Date)
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#getDate()
	 */
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#setThumb(com.google.appengine.api.datastore.Blob)
	 */
	public void setThumb(Blob thumb) {
		this.thumb = thumb;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#getThumb()
	 */
	public Blob getThumb() {
		return thumb;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#setImage(com.google.appengine.api.datastore.Blob)
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#getImage()
	 */
	public Blob getImage() {
		return image;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#setImageLoaded(java.lang.Boolean)
	 */
	public void setImageLoaded(Boolean imageLoaded) {
		this.imageLoaded = imageLoaded;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.photo.Photo#getImageLoaded()
	 */
	public Boolean getImageLoaded() {
		return imageLoaded;
	}

}
