package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class TagDataResult implements Result, Serializable {

	private static final long serialVersionUID = 4359780265493816575L;

	private List<TagData> tagDataList;

	public TagDataResult(List<TagData> tagDataList) {
		super();
		this.tagDataList = tagDataList;
	}

	public List<TagData> getTagDataList() {
		return tagDataList;
	}
	
}
