package fspotcloud.server.admin.actions;

import net.customware.gwt.dispatch.server.ExecutionContext;
import net.customware.gwt.dispatch.server.SimpleActionHandler;
import net.customware.gwt.dispatch.shared.ActionException;
import net.customware.gwt.dispatch.shared.DispatchException;

import com.google.inject.Inject;

import fspotcloud.server.model.api.PeerDatabase;
import fspotcloud.server.model.api.PeerDatabases;
import fspotcloud.shared.dashboard.actions.ResetSynchronizationPoint;
import fspotcloud.shared.dashboard.actions.VoidResult;

public class ResetSynchronizationPointHandler extends
		SimpleActionHandler<ResetSynchronizationPoint, VoidResult> {

	final private PeerDatabases defaultPeer;

	@Inject
	private ResetSynchronizationPointHandler(PeerDatabases defaultPeer) {
		super();
		this.defaultPeer = defaultPeer;
	}

	@Override
	public VoidResult execute(ResetSynchronizationPoint action,
			ExecutionContext context) throws DispatchException {
		try {
			PeerDatabase peerDatabase = defaultPeer.get();
			peerDatabase.setPeerPhotoCount(0);
			defaultPeer.save(peerDatabase);
		} catch (Exception e) {
			throw new ActionException(e);
		}
		return new VoidResult();
	}

}