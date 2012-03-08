package com.googlecode.fspotcloud.shared.peer.rpc.actions;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

public class TagUpdateInstructionsResult implements Result, Serializable {

	private static final long serialVersionUID = -4987610701630937829L;
	final private List<PhotoUpdate> toBoUpdated;
	final private List<PhotoRemovedFromTag> toBoRemovedFromTag;

	public TagUpdateInstructionsResult(List<PhotoUpdate> toBoUpdated,
			List<PhotoRemovedFromTag> toBoRemovedFromTag) {
		super();
		this.toBoUpdated = toBoUpdated;
		this.toBoRemovedFromTag = toBoRemovedFromTag;
	}

	public List<PhotoRemovedFromTag> getToBoRemovedFromTag() {
		return toBoRemovedFromTag;
	}

	public List<PhotoUpdate> getToBoUpdated() {
		return toBoUpdated;
	}
}
