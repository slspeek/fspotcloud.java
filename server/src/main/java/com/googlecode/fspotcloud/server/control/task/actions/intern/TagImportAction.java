package com.googlecode.fspotcloud.server.control.task.actions.intern;

import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;

public class TagImportAction implements Action<VoidResult>, Serializable {

	private static final long serialVersionUID = -8689981785339334225L;

	private final int offset;
	private final int limit;

	public TagImportAction(int offset, int limit) {
		super();
		this.offset = offset;
		this.limit = limit;
	}

	public int getOffset() {
		return offset;
	}

	public int getLimit() {
		return limit;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TagImportAction) {
			TagImportAction new_name = (TagImportAction) obj;
			return new_name.getLimit() == limit
					&& new_name.getOffset() == offset;
		} else {
			return false;
		}
	}

	public String toString() {
		return "(" + offset + ", " + limit + ")";
	}

}
