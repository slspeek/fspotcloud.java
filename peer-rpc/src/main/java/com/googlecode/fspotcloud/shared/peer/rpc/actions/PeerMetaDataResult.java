package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Result;

public class PeerMetaDataResult implements Result, Serializable {
	
	private static final long serialVersionUID = 5786908411967248429L;
	
	private int tagCount;
	private int photoCount;
	
	public PeerMetaDataResult(int tagCount, int photoCount) {
		super();
		this.tagCount = tagCount;
		this.photoCount = photoCount;
	}

	public PeerMetaDataResult(){}
	
	public int getTagCount() {
		return tagCount;
	}

	public int getPhotoCount() {
		return photoCount;
	}
	

}
