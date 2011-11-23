package fspotcloud.server.control.task.actions.intern;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class DeletePhotos implements Action<VoidResult>, Serializable {
     
	private static final long serialVersionUID = -8357537263892135688L;
	private final List<String> keysToBeDeleted;

	public DeletePhotos(List<String> keysToBeDeleted) {
		super();
		this.keysToBeDeleted = keysToBeDeleted;
	}

	public List<String> getKeysToBeDeleted() {
		return keysToBeDeleted;
	}
	
	public boolean equals(Object o) {
		if (o instanceof DeletePhotos) {
			DeletePhotos new_name = (DeletePhotos) o;
			if (keysToBeDeleted != null) {
				return keysToBeDeleted.equals(new_name.getKeysToBeDeleted());
			} else {
				if (new_name.getKeysToBeDeleted() != null) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			return false;
		}
	}
	
}