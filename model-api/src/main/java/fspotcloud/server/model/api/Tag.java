package fspotcloud.server.model.api;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface Tag {

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
	public abstract void setCachedPhotoList(List<String> cachedPhotoList);

	/**
	 * @return the cachedPhotoList
	 */
	public abstract List<String> getCachedPhotoList();

	/**
	 * @param importIssued the importIssued to set
	 */
	public abstract void setImportIssued(boolean importIssued);

	/**
	 * @return the importIssued
	 */
	public abstract boolean isImportIssued();

}