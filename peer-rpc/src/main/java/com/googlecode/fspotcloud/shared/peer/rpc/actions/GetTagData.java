package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;


public class GetTagData  implements Action<TagDataResult>, Serializable {
	private static final long serialVersionUID = -2428269504170714946L;
	private int offset;
	private int count;
	
	public GetTagData(int offset, int count) {
		super();
		this.offset = offset;
		this.count = count;
	}
	
	public int getOffset() {
		return offset;
	}
	public int getCount() {
		return count;
	}
}
