package fspotcloud.shared.dashboard.actions;

import net.customware.gwt.dispatch.shared.Action;

public class UnImportTag implements Action<VoidResult> {

	UnImportTag() {

	}

	private String tagId;
	
	
	public UnImportTag(String tagId) {
		super();
		this.tagId = tagId;
	}

	public String getTagId() {
		return tagId;
	}

}