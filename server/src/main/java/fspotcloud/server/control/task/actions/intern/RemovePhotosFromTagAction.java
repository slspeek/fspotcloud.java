package fspotcloud.server.control.task.actions.intern;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.PhotoRemovedFromTag;

public class RemovePhotosFromTagAction implements Action<VoidResult>, Serializable {

	private static final long serialVersionUID = 7650176553018963544L;
	private final List<PhotoRemovedFromTag> toBoDeleted;
	private String tagId;

	public RemovePhotosFromTagAction(String tagId,
			List<PhotoRemovedFromTag> toBoDeleted) {
		super();
		this.toBoDeleted = toBoDeleted;
		this.tagId = tagId;
	}

	public List<PhotoRemovedFromTag> getToBoDeleted() {
		return toBoDeleted;
	}

	public String getTagId() {
		return tagId;
	}
	
	public String toString() {
		return toBoDeleted.toString();
	}
}