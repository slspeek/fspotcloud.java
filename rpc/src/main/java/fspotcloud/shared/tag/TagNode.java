package fspotcloud.shared.tag;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fspotcloud.shared.photo.PhotoInfoStore;

@SuppressWarnings("serial")
public class TagNode implements Serializable {

	private int count;

	private String description;
	
	private boolean importIssued;

	private String id;

	private TagNode parent;

	private String parentId;

	private String tagName;
	
	private PhotoInfoStore cachedPhotoList = new PhotoInfoStore(Collections.EMPTY_LIST);

	private List<TagNode> children = new ArrayList<TagNode>();

	public TagNode() {
		
	}
	
	public TagNode(String id) {
		this(id, null);
	}
	
	public TagNode(String id, String parentId) {
		this.id = id;
		this.parentId = parentId;
	}
	
	public String getId() {
		return id;
	}
	public int getCount() {
		return count;
	}
	public String getDescription() {
		return description;
	}
	public TagNode getParent() {
		return parent;
	}
	public String getParentId() {
		return parentId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setParent(TagNode parent) {
		this.parent = parent;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setChildren(List<TagNode> children) {
		this.children = children;
	}

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
		return "TagNode(" + String.valueOf(tagName) + ": " + String.valueOf(id) + " " 
		+ String.valueOf(cachedPhotoList) + ")";
	}

	public void setCachedPhotoList(PhotoInfoStore cachedPhotoList) {
		this.cachedPhotoList = cachedPhotoList;
	}

	public PhotoInfoStore getCachedPhotoList() {
		return cachedPhotoList;
	}
	
	public void setImportIssued(boolean importIssued) {
		this.importIssued = importIssued;
	}
	public boolean isImportIssued() {
		return importIssued;
	}
}
