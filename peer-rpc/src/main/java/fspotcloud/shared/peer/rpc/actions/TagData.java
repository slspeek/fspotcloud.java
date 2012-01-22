package fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

public class TagData implements Serializable {

	private static final long serialVersionUID = -7990602627338507900L;
	private String tagId;
	private String parentId;
	private String name;
	private int count;

	public TagData(String tagId, String name, String parentId, int count) {
		super();
		this.tagId = tagId;
		this.parentId = parentId;
		this.name = name;
		this.count = count;
	}

	public String getTagId() {
		return tagId;
	}

	public String getParentId() {
		return parentId;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}
}
