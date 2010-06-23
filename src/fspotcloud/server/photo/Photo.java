/**
 * 
 */
package fspotcloud.server.photo;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;

/**
 * @author slspeek@gmail.com
 * 
 */
@PersistenceCapable
public class Photo {
	@PrimaryKey
	private String name;

	@Persistent
	private String description;

	@Persistent
	private Date date;

	@Persistent
	private List<String> tagList;

	@Persistent
	private Blob image;
	
	@Persistent
	private Blob thumb;
	
	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param tagList
	 *            the tagList to set
	 */
	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return the tagList
	 */
	public List<String> getTagList() {
		return tagList;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param thumb the thumb to set
	 */
	public void setThumb(Blob thumb) {
		this.thumb = thumb;
	}

	/**
	 * @return the thumb
	 */
	public Blob getThumb() {
		return thumb;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(Blob image) {
		this.image = image;
	}

	/**
	 * @return the image
	 */
	public Blob getImage() {
		return image;
	}

}
