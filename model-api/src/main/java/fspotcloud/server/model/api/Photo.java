package fspotcloud.server.model.api;

import java.util.Date;
import java.util.List;

import com.google.appengine.api.datastore.Blob;

public interface Photo {

	/**
	 * @param name
	 *            the name to set
	 */
	public abstract void setName(String name);

	/**
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * @param description
	 *            the description to set
	 */
	public abstract void setDescription(String description);

	/**
	 * @return the description
	 */
	public abstract String getDescription();

	/**
	 * @param tagList
	 *            the tagList to set
	 */
	public abstract void setTagList(List<String> tagList);

	/**
	 * @return the tagList
	 */
	public abstract List<String> getTagList();

	/**
	 * @param date the date to set
	 */
	public abstract void setDate(Date date);

	/**
	 * @return the date
	 */
	public abstract Date getDate();

	/**
	 * @param thumb the thumb to set
	 */
	public abstract void setThumb(Blob thumb);

	/**
	 * @return the thumb
	 */
	public abstract Blob getThumb();

	/**
	 * @param image the image to set
	 */
	public abstract void setImage(Blob image);

	/**
	 * @return the image
	 */
	public abstract Blob getImage();

	/**
	 * @param imageLoaded the imageLoaded to set
	 */
	public abstract void setImageLoaded(Boolean imageLoaded);

	/**
	 * @return the imageLoaded
	 */
	public abstract Boolean getImageLoaded();

}