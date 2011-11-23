package fspotcloud.server.control.task.actions.intern;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class PhotoImportScheduleAction implements Action<VoidResult>, Serializable {
     
	private static final long serialVersionUID = -5496531638622812304L;
	private final String tagId;
	private final String minKey;
	private final int offset;
	private final int limit;
	
	public PhotoImportScheduleAction(String tagId, String minKey, int offset,
			int limit) {
		super();
		this.tagId = tagId;
		this.minKey = minKey;
		this.offset = offset;
		this.limit = limit;
	}
	
	public String getTagId() {
		return tagId;
	}

	public String getMinKey() {
		return minKey;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

}