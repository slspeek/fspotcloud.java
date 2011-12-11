package fspotcloud.server.control.task.actions.intern;

import java.io.Serializable;
import java.util.List;

import net.customware.gwt.dispatch.shared.Action;
import fspotcloud.shared.dashboard.actions.VoidResult;
import fspotcloud.shared.peer.rpc.actions.PhotoUpdate;

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

}