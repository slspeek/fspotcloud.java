package fspotcloud.server.control.task.actions.intern;

import java.io.Serializable;
import java.util.SortedSet;

import net.customware.gwt.dispatch.shared.Action;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.photo.PhotoInfo;

public class PhotoImportAction implements Action<VoidResult>, Serializable {
     
	private static final long serialVersionUID = -5496531638622812304L;
	private final String tagId;
	private final SortedSet<PhotoInfo> info;
	private final int offset;
	private final int limit;
	
	public PhotoImportAction(String tagId, SortedSet<PhotoInfo> info, int offset,
			int limit) {
		super();
		this.tagId = tagId;
		this.offset = offset;
		this.limit = limit;
		this.info = info;
	}
	
	public String getTagId() {
		return tagId;
	}


	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	public SortedSet<PhotoInfo> getInfo() {
		return info;
	}

}