package fspotcloud.server.control.task.actions.intern;

import java.io.Serializable;

import net.customware.gwt.dispatch.shared.Action;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class DeletePhotos implements Action<VoidResult>, Serializable {

	private static final long serialVersionUID = -8353337263892135688L;
	private final String tagId;

	public DeletePhotos(String tagId) {
		super();
		this.tagId = tagId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTagId() {
		return tagId;
	}

	
}