package fspotcloud.shared.tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class TagNode implements Serializable {

	private int count;

	private String description;

	private String id;

	private TagNode parent;

	private String parentId;

	private String tagName;
	
	private List<String> cachedPhotoList;

	private List<TagNode> children = new ArrayList<TagNode>();

	
	public String getId() {
		return id;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the parent
	 */
	public TagNode getParent() {
		return parent;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @return the tagName
	 */
	public String getTagName() {
		return tagName;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(TagNode parent) {
		this.parent = parent;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @param tagName
	 *            the tagName to set
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<TagNode> children) {
		this.children = children;
	}

	/**
	 * @return the children
	 */
	public List<TagNode> getChildren() {
		return children;
	}

	public void addChild(TagNode child) {
		getChildren().add(child);
	}
	
	public boolean equals(Object other) {
		if ( !(other instanceof TagNode) || other == null) return false;
		TagNode node = (TagNode) other;
		return node.getId().equals(getId());
	}
	
	public int hashCode() {
		return getId().hashCode();
	}

	public String toString() {
		return String.valueOf(tagName) + ": " + String.valueOf(id);
	}

	public void setCachedPhotoList(List<String> cachedPhotoList) {
		this.cachedPhotoList = cachedPhotoList;
	}

	public List<String> getCachedPhotoList() {
		return cachedPhotoList;
	}
}
