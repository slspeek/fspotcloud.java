package fspotcloud.shared.dashboard.actions;

import net.customware.gwt.dispatch.shared.Action;

public class ImportTag implements Action<VoidResult> {

	ImportTag() {

	}

	private String tagId;
	private int previousCount;

	public int getPreviousCount() {
		return previousCount;
	}

	public ImportTag(String tagId, int previousCount) {
		super();
		this.tagId = tagId;
		this.previousCount = previousCount;
	}

	public String getTagId() {
		return tagId;
	}

}