package com.googlecode.fspotcloud.server.control.task.actions.intern;

import com.googlecode.fspotcloud.shared.dashboard.actions.VoidResult;
import com.googlecode.fspotcloud.shared.peer.rpc.actions.PhotoUpdate;
import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;

public class PhotoUpdateAction implements Action<VoidResult>, Serializable {

	private static final long serialVersionUID = -5470374310534233053L;
	final private List<PhotoUpdate> updates;

	public PhotoUpdateAction(List<PhotoUpdate> updates) {
		super();
		this.updates = updates;
	}

	public List<PhotoUpdate> getUpdates() {
		return updates;
	}

	public String toString() {
		return updates.toString();
	}
}