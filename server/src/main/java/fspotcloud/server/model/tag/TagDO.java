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
@PersistenceCapable(detachable="true")
public class TagDO implements Tag {
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
	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#getName()
	 */
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#setParent(com.google.appengine.api.datastore.Key)
	 */
	public void setParent(Key parent) {
		this.parent = parent;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#getParent()
	 */
	public Key getParent() {
		return parent;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#setCount(int)
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#getCount()
	 */
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#setTagName(java.lang.String)
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#getTagName()
	 */
	public String getTagName() {
		return tagName;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#setParentId(java.lang.String)
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#getParentId()
	 */
	public String getParentId() {
		return parentId;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#setCachedPhotoList(java.util.List)
	 */
	public void setCachedPhotoList(List<String> cachedPhotoList) {
		this.cachedPhotoList = cachedPhotoList;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#getCachedPhotoList()
	 */
	public List<String> getCachedPhotoList() {
		return cachedPhotoList;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#setImportIssued(boolean)
	 */
	public void setImportIssued(boolean importIssued) {
		this.importIssued = importIssued;
	}

	/* (non-Javadoc)
	 * @see fspotcloud.server.model.tag.Tag#isImportIssued()
	 */
	public boolean isImportIssued() {
		return importIssued;
	}
}
