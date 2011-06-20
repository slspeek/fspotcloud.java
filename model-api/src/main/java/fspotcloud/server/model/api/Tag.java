package fspotcloud.server.model.api;

import java.util.SortedSet;

import com.google.appengine.api.datastore.Key;

import fspotcloud.shared.photo.PhotoInfo;

public interface Tag {

	/**
	 * @param name
	 *            the id to set
	 */
	public abstract void setId(String id);

	/**
	 * @return the id
	 */
	public abstract String getId();

	/**
	 * @param parent
	 *            the parent to set
	 */
	public abstract void setParent(Key parent);

	/**
	 * @return the parent
	 */
	public abstract Key getParent();

	/**
	 * @param count
	 *            the count to set
	 */
	public abstract void setCount(int count);

	/**
	 * @return the count
	 */
	public abstract int getCount();

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public abstract void setTagName(String tagName);

	/**
	 * @return the tagName
	 */
	public abstract String getTagName();

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public abstract void setParentId(String parentId);

	/**
	 * @return the parentId
	 */
	public abstract String getParentId();

	/**
	 * @param cachedPhotoList the cachedPhotoList to set
	 */
	public abstract void setCachedPhotoList(SortedSet<PhotoInfo> cachedPhotoList);

	/**
	 * @return the cachedPhotoList
	 */
	public abstract SortedSet<PhotoInfo> getCachedPhotoList();

	/**
	 * @param importIssued the importIssued to set
	 */
	public abstract void setImportIssued(boolean importIssued);

	/**
	 * @return the importIssued
	 */
	public abstract boolean isImportIssued();

}