package fspotcloud.server.control.task.actions.intern;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.photo.PhotoInfo;

public class DeletePhotos implements Action<VoidResult>, Serializable {

	private static final long serialVersionUID = -8353337263892135688L;
	private final String tagId;
	private final List<PhotoInfo> toBoDeleted;

	public DeletePhotos(String tagId, List<PhotoInfo> toBoDeleted) {
		super();
		this.tagId = tagId;
		this.toBoDeleted = toBoDeleted;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTagId() {
		return tagId;
	}


	public List<PhotoInfo> getToBoDeleted() {
		return toBoDeleted;
	}

	
}