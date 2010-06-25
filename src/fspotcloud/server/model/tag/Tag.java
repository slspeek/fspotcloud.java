/**
 * 
 */
package fspotcloud.server.model.tag;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * Represents a Label in F-Spot
 * 
 */
@PersistenceCapable
public class Tag {
	@PrimaryKey
	private String name;

	@Persistent
	private String tagName;

	@Persistent
	private String parentId;

	@Persistent
	private Key parent;

	@Persistent
	private int count;

	@Persistent
	private boolean importIssued = false;
	
	@Persistent
	private List<String> cachedPhotoList;
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
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Key parent) {
		this.parent = parent;
	}

	/**
	 * @return the parent
	 */
	public Key getParent() {
		return parent;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param cachedPhotoList the cachedPhotoList to set
	 */
	public void setCachedPhotoList(List<String> cachedPhotoList) {
		this.cachedPhotoList = cachedPhotoList;
	}

	/**
	 * @return the cachedPhotoList
	 */
	public List<String> getCachedPhotoList() {
		return cachedPhotoList;
	}

	/**
	 * @param importIssued the importIssued to set
	 */
	public void setImportIssued(boolean importIssued) {
		this.importIssued = importIssued;
	}

	/**
	 * @return the importIssued
	 */
	public boolean isImportIssued() {
		return importIssued;
	}
}
