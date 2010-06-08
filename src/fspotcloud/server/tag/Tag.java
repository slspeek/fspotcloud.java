/**
 * 
 */
package fspotcloud.server.tag;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

/**
 * @author slspeek@gmail.com
 * 
 */
@PersistenceCapable
public class Tag {
	@PrimaryKey
	private String name;

	@Persistent
	private String tagName;

	@Persistent
	private String description;

	@Persistent
	private String parentId;

	@Persistent
	private Key parent;

	@Persistent
	private int count;

	
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
	 * @param tagName the tagName to set
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
	 * @param parentId the parentId to set
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
}
